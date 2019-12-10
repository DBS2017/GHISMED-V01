package controller;

import dao.AutoCompleteImpl;
import dao.ConsultaImpl;
import dao.EspecialidadImpl;
import dao.PacienteImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Consulta;
import model.Especialidad;
import model.Paciente;
import model.Persona;
import model.Trabajador;

@Named(value = "consultaC")
@SessionScoped
public class ConsultaC implements Serializable {

    private Consulta modelo;
    private ConsultaImpl dao;
    private List<Consulta> listadoCon;
    //----------------------------------------------//
    private List<Consulta> filteredCons;
    private List<Consulta> modelos;
    private Consulta selectedCons;

    //Vista
    private AutoCompleteImpl daoBuscar;
    private Especialidad acEspec;
    private PacienteImpl daoPaci;
    private EspecialidadImpl daoEspec;
    private String IDESP = "%%";
    private List<Date> range;

    private Paciente acPac;

    private String varEspec;
    private String varPac;

    private char tipoList = 'P';

    //aaaaa
    private List<Consulta> lista = null;
    private List<Paciente> listaPaciente = null;
    private List pacientes = null;
    //aaaaa

    public ConsultaC() {
        dao = new ConsultaImpl();
        modelo = new Consulta();
        listadoCon = new ArrayList();
        filteredCons = new ArrayList();
        daoBuscar = new AutoCompleteImpl();
        acEspec = new Especialidad();

        pacientes = new ArrayList();
        daoEspec = new EspecialidadImpl();
        daoPaci = new PacienteImpl();

        modelos = new ArrayList();
    }

    @PostConstruct
    public void init() {
        try {
            //  listEspecialidad = dao.listarEsp(getSesion().getIDEST(), "%%");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void prepararModelo() {
        modelo = new model.Consulta();
    }

    public void insertar() throws Exception {
        try {
            System.out.println("ss");
            //  System.out.println(getSesion().getIDEST());
            // System.out.println(getSesion().getIDTRA());
            //  modelo.setIDEST(getSesion().getIDEST());
//            modelo.getIDESPTRA().IDTRA.setIDTRA(getSesion().getIDTRA());
            dao.insertar(modelo);
            lista = null;
            mensaje("Registrado!", "Se registro correctamente el Prestamo.", 'I');
        } catch (Exception e) {
            mensaje("Error en el servidor!", "Vuelva a intentarlo en unos momentos.", 'F');
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            for (Consulta modelo : modelos) {
                dao.modificar(modelo);
            }
            limpiar();
            mensaje("Registrado!", "Se marcaron como entregados " + modelos.size() + "historia(s) prestada(s).", 'I');
        } catch (Exception e) {
            mensaje("Error", "Vuelva a intentarlo en unos mommentos", 'F');
            throw e;
        }
    }

    public List<Consulta> getLista() throws Exception {
        if (lista == null) {
            lista = dao.listar();
        }
        return lista;
    }

    public List<model.Persona> autocompletePaciente(String consulta) throws Exception {
        return pacientes = dao.listarPacientes(consulta);
    }

    public model.Persona getESPECIALISTA() throws Exception {
        model.Persona persona = dao.obtenerTrabajador(modelo.IDESPTRA.IDESTESP.getIDESTESP());
        System.out.println(persona.getIDPER());
        modelo.IDESPTRA.setIDESPTRA(persona.getIDPER());
        return persona;
    }

    public void limpiar() throws Exception {
        try {
            setModelo(new Consulta());
        } catch (Exception e) {
            throw e;
        }
    }

    public void refrescarEstablecimiento() {
        try {
            //int id = daoBuscar.idEstab(modelo.getESTABLECIMIENTO());
            //modelo.IDESPTRA.IDESTESP.IDEST.setIDEST(id);
            //listEspecialidad = daoBuscar.listarEspecialidad(modelo.getIDEST());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public void getEspecialista() throws Exception {
//        try {
//            for (Especialidad especialidad : listEspecialidad) {
////                if (especialidad.getIDESP() == modelo.getIDESP()) {
////                    modelo.setRESP(especialidad.getENCARGADO());
////                    //modelo.setIDTRA(especialidad.getIDTRA());
////                }
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
    public void mensaje(String title, String content, char n) {
        FacesContext fc = FacesContext.getCurrentInstance();
        switch (n) {
            case 'I':
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, content));
                break;
            case 'W':
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, title, content));
                break;
            case 'E':
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, content));
                break;
            case 'F':
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, title, content));
                break;
            default:
                break;
        }
    }

    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("MMM/yyyy");
        return formateador.format(ahora);
    }

    public List<String> completeTextEspec(String consult) throws Exception {
        return daoBuscar.buscarEspec(IDESP, consult);
    }

    //  Getter and Setter  //
    public Consulta getModelo() {
        return modelo;
    }

    public void setModelo(Consulta modelo) {
        this.modelo = modelo;
    }

    public ConsultaImpl getDao() {
        return dao;
    }

    public void setDao(ConsultaImpl dao) {
        this.dao = dao;
    }

    public List<Consulta> getListadoCon() {
        return listadoCon;
    }

    public void setListadoCon(List<Consulta> listadoCon) {
        this.listadoCon = listadoCon;
    }

    public List<Consulta> getFilteredCons() {
        return filteredCons;
    }

    public void setFilteredCons(List<Consulta> filteredCons) {
        this.filteredCons = filteredCons;
    }

    public Consulta getSelectedCons() {
        return selectedCons;
    }

    public void setSelectedCons(Consulta selectedCons) {
        this.selectedCons = selectedCons;
    }

    public AutoCompleteImpl getUbiD() {
        return daoBuscar;
    }

    public void setUbiD(AutoCompleteImpl daoBuscar) {
        this.daoBuscar = daoBuscar;
    }

    public Especialidad getAcEspec() {
        return acEspec;
    }

    public void setAcEspec(Especialidad acEspec) {
        this.acEspec = acEspec;
    }

    public String getVarEspec() {
        return varEspec;
    }

    public void setVarEspec(String varEspec) {
        this.varEspec = varEspec;
    }

    public Paciente getAcPac() {
        return acPac;
    }

    public void setAcPac(Paciente acPac) {
        this.acPac = acPac;
    }

    public String getVarPac() {
        return varPac;
    }

    public void setVarPac(String varPac) {
        this.varPac = varPac;
    }

    public PacienteImpl getDaoPaci() {
        return daoPaci;
    }

    public void setDaoPaci(PacienteImpl daoPaci) {
        this.daoPaci = daoPaci;
    }

    public EspecialidadImpl getDaoEspec() {
        return daoEspec;
    }

    public void setDaoEspec(EspecialidadImpl daoEspec) {
        this.daoEspec = daoEspec;
    }

    public List<Consulta> getModelos() {
        return modelos;
    }

    public void setModelos(List<Consulta> modelos) {
        this.modelos = modelos;
    }

    public char getTipoList() {
        return tipoList;
    }

    public void setTipoList(char tipoList) {
        this.tipoList = tipoList;
    }

    private Trabajador getSesion() {
        LoginC loginC = new LoginC();
        return (Trabajador) loginC.getSesion();
    }

    public String getIDESP() {
        return IDESP;
    }

    public void setIDESP(String IDESP) {
        this.IDESP = IDESP;
    }

    public List<Date> getRange() {
        return range;
    }

    public void setRange(List<Date> range) {
        this.range = range;
    }

}
