package tn.khadamni.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.khadamni.entity.Services;
import tn.khadamni.gui.MypaneController;
import tn.khadamni.service.ServicesService;
import tn.khadamni.gui.AfficherSousServiceVitrineController;

public class AfficheVitrineController implements Initializable {

    @FXML
    private GridPane gridPane;
    private int row = 0;
    private int col = 0;
    private boolean alternate = false; // variable booléenne pour l'alternance de couleurs

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane mybutton;
    @FXML
    private TextField recherche;
  public static Services currentservice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        ServicesService service = new ServicesService();
        List<Services> serv = service.afficher();

        for (Services i : serv) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyPane.fxml"));
            try {
                Node node = fxmlLoader.load();
                MypaneController controller = fxmlLoader.getController();
                controller.setNom(i.getService_nom());
                controller.setDescription(i.getService_description());
                String imageUrl = i.getService_image(); // l'URL de l'image à charger
                Image image = new Image(imageUrl);
                controller.setImage(image);

                node.setOnMouseClicked(event -> {
                    // Action à effectuer lorsqu'un MyPane est cliqué
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherSousServiceVitrine.fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                        AfficherSousServiceVitrineController controller1 = loader.getController();
                        controller1.setServiceID(i.getService_id());
                        currentservice=i;

                        Scene scene = new Scene(root);
                        Stage stage = (Stage) mybutton.getScene().getWindow();
                        stage.setTitle("ajouter Sous Service");
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

        ServicesService service = new ServicesService();
        List<Services> serv = service.afficher();

        for (Services i : serv) {
            // Vérifier si le nom de service contient la chaîne de recherche
            if (i.getService_nom().toLowerCase().contains(rechercheText)) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyPane.fxml"));
                try {
                    Node node = fxmlLoader.load();
                    MypaneController controller = fxmlLoader.getController();
                    controller.setNom(i.getService_nom());
                    controller.setDescription(i.getService_description());
                    String imageUrl = i.getService_image();
                    Image image = new Image(imageUrl);
                    controller.setImage(image);

                    node.setOnMouseClicked(event1 -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherSousServiceVitrine.fxml"));
                        Parent root;
                        try {
                            root = loader.load();
                            AfficherSousServiceVitrineController controller1 = loader.getController();
                            controller1.setServiceID(i.getService_id());

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
}
