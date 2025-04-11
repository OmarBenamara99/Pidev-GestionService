/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import tn.khadamni.entity.Freelancer;
import tn.khadamni.service.FreelancerService;

/**
 * FXML Controller class
 *
 * @author morta
 */
public class HomeFreelancerController implements Initializable {

    @FXML
    private Pane affichage;
    @FXML
    private Pane pane_top;
    @FXML
    private Label idNuser;
    @FXML
    private Button offre;
    @FXML
    private Button app;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficheOffre(ActionEvent event) throws IOException {
        
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/PostuleF.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        PostuleFController controller = loader.getController();
        //Stage stage = new Stage();
        //stage.setTitle("Afficher Offre");
        
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void afficherProfil(ActionEvent event) {
     System.out.println("profil clicked");
        FreelancerService fs = new FreelancerService();
        //fs.logout(id);
        Freelancer f1 = fs.findById(SessionController.currentuser.getId());
        //System.out.println(f1);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/profilefreelancer.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            if (f1 != null) {
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
    private void appp(ActionEvent event) {
    }
    
}
