/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;

import tn.khadamni.api.Calendrier;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tn.khadamni.entity.Engagment;
import tn.khadamni.entity.Freelancer;
import tn.khadamni.entity.Services;
import static tn.khadamni.gui.AfficheVitrineController.currentservice;
import tn.khadamni.service.EngagmentService;
import tn.khadamni.service.ServicesService;

/**
 * calndrier imports
 *
 *
 */


public class PaneFreelancerController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label idNomPrenom;
    @FXML
    private Label idtel;
    @FXML
    private Label idprofdesc;
    @FXML
    private Circle circle;
    @FXML
    private Label idadr;
    @FXML
    private Button ideng;
    @FXML
    private Button calendriereng;
    @FXML
    private Button profil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setNomPrenom(String nom, String Prenom) {
        idNomPrenom.setText(nom + " " + Prenom);
    }

    public void setTel(String Tel) {
        idtel.setText(Tel);
    }

    public void setImage(Image image) {
        img.setImage(image);
    }

    public void setAdress(String adr) {
        idadr.setText(adr);
    }

    public void setProfDesc(String Profession, String Descreption) {
        idprofdesc.setText(Profession + "  " + Descreption);
    }

    public void setVerified(boolean verif) {
        if (verif == true) {
            System.out.println(verif);
            circle.setFill(Color.web("#2980B9"));

        } else {

            circle.setFill(Color.web("#66605F"));
        }
    }


    

    @FXML
    private void afficherEngagement(ActionEvent event) {
     
        
        EngagmentService engagement = new EngagmentService();
       List <Engagment> enga = engagement.findById1(AfficheListFreelancerController.chosenfreelancer.getId());
        
       for(Engagment i:enga)
       {
       System.out.println(i.toString());
     FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheEngagment.fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                        AfficheEngagmentController controller1 = loader.getController();
                        controller1.setEngagmentIDF(AfficheListFreelancerController.chosenfreelancer.getId());
                          
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) circle.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AfficheVitrineController.class.getName()).log(Level.SEVERE, null, ex);
                    }
       
       }
    }

    @FXML
    private void afficheProfil(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("UserCard.fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                        UserCardController controller = loader.getController();
                       // controller.setprofil(1);

                        Scene scene = new Scene(root);
                        Stage stage = (Stage) circle.getScene().getWindow();
                        stage.setTitle("affiche profil");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AfficheVitrineController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }

   

}
