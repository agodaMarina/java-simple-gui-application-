package com.example.projetdefindesemetrecsc301;

import Models.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreationMatiere implements Initializable {

    @FXML
    private TableColumn<Matiere, String> codeColumn;
    @FXML
    private TableColumn<Matiere, String> intitutleColumn;
    @FXML
    private TableView<Matiere> table;

    @FXML
    private Button Benregistrer;

    @FXML
    private Button close;

    @FXML
    private TextField MatiereRechercher;

    @FXML
    private TextField code;

    @FXML
    private TextField intitule;


//parametrage du tableView
    ObservableList<Matiere> matiereObservableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        liste();
    }

    public void EnregistrementEnBase(){
        String Code= code.getText();
        String Intitulee=intitule.getText();
        ConnexionDB c=new ConnexionDB();
        Connection connexion=c.getConnection();

        String requete= "INSERT INTO Matiere  (code, intitule) VALUES('";
        String valeurInseree= Code + "','" + Intitulee + "')";
        String requeteTotale=requete+valeurInseree;

        try {
            Statement statement =connexion.createStatement();
            statement.executeUpdate(requeteTotale);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void liste(){
        ConnexionDB connexionDB=new ConnexionDB();
        Connection c=connexionDB.getConnection();
        String requete="SELECT code, intitule FROM Matiere";
        try{
            Statement statement = c.createStatement();
            ResultSet resultatRequete=statement.executeQuery(requete);
            while (resultatRequete.next()){
                String RequetecodeColumn = resultatRequete.getString("code");
                String RequeteIntituleColumn = resultatRequete.getString("intitule");
                matiereObservableList.add(new Matiere(RequetecodeColumn,RequeteIntituleColumn));
            }
            codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
            intitutleColumn.setCellValueFactory(new PropertyValueFactory<>("intitule"));
            table.setItems(matiereObservableList);

            //recherche
            FilteredList<Matiere> filtre= new FilteredList<>(matiereObservableList, b -> true);

            MatiereRechercher.textProperty().addListener((observable, oldValue, newValue)-> {
                filtre.setPredicate(matiere -> {
                    if(newValue.isEmpty() || newValue.isBlank()|| newValue == null){
                        return  true;
                    }
                    //convertion de la recherche en minuscule
                    String motCherche=newValue.toLowerCase();
                    if(matiere.getCode().toLowerCase().indexOf(motCherche) > -1){
                        return  true;

                    }else if(matiere.getIntitule().toLowerCase().indexOf(motCherche) > -1){
                        return true;
                    }else {
                        return false;
                    }
                });
            }  );
            /*---------------rier les donnee du tableau de sorte qu'il ne reste que le mot recherché-------------*/

            SortedList<Matiere> donneeTriee= new SortedList<>(filtre);
            donneeTriee.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(donneeTriee);

        } catch (SQLException e) {
            Logger.getLogger(CreationMatiere.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    public void AjoutMatiere(ActionEvent e){

            EnregistrementEnBase();


    }

}
