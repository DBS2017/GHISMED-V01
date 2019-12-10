package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Especialidad;
import model.TEstablecimiento;

public class EspecialidadImpl extends Conexion {

    public void registrar(Especialidad especialidad) throws Exception {
        String sql = "insert into ESPECIALIDAD (NOMESP) values (?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, especialidad.getNOMESP());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            this.cerrar();
        }
    }

    public void modificar(Especialidad especialidad) throws Exception {
        String sql = "update ESPECIALIDAD set NOMESP = ?, TIPOESP = ? where IDESP = ?";
        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setString(1, especialidad.getNOMESP());
            ps.setString(2, especialidad.getTIPOESP());
            ps.setInt(3, especialidad.getIDESP());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            this.cerrar();
        }
    }

    public void eliminar(Especialidad especialidad) throws Exception {
        String sql = "update ESPECIALIDAD set ESTDESP = 1 where IDESP=?";
        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, especialidad.getIDESP());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al eliminar Especialidad" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public void asignarEstablecimiento(TEstablecimiento ee) throws Exception {
        String sql = "INSERT INTO ESTABLECIMIENTO_ESPECIALIDAD (IDEST,IDESP) VALUES (?,?)";
        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, ee.IDEST.getIDEST());
            ps.setInt(2, ee.IDESP.getIDESP());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public List<Especialidad> listarNoAsignados(int idest) throws Exception {
        String sql = "SELECT * FROM especialidad e WHERE e.IDESP NOT IN (SELECT CE.IDESP FROM ESTABLECIMIENTO_ESPECIALIDAD CE WHERE CE.IDEST = ?)";
        List<Especialidad> listado;
        Especialidad modelo;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, idest);
            listado = new ArrayList();
            rs = ps.executeQuery();
            while (rs.next()) {
                modelo = new Especialidad();
                modelo.setIDESP(rs.getInt("IDESP"));
                modelo.setNOMESP(rs.getString("NOMESP"));
                modelo.setESTDESP(rs.getString("ESTDESP"));
                listado.add(modelo);
            }
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public List<Especialidad> listarEsp(int IDEST, int ESTDESP) throws Exception {
        List<Especialidad> lista;
        Especialidad modelo;
        String sql = "SELECT * FROM VW_ESPECIALIDAD_FILTRO WHERE IDESP NOT IN (SELECT IDESP FROM VW_ASIG_ESPECIALIDAD WHERE IDEST = ? AND IDESP IS NOT NULL) AND ESTDESP LIKE ? ORDER BY NOMESP";
        ResultSet rs;
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setInt(1, IDEST);
            ps.setInt(2, ESTDESP);
            rs = ps.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                modelo = new Especialidad();
                modelo.setIDESP(rs.getInt("IDESP"));
                modelo.setNOMESP(rs.getString("NOMESP"));
                lista.add(modelo);
            }
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("mi error");
            System.out.println(e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public void habilitar(Especialidad modelo) throws Exception {
        String sql = "update ESPECIALIDAD set ESTDESP = 'A' where IDESP = ?";
        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDESP());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al eliminar Especialidad" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public void deshabilitar(Especialidad modelo) throws Exception {
        String sql = "update ESPECIALIDAD set ESTDESP = 'I' where IDESP = ?";
        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDESP());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al eliminar Especialidad" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public List<Especialidad> listar(String TIPO_LISTA) throws Exception {
        String sql = "SELECT * FROM ESPECIALIDAD WHERE ESTDESP = ? ORDER BY NOMESP DESC";
        List<Especialidad> lista = new ArrayList();
        Especialidad modelo;
        ResultSet rs;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, TIPO_LISTA);
            rs = ps.executeQuery();
            while (rs.next()) {
                modelo = new Especialidad();
                modelo.setIDESP(rs.getInt("IDESP"));
                modelo.setNOMESP(rs.getString("NOMESP"));
                modelo.setTIPOESP(rs.getString("TIPOESP"));
                modelo.setESTDESP(rs.getString("ESTDESP"));
                lista.add(modelo);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }
}
