/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.khadamni.gui;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import com.google.zxing.qrcode.encoder.QRCode;
import com.google.zxing.BarcodeFormat;

import com.itextpdf.text.pdf.qrcode.EncodeHintType;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.khadamni.entity.Freelancer;
import tn.khadamni.service.FreelancerService;
import tn.khadamni.test.MainGui;

/**
 * FXML Controller class
 *
 * @author lordg
 */
public class UserCardController implements Initializable {

    @FXML
    private ImageView pdp;
    @FXML
    private Label name;
    @FXML
    private Label prenom;
    @FXML
    private Label num;
    @FXML
    private Label mail;
    @FXML
    private Button back;
    @FXML
    private ImageView QrCode;
    @FXML
    private Button qrcode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FreelancerService fs = new FreelancerService();
        Freelancer f = AfficheListFreelancerController.chosenfreelancer;
        String imagePath = "";
        if (f != null) {
            imagePath = f.getPhoto();
        } else {
            imagePath = "C:\\Users\\lordg\\Desktop\\anonyme.jpg";
        }

        Image image = new Image(new File(imagePath).toURI().toString());

        pdp.setImage(image);
        pdp.setFitWidth(350);
        pdp.setFitHeight(350);
        name.setText(f.getNom());
        prenom.setText(f.getPrenom());
        num.setText(String.valueOf(f.getNumtel()));
        mail.setText(f.getAdresse());
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheListFreelancer.fxml"));
        Parent root = loader.load();
        AfficheListFreelancerController controller = loader.getController();
        Scene scene = new Scene(root);
  Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          stage.setTitle("Retour");
        stage.setScene(scene);
        stage.show();    
    }
public void start(Freelancer f) {
 
    QRCodeWriter QRCodeWriter;
    
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    String myWeb = "nom : " +f.getNom()+"\n"+"mail : "+f.getAdresse()+"\n"+"numero : "+String.valueOf(f.getNumtel())+"\n"+"service : "+f.getProfession();
    int width = 300;
    int height = 300;
    String fileType = "png";
    
    BufferedImage bufferedImage = null;
     try { BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
    //BitMatrix byteMatrix = qrCodeWriter.encode(myData, BarcodeFormat.QR_CODE, width, height);
    bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    bufferedImage.createGraphics();
    Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, width, height);
    graphics.setColor(Color.BLACK);
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            if (byteMatrix.get(i, j)) {
                graphics.fillRect(i, j, 1, 1);
            }
        }
    }
    ImageView qrView = new ImageView();
    qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
    StackPane root = new StackPane();
    root.getChildren().add(qrView);
    Scene scene = new Scene(root, 350, 350);
    Stage stage = new Stage();
    stage.setTitle("QR Code");
    stage.setScene(scene);
    stage.show();
    System.out.println("Success...");
     } catch (WriterException ex) {
        Logger.getLogger(MainGui.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    @FXML
    private void qrcode(ActionEvent event) {
         FreelancerService fs = new FreelancerService();
        Freelancer f = AfficheListFreelancerController.chosenfreelancer;
        start(f);
    }
}
