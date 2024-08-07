package Zapateria_Don_Manuel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Eliminar_Products extends JFrame {
    private JPanel JPanel_ElimProd;
    private JTextField cod_buscar;
    private JButton buscarButton;
    private JButton limpiarButton;
    private JButton eliminarButton;
    private JTable table1;
    private JButton menuButton;


    public Eliminar_Products() {
        super("Eliminar Producto");
        setContentPane(JPanel_ElimProd);

        String[] column_names = {"Cod_Producto","Nombre","Descripción","Precio","Cantidad","Imagen"};
        DefaultTableModel model = new DefaultTableModel(column_names,0);
        table1.setModel(model);
        table1.getColumnModel().getColumn(5).setCellRenderer(new ImagenRender());

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String cedula_buscada = cod_buscar.getText();
                    String sql = "SELECT codigo_producto,nombre,descripcion,precio,cantidad,ruta_imagen FROM PRODUCTOS WHERE codigo_producto=?";
                    PreparedStatement strm = conectarse.prepareStatement(sql);
                    strm.setInt(1,Integer.parseInt(cedula_buscada));
                    ResultSet rs = strm.executeQuery();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                    if (!rs.isBeforeFirst()) { // Verificar si el ResultSet está vacío
                        JOptionPane.showMessageDialog(null, "El código de producto ingresado no existe.");
                        return;
                    }
                    while (rs.next()){
                        int id = rs.getInt("codigo_producto");
                        String nombre = rs.getString("nombre");
                        String descripcion = rs.getString("descripcion");
                        double precio = rs.getDouble("precio");
                        int stock = rs.getInt("cantidad");
                        byte[] imgBytes = rs.getBytes("ruta_imagen");
                        ImageIcon icon = null;
                        if (imgBytes != null) {
                            Image img = Toolkit.getDefaultToolkit().createImage(imgBytes);
                            icon = new ImageIcon(img.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
                        }
                        model.addRow(new Object[]{id, nombre, descripcion, precio, stock, icon});
                    }
                    strm.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un código de producto válido.");
                }
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu_Admin inicio = new Menu_Admin();
                inicio.iniciar();
                dispose();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String codbusca1=cod_buscar.getText();
                    String sql = "DELETE FROM PRODUCTOS WHERE codigo_producto=?";
                    PreparedStatement stm = conectarse.prepareStatement(sql);
                    stm.setString(1,codbusca1);
                    int affectedRows = stm.executeUpdate();
                    if (affectedRows> 0){
                        JOptionPane.showMessageDialog(null,"El producto se encuentra eliminado");
                        DefaultTableModel model = new DefaultTableModel(column_names,0);
                        table1.setModel(model);
                        cod_buscar.setText("");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"El codigo ingresado es incorrecto");
                    }
                    stm.close();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = new DefaultTableModel(column_names,0);
                table1.setModel(model);
                cod_buscar.setText("");
            }
        });
    }

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
