/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.khadamni.entity.Freelancer;
import tn.khadamni.entity.Services;
import tn.khadamni.entity.Sous_services;
import tn.khadamni.entity.Utilisateur;
import tn.khadamni.service.FreelancerService;
import tn.khadamni.service.ServicesService;
import tn.khadamni.service.Sous_servicesService;
import tn.khadamni.service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author morta
 */
public class AfficheListFreelancerController implements Initializable {
        public static Freelancer chosenfreelancer;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridPane;
    private int row = 0;
    private int col = 0;
    private boolean alternate = false;
    @FXML
    private AnchorPane global;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
          Sous_servicesService ss = new Sous_servicesService();
        List<Sous_services> s = ss.afficher();
        for(Sous_services m:s)
        {
        FreelancerService fr = new FreelancerService();
            System.out.println(AfficheVitrineController.currentservice.getService_nom());
        List<Freelancer> freel = fr.findByNom(AfficheVitrineController.currentservice.getService_nom());
        
        for (Freelancer i : freel) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PaneFreelancer.fxml"));
            try {
                Node node = fxmlLoader.load();
                PaneFreelancerController controller = fxmlLoader.getController();
                controller.setNomPrenom(i.getNom(), i.getPrenom());
                controller.setAdress(i.getAdresse());
                  String imageUrl = i.getPhoto(); // l'URL de l'image à charger
                Image image = new Image(new File(imageUrl).toURI().toString());
                controller.setImage(image);
                 
                controller.setProfDesc(i.getProfession(), i.getDescription());
                controller.setTel(i.getNumtel() + "");
                controller.setVerified(i.isVerified());
                
                node.setOnMouseClicked(event -> {
  chosenfreelancer=i;
                    
                });
                // Alternance de couleurs de fond
                if (alternate) {
                    node.setStyle("-fx-background-color: #FFCC09;  -fx-background-radius: 20px; ");
                } else {
                    node.setStyle("-fx-background-color: #515047;-fx-background-radius: 20px;");
                }
                alternate = !alternate;

                // Ajoutez le nœud au GridPane en spécifiant la position de chaque nœud
                gridPane.add(node, col, row);

                // Incrémente la colonne pour la prochaine instance de MyPane
                col++;

                // Si on atteint la troisième colonne, retourne à la première colonne et incrémente la ligne
                if (col > 0) {
                    col = 0;
                    row++;
                }
            } catch (IOException ex) {
                Logger.getLogger(MypaneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Créez une HBox et ajoutez-y le GridPane
        HBox hbox = new HBox(gridPane);
        hbox.setAlignment(Pos.CENTER);

        // Définir la HBox comme le contenu du ScrollPane
        scrollPane.setContent(hbox);
        
        }    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root = loader.load();
        HomeController controller = loader.getController();
        Scene scene = new Scene(root, 1000, 600);
        Stage stage = (Stage) global.getScene().getWindow();
        stage.setTitle("Retour");
        stage.setScene(scene);
        stage.show();
    }
    
}
