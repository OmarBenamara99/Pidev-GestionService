package tn.khadamni.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class MapController implements Initializable {
    
        public static double lon;
        public static double lat;

    @FXML
    private WebView wv;
    @FXML
    private TextField coordinatesField;
    private WebEngine engine;
    @FXML
    private Button btn;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //engine = wv.getEngine();    
        //engine.load(getClass().getResource("../html/Map1.html").toString());
        //engine.executeScript("sendData()");
        //googleMap
        
        engine = wv.getEngine();
        url = this.getClass().getResource("map/Map1.html");
        engine.load(url.toString());
    }
    

    @FXML
    private void tt(MouseEvent event) {
        lat = (Double) wv.getEngine().executeScript("lat");
        lon = (Double) wv.getEngine().executeScript("lon");


                //System.out.println("Lat: " + lat);
                //System.out.println("Lon " + lon);
                coordinatesField.setText("Latitude : "+Double.toString(lat)+" Longitude : "+Double.toString(lon));
    }

    @FXML
    private void sendd(ActionEvent event) throws IOException {
        String text = coordinatesField.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterOffre.fxml"));
        Parent root = loader.load();
        AjouterOffreController controller = loader.getController();
        controller.setLabelText(text);
        Scene scene = new Scene(root);
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // JavaScript interface object
    private class JavaApp {
        public void exit() {
        Platform.exit();
        }}
    
}
  
