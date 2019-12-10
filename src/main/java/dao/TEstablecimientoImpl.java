package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TEstablecimiento;

public class TEstablecimientoImpl extends Conexion {
//

    public void insertar(TEstablecimiento modelo) throws Exception {
        String sql = "INSERT INTO ESTABLECIMIENTO_ESPECIALIDAD (IDEST,IDESP) VALUES (?,?)";

        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.IDEST.getIDEST());
            ps.setInt(2, modelo.IDESP.getIDESP());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public void deshabilitar(TEstablecimiento modelo) throws Exception {
        String sql = "UPDATE ESTABLECIMIENTO_ESPECIALIDAD SET ESTDESTESP = 'I' where IDESP = ? AND IDEST = ?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.IDESP.getIDESP());
            ps.setInt(2, modelo.IDEST.getIDEST());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("AsignacionEspecialidadImpl");
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void habilitar(TEstablecimiento modelo) throws Exception {
        String sql = "UPDATE ESTABLECIMIENTO_ESPECIALIDAD SET ESTDESTESP = 'A' where IDESP = ? AND IDEST = ?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.IDESP.getIDESP());
            ps.setInt(2, modelo.IDEST.getIDEST());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("AsignacionEspecialidadImpl");
            throw e;
        } finally {
            this.cerrar();
        }
    }
    //
    //    public void registrar(Establecimiento modelo) throws Exception {
    //        String sql = "{call SP_ESTAB_ESPECIALIDAD(?,?,?)}";
    //        CallableStatement cs;
    //        try {
    //            cs = this.conectar().prepareCall(sql);
    //            cs.setInt("V_IDESP", modelo.getIDESP());
    //            cs.setInt("V_IDEST", modelo.getIDEST());
    //            cs.registerOutParameter("MI_ERROR", Types.VARCHAR);
    //            cs.execute();
    //            System.out.println(cs.getString("MI_ERROR"));
    //            cs.close();
    //        } catch (ClassNotFoundException | SQLException e) {
    //            System.out.println(e.getMessage());
    //            throw e;
    //        } finally {
    //            this.cerrar();
    //        }
    //    }
    //
    //    
    //

    public List<TEstablecimiento> listar(String TIPO_LISTA) throws Exception {
        String sql = "SELECT * FROM VW_ESTABLECIMIENTO_TRANSA WHERE ESTDESTESP LIKE ?";
        List<TEstablecimiento> listado = new ArrayList();
        TEstablecimiento modelo;
        ResultSet rs;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, TIPO_LISTA);
            rs = ps.executeQuery();
            while (rs.next()) {
                modelo = new TEstablecimiento();
                modelo.setIDESTESP(rs.getInt("IDESTESP"));
                modelo.IDEST.setIDEST(rs.getInt("IDEST"));
                modelo.IDESP.setIDESP(rs.getInt("IDESP"));
                modelo.IDESP.setNOMESP(rs.getString("NOMESP"));
                modelo.IDEST.setNOMEST(rs.getString("NOMEST"));
                modelo.IDESP.setTIPOESP(rs.getString("TIPOESP"));
                modelo.IDESP.setESTDESP(rs.getString("ESTDESTESP"));
                listado.add(modelo);
            }
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("AsignacionEspecialidadImpl");
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }
//
//    public List<Establecimiento> listar(String q) throws Exception {
//        List<Establecimiento> listado;
//        Establecimiento modelo;
//        PreparedStatement ps;
//        ResultSet rs;
//        String sql = "SELECT * FROM VW_EST_ESPECIALIDAD WHERE ESTDESTESP = 'A' AND IDEST LIKE ?;" + q;
//        try {
//            ps = this.conectar().prepareStatement(sql);
//            listado = new ArrayList();
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                modelo = new Establecimiento();
//                modelo.setIDESP(rs.getInt("IDESP"));
//                modelo.setIDEST(rs.getInt("IDEST"));
//                modelo.setNOMESP(rs.getString("NOMESP"));
//                modelo.setNOMEST(rs.getString("NOMEST"));
//                modelo.setESTDESP(rs.getString("ESTDASIGESP"));
//                modelo.setIDTRA(rs.getInt("IDPER"));
//                modelo.setENCARGADO(rs.getString("ENCARGADO"));
//                listado.add(modelo);
//            }
//            rs.close();
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println("AsignacionEspecialidadImpl");
//            throw e;
//        } finally {
//            this.cerrar();
//        }
//        return listado;
//    }
}
