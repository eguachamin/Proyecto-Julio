package Zapateria_Don_Manuel_CAJERO;

import Zapateria_Don_Manuel.Conection_BD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public cliente_acciones() {
        super("GESTION DE CLIENTES");
        setContentPane(JPanel_Ingresar);

        buscarButton.addActionListener(new ActionListener() {
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
    }

    public void iniciar(){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500,400);
    }
}
