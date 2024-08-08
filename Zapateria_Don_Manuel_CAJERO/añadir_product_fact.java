package Zapateria_Don_Manuel_CAJERO;

import Zapateria_Don_Manuel.Conection_BD;
import Zapateria_Don_Manuel.ImagenRender;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class añadir_product_fact extends JFrame {
    private JTextField cod_search;
    private JTextField cantidad_ing;
    private JButton añadirButton;
    private JButton cancelarButton;
    private JButton buscarButton;
    private JButton limpiarButton;
    private JLabel name_product;
    private JLabel precio_unit;
    private JPanel JPanel_aniadirProd;
    private JLabel foto_prod;
    private JLabel Venta_num;
    private nota_de_venta notaDeVenta;
    public JTable table1; // La tabla se muestra en nota_de_venta

    public añadir_product_fact(nota_de_venta notaDeVenta) {
        super("Añadir Producto");
        setContentPane(JPanel_aniadirProd);

        this.notaDeVenta = notaDeVenta;
        Venta_num.setText(String.valueOf(notaDeVenta.getNumero_nota())); // Mostrar el número de nota

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()) {
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String codigo = cod_search.getText();
                    String sql = "SELECT codigo_producto, nombre, descripcion, precio, cantidad, imagen_nombre, ruta_imagen FROM PRODUCTOS WHERE codigo_producto=?";
                    PreparedStatement strm = conectarse.prepareStatement(sql);
                    strm.setInt(1, Integer.parseInt(codigo));
                    ResultSet rs = strm.executeQuery();
                    if (rs.next()) {
                        name_product.setText(rs.getString("nombre"));
                        precio_unit.setText(rs.getString("precio"));
                        byte[] imgBytes = rs.getBytes("ruta_imagen");
                        ImageIcon icon = null;
                        if (imgBytes != null) {
                            Image img = Toolkit.getDefaultToolkit().createImage(imgBytes);
                            icon = new ImageIcon(img.getScaledInstance(64, 64, Image.SCALE_SMOOTH));
                            foto_prod.setIcon(icon);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Código no encontrado");
                    }
                    strm.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cod_search.setText("");
                name_product.setText("");
                precio_unit.setText("");
                ImageIcon icon_img = new ImageIcon(getClass().getResource("IMAGENES2/imagen.png"));
                foto_prod.setIcon(icon_img);
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notaDeVenta.iniciar(); // Asegúrate de que la instancia de nota_de_venta se muestra
                dispose();
            }
        });

        añadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()) {
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }

                    // Obtener los datos del producto
                    String codigo = cod_search.getText();
                    String nombre = name_product.getText();
                    double precio_unitario = Double.parseDouble(precio_unit.getText());
                    int cantidad_deseada = Integer.parseInt(cantidad_ing.getText());
                    double totalDet = precio_unitario * cantidad_deseada;
                    int numero_nota = notaDeVenta.getNumero_nota(); // Obtener el número de nota

                    // Insertar en la base de datos
                    String sqlInsert = "INSERT INTO DETFACTURA (NUMFAC, CODPROD, NOMBREPROD, CANTIDAD_ELEG, VALORVENTA, TOTALDET) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement strmInsert = conectarse.prepareStatement(sqlInsert);
                    strmInsert.setInt(1, numero_nota);
                    strmInsert.setInt(2, Integer.parseInt(codigo));
                    strmInsert.setString(3, nombre);
                    strmInsert.setInt(4, cantidad_deseada );
                    strmInsert.setDouble(5,precio_unitario );
                    strmInsert.setDouble(6, totalDet);
                    int rowAffected = strmInsert.executeUpdate();
                    if (rowAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Producto ingresado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Registro no ingresado, verifique los datos");
                    }
                    strmInsert.close();

                    // Actualizar la tabla
                    String sqlSelect = "SELECT p.codigo_producto, p.nombre, p.precio, d.CANTIDAD_ELEG, p.ruta_imagen " +
                            "FROM PRODUCTOS p, DETFACTURA d WHERE p.codigo_producto = d.CODPROD AND  d.Numfac=? ";
                    PreparedStatement strmSelect = conectarse.prepareStatement(sqlSelect);
                    strmSelect.setInt(1, numero_nota);
                    ResultSet rs = strmSelect.executeQuery();
                    DefaultTableModel model = (DefaultTableModel) notaDeVenta.table1.getModel();
                    model.setRowCount(0); // Limpiar la tabla antes de volver a llenarla
                    if (!rs.isBeforeFirst()) { // Verificar si el ResultSet está vacío
                        JOptionPane.showMessageDialog(null, "El código de producto ingresado no existe.");
                        return;
                    }
                    while (rs.next()) {
                        int id = rs.getInt("codigo_producto");
                        String nombre1 = rs.getString("nombre");
                        double precio = rs.getDouble("precio");
                        int stock = rs.getInt("CANTIDAD_ELEG");
                        byte[] imgBytes = rs.getBytes("ruta_imagen");
                        ImageIcon icon = null;
                        if (imgBytes != null) {
                            Image img = Toolkit.getDefaultToolkit().createImage(imgBytes);
                            icon = new ImageIcon(img.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
                        }
                        model.addRow(new Object[]{id, nombre1, precio, stock, icon});
                    }
                    strmSelect.close();
                    //Colocar Datos de calculo
                    String sqlCalculos = "SELECT SUM(TOTALDET) AS SUBTOTAL, SUM(TOTALDET) * 0.15 AS IVA, SUM(TOTALDET) + (SUM(TOTALDET) * 0.15) AS TOTALFACTURA " +
                            "FROM DETFACTURA WHERE NUMFAC = ? GROUP BY NUMFAC";
                    PreparedStatement strmCalculo = conectarse.prepareStatement(sqlCalculos);
                    strmCalculo.setInt(1, numero_nota);
                    ResultSet rs1 = strmCalculo.executeQuery();
                    if (!rs1.isBeforeFirst()) { // Verificar si el ResultSet está vacío
                        JOptionPane.showMessageDialog(null, "El código de producto ingresado no existe.");
                        return;
                    }
                    while (rs1.next()) {
                        double subtotal = rs1.getDouble("SUBTOTAL");
                        double iva = rs1.getDouble("IVA");
                        double totalFactura = rs1.getDouble("TOTALFACTURA");

                        // Actualizar los campos en notaDeVenta
                        notaDeVenta.subtotal.setText(String.format("%.2f", subtotal));
                        notaDeVenta.iva.setText(String.format("%.2f", iva));
                        notaDeVenta.total.setText(String.format("%.2f", totalFactura));
                    }
                    strmCalculo.close();
                    // ACTUALIZAR STOCK
                    String sqlactualizar = "UPDATE PRODUCTOS SET Cantidad= Cantidad - ? WHERE codigo_producto=?";
                    PreparedStatement strmStock = conectarse.prepareStatement(sqlactualizar);
                    strmStock.setInt(1, cantidad_deseada );
                    strmStock.setInt(2, Integer.parseInt(codigo));
                    strmStock.executeUpdate();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un código de producto válido.");
                }


            }
        });
    }

    public void iniciar() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 400);
    }
}
