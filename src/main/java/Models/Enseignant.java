package Models;

public class Enseignant {
    private String nom;
    private String prenom;
   private String tel;
   private String matricule;
   private String email;

    public Enseignant(String nom, String prenom, String tel, String matricule, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.matricule = matricule;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
