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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class for end game dialog.
 */
public class GameOverController implements Initializable {

    /**
     * Logger instance for logging.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GameOverController.class.getName());
    
    @FXML
    private ImageView backgoundImageView;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button newGameButton;
    @FXML
    private Button statisticsButton;
    @FXML
    private Button quitButton;
    
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
            stage.show();
        } catch (IOException ex) {
            LOGGER.error(ex.getLocalizedMessage());
        }
    }
    @FXML
    private void quitButtonAction(ActionEvent event){Platform.exit();}
    /**
     * Sets the background.
     * @param event a String whitch van be victory or defeat
     */
    public void setBackGround(String event){
        if(event.equals("Victory!") ){
            this.backgoundImageView.setImage(new Image(getClass().getResourceAsStream("/pictures/victory.png")));
        }else{
            this.backgoundImageView.setImage(new Image(getClass().getResourceAsStream("/pictures/defeat.png")));
        }
        Stage st = (Stage) this.newGameButton.getScene().getWindow();
        st.setOnCloseRequest(e -> e.consume());
        st.resizableProperty().setValue(Boolean.FALSE);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
