package controller;

import dao.LoginImpl;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import model.Trabajador;
import org.primefaces.PrimeFaces;
import static services.serv.*;

@Named(value = "loginC")
@ViewScoped
public class LoginC implements Serializable {

    private Trabajador modelo;
    private LoginImpl dao;
    private String user = "";
    private String pwd;
    private String newPwd;
    private boolean rq = true;
    private boolean cu = false;

    public LoginC() {
        modelo = new Trabajador();
        dao = new LoginImpl();
    }

    public void init() {
        if (getSesion() != null) {
            irA("PD");
        }
    }

    public void addSesion(String key, Trabajador value) throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }

    public Trabajador getSesion() {
        return (Trabajador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    public static Trabajador obtenterSesion() {
        return (Trabajador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    public void cerrarSesion() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            FacesContext.getCurrentInstance().getExternalContext().
                    redirect("../../../");
            pwd = "";
            user = "";
        } catch (IOException ex) {
            mensajeFatal("Error al Salir", "No se ha podido cerrar la sesión.");
        }
    }

    public void changePassword() throws Exception {
        try {
            Trabajador t = getSesion();
            if (pwd.equals(getSesion().getPASSTRA())) {
                PrimeFaces.current().ajax().addCallbackParam("loggedIn", true);
                dao.editarCuenta(t.getIDTRA(), user, newPwd);
                t.setPASSTRA(newPwd);
                addSesion("user", t);
                mensajeInfo("Cambios de privilegios.", "Exitosamente fueron cambiados");
            } else {
                mensajeError("Contraseña incorrecta.", "La contraseña actual es incorrecta!");
            }
            pwd = "";

        } catch (Exception e) {
            mensajeFatal("Error.", "Ocurrio un error en el sistema.");
            throw e;
        }
    }

    public void validarSesion() {
        if (getSesion() == null) {
            irA("PL");
        }
    }

    public int hcPendientes() throws Exception {
        int result = 0;
        if ("AD".equals(getSesion().getCARGTRA())) {
            result = dao.hcPendientes(getSesion().getIDTRA());
        }
        return result;
    }

    public void irA(String page) {
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        try {
            switch (page) {
                case "PD":
                    System.out.println(contextPath);
                    FacesContext.getCurrentInstance().getExternalContext().
                            redirect(contextPath+"/web/vistas/Dashboard/Dashboard.xhtml");
                    break;
                case "PL":
                    FacesContext.getCurrentInstance().getExternalContext().
                            redirect(contextPath+"/web/vistas/login.xhtml");
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void iniciarSesion() throws Exception {
        try {
            modelo = dao.verificar(user, pwd);
            if (modelo.getCARGTRA() != null) {
                switch (modelo.getCARGTRA()) {
                    case "RD":
                        addSesion("user", modelo);
                        irA("PD");
                        break;
                    case "DE":
                        addSesion("user", modelo);
                        irA("PD");
                        break;
                    case "RS":
                        addSesion("user", modelo);
                        irA("PD");
                        break;
                    case "UE":
                        addSesion("user", modelo);
                        irA("PD");
                        break;
                    default:
                        pwd = "";
                        mensajeError("Usuario Incorrecto", "Usuario/Contraseña Incorrecta");
                        break;
                }
            } else {
                pwd = "";
                mensajeError("Usuario Incorrecto", "Usuario/Contraseña Incorrecta");
            }
        } catch (Exception e) {
            mensajeError("Error del servidor", "Estamos trabajando para solucionarlo.");
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Trabajador getModelo() {
        return modelo;
    }

    public void setModelo(Trabajador modelo) {
        this.modelo = modelo;
    }

    public LoginImpl getDao() {
        return dao;
    }

    public void setDao(LoginImpl dao) {
        this.dao = dao;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isRq() {
        return rq;
    }

    public void setRq(boolean rq) {
        this.rq = rq;
    }

    public boolean isCu() {
        return cu;
    }

    public void setCu(boolean cu) {
        this.cu = cu;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
