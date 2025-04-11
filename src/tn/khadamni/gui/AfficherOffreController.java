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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.khadamni.entity.Offre;
import tn.khadamni.entity.Utilisateur;
import tn.khadamni.service.OffreService;


public class AfficherOffreController implements Initializable {

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
    private Button ajout;
    private Button pos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refresh();
    }   
    
    private void refresh(){
        Utilisateur u=SessionController.currentuser;
        gridPane = new GridPane();
        //gridPane.setHgap(20);
        gridPane.setVgap(20);
        OffreService os = new OffreService();
        List<Offre> serv = os.getAllOffree(SessionController.currentuser.getId());
        for (Offre i : serv){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("azer.fxml"));
            try{
                Node node = fxmlLoader.load();
                AzerController controller = fxmlLoader.getController();
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
                Logger.getLogger(AzerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            HBox hbox = new HBox(gridPane);
            hbox.setAlignment(Pos.CENTER);
            scrollPane.setContent(hbox);
        }
    }
    

    @FXML
    private void pageajt(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterOffre.fxml"));
        Parent root = loader.load();
        AjouterOffreController controller = loader.getController();
        Scene scene = new Scene(root, 900, 600);
        //Stage stage = new Stage();
        //stage.setTitle("Ajouter Offre");
        Stage stage = (Stage) ajout.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
