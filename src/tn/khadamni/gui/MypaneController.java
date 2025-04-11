/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author morta
 */
public class MypaneController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private TextField descriptionLabel;
    @FXML
    private ImageView img;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setNom(String nom) {
        nameLabel.setText(nom);
    }


public void setDescription(String description) {
        descriptionLabel.setText(description);
    }
   public void setImage(Image image) {
        img.setImage(image);
    } 
  
}
