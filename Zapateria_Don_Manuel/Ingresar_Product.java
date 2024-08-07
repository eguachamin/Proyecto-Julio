package Zapateria_Don_Manuel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ingresar_Product extends JFrame {
    private JPanel JPanel_IntoProduct;
    private JTextField cod_ing;
    private JTextField name_ing;
    private JTextField details_ing;
    private JTextField precUnit_ing;
    private JButton buscarButton;
    private JButton limpiarButton;
    private JButton actualizarButton;
    private JButton ingresarButton;
    private JTextField stock_ing;
    private JLabel imagenproducto;
    private JButton menuButton;

    public Ingresar_Product() {
        super("Ingreso Productos");
        setContentPane(JPanel_IntoProduct);
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String codigo = cod_ing.getText();
                    String name = name_ing.getText();
                    String description =details_ing.getText();
                    String precio_unitario = precUnit_ing.getText();
                    String stock = stock_ing.getText();
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        String rutaImagen = ((File) selectedFile).getAbsolutePath();
                        String nombreImagen = selectedFile.getName();
                        FileInputStream fis = new FileInputStream(new File(rutaImagen));

                        String sql = "Insert into PRODUCTOS ( codigo_producto,nombre,descripcion,precio,cantidad,imagen_nombre,ruta_imagen) values (?,?,?,?,?,?,?)";
                        PreparedStatement strm = conectarse.prepareStatement(sql);
                        strm.setInt(1, Integer.parseInt(codigo));
                        strm.setString(2, name);
                        strm.setString(3, description);
                        strm.setDouble(4, Double.parseDouble(precio_unitario));
                        strm.setInt(5, Integer.parseInt(stock));
                        strm.setString(6, nombreImagen);
                        strm.setBinaryStream(7, fis,(int) new File(rutaImagen).length());
                        int rowAffected = strm.executeUpdate();
                        if (rowAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Producto ingresado correctamente");
                            cod_ing.setText("");
                            name_ing.setText("");
                            details_ing.setText("");
                            precUnit_ing.setText("");
                            stock_ing.setText("");

                        } else {
                            JOptionPane.showMessageDialog(null, "Registro no ingresado, verifique los datos");
                        }
                        strm.close();
                    }
                }
                catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String codigo = cod_ing.getText();
                    String sql = "SELECT codigo_producto,nombre,descripcion,precio,cantidad,imagen_nombre,ruta_imagen FROM PRODUCTOS WHERE codigo_producto=?";
                        PreparedStatement strm = conectarse.prepareStatement(sql);
                        strm.setInt(1, Integer.parseInt(codigo));
                        ResultSet rs = strm.executeQuery();
                        if (rs.next()) {
                            String codigo1= rs.getString("codigo_producto");
                            if (codigo1.equals(codigo)){
                                name_ing.setText(rs.getString("nombre"));
                                details_ing.setText(rs.getString("descripcion"));
                                precUnit_ing.setText(rs.getString("precio"));
                                stock_ing.setText(rs.getString("cantidad"));
                                byte[] imgBytes = rs.getBytes("ruta_imagen");
                                ImageIcon icon = null;
                                if (imgBytes != null) {
                                    Image img = Toolkit.getDefaultToolkit().createImage(imgBytes);
                                    icon = new ImageIcon(img.getScaledInstance(64, 64, Image.SCALE_SMOOTH));
                                    imagenproducto.setIcon(icon);
                                }

                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Registro no ingresado, verifique los datos");
                        }
                        strm.close();
                    }

                catch (SQLException ex) {
                    throw new RuntimeException(ex);
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
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String codigo = cod_ing.getText();
                    String name = name_ing.getText();
                    String description =details_ing.getText();
                    String precio_unitario = precUnit_ing.getText();
                    String stock = stock_ing.getText();
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        String rutaImagen = ((File) selectedFile).getAbsolutePath();
                        String nombreImagen = selectedFile.getName();
                        FileInputStream fis = new FileInputStream(new File(rutaImagen));

                        String sql = "UPDATE PRODUCTOS SET nombre=?,descripcion=?,precio=?,cantidad=?,imagen_nombre=?,ruta_imagen=? WHERE codigo_producto=?";
                        PreparedStatement strm = conectarse.prepareStatement(sql);
                        strm.setString(1, name);
                        strm.setString(2, description);
                        strm.setDouble(3, Double.parseDouble(precio_unitario));
                        strm.setInt(4, Integer.parseInt(stock));
                        strm.setString(5, nombreImagen);
                        strm.setBinaryStream(6, fis,(int) new File(rutaImagen).length());
                        strm.setInt(7,Integer.parseInt(codigo));
                        int rowAffected = strm.executeUpdate();
                        if (rowAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
                            cod_ing.setText("");
                            name_ing.setText("");
                            details_ing.setText("");
                            precUnit_ing.setText("");
                            stock_ing.setText("");
                            ImageIcon icon_img1 = new ImageIcon(getClass().getResource("IMAGENES/imagen.png"));
                            imagenproducto.setIcon(icon_img1);

                        } else {
                            JOptionPane.showMessageDialog(null, "Registro no ingresado, verifique los datos");
                        }
                        strm.close();
                    }
                }
                catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cod_ing.setText("");
                name_ing.setText("");
                details_ing.setText("");
                precUnit_ing.setText("");
                stock_ing.setText("");
                ImageIcon icon_img = new ImageIcon(getClass().getResource("IMAGENES/imagen.png"));
                imagenproducto.setIcon(icon_img);
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
