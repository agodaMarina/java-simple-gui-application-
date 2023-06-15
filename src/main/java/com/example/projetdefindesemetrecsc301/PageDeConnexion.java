package com.example.projetdefindesemetrecsc301;
import Models.ConnexionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;


public class PageDeConnexion {
    @FXML
    private Button close;

    @FXML
    private Button connexion;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label LoginMessageFailed;


    public void changerFenetre() {
        try{
            Parent file= FXMLLoader.load(getClass().getResource("PagePrincipale.fxml"));
            Scene scene=new Scene(file);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

        }catch (Exception ev){
            ev.printStackTrace();
        }

    }
    public void ValidationDesIdentifiants(){
        ConnexionDB c=new ConnexionDB();
        Connection connexion=c.getConnection();

        String requete="SELECT count(1) FROM Utilisateur WHERE username= '"+ username.getText()+ "'AND password = '" +password.getText() +"'";
        try {
            Statement statement =connexion.createStatement();
            ResultSet resultatRequete=statement.executeQuery(requete);

            while (resultatRequete.next()){
                if (resultatRequete.getInt(1)==1){
                    LoginMessageFailed.setText("Connexion réussie");
                }else {
                    LoginMessageFailed.setText("Connexion échouée.Veuillez reéssayer");
                }
        }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void BoutonConnexionVide(ActionEvent e){


        if(!username.getText().isBlank() && !password.getText().isBlank()){
            ValidationDesIdentifiants();
            Stage stage=(Stage) connexion.getScene().getWindow();
            stage.close();
            changerFenetre();


        }else{
            LoginMessageFailed.setText("Veuillez entrer vos identifiants !");
        }
    }





}
