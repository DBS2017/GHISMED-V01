package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TEstablecimiento;
import model.Consulta;

public class DashboardImpl extends Conexion {

//    public List<EstablecimientoEspecialidad> listarAsig() throws Exception {
//        List<EstablecimientoEspecialidad> lista = new ArrayList<>();
//        TEstablecimiento modelo;
//        ResultSet rs;
//        String sql = "SELECT * FROM VW_ESTABLECIMIENTO_INDICACDOR";
//        try {
//            rs = this.conectar().createStatement().executeQuery(sql);
//            while (rs.next()) {
//                modelo = new TEstablecimiento();
//                modelo.setIDEST(rs.getInt("IDEST"));
//                modelo.setESTA(rs.getString("NOMEST"));
//                modelo.setIDESP(rs.getInt("TOTESP"));
//                modelo.setIDTRAB(rs.getInt("TOTTRA"));
//                modelo.setIDASIGESP(rs.getInt("TOTCON"));
//                lista.add(modelo);
//            }
//            rs.close();
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println("listarAsig");
//            System.out.println(e.getMessage());
//            throw e;
//        } finally {
//            this.cerrar();
//        }
//        return lista;
//    }
//
//    public List<Consulta> listarAtenciones(String q) throws Exception {
//        List<Consulta> lista = new ArrayList<>();
//        Consulta modelo;
//        ResultSet rs;
//        String sql = "SELECT * FROM VW_CONSULTA_POR_SEXO";
//        try {
//            PreparedStatement ps = this.conectar().prepareStatement(sql);
//            //ps.setString(1, q);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                modelo = new Consulta();
//                modelo.setIDEST(rs.getInt("CANTIDAD"));
//                modelo.setESTDCONS(rs.getString("SEXPER"));
//                lista.add(modelo);
//            }
//            rs.close();
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println("listarAtenciones: " + e.getMessage());
//            System.out.println(e.getMessage());
//            throw e;
//        } finally {
//            this.cerrar();
//        }
//        return lista;
//    }
}
