package controller;

import dao.EspecialidadImpl;
import dao.Reportes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import model.Especialidad;
import model.TEstablecimiento;
import services.serv;

@Named(value = "especialidadTrabajadorC")
@SessionScoped
public class EspecialidadTrabajadorC implements Serializable {

    private TEstablecimiento modelo;
    private EspecialidadImpl dao;
    private List<Especialidad> listaEspecialidad = null;
    //----------------------------------------------//
    private List<Especialidad> filteredEsp;
    private String ESTDESP = "ACTIVOS";

    public EspecialidadTrabajadorC() {

    }

    public void prepararAsignacionEstablecimiento() throws Exception {
        modelo = new TEstablecimiento();
    }

    public void asignarEstablecimiento() throws Exception {
        System.out.println(modelo.IDESP.getIDESP());
        System.out.println(modelo.IDEST.getIDEST());
        dao.asignarEstablecimiento(modelo);
    }

    public void registrar() throws Exception {
        try {
            limpiar();
            listaEspecialidad = null;
            serv.mensajeInfo("Registro", "completo...");
        } catch (Exception e) {
            serv.mensajeFatal();
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            limpiar();
            listaEspecialidad = null;
            serv.mensajeInfo("Actualizacion", "completa...");
        } catch (Exception e) {
            serv.mensajeFatal();
            throw e;
        }
    }

    public void eliminar() throws Exception {
        try {
            serv.mensajeInfo("Eliminado!", "Se realizo correctamente");
            limpiar();
            listaEspecialidad = null;
        } catch (Exception e) {
            serv.mensajeFatal();
            throw e;
        }
    }

    public List<Especialidad> getListaEspecialidad() throws Exception {
        System.out.println("holaaaaa estoy listando");
        if (listaEspecialidad == null) {
        }
        return listaEspecialidad;
    }

    public void limpiar() throws Exception {
        try {
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void descargarPDF() throws Exception {
        Reportes report;
        try {
            report = new Reportes();
            Map<String, Object> parameters = new HashMap();
            parameters.put("ESTADO", ESTDESP);
            report.exportPrograma(parameters, "ESPECIALIDADES.pdf", "ESPECIALIDADES");
            serv.ReporteGenerado();
        } catch (Exception e) {
            serv.mensajeFatal();
        }
    }

    // Código generado
    public EspecialidadImpl getDao() {
        return dao;
    }

    public void setDao(EspecialidadImpl dao) {
        this.dao = dao;
    }

    public List<Especialidad> getFilteredEsp() {
        return filteredEsp;
    }

    public void setFilteredEsp(List<Especialidad> filteredEsp) {
        this.filteredEsp = filteredEsp;
    }

    public String getESTDESP() {
        return ESTDESP;
    }

    public void setESTDESP(String ESTDESP) {
        this.ESTDESP = ESTDESP;
    }

    public TEstablecimiento getModelo() {
        return modelo;
    }

    public void setModelo(TEstablecimiento modelo) {
        this.modelo = modelo;
    }

}
