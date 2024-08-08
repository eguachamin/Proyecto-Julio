package Zapateria_Don_Manuel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Visualizar_NotaVenta extends JFrame{
    private JPanel JPanel_Visualizar;
    private JButton mostrarVentasButton;
    private JTable table1;
    private JButton MENUButton;


    public Visualizar_NotaVenta() {
        super("VISUALIZACION DE LAS VENTAS");
        setContentPane(JPanel_Visualizar);
        String[] column_names = {"NUM° FACT","ID_CLIENTE","NOMBRE CLIENTE","FECHA","SUBTOTAL","IVA","TOTAL"};
        DefaultTableModel model = new DefaultTableModel(column_names,0);
        table1.setModel(model);
        table1.getColumnModel().getColumn(6).setCellRenderer(new ImagenRender());
        mostrarVentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    String sql = "SELECT CONCAT (F.nombre_cliente,' ',F.apellido_cliente) as NOMBRE_CLIENTE, D.NUMFAC, D.IDCLI, D.FECHA,D.TOTAL_FACT,D.IVA_FACT ,D.TOTALFACT FROM CLIENTES F, CABFACTURA D";
                    PreparedStatement strm = conectarse.prepareStatement(sql);
                    ResultSet rs = strm.executeQuery();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    model.setRowCount(0);
                        while (rs.next()){
                        int id_FACT = rs.getInt("NUMFAC");
                        String nombre = rs.getString("NOMBRE_CLIENTE");
                        int id_CLI = rs.getInt("IDCLI");
                        Date fecha = rs.getDate("FECHA");
                        double subtotal2 = rs.getDouble("TOTAL_FACT");
                        double iva2 = rs.getDouble("IVA_FACT");
                        double total2 = rs.getDouble("TOTALFACT");
                        model.addRow(new Object[]{id_FACT,id_CLI, nombre,fecha,subtotal2,iva2,total2});
                    }
                    strm.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        MENUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu_Admin inicio = new Menu_Admin();
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
