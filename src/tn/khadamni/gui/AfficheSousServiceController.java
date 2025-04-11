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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
public class AfficheSousServiceController implements Initializable {

    int idtable = 0;

    @FXML
    private Button btnmodifier;
    @FXML
    private TextField id;
    @FXML
    private Pane global;
    @FXML
    private TableView<Sous_services> tbview;
    @FXML
    private TableColumn<Sous_services, String> colNom;
    @FXML
    private TableColumn<Sous_services, String> colDesc;
    @FXML
    private TableColumn<Sous_services, String> colIm;
    @FXML
    private AnchorPane affichage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refrechTable();
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {

        Sous_services serviceSelectionne = tbview.getSelectionModel().getSelectedItem();
        if (serviceSelectionne != null) {

            affichage.getChildren().clear();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierSousService.fxml"));

            Parent root;
            try {
                root = loader.load();
                ModifierSousServiceController controller = loader.getController();
                controller.initService(serviceSelectionne);
                affichage.getChildren().setAll(root);
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Sous_services s1 = tbview.getSelectionModel().getSelectedItem();

        Sous_servicesService ss = new Sous_servicesService();
        ss.supprimer(s1);
        refrechTable();
    }

    public void initTable() {

        colNom.setCellValueFactory(new PropertyValueFactory<>("Sous_service_nom"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("Sous_service_description"));
        colIm.setCellValueFactory(new PropertyValueFactory<>("Sous_service_image"));

    }

    public void refrechTable() {
        initTable();

        Sous_servicesService ss = new Sous_servicesService();
        List<Sous_services> serv = ss.afficher();
        ObservableList<Sous_services> listServices = FXCollections.observableArrayList(serv);
        tbview.setItems(listServices);
    }

}
