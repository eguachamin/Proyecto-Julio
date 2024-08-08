package Zapateria_Don_Manuel;

import Zapateria_Don_Manuel_CAJERO.Login_Cajero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
public class Pant_Principal  extends JFrame{
    private JButton ADMINISTRADORButton;
    private JButton CAJEROButton;
    private JPanel JPanel_Opciones;
    private JLabel foto_adm;
    private JLabel foto_caj;
    private JLabel Foto_zapa;


    public Pant_Principal() {

        super("Pantalla Principal");
        setContentPane(JPanel_Opciones);
        ImageIcon icon_adm = new ImageIcon(getClass().getResource("IMAGENES/administracion.png"));
        foto_adm.setIcon(icon_adm);
        ImageIcon icon_caj = new ImageIcon(getClass().getResource("IMAGENES/cajero.png"));
        foto_caj.setIcon(icon_caj);
        ImageIcon icon_zapa = new ImageIcon(getClass().getResource("IMAGENES/zapatos.png"));
        Foto_zapa.setIcon(icon_zapa);
        ADMINISTRADORButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login_Admin inicio = new Login_Admin();
                inicio.iniciar();
                dispose();
            }
        });
        CAJEROButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login_Cajero inicio = new Login_Cajero();
                inicio.iniciar();
                dispose();
            }
        });
    }

    public void iniciar() {
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}


