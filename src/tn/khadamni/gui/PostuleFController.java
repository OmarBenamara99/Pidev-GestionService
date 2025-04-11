
package tn.khadamni.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import tn.khadamni.entity.Offre;
import tn.khadamni.service.OffreService;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import tn.khadamni.entity.Services;
import tn.khadamni.entity.Sous_services;
import tn.khadamni.service.ServicesService;
import tn.khadamni.service.Sous_servicesService;
import javax.mail.*;
import javax.mail.internet.*;


public class PostuleFController implements Initializable {

    @FXML
    private AnchorPane mybutton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridPane;
    
    private int row = 0;
    private int col = 0;
    public static Offre currentoffre;
    @FXML
    private ComboBox<String> serviceCombo;
    @FXML
    private ComboBox<String> sserviceCombo;
    
    

    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateServiceCombo();
        populateSserviceCombo();
        serviceCombo.setOnAction(e -> filterOffers());
        sserviceCombo.setOnAction(e -> filterOffers());
        gridPane = new GridPane();
        //gridPane.setHgap(20);
        gridPane.setVgap(20);
        OffreService os = new OffreService();
        List<Offre> serv = os.getAllOffre();
        for (Offre i : serv){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("thumb.fxml"));
            try{
                Node node = fxmlLoader.load();
                ThumbController controller = fxmlLoader.getController();
                controller.setcatt(i.getService());
                controller.setscatt(i.getSservice());
                controller.setadrr(i.getAdr());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String datestr = dateFormat.format(i.getDate_offre());
                controller.setdatee(datestr);
                controller.setdescc(i.getDesc());
                String imageUrl = i.getImg();
                Image image = new Image(imageUrl);
                controller.setimgg(image);
                controller.setid(i.getId_offre());
                
                gridPane.add(node, col, row);
                row++;
            }
            catch (IOException ex) {
                Logger.getLogger(ThumbController.class.getName()).log(Level.SEVERE, null, ex);
            }
            HBox hbox = new HBox(gridPane);
            hbox.setAlignment(Pos.CENTER);
            scrollPane.setContent(hbox);
        }
       
    }    
    
    public void populateServiceCombo() {
        ServicesService service = new ServicesService();
        List<Services> serv = service.afficher();

        for (Services i : serv) {
            serviceCombo.getItems().add(i.getService_nom());

        }
    }
    public void populateSserviceCombo() {
        Sous_servicesService service = new Sous_servicesService();
        List<Sous_services> serv = service.afficher();

        for (Sous_services i : serv) {
            sserviceCombo.getItems().add(i.getSous_service_nom());

        }
    }
    
    private void filterOffers() {
        // Retrieve the selected service and sub-service from the combo boxes
        String service = serviceCombo.getValue();
        String sservice = sserviceCombo.getValue();
        
        // Create a new instance of your OffreService class and call a method that retrieves offers filtered by service and sub-service
        OffreService os = new OffreService();
        List<Offre> filteredOffers = os.getFilteredOffers(service, sservice);
        
        // Clear the existing nodes in the grid pane
        gridPane.getChildren().clear();
        
        // Display the filtered offers in the grid pane
        for (Offre i : filteredOffers){
            // Create a new instance of your ThumbController class
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("thumb.fxml"));
            try{
                Node node = fxmlLoader.load();
                ThumbController controller = fxmlLoader.getController();
                // Set the properties of the ThumbController instance using the data from the current offer
                controller.setcatt(i.getService());
                controller.setscatt(i.getSservice());
                controller.setadrr(i.getAdr());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String datestr = dateFormat.format(i.getDate_offre());
                controller.setdatee(datestr);
                controller.setdescc(i.getDesc());
                String imageUrl = i.getImg();
                Image image = new Image(imageUrl);
                controller.setimgg(image);
                controller.setid(i.getId_offre());
                
                gridPane.add(node, col, row);
                row++;
            }
            catch (IOException ex) {
                Logger.getLogger(ThumbController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void display(MouseEvent event) {
        
    }

    

    
}
