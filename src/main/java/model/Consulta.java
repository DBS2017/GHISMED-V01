package model;

import java.util.Date;

public class Consulta {

    private int IDCONS;
    public Paciente IDPAC = new Paciente();
    public EspecialidadTrabajador IDESPTRA = new EspecialidadTrabajador();
    private Date FECINICONS;
    private Date FECFINCONS;
    private String ESTDCONS;
    private String USER;
    private String RESP;
    private boolean devu;

    public int getIDCONS() {
        return IDCONS;
    }

    public void setIDCONS(int IDCONS) {
        this.IDCONS = IDCONS;
    }

    public String getESTDCONS() {
        return ESTDCONS;
    }

    public void setESTDCONS(String ESTDCONS) {
        this.ESTDCONS = ESTDCONS;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getRESP() {
        return RESP;
    }

    public void setRESP(String RESP) {
        this.RESP = RESP;
    }

    public boolean isDevu() {
        return devu;
    }

    public void setDevu(boolean devu) {
        this.devu = devu;
    }

    public Date getFECINICONS() {
        return FECINICONS;
    }

    public void setFECINICONS(Date FECINICONS) {
        this.FECINICONS = FECINICONS;
    }

    public Date getFECFINCONS() {
        return FECFINCONS;
    }

    public void setFECFINCONS(Date FECFINCONS) {
        this.FECFINCONS = FECFINCONS;
    }

    public Paciente getIDPAC() {
        return IDPAC;
    }

    public void setIDPAC(Paciente IDPAC) {
        this.IDPAC = IDPAC;
    }

    public EspecialidadTrabajador getIDESPTRA() {
        return IDESPTRA;
    }

    public void setIDESPTRA(EspecialidadTrabajador IDESPTRA) {
        this.IDESPTRA = IDESPTRA;
    }

}
