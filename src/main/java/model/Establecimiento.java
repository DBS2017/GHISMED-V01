package model;

public class Establecimiento {

    private int IDEST;
    private String NOMEST;
    private String DIREST;
    private String UBIGEO;
    public Ubigeo CODUBI = new Ubigeo();
    private String TELFEST;
    private String MAILEST;
    private String TRABTOTEST;
    private int ESTDEST;
    private String DISTEST;
    private String ESTABLECIMIENTO;

    public int getIDEST() {
        return IDEST;
    }

    public void setIDEST(int IDEST) {
        this.IDEST = IDEST;
    }

    public String getNOMEST() {
        return NOMEST;
    }

    public void setNOMEST(String NOMEST) {
        this.NOMEST = NOMEST;
    }

    public String getDIREST() {
        return DIREST;
    }

    public void setDIREST(String DIREST) {
        this.DIREST = DIREST;
    }

    public String getTELFEST() {
        return TELFEST;
    }

    public void setTELFEST(String TELFEST) {
        this.TELFEST = TELFEST;
    }

    public String getMAILEST() {
        return MAILEST;
    }

    public void setMAILEST(String MAILEST) {
        this.MAILEST = MAILEST;
    }

    public String getTRABTOTEST() {
        return TRABTOTEST;
    }

    public void setTRABTOTEST(String TRABTOTEST) {
        this.TRABTOTEST = TRABTOTEST;
    }

    public int getESTDEST() {
        return ESTDEST;
    }

    public void setESTDEST(int ESTDEST) {
        this.ESTDEST = ESTDEST;
    }

    public String getDISTEST() {
        return DISTEST;
    }

    public void setDISTEST(String DISTEST) {
        this.DISTEST = DISTEST;
    }

    public String getUBIGEO() {
        return UBIGEO;
    }

    public void setUBIGEO(String UBIGEO) {
        this.UBIGEO = UBIGEO;
    }

    public String getESTABLECIMIENTO() {
        return ESTABLECIMIENTO;
    }

    public void setESTABLECIMIENTO(String ESTABLECIMIENTO) {
        this.ESTABLECIMIENTO = ESTABLECIMIENTO;
    }

    public Ubigeo getCODUBI() {
        return CODUBI;
    }

    public void setCODUBI(Ubigeo CODUBI) {
        this.CODUBI = CODUBI;
    }

}
