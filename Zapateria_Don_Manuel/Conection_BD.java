package Zapateria_Don_Manuel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection_BD {
    private static final String url = "jdbc:mysql://localhost:3306/ZAPATERIA_FACTURACION";
    private static final String user = "root";
    private static final String password = "";

    public static Connection getConnection()  {
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }

    }
}
