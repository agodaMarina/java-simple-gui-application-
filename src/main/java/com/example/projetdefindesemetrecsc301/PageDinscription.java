package com.example.projetdefindesemetrecsc301;

import Models.ConnexionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;


public class PageDinscription implements Initializable {

    public TextField nom1;
    @FXML
    private Button BouttonInscription;

    @FXML
    private TextField nom;

    @FXML
    private PasswordField password;

    @FXML
    private TextField prenom;

    @FXML
    private TextField username;

    @FXML
    private Label LoginMessageFailed;

    @FXML
    private AnchorPane main;
    private Parent file;

    public void  insertionDonnee() {
        String Nom = nom.getText();
        String Prenom = prenom.getText();
        String Username = username.getText();
        String Password = password.getText();

        ConnexionDB c = new ConnexionDB();
        Connection connection = c.getConnection();

        String R = "INSERT INTO Utilisateur (nom, prenom,username, password) VALUES('";
        String r = Nom + "','" + Prenom + "','" + Username + "','" + Password + "')";
        String requete = R + r;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(requete);


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void changerFenetre() {
        try{
            Parent file=FXMLLoader.load(getClass().getResource("PageDeConnexion.fxml"));
            Scene scene=new Scene(file);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

        }catch (Exception ev){
            ev.printStackTrace();
        }

    }
    public void Enregistrer(ActionEvent e){
        if(!username.getText().isBlank() && !password.getText().isBlank() && !nom.getText().isBlank() && !prenom.getText().isBlank()){
            insertionDonnee();
            //redirection vers la page principale
            Stage stage=(Stage) BouttonInscription.getScene().getWindow();
            stage.close();
            changerFenetre();



        }else{
            LoginMessageFailed.setText("Veuillez entrer vos identifiants !");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


