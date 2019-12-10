package model;

import java.io.Serializable;

public class Trabajador implements Serializable {

    private int IDTRA;
    private String CARGTRA;
    private String PERFPROFTRA;
    private String FECREGTRA;
    private String USERTRA;
    private String PASSTRA;
    public Persona IDPER = new Persona();
    private String ESTDTRA;
    private String NOMCOMPL;
    private String DIRESTAB;

    public String getDIRESTAB() {
        return DIRESTAB;
    }

    public void setDIRESTAB(String DIRESTAB) {
        this.DIRESTAB = DIRESTAB;
    }

    public int getIDTRA() {
        return IDTRA;
    }

    public void setIDTRA(int IDTRA) {
        this.IDTRA = IDTRA;
    }

    public String getCARGTRA() {
        return CARGTRA;
    }

    public void setCARGTRA(String CARGTRA) {
        this.CARGTRA = CARGTRA;
    }

    public String getPERFPROFTRA() {
        return PERFPROFTRA;
    }

    public void setPERFPROFTRA(String PERFPROFTRA) {
        this.PERFPROFTRA = PERFPROFTRA;
    }

    public String getFECREGTRA() {
        return FECREGTRA;
    }

    public void setFECREGTRA(String FECREGTRA) {
        this.FECREGTRA = FECREGTRA;
    }

    public String getUSERTRA() {
        return USERTRA;
    }

    public void setUSERTRA(String USERTRA) {
        this.USERTRA = USERTRA;
    }

    public String getPASSTRA() {
        return PASSTRA;
    }

    public void setPASSTRA(String PASSTRA) {
        this.PASSTRA = PASSTRA;
    }

    public String getESTDTRA() {
        return ESTDTRA;
    }

    public void setESTDTRA(String ESTDTRA) {
        this.ESTDTRA = ESTDTRA;
    }
    
    public String getNOMCOMPL() {
        return NOMCOMPL;
    }

    public void setNOMCOMPL(String NOMCOMPL) {
        this.NOMCOMPL = NOMCOMPL;
    }

    public Persona getIDPER() {
        return IDPER;
    }

    public void setIDPER(Persona IDPER) {
        this.IDPER = IDPER;
    }
}
