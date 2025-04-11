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
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import tn.khadamni.tools.ChartController;

/**
 * FXML Controller class
 *
 * @author morta
 */
public class DashBoardController implements Initializable {

    @FXML
    private MenuButton gererServiceMenuButton;
    @FXML
    private Pane affichage;
    @FXML
    private Pane pane_top;
    @FXML
    private Label nomaff;
    @FXML
    private MenuButton gererSServiceMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomaff.setText(" ");
        gererSousService();
        gereService();

    }

    public void gereService() {
        // Ajout d'un nouveau MenuItem
        gererServiceMenuButton.getItems().clear();

        MenuItem action3MenuItem = new MenuItem("Gestion Service");
        action3MenuItem.setOnAction(event -> {
            nomaff.setText("Gestion Service");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheService.fxml"));

            Parent root;
            try {
                root = loader.load();
               AfficheServiceController controller = loader.getController();
                affichage.getChildren().setAll(root);
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        gererServiceMenuButton.getItems().add(action3MenuItem);
    }

    public void gererSousService() {
        // Ajout d'un nouveau MenuItem
        gererSServiceMenu.getItems().clear();

        MenuItem action3MenuItem = new MenuItem("Ajouter");
        action3MenuItem.setOnAction(event -> {
            affichage.getChildren().clear();

            nomaff.setText("Ajouter Sous  Service");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gererSousService.fxml"));

            Parent root;
            try {
                root = loader.load();
                GererSousServiceController controller = loader.getController();
                affichage.getChildren().setAll(root);
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        gererSServiceMenu.getItems().add(action3MenuItem);

        MenuItem action2MenuItem = new MenuItem("Afficher");
        action2MenuItem.setOnAction(event -> {
            affichage.getChildren().clear();

            nomaff.setText("Afficher Sous  Service");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheSousService.fxml"));

            Parent root;
            try {
                root = loader.load();
                AfficheSousServiceController controller = loader.getController();
                affichage.getChildren().setAll(root);
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        gererSServiceMenu.getItems().add(action2MenuItem);
    }

    @FXML
    private void affichechart(ActionEvent event) {
     affichage.getChildren().clear();

            nomaff.setText("Affiche Chart");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../tools/chart.fxml"));

            Parent root;
            try {
                root = loader.load();
                ChartController controller = loader.getController();
                affichage.getChildren().setAll(root);
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void gestionUser(ActionEvent event) {
      affichage.getChildren().clear();

            nomaff.setText("Gestion User");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AdminHome.fxml"));

            Parent root;
            try {
                root = loader.load();
                AdminHomeController controller = loader.getController();
                affichage.getChildren().setAll(root);
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        
        
    }
    
}
