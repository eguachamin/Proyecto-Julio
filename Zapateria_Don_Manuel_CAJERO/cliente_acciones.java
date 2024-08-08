package Zapateria_Don_Manuel_CAJERO;

import Zapateria_Don_Manuel.Conection_BD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class cliente_acciones extends JFrame{
    private JTabbedPane JPanel_Ingresar;
    private JTextField cedula_ing;
    private JTextField name_ing;
    private JTextField last_ing;
    private JTextField direcc_ing;
    private JTextField telf_ing;
    private JTextField mail_ing;
    private JButton buscarButton;
    private JButton ingresarButton;
    private JButton limpiarButton;
    private JTextField cedu_act;
    private JTextField name_act;
    private JTextField last_act;
    private JTextField direcc_act;
    private JTextField telf_act;
    private JTextField mail_act;
    private JButton buscarButton1;
    private JButton limpiarButton1;
    private JButton actualizarButton;
    private JTextField cod_ing;
    private JButton buscarButton2;
    private JButton limpiarButton2;
    private JButton ELIMINARButton;
    private JLabel name_searched;
    private JLabel last_searched;
    private JLabel direcc_searched;
    private JLabel telf_searched;
    private JLabel mail_searched;
    private JButton menuButton;
    private JButton menuButton1;
    private JButton MENUButton;

    public cliente_acciones() {
        super("GESTION DE CLIENTES");
        setContentPane(JPanel_Ingresar);

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String cedula_ruc = cedula_ing.getText();
                    String name = name_ing.getText();
                    String apellido =last_ing.getText();
                    String direccion = direcc_ing.getText();
                    String telefono = telf_ing.getText();
                    String correo1 = mail_ing.getText();
                    String sql = "Insert into CLIENTES ( cedula_ruc_cli,nombre_cliente,apellido_cliente,direccion_cliente,telefono_cliente,email_cliente) values (?,?,?,?,?,?)";
                    PreparedStatement strm = conectarse.prepareStatement(sql);
                    strm.setInt(1,Integer.parseInt(cedula_ruc));
                    strm.setString(2,name);
                    strm.setString(3,apellido);
                    strm.setString(4,direccion);
                    strm.setInt(5,Integer.parseInt(telefono));
                    strm.setString(6,correo1);
                    int rowAffected = strm.executeUpdate();
                    if (rowAffected>0)
                    {
                        JOptionPane.showMessageDialog(null,"Cliente ingresado correctamente");
                        cedula_ing.setText("");
                        name_ing.setText("");
                        last_ing.setText("");
                        direcc_ing.setText("");
                        telf_ing.setText("");
                        mail_ing.setText("");

                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Registro no ingresado, verifique los datos");
                    }
                    strm.close();
                }
                catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String cedula_buscada = cedula_ing.getText();
                    String sql = "SELECT * FROM CLIENTES WHERE cedula_ruc_cli=?";
                    PreparedStatement strm = conectarse.prepareStatement(sql);
                    strm.setInt(1,Integer.parseInt(cedula_buscada));
                    ResultSet rs = strm.executeQuery();
                    if (rs.next()){
                        String cedula1= rs.getString("cedula_ruc_cli");
                        if (cedula1.equals(cedula_buscada)){
                            name_ing.setText(rs.getString("nombre_cliente"));
                            last_ing.setText(rs.getString("apellido_cliente"));
                            direcc_ing.setText(rs.getString("direccion_cliente"));
                            telf_ing.setText(rs.getString("telefono_cliente"));
                            mail_ing.setText(rs.getString("email_cliente"));
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"La cédula ingresada no existe");
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
                cedula_ing.setText("");
                name_ing.setText("");
                last_ing.setText("");
                direcc_ing.setText("");
                telf_ing.setText("");
                mail_ing.setText("");
            }
        });
        buscarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String cedula_buscada = cedu_act.getText();
                    String sql = "SELECT * FROM CLIENTES WHERE cedula_ruc_cli=?";
                    PreparedStatement strm = conectarse.prepareStatement(sql);
                    strm.setInt(1,Integer.parseInt(cedula_buscada));
                    ResultSet rs = strm.executeQuery();
                    if (rs.next()){
                        String cedula1= rs.getString("cedula_ruc_cli");
                        if (cedula1.equals(cedula_buscada)){
                            name_act.setText(rs.getString("nombre_cliente"));
                            last_act.setText(rs.getString("apellido_cliente"));
                            direcc_act.setText(rs.getString("direccion_cliente"));
                            telf_act.setText(rs.getString("telefono_cliente"));
                            mail_act.setText(rs.getString("email_cliente"));
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"La cédula ingresada no existe");
                    }
                    strm.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        limpiarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cedu_act.setText("");
                name_act.setText("");
                last_act.setText("");
                direcc_act.setText("");
                telf_act.setText("");
                mail_act.setText("");
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String cedula_ruc = cedu_act.getText();
                    String name = name_act.getText();
                    String apellido =last_act.getText();
                    String direccion = direcc_act.getText();
                    String telefono = telf_act.getText();
                    String correo1 = mail_act.getText();
                    String sql = "UPDATE CLIENTES SET nombre_cliente=?,apellido_cliente=?,direccion_cliente=?,telefono_cliente=?,email_cliente=? WHERE cedula_ruc_cli=?";
                    PreparedStatement strm = conectarse.prepareStatement(sql);
                    strm.setString(1,name);
                    strm.setString(2,apellido);
                    strm.setString(3,direccion);
                    strm.setInt(4,Integer.parseInt(telefono));
                    strm.setString(5,correo1);
                    strm.setInt(6,Integer.parseInt(cedula_ruc));
                    int rowAffected = strm.executeUpdate();
                    if (rowAffected>0)
                    {
                        JOptionPane.showMessageDialog(null,"Cliente actualizado correctamente");
                        cedu_act.setText("");
                        name_act.setText("");
                        last_act.setText("");
                        direcc_act.setText("");
                        telf_act.setText("");
                        mail_act.setText("");

                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Registro no ingresado, verifique los datos");
                    }
                    strm.close();
                }
                catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buscarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String cedula_buscada = cod_ing.getText();
                    String sql = "SELECT * FROM CLIENTES WHERE cedula_ruc_cli=?";
                    PreparedStatement strm = conectarse.prepareStatement(sql);
                    strm.setInt(1,Integer.parseInt(cedula_buscada));
                    ResultSet rs = strm.executeQuery();
                    if (rs.next()){
                        String cedula1= rs.getString("cedula_ruc_cli");
                        if (cedula1.equals(cedula_buscada)){
                            name_searched.setText(rs.getString("nombre_cliente"));
                            last_searched.setText(rs.getString("apellido_cliente"));
                            direcc_searched.setText(rs.getString("direccion_cliente"));
                            telf_searched.setText(rs.getString("telefono_cliente"));
                            mail_searched.setText(rs.getString("email_cliente"));
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"La cédula ingresada no existe");
                    }
                    strm.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        limpiarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cod_ing.setText("");
                name_searched.setText("");
                last_searched.setText("");
                direcc_searched.setText("");
                telf_searched.setText("");
                mail_searched.setText("");
            }
        });
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String codbusca1=cod_ing.getText();
                    String sql = "DELETE FROM CLIENTES WHERE cedula_ruc_cli=?";
                    PreparedStatement stm = conectarse.prepareStatement(sql);
                    stm.setString(1,codbusca1);
                    int affectedRows = stm.executeUpdate();
                    if (affectedRows> 0){
                        JOptionPane.showMessageDialog(null,"El Cliente se encuentra eliminado");
                        cod_ing.setText("");
                        name_searched.setText("");
                        last_searched.setText("");
                        direcc_searched.setText("");
                        telf_searched.setText("");
                        mail_searched.setText("");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"El codigo ingresado es incorrecto");
                        cod_ing.setText("");
                    }
                    stm.close();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu_Cajero inicio = new Menu_Cajero();
                inicio.iniciar();
                dispose();
            }
        });
        menuButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu_Cajero inicio = new Menu_Cajero();
                inicio.iniciar();
                dispose();
            }
        });
        MENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu_Cajero inicio = new Menu_Cajero();
                inicio.iniciar();
                dispose();
            }
        });
    }

    public void iniciar(){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500,400);
    }
}
