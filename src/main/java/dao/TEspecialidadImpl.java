package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import model.Persona;
import model.TEspecialidad;
import model.Trabajador;

public class TEspecialidadImpl extends Conexion {

    public void insertar(TEspecialidad modelo) throws Exception {
        String sql = "INSERT INTO ESPECIALIDAD_TRABAJADOR (IDTRA,IDESTESP) VALUES (?,?)";
        PreparedStatement ps;
        try {
            ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.IDTRA.getIDTRA());
            ps.setInt(2, modelo.IDESTESP.getIDESTESP());
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public List<TEspecialidad> listar(String TIPO_LISTA) throws Exception, Exception, Exception, Exception {
        System.out.println("Lis");
        String sql = "SELECT * FROM VW_ESPECIALIDAD_TRANSA WHERE TURNESPTRA = ?";
        List<TEspecialidad> listado = new ArrayList();
        TEspecialidad modelo;
        ResultSet rs;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, TIPO_LISTA);
            rs = ps.executeQuery();
            while (rs.next()) {
                modelo = new TEspecialidad();
                modelo.setIDESPTRA(rs.getInt("IDESPTRA"));
                modelo.IDTRA.setIDTRA(rs.getInt("IDTRA"));
                modelo.setFECESPTRA(rs.getDate("FECESPTRA"));
                modelo.IDTRA.setCARGTRA(rs.getString("CARGTRA"));
                modelo.IDTRA.setUSERTRA(rs.getString("USERTRA"));
                modelo.IDESTESP.IDESP.setIDESP(rs.getInt("IDESP"));
                modelo.IDESTESP.IDESP.setNOMESP(rs.getString("NOMESP"));
                modelo.IDESTESP.IDEST.setNOMEST(rs.getString("NOMEST"));
                modelo.IDTRA.IDPER.setNOMPER(rs.getString("NOMPER"));
                modelo.IDTRA.IDPER.setAPEMATPER(rs.getString("APEMATPER"));
                modelo.IDTRA.IDPER.setAPEPATPER(rs.getString("APEPATPER"));
                modelo.IDTRA.IDPER.setDNIPER(rs.getString("DNIPER"));
                modelo.setTURNESPTRA(rs.getString("TURNESPTRA"));
                listado.add(modelo);
            }
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROR AQUI IMPL");
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public void deshabilitar(TEspecialidad modelo) throws Exception {
        String sql = "UPDATE ESPECIALIDAD_TRABAJADOR SET TURNESPTRA = 'I' WHERE IDESPTRA = ? AND IDTRA = ?";
        
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDESPTRA());
            ps.setInt(2, modelo.IDTRA.getIDTRA());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("errorr");
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void habilitar(TEspecialidad modelo) throws Exception {
        String sql = "UPDATE ESPECIALIDAD_TRABAJADOR SET TURNESPTRA = 'A' WHERE IDESPTRA = ? AND IDTRA = ?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDESPTRA());
            ps.setInt(2, modelo.IDTRA.getIDTRA());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error");
            throw e;
        } finally {
            this.cerrar();
        }
    }
    
   /* public List<Trabajador> listarTrabajador(String especitra) throws Exception {
        System.out.println("Lista Trabajador");
        String sql = "SELECT * FROM VW_TRABAJADOR_BUSCAR WHERE UPPER(REPLACE(PERSONA,'|',' ')) LIKE UPPER(?) ";   
        List<Trabajador> lista = new ArrayList();
        Trabajador modelo;
        ResultSet rs;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "%" + especitra + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                modelo = new Trabajador();
                modelo.IDPER.setIDPER(rs.getInt("IDPER"));
                modelo.IDPER.setNOMPER(rs.getString("NOMPER"));
                modelo.IDPER.setAPEMATPER(rs.getString("APEMATPER"));
                modelo.IDPER.setAPEPATPER(rs.getString("APEPATPER"));
                //modelo.IDPER.setDNIPER(rs.getString("DNIPER"));
                modelo.setIDTRA(rs.getInt("IDTRA"));
                lista.add(modelo);
            }
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROR IMPL");
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }*/
    
    
    public List<model.Persona> listarTrabajador(String ESPETRA) throws Exception {
         System.out.println("Lista Trabajador");
        String sql = "SELECT * FROM VW_TRABAJADOR_BUSCAR WHERE UPPER(REPLACE(PERSONA,'|',' ')) LIKE UPPER(?) ";
        List<model.Persona> lista = new ArrayList();
        Persona modelo;
        ResultSet rs;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "%" + ESPETRA + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("PERSONA"));
                modelo = new Persona(rs.getString("PERSONA"));
                modelo.setIDPER(rs.getInt("IDTRA"));
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
