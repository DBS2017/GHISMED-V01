package controller;

import dao.PersonaImpl;
import dao.AutoCompleteImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import model.Persona;
import static services.serv.mensajeFatal;
import static services.serv.mensajeInfo;

@Named(value = "personaC")
@SessionScoped
public class PersonaC implements Serializable {

    private Persona modelo;
    private PersonaImpl dao;
    private List<Persona> listaPersona;
    private Persona selectedPers;

    AutoCompleteImpl daoBuscar;

    public PersonaC() {
        dao = new PersonaImpl();
        modelo = new Persona();
        daoBuscar = new AutoCompleteImpl();
    }

    @PostConstruct
    public void init() {
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(modelo);
            limpiar();
            listaPersona = null;
            mensajeInfo("Registro", "completo...");
        } catch (Exception e) {
            mensajeFatal();
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(selectedPers);
            listaPersona = null;
            limpiar();
            mensajeInfo("Actualizacion", "completa...");
        } catch (Exception e) {
            mensajeFatal();
            throw e;
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(selectedPers);
            limpiar();
            listaPersona = null;
            mensajeInfo("Eliminacion", "completado...");
            limpiar();
        } catch (Exception e) {
            mensajeFatal();
            throw e;
        }
    }

    public List<Persona> getListaPersona() throws Exception {
        if (listaPersona == null) {
            listaPersona = dao.listar();
        }
        return listaPersona;
    }

    public void limpiar() throws Exception {
        try {
            modelo = new Persona();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public List<String> autocomUbige(String consulta) throws Exception {
        return daoBuscar.buscarUbige(consulta);
    }

    public void refrescarUbigeo() {
        try {
//            String cod = daoBuscar.codUbige(modelo.getUBIGEO());
//            modelo.CODUBI.setCODUBI(cod);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /* codigo Generado*/
    public Persona getModelo() {
        return modelo;
    }

    public void setModelo(Persona modelo) {
        this.modelo = modelo;
    }

    public PersonaImpl getDao() {
        return dao;
    }

    public void setDao(PersonaImpl dao) {
        this.dao = dao;
    }

    public AutoCompleteImpl getDaoBuscar() {
        return daoBuscar;
    }

    public void setDaoBuscar(AutoCompleteImpl daoBuscar) {
        this.daoBuscar = daoBuscar;
    }

    public Persona getSelectedPers() {
        return selectedPers;
    }

    public void setSelectedPers(Persona selectedPers) {
        this.selectedPers = selectedPers;
    }
}
