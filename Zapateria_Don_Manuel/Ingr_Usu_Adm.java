package Zapateria_Don_Manuel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Ingr_Usu_Adm extends JFrame{
    private JTextField Nom_Usuario;
    private JTextField Apell__Usuario;
    private JTextField Direcc_Usuario;
    private JTextField Nac_Usuario;
    private JTextField Telf_Usuario;
    private JTextField UsuAs_Usuario;
    private JTextField ContAsi_Usuario;
    private JButton INGRESARButton;
    private JButton MENÚButton;
    private JTextField CI_Usuario;
    private JButton BUSCARButton;
    private JButton LIMPIARButton;
    private JButton ACTUALIZARButton;
    private JPanel JPanel_UsuAdmin;
    private JLabel foto_zapa;


    public Ingr_Usu_Adm() {
        super("Ingreso / Actualizacion de Usuarios");
        setContentPane(JPanel_UsuAdmin);
        ImageIcon icon_user = new ImageIcon(getClass().getResource("IMAGENES/user-interface1.png"));
        foto_zapa.setIcon(icon_user);

        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String cedula = CI_Usuario.getText();
                    String name = Nom_Usuario.getText();
                    String apellido = Apell__Usuario.getText();
                    String direccion = Direcc_Usuario.getText();
                    String fecha = Nac_Usuario.getText();
                    String telefono = Telf_Usuario.getText();
                    String usuario_asig = UsuAs_Usuario.getText();
                    String pass_asig = ContAsi_Usuario.getText();
                    String sql = "Insert into CAJEROS_USUARIOS (cedula_cli,nombre_cli,apellido_cli,direccion_cli,fechaNac_cli,telefono_cli,usuario_asig,contra_asig)values (?,?,?,?,?,?,?,?)";
                    PreparedStatement strm = conectarse.prepareStatement(sql);
                    strm.setInt(1,Integer.parseInt(cedula));
                    strm.setString(2,name);
                    strm.setString(3,apellido);
                    strm.setString(4,direccion);
                    strm.setDate(5, Date.valueOf(fecha));
                    strm.setInt(6,Integer.parseInt(telefono));
                    strm.setString(7,usuario_asig);
                    strm.setString(8,pass_asig);
                    int rowAffected = strm.executeUpdate();
                        if (rowAffected>0)
                        {
                            JOptionPane.showMessageDialog(null,"Registro ingresado correctamente");
                            CI_Usuario.setText("");
                            Nom_Usuario.setText("");
                            Apell__Usuario.setText("");
                            Direcc_Usuario.setText("");
                            Nac_Usuario.setText("");
                            Telf_Usuario.setText("");
                            UsuAs_Usuario.setText("");
                            ContAsi_Usuario.setText("");
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

        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String cedula = CI_Usuario.getText();
                    String name = Nom_Usuario.getText();
                    String apellido = Apell__Usuario.getText();
                    String direccion = Direcc_Usuario.getText();
                    String fecha = Nac_Usuario.getText();
                    String telefono = Telf_Usuario.getText();
                    String usuario_asig = UsuAs_Usuario.getText();
                    String pass_asig = ContAsi_Usuario.getText();
                    String sql = "UPDATE  CAJEROS_USUARIOS  SET nombre_cli=?,apellido_cli=?,direccion_cli=?,fechaNac_cli=?,telefono_cli=?,usuario_asig=?,contra_asig=? where cedula_cli=?";
                    PreparedStatement strm = conectarse.prepareStatement(sql);
                    strm.setString(1,name);
                    strm.setString(2,apellido);
                    strm.setString(3,direccion);
                    strm.setDate(4, Date.valueOf(fecha));
                    strm.setInt(5,Integer.parseInt(telefono));
                    strm.setString(6,usuario_asig);
                    strm.setString(7,pass_asig);
                    strm.setInt(8,Integer.parseInt(cedula));

                    int affectedRows = strm.executeUpdate();
                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(null, "La actualización se realizó correctamente");
                        CI_Usuario.setText("");
                        Nom_Usuario.setText("");
                        Apell__Usuario.setText("");
                        Direcc_Usuario.setText("");
                        Nac_Usuario.setText("");
                        Telf_Usuario.setText("");
                        UsuAs_Usuario.setText("");
                        ContAsi_Usuario.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "La cédula ingresada es incorrecta");
                    }
                    strm.close();

            } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        LIMPIARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CI_Usuario.setText("");
                Nom_Usuario.setText("");
                Apell__Usuario.setText("");
                Direcc_Usuario.setText("");
                Nac_Usuario.setText("");
                Telf_Usuario.setText("");
                UsuAs_Usuario.setText("");
                ContAsi_Usuario.setText("");
            }
        });
        MENÚButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu_Admin inicio = new Menu_Admin();
                inicio.iniciar();
                dispose();
            }
        });
        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String cedula_buscada = CI_Usuario.getText();
                    String sql = "SELECT * FROM CAJEROS_USUARIOS WHERE cedula_cli=?";
                    PreparedStatement strm = conectarse.prepareStatement(sql);
                    strm.setInt(1,Integer.parseInt(cedula_buscada));
                    ResultSet rs = strm.executeQuery();
                    if (rs.next()){
                        String cedula1= rs.getString("cedula_cli");
                        if (cedula1.equals(cedula_buscada)){
                            Nom_Usuario.setText(rs.getString("nombre_cli"));
                            Apell__Usuario.setText(rs.getString("apellido_cli"));
                            Direcc_Usuario.setText(rs.getString("direccion_cli"));
                            Nac_Usuario.setText(rs.getString("fechaNac_cli"));
                            Telf_Usuario.setText(rs.getString("telefono_cli"));
                            UsuAs_Usuario.setText(rs.getString("usuario_asig"));
                            ContAsi_Usuario.setText(rs.getString("contra_asig"));
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
    }
    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(700,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}