package controller;

import dao.AutoCompleteImpl;
import dao.TEspecialidadImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import model.Especialidad;
import model.TEspecialidad;
import model.Trabajador;
import org.primefaces.PrimeFaces;
import services.Mensaje;

@Named(value = "tEspecialidadC")
@SessionScoped
public class TEspecialidadC implements Serializable {

    private boolean TIPO_LISTA = Boolean.TRUE;
    private TEspecialidad modelo;
    private List especialidades;
    private final TEspecialidadImpl dao;
    private final AutoCompleteImpl daox;
    private List<TEspecialidad> lista = null;
    private List<TEspecialidad> filtro;
    private List trabajadores;
    

    public TEspecialidadC() {
        this.dao = new TEspecialidadImpl();
        this.daox = new AutoCompleteImpl();
        
    }

    public void forzarListado() {
        lista = null;
        especialidades = null;
        PrimeFaces.current().executeScript("PF('dt_tespecialidad').clearFilters()");
    }

    public void insertar() {
        try {
            modelo.IDTRA.setIDTRA(1);
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
    

    public void prepararModelo() {
        this.modelo = new TEspecialidad();
    }

    public boolean isTIPO_LISTA() {
        return TIPO_LISTA;
    }

    public void setTIPO_LISTA(boolean TIPO_LISTA) {
        this.TIPO_LISTA = TIPO_LISTA;
        lista = null;
    }

    public TEspecialidad getModelo() {
        return modelo;
    }

    public void setModelo(TEspecialidad modelo) {
        this.modelo = modelo;
    }

    public List<TEspecialidad> getLista() throws Exception {
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
    public void setLista(List<TEspecialidad> lista) {
        this.lista = lista;
    }
    public void setEspecialidades(List especialidades) {
        this.especialidades = especialidades;
    }

    public List<TEspecialidad> getFiltro() {
        return filtro;
    }

    public void setFiltro(List<TEspecialidad> filtro) {
        this.filtro = filtro;
    }

    public List<Trabajador> getTrabajadores() {
        return trabajadores;
    }
    public List<model.Persona> autocompleteTrabajador(String esptra) throws Exception {
        return trabajadores = dao.listarTrabajador(esptra);
    }
    public void setTrabajadores(List<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
    } 
}
