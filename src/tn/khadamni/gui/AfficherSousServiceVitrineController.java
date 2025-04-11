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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.khadamni.entity.Services;
import tn.khadamni.entity.Sous_services;
import tn.khadamni.service.ServicesService;
import tn.khadamni.service.Sous_servicesService;

/**
 * FXML Controller class
 *
 * @author morta
 */
public class AfficherSousServiceVitrineController implements Initializable {

    private int serviceID;
    @FXML
    private AnchorPane mybutton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridPane;
    private int row = 0;
    private int col = 0;
    private boolean alternate = false;
    @FXML
    private TextField recherche;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setServiceID(int id) {
        this.serviceID = id;
        gridPane = new GridPane();
        gridPane.setHgap(70);
        gridPane.setVgap(20);

        Sous_servicesService service = new Sous_servicesService();
        List<Sous_services> serv = service.findByServiceId(serviceID);
        for (Sous_services i : serv) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyPane.fxml"));
            try {
                Node node = fxmlLoader.load();
                MypaneController controller = fxmlLoader.getController();
                controller.setNom(i.getSous_service_nom());
                controller.setDescription(i.getSous_service_description());
                String imageUrl = i.getSous_service_image(); // l'URL de l'image à charger
                Image image = new Image(imageUrl);
                controller.setImage(image);

                node.setOnMouseClicked(event -> {
                    // Action à effectuer lorsqu'un MyPane est cliqué
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheListFreelancer.fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                        AfficheListFreelancerController controller1 = loader.getController();

                        Scene scene = new Scene(root);
                        Stage stage = (Stage) mybutton.getScene().getWindow();
                        stage.setTitle("liste des Freelancer");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AfficheVitrineController.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                if (col > 2) {
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
    private void rechercher(KeyEvent event) {
      // Récupérer la valeur actuelle du champ de recherche
        String rechercheText = recherche.getText().trim().toLowerCase();

        // Vider le GridPane actuel
        gridPane.getChildren().clear();

        // Recréer le GridPane pour afficher les résultats de la recherche
        int row = 0;
        int col = 0;
        boolean alternate = false;

        Sous_servicesService service = new Sous_servicesService();
        List<Sous_services> serv = service.afficher();

        for (Sous_services i : serv) {
            // Vérifier si le nom de service contient la chaîne de recherche
            if (i.getSous_service_nom().toLowerCase().contains(rechercheText)) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyPane.fxml"));
                try {
                    Node node = fxmlLoader.load();
                    MypaneController controller = fxmlLoader.getController();
                    controller.setNom(i.getSous_service_nom());
                    controller.setDescription(i.getSous_service_description());
                    String imageUrl = i.getSous_service_image();
                    Image image = new Image(imageUrl);
                    controller.setImage(image);

                    node.setOnMouseClicked(event1 -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherSousServiceVitrine.fxml"));
                        Parent root;
                        try {
                            root = loader.load();
                            AfficherSousServiceVitrineController controller1 = loader.getController();
                            controller1.setServiceID(i.getService().getService_id());

                            Scene scene = new Scene(root);
                            Stage stage = (Stage) mybutton.getScene().getWindow();
                            stage.setTitle("ajouter Sous Service");
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AfficheVitrineController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    });

                    if (alternate) {
                        node.setStyle("-fx-background-color: #FFCC09;  -fx-background-radius: 20px; ");
                    } else {
                        node.setStyle("-fx-background-color: #515047;-fx-background-radius: 20px;");
                    }
                    alternate = !alternate;

                    gridPane.add(node, col, row);

                    col++;

                    if (col > 2) {
                        col = 0;
                        row++;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MypaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        // Afficher un message si aucun résultat n'a été trouvé
        if (gridPane.getChildren().isEmpty()) {
            Label label = new Label("Aucun résultat trouvé.");
            label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: black;");
            gridPane.add(label, 0, 0);
        }

        // Mettre à jour le contenu du ScrollPane
        HBox hbox = new HBox(gridPane);
        hbox.setAlignment(Pos.CENTER);
        scrollPane.setContent(hbox);

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root = loader.load();
        HomeController controller = loader.getController();
        Scene scene = new Scene(root, 1000, 600);
        Stage stage = (Stage) mybutton.getScene().getWindow();
        stage.setTitle("Retour");
        stage.setScene(scene);
        stage.show();
    }
    }


