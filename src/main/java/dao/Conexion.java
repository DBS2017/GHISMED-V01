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
            cnx = DriverManager.getConnection("jdbc:oracle:thin:@34.69.77.101:1521:XE", "db_IHISMAN", "db_IHISMAN-2019");
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
