package Zapateria_Don_Manuel;

import javax.swing.*;

public class Ingresar_Product extends JFrame {
    private JPanel JPanel_IntoProduct;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton buscarButton;
    private JButton limpiarButton;
    private JButton actualizarButton;
    private JButton ingresarButton;
    private JTextField textField5;

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
