package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Especialidad;
import model.Ubigeo;

public class AutoCompleteImpl extends Conexion {

    //AUTOCOMPLETE ESTABLECIMIENTO
    public int idEstab(String value) throws Exception {
        ResultSet rs;
        int id = 0;
        try {
            String sql = "SELECT CODE FROM VW_BUSCAR_ESTABLECIMIENTO WHERE UPPER(RESULTADO) = UPPER(?)";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("CODE");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.getMessage();
            throw e;
        } finally {
            this.cerrar();
        }
        return id;
    }

    public List<String> buscarEstab(String value) throws Exception {
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT RESULTADO FROM VW_BUSCAR_ESTABLECIMIENTO WHERE UPPER(RESULTADO) LIKE UPPER(?) AND ROWNUM < 16";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, value + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("RESULTADO"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return Lista;
    }

    //AUTOCOMPLETE ESPECIALIDAD
    public String idEspec(String value) throws Exception {
        ResultSet rs;
        String id = "";
        try {
            String sql = "SELECT CODE FROM VW_BUSCAR_ESPECIALIDAD WHERE UPPER(RESULTADO) = UPPER(?)";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getString("CODE");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.getMessage();
            throw e;
        } finally {
            this.cerrar();
        }
        return id;
    }

    public List<String> buscarEspec(String CODE, String value) throws Exception {
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT RESULTADO FROM VW_BUSCAR_ESPECIALIDAD WHERE ESTABLECIMIENTO = ? AND UPPER(RESULTADO) LIKE UPPER(?) AND ROWNUM < 16";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, CODE);
            ps.setString(2, value + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("RESULTADO"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return Lista;
    }

    //AUTOCOMPLETE UBIGEO
    public String codUbige(String value) throws Exception {
        ResultSet rs;
        String code = "";
        try {
            String sql = "SELECT CODE FROM VW_BUSCAR_UBIGEO WHERE UPPER(DISTRITO) = UPPER(?)";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, value);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = rs.getString("CODE");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return code;
    }

    public List<Ubigeo> buscarUbige0(String value) throws Exception {
        ResultSet rs;
        Ubigeo modelo;
        List<Ubigeo> lista;
        try {
            String sql = "SELECT * FROM UBIGEO WHERE UPPER(DISTUBI) LIKE UPPER(?) AND ROWNUM < 11";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, value + "%");
            lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                modelo = new Ubigeo();
                modelo.setCODUBI(rs.getString("CODUBI"));
                modelo.setDISTUBI(rs.getString("DISTUBI"));
                modelo.setPROVUBI(rs.getString("PROVUBI"));
                modelo.setDEPAUBI(rs.getString("DEPAUBI"));
                lista.add(modelo);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public List<String> buscarUbige(String value) throws Exception {
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT DISTRITO FROM VW_BUSCAR_UBIGEO WHERE UPPER(DISTRITO) LIKE UPPER(?) AND ROWNUM < 16";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, value + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("DISTRITO"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return Lista;
    }
//oficial funcional
    public List<Ubigeo> buscarUbigeo(String value) throws Exception {
        final String sql = "SELECT (CODUBI||'|'||DEPAUBI||'|'||PROVUBI||'|'||DISTUBI) U FROM UBIGEO WHERE UPPER(DISTUBI) LIKE UPPER(?) AND ROWNUM < 6";
        List<Ubigeo> lista = new ArrayList();
        ResultSet rs;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, value + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Ubigeo modelo = new Ubigeo(rs.getString("U"));
                lista.add(modelo);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public List<Especialidad> listarEspecialidad(int consulta) throws Exception {
        List<Especialidad> lista;
        Especialidad modelo;
        String sql = "SELECT IDESP,NOMESP FROM ESPECIALIDAD "
                + "WHERE IDESP NOT IN(SELECT IDESP FROM ESTABLECIMIENTO_ESPECIALIDAD WHERE IDEST = ?)";
        ResultSet rs;
        try {
            PreparedStatement cs = this.conectar().prepareCall(sql);
            cs.setInt(1, consulta);
            rs = cs.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                modelo = new Especialidad();
                modelo.setIDESP(rs.getInt("IDESP"));
                modelo.setNOMESP(rs.getString("NOMESP"));
                lista.add(modelo);
            }
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("soy el errooooorr ahora asas: " + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }
}
