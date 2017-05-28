/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.poker.Controller;

import hu.unideb.inf.poker.Database.EntityManagement;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class for representing the player's statistics.
 */
public class StatisticsSceneController implements Initializable {

    private EntityManagement dbConnect;
    private ArrayList<Long> handStatistic;
    private final XYChart.Series set1 = new XYChart.Series();
    private final XYChart.Series set2 = new XYChart.Series();
    private final XYChart.Series set3 = new XYChart.Series();
    
    @FXML
    private AnchorPane   anchorPane;
    @FXML
    private Label        winRateLabel;
    @FXML
    private Label        prizeWinLabel;
    @FXML
    private Label        prizeLostLabel;
    @FXML
    private BarChart     cardStrengthBarChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis   yAxis;
    @FXML
    private Button       mainMenuButton;
    
    @FXML
    private void mainMenuButtonAction(ActionEvent event){
        this.dbConnect.closeTransaction();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainMenuScene.fxml"));
            Parent root = fxmlLoader.load();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/welcome.css");
            
            Stage stage = (Stage)(mainMenuButton.getScene().getWindow());
            
            stage.setTitle("MainMenu");
            stage.setScene(scene);
            fxmlLoader.<MainMenuController>getController().afterInitialize();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void quitButtonAction(ActionEvent event){
        this.dbConnect.closeTransaction();
        Platform.exit();
    }
    public void afterInitialize(){
        Stage st = (Stage)this.winRateLabel.getScene().getWindow();
        st.resizableProperty().setValue(Boolean.FALSE);
        st.setOnCloseRequest(e -> {
            this.dbConnect.closeTransaction();
            Platform.exit();
        });
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.dbConnect     = new EntityManagement();
        this.handStatistic = dbConnect.getCardsPresentRate();
        
        this.winRateLabel.setText(dbConnect.getWinRate());
        this.prizeWinLabel.setText(dbConnect.getPrizeWinAvarage());
        this.prizeLostLabel.setText(dbConnect.getPrizeLostAvarage()); 

        set1.setName("Win");
        
        set1.getData().add(new XYChart.Data("High",          this.handStatistic.get(0)));
        set1.getData().add(new XYChart.Data("Pair",          this.handStatistic.get(3)));
        set1.getData().add(new XYChart.Data("TwoPair",       this.handStatistic.get(6)));
        set1.getData().add(new XYChart.Data("Drill",         this.handStatistic.get(9)));
        set1.getData().add(new XYChart.Data("Straight",      this.handStatistic.get(12)));
        set1.getData().add(new XYChart.Data("Flush",         this.handStatistic.get(15)));
        set1.getData().add(new XYChart.Data("FullHouse",     this.handStatistic.get(18)));
        set1.getData().add(new XYChart.Data("Poker",         this.handStatistic.get(21)));
        set1.getData().add(new XYChart.Data("StraightFlush", this.handStatistic.get(24)));
        set1.getData().add(new XYChart.Data("RoyalFlush",    this.handStatistic.get(27)));
        
       
        set2.setName("Lose");
        
        set2.getData().add(new XYChart.Data("High",          this.handStatistic.get(1)));
        set2.getData().add(new XYChart.Data("Pair",          this.handStatistic.get(4)));
        set2.getData().add(new XYChart.Data("TwoPair",       this.handStatistic.get(7)));
        set2.getData().add(new XYChart.Data("Drill",         this.handStatistic.get(10)));
        set2.getData().add(new XYChart.Data("Straight",      this.handStatistic.get(13)));
        set2.getData().add(new XYChart.Data("Flush",         this.handStatistic.get(16)));
        set2.getData().add(new XYChart.Data("FullHouse",     this.handStatistic.get(19)));
        set2.getData().add(new XYChart.Data("Poker",         this.handStatistic.get(22)));
        set2.getData().add(new XYChart.Data("StraightFlush", this.handStatistic.get(25)));
        set2.getData().add(new XYChart.Data("RoyalFlush",    this.handStatistic.get(28)));
        
        
        set3.setName("Draw");
        
        set3.getData().add(new XYChart.Data("High",          this.handStatistic.get(2)));
        set3.getData().add(new XYChart.Data("Pair",          this.handStatistic.get(5)));
        set3.getData().add(new XYChart.Data("TwoPair",       this.handStatistic.get(8)));
        set3.getData().add(new XYChart.Data("Drill",         this.handStatistic.get(11)));
        set3.getData().add(new XYChart.Data("Straight",      this.handStatistic.get(14)));
        set3.getData().add(new XYChart.Data("Flush",         this.handStatistic.get(17)));
        set3.getData().add(new XYChart.Data("FullHouse",     this.handStatistic.get(20)));
        set3.getData().add(new XYChart.Data("Poker",         this.handStatistic.get(23)));
        set3.getData().add(new XYChart.Data("StraightFlush", this.handStatistic.get(26)));
        set3.getData().add(new XYChart.Data("RoyalFlush",    this.handStatistic.get(29)));

        this.cardStrengthBarChart.getData().addAll(set1, set2, set3);
        
    }   
}
