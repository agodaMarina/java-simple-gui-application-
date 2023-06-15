package com.example.projetdefindesemetrecsc301;

import Models.ConnexionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Random;

public class PageEnseignant {

    @FXML
    private Button close;

    @FXML
    private TextField email;

    @FXML
    private Label messageChampVide;
    @FXML
    private Button BouttonInscription;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField tel;

    @FXML
    void CloseWindow(ActionEvent event) {

    }

    public void EnregistrementEnBase(){
        String Nom= nom.getText();
        String Prenom=prenom.getText();
        String Tel=tel.getText();

        //generation du n°matricule
        Random obj = new Random();
        int numero=obj.nextInt(100);
        String caractereDuNom=Nom.substring(0, 3);

        String matriculeGenere=" "+numero+caractereDuNom;



        String Email=email.getText();
        ConnexionDB c=new ConnexionDB();
        Connection connexion=c.getConnection();

        String requete= "INSERT INTO Enseignant  (nom, prenom,tel,matricule,email) VALUES('";
        String valeurInseree=Nom + "','" + Prenom +"','" +Tel+"','" +matriculeGenere+"','" +Email+ "')";
        String requeteTotale=requete+valeurInseree;

        try {
            Statement statement =connexion.createStatement();
            statement.executeUpdate(requeteTotale);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void AjoutEnseignant(ActionEvent e){
        if(!nom.getText().isBlank() && !prenom.getText().isBlank()){
            EnregistrementEnBase();
            messageChampVide.setText("succès");
            Stage stage=(Stage) BouttonInscription.getScene().getWindow();
            stage.close();
        }else {
            messageChampVide.setText("Veuillez renseigner les champs !!");
        }
    }
}
