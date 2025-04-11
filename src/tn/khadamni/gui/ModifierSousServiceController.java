package tn.khadamni.gui;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.khadamni.entity.Services;
import tn.khadamni.entity.Sous_services;
import tn.khadamni.service.ServicesService;
import tn.khadamni.service.Sous_servicesService;


public class ModifierSousServiceController implements Initializable {

    int id,idService;
    private File imgFile;
String nom, description, image;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDesc;
    private Sous_services serviceSelectionne;
    private Pane global;
    @FXML
    private AnchorPane mybutton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Annuler(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheSousService.fxml"));
        Parent root = loader.load();
        AfficheSousServiceController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = (Stage) global.getScene().getWindow();
        stage.setTitle("Modifier Service");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Modifier(ActionEvent event) {
        
           // Vérifier que les champs sont remplis correctement
    if (txtNom.getText().isEmpty() || txtDesc.getText().isEmpty() || imgFile == null ) {
        // Afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }
    
    // Vérifier que le champ nombre de services est bien un entier
    int nombreServices;
  
          nom = txtNom.getText();
        description = txtDesc.getText();
        String imgURL = "";
        if (imgFile != null) {
            imgURL = imgFile.toURI().toString();
        }
        Sous_services s = new Sous_services(id,nom, description,imgURL);
        Sous_servicesService ss = new Sous_servicesService();
        ss.modifierS(s);
        txtNom.setText("");
        txtDesc.setText("");
        imageview.setImage(null);
        

    }

    void initService(Sous_services serviceSelectionne) {
        this.serviceSelectionne = serviceSelectionne;
        id = serviceSelectionne.getSous_service_id();
        txtNom.setText(serviceSelectionne.getSous_service_nom());

        txtDesc.setText(serviceSelectionne.getSous_service_description());
           imageview.setImage(new Image(serviceSelectionne.getSous_service_image()));

    }

    @FXML
    private void insertimage(MouseEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Fichiers Image", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        imgFile = fileChooser.showOpenDialog(null);
        if (imgFile != null) {
            Image image = new Image(imgFile.toURI().toString());
            imageview.setImage(image);
        }
    }

    @FXML
    private void verifnom(KeyEvent event) {
      if (!txtNom.getText().matches("[a-zA-Z]+")) {
    {
         Alert alert = new Alert(Alert.AlertType.ERROR, "seulement des caractere svp!");
        alert.showAndWait();
        txtNom.setText("");
        return;
    }
}
    }

    @FXML
    private void verifdesc(KeyEvent event) {
    if (!txtDesc.getText().matches("[a-zA-Z]+")) {
    {
         Alert alert = new Alert(Alert.AlertType.ERROR, "seulement des caractere svp!");
        alert.showAndWait();
        txtDesc.setText("");
        return;
    }
}
    }

}
