package Zapateria_Don_Manuel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_Admin extends JFrame{
    private JTextField usuario_ing;
    private JPasswordField pass_ing;
    private JPanel JPanel_LogAdm;
    private JButton INGRESARButton;
    private JLabel imagen_admin;
    private JButton SALIRButton;

    public Login_Admin() {
        super("Inicio de Sesión");
        setContentPane(JPanel_LogAdm);
        ImageIcon icon_adm = new ImageIcon(getClass().getResource("IMAGENES/administracion.png"));
        imagen_admin.setIcon(icon_adm);
        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String usuario_read = usuario_ing.getText();
                    String pass_read = new String (pass_ing.getPassword());
                    String sql = "Select * From USUARIO_ADMIN where username=? and password=?";
                    PreparedStatement stm = conectarse.prepareStatement(sql);
                    stm.setString(1,usuario_read);
                    stm.setString(2,pass_read);
                    ResultSet rs = stm.executeQuery();

                    if (rs.next()) {
                        String usuario1 = rs.getString("username");
                        String pass1 = rs.getString("password");
                        if (usuario1.equals(usuario_read) && pass1.equals(pass_read))
                        {
                            Menu_Admin inicio = new Menu_Admin();
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

        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pant_Principal inicio = new Pant_Principal();
                inicio.iniciar();
                dispose();
            }
        });
    }
    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
