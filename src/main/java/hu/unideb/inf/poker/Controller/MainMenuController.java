/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.poker.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class MainMenuController implements Initializable {
   
    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenuController.class.getName());
    
    @FXML
    private Button newGameButton;
    @FXML
    private Button statisticsButton;
    @FXML
    private Button quitButton;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private void newGameButtonAction(ActionEvent event){
    
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainScene.fxml"));
            Parent root = fxmlLoader.load();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            
            Stage stage = (Stage)(newGameButton.getScene().getWindow());
            
            stage.setTitle("PokerGame");
            stage.setScene(scene);
            fxmlLoader.<MainFXMLController>getController().afterInitialize();
            stage.show();
        } catch (IOException ex) {
            LOGGER.error(ex.getLocalizedMessage());
        }
    }
    @FXML
    private void statisticsButtonAction(ActionEvent event){
    
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/StatisticsScene.fxml"));
            Parent root = fxmlLoader.load();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/statistics.css");
            
            Stage stage = (Stage)(statisticsButton.getScene().getWindow());
            
            stage.setTitle("Statistics");
            stage.setScene(scene);
            fxmlLoader.<StatisticsSceneController>getController().afterInitialize();
            stage.show();
        } catch (IOException ex) {
            LOGGER.error(ex.getLocalizedMessage());
        }
    }
    @FXML
    private void quitButtonAction(ActionEvent event){Platform.exit();}
        
    public void afterInitialize(){
    
        Stage st = (Stage)this.newGameButton.getScene().getWindow();
        st.resizableProperty().setValue(Boolean.FALSE);
        st.setOnCloseRequest(e->Platform.exit());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.anchorPane.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/welcomeMenuBackGround.png")),null,null,null,null)));
        this.newGameButton.setOpacity(0.8);
        this.statisticsButton.setOpacity(0.8);
        this.quitButton.setOpacity(0.8);

    }    
    
}
