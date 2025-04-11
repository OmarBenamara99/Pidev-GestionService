/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;

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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.khadamni.entity.Mysession;
import tn.khadamni.entity.Utilisateur;
import tn.khadamni.service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author morta
 */
public class HomeController implements Initializable {

    @FXML
    private Pane affichage;
    @FXML
    private Pane pane_top;
    @FXML
    private Label idNuser;
    @FXML
    private Button profil;
    @FXML
    private Button offf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Utilisateur u=SessionController.currentuser;
        idNuser.setText(u.getNom());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheVitrine.fxml"));

            Parent root;
            try {
                root = loader.load();
                AfficheVitrineController controller = loader.getController();
                affichage.getChildren().setAll(root);
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    

    @FXML
    private void afficheProfil(ActionEvent event) {
         try{
           System.out.println("hello");
        
        
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/profil.fxml"));
            Parent root = loader.load();
        Scene scene = new Scene(root);
       ProfilController pc = loader.getController();
       
       
        UtilisateurService us = new UtilisateurService();
         Utilisateur u1 = SessionController.currentuser;
          // System.out.println(a.getText());
         
       if(u1!=null){
           
           System.out.println(u1);
           System.out.println("aalisson becker");
            pc.setuser(u1);
            pc.setid(SessionController.currentuser.getId());
            //System.out.println(u);
            pc.initializeprem(SessionController.currentuser);
       }else {
           System.out.println("non trouvé");
       }
      
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
       }catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("warning ");
            alert.setHeaderText(null);
            alert.setContentText("Personne invalide ");
            alert.show();
        }
       
    
    }

    @FXML
    private void offff(ActionEvent event) throws IOException {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherOffre.fxml"));
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AfficherOffre.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        //Parent root = loader.load();
        AfficherOffreController controller = loader.getController();
        //Scene scene = new Scene(root, 900, 600);
        //Stage stage = new Stage();
        //stage.setTitle("Afficher Offre");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        //Stage stage = (Stage) offf.getScene().getWindow();
        //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //stage.setScene(scene);
        //stage.show();
    }
    
}
