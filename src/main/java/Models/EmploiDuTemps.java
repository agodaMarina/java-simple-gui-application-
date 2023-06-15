package Models;

public class EmploiDuTemps {
    private String enseignant;
    private String cour;
    private String heure;

    public EmploiDuTemps(String enseignant, String cour, String heure) {
        this.enseignant = enseignant;
        this.cour = cour;
        this.heure = heure;
    }

    public String getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(String enseignant) {
        this.enseignant = enseignant;
    }

    public String getCour() {
        return cour;
    }

    public void setCour(String cours) {
        this.cour = cours;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
}
