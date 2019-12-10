package controller;

import dao.EstablecimientoImpl;
import dao.AutoCompleteImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Establecimiento;
import dao.Reportes;
import model.Ubigeo;
import org.primefaces.PrimeFaces;
import services.Mensaje;
import services.serv;

@Named(value = "establecimientoC")
@SessionScoped
public class EstablecimientoC implements Serializable {

    private List<Establecimiento> lista = null;
    private List<Establecimiento> establecimientosA = null;
    private List<Establecimiento> establecimientosI = null;
    private List<Establecimiento> establecimientosAA = null;
    private List<Establecimiento> establecimientosNA = null;
    private List<Establecimiento> filteredEst;
    private Establecimiento selectedEst;
    private String ESTDEST = "ACTIVO";
    private EstablecimientoImpl dao = new EstablecimientoImpl();
    private Establecimiento modelo;
    private final AutoCompleteImpl daoBuscar = new AutoCompleteImpl();
    private boolean mail;
    private boolean TIPO_LISTA = Boolean.TRUE;

    public void prepararModelo() {
        this.modelo = new Establecimiento();
    }

    public void insertar() throws Exception {
        try {
            dao.registrar(modelo);
            // lista = null;
            forzarListado();
            limpiar();
            Mensaje.info(Mensaje.INSERTAR);

        } catch (Exception e) {
            Mensaje.error(Mensaje.INSERTAR_ERROR);
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(modelo);
            // lista = null;
            forzarListado();
            limpiar();
            Mensaje.info(Mensaje.MODIFICAR);
        } catch (Exception e) {
            Mensaje.error(Mensaje.MODIFICAR_ERROR);
            throw e;
        }
    }

    public void deshabilitar() throws Exception {
        try {
            dao.deshabilitar(modelo);
            // lista = null;
            forzarListado();
            Mensaje.info(Mensaje.DESHABILITAR);
        } catch (SQLException e) {
            Mensaje.error(Mensaje.DESHABILITAR_ERROR);
            throw e;
        }
    }

    public void habilitar() throws Exception {
        try {
            dao.habilitar(modelo);
            // lista = null;
            forzarListado();
            Mensaje.info(Mensaje.HABILITAR);
        } catch (SQLException e) {
            Mensaje.error(Mensaje.HABILITAR_ERROR);
            throw e;
        }
    }

    public void forzarListado() {
        lista = null;
        PrimeFaces.current().executeScript("PF('dt_establecimiento').clearFilters()");
    }

    public void descargarPDF() throws Exception {
        Reportes report;
        try {
            report = new Reportes();
            Map<String, Object> parameters = new HashMap();
            parameters.put("ESTDEST", ESTDEST);
            report.exportPrograma(parameters, "ESTABLECIMIENTOS-" + ESTDEST + "S.pdf", "ESTABLECIMIENTOS");
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Establecimiento> getLista() throws Exception {
        if (lista == null) {
            lista = dao.listar(this.TIPO_LISTA ? "A" : "I");
        }
        return lista;
    }

//    public List<Establecimiento> getEstablecimientosA() throws Exception {
//        if (establecimientosA == null) {
//            establecimientosA = dao.listarActivos();
//        }
//        return establecimientosA;
//    }
//
//    public List<Establecimiento> getEstablecimientosI() throws Exception {
//        if (establecimientosI == null) {
//            establecimientosI = dao.listarInactivos();
//        }
//        return establecimientosI;
//    }
//
//    public List<Establecimiento> getEstablecimientosAA() throws Exception {
//        if (establecimientosAA == null) {
//            establecimientosAA = dao.listarAsignados();
//        }
//        return establecimientosAA;
//    }
//
//    public List<Establecimiento> getEstablecimientosNA() throws Exception {
//        if (establecimientosNA == null) {
//            establecimientosNA = dao.listarNoAsignados();
//        }
//        return establecimientosNA;
//    }
    public void limpiar() throws Exception {
        try {
            modelo = new Establecimiento();
        } catch (Exception e) {
        }
    }

    public List<Ubigeo> autocomUbigeo(String consulta) throws Exception {
        return daoBuscar.buscarUbigeo(consulta);
    }

    public EstablecimientoImpl getDao() {
        return dao;
    }

    public void setDao(EstablecimientoImpl dao) {
        this.dao = dao;
    }

    public Establecimiento getModelo() {
        return modelo;
    }

    public void setModelo(Establecimiento modelo) {
        this.modelo = modelo;
    }

    public List<Establecimiento> getFilteredEst() {
        return filteredEst;
    }

    public void setFilteredEst(List<Establecimiento> filteredEst) {
        this.filteredEst = filteredEst;
    }

    public Establecimiento getSelectedEst() {
        return selectedEst;
    }

    public void setSelectedEst(Establecimiento selectedEst) {
        this.selectedEst = selectedEst;
    }

    public String getESTDEST() {
        return ESTDEST;
    }

    public void setESTDEST(String ESTDEST) {
        this.ESTDEST = ESTDEST;
    }

    public boolean isMail() {
        return mail;
    }

    public void setMail(boolean mail) {
        this.mail = mail;
    }

    public boolean isTIPO_LISTA() {
        return TIPO_LISTA;
    }

    public void setTIPO_LISTA(boolean TIPO_LISTA) {
        System.out.println("sdsf");
        this.TIPO_LISTA = TIPO_LISTA;
        lista = null;
    }
}
