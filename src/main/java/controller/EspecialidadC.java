package controller;

import dao.EspecialidadImpl;
import dao.Reportes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Especialidad;
import services.serv;
import java.util.ArrayList;
import org.primefaces.PrimeFaces;
import services.Mensaje;

@Named(value = "especialidadC")
@SessionScoped
public class EspecialidadC implements Serializable {

    private Especialidad especialidad;
    private Especialidad modelo;
//    private TEstablecimiento ee;
    private EspecialidadImpl dao;
    private List<Especialidad> lista = null;
    //----------------------------------------------//
    private List<Especialidad> filteredEsp;
    private List<Especialidad> filtro;
    private String ESTDESP = "ACTIVOS";
    private boolean TIPO_LISTA = Boolean.TRUE;

    public EspecialidadC() {
        dao = new EspecialidadImpl();
        especialidad = new Especialidad();
    }

    public void prepararModelo() {
        this.modelo = new Especialidad();
    }

    public void insertar() {
        try {
            dao.registrar(especialidad);
            forzarListado();
            Mensaje.info(Mensaje.INSERTAR);
        } catch (Exception e) {
            Mensaje.error(Mensaje.INSERTAR_ERROR);
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(modelo);
            forzarListado();
            Mensaje.info(Mensaje.MODIFICAR);
        } catch (Exception e) {
            Mensaje.error(Mensaje.MODIFICAR_ERROR);
            throw e;
        }
    }

    public void habilitar() {
        try {
            dao.habilitar(modelo);
            Mensaje.info(Mensaje.HABILITAR);
            forzarListado();
        } catch (Exception e) {
            Mensaje.error(Mensaje.HABILITAR_ERROR);
        }
    }

    public void deshabilitar() {
        try {
            dao.deshabilitar(modelo);
            Mensaje.info(Mensaje.DESHABILITAR);
            forzarListado();
        } catch (Exception e) {
            Mensaje.error(Mensaje.DESHABILITAR_ERROR);
        }
    }

    public void forzarListado() {
        lista = null;
        PrimeFaces.current().executeScript("PF('dt_especialidad').clearFilters()");
    }

    public List<Especialidad> getLista() throws Exception {
        if (lista == null) {
            lista = dao.listar(this.TIPO_LISTA ? "A" : "I");
        }
        return lista;
    }

    public void limpiar() throws Exception {
        try {
            especialidad = new Especialidad();
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

//    public List<Especialidad> getEspecialidadesNA() throws Exception {
//        if (especialidadesNA == null) {
//            especialidadesNA = dao.listarNoAsignados(ee.IDEST.getIDEST());
//        }
//        return especialidadesNA;
//    }
    // Código generado
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

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

    public boolean isTIPO_LISTA() {
        return TIPO_LISTA;
    }

    public void setTIPO_LISTA(boolean TIPO_LISTA) {
        this.TIPO_LISTA = TIPO_LISTA;
        lista = null;
    }

    public Especialidad getModelo() {
        return modelo;
    }

    public void setModelo(Especialidad modelo) {
        this.modelo = modelo;
    }

    public List<Especialidad> getFiltro() {
        return filtro;
    }

    public void setFiltro(List<Especialidad> filtro) {
        this.filtro = filtro;
    }

}
