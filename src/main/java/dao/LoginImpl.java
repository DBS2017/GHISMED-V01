package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Trabajador;

public class LoginImpl extends Conexion {

    public Trabajador verificar(String user, String pwd) throws Exception {
        Trabajador modelo = new Trabajador();
        ResultSet rs;
        String sql = "SELECT * FROM VW_LOGIN WHERE USERTRA = ? AND PASSTRA = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, user);
            ps.setString(2, pwd);
            rs = ps.executeQuery();
            if (rs.next()) {
                modelo.setIDTRA(rs.getInt("IDTRA"));
                modelo.getIDPER().setNOMPER(rs.getString("NOMPER"));
                modelo.getIDPER().CODUBI.setCODUBI(rs.getString("CODUBI"));
                modelo.setPASSTRA(rs.getString("PASSTRA"));
                modelo.setCARGTRA(rs.getString("CARGTRA"));
                modelo.getIDPER().setAPEPATPER(rs.getString("APEPATPER"));
                modelo.getIDPER().setAPEMATPER(rs.getString("APEMATPER"));
            }
            return modelo;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("verificar");
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void editarCuenta(int id, String user, String pwd) throws Exception {
        String sql = "UPDATE TRABAJADOR SET PASSTRA = ? WHERE IDTRA = ?";
        try {
            System.out.println(pwd);
            System.out.println(user);
            PreparedStatement cs = this.conectar().prepareCall(sql);
            cs.setString(1, pwd);
            cs.setInt(2, id);
            cs.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public int hcPendientes(int id) throws Exception {
        ResultSet rs;
        int result = 0;
        String sql = "SELECT COUNT(*) TOTAL FROM VW_CONSULTA WHERE IDTRA = ?";
        try {
            System.out.println(id);
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("TOTAL");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return result;
    }
}
