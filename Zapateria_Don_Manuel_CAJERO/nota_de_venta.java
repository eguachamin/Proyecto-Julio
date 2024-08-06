package Zapateria_Don_Manuel_CAJERO;

import javax.swing.*;

public class nota_de_venta extends  JFrame{
    private JTextField textField1;
    private JButton buscarButton;
    private JTable table1;
    private JButton añadirProductoButton;

    public void iniciar(){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500,400);
    }
}
