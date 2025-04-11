/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;

import java.io.File;
import java.io.IOException;
import static java.lang.System.in;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.khadamni.entity.Services;
import tn.khadamni.service.ServicesService;

/**
 * FXML Controller class
 *
 * @author morta
 */
public class AfficheServiceController implements Initializable {

    String nom, description, image;
    int nbservice;
    
    int idtable=0;
    @FXML
    private Pane mybutton;
    @FXML
    private Pane afficherPane;

    public AfficheServiceController() {
    }
    private File imgFile;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtnbs;
    @FXML
    private TextField txtdesc;
    @FXML

    private TableView<Services> tbview;
    @FXML
    private TableColumn<Services, String> colnom;
    @FXML
    private TableColumn<Services, String> coldesc;
    @FXML
    private TableColumn<Services, String> colimg;
    @FXML
    private TableColumn<Services, String> colnb;
    private TextArea test;

    @FXML
    private TextField id;
    @FXML
    private ImageView imgView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refrechTable();

    }

    @FXML
    private void AjouterService(ActionEvent event) {
         // Vérifier que les champs sont remplis correctement
    if (txtnom.getText().isEmpty() || txtdesc.getText().isEmpty() || imgFile == null || txtnbs.getText().isEmpty()) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR, "Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }
      if (!txtnom.getText().matches("[a-zA-Z]+")) {
             Alert alert = new Alert(AlertType.ERROR, "seulement des caracteres Svp!");
        alert.showAndWait();
        return;
            }
    
    // Vérifier que le champ nombre de services est bien un entier
    int nombreServices;
    try {
        nombreServices = Integer.parseInt(txtnbs.getText());
    } catch (NumberFormatException e) {
        // Afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR, "Le nombre de services doit être un entier.");
        alert.showAndWait();
        return;
    }
        
        
        nom = txtnom.getText();
        description = txtdesc.getText();
        nbservice = Integer.parseInt(txtnbs.getText());
        String imgURL = "";
        if (imgFile != null) {
            imgURL = imgFile.toURI().toString();
        }
        Services s = new Services(nom, description, imgURL, nbservice);
        ServicesService ss = new ServicesService();
        ss.ajouter(s);
        txtnom.setText("");
        txtdesc.setText("");
        txtnbs.setText("");


        refrechTable();
    }

    public void initTable() {
       

        colnom.setCellValueFactory(new PropertyValueFactory<>("service_nom"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("service_description"));
        colimg.setCellValueFactory(new PropertyValueFactory<>("service_image"));
        colnb.setCellValueFactory(new PropertyValueFactory<>("nb_sous_services"));

    }

    public void refrechTable() {
        initTable();
        
        ServicesService ss = new ServicesService();
        List<Services> serv = ss.afficher();
        ObservableList<Services> listServices = FXCollections.observableArrayList(serv);
        tbview.setItems(listServices);
    }

    @FXML
    private void displaySelected(MouseEvent event) {

        Services s = tbview.getSelectionModel().getSelectedItem();
        if (s == null) {
            id.setText("vide");
        } else {
            idtable = s.getService_id();
            id.setText(String.valueOf(idtable));
        }
    }

    @FXML
    private void ModifierService(ActionEvent event) throws IOException {
   Services serviceSelectionne = tbview.getSelectionModel().getSelectedItem();
    if (serviceSelectionne != null) {  
         mybutton.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierService.fxml"));

            Parent root;
            try {
                root = loader.load();
                 ModifierServiceController controller = loader.getController();
        controller.initService(serviceSelectionne);
                mybutton.getChildren().setAll(root);
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        
        
    }}

  
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
            imgView.setImage(image);
        }

    }



    @FXML
    private void supprimerS(ActionEvent event) {
  int i = Integer.parseInt(id.getText());
      
  System.out.println("mesaaage"+i);
        Services s = new Services(i);
        ServicesService ss = new ServicesService();
        ss.supprimer(s);
        refrechTable();
    }

    @FXML
    private void ajouterSousService(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("gererSousService.fxml"));
        Parent root = loader.load();
        GererSousServiceController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = (Stage) mybutton.getScene().getWindow();
        stage.setTitle("ajouter Sous Service");
        stage.setScene(scene);
        stage.show();
   
    }

    @FXML
    private void verifNom(KeyEvent event) {
if (!txtnom.getText().matches("[a-zA-Z]+")) {
    {
         Alert alert = new Alert(AlertType.ERROR, "seulement des caractere svp!");
        alert.showAndWait();
        txtnom.setText("");
        return;
    }
}
    }

    @FXML
    private void verifDesc(KeyEvent event) {
   if (!txtdesc.getText().matches("[a-zA-Z]+")) {
    {
         Alert alert = new Alert(AlertType.ERROR, "seulement des caractere svp!");
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
        nombreServices = Integer.parseInt(txtnbs.getText());
    } catch (NumberFormatException e) {
        // Afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR, "Le nombre de services doit être un entier.");
        alert.showAndWait();
    txtnbs.setText("");
        return;
    }
    }
}
