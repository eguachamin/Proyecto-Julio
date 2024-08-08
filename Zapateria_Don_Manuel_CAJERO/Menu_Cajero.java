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
    private JButton PRODUCTOSButton;
    private JLabel foto_inv;
    private JButton SALIRButton;

    public Menu_Cajero() {
        super("Menu Cajero");
        setContentPane(JPanel_MenuCajero);
        ImageIcon icon_clientes = new ImageIcon(getClass().getResource("IMAGENES2/usuario.png"));
        img_clientes.setIcon(icon_clientes);
        ImageIcon icon_notaVenta = new ImageIcon(getClass().getResource("IMAGENES2/factura.png"));
        img_notadeventa.setIcon(icon_notaVenta);
        ImageIcon icon_verNota = new ImageIcon(getClass().getResource("IMAGENES2/adjunto-archivo.png"));
        ver_nota_imagen.setIcon(icon_verNota);
        ImageIcon icon_PROD = new ImageIcon(getClass().getResource("IMAGENES2/produccion.png"));
        foto_inv.setIcon(icon_PROD);

        CLIENTESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliente_acciones inicio = new cliente_acciones();
                inicio.iniciar();
                dispose();
            }
        });
        NOTADEVENTAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nota_de_venta inicio = new nota_de_venta();
                inicio.iniciar();
                dispose();
            }
        });

        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login_Cajero inicio = new Login_Cajero();
                inicio.iniciar();
                dispose();
            }
        });
        VISUALIZARNOTAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Descargar_Visualizar inicio = new Descargar_Visualizar();
                inicio.iniciar();
                dispose();
            }
        });
        PRODUCTOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inventario_Productos inicio = new Inventario_Productos();
                inicio.iniciar();
                dispose();
            }
        });
    }

    public void iniciar(){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700,500);
    }
}
