/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.khadamni.entity.Engagment;
import tn.khadamni.entity.Sous_services;
import tn.khadamni.service.EngagmentService;
import tn.khadamni.service.Sous_servicesService;

public class AfficheEngagmentController implements Initializable {

    private int EngagmentIDF;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridPane;
    private int row = 0;
    private int col = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setEngagmentIDF(int i) {
        this.EngagmentIDF = i;
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        EngagmentService engagment = new EngagmentService();
        List<Engagment> eng = engagment.findById1(EngagmentIDF);
        for (Engagment j : eng) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("paneEngagment.fxml"));
            try {
                Node node = fxmlLoader.load();
                PaneEngagmentController controller = fxmlLoader.getController();
                controller.setNomEvents(j.getNom());
                controller.setJour(j.getJour());
                controller.setDate(j.getDate());
                controller.setHeure(j.getHeure());

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
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheListFreelancer.fxml"));
        Parent root = loader.load();
        AfficheListFreelancerController controller = loader.getController();
        Scene scene = new Scene(root, 1000, 600);
        Stage stage = (Stage) scrollPane.getScene().getWindow();
        stage.setTitle("Retour");
        stage.setScene(scene);
        stage.show();
    }

}
