/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;

import tn.khadamni.entity.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.khadamni.service.UtilisateurService;
import tn.khadamni.test.MainGui;
import tn.khadamni.tools.BcryptHasher;

/**
 * FXML Controller class
 *
 * @author lordg
 */
public class ProfilController implements Initializable {

    @FXML
    private ImageView pdp;
    @FXML
    private Label nom;
    
    @FXML
    private Button modier;
    @FXML
    private Label prenom;
    
    @FXML
    private Button d;
    private Text n;
    private Text p;
    @FXML
    private Button supp;
    @FXML
    private Button supdone;
    @FXML
    private Button home;
    @FXML
    private Button logout;
    @FXML
    private Label num;
    @FXML
    private Label adresse;
    private boolean b=false;
    @FXML
    private Label pr;
    private Utilisateur u;
    private int id;
    @FXML
    private Text nt;
    
    @FXML
    private TextField numero_field;
    @FXML
    private TextField prenom_field;
    @FXML
    private TextField adresse_field;
    @FXML
    private Text pt;
    @FXML
    private Text numt;
    @FXML
    private Text adt;
    @FXML
    private TextField nom_field;
    @FXML
    private PasswordField mdp;
    @FXML
    private PasswordField nmdp;
    @FXML
    private PasswordField cnmdp;
    @FXML
    private Label label_mdp;
    @FXML
    private Label label_nmdp;
    @FXML
    private Label label_cn;
    @FXML
    private Button confirmer;
    @FXML
    private Button showmdp;
    private BcryptHasher hasher;
    /**
     * Initializes the controller class.
     */
    
   
   

    public void initializeprem(Utilisateur u1) {
        //System.out.println(u1);
//        
//        nom_field.setVisible(false);
//        prenom_field.setVisible(false);
//        numero_field.setVisible(false);
//        adresse_field.setVisible(false);
//        confirmer.setVisible(false);
//         mdp.setVisible(false);
//         nmdp.setVisible(false);
//         cnmdp.setVisible(false);
//         label_mdp.setVisible(false);
//         label_nmdp.setVisible(false);
//         label_cn.setVisible(false);
//        d.setVisible(false);
//        supdone.setVisible(false);
//        u1=SessionController.currentuser;
//        if(u1!=null){
//            String name = u1.getNom();
//            nom.setText(name);
//            prenom.setText(u1.getPrenom());
//            adresse.setText(u1.getAdresse());
//            num.setText(Integer.toString(u1.getNumtel()));
//        }else{
//            System.err.println("sanctionné");
//        }
//        String  imagePath = "C:\\Users\\lordg\\Desktop\\anonyme.jpg";
//        Image image = new Image(new File(imagePath).toURI().toString());
//        pdp.setImage(image);
//        pdp.setFitHeight(250);
//        pdp.setFitWidth(200);
//       
//        nom.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
//        prenom.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
//        adresse.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
//        num.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 Utilisateur u1=new Utilisateur();
        nom_field.setVisible(false);
        prenom_field.setVisible(false);
        numero_field.setVisible(false);
        adresse_field.setVisible(false);
        confirmer.setVisible(false);
         mdp.setVisible(false);
         nmdp.setVisible(false);
         cnmdp.setVisible(false);
         label_mdp.setVisible(false);
         label_nmdp.setVisible(false);
         label_cn.setVisible(false);
        d.setVisible(false);
        supdone.setVisible(false);
        u1=SessionController.currentuser;
        if(u1!=null){
            String name = u1.getNom();
            nom.setText(name);
            prenom.setText(u1.getPrenom());
            adresse.setText(u1.getAdresse());
            num.setText(Integer.toString(u1.getNumtel()));
        }else{
            System.err.println("sanctionné");
        }
        String  imagePath = "C:\\Users\\lordg\\Desktop\\anonyme.jpg";
        Image image = new Image(new File(imagePath).toURI().toString());
        pdp.setImage(image);
        pdp.setFitHeight(250);
        pdp.setFitWidth(200);
       
        nom.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        prenom.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        adresse.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        num.setFont(Font.font("Verdana", FontWeight.BOLD, 16));     
        
    }    

    @FXML
    private void mshow(ActionEvent event) {
   
       
       nom_field.setText(u.getNom());
       prenom_field.setText(u.getPrenom());
       numero_field.setText(Integer.toString(u.getNumtel()));
       adresse_field.setText(u.getAdresse());
       
       if(b){
            b=false;
            nom_field.setVisible(false);
        prenom_field.setVisible(false);
        numero_field.setVisible(false);
        adresse_field.setVisible(false);
        
     
      
      d.setVisible(false);
        }else{
            b=true;
              nom_field.setVisible(true);
        prenom_field.setVisible(true);
        numero_field.setVisible(true);
        adresse_field.setVisible(true);
         
         
          
          
          d.setVisible(true);
        }
    }

    @FXML
    private void valider(ActionEvent event) {
        
        if(nom_field.getText()==""){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("warning ");
            alert.setHeaderText(null);
            alert.setContentText("remplir le champs nom ");
            alert.show();
            return;
        }if (nom_field.getText().length() > 20) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le nom ne doit pas dépasser 20 caractères.");
                alert.show();
                return;
            }
            if (prenom_field.getText().length() > 20) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le nom ne doit pas dépasser 20 caractères.");
                alert.show();
                return;
            }
            // Vérifier que le numéro de téléphone est composé de 8 chiffres
            if (!numero_field.getText().matches("\\d{8}")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le numéro de téléphone doit être composé de 8 chiffres.");
                alert.show();
                return;
            }
            
            // Vérifier que le mot de passe comporte au moins 8 caractères
            
            if(!adresse_field.getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("adresse mail invalide.");
                alert.show();
                return;
            }
        UtilisateurService us = new UtilisateurService();
    
         Utilisateur u1 = new Utilisateur(u.getId(), Integer.parseInt(numero_field.getText()), nom_field.getText(), prenom_field.getText(), adresse_field.getText(), "user");
                us.modifier(u1);
        us.modifier(nom_field.getText(), u.getId());
        nom_field.setText("");
       prenom_field.setText("");
       numero_field.setText("");
       adresse_field.setText("");
      
      nom_field.setVisible(false);
        prenom_field.setVisible(false);
        numero_field.setVisible(false);
        adresse_field.setVisible(false);
         
//        n.setVisible(false);
      // p.setVisible(false);
      // d.setVisible(false);
       
    }

    @FXML
    private void supp(ActionEvent event) {
        if(b){
            b=false;
           nom_field.setVisible(false);
        prenom_field.setVisible(false);
        numero_field.setVisible(false);
        adresse_field.setVisible(false);
         
      
       supdone.setVisible(false);
        }else{
            b=true;
              nom_field.setVisible(true);
        prenom_field.setVisible(true);
        numero_field.setVisible(true);
        adresse_field.setVisible(true);
        
      
      supdone.setVisible(true);
        }
       
        
    }
 public void setuser(Utilisateur u1){
        this.u=u1;
        //System.err.println(u);
    }
    public void setid(int i){
        this.id=i;
    }
    @FXML
    private void supprimer(ActionEvent event) {
      /*   if(!u.getMdp().equals(mdp_field.getText())){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("warning ");
            alert.setHeaderText(null);
            alert.setContentText("mot de passe incorrecte ");
            alert.show();
            return;
        }
         */
        UtilisateurService us = new UtilisateurService();
        us.supprimer(id);
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

    @FXML
    private void home(ActionEvent event) {
        try {

           FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/home.fxml"));
            Parent root = loader.load();
            UtilisateurhomeController uc = new UtilisateurhomeController();
            
           // uc.setid(id);
           //uc.initializeprem(SessionController.currentuser);
        Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
         
            stage.show();

        } catch (IOException ex) {
            

            Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE,
                    null, ex);

        }
    }

    @FXML
    private void logout(ActionEvent event) {
        
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

    @FXML
    private void changermdp(ActionEvent event) {
        UtilisateurService us = new UtilisateurService();
         hasher = new BcryptHasher();
        if (hasher.checkPassword(SessionController.currentuser.getMdp(), mdp.getText())==false){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("mot de passe invalide.");
                alert.show();
                return;
        }
        if(nmdp.getText().length()<8){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("le mdp doit avoir au minumum 8 charactere.");
                alert.show();
                return;   
        }
        if(!(nmdp.getText().equals(cnmdp.getText()))){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("les deux mdp sont differrent.");
                alert.show();
                return;
        }
        System.out.println(String.valueOf(SessionController.currentuser.getId()));
        us.modifiermdp(nmdp.getText(),SessionController.currentuser.getId());
        mdp.setText("");
        nmdp.setText("");
        cnmdp.setText("");
          mdp.setVisible(false);
         nmdp.setVisible(false);
         cnmdp.setVisible(false);
         label_mdp.setVisible(false);
         label_nmdp.setVisible(false);
         label_cn.setVisible(false);
          confirmer.setVisible(false);
    }

    @FXML
    private void showmdp(ActionEvent event) {
          mdp.setVisible(true);
         nmdp.setVisible(true);
         cnmdp.setVisible(true);
         label_mdp.setVisible(true);
         label_nmdp.setVisible(true);
         label_cn.setVisible(true);
         confirmer.setVisible(true);
    }
    
}
