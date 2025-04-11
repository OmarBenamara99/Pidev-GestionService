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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.khadamni.api.Calendrier;
import tn.khadamni.entity.Freelancer;
import tn.khadamni.service.FreelancerService;
import tn.khadamni.service.UtilisateurService;
import tn.khadamni.test.MainGui;

/**
 * FXML Controller class
 *
 * @author lordg
 */
public class ProfilefreelancerController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField num;
    @FXML
    private TextField adresse;
    @FXML
    private TextField description;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button ajout;
    @FXML
    private Button modif;
    @FXML
    private Button sup;
    @FXML
    private Button session;
    @FXML
    private Button home;
  
     private Freelancer f;
     private int id; 
    @FXML
    private ImageView pdp;

    /*public ProfilefreelancerController(Freelancer f) {
        this.f = f;
    }*/
   
    /**
     * Initializes the controller class.
     */
    public void initializeprem(Freelancer f1){
       /* System.out.println(f1);
         this.id=f1.getId();
         this.f = f1 ;
         String imagePath = "";
        if (f1 != null) {
            imagePath = f1.getPhoto();
        } else {
            imagePath = "C:\\Users\\lordg\\Desktop\\anonyme.jpg";
        }
        
        Image image = new Image(new File(imagePath).toURI().toString());
        pdp.setImage(image);
         pdp.setFitHeight(250);
        pdp.setFitWidth(200);
        nom.setText(f1.getNom());
        prenom.setText(f1.getPrenom());
        adresse.setText(f1.getAdresse());
        num.setText(Integer.toHexString(f1.getNumtel()));
        description.setText(f1.getDescription());
        mdp.setText(f1.getMdp());*/
        
      
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    UtilisateurService us = new UtilisateurService();
        Freelancer f1 = us.convert(SessionController.currentuser.getId());
        System.out.println(f1);
         this.id=f1.getId();
         this.f = f1 ;
         String imagePath = "";
        if (f1 != null) {
            imagePath = f1.getPhoto();
        } else {
            imagePath = "C:\\Users\\lordg\\Desktop\\anonyme.jpg";
        }
        
        Image image = new Image(new File(imagePath).toURI().toString());
        pdp.setImage(image);
         pdp.setFitHeight(250);
        pdp.setFitWidth(200);
        nom.setText(f1.getNom());
        prenom.setText(f1.getPrenom());
        adresse.setText(f1.getAdresse());
        num.setText(Integer.toString(f1.getNumtel()));
        description.setText(f1.getDescription());
        mdp.setText(f1.getMdp());
        
    } 
    

    @FXML
    private void ajouter(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
         if (nom.getText().length() > 20) {
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le nom ne doit pas dépasser 20 caractères.");
                alert.show();
                return;
            }
            if (prenom.getText().length() > 20) {
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le nom ne doit pas dépasser 20 caractères.");
                alert.show();
                return;
            }
            // Vérifier que le numéro de téléphone est composé de 8 chiffres
            if (!num.getText().matches("\\d{8}")) {
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le numéro de téléphone doit être composé de 8 chiffres.");
                alert.show();
                return;
            }
            
            // Vérifier que le mot de passe comporte au moins 8 caractères
            if (mdp.getText().length() < 8) {
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le mot de passe doit comporter au moins 8 caractères.");
                alert.show();
                return;
            }
            if(!adresse.getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")){
                 alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("adresse mail invalide.");
                alert.show();
                return;
            }
            FreelancerService fs = new FreelancerService();
            Freelancer f1 =new   Freelancer(f.getId(),Integer.parseInt(num.getText()) , nom.getText(),prenom.getText(),  adresse.getText(), "freelancer", f.getPhoto(), description.getText(), f.getRate(), f.getProfession(),f.isVerified() , mdp.getText());
            fs.modifier(f1);
    }

    @FXML
    private void supprimer(ActionEvent event) {
         FreelancerService fs = new FreelancerService();
         fs.supprimer(id);
    }

    @FXML
    private void back(ActionEvent event) {
        try {

           FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/homeFreelancer.fxml"));
            Parent root = loader.load();
            
            //System.out.println(Integer.toString(id));
            FreelancerService fs = new FreelancerService();
            System.out.println("#####################################");
            System.out.println(fs.findById(id));
            Freelancer f2 = null;
            f2 = fs.findById(id);
            
            if (f2!=null){
                FreelancerhomeController uc = new FreelancerhomeController();
                System.out.println("#####################################");
                System.out.println(f2);
           // uc.initializeprem(f2);
            uc.setid(id);
            }else{
                System.out.println("failed");
            }
            
        Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            

            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE,
                    null, ex);

        }
    }

    @FXML
    private void logout(ActionEvent event) {
         FreelancerService fs = new FreelancerService();
         fs.logout(SessionController.currentuser.getId());
           try {

           FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/session.fxml"));
            Parent root = loader.load();
            UtilisateurhomeController uc = new UtilisateurhomeController();
            
            uc.setid(id);
        Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) { 
            
            

            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE,
                    null, ex);

        }
    }

    @FXML
    private void ajoutEngagment(ActionEvent event) {
          try {
                    Calendrier.main(new String[0]);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
    }
    
}
