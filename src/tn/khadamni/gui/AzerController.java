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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.khadamni.entity.Offre;
import tn.khadamni.service.OffreService;

/**
 * FXML Controller class
 *
 * @author Paladach
 */
public class AzerController implements Initializable {

    @FXML
    private HBox hb1;
    @FXML
    private VBox vb2;
    @FXML
    private Label catt;
    @FXML
    private Label scatt;
    @FXML
    private Label adrr;
    @FXML
    private VBox vb3;
    @FXML
    private Button mod;
    @FXML
    private Button sup;
    @FXML
    private Button pos;
    @FXML
    private Label datee;
    @FXML
    private HBox hb2;
    @FXML
    private Label descc;
    @FXML
    private TextField id;
    @FXML
    private ImageView imgg;
    @FXML
    private VBox vb1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    public void setid(int i) {
        id.setText(String.valueOf(i));
    }
    
    public void setcatt(String nom) {
        catt.setText(nom);
    }
    
    public void setscatt(String nom) {
        scatt.setText(nom);
    }
    
    public void setadrr(String nom) {
        adrr.setText(nom);
    }
    /*
    public void setpos(String nom) {
        pos.setText(nom);
    }
    */
    public void setdatee(String nom) {
        datee.setText(nom);
    }
    
    public void setdescc(String nom) {
        descc.setText(nom);
    }
    
    public void setimgg(Image image) {
        imgg.setImage(image);
    } 

    @FXML
    private void modd(ActionEvent event) throws IOException {
        int i = Integer.parseInt(id.getText());
        Offre os = new Offre(i);
        if (os != null) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierOffre.fxml"));
        Parent root = loader.load();
        ModifierOffreController controller = loader.getController();
        controller.initService(os);

        Scene scene = new Scene(root);
        //Stage stage = new Stage();
        //stage.setTitle("Modifier Offre");
        Stage stage = (Stage) mod.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    }
    
    

    @FXML
    private void supp(ActionEvent event) throws IOException {
        int i = Integer.parseInt(id.getText());
        Offre o = new Offre(i);
        OffreService os = new OffreService();
        os.supprimerOffre(o);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherOffre.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        Stage stage = (Stage) sup.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void poss(ActionEvent event) throws IOException {
        int i = Integer.parseInt(id.getText());
        Offre os = new Offre(i);
        if (os != null) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Postule.fxml"));
        Parent root = loader.load();
        PostuleController controller = loader.getController();
        controller.refresh(i);
        //controller.initService(os);
        Scene scene = new Scene(root);
        Stage stage = (Stage) pos.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    }
    

    
}
