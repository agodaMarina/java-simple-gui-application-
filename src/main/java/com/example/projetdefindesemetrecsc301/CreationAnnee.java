package com.example.projetdefindesemetrecsc301;

import Models.ConnexionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.gluonhq.charm.glisten.control.TextField;

import java.sql.Connection;
import java.sql.Statement;
import java.time.Year;

public class CreationAnnee {

    @FXML
    private Button Benregistrer;

    @FXML
    private TextField yearDebut;

    @FXML
    private TextField yearFin;


    @FXML
    private Label messageChampVide;

    @FXML
    private TextField code;




    void EnregistrementEnBase(ActionEvent event) {
        if(!yearDebut.getText().isBlank() && !yearFin.getText().isBlank()){
            EnregistrementEnBase();
            messageChampVide.setText("Nouvelle année ajoutée");
        }else {
            messageChampVide.setText("Veuillez renseigner les champs !!");
        }
    }

    public void EnregistrementEnBase(){
        Year dateDebut= Year.parse(yearDebut.getText());
        Year dateFin= Year.parse(yearFin.getText());
        String concatenation=dateDebut.toString()+"-"+dateFin.toString();
        code.setText(concatenation);
        String Code=code.getText();


       ConnexionDB c=new ConnexionDB();
        Connection connexion=c.getConnection();

        String requete= "INSERT INTO Annee  (code, date_de_debut,date_de_fin) VALUES('";
        String valeurInseree= Code+"','"+ dateDebut + "','" + dateFin + "')";
        String requeteTotale=requete+valeurInseree;

        try {
            Statement statement =connexion.createStatement();
            statement.executeUpdate(requeteTotale);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}



