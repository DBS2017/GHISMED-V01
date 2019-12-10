package model;

import java.io.Serializable;
import java.util.Date;

public class EspecialidadTrabajador implements Serializable {
    
    public Trabajador IDTRA = new Trabajador();
    public TEstablecimiento IDESTESP = new TEstablecimiento();
    public Establecimiento IDEST = new Establecimiento();
    public Especialidad IDESP = new Especialidad();
    private String TURNESPTRA;
    private Date FECESPTRA;
    private int IDESPTRA;

    public Trabajador getIDTRA() {
        return IDTRA;
    }

    public void setIDTRA(Trabajador IDTRA) {
        this.IDTRA = IDTRA;
    }

    public Establecimiento getIDEST() {
        return IDEST;
    }

    public void setIDEST(Establecimiento IDEST) {
        this.IDEST = IDEST;
    }

    public Especialidad getIDESP() {
        return IDESP;
    }

    public void setIDESP(Especialidad IDESP) {
        this.IDESP = IDESP;
    }

    public String getTURNESPTRA() {
        return TURNESPTRA;
    }

    public void setTURNESPTRA(String TURNESPTRA) {
        this.TURNESPTRA = TURNESPTRA;
    }

    public Date getFECESPTRA() {
        return FECESPTRA;
    }

    public void setFECESPTRA(Date FECESPTRA) {
        this.FECESPTRA = FECESPTRA;
    }

    public TEstablecimiento getIDESTESP() {
        return IDESTESP;
    }

    public void setIDESTESP(TEstablecimiento IDESTESP) {
        this.IDESTESP = IDESTESP;
    }

    public int getIDESPTRA() {
        return IDESPTRA;
    }

    public void setIDESPTRA(int IDESPTRA) {
        this.IDESPTRA = IDESPTRA;
    }
    
    
}
