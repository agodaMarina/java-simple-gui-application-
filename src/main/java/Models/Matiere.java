package Models;

public class Matiere {
    private String code;
    private String intitule;

    public Matiere(String code, String intitule) {
        this.code = code;
        this.intitule=intitule;
    }

    public Matiere(String intitule) {
        this.intitule = intitule;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
}
