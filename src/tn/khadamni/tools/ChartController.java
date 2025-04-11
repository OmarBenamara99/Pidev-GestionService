package tn.khadamni.tools;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import tn.khadamni.entity.Services;
import tn.khadamni.service.ServicesService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import tn.khadamni.service.SessionSercive;

public class ChartController implements Initializable {

    @FXML
    private SwingNode chartNode;

    private CategoryDataset dataset;
    @FXML
    private BorderPane borderPane;
    int count = 0;
    @FXML
    private BorderPane borderPan;

    private void createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        SessionSercive ss = new SessionSercive();
        int[] serv = ss.getNombreFreelancersEtUtilisateursConnectesAujourdhui();
     

        

        this.dataset = dataset;
        count = 0;
    }

    private void createChart(int nbFreelancers, int nbUsers) {
        Platform.runLater(() -> {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(nbFreelancers, "Freelancers", "");
        dataset.addValue(nbUsers, "Users", "");
        JFreeChart chart = ChartFactory.createBarChart("Nombre de Freelancers et d'Utilisateurs Connectés Aujourd'hui", "", "Nombre", dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(chartPanel);
        borderPane.setCenter(swingNode);
        });
    }

    @FXML
    private void refreshChart() {
        createDataset();
        
    }

    @FXML
    private void exit() {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SessionSercive ss = new SessionSercive();
        int[] nbConnectes = ss.getNombreFreelancersEtUtilisateursConnectesAujourdhui();
        createChart(nbConnectes[0], nbConnectes[1]);
    }
}
