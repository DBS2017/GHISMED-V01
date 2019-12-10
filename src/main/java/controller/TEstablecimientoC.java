package controller;

import dao.AutoCompleteImpl;
import dao.TEstablecimientoImpl;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Especialidad;
import model.TEstablecimiento;
import org.primefaces.PrimeFaces;
import services.Mensaje;

@Named(value = "testablecimientoC")
@SessionScoped
public class TEstablecimientoC implements Serializable {

    private boolean TIPO_LISTA = Boolean.TRUE;

    private TEstablecimiento modelo;

    private List especialidades;
    private List<TEstablecimiento> filtro;
    private List<TEstablecimiento> lista = null;

    private final AutoCompleteImpl daox;
    private final TEstablecimientoImpl dao;

    public TEstablecimientoC() {
        this.dao = new TEstablecimientoImpl();
        this.daox = new AutoCompleteImpl();
    }

    public void prepararModelo() {
        this.modelo = new TEstablecimiento();
    }

    public void insertar() {
        try {
            //cambiar por el id del establecimiento del usuario
            modelo.IDEST.setIDEST(1);
            dao.insertar(modelo);
            Mensaje.info(Mensaje.INSERTAR);
            forzarListado();
        } catch (Exception e) {
            Mensaje.error(Mensaje.INSERTAR_ERROR);
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
        especialidades = null;
        PrimeFaces.current().executeScript("PF('dt_testablecimiento').clearFilters()");
    }

    public List<TEstablecimiento> getLista() throws Exception {
        if (lista == null) {
            lista = dao.listar(this.TIPO_LISTA ? "A" : "I");
        }
        return lista;
    }

    public List<Especialidad> getEspecialidades() throws Exception {
        if (especialidades == null) {
            //cambiar por el id del establecimiento del usuario
            especialidades = daox.listarEspecialidad(1);
        }
        return especialidades;
    }

    public TEstablecimiento getModelo() {
        return modelo;
    }

    public void setModelo(TEstablecimiento modelo) {
        this.modelo = modelo;
    }

    public List<TEstablecimiento> getFiltro() {
        return filtro;
    }

    public void setFiltro(List<TEstablecimiento> filtro) {
        this.filtro = filtro;
    }

    public boolean getTIPO_LISTA() {
        return TIPO_LISTA;
    }

    public void setTIPO_LISTA(boolean TIPO_LISTA) {
        this.TIPO_LISTA = TIPO_LISTA;
        lista = null;
    }
}
