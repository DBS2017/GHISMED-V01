
package model;

import java.util.Date;

public class TEspecialidad {

    private int IDESPTRA;
    public Trabajador IDTRA = new Trabajador();
    public TEstablecimiento IDESTESP = new TEstablecimiento();
    private String TURNESPTRA;
    private Date FECESPTRA;

    public int getIDESPTRA() {
        return IDESPTRA;
    }

    public void setIDESPTRA(int IDESPTRA) {
        this.IDESPTRA = IDESPTRA;
    }

    public Trabajador getIDTRA() {
        return IDTRA;
    }

    public void setIDTRA(Trabajador IDTRA) {
        this.IDTRA = IDTRA;
    }

    public TEstablecimiento getIDESTESP() {
        return IDESTESP;
    }

    public void setIDESTESP(TEstablecimiento IDESTESP) {
        this.IDESTESP = IDESTESP;
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
    
    
}
