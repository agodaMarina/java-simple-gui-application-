package com.example.projetdefindesemetrecsc301;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;



import java.io.IOException;

public class PagePrincipale {
    private Parent file;
    @FXML
    private AnchorPane conteneur;


    @FXML
    void aceuil(MouseEvent event) {
            try {
                file= FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                conteneur.getChildren().removeAll();
                conteneur.getChildren().setAll(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
        @FXML
    void anne(MouseEvent event) {
            try {
                file= FXMLLoader.load(getClass().getResource("CreationAnnee.fxml"));
                conteneur.getChildren().removeAll();
                conteneur.getChildren().setAll(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    @FXML
    void classe(MouseEvent event) {
        try {
            file= FXMLLoader.load(getClass().getResource("CreationClasse.fxml"));
            conteneur.getChildren().removeAll();
            conteneur.getChildren().setAll(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cours(MouseEvent event) {
        try {
            file= FXMLLoader.load(getClass().getResource("CreationCours.fxml"));
            conteneur.getChildren().removeAll();
            conteneur.getChildren().setAll(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void enseignant(MouseEvent event) {
        try {
            file= FXMLLoader.load(getClass().getResource("CreationEnseignant.fxml"));
            conteneur.getChildren().removeAll();
            conteneur.getChildren().setAll(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void matiere(MouseEvent event) {
        try {
            file= FXMLLoader.load(getClass().getResource("CreationMatiere.fxml"));
            conteneur.getChildren().removeAll();
            conteneur.getChildren().setAll(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void planning(MouseEvent event) {
        try {
            file= FXMLLoader.load(getClass().getResource("CreationPlanning.fxml"));
            conteneur.getChildren().removeAll();
            conteneur.getChildren().setAll(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
