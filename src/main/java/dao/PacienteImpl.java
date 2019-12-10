package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.Consulta;
import model.Paciente;
import services.serv;

public class PacienteImpl extends Conexion {

    public void insertar(Paciente modelo) throws Exception {
        String sql = "{call SP_ADDPACIENTE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement cs;
        try {
            cs = this.conectar().prepareCall(sql);
            cs.setString("V_NOMPER", modelo.IDPER.getNOMPER());
            System.out.println(modelo.IDPER.getNOMPER());
            cs.setString("V_APEPATPER", modelo.IDPER.getAPEPATPER());
            System.out.println(modelo.IDPER.getAPEPATPER());
            cs.setString("V_APEMATPER", modelo.IDPER.getAPEMATPER());
            System.out.println(modelo.IDPER.getAPEMATPER());
            cs.setString("V_DNIPER", modelo.IDPER.getDNIPER());
            System.out.println(modelo.IDPER.getDNIPER());
            cs.setString("V_SEXPER", modelo.IDPER.getSEXPER());
            System.out.println(modelo.IDPER.getSEXPER());
            cs.setString("V_DIRACTPER", modelo.IDPER.getDIRPER());
            System.out.println(modelo.IDPER.getDIRPER());
            cs.setString("V_CODUBI", modelo.IDPER.CODUBI.getCODUBI());
            System.out.println(modelo.IDPER.getCODUBI());
            cs.setString("V_NUMCELPER", modelo.IDPER.getNUMCELPER());
            System.out.println(modelo.IDPER.getNUMCELPER());
            cs.setDate("V_FECNACPER", serv.sqlDate(modelo.IDPER.getFECNACPER()));
            System.out.println(serv.sqlDate(modelo.IDPER.getFECNACPER()));
            cs.setString("V_NUMSEGUPAC", modelo.getNUMSEGUPAC());
            System.out.println(modelo.getNUMSEGUPAC());
            cs.setString("V_TIPOSEGUPAC", modelo.getTIPOSEGUPAC());
            System.out.println(modelo.getTIPOSEGUPAC());
            cs.setString("V_DOMIPROCPAC", modelo.getDOMIPROCPAC());
            System.out.println(modelo.getDOMIPROCPAC());
            cs.setString("V_CODUBI_P", modelo.CODUBI.getCODUBI());
            System.out.println(modelo.CODUBI.getCODUBI());
            cs.setString("V_ESTCIVPAC", modelo.getESTCIVPAC());
            System.out.println(modelo.getESTCIVPAC());
            cs.setString("V_GRADINSTPAC", modelo.getGRADINSTPAC());
            System.out.println(modelo.getGRADINSTPAC());
            cs.setString("V_OCUPPAC", modelo.getOCUPPAC());
            System.out.println(modelo.getOCUPPAC());
            cs.setString("V_RELPAC", modelo.getRELPAC());
            System.out.println(modelo.getRELPAC());
            cs.registerOutParameter("MIERROR", Types.VARCHAR);
            cs.executeUpdate();
            System.out.println(cs.getString("MIERROR"));
            cs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void modificar(Paciente modelo) throws Exception {

        String sql = "{call SP_EDITPACIENTE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        CallableStatement cs;
        try {
            cs = this.conectar().prepareCall(sql);
            //PERSONA-PACIENTE
            cs.setString("V_NOMPER", modelo.IDPER.getNOMPER());
            cs.setString("V_APEPATPER", modelo.IDPER.getAPEPATPER());
            cs.setString("V_APEMATPER", modelo.IDPER.getAPEMATPER());
            cs.setString("V_DNIPER", modelo.IDPER.getDNIPER());
            cs.setString("V_SEXPER", modelo.IDPER.getSEXPER());
            cs.setString("V_DIRACTPER", modelo.IDPER.getDIRPER());
            cs.setString("V_CODUBI", modelo.IDPER.CODUBI.getCODUBI());
            cs.setString("V_NUMCELPER", modelo.IDPER.getNUMCELPER());
            cs.setDate("V_FECNACPER", modelo.IDPER.getFECHA(modelo.IDPER.getFECNACPER()));

            cs.setInt("V_IDPER", modelo.IDPER.getIDPER());
            cs.setInt("V_IDPAC", modelo.getIDPAC());
            //PACIENTE
            cs.setString("V_NUMSEGUPAC", modelo.getNUMSEGUPAC());
            cs.setString("V_TIPOSEGUPAC", modelo.getTIPOSEGUPAC());
            cs.setString("V_DOMIPROCPAC", modelo.getDOMIPROCPAC());
            cs.setString("V_CODUBI_P", modelo.CODUBI.getCODUBI());
            cs.setString("V_ESTCIVPAC", modelo.getESTCIVPAC());
            cs.setString("V_GRADINSTPAC", modelo.getGRADINSTPAC());
            cs.setString("V_OCUPPAC", modelo.getOCUPPAC());
            cs.setString("V_RELPAC", modelo.getRELPAC());
            cs.registerOutParameter("MIERROR", Types.VARCHAR);
            cs.executeUpdate();
            System.out.println(cs.getString("MIERROR"));
            cs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al modificar PACIENTE: " + e.getMessage());
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<Consulta> verHistoria(int IDPAC) throws Exception {
        System.out.println("listando historia");
        List<Consulta> list = new ArrayList<>();
        Consulta p;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM VW_HISTORIA WHERE IDPAC = ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, IDPAC);
            rs = ps.executeQuery();
            while (rs.next()) {
                p = new Consulta();
                p.getIDESPTRA().IDEST.setNOMEST(rs.getString("NOMEST"));
                p.getIDESPTRA().IDESP.setNOMESP(rs.getString("NOMESP"));
                p.setFECINICONS(serv.Sql2Util(rs.getDate("FECINICONS")));
                list.add(p);
            }
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return list;
    }

    public void habilitar(Paciente modelo) throws Exception {
        String sql = "update PACIENTE set ESTDPAC = 'A' where IDPAC=?";
        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDPAC());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void deshabilitar(Paciente modelo) throws Exception {
        String sql = "update PACIENTE set ESTDPAC = 'I' where IDPAC=?";
        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDPAC());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<Paciente> listar(String TIPO_LISTA) throws Exception {
        String sql = "SELECT * FROM VW_PACIENTE where ESTDPAC = ?";
        List<Paciente> lista = new ArrayList();
        Paciente modelo;
        ResultSet rs;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, TIPO_LISTA);
            rs = ps.executeQuery();
            while (rs.next()) {
                modelo = new Paciente();
                //PRIMARY-KEYS
                modelo.setIDPAC(rs.getInt("IDPAC"));
                modelo.IDPER.setIDPER(rs.getInt("IDPER"));
                //PACIENTE
                modelo.setNUMSEGUPAC(rs.getString("NUMSEGUPAC"));
                modelo.setTIPOSEGUPAC(rs.getString("TIPOSEGUPAC"));
                modelo.setFECREGPAC(rs.getString("FECREGPAC"));
                modelo.setDOMIPROCPAC(rs.getString("DOMIPROCPAC"));
                modelo.setESTCIVPAC(rs.getString("ESTCIVPAC"));
                modelo.setRELPAC(rs.getString("RELPAC"));
                modelo.setGRADINSTPAC(rs.getString("GRADINSTPAC"));
                modelo.setOCUPPAC(rs.getString("OCUPPAC"));
                modelo.setESTCIVPAC(rs.getString("ESTCIVPAC"));
                modelo.setOCUPPAC(rs.getString("OCUPPAC"));
                modelo.CODUBI.setCODUBI(rs.getString("CODUBI_P"));
                modelo.CODUBI.setDEPAUBI(rs.getString("DEPAUBI_P"));
                modelo.CODUBI.setPROVUBI(rs.getString("PROVUBI_P"));
                modelo.CODUBI.setDISTUBI(rs.getString("DISTUBI_P"));

                //PERSONA-PACIENTE
                modelo.IDPER.setSEXPER(rs.getString("SEXPER"));
                modelo.IDPER.setFECNACPER(modelo.IDPER.setFECHA(rs.getDate("FECNACPER")));
                modelo.IDPER.setNOMPER(rs.getString("NOMPER"));
                modelo.IDPER.setAPEPATPER(rs.getString("APEPATPER"));
                modelo.IDPER.setAPEMATPER(rs.getString("APEMATPER"));
                modelo.IDPER.setNUMCELPER(rs.getString("NUMCELPER"));
                modelo.IDPER.setDNIPER(rs.getString("DNIPER"));
                modelo.IDPER.setDIRPER(rs.getString("DIRACTPER"));
                modelo.IDPER.CODUBI.setCODUBI(rs.getString("CODUBI"));
                modelo.IDPER.CODUBI.setDEPAUBI(rs.getString("DEPAUBI"));
                modelo.IDPER.CODUBI.setPROVUBI(rs.getString("PROVUBI"));
                modelo.IDPER.CODUBI.setDISTUBI(rs.getString("DISTUBI"));
                lista.add(modelo);
            }
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("soy yoooooo");
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }
}
