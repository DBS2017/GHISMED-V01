package services;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensaje {

    public static final String INSERTAR = "Excelente, su registro se instertó con éxito.";
    public static final String MODIFICAR = "Excelente, su registro se actualizó con éxito.";
    public static final String HABILITAR = "Excelente, su registro se deshabilitó con éxito.";
    public static final String DESHABILITAR = "Excelente, su registro se habilitó con éxito.";
    public static final String VALIDAR= "Excelente, puede continuar con su registro.";
    
    public static final String INSERTAR_ERROR = "Lo sentimos, su registro no se instertó con éxito.";
    public static final String MODIFICAR_ERROR = "Lo sentimos, su registro no se modificó con éxito.";
    public static final String HABILITAR_ERROR = "Lo sentimos, su registro no se deshabilitó con éxito.";
    public static final String DESHABILITAR_ERROR = "Lo sentimos, su registro no se habilitó con éxito.";
    public static final String VALIDAR_ERROR = "Lo sentimos, Ya existe un registro con ese DNI.";

    public static void info(String message) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Informe!", message));
    }

    public static void warn(String message) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "¡Advertencia!", message));
    }

    public static void error(String message) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", message));
    }

    public static void fatal(String message) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Fatal!", message));
    }

    public static void fatal() {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Error del Sistema!", "Vuelva a intentarlo en unos minutos."));
    }
}
