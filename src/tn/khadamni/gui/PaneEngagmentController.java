/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author morta
 */
public class PaneEngagmentController implements Initializable {

    @FXML
    private Label NomEvents;
    @FXML
    private TextField jour;
    @FXML
    private TextField date;
    @FXML
    private TextField heure;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setNomEvents(String nom) {
        NomEvents.setText(nom);
    }


public void setJour(String jours) {
        jour.setText(jours);
    }

public void setDate(String d) {
        date.setText(d);
    }

public void setHeure(String heurs) {
        heure.setText(heurs);
    }
}
