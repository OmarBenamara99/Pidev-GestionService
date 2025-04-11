package tn.khadamni.gui;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.mail.MessagingException;
import tn.khadamni.entity.Offre;
import tn.khadamni.entity.Postule;
import tn.khadamni.entity.Utilisateur;
import tn.khadamni.service.OffreService;
import tn.khadamni.service.PostuleService;


public class ThumbController implements Initializable {

    @FXML
    private Label catt;
    @FXML
    private Label scatt;
    @FXML
    private Label adrr;
    
    @FXML
    private Button pos;
    
    @FXML
    private Label datee;
    @FXML
    private Label descc;
    @FXML
    private ImageView imgg;
    @FXML
    private VBox vb1;
    @FXML
    private HBox hb1;
    @FXML
    private VBox vb2;
    @FXML
    private VBox vb3;
    @FXML
    private HBox hb2;
    @FXML
    private TextField id;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Utilisateur u=SessionController.currentuser;
        
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
    private void ajoutpos(ActionEvent event) {
        int i = Integer.parseInt(id.getText());
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        
        PostuleService ps = new PostuleService();
        Postule p = new Postule(i, SessionController.currentuser.getId(), date);
        ps.ajouterPostule(p);
        
        OffreService os = new OffreService();
        List<Offre> o = os.getClient(i);
        for (Offre t : o){
            String cn = t.getUser().getNom();
            String ce = t.getUser().getAdresse();
            String nm = SessionController.currentuser.getNom();
            String prn = SessionController.currentuser.getPrenom();
            int ad = SessionController.currentuser.getNumtel();
            System.out.println(cn+ce);
             EmailSender emailSender = new EmailSender();
            try {
            emailSender.sendEmail(ce, "Freelancer Application", "Hello "+ cn +  " I'm interested in your job offer my name is " + nm + " " + prn + " and here is my phone numbre : "+ ad );
            }
            catch (MessagingException ex) {
            System.out.println("Error sending email: " + ex.getMessage());
        }
        }
    }
    
}
