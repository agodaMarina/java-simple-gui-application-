package com.example.projetdefindesemetrecsc301;

import Models.ConnexionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.gluonhq.charm.glisten.control.TextField;


import java.sql.Connection;
import java.sql.Statement;

public class CreationClasse {

    @FXML
    private Button Benregistrer;


    @FXML
    private TextField code;

    @FXML
    private TextField intitule;

    @FXML
    private Label messageChampVide;


    public void EnregistrementEnBase(){
        String Cod = code.getText();
        String Intitulee=intitule.getText();
        ConnexionDB c=new ConnexionDB();
        Connection connexion=c.getConnection();

        String requete= "INSERT INTO Classe  (code, intitule) VALUES('";
        String valeurInseree= Cod + "','" + Intitulee + "')";
        String requeteTotale=requete+valeurInseree;

        try{
            Statement statement = connexion.createStatement();
            statement.executeUpdate(requeteTotale);
        }catch (Exception e){

            e.printStackTrace();
            e.getCause();
    }

    }

    public void AjoutClasse(ActionEvent e){
        if(!code.getText().isBlank() && !intitule.getText().isBlank()){
            EnregistrementEnBase();
            messageChampVide.setText("nouvelle classe ajoutée");
        }else {
            messageChampVide.setText("Veuillez renseigner les champs !!");

        }
    }

}



