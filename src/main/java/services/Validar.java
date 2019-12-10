package services;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Persona;

public class Validar extends Conexion {
    
    public Persona DNI(String dni) throws Exception {
        Persona modelo = new Persona();
        String sql = "SELECT P.*, FN_VALIDAR(IDPER) EXISTO FROM VW_PERSONA P WHERE P.DNIPER = ?";
        ResultSet rs;
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                modelo.setIDPER(rs.getInt("IDPER"));
                modelo.setNOMPER(rs.getString("NOMPER"));
                modelo.setAPEPATPER(rs.getString("APEPATPER"));
                modelo.setAPEMATPER(rs.getString("APEMATPER"));
                modelo.setDNIPER(rs.getString("DNIPER"));
                modelo.setSEXPER(rs.getString("SEXPER"));
                modelo.setDIRPER(rs.getString("DIRACTPER"));
                modelo.CODUBI.setCODUBI(rs.getString("CODUBI"));
                modelo.CODUBI.setDISTUBI(rs.getString("DISTUBI"));
                modelo.CODUBI.setPROVUBI(rs.getString("PROVUBI"));
                modelo.CODUBI.setDEPAUBI(rs.getString("DEPAUBI"));
                modelo.setNUMCELPER(rs.getString("NUMCELPER"));
                modelo.setESTDPER(rs.getString("EXISTO"));
                modelo.setFECNACPER(rs.getDate("FECNACPER"));
            } else {
                modelo.setDNIPER(dni);
                modelo.setESTDPER("");
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        System.out.println(modelo.getIDPER());
        return modelo;
    }
}
