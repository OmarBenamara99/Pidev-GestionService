/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import tn.khadamni.entity.Admin;
import tn.khadamni.entity.Freelancer;
import tn.khadamni.entity.Mysession;
import tn.khadamni.entity.Utilisateur;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.khadamni.service.AdminService;
import tn.khadamni.service.FreelancerService;
import tn.khadamni.gui.FreelancerhomeController;
import tn.khadamni.service.SessionSercive;
import tn.khadamni.service.UtilisateurService;
import tn.khadamni.test.MainGui;
import javax.mail.*;
import javax.mail.internet.*;
import java.net.PasswordAuthentication;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.util.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;
import javax.swing.JOptionPane;
//import sun.rmi.transport.Transport;
/**
 * FXML Controller class
 *
 * @author lordg
 */
public class SessionController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField mdp;
    @FXML
    private Button connect;
    @FXML
    private Button inscrire;
    private int dataToPass;
    public static Utilisateur currentuser;
    @FXML
    private Button reset;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nom.setPromptText("foulen@benfoulen.com");
        mdp.setPromptText("mot de passe");
    }

    public void setDataToPass(int dataToPass) {
        this.dataToPass = dataToPass;
    }
    
    @FXML
    private void connect(ActionEvent event) {

 //NB NB NB
 //ceci le test 2 le plus efficace
 try {
            UtilisateurService us = new UtilisateurService();
            AdminService as = new AdminService();
            FreelancerService fs = new FreelancerService();
            Utilisateur u = us.session(nom.getText(), mdp.getText());
            if (u!=null){
                         currentuser = u;
   
            }
            Freelancer f =null;
            Admin a =null;
            String role = "";
            String path = "";
             int id =u.getId();
             String t ="";
            role = u.getRole();
            switch (role) {
                case "freelancer": {
                     f = fs.session(nom.getText(), mdp.getText());
                    t="f";
                    if (f == null) {
                        System.out.println("Freelancer non trouvé");
                       Freelancer f2 =fs.findById(u.getId());
                        //id = u.getId();
                        
                        
                    } else {
                        System.out.println("Freelancer trouvé");
                        System.out.println(Integer.toString(id));
                        path = "../gui/homeFreelancer.fxml";
                        Mysession s = new Mysession(f);
                        SessionSercive sc2 = new SessionSercive();
                        sc2.ajouter(s);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("connexion ");
                        alert.setHeaderText(null);
                        alert.setContentText("vous êtes connecté ");
                        alert.show();
                    }
                    break;
                }
                case "admin": {
                    path = "../gui/DashBoard.fxml";
                     a = as.session(nom.getText(), mdp.getText());
                    t="a";
                    id=a.getId();
                    if (a == null) {
                        System.out.println("admin non trouvé");
                    } else {
                        System.out.println("admin trouvé");
                        System.out.println(Integer.toString(id));
                        Mysession s = new Mysession(a);
                        SessionSercive sc3 = new SessionSercive();
                        sc3.ajouter(s);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("connexion ");
                        alert.setHeaderText(null);
                        alert.setContentText("vous êtes connecté ");
                        alert.show();
                    }
                    break;
                }
                case "user": {
                    t="u";
                       //id = u.getId();
                    Mysession s = new Mysession(u);
                    SessionSercive sc = new SessionSercive();
                    sc.ajouter(s);
                     
                    path = "../gui/home.fxml";

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("connexion ");
                    alert.setHeaderText(null);
                    alert.setContentText("vous êtes connecté ");
                    alert.show();
                    break;
                }

                
            }

          
             FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
         JOptionPane.showMessageDialog(null, "bienvenue"+""+u.getNom());
        Scene scene = new Scene(root);
        if(t=="u"){
           // UtilisateurhomeController c = loader.getController();
            
             //   System.err.println(Integer.toString(id));
               ////  System.out.print(u);
             //c = new UtilisateurhomeController(id, u);
           //c.initializeprem(u);
          // c.setid(id);
           //c.id(id);
        }
        if(t=="f"){
           /// FreelancerhomeController fc = loader.getController();
            //fc.initializeprem(f);
            
           //fc.setid(id);
        }
        if(t=="a"){
           /* AdminHomeController ac = loader.getController();
           ac.initializeprem(a);
            ac.setid(id);*/
        }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
       
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("warning ");
            alert.setHeaderText(null);
            alert.setContentText("Personne invalide ");
            alert.show();
            
        }

    }

    @FXML
    private void inscrire(ActionEvent event) {
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("../gui/inscrire.fxml"
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
    private void reset(ActionEvent event) throws Exception {
        if(nom.getText().equals("")){
                  Notifications notificationBuilder = Notifications.create()
                        .title("reset mot de passe ")
                        .text("entrer une adresse mail ")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Clicked on Notification");        }
    });     
               notificationBuilder.show(); 
               return;
        }
        else{
               UtilisateurService us = new UtilisateurService();
         Random rnd = new Random();
            int number = rnd.nextInt(999999);
            String code = String.format("%06d", number);
        us.resetmdp(code, nom.getText());
                Notifications notificationBuilder = Notifications.create()
                        .title("mot de passe ")
                        .text("mot de passe envoyé par mail ")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Clicked on Notification");        }
    });     
               notificationBuilder.show();
        }
     
               //sendSMS.sendSMS(code);
                       
    }
//test 
  

}
