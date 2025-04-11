/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.Provider.Service;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.khadamni.entity.Services;
import tn.khadamni.entity.Sous_services;
import tn.khadamni.service.ServicesService;
import tn.khadamni.service.Sous_servicesService;

/**
 * FXML Controller class
 *
 * @author morta
 */
public class GererSousServiceController implements Initializable {

    private File imgFile;
    int Id;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDesc;
    @FXML
    private Button btnAjouter;
    @FXML
    private ComboBox<String> listDeroulant;
    @FXML
    private ImageView img;
    @FXML
    private Pane global;
    @FXML
    private AnchorPane retour;
    private int serviceId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();

    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {

// Vérifier que les champs sont remplis correctement
        if (txtNom.getText().isEmpty() || txtDesc.getText().isEmpty() || imgFile == null) {
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez remplir tous les champs.");
            alert.showAndWait();
            return;
        }

        // create an array of size 1 to store the ID value
        String nom = txtNom.getText();
        String description = txtDesc.getText();

        String imgURL = "";
        if (imgFile != null) {
            imgURL = imgFile.toURI().toString();
        }
ServicesService serviceService = new ServicesService();
Services service = serviceService.findById(serviceId);
        Sous_services s = new Sous_services(nom, description, imgURL, service,52);
        Sous_servicesService ss = new Sous_servicesService();
        System.out.println(s);
        ss.ajouter(s);
        txtNom.setText("");
        txtDesc.setText("");
       // gotoaffichesousservice();

    }

    @FXML
    private void choisirImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers Image", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        imgFile = fileChooser.showOpenDialog(null);
        if (imgFile != null) {
            Image image = new Image(imgFile.toURI().toString());
            img.setImage(image);
        }

    }

    @FXML
    private void listderoulant(ActionEvent event) {
        ServicesService service = new ServicesService();
        List<Services> serv = service.afficher();
        String selectedServiceName = listDeroulant.getSelectionModel().getSelectedItem();
        serviceId = getIdFromSelectedService(selectedServiceName, serv);
    }

    private int getIdFromSelectedService(String selectedServiceName, List<Services> serv) {
        for (Services i : serv) {
            if (i.getService_nom().equals(selectedServiceName)) {
                return i.getService_id();
            }
        }
        return -1; // Si aucun service correspondant n'est trouvé, retourner -1 ou une valeur appropriée pour votre cas d'utilisation
    }

    public void init() {
        ServicesService service = new ServicesService();
        List<Services> serv = service.afficher();

        for (Services i : serv) {
            listDeroulant.getItems().add(i.getService_nom());

        }
    }

    private void gotoaffichesousservice() throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheSousService.fxml"));
        Parent root = loader.load();
        AfficheSousServiceController controller = loader.getController();
        Scene scene = new Scene(root, 600, 450);
        //Stage stage = new Stage();
        Stage stage = (Stage) global.getScene().getWindow();
        //stage.setTitle("Modifier Service");
        stage.setScene(scene);
        stage.show();
    }

    private void retour(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheService.fxml"));
        Parent root = loader.load();
        AfficheServiceController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = (Stage) retour.getScene().getWindow();
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
    private void verifdes(KeyEvent event) {
       if (!txtDesc.getText().matches("[a-zA-Z]+")) {
    {
         Alert alert = new Alert(Alert.AlertType.ERROR, "seulement des caractere svp!");
        alert.showAndWait();
        txtDesc.setText("");
        return;
    }
}
    }

}
