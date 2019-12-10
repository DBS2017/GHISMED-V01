package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.EspecialidadTrabajador;
import model.Trabajador;
import static services.serv.Util2Sql;

public class TrabajadorImpl extends Conexion {

    public void insertar(Trabajador modelo) throws Exception {
        String sql = "{call SP_ADDTRABAJADOR(?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement cs;
        try {
            cs = this.conectar().prepareCall(sql);
            cs.setString("V_SEXPER", modelo.IDPER.getSEXPER());
            cs.setString("V_NOMPER", modelo.IDPER.getNOMPER());
            cs.setString("V_APEPATPER", modelo.IDPER.getAPEPATPER());
            cs.setString("V_APEMATPER", modelo.IDPER.getAPEMATPER());
            cs.setString("V_DNIPER", modelo.IDPER.getDNIPER());
            cs.setString("V_DIRACTPER", modelo.IDPER.getDIRPER());
            cs.setString("V_CODUBI", modelo.IDPER.CODUBI.getCODUBI());
            cs.setString("V_NUMCELPER", modelo.IDPER.getNUMCELPER());
            cs.setDate("V_FECNACPER", Util2Sql(modelo.IDPER.getFECNACPER()));
            cs.setString("V_CARGTRA", modelo.getCARGTRA());
            cs.setString("V_PERFPROFTRA", modelo.getPERFPROFTRA());
            cs.execute();
            cs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void eliminar(Trabajador trabajador) throws Exception {
        String sql = "update TRABAJADOR set ESTDTRA = 'I' where IDTRA=?";
        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, trabajador.getIDTRA());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al eliminar Trabajador" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void asignarEspecialidad(EspecialidadTrabajador et) throws Exception {
        String sql = "INSERT INTO ESPECIALIDAD_TRABAJADOR (IDTRA,IDEST,IDESP) VALUES (?,?,?)";
        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, et.IDTRA.getIDTRA());
            ps.setInt(2, et.IDEST.getIDEST());
            ps.setInt(3, et.IDESP.getIDESP());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al eliminar Trabajador" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public int obtenerCodPer(String persona) throws Exception {
        ResultSet rs;
        try {
            String sql = "SELECT IDPER FROM PERSONA WHERE CONCAT(NOMPER,APEPATPER,APEMATPER,DNIPER) like ?";
            PreparedStatement cs = this.conectar().prepareCall(sql);
            cs.setString(1, "%" + persona + "%");
            rs = cs.executeQuery();
            if (rs.next()) {
                return rs.getInt("IDPER");
            }
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
        return -1;
    }

    public void deshabilitar(Trabajador modelo) throws Exception {
        String sql = "update VW_TRABAJADOR set ESTDTRA = 'I' where IDTRA = ?";
        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDTRA());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al eliminar Especialidad" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public void habilitar(Trabajador modelo) throws Exception {
        String sql = "update VW_TRABAJADOR set ESTDTRA = 'A' where IDTRA = ?";
        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDTRA());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al eliminar Trabajador" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public List<Trabajador> listar(String TIPO_LISTA) throws Exception {
        System.out.println("listando trabajador");
        String sql = "SELECT * FROM VW_TRABAJADOR WHERE ESTDTRA = ? ORDER BY NOMPER DESC";
        List<Trabajador> lista = new ArrayList();
        Trabajador modelo;
        ResultSet rs;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, TIPO_LISTA);
            rs = ps.executeQuery();
            while (rs.next()) {
                modelo = new Trabajador();
                //PRIMARY-KEYS
                modelo.setIDTRA(rs.getInt("IDTRA"));
                modelo.IDPER.setIDPER(rs.getInt("IDPER"));

                // TRABAJADOR-PERSONA
                modelo.IDPER.setNOMPER(rs.getString("NOMPER"));
                modelo.IDPER.setAPEPATPER(rs.getString("APEPATPER"));
                modelo.IDPER.setAPEMATPER(rs.getString("APEMATPER"));
                modelo.IDPER.setDNIPER(rs.getString("DNIPER"));
                modelo.IDPER.setNUMCELPER(rs.getString("NUMCELPER"));
                modelo.IDPER.CODUBI.setCODUBI(rs.getString("CODUBI"));
                modelo.IDPER.setSEXPER(rs.getString("SEXPER"));
                modelo.IDPER.setDIRPER(rs.getString("DIRACTPER"));
                modelo.IDPER.setDNIPER(rs.getString("DNIPER"));
                modelo.IDPER.setFECNACPER(Util2Sql(rs.getDate("FECNACPER")));
                modelo.IDPER.CODUBI.setDEPAUBI(rs.getString("DEPAUBI"));
                modelo.IDPER.CODUBI.setPROVUBI(rs.getString("PROVUBI"));
                modelo.IDPER.CODUBI.setDISTUBI(rs.getString("DISTUBI"));

                // TRABAJADOR-PERSONA
                modelo.setCARGTRA(rs.getString("CARGTRA"));
                modelo.setPERFPROFTRA(rs.getString("PERFPROFTRA"));
                modelo.setFECREGTRA(rs.getString("FECREGTRA"));
                modelo.setUSERTRA(rs.getString("USERTRA"));
                modelo.setPASSTRA(rs.getString("PASSTRA"));
                modelo.setESTDTRA(rs.getString("ESTDTRA"));
                lista.add(modelo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public void modificar(Trabajador modelo) throws Exception {
        String sql = "{call SP_EDITTRABAJADOR(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement cs;
        try {
            cs = this.conectar().prepareCall(sql);
            cs.setInt("V_IDPER", modelo.IDPER.getIDPER());
            System.out.println(modelo.IDPER.getIDPER());
            cs.setInt("V_IDTRA", modelo.getIDTRA());
            System.out.println(modelo.getIDTRA());
            cs.setString("V_NOMPER", modelo.IDPER.getNOMPER());
            System.out.println(modelo.IDPER.getNOMPER());
            cs.setString("V_APEPATPER", modelo.IDPER.getAPEPATPER());
            System.out.println(modelo.IDPER.getAPEPATPER());
            cs.setString("V_APEMATPER", modelo.IDPER.getAPEMATPER());
            System.out.println(modelo.IDPER.getAPEMATPER());
            cs.setString("V_DNIPER", modelo.IDPER.getDNIPER());
            System.out.println(modelo.IDPER.getDNIPER());
            cs.setString("V_DIRACTPER", modelo.IDPER.getDIRPER());
            System.out.println(modelo.IDPER.getDIRPER());
            cs.setString("V_SEXPER", modelo.IDPER.getSEXPER());
            System.out.println(modelo.IDPER.getSEXPER());
            cs.setString("V_CODUBI", modelo.IDPER.CODUBI.getCODUBI());
            System.out.println(modelo.IDPER.CODUBI.getCODUBI());
            cs.setString("V_NUMCELPER", modelo.IDPER.getNUMCELPER());
            System.out.println(modelo.IDPER.getNUMCELPER());
            cs.setDate("V_FECNACPER", Util2Sql(modelo.IDPER.getFECNACPER()));//"21-07-2001"
            System.out.println(Util2Sql(modelo.IDPER.getFECNACPER()));
            cs.setString("V_CARGTRA", modelo.getCARGTRA());
            System.out.println(modelo.getCARGTRA());
            cs.setString("V_PERFPROFTRA", modelo.getPERFPROFTRA());
            System.out.println(modelo.getPERFPROFTRA());
            cs.registerOutParameter("MIERROR", Types.VARCHAR);
            cs.execute();
            System.out.println(cs.getString("MIERROR"));
            cs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }
}
