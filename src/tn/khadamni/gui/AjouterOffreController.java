package tn.khadamni.gui;

import tn.khadamni.entity.Offre;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.khadamni.service.OffreService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.khadamni.entity.Services;
import tn.khadamni.entity.Sous_services;
import tn.khadamni.entity.Utilisateur;
import tn.khadamni.service.ServicesService;
import tn.khadamni.service.Sous_servicesService;


public class AjouterOffreController implements Initializable {
    
    private File imgFile;
    int cat,Scat;
    String selectedServiceName, selectedSServiceName;
    @FXML
    private TextField txtadr;
    @FXML
    private TextField txtdesc;
    @FXML
    private ImageView imgid;
    @FXML
    private Button btn;
    @FXML
    private Button ann;
    @FXML
    private ComboBox<String> listDeroulant;
    @FXML
    private ComboBox<String> listDeroulantt;
    @FXML
    private Button map;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                init();
                initt();
                Utilisateur u=SessionController.currentuser;
        
    }

@FXML
    private void addPerson(ActionEvent event) {
        if (imgFile == null || txtadr.getText().isEmpty()|| txtdesc.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;}
    /*    
    int catt,Scatt;
    try {
        catt = Integer.parseInt(txtcat.getText());
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Catégorie doit être un entier.");
        alert.showAndWait();
        return;
    }
    try {
        Scatt = Integer.parseInt(txtScat.getText());
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Sous_Catégorie doit être un entier.");
        alert.showAndWait();
        return;
    }
      */  
        
        try {
            
            //int cat = Integer.valueOf(txtcat.getText());
            //int Scat = Integer.valueOf(txtScat.getText());
            String adr = txtadr.getText();
            String desc = txtdesc.getText();
            String ser = listDeroulant.getSelectionModel().getSelectedItem();
            String sser = listDeroulantt.getSelectionModel().getSelectedItem();
            int idc =SessionController.currentuser.getId();
            
            
            String imgURL = "";
            if (imgFile != null) {
            imgURL = imgFile.toURI().toString();
            }
            
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            
            Offre oo = new Offre(cat, Scat, adr, date, desc, imgURL, ser, sser, idc);
            OffreService os = new OffreService();
            os.ajouterOffree(oo);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherOffre.fxml"));
            Parent root = loader.load();
            AfficherOffreController controller = loader.getController();
            Scene scene = new Scene(root, 900, 600);
            //Stage stage = new Stage();
            //stage.setTitle("Afficher Offre");
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
       
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
            imgid.setImage(image);
        }
    }

    @FXML
    private void anulll(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherOffre.fxml"));
        Parent root = loader.load();
        AfficherOffreController controller = loader.getController();
        Scene scene = new Scene(root, 900, 600);
        //Stage stage = new Stage();
        //stage.setTitle("Afficher Offre");
        Stage stage = (Stage) ann.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void listderoulant(ActionEvent event) {
        ServicesService service = new ServicesService();
        List<Services> serv = service.afficher();
        selectedServiceName = listDeroulant.getSelectionModel().getSelectedItem();
        cat = getIdFromSelectedService(selectedServiceName, serv);
    }
    
    private int getIdFromSelectedService(String selectedServiceName, List<Services> serv) {
        for (Services i : serv) {
            if (i.getService_nom().equals(selectedServiceName)) {
                return i.getService_id();
            }
        }
        return -1; // Si aucun service correspondant n'est trouvé, retourner -1 ou une valeur appropriée pour votre cas d'utilisation
    }

    @FXML
    private void listderoulantt(ActionEvent event) {
        Sous_servicesService service = new Sous_servicesService();
        List<Sous_services> serv = service.afficher();
        selectedSServiceName = listDeroulantt.getSelectionModel().getSelectedItem();
        Scat = getIdFromSelectedSService(selectedSServiceName, serv);
    }
    
    private int getIdFromSelectedSService(String selectedServiceName, List<Sous_services> serv) {
        for (Sous_services i : serv) {
            if (i.getSous_service_nom().equals(selectedServiceName)) {
                return i.getSous_service_id();
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
    
    public void initt() {
        Sous_servicesService service = new Sous_servicesService();
        List<Sous_services> serv = service.afficher();

        for (Sous_services i : serv) {
            listDeroulantt.getItems().add(i.getSous_service_nom());

        }
    }
    public void setLabelText(String text) {
        txtadr.setText(text);
    }

    @FXML
    private void mapp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));
        Parent root = loader.load();
        MapController controller = loader.getController();
        Scene scene = new Scene(root, 900, 600);
        //Stage stage = new Stage();
        //stage.setTitle("Afficher Offre");
        Stage stage = (Stage) map.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}

