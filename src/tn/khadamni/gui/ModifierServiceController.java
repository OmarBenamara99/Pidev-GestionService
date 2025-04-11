/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.khadamni.entity.Services;
import tn.khadamni.entity.Sous_services;
import tn.khadamni.service.ServicesService;

/**
 * FXML Controller class
 *
 * @author morta
 */
public class ModifierServiceController implements Initializable {
    private File imgFile;
String nom, description, image;
    int nbservice,id;
    @FXML
    private TextField txtdesc;
    @FXML
    private TextField txtnb;
    @FXML
    private Button btnM;
    @FXML
    private Button btnAn;
    private Services serviceSelectionne;
    @FXML
    private TextField txtNom;
    @FXML
    private ImageView img_m;
    private Pane global;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
public void initService(Services service) {
    this.serviceSelectionne = service;
    id=service.getService_id();
    txtNom.setText(service.getService_nom());
    txtdesc.setText(service.getService_description());
   img_m.setImage(new Image(service.getService_image()));
    txtnb.setText(Integer.toString(service.getNb_sous_services()));
}

    @FXML
    private void modfierService(ActionEvent event) {
    
           // Vérifier que les champs sont remplis correctement
    if (txtNom.getText().isEmpty() || txtdesc.getText().isEmpty() || imgFile == null || txtnb.getText().isEmpty()) {
        // Afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }
    
    // Vérifier que le champ nombre de services est bien un entier
  
          nom = txtNom.getText();
        description = txtdesc.getText();
        nbservice = Integer.parseInt(txtnb.getText());
        String imgURL = "";
        if (imgFile != null) {
            imgURL = imgFile.toURI().toString();
        }
        Services s = new Services(id,nom, description, imgURL, nbservice);
        ServicesService ss = new ServicesService();
        ss.modifierS(s);
        txtNom.setText("");
        txtdesc.setText("");
        txtnb.setText("");
        
    
    }

    @FXML
    private void insertimage(MouseEvent event) {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers Image", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        imgFile = fileChooser.showOpenDialog(null);
        if (imgFile != null) {
            Image image = new Image(imgFile.toURI().toString());
            img_m.setImage(image);
        }

    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheService.fxml"));
        Parent root = loader.load();
        AfficheServiceController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = (Stage) global.getScene().getWindow();
        stage.setTitle("Modifier Service");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void verifNom(KeyEvent event) {
        if (!txtNom.getText().matches("[a-zA-Z]+")) {
    {
         Alert alert = new Alert(Alert.AlertType.ERROR, "seulement des caractere svp!");
        alert.showAndWait();
        txtNom.setText("");
        return;
    }
}
    }

    @FXML
    private void verifDesc(KeyEvent event) {
    if (!txtdesc.getText().matches("[a-zA-Z]+")) {
    {
         Alert alert = new Alert(Alert.AlertType.ERROR, "seulement des caractere svp!");
        alert.showAndWait();
        txtdesc.setText("");
        return;
    }
}
    }

    @FXML
    private void verifNb(KeyEvent event) {
    int nombreServices;
    try {
        nombreServices = Integer.parseInt(txtnb.getText());
    } catch (NumberFormatException e) {
        // Afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR, "Le nombre de services doit être un entier.");
        alert.showAndWait();
    txtnb.setText("");
        return;
    }
    }

}
