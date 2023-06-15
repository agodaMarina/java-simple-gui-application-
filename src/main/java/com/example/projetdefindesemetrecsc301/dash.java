package com.example.projetdefindesemetrecsc301;

import Models.ConnexionDB;
import Models.EmploiDuTemps;

import Models.Matiere;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dash implements Initializable {

    @FXML
    private TableColumn<EmploiDuTemps, String> enseignantColumn;

    @FXML
    private TableColumn<EmploiDuTemps, String> heureColumn;

    @FXML
    private TableColumn<EmploiDuTemps, String> matiereColumn;

    @FXML
    private TableView<EmploiDuTemps> table;

    ObservableList<EmploiDuTemps> emploiDuTempsObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ConnexionDB connexionDB=new ConnexionDB();
        Connection c=connexionDB.getConnection();
        //requete
        String requete="SELECT enseignant, cours,heure FROM Emploi_du_temps";
        try{
            Statement statement = c.createStatement();
            ResultSet resultatRequete=statement.executeQuery(requete);
            while (resultatRequete.next()){
                String RequeteProfColumn = resultatRequete.getString("enseignant");
                String RequeteMatiereColumn = resultatRequete.getString("cours");
                String RequeteHeureColumn = resultatRequete.getString("heure");
                emploiDuTempsObservableList.add(new EmploiDuTemps(RequeteProfColumn,RequeteMatiereColumn,RequeteHeureColumn));

            }
            enseignantColumn.setCellValueFactory(new PropertyValueFactory<>("enseignant"));
            matiereColumn.setCellValueFactory(new PropertyValueFactory<>("cour"));
            heureColumn.setCellValueFactory(new PropertyValueFactory<>("heure"));
            table.setItems(emploiDuTempsObservableList);
    } catch (SQLException e) {
            Logger.getLogger(CreationMatiere.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
}
}
