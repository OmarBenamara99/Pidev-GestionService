package tn.khadamni.gui;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.khadamni.entity.Offre;
import tn.khadamni.entity.Postule;
import tn.khadamni.service.OffreService;
import tn.khadamni.service.PostuleService;


public class PostuleController implements Initializable {
    
    private Offre offreSelectionne;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridPane;
    
    private int row = 0;
    private int col = 0;
    public static Offre currentoffre;
    @FXML
    private Button rt;
    
    @Override
    public void initialize(URL url, ResourceBundle rb ) {
        //refresh();
    }   
    
    public void refresh(int offerId){
        gridPane = new GridPane();
        //gridPane.setHgap(20);
        gridPane.setVgap(20);
        
        PostuleService ps = new PostuleService();
        List<Postule> p = ps.getAllpostulation(offerId);
        for (Postule i : p){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("thum.fxml"));
            try{
               
                Node node = fxmlLoader.load();
                ThumController controller = fxmlLoader.getController();
                controller.setnm(i.getUser().getNom());
                controller.setprn(i.getUser().getPrenom());
                controller.setnum(i.getUser().getNumtel());
                controller.setem(i.getUser().getAdresse());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String datestr = dateFormat.format(i.getPostulation_date());
                controller.setdatee(datestr);
                /*
                String imageUrl = i.getUser().;
                Image image = new Image(imageUrl);
                controller.setimgg(image);
                */
                //controller.setid(id);
                controller.setid(offerId);
                gridPane.add(node, col, row);
                row++;
            }
            catch (IOException ex) {
                Logger.getLogger(ThumController.class.getName()).log(Level.SEVERE, null, ex);
            }
            HBox hbox = new HBox(gridPane);
            hbox.setAlignment(Pos.CENTER);
            scrollPane.setContent(hbox);
        }
    }       

    @FXML
    private void rtr(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherOffre.fxml"));
        Parent root = loader.load();
        AfficherOffreController controller = loader.getController();
        Scene scene = new Scene(root, 900, 600);
        Stage stage = (Stage) rt.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
