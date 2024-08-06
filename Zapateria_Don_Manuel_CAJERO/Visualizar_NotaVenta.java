package Zapateria_Don_Manuel_CAJERO;

import javax.swing.*;

public class Visualizar_NotaVenta extends JFrame{
    private JPanel JPanel_Visualizar;
    private JTextField num_search;
    private JButton BUSCARButton;
    private JButton LIMPIARButton;
    private JTable table1;
    private JButton DESCARGARButton;
    private JButton MENUButton;

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
