package com.example.projetdefindesemetrecsc301;

import Models.ConnexionDB;
import Models.Enseignant;
import Models.Matiere;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class CreationEnseignant implements Initializable {

    @FXML
    private Button Bajouter;

    @FXML
    private TableColumn<Enseignant, String> NomColumn;

    @FXML
    private TableColumn<Enseignant, String> PrenomColumn;

    @FXML
    private TextField ProfRecherce;

    @FXML
    private TableColumn<Enseignant, String> emailColumn;

    @FXML
    private TableColumn<Enseignant, String> matriculeColumn;

    @FXML
    private TableView<Enseignant> tableView;

    @FXML
    private TableColumn<Enseignant, String> telColumn;



    ObservableList<Enseignant> enseignantObservableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnexionDB connexionDB=new ConnexionDB();
        Connection c=connexionDB.getConnection();
        //requete
        String requete="SELECT nom,prenom,tel,matricule,email FROM Enseignant";
        try{
            Statement statement = c.createStatement();
            ResultSet resultatRequete=statement.executeQuery(requete);
            while (resultatRequete.next()){
                String RequetenomColumn = resultatRequete.getString("nom");
                String RequeteprenomColumn = resultatRequete.getString("prenom");
                String RequetetelColumn = resultatRequete.getString("tel");
                String RequetematriculeColumn = resultatRequete.getString("matricule");
                String RequeteemailColumn = resultatRequete.getString("email");
                //ajout d'objet enseignant à la liste
                enseignantObservableList.add(new Enseignant(RequetenomColumn,RequeteprenomColumn,RequetetelColumn,RequetematriculeColumn,RequeteemailColumn));
            }
            //ajout des elements aux différentes colones
            NomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            PrenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            telColumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
            matriculeColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            tableView.setItems(enseignantObservableList);


            //---------------------implementation de la recherche
            //creation de la liste filtée pour les elements rechechés
            FilteredList<Enseignant> filtre= new FilteredList<>(enseignantObservableList, b -> true);

            ProfRecherce.textProperty().addListener((observable, oldValue, newValue)-> {
                filtre.setPredicate(enseignant -> {
                    if(newValue.isEmpty() || newValue.isBlank()|| newValue == null){
                        return  true;
                    }
                    //convertion de la recherche en minuscule
                    String motCherche=newValue.toLowerCase();
                    if(enseignant.getNom().toLowerCase().indexOf(motCherche) > -1){
                        return  true;

                    }else if(enseignant.getPrenom().toLowerCase().indexOf(motCherche) > -1){
                        return true;
                    }else if(enseignant.getTel().indexOf(motCherche) > -1){
                        return true;
                    }

                    else {
                        return false;

                    }
                });
            }  );
            /*---------------trier les donnee du tableau de sorte qu'il ne reste que le mot recherché-------------*/

            SortedList<Enseignant> donneeTriee= new SortedList<>(filtre);
            donneeTriee.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(donneeTriee);

        } catch (SQLException e) {
            Logger.getLogger(CreationMatiere.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

    }
    public void changerFenetre(ActionEvent e) {
        try{
            Parent file= FXMLLoader.load(getClass().getResource("PageEnseignant.fxml"));
            Scene scene=new Scene(file);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

        }catch (Exception ev){
            ev.printStackTrace();
        }
}
}
