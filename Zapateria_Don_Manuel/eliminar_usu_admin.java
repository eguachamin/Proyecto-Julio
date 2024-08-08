package Zapateria_Don_Manuel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class eliminar_usu_admin extends JFrame{
    private JPanel JPanel_eliminar;
    private JTextField CI_Buscar;
    private JButton BUSCARButton;
    private JButton ELIMINARButton;
    private JLabel name;
    private JLabel apell;
    private JLabel direcc;
    private JLabel fecha_nac;
    private JLabel telfe;
    private JLabel usuar_asig;
    private JLabel contra_asig;
    private JButton MENUButton;
    private JButton LIMPIARButton1;
    private JLabel elim_foto;

    public eliminar_usu_admin() {
        super("Eliminar Cajeros_Usuarios");
        setContentPane(JPanel_eliminar);
        ImageIcon icon_user = new ImageIcon(getClass().getResource("IMAGENES/borrar-usuario.png"));
        elim_foto.setIcon(icon_user);

        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String cedula_buscada = CI_Buscar.getText();
                    String sql = "SELECT * FROM CAJEROS_USUARIOS WHERE cedula_cli=?";
                    PreparedStatement strm = conectarse.prepareStatement(sql);
                    strm.setInt(1,Integer.parseInt(cedula_buscada));
                    ResultSet rs = strm.executeQuery();
                    if (rs.next()){
                        String cedula1= rs.getString("cedula_cli");
                        if (cedula1.equals(cedula_buscada)){
                            name.setText(rs.getString("nombre_cli"));
                            apell.setText(rs.getString("apellido_cli"));
                            direcc.setText(rs.getString("direccion_cli"));
                            fecha_nac.setText(rs.getString("fechaNac_cli"));
                            telfe.setText(rs.getString("telefono_cli"));
                            usuar_asig.setText(rs.getString("usuario_asig"));
                            contra_asig.setText(rs.getString("contra_asig"));
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"La cédula ingresada no existe");
                        CI_Buscar.setText("");
                    }
                    strm.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        LIMPIARButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CI_Buscar.setText("");
                name.setText("");
                apell.setText("");
                direcc.setText("");
                fecha_nac.setText("");
                telfe.setText("");
                usuar_asig.setText("");
                contra_asig.setText("");
            }
        });
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String codbusca1=CI_Buscar.getText();
                    String sql = "DELETE FROM CAJEROS_USUARIOS WHERE cedula_cli=?";
                    PreparedStatement stm = conectarse.prepareStatement(sql);
                    stm.setString(1,codbusca1);
                    int affectedRows = stm.executeUpdate();
                    if (affectedRows> 0){
                        JOptionPane.showMessageDialog(null,"El usuario/cajero se encuentra eliminado");
                        CI_Buscar.setText("");
                        name.setText("");
                        apell.setText("");
                        direcc.setText("");
                        fecha_nac.setText("");
                        telfe.setText("");
                        usuar_asig.setText("");
                        contra_asig.setText("");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"El codigo ingresado es incorrecto");
                        CI_Buscar.setText("");
                    }
                    stm.close();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        MENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu_Admin inicio = new Menu_Admin();
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
