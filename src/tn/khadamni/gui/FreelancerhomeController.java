/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;

import tn.khadamni.entity.Freelancer;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import jdk.nashorn.api.scripting.JSObject;
import tn.khadamni.entity.Utilisateur;
import tn.khadamni.service.FreelancerService;
import tn.khadamni.service.UtilisateurService;
import tn.khadamni.test.MainGui;

/**
 * FXML Controller class
 *
 * @author lordg
 */
public class FreelancerhomeController implements Initializable {

    @FXML
    private ImageView pdp;
    private int id;
    @FXML
    private Button logout;
    @FXML
    private Text nom;
    @FXML
    private Button profil;
    @FXML
    private Button map;

    /**
     * Initializes the controller class.
     */
     public void setid(int id) {
       // this.id = id;
    }
    public void initializeprem(Freelancer f) {
       /* System.out.println("hello");
        System.out.println(f);
        this.id=f.getId();
        String imagePath = "";
        if (f != null) {
            imagePath = f.getPhoto();
            System.out.println(imagePath);
        } else {
            imagePath = "C:\\Users\\lordg\\Desktop\\anonyme.jpg";
        }

        Image image = new Image(new File(imagePath).toURI().toString());
       pdp.setImage(image);
       /* pdp.setFitHeight(250);
        pdp.setFitWidth(200);*/
        //nom.setText(f.getNom());
        //nom.setFont(Font.font("Verdana", FontWeight.BOLD, 16));*/
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UtilisateurService us = new UtilisateurService();
        Freelancer f = us.convert(SessionController.currentuser.getId());
System.out.println("hello");
        System.out.println(f);
        this.id=f.getId();
        String imagePath = "";
        if (f != null) {
            imagePath = f.getPhoto();
            System.out.println(imagePath);
        } else {
            imagePath = "C:\\Users\\lordg\\Desktop\\anonyme.jpg";
        }

        Image image = new Image(new File(imagePath).toURI().toString());
       pdp.setImage(image);
       /* pdp.setFitHeight(250);
        pdp.setFitWidth(200);*/
        //nom.setText(f.getNom());
        //nom.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
    }

   

    @FXML
    private void logout(ActionEvent event) {
        FreelancerService fs = new FreelancerService();
        fs.logout(SessionController.currentuser.getId());
        Freelancer f1 = fs.findById(id);
        //rja3t lil login   
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("../gui/session.fxml"
                    ));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE,
                    null, ex);

        }
    }

    @FXML
    private void profil(ActionEvent event) {
        System.out.println("profil clicked");
        FreelancerService fs = new FreelancerService();
        //fs.logout(id);
        Freelancer f1 = fs.findById(id);
        //System.out.println(f1);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/profilefreelancer.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            if (f1 != null) {
                System.out.println(Integer.toString(id));
                ProfilefreelancerController pc = loader.getController();
                System.out.println(f1); 
                pc.initializeprem(f1);

                //pc.setid(id);
            } else {
                System.out.println("non trouvé");
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("warning ");
            alert.setHeaderText(null);
            alert.setContentText("Personne invalide ");
            alert.show();
        }
    }

    @FXML
    private void map(ActionEvent event) {
           WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();

    // Enable JavaScript in the WebView
    webEngine.setJavaScriptEnabled(true);

    // Load the Google Maps JavaScript API with a callback function
   webEngine.loadContent("<html><head><script src=\"https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&callback=initialize\" async defer></script></head><body><div id=\"map\" style=\"height: 100%; width: 100%;\"></div></body></html>");

    // Define the initialize function that will be called when the API loads
    webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue == Worker.State.SUCCEEDED) {
            JSObject window = (JSObject) webEngine.executeScript("window");
            window.setMember("app", this);
 
            webEngine.executeScript("function initialize() {\n" +
                    "  if (navigator.geolocation) {\n" +
                    "    navigator.geolocation.getCurrentPosition(function(position) {\n" +
                    "      var map = new google.maps.Map(document.getElementById('map'), {\n" +
                    "        center: {lat: position.coords.latitude, lng: position.coords.longitude},\n" +
                    "        zoom: 8\n" +
                    "      });\n" +
                    "    });\n" +
                    "  } else {\n" +
                    "    alert('Geolocation is not supported by this browser.');\n" +
                    "  }\n" +
                    "}");
        }
    });

    // Create a Scene containing the WebView and show it
    Scene scene = new Scene(webView);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
    }
}
