package Models;

public class Cours {
    String matiere;
    String enseigant;
    String classe;

    String anneeScolaire;

    public Cours(String matiere, String enseigant, String classe, String anneeScolaire) {
        this.matiere = matiere;
        this.enseigant = enseigant;
        this.classe = classe;
        this.anneeScolaire = anneeScolaire;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getEnseigant() {
        return enseigant;
    }

    public void setEnseigant(String enseigant) {
        this.enseigant = enseigant;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }
}
