package Zapateria_Don_Manuel_CAJERO;

import Zapateria_Don_Manuel.Conection_BD;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_Cajero extends JFrame{
    private JPanel JPanel_login;
    private JTextField user_ing;
    private JButton INGRESARButton;
    private JPasswordField pass_ing;
    private JLabel caj_img;

    public Login_Cajero() {
        super("Login Cajero");
        setContentPane(JPanel_login);
        ImageIcon icon_adm = new ImageIcon(getClass().getResource("IMAGENES2/cajero.png"));
        caj_img.setIcon(icon_adm);

        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try(Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String usuario_read = user_ing.getText();
                    String pass_read = new String (pass_ing.getPassword());
                    String sql = "Select * From CAJEROS_USUARIOS where usuario_asig=? and contra_asig=?";
                    PreparedStatement stm = conectarse.prepareStatement(sql);
                    stm.setString(1,usuario_read);
                    stm.setString(2,pass_read);
                    ResultSet rs = stm.executeQuery();
                    if (rs.next()) {
                        String usuario1 = rs.getString("usuario_asig");
                        String pass1 = rs.getString("contra_asig");
                        if (usuario1.equals(usuario_read) && pass1.equals(pass_read))
                        {
                            Menu_Cajero inicio = new Menu_Cajero();
                            inicio.iniciar();
                            dispose();
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Los datos ingresados son incorrectos ");
                    }
                    stm.close();
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
