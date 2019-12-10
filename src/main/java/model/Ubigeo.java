package model;

public class Ubigeo {

    private String CODUBI;
    private String DISTUBI;
    private String DEPAUBI;
    private String PROVUBI;

    public Ubigeo() {
    }

    public Ubigeo(String ubigeo) {
        String[] value = ubigeo.split("\\|");
        if (value.length == 4) {
            this.CODUBI = value[0];
            this.DEPAUBI = value[1];
            this.PROVUBI = value[2];
            this.DISTUBI = value[3];
        }
    }

    public String getCODUBI() {
        return CODUBI;
    }

    public void setCODUBI(String CODUBI) {
        this.CODUBI = CODUBI;
    }

    public String getDISTUBI() {
        return DISTUBI;
    }

    public void setDISTUBI(String DISTUBI) {
        this.DISTUBI = DISTUBI;
    }

    public String getDEPAUBI() {
        return DEPAUBI;
    }

    public void setDEPAUBI(String DEPAUBI) {
        this.DEPAUBI = DEPAUBI;
    }

    public String getPROVUBI() {
        return PROVUBI;
    }

    public void setPROVUBI(String PROVUBI) {
        this.PROVUBI = PROVUBI;
    }

    @Override
    public String toString() {
        return CODUBI + "|" + DEPAUBI + "|" + PROVUBI + "|" + DISTUBI;
    }

    public String getVALOR() {
        return (CODUBI + DEPAUBI + PROVUBI + DISTUBI).toUpperCase().contains("null".toUpperCase()) ? "" : DISTUBI + ", " + PROVUBI + " - " + DEPAUBI;
    }

}
