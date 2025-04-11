/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;

import tn.khadamni.entity.Services;
import tn.khadamni.entity.Freelancer;
import tn.khadamni.entity.Utilisateur;
import javafx.geometry.Pos;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import tn.khadamni.service.ServicesService;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.khadamni.service.FreelancerService;
import tn.khadamni.service.UtilisateurService;
import tn.khadamni.test.MainGui;

/**
 * FXML Controller class
 *
 * @author lordg
 */
public class UtilisateurhomeController implements Initializable {

    private TableView<Services> category;
    @FXML
    private Button logout;
    private int id;
    private Utilisateur u;
    @FXML
    private ImageView pdp;
    @FXML
    private Label nomu;
    private String photo;
    private String name;
    private TextField a;
    @FXML
    private Button check;
    @FXML
    private ScrollPane scrollPane;
   private int row = 0;
    private int col = 0;
    @FXML
    private GridPane gridPane;
    @FXML
    private Pane dashboard;
    @FXML
    private Button offre;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label num;
    public UtilisateurhomeController() {
    }
public void id(int i){
   // a.setText(Integer.toString(i)); ;
    
    }
    public UtilisateurhomeController(int i, Utilisateur u7) {
        this.id = i;
        this.u = u7;
        System.out.println(u);
    }
    

    /**
     * Initializes the controller class.
     */
    public void setid(int data){
        this.id=data;
        //System.out.println(Integer.toString(id));  
    }
    public void initializeprem(Utilisateur f){
        
  
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setText(SessionController.currentuser.getNom());
        prenom.setText(SessionController.currentuser.getPrenom());
        num.setText(Integer.toString(SessionController.currentuser.getNumtel()));
          UtilisateurService us = new UtilisateurService();
       Utilisateur u1 = SessionController.currentuser;
        System.out.println(u1);
             

       if(u1!=null){
           System.err.println("hello world");
       }else {
           System.err.println("rien");
       }
        

        ServicesService cs = new ServicesService();
        List<Services> categorys = cs.afficher();
        ObservableList<Services> observableList = FXCollections.observableArrayList(categorys);
        
    
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
                Image image = new Image(new File(imageUrl).toURI().toString());
                controller.setImage(image);
node.setStyle("-fx-background-color:#ffcc09");
               /* node.setOnMouseClicked(event -> {
                    // Action à effectuer lorsqu'un MyPane est cliqué
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

                });*/
                // Alternance de couleurs de fond
              

                // Ajoutez le nœud au GridPane en spécifiant la position de chaque nœud
                gridPane.add(node, col, row);

                // Incrémente la colonne pour la prochaine instance de MyPane
                col++;

                // Si on atteint la troisième colonne, retourne à la première colonne et incrémente la ligne
                if (col > 1) {
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
    private void logout(ActionEvent event) {
        //date_fin_session
        UtilisateurService us = new UtilisateurService();
        System.out.println(id);
        us.logout(id);
        
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

    private void profil(ActionEvent event) {
       
        
    }

    @FXML
    private void clicked(ActionEvent event) {
         try{
           System.out.println("hello");
        
        
       FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/profil.fxml"));
            Parent root = loader.load();
        Scene scene = new Scene(root);
       ProfilController pc = loader.getController();
       
       
        UtilisateurService us = new UtilisateurService();
         Utilisateur u1 = SessionController.currentuser;
          // System.out.println(a.getText());
         System.out.println(id);
         
       if(u1!=null){
           
           System.out.println(u1);
           System.out.println("aalisson becker");
            pc.setuser(u1);
            pc.setid(id);
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
    private void offre(ActionEvent event) {
    }
   
}
