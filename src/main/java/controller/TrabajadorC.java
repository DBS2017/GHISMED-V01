package controller;

import dao.TrabajadorImpl;
import dao.AutoCompleteImpl;
import dao.Reportes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Trabajador;
import org.primefaces.PrimeFaces;
import services.Validar;
import static controller.LoginC.obtenterSesion;
import model.EspecialidadTrabajador;
import model.Ubigeo;
import org.primefaces.event.FlowEvent;
import services.Mensaje;
import static services.serv.mensajeError;
import static services.serv.mensajeFatal;
import static services.serv.mensajeInfo;

@Named(value = "trabajadorC")
@SessionScoped
public class TrabajadorC implements Serializable {

    private int ESTDTRA;

    private EspecialidadTrabajador et;
    private Trabajador modelo;
    private TrabajadorImpl dao;
    private final AutoCompleteImpl daoBuscar;
    //----------------------------------------------//
    private List<Trabajador> filteredTra;

    //Vista
    private List<Trabajador> lista = null;
    private boolean TIPO_LISTA = Boolean.TRUE;

    public TrabajadorC() {
        dao = new TrabajadorImpl();
        modelo = new Trabajador();
        daoBuscar = new AutoCompleteImpl();
        filteredTra = new ArrayList();
    }

    public void validar() throws Exception {
        try {
            Validar v = new Validar();
            modelo.IDPER = v.DNI(modelo.IDPER.getDNIPER());
        } catch (Exception e) {
            Mensaje.fatal();
            throw e;
        }
    }

    public void insertar() throws Exception {
        try {
            dao.insertar(modelo);
            Mensaje.info(Mensaje.INSERTAR);
            forzarListado();
        } catch (Exception e) {
            Mensaje.error(Mensaje.INSERTAR_ERROR);
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            System.out.println(modelo.IDPER.getNOMPER());
            dao.modificar(modelo);
            forzarListado();
            Mensaje.info(Mensaje.MODIFICAR);
        } catch (Exception e) {
            Mensaje.error(Mensaje.MODIFICAR_ERROR);
            throw e;
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(modelo);
            limpiar();
            forzarListado();
            mensajeInfo("Eliminacion", "completado...");
        } catch (Exception e) {
            mensajeFatal();
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

    public void prepararModelo() {
        this.modelo = new Trabajador();
    }

    public void prepararAsignacionEspecialidad() {
        et = new EspecialidadTrabajador();
    }

    public void asignarEspecialidad() throws Exception {
        try {
            et.IDTRA.setIDTRA(1);
            dao.asignarEspecialidad(et);
            limpiar();
            forzarListado();
            mensajeInfo("Eliminacion", "completado...");
        } catch (Exception e) {
            mensajeFatal();
            throw e;
        }
    }

    public void forzarListado() {
        lista = null;
        PrimeFaces.current().executeScript("PF('dt_trabajador').clearFilters()");
    }

    public void limpiar() throws Exception {
        try {
            modelo = new Trabajador();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void descargarPDF() throws Exception {
        Reportes report;
        try {
            report = new Reportes();
            Map<String, Object> parameters = new HashMap();
            parameters.put("ESTDTRA", ESTDTRA);
            report.exportPrograma(parameters, "TRABAJADORES.pdf", "TRABAJADORES");
        } catch (Exception e) {
            mensajeFatal();
        }
    }

    public String flujoWizard(FlowEvent e) {
        boolean saltar = false;
        if (saltar) {
            return "ss";
        } else {
            return e.getNewStep();
        }
    }

    public List<Ubigeo> autocomUbige(String consulta) throws Exception {
        return daoBuscar.buscarUbige0(consulta);
    }

    public List<String> autocomEstab(String consulta) throws Exception {
        return daoBuscar.buscarEstab(consulta);
    }

    public void refrescarUbigeo() throws Exception {
        if (modelo.IDPER.getIDPER() != 0) {
            String cod = daoBuscar.codUbige(modelo.IDPER.CODUBI.toString());
            System.out.println(cod);
            //trabajador.IDPER.CODUBI.setCODUBI(cod);
        } else {
            String cod = daoBuscar.codUbige(modelo.IDPER.CODUBI.toString());
            System.out.println(cod);
            //trabajador.IDPER.CODUBI.setCODUBI(cod);
        }
    }

    // GETTER AND SETTER//
    public TrabajadorImpl getDao() {
        return dao;
    }

    public void setDao(TrabajadorImpl dao) {
        this.dao = dao;
    }

    public List<Trabajador> getFilteredTra() {
        return filteredTra;
    }

    public void setFilteredTra(List<Trabajador> filteredTra) {
        this.filteredTra = filteredTra;
    }

    public int getESTDTRA() {
        return ESTDTRA;
    }

    public void setESTDTRA(int ESTDTRA) {
        this.ESTDTRA = ESTDTRA;
    }

    public EspecialidadTrabajador getEt() {
        return et;
    }

    public void setEt(EspecialidadTrabajador et) {
        this.et = et;
    }

    public boolean isTIPO_LISTA() {
        return TIPO_LISTA;
    }

    public void setTIPO_LISTA(boolean TIPO_LISTA) {
        this.TIPO_LISTA = TIPO_LISTA;
        lista = null;
    }

    public List<Trabajador> getLista() throws Exception {
        if (lista == null) {
            lista = dao.listar(this.TIPO_LISTA ? "A" : "I");
        }
        return lista;
    }

    public void setLista(List<Trabajador> lista) {
        this.lista = lista;
    }

    public Trabajador getModelo() {
        return modelo;
    }

    public void setModelo(Trabajador modelo) {
        this.modelo = modelo;
    }
}
