package dao;

import model.Establecimiento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstablecimientoImpl extends Conexion {

    public void registrar(Establecimiento establecimiento) throws Exception {
        String sql = "insert into ESTABLECIMIENTO (NOMEST,DIREST,CODUBI,TELFEST,MAILEST) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, establecimiento.getNOMEST());
            ps.setString(2, establecimiento.getDIREST());
            ps.setString(3, establecimiento.getCODUBI().getCODUBI());
            ps.setString(4, establecimiento.getTELFEST());
            ps.setString(5, establecimiento.getMAILEST());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void modificar(Establecimiento establecimiento) throws Exception {
        String sql = "update ESTABLECIMIENTO set NOMEST=?,DIREST=?,CODUBI=?,TELFEST=?,MAILEST=? where IDEST=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, establecimiento.getNOMEST());
            ps.setString(2, establecimiento.getDIREST());
            ps.setString(3, establecimiento.getCODUBI().getCODUBI());
            ps.setString(4, establecimiento.getTELFEST());
            ps.setString(5, establecimiento.getMAILEST());
            ps.setInt(6, establecimiento.getIDEST());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al modificar sede" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public void eliminar(Establecimiento establecimiento) throws Exception {
        String sql = "update ESTABLECIMIENTO set ESTDEST = 1 where IDEST=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, establecimiento.getIDEST());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al eliminar SEDE" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public void habilitar(Establecimiento establecimiento) throws Exception {
        String sql = "update ESTABLECIMIENTO set ESTDEST = 'A' where IDEST=?";
        PreparedStatement ps = this.conectar().prepareStatement(sql);
        try {
            ps.setInt(1, establecimiento.getIDEST());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void deshabilitar(Establecimiento establecimiento) throws Exception {
        String sql = "update ESTABLECIMIENTO set ESTDEST = 'I' where IDEST=?";
        PreparedStatement ps = this.conectar().prepareStatement(sql);
        try {
            ps.setInt(1, establecimiento.getIDEST());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<Establecimiento> listar(String estado) throws Exception {
        String sql = "SELECT * FROM VW_ESTABLECIMIENTO WHERE ESTDEST LIKE ?";
        final List<Establecimiento> listado = new ArrayList();
        Establecimiento modelo;
        ResultSet rs;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, estado);
            rs = ps.executeQuery();
            while (rs.next()) {
                modelo = new Establecimiento();
                modelo.setIDEST(rs.getInt("IDEST"));//rs.getString("CODSEDE")
                modelo.setNOMEST(rs.getString("NOMEST"));//rs.getString("NOMSEDE")
                modelo.setDIREST(rs.getString("DIREST"));//rs.getString("DIRSEDE")
                modelo.setTELFEST(rs.getString("TELFEST")); // se aumento telfomo
                modelo.setMAILEST(rs.getString("MAILEST")); // se aumento email
                modelo.getCODUBI().setCODUBI(rs.getString("CODUBI"));
                modelo.getCODUBI().setDEPAUBI(rs.getString("DEPAUBI"));
                modelo.getCODUBI().setPROVUBI(rs.getString("PROVUBI"));
                modelo.getCODUBI().setDISTUBI(rs.getString("DISTUBI"));
                listado.add(modelo);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }
    /*
    public List<Establecimiento> listarActivos() throws Exception {
        List<Establecimiento> listado;
        Establecimiento modelo;
        String sql = "SELECT * FROM VW_ESTABLECIMIENTO WHERE ESTDEST = 'A'";
        try {
            listado = new ArrayList();
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                modelo = new Establecimiento();
                modelo.setIDEST(rs.getInt("IDEST"));//rs.getString("CODSEDE")
                modelo.setNOMEST(rs.getString("NOMEST"));//rs.getString("NOMSEDE")
                modelo.setDIREST(rs.getString("DIREST"));//rs.getString("DIRSEDE")
                modelo.setUBIGEO(rs.getString("UBIGEO"));//rs.getString("CODUBI")
                modelo.getCODUBI().setCODUBI(rs.getString("CODUBI"));
                modelo.setTELFEST(rs.getString("TELFEST")); // se aumento telfomo
                modelo.setMAILEST(rs.getString("MAILEST")); // se aumento email

                listado.add(modelo);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public List<Establecimiento> listarInactivos() throws Exception {
        List<Establecimiento> listado;
        Establecimiento modelo;
        String sql = "SELECT * FROM VW_ESTABLECIMIENTO WHERE ESTDEST != 'A'";
        try {
            listado = new ArrayList();
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                modelo = new Establecimiento();
                modelo.setIDEST(rs.getInt("IDEST"));//rs.getString("CODSEDE")
                modelo.setNOMEST(rs.getString("NOMEST"));//rs.getString("NOMSEDE")
                modelo.setDIREST(rs.getString("DIREST"));//rs.getString("DIRSEDE")
                modelo.setUBIGEO(rs.getString("UBIGEO"));//rs.getString("CODUBI")
                modelo.CODUBI.setCODUBI(rs.getString("CODUBI"));
                modelo.setTELFEST(rs.getString("TELFEST")); // se aumento telfomo
                modelo.setMAILEST(rs.getString("MAILEST")); // se aumento email

                listado.add(modelo);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public List<Establecimiento> listarNoAsignados() throws Exception {
        List<Establecimiento> listado;
        Establecimiento modelo;
        String sql = "SELECT * FROM VW_ESTABLECIMIENTO WHERE estdestesp != 'A' or estdestesp is null";
        try {
            listado = new ArrayList();
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                modelo = new Establecimiento();
                modelo.setIDEST(rs.getInt("IDEST"));//rs.getString("CODSEDE")
                modelo.setNOMEST(rs.getString("NOMEST"));//rs.getString("NOMSEDE")
                modelo.setDIREST(rs.getString("DIREST"));//rs.getString("DIRSEDE")
                modelo.setUBIGEO(rs.getString("UBIGEO"));//rs.getString("CODUBI")
                modelo.getCODUBI().setCODUBI(rs.getString("CODUBI"));
                modelo.setTELFEST(rs.getString("TELFEST")); // se aumento telfomo
                modelo.setMAILEST(rs.getString("MAILEST")); // se aumento email

                listado.add(modelo);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public List<Establecimiento> listarAsignados() throws Exception {
        List<Establecimiento> listado;
        Establecimiento modelo;
        String sql = "SELECT * FROM VW_ESTABLECIMIENTO WHERE estdestesp = 'A'";
        try {
            listado = new ArrayList();
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                modelo = new Establecimiento();
                modelo.setIDEST(rs.getInt("IDEST"));//rs.getString("CODSEDE")
                modelo.setNOMEST(rs.getString("NOMEST"));//rs.getString("NOMSEDE")
                modelo.setDIREST(rs.getString("DIREST"));//rs.getString("DIRSEDE")
                modelo.setUBIGEO(rs.getString("UBIGEO"));//rs.getString("CODUBI")
                modelo.getCODUBI().setCODUBI(rs.getString("CODUBI"));
                modelo.setTELFEST(rs.getString("TELFEST")); // se aumento telfomo
                modelo.setMAILEST(rs.getString("MAILEST")); // se aumento email

                listado.add(modelo);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public List<Establecimiento> listar(int q) throws Exception {
        List<Establecimiento> listado;
        Establecimiento establecimiento;
        String sql = "SELECT * FROM VW_ESTABLECIMIENTO WHERE ESTDEST = 0 AND IDEST = " + q;
        try {
            listado = new ArrayList();
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            while (rs.next()) {
                establecimiento = new Establecimiento();
                establecimiento.setIDEST(rs.getInt("IDEST"));//rs.getString("CODSEDE")
                establecimiento.setNOMEST(rs.getString("NOMEST"));//rs.getString("NOMSEDE")
                establecimiento.setDIREST(rs.getString("DIREST"));//rs.getString("DIRSEDE")
                establecimiento.getCODUBI().setCODUBI(rs.getString("ZONALOCA"));//rs.getString("CODUBI")
                establecimiento.setTELFEST(rs.getString("TELFEST")); // se aumento telfomo
                establecimiento.setMAILEST(rs.getString("MAILEST")); // se aumento email
                establecimiento.setTRABTOTEST(rs.getString("TRABTOTESTA")); // se aumento email
                listado.add(establecimiento);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }
     */
}
