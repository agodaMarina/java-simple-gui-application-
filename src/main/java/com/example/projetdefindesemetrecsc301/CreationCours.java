package com.example.projetdefindesemetrecsc301;

import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CreationCours implements Initializable {
    @FXML
    private Label message;
    @FXML
    private ComboBox<String> ListeEnseignant;

    @FXML
    private ComboBox<String> ListeMatiere;

    @FXML
    private ComboBox<String> listeAnnee;

    @FXML
    private ComboBox<String> listeClasse;

    ConnexionDB connexionDB=new ConnexionDB();
    Connection c=connexionDB.getConnection();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //requete
        String requetematiere="SELECT intitule FROM Matiere";
        String requeteclasse="SELECT intitule FROM Classe";
        String requeteannee="SELECT Code FROM Annee";
        String requeteprof="SELECT nom FROM Enseignant";


        try{
            Statement statement = c.createStatement();
            Statement statementA = c.createStatement();
            Statement statementC = c.createStatement();
            Statement statementP= c.createStatement();
            ResultSet resultatRequete=statement.executeQuery(requetematiere);
            ResultSet resultatRequete1=statementC.executeQuery(requeteclasse);
            ResultSet resultatRequete2=statementA.executeQuery(requeteannee);
            ResultSet resultatRequete3=statementP.executeQuery(requeteprof);
            ObservableList<String> donnee = FXCollections.observableArrayList();
            ObservableList<String> donneeClasse = FXCollections.observableArrayList();
            ObservableList<String> donneeAnnee = FXCollections.observableArrayList();
            ObservableList<String> donneeProf = FXCollections.observableArrayList();

            while ((resultatRequete.next())){
                donnee.add(resultatRequete.getString(1));
            }
            ListeMatiere.setItems(donnee);

            while ((resultatRequete1.next())){
                donneeClasse.add(resultatRequete1.getString(1));
            }
            listeClasse.setItems(donneeClasse);


            while ((resultatRequete2.next())){
                donneeAnnee.add(resultatRequete2.getString(1));
            }
            listeAnnee.setItems(donneeAnnee);

            while ((resultatRequete3.next())){
                donneeProf.add(resultatRequete3.getString(1));
            }
            ListeEnseignant.setItems(donneeProf);

            //recuperation des valeurs des comboBox

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void enregistrer(ActionEvent event){

        String matiere= ListeMatiere.getValue();
        String classe= listeClasse.getValue();
        String annee= listeAnnee.getValue();
        String enseignant= ListeEnseignant.getValue();
        //requte d'insertion
        String r1="INSERT INTO Cours(Matiere, Enseignant, Classe, Annee_scolaire) VALUES (' ";
        String r2= matiere +"','"+ enseignant+"','"+classe+"','"+annee+"')";
        String r=r1+r2;

        try{
            Statement statement = c.createStatement();
            statement.executeUpdate(r);
            message.setText("Nouveau Cours ajouté");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
