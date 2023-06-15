package com.example.projetdefindesemetrecsc301;

import Models.ConnexionDB;
import Models.EmploiDuTemps;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreationPlanning implements Initializable {

    @FXML
    private Button Benregistrer;

    @FXML
    private ComboBox<String> Enseignant;
    @FXML
    private Label LoginMessageFailed;
    @FXML
    private TextField HeureDebut;

    @FXML
    private TextField HeureFin;

    @FXML
    private ComboBox<String> Matiere;



    ConnexionDB connexionDB=new ConnexionDB();
    Connection c=connexionDB.getConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //requete
        String requetematiere="SELECT intitule FROM Matiere";
        String requeteprof="SELECT nom FROM Enseignant";


        try{
            Statement statement = c.createStatement();
            Statement statementA = c.createStatement();

            ResultSet resultatRequete=statement.executeQuery(requetematiere);
            ResultSet resultatRequete2=statementA.executeQuery(requeteprof);
            ObservableList<String> donnee = FXCollections.observableArrayList();
            ObservableList<String> donneeProf = FXCollections.observableArrayList();

            while ((resultatRequete.next())){
                donnee.add(resultatRequete.getString(1));
            }
            Matiere.setItems(donnee);

            while ((resultatRequete2.next())){
                donneeProf.add(resultatRequete2.getString(1));
            }
            Enseignant.setItems(donneeProf);


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void enregistrer(ActionEvent event){

        String matiere= Matiere.getValue();
        String hd=HeureDebut.getText();
        String hF=HeureFin.getText();
        String heure = hd+" à "+hF;
        String enseignant= Enseignant.getValue();

        //requte d'insertion

        try{
            String r1="INSERT INTO Emploi_du_temps(enseignant, cours,heureDebut,heureFin,heure) VALUES ('";
            String r2=enseignant +"','"+ matiere+"','"+hd+"','"+hF+"','"+heure+"')";
            String r=r1+r2;
            boolean verifier=exit(enseignant,hd,hF);
            if(!verifier){
                Statement statment = c.createStatement();
                statment.executeUpdate(r);
                LoginMessageFailed.setText("Emploi du temps créé avec succès");
            }else {
                LoginMessageFailed.setText("Il y'a déja un cours programmé pour ce enseignant à cette heure !!");
            }


        } catch (Exception e) {
                    throw new RuntimeException(e);
        }
    }

    public boolean exit(String enseignant, String heure_debut, String heure_fin){
        // Vérifier si l'enseignant a déjà un cours à la même heure
        try{
            Statement statement = c.createStatement();
            String requete="SELECT * FROM Emploi_du_temps WHERE Enseignant='"+enseignant +"'AND heureDebut ='"+heure_debut +"'AND heureFin ='"+heure_fin +"'";

            ResultSet resultatRequete=statement.executeQuery(requete);
            if (resultatRequete.next()){
                return true;
            }
        } catch (SQLException ex ) {
            Logger.getLogger(EmploiDuTemps.class.getName()).log(Level.SEVERE, null, ex);
        }


        return false;
    }


}

