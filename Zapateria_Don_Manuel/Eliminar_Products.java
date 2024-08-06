package Zapateria_Don_Manuel;

import javax.swing.*;

public class Eliminar_Products extends JFrame {
    private JPanel JPanel_ElimProd;
    private JTextField cod_buscar;
    private JButton buscarButton;
    private JButton limpiarButton;
    private JButton eliminarButton;
    private JLabel nom_product;
    private JLabel descrip_product;
    private JLabel precio_product;
    private JLabel stock_product;

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
