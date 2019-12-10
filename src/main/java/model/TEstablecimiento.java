package model;

import java.util.Date;

public class TEstablecimiento {

    private int IDESTESP;
    public Establecimiento IDEST = new Establecimiento();
    public Especialidad IDESP = new Especialidad();
    private String ESTDESTESP;
    private Date FECESTESP;

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

    public String getESTDESTESP() {
        return ESTDESTESP;
    }

    public void setESTDESTESP(String ESTDESTESP) {
        this.ESTDESTESP = ESTDESTESP;
    }

    public int getIDESTESP() {
        return IDESTESP;
    }

    public void setIDESTESP(int IDESTESP) {
        this.IDESTESP = IDESTESP;
    }

    public Date getFECESTESP() {
        return FECESTESP;
    }

    public void setFECESTESP(Date FECESTESP) {
        this.FECESTESP = FECESTESP;
    }

}
