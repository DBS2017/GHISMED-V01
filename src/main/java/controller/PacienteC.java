package controller;

import dao.PacienteImpl;
import dao.AutoCompleteImpl;
import dao.Reportes;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Consulta;
import model.Paciente;
import model.Trabajador;
import org.primefaces.PrimeFaces;
import services.Mensaje;
import services.Validar;
import services.serv;

@Named(value = "pacienteC")
@SessionScoped
public class PacienteC implements Serializable {

    private List<Paciente> lista = null;
    private Paciente modelo;
    private PacienteImpl dao;

    //Vista
    private List<Consulta> historia = null;
    private boolean TIPO_LISTA = Boolean.TRUE;

    public PacienteC() {
        dao = new PacienteImpl();
    }

    public void validar() throws Exception {
        try {
            Validar v = new Validar();
            model.Persona p;
            p = v.DNI(modelo.IDPER.getDNIPER());
            switch (p.getESTDPER()) {
                case "P":
                    Mensaje.error(Mensaje.VALIDAR_ERROR);
                    break;
                case "-":
                    Mensaje.error(Mensaje.VALIDAR_ERROR);
                    break;
                default:
                    Mensaje.info(Mensaje.VALIDAR);
                    modelo.IDPER = p;
                    break;
            }
            System.out.println();
        } catch (Exception e) {
            Mensaje.fatal();
            throw e;
        }
    }

    public void prepararModelo() {
        this.modelo = new Paciente();
    }

    public void insertar() throws Exception {
        try {
            //cambiar por el id del establecimiento del usuario
            dao.insertar(modelo);
            Mensaje.info(Mensaje.INSERTAR);
            lista = null;
            prepararModelo();
        } catch (Exception e) {
            Mensaje.error(Mensaje.INSERTAR_ERROR);
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            System.out.println(modelo.IDPER.getSEXPER());
            System.out.println(modelo.IDPER.getNOMPER());
            dao.modificar(modelo);
            lista = null;
            Mensaje.info(Mensaje.MODIFICAR);
        } catch (Exception e) {
            Mensaje.error(Mensaje.MODIFICAR_ERROR);
            throw e;
        }
    }

    public void habilitar() throws Exception {
        try {
            dao.habilitar(modelo);
            Mensaje.info(Mensaje.HABILITAR);
            forzarListado();
        } catch (Exception e) {
            Mensaje.error(Mensaje.HABILITAR_ERROR);
            throw e;
        }
    }

    public void deshabilitar() throws Exception {
        try {
            dao.deshabilitar(modelo);
            Mensaje.info(Mensaje.DESHABILITAR);
            forzarListado();
        } catch (Exception e) {
            Mensaje.error(Mensaje.DESHABILITAR_ERROR);
            throw e;
        }
    }

    public void forzarListado() {
        PrimeFaces.current().executeScript("PF('dt_paciente').clearFilters()");
        lista = null;
    }

    public List<Paciente> getLista() throws Exception {
        if (lista == null) {
            lista = dao.listar(this.TIPO_LISTA ? "A" : "I");
        }
        return lista;
    }

    public List<Consulta> getHistoria() throws Exception {
        if (historia == null) {
            System.out.println("historiando");
            historia = dao.verHistoria(modelo.getIDPAC());
        }
        return historia;
    }

    public void descargarPDF() throws Exception {
        Reportes report;

        try {
            System.out.println(modelo.getIDACOMPAC());

            report = new Reportes();
            Map<String, Object> parameters = new HashMap();
            report.exportPrograma(parameters, "PACIENTES.pdf", "PACIENTES_R");
            System.out.println(modelo.getIDACOMPAC());
            System.out.println(modelo.getIDACOMPAC());
        } catch (Exception e) {
            serv.mensajeWarn("Advertencia!", "No tiene ningún registro para mostrar");
        }
    }

    public void verHistoria() throws Exception {
        historia = dao.verHistoria(modelo.getIDPAC());
    }

    public PacienteImpl getDao() {
        return dao;
    }

    public void setDao(PacienteImpl dao) {
        this.dao = dao;
    }

    public Trabajador getSesion() {
        LoginC loginC = new LoginC();
        return (Trabajador) loginC.getSesion();
    }

    public Paciente getModelo() {
        return modelo;
    }

    public void setModelo(Paciente modelo) {
        this.modelo = modelo;
    }

    public boolean isTIPO_LISTA() {
        return TIPO_LISTA;
    }

    public void setTIPO_LISTA(boolean TIPO_LISTA) {
        this.TIPO_LISTA = TIPO_LISTA;
        lista = null;
    }

}
