package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection cnx;

    public Connection conectar() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //cnx = DriverManager.getConnection("jdbc:oracle:thin:@35.184.25.222:1521:XE", "dbGHISMED", "GHISMED-2019");
            cnx = DriverManager.getConnection("jdbc:oracle:thin:@40.78.124.229:1521:XE", "Team07", "Team07");
            System.out.println("Conectado");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return cnx;
    }

    public void cerrar() throws Exception {
        try {
            if (cnx != null) {
                cnx.close();
                System.out.println("Cerrado");
            }
        } catch (SQLException e) {
            throw e;
        }
    }
}
