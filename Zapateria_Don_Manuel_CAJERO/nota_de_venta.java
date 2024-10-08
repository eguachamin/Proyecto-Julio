package Zapateria_Don_Manuel_CAJERO;

import Zapateria_Don_Manuel.Conection_BD;
import Zapateria_Don_Manuel.ImagenRender;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import javax.swing.JTextField;
import java.text.SimpleDateFormat;

public class nota_de_venta extends JFrame {
    private JTextField cod_cliente;
    private JButton buscarButton;
    public JTable table1;
    private JButton añadirProductoButton;
    private JLabel name_fac;
    private JLabel direcc_fac;
    private JLabel telf_fac;
    private JLabel fecha_actual;
    public JLabel subtotal;
    public JLabel iva;
    public JLabel total;
    private JPanel JPAnel_notaVenta;
    private JButton cancelarButton;
    private JButton generarButton;
    private JTextField numero_nota;
    private JLabel logo_nota;

    public nota_de_venta() {
        super("Nota de Venta / Compra");
        setContentPane(JPAnel_notaVenta);
        ImageIcon icon_adm = new ImageIcon(getClass().getResource("IMAGENES2/zapatos.png"));
        logo_nota.setIcon(icon_adm);
        // Configurar la tabla
        String[] column_names = {"Cod_Producto", "Nombre", "Precio", "Cantidad", "Imagen"};
        DefaultTableModel model = new DefaultTableModel(column_names, 0);
        table1.setModel(model);
        table1.getColumnModel().getColumn(4).setCellRenderer(new ImagenRender()); // Asegúrate de tener ImagenRender

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()) {
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String cedula_buscada = cod_cliente.getText();
                    String sql = "SELECT CONCAT(nombre_cliente, ' ', apellido_cliente) AS nombre_completo_cliente, " +
                            "now() AS FECHA_HORA, cedula_ruc_cli, direccion_cliente, telefono_cliente, email_cliente " +
                            "FROM CLIENTES WHERE cedula_ruc_cli = ?";
                    PreparedStatement strm = conectarse.prepareStatement(sql);
                    strm.setInt(1, Integer.parseInt(cedula_buscada));
                    ResultSet rs = strm.executeQuery();
                    if (rs.next()) {
                        String cedula1 = rs.getString("cedula_ruc_cli");
                        if (cedula1.equals(cedula_buscada)) {
                            name_fac.setText(rs.getString("nombre_completo_cliente"));
                            direcc_fac.setText(rs.getString("direccion_cliente"));
                            telf_fac.setText(rs.getString("telefono_cliente"));
                            fecha_actual.setText(rs.getString("FECHA_HORA"));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La cédula ingresada no existe");
                    }
                    strm.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        añadirProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                añadir_product_fact inicio = new añadir_product_fact(nota_de_venta.this); // Pasar la instancia actual
                inicio.iniciar();
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu_Cajero inicio = new Menu_Cajero();
                inicio.iniciar();
                dispose();
            }
        });
        generarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()) {
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    int numero_venta =Integer.parseInt(numero_nota.getText());
                    int cedula_buscada = Integer.parseInt(cod_cliente.getText());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = dateFormat.parse(fecha_actual.getText());
                    java.sql.Date fecha = new java.sql.Date(parsedDate.getTime());
                    double subtotal1 = Double.parseDouble(subtotal.getText().replace(",", "."));
                    double iva1 = Double.parseDouble(iva.getText().replace(",", "."));
                    double total1 = Double.parseDouble(total.getText().replace(",", "."));
                    String sql1 = "INSERT INTO CABFACTURA (NUMFAC, IDCLI,FECHA,TOTAL_FACT,IVA_FACT,TOTALFACT) VALUES (?,?,?,?,?,?)";
                    PreparedStatement strm = conectarse.prepareStatement(sql1);
                    strm.setInt(1, numero_venta);
                    strm.setInt(2, cedula_buscada);
                    strm.setDate(3, fecha);
                    strm.setDouble(4, subtotal1);
                    strm.setDouble(5, iva1);
                    strm.setDouble(6, total1);
                    int rowsInserted = strm.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "La nota se ha generado correctamente");
                        DefaultTableModel model = new DefaultTableModel(column_names,0);
                        table1.setModel(model);
                        fecha_actual.setText("");
                        name_fac.setText("");
                        direcc_fac.setText("");
                        telf_fac.setText("");
                        subtotal.setText("");
                        iva.setText("");
                        total.setText("");
                        numero_nota.setText("");
                        cod_cliente.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Verifique Datos y complete campos");
                    }
                    strm.close();


                } catch (SQLException | ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public int getNumero_nota() {
        return Integer.parseInt(numero_nota.getText()); // Convertir el texto a int
    }

    public void setNumero_nota(int numero) {
        numero_nota.setText(String.valueOf(numero)); // Actualizar el texto del JTextField
    }

    public void iniciar() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 600);
    }
    public void generarPDF(int numero_venta, int cedula_buscada, java.sql.Date fecha, double subtotal1, double iva1, double total1) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("nota_de_venta_" + numero_venta + ".pdf"));
            document.open();
            document.add(new Paragraph("Nota de Venta"));
            document.add(new Paragraph("Número de Venta: " + numero_venta));
            document.add(new Paragraph("ID Cliente: " + cedula_buscada));
            document.add(new Paragraph("Fecha: " + fecha));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Productos:"));
            document.add(new Paragraph(" "));
            PdfPTable table = new PdfPTable(5);
            table.addCell("Cod_Producto");
            table.addCell("Nombre");
            table.addCell("Precio");
            table.addCell("Cantidad");
            table.addCell("Total");

            try (Connection conectarse = Conection_BD.getConnection()) {
                if (conectarse == null) {
                    throw new SQLException("No se pudo obtener la conexión.");
                }
                String sql = "SELECT CODPROD, NOMBREPROD, VALORVENTA, CANTIDAD_ELEG,TOTALDET FROM DETFACTURA WHERE NUMFAC = ?";
                PreparedStatement strm = conectarse.prepareStatement(sql);
                strm.setInt(1, numero_venta);
                ResultSet rs = strm.executeQuery();
                while (rs.next()) {
                    table.addCell(rs.getString("CODPROD"));
                    table.addCell(rs.getString("NOMBREPROD"));
                    table.addCell(rs.getString("VALORVENTA"));
                    table.addCell(rs.getString("CANTIDAD_ELEG"));
                    table.addCell(rs.getString("TOTALDET"));
                }
                strm.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            document.add(table);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Subtotal: " + subtotal1));
            document.add(new Paragraph("IVA: " + iva1));
            document.add(new Paragraph("Total: " + total1));

            JOptionPane.showMessageDialog(null, "PDF generado correctamente.");
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        } finally {
            document.close();
        }
    }


}
