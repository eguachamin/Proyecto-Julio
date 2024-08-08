package Zapateria_Don_Manuel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection_BD {
    private static final String url = "jdbc:mysql://bx27fol6tu9somzzfsun-mysql.services.clever-cloud.com:3306/bx27fol6tu9somzzfsun";
    private static final String user = "us6fu1ztezjezwe1";
    private static final String password = "urpLfu2mbIQP2tDmeS9A";

    public static Connection getConnection()  {
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return null;
        }

    }
}
