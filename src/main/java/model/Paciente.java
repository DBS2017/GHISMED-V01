package model;

import java.io.Serializable;

public class Paciente implements Serializable {

    private int IDPAC;
    private String NUMSEGUPAC;
    private String TIPOSEGUPAC;
    private String FECREGPAC;
    private String DOMIPROCPAC;
    private String ESTCIVPAC;
    private String GRADINSTPAC;
    private String OCUPPAC;
    private String RELPAC;
    public Persona IDPER = new Persona();
    public Ubigeo CODUBI = new Ubigeo();
    
    //borrar
    private String ACOMPAC;
    private String NOMCOMP;
    private String DATOPERS;
    private String ESTDPAC;
    private int IDACOMPAC;

    private String CODUBI_P;
    private String UBIGEO_P;

    /*
     * ***********HISTORIA****************
     */
    private String ESPECIALIDAD;
    private String ESTABLECIMIENTO;

    /*
     * ***********************************
     */
    public int getIDPAC() {
        return IDPAC;
    }

    public void setIDPAC(int IDPAC) {
        this.IDPAC = IDPAC;
    }

    public String getNUMSEGUPAC() {
        return NUMSEGUPAC;
    }

    public void setNUMSEGUPAC(String NUMSEGUPAC) {
        this.NUMSEGUPAC = NUMSEGUPAC;
    }

    public String getTIPOSEGUPAC() {
        return TIPOSEGUPAC;
    }

    public void setTIPOSEGUPAC(String TIPOSEGUPAC) {
        this.TIPOSEGUPAC = TIPOSEGUPAC;
    }

    public String getFECREGPAC() {
        return FECREGPAC;
    }

    public void setFECREGPAC(String FECREGPAC) {
        this.FECREGPAC = FECREGPAC;
    }

    public String getDOMIPROCPAC() {
        return DOMIPROCPAC;
    }

    public void setDOMIPROCPAC(String DOMIPROCPAC) {
        this.DOMIPROCPAC = DOMIPROCPAC;
    }

    public String getESTCIVPAC() {
        return ESTCIVPAC;
    }

    public void setESTCIVPAC(String ESTCIVPAC) {
        this.ESTCIVPAC = ESTCIVPAC;
    }

    public String getGRADINSTPAC() {
        return GRADINSTPAC;
    }

    public void setGRADINSTPAC(String GRADINSTPAC) {
        this.GRADINSTPAC = GRADINSTPAC;
    }

    public String getOCUPPAC() {
        return OCUPPAC;
    }

    public void setOCUPPAC(String OCUPPAC) {
        this.OCUPPAC = OCUPPAC;
    }

    public String getRELPAC() {
        return RELPAC;
    }

    public void setRELPAC(String RELPAC) {
        this.RELPAC = RELPAC;
    }

    public String getACOMPAC() {
        return ACOMPAC;
    }

    public void setACOMPAC(String ACOMPAC) {
        this.ACOMPAC = ACOMPAC;
    }

    public Persona getIDPER() {
        return IDPER;
    }

    public void setIDPER(Persona IDPER) {
        this.IDPER = IDPER;
    }

    public String getNOMCOMP() {
        return NOMCOMP;
    }

    public void setNOMCOMP(String NOMCOMP) {
        this.NOMCOMP = NOMCOMP;
    }

    public String getDATOPERS() {
        return DATOPERS;
    }

    public void setDATOPERS(String DATOPERS) {
        this.DATOPERS = DATOPERS;
    }

    public String getESTDPAC() {
        return ESTDPAC;
    }

    public void setESTDPAC(String ESTDPAC) {
        this.ESTDPAC = ESTDPAC;
    }

    public int getIDACOMPAC() {
        return IDACOMPAC;
    }

    public void setIDACOMPAC(int IDACOMPAC) {
        this.IDACOMPAC = IDACOMPAC;
    }

    public String getCODUBI_P() {
        return CODUBI_P;
    }

    public void setCODUBI_P(String CODUBI_P) {
        this.CODUBI_P = CODUBI_P;
    }

    public String getESPECIALIDAD() {
        return ESPECIALIDAD;
    }

    public void setESPECIALIDAD(String ESPECIALIDAD) {
        this.ESPECIALIDAD = ESPECIALIDAD;
    }

    public String getESTABLECIMIENTO() {
        return ESTABLECIMIENTO;
    }

    public void setESTABLECIMIENTO(String ESTABLECIMIENTO) {
        this.ESTABLECIMIENTO = ESTABLECIMIENTO;
    }

    public String getUBIGEO_P() {
        return UBIGEO_P;
    }

    public void setUBIGEO_P(String UBIGEO_P) {
        this.UBIGEO_P = UBIGEO_P;
    }

    public Ubigeo getCODUBI() {
        return CODUBI;
    }

    public void setCODUBI(Ubigeo CODUBI) {
        this.CODUBI = CODUBI;
    }

}
