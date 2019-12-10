package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Persona;
import services.serv;

public class PersonaImpl extends Conexion implements ICRUD<Persona> {

    @Override
    public void registrar(Persona persona) throws Exception {
        PreparedStatement ps;
        String sql = "insert into PERSONA"
                + " (NOMPER,APEPATPER,APEMATPER,FECNACPER,DNIPER,SEXPER,DIRACTPER,CODUBI,NUMCELPER,IDPER)"
                + "values (?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setString(1, persona.getNOMPER());
            ps.setString(2, persona.getAPEPATPER());
            ps.setString(3, persona.getAPEMATPER());
            ps.setDate(4, serv.sqlDate(persona.getFECNACPER()));//persona.getFECNACPER()
            ps.setString(5, persona.getDNIPER());
            ps.setString(6, persona.getSEXPER());
            ps.setString(7, persona.getDIRPER());
            ps.setString(8, persona.CODUBI.getCODUBI());
            ps.setString(9, persona.getNUMCELPER());
            System.out.println("soy el codigo del acompañante dao1");
            System.out.println(persona.getIDPER());
            ps.setInt(10, persona.getIDPER());
            ps.executeUpdate();
            System.out.println("soy el codigo del acompañante dao2");
            System.out.println(persona.getIDPER());
            ps.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en Registrar Persona" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Persona persona) throws Exception {
        String sql = "update PERSONA set NOMPER=?,APEPATPER=?,APEMATPER=?,FECNACPER=?,DNIPER=?,SEXPER=?,DIRACTPER=?,CODUBI=?,NUMCELPER=?,ESTDPER=? where IDPER = ?";
        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setString(1, persona.getNOMPER());
            ps.setString(2, persona.getAPEPATPER());
            ps.setString(3, persona.getAPEMATPER());
            ps.setDate(4, serv.sqlDate(persona.getFECNACPER()));
            ps.setString(5, persona.getDNIPER());
            ps.setString(6, persona.getSEXPER());
            ps.setString(7, persona.getDIRPER());
            ps.setString(8, persona.CODUBI.getCODUBI());
            ps.setString(9, persona.getNUMCELPER());
            ps.setString(10, persona.getESTDPER());
            ps.setInt(11, persona.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error daoooooo");
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Persona persona) throws Exception {
        String sql = "update PERSONA set ESTDPER = 1 where IDPER=?";
        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, persona.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al eliminar Persona" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Persona> listar() throws Exception {
        String sql = "SELECT * FROM VW_PERSONA WHERE ESTDPER = 'A'";
        List<Persona> listado;
        Persona per;
        ResultSet rs;
        try {
            rs = this.conectar().createStatement().executeQuery(sql);
            listado = new ArrayList();
            while (rs.next()) {
                per = new Persona();
                per.setIDPER(rs.getInt("IDPER"));
                per.setNOMPER(rs.getString("NOMPER"));
                per.setAPEPATPER(rs.getString("APEPATPER"));
                per.setAPEMATPER(rs.getString("APEMATPER"));
                per.setFECNACPER(serv.utilDate(rs.getDate("FECNACPER")));
                per.setDNIPER(rs.getString("DNIPER"));
                per.setSEXPER(rs.getString("SEXPER"));
                per.setDIRPER(rs.getString("DIRACTPER"));
                per.CODUBI.setCODUBI(rs.getString("CODUBI"));
                per.setNUMCELPER(rs.getString("NUMCELPER"));
                per.setESTDPER(rs.getString("ESTDPER"));
                listado.add(per);
            }
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public int generarIDPER() throws Exception {
        try {
            String sql = "SELECT TOP 1 (IDPER + 1) AS IDPER FROM PERSONA ORDER BY IDPER DESC";//WHERE CONCAT(NOMPER,APEPATPER,APEMATPER,DNIPER) like ?
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("IDPER");
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return -1;
    }

    public Persona autocompletePers(Integer idper) throws Exception {
        Persona per = new Persona();
        ResultSet rs;
        String sql = "SELECT * FROM VW_PERSONA WHERE IDPER LIKE ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setInt(1, idper);
            rs = ps.executeQuery();
            while (rs.next()) {
                per.setIDPER(rs.getInt("IDPER"));
                per.setNOMPER(rs.getString("NOMPER"));
                per.setAPEPATPER(rs.getString("APEPATPER"));
                per.setAPEMATPER(rs.getString("APEMATPER"));
                per.setFECNACPER(per.setFECHA(rs.getDate("FECNACPER")));
                per.setDNIPER(rs.getString("DNIPER"));
                per.setSEXPER(rs.getString("SEXPER"));
                per.setDIRPER(rs.getString("DIRACTPER"));
                per.CODUBI.setCODUBI(rs.getString("UBICACION"));
                per.setNUMCELPER(rs.getString("NUMCELPER"));
                per.setESTDPER(rs.getString("ESTDPER"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("errror implemento paciente-persona\n" + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
        return per;
    }
}
