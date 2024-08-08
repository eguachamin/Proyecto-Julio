package Zapateria_Don_Manuel_CAJERO;

import Zapateria_Don_Manuel.Conection_BD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Descargar_Visualizar extends JFrame {
    private JPanel JPanel_DesVis;
    private JTextField nota_bucar;
    private JButton buscarButton;
    private JButton limpiarButton;
    private JButton menuButton;
    private JButton descargarPDFButton;
    private JLabel ci_cli;
    private JLabel fecha_buscada;
    private JLabel subtotal_ing;
    private JLabel iva_ing;
    private JLabel total_1;
    private JLabel name_cli;
    private nota_de_venta notaDeVenta;


    public Descargar_Visualizar() {
        super("Visualización / Descarga Nota de Venta");
        setContentPane(JPanel_DesVis);
        notaDeVenta = new nota_de_venta();
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conectarse = Conection_BD.getConnection()){
                    if (conectarse == null) {
                        throw new SQLException("No se pudo obtener la conexión.");
                    }
                    int num_nota1 = Integer.parseInt(nota_bucar.getText());
                    String sql = "SELECT CONCAT (F.nombre_cliente,' ',F.apellido_cliente) as NOMBRE_CLIENTE, D.NUMFAC, D.IDCLI, D.FECHA,D.TOTAL_FACT,D.IVA_FACT ,D.TOTALFACT FROM CLIENTES F, CABFACTURA D WHERE NUMFAC=?";
                    PreparedStatement strm = conectarse.prepareStatement(sql);
                    strm.setInt(1,num_nota1);
                    ResultSet rs = strm.executeQuery();
                    if (rs.next()){
                        int num_fact = rs.getInt("NUMFAC");
                        if (num_nota1==num_fact){
                            ci_cli.setText(rs.getString("IDCLI"));
                            name_cli.setText(rs.getString("NOMBRE_CLIENTE"));
                            fecha_buscada.setText(rs.getString("FECHA"));
                            subtotal_ing.setText(rs.getString("TOTAL_FACT"));
                            iva_ing.setText(rs.getString("IVA_FACT"));
                            total_1.setText(rs.getString("TOTALFACT"));

                        }
                        else {
                            JOptionPane.showMessageDialog(null,"El numero de Factura no se encuentra registrado, vuelva a intentar");
                            nota_bucar.setText("");
                        }
                    }
            } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ci_cli.setText("");
                name_cli.setText("");
                fecha_buscada.setText("");
                subtotal_ing.setText("");
                iva_ing.setText("");
                total_1.setText("");
                nota_bucar.setText("");
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu_Cajero inicio = new Menu_Cajero();
                inicio.iniciar();
                dispose();
            }
        });
        descargarPDFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int numero_venta = Integer.parseInt(nota_bucar.getText());
                int cedula_buscada = Integer.parseInt(ci_cli.getText());
                java.sql.Date fecha = java.sql.Date.valueOf(fecha_buscada.getText());
                double subtotal1 = Double.parseDouble(subtotal_ing.getText().replace(",", "."));
                double iva1 = Double.parseDouble(iva_ing.getText().replace(",", "."));
                double total1 = Double.parseDouble(total_1.getText().replace(",", "."));

                // Llama al método generarPDF en la instancia de nota_de_venta
                notaDeVenta.generarPDF(numero_venta, cedula_buscada, fecha, subtotal1, iva1, total1);
            }

        });
    }

    public void iniciar(){
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


