package Zapateria_Don_Manuel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu_Admin extends JFrame{
    private JPanel JPanel_Menu;
    private JButton ingresarActualizarUsuariosButton;
    private JButton eliminarUsuariosButton;
    private JButton ventasPorVendedorButton;
    private JButton ventasGeneralButton;
    private JButton eliminarProductosButton;
    private JButton ingresarActualizarProductoButton;
    private JButton SALIRButton;
    private JLabel img_user_ing;
    private JLabel delte_img_user;
    private JLabel img_venta_user;
    private JLabel img_ing_prd;
    private JLabel delte_prd_img;
    private JLabel genral_img_vent;
    private JLabel fot_zapa;


    public Menu_Admin() {
        super("Menú Administrador");
        setContentPane(JPanel_Menu);
        ImageIcon icon_user = new ImageIcon(getClass().getResource("IMAGENES/user-interface1.png"));
        img_user_ing.setIcon(icon_user);
        ImageIcon icon_delete_user = new ImageIcon(getClass().getResource("IMAGENES/borrar-usuario.png"));
        delte_img_user.setIcon(icon_delete_user);
        ImageIcon icon_venta = new ImageIcon(getClass().getResource("IMAGENES/metodo-de-pago.png"));
        img_venta_user.setIcon(icon_venta);
        ImageIcon icon_product = new ImageIcon(getClass().getResource("IMAGENES/zapatillas.png"));
        img_ing_prd.setIcon(icon_product);
        ImageIcon icon_delete_prod = new ImageIcon(getClass().getResource("IMAGENES/borrar.png"));
        delte_prd_img.setIcon(icon_delete_prod);
        ImageIcon icon_genrl = new ImageIcon(getClass().getResource("IMAGENES/ventas.png"));
        genral_img_vent.setIcon(icon_genrl);
        ImageIcon icon_zapa = new ImageIcon(getClass().getResource("IMAGENES/zapatos.png"));
        fot_zapa.setIcon(icon_zapa);

        ingresarActualizarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ingr_Usu_Adm inicio = new Ingr_Usu_Adm();
                inicio.iniciar();
                dispose();
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
        eliminarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar_usu_admin inicio = new eliminar_usu_admin();
                inicio.iniciar();
                dispose();
            }
        });
        ingresarActualizarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ingresar_Product inicio = new Ingresar_Product();
                inicio.iniciar();
                dispose();
            }
        });

        eliminarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Eliminar_Products inicio = new Eliminar_Products();
                inicio.iniciar();
                dispose();
            }
        });
    }

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
