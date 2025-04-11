package tn.khadamni.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.mail.MessagingException;
import tn.khadamni.entity.Postule;
import tn.khadamni.service.PostuleService;


public class ThumController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private Label nm;
    @FXML
    private Label prn;
    @FXML
    private Label num;
    @FXML
    private Label em;
    @FXML
    private Label prf;
    private ImageView im;
    @FXML
    private Label dtt;
    @FXML
    private Button Em;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setid(int i) {
        id.setText(String.valueOf(i));
    }
    
    public void setnm(String nom) {
        nm.setText(nom);
    }
    public void setprn(String nom) {
        prn.setText(nom);
    }
    public void setnum(int i) {
        num.setText(String.valueOf(i));
    }
    public void setem(String nom) {
        em.setText(nom);
    }
    public void setprf(String nom) {
        prf.setText(nom);
    }
    public void setdatee(String nom) {
        dtt.setText(nom);
    }
    public void setimgg(Image image) {
        im.setImage(image);
    } 

    @FXML
    private void Emm(ActionEvent event) {
        int i = Integer.parseInt(id.getText());
        PostuleService ps = new PostuleService();
        List<Postule> p = ps.getAllpostulation(i);
        for (Postule t : p){
            
            String fn = t.getUser().getNom();
            String fe = t.getUser().getAdresse();
            
            EmailSender emailSender = new EmailSender();
        try {
            emailSender.sendEmail(fe, "Application Accepted", "Hello "+ fn +  " I'm interested in hiring you for the job offer");
        }
         catch (MessagingException ex) {
            System.out.println("Error sending email: " + ex.getMessage());
        }
        }
    }
    
}
