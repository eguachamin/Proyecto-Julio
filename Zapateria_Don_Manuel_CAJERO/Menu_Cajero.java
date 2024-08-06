package Zapateria_Don_Manuel_CAJERO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu_Cajero extends JFrame{
    private JButton CLIENTESButton;
    private JButton NOTADEVENTAButton;
    private JButton VISUALIZARNOTAButton;
    private JLabel img_clientes;
    private JLabel img_notadeventa;
    private JLabel ver_nota_imagen;
    private JPanel JPanel_MenuCajero;

    public Menu_Cajero() {
        super("Menu Cajero");
        setContentPane(JPanel_MenuCajero);
        ImageIcon icon_clientes = new ImageIcon(getClass().getResource("IMAGENES2/usuario.png"));
        img_clientes.setIcon(icon_clientes);
        ImageIcon icon_notaVenta = new ImageIcon(getClass().getResource("IMAGENES2/factura.png"));
        img_notadeventa.setIcon(icon_notaVenta);
        ImageIcon icon_verNota = new ImageIcon(getClass().getResource("IMAGENES2/adjunto-archivo.png"));
        ver_nota_imagen.setIcon(icon_verNota);

        CLIENTESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliente_acciones inicio = new cliente_acciones();
                inicio.iniciar();
            }
        });
        NOTADEVENTAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nota_de_venta inicio = new nota_de_venta();
                inicio.iniciar();
            }
        });
        VISUALIZARNOTAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Visualizar_NotaVenta inicio = new Visualizar_NotaVenta();
                inicio.iniciar();
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
