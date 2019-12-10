package model;

import java.util.Date;

public class Persona {

    private int IDPER = 0;
    private String NOMPER;
    private String APEPATPER;
    private String APEMATPER;
    private Date FECNACPER;
    private String DNIPER;
    private String SEXPER;
    private String DIRPER;
    public Ubigeo CODUBI = new Ubigeo();
    private String NUMCELPER;
    private String ESTDPER;

    public Persona() {
    }

    public Persona(String persona) {
        String[] value = persona.split("\\|");
        if (value.length == 5) {
            this.IDPER = Integer.valueOf(value[0]);
            this.NOMPER = value[1];
            this.APEPATPER = value[2];
            this.APEMATPER = value[3];
            this.DNIPER = value[4];
        }

    }

    public String getFULLNAME() {
        String valor = (NOMPER + ", " + (APEPATPER + " " + APEMATPER).toUpperCase());

        return valor.toUpperCase().contains("null".toUpperCase()) ? "" : valor;
    }

    public int getIDPER() {
        return IDPER;
    }

    public void setIDPER(int IDPER) {
        this.IDPER = IDPER;
    }

    public String getNOMPER() {
        return NOMPER;
    }

    public void setNOMPER(String NOMPER) {
        this.NOMPER = NOMPER;
    }

    public String getAPEPATPER() {
        return APEPATPER;
    }

    public void setAPEPATPER(String APEPATPER) {
        this.APEPATPER = APEPATPER;
    }

    public String getAPEMATPER() {
        return APEMATPER;
    }

    public void setAPEMATPER(String APEMATPER) {
        this.APEMATPER = APEMATPER;
    }

    public Date getFECNACPER() {
        return FECNACPER;
    }

    public void setFECNACPER(Date FECNACPER) {
        this.FECNACPER = FECNACPER;
    }

    public String getDNIPER() {
        return DNIPER;
    }

    public void setDNIPER(String DNIPER) {
        this.DNIPER = DNIPER;
    }

    public String getSEXPER() {
        return SEXPER;
    }

    public void setSEXPER(String SEXPER) {
        this.SEXPER = SEXPER;
    }

    public String getDIRPER() {
        return DIRPER;
    }

    public void setDIRPER(String DIRPER) {
        this.DIRPER = DIRPER;
    }

    public Ubigeo getCODUBI() {
        return CODUBI;
    }

    public void setCODUBI(Ubigeo CODUBI) {
        this.CODUBI = CODUBI;
    }

    public String getNUMCELPER() {
        return NUMCELPER;
    }

    public void setNUMCELPER(String NUMCELPER) {
        this.NUMCELPER = NUMCELPER;
    }

    public String getESTDPER() {
        return ESTDPER;
    }

    public void setESTDPER(String ESTPER) {
        this.ESTDPER = ESTPER;
    }

    /*
    *Obtener fechar para enviar a la DB
     */
    public java.sql.Date getFECHA(Date fecha) {
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        return sqlDate;
    }

    public Date setFECHA(java.sql.Date fecha) {
        Date utilDate = new Date(fecha.getTime());
        return utilDate;
    }

    @Override
    public String toString() {
        return IDPER + "|" + NOMPER + "|" + APEPATPER + "|" + APEMATPER + "|" + DNIPER;
    }

}
