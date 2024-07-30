package Zapateria_Don_Manuel_CAJERO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_Cajero extends JFrame{
    private JPanel JPanel_login;
    private JTextField user_ing;
    private JButton INGRESARButton;
    private JPasswordField pass_ing;

    public Login_Cajero() {
        super("Login Cajero");
        setContentPane(JPanel_login);
        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String usuario_get= user_ing.getText();
                String pass = new String(pass_ing.getPassword());
                if (usuario_get.equals("Eve")  && pass.equals("2563-98")){
                    JOptionPane.showMessageDialog(null,"ok");
                }
                else {
                    JOptionPane.showMessageDialog(null,"ingresaste mal tonto");
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
