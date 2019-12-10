package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.Consulta;
import model.Paciente;
import model.Persona;
import static services.serv.Sql2Util;
import static services.serv.Util2Sql;

public class ConsultaImpl extends Conexion {

    public void insertar(Consulta modelo) throws Exception {
        PreparedStatement ps;
        String sql = "INSERT INTO CONSULTA (IDCONS,IDPAC,IDESPTRA) values (17,?,?)";
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.IDPAC.IDPER.getIDPER());
            ps.setInt(2, modelo.IDESPTRA.getIDESPTRA());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ConsultaImpl");
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void modificar(Consulta consulta) throws Exception {
        System.out.println(consulta.getIDPAC().getIDPAC());
        System.out.println(consulta.getIDESPTRA().IDTRA.getIDTRA());
        System.out.println(consulta.getFECINICONS());
        String sql = "UPDATE CONSULTA SET FECFINCONS = CURRENT_TIMESTAMP - (5/24), ESTDCONS = 'I' WHERE IDPAC = ? AND IDTRA =? AND FECINICONS = ?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, consulta.getIDPAC().getIDPAC());
            ps.setInt(2, consulta.getIDESPTRA().IDTRA.getIDTRA());
            ps.setDate(3, Util2Sql(consulta.getFECINICONS()));
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ConsultaImpl");
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<Consulta> listar() throws Exception {
        String sql = "SELECT * FROM VW_CONSULTA where FECFINCONS IS NULL AND IDEST LIKE ?";
        if (false) {
            sql = "SELECT * FROM VW_CONSULTA where FECFINCONS IS NOT NULL AND IDEST LIKE ?";
        }
        List<Consulta> lista = new ArrayList();
        Consulta modelo;
        ResultSet rs;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "1");
            rs = ps.executeQuery();
            while (rs.next()) {
                modelo = new Consulta();
                modelo.IDPAC.setIDPAC(rs.getInt("IDPAC"));
                modelo.IDPAC.IDPER.setNOMPER(rs.getString("NOMPER"));
                modelo.IDPAC.IDPER.setAPEPATPER(rs.getString("APEPATPER"));
                modelo.IDPAC.IDPER.setAPEMATPER(rs.getString("APEMATPER"));
                modelo.IDPAC.setNUMSEGUPAC(rs.getString("NUMSEGUPAC"));
                modelo.IDPAC.setTIPOSEGUPAC(rs.getString("TIPOSEGUPAC"));
                modelo.IDESPTRA.IDESTESP.IDESP.setNOMESP(rs.getString("NOMESP"));

                modelo.setFECINICONS(Sql2Util(rs.getDate("FECINICONS")));
                modelo.setFECFINCONS(Sql2Util(rs.getDate("FECFINCONS")));
                modelo.IDESPTRA.IDESTESP.IDEST.setIDEST(rs.getInt("IDESPTRA"));
                modelo.setDevu(rs.getString("FECFINCONS") != null);
                lista.add(modelo);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public List<model.Persona> listarPacientes(String CONSULTA) throws Exception {
        String sql = "SELECT * FROM VW_PACIENTE_AC WHERE UPPER(REPLACE(PERSONA,'|',' ')) LIKE UPPER(?)";
        List<model.Persona> lista = new ArrayList();
        Persona modelo;
        ResultSet rs;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "%" + CONSULTA + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("PERSONA"));
                modelo = new Persona(rs.getString("PERSONA"));
                modelo.setIDPER(rs.getInt("IDPAC"));
                lista.add(modelo);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }

    public model.Persona obtenerTrabajador(int IDESPTRA) throws Exception {
        String sql = "SELECT T.IDESPTRA, T.NOMPER,T.APEPATPER,T.APEMATPER FROM VW_TRABAJADOR T WHERE T.IDESTESP = ? AND ROWNUM < 2";
        Persona modelo = new Persona();
        ResultSet rs;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, IDESPTRA);
            rs = ps.executeQuery();
            while (rs.next()) {
                modelo.setIDPER(rs.getInt("IDESPTRA"));
                modelo.setNOMPER(rs.getString("NOMPER"));
                modelo.setAPEPATPER(rs.getString("APEPATPER"));
                modelo.setAPEMATPER(rs.getString("APEMATPER"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return modelo;
    }
}
