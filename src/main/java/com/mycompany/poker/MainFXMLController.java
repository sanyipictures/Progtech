package com.mycompany.poker;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;

public class MainFXMLController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(MainFXMLController.class.getName());
    
    private final Dealer dealer = new Dealer();

    private final Player[] playerArray = new Player[2];

    private final GameMaster gameMaster = new GameMaster();
    
    private final Button[][] cardPicturesArray = new Button[2][5];
    
    private final AI ai = new AI();
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML 
    private TextArea textArea;
    
    @FXML
    private Label playerCredit;
    @FXML
    private Label aiCredit;
    @FXML
    private Label playerBet;
    @FXML
    private Label aiBet;
    
    @FXML
    private ImageView gold;
    
    @FXML
    private TextField betTextField;
    
    @FXML
    private Button throwButton;
    @FXML
    private Button checkButton;
    @FXML
    private Button betButton;
    @FXML
    private Button changeButton;
    @FXML
    private Button increaseBetButton;
    @FXML 
    private Button decreseBetButtonAction;
    
    @FXML
    private Button playerCard1;
    @FXML
    private Button playerCard2;
    @FXML
    private Button playerCard3;
    @FXML
    private Button playerCard4;
    @FXML
    private Button playerCard5;
    
    @FXML
    private Button aiCard1;
    @FXML
    private Button aiCard2;
    @FXML
    private Button aiCard3;
    @FXML
    private Button aiCard4;
    @FXML
    private Button aiCard5;

    @FXML
    private void cardClickedAction(ActionEvent event){
    
        if(this.gameMaster.getPlayerChangedCard()){return;}
        
        String cardId = ((Button)event.getSource()).getId();
        
        switch(cardId){
        
            case "playerCard1" : 
                        this.playerArray[0].setSelectedCards(1);
                        if(this.playerArray[0].getSelectedCard(1)){
                            this.playerCard1.setOpacity(0.5);
                            this.textArea.appendText("\nYou have selected: " + cardId);
                        }
                        else{    
                            this.playerCard1.setOpacity(1);
                            this.textArea.appendText("\nYou have deselected: " + cardId);
                        }break;
            case "playerCard2" :
                        this.playerArray[0].setSelectedCards(2);
                        if(this.playerArray[0].getSelectedCard(2)){
                            this.playerCard2.setOpacity(0.5);
                            this.textArea.appendText("\nYou have selected: " + cardId);
                        }
                        else{    
                            this.playerCard2.setOpacity(1);
                            this.textArea.appendText("\nYou have deselected: " + cardId);
                        }break;
            case "playerCard3" :
                        this.playerArray[0].setSelectedCards(3);
                        if(this.playerArray[0].getSelectedCard(3)){
                            this.playerCard3.setOpacity(0.5);
                            this.textArea.appendText("\nYou have selected: " + cardId);
                        }
                        else{    
                            this.playerCard3.setOpacity(1);
                            this.textArea.appendText("\nYou have deselected: " + cardId);
                        }break;
            case "playerCard4" :
                        this.playerArray[0].setSelectedCards(4);
                        if(this.playerArray[0].getSelectedCard(4)){
                            this.playerCard4.setOpacity(0.5);
                            this.textArea.appendText("\nYou have selected: " + cardId);
                        }
                        else{    
                            this.playerCard4.setOpacity(1);
                            this.textArea.appendText("\nYou have deselected: " + cardId);
                        }break;
            case "playerCard5" :
                        this.playerArray[0].setSelectedCards(5);
                        if(this.playerArray[0].getSelectedCard(5)){
                            this.playerCard5.setOpacity(0.5);
                            this.textArea.appendText("\nYou have selected: " + cardId);
                        }
                        else{    
                            this.playerCard5.setOpacity(1);
                            this.textArea.appendText("\nYou have deselected: " + cardId);
                        }break;
        }
    }
    
    @FXML
    private void throwButtonAction(ActionEvent event){
    
        this.gameMaster.setFlopped(1, true);
        this.textArea.appendText("\nYou Have Flopped Your Card!");
        LOGGER.log(Level.INFO, "Player has flopped cards!");
        this.endTurn();
    }
    
    @FXML
    private void betButtonAction(ActionEvent event){
        int betAmount;
        
        try{
            betAmount = Integer.parseInt(this.betTextField.getText());
        }catch(NumberFormatException exception){
            LOGGER.log(Level.WARNING, "Player has entered invalid number!", exception);
            this.textArea.appendText("\n\nYou must enter numeric number!");
            return;
        }
        
        if(betAmount < this.gameMaster.getMinBet()){
            LOGGER.log(Level.INFO, "Player tries to bet below minimum amount!");
            this.textArea.appendText("\n\nThe minimum bet is 200!\n Increase your bet!!!");
            return;
        }
        
        if( (betAmount + this.playerArray[0].getBet()) > this.gameMaster.getMaxBet()){
            if( betAmount > this.playerArray[0].getCredit() ){
                
                LOGGER.log(Level.INFO, "Player has put all in!");
                this.textArea.appendText("\n\nYou have put all in!");
                this.playerArray[0].incrementBet(this.playerArray[0].getCredit());
                this.playerArray[0].setCredit(0);
                
                this.displayInterface();
                this.ai.aiTurn(this.playerArray[1], dealer, gameMaster);
                this.displayInterface();
                if(gameMaster.getFlopped(2)){
                    this.endTurn();
                }
            }else{
            
                this.playerArray[0].incrementBet(betAmount);
                this.gameMaster.setMaxBet(this.playerArray[0].getBet());
                this.playerArray[0].decrementCredit(betAmount);
                
                this.displayInterface();
                this.ai.aiTurn(this.playerArray[1], dealer, gameMaster);
                this.displayInterface();
                
                if(gameMaster.getFlopped(2)){
                    this.endTurn();
                }
            }
        }
    }
    
    @FXML
    private void checkButtonAction(ActionEvent event){
        if(this.playerArray[0].getBet() == 0){
            this.textArea.appendText("\nYou can not check without any bet!");
            return;
        }
        
        if(this.playerArray[0].getBet() == this.playerArray[1].getBet()){
            LOGGER.log(Level.INFO, "Player has checked!");
            this.textArea.appendText("\n\nYou have checked!");
            this.endTurn();
        }else 
            if(this.playerArray[0].getCredit() > 0){
            LOGGER.log(Level.INFO, "Player can not check!");
            this.textArea.appendText("\n\nYou can not check, becouse the bets are not equal!");
        }
    }
    
    @FXML
    private void changeButtonAction(ActionEvent event){
        if(!this.gameMaster.getPlayerChangedCard()){
            boolean[] selectedCards = this.playerArray[0].getSelectedCards();

            if(!selectedCards[0] && !selectedCards[1] && !selectedCards[2] && !selectedCards[3] && !selectedCards[4]){
                this.textArea.appendText("\nYou have tried to changed cards,\n without selecting one!");
                return;
            }
            for(int i = 0; i < 5; ++i){

                if(selectedCards[i]){
                    this.dealer.dealToPlayer(this.playerArray[0], i);
                    this.textArea.appendText("\nYou have changed a card!\n New card is: " + this.playerArray[0].getHand()[i]);
                }
            }
            this.playerCard1.setOpacity(1);
            this.playerCard2.setOpacity(1);
            this.playerCard3.setOpacity(1);
            this.playerCard4.setOpacity(1);
            this.playerCard5.setOpacity(1);
            
            this.setCardPictures();
            
            this.playerArray[0].setAllSelectedCards();
            
            this.gameMaster.setplayerChangedCard(true);
        }
    }
    @FXML
    private void increaseBetButtonAction(ActionEvent event){
    
        if(this.betTextField.getText().equals("0")){
        
            this.betTextField.setText("100");
        }else
        this.betTextField.setText(Integer.toString(
                                  Integer.parseInt(this.betTextField.getText()) + 100
        ));
    }
    @FXML
    private void decreseBetButtonAction(ActionEvent event){
    
        if(!this.betTextField.getText().equals(""))
            if( Integer.parseInt(this.betTextField.getText()) >= 100 ){
                this.betTextField.setText(Integer.toString(
                                Integer.parseInt(this.betTextField.getText()) - 100
                ));
            }else
                this.betTextField.setText("0");
    }

    private void endTurn(){
        String calculatedHandString = this.gameMaster.getCalculatedStrengthAsString(this.playerArray[0].getHand());
        
        if(this.gameMaster.getFlopped(1) == true){
            this.playerArray[1].incrementCredit(this.playerArray[0].getBet() + this.playerArray[1].getBet());
            this.gameMaster.insertData(0, calculatedHandString , this.playerArray[0].getBet() + this.playerArray[1].getBet());
            
            this.newTurn();
            return;
        }
        if(this.gameMaster.getFlopped(2) == true){
            this.playerArray[0].incrementCredit(this.playerArray[0].getBet() + this.playerArray[1].getBet());
            this.gameMaster.insertData(1, calculatedHandString, this.playerArray[0].getBet() + this.playerArray[1].getBet());
            
            this.newTurn();
            return;
        }
        
        int winner = this.gameMaster.getWinner(this.playerArray[0].getHand(), this.playerArray[1].getHand());
        
        switch(winner){
        
            case 1:
                if(this.playerArray[1].getCredit()<=0){
                    this.playerArray[0].incrementCredit(this.playerArray[0].getBet()+ this.playerArray[1].getBet());
                    this.gameMaster.insertData(1, calculatedHandString, this.playerArray[0].getBet() + this.playerArray[1].getBet());
                    
                    this.victory();
                    return;
                }
                if(this.playerArray[0].getBet() < this.gameMaster.getMaxBet()){
                    this.playerArray[0].incrementCredit(this.playerArray[0].getBet()*2);
                    this.playerArray[1].incrementCredit(this.gameMaster.getMaxBet() - this.playerArray[0].getBet());
                    this.gameMaster.insertData(1, calculatedHandString, this.playerArray[0].getBet()*2);
                    
                    this.newTurn();
                    return;
                }else{
                    this.playerArray[0].incrementCredit(this.playerArray[0].getBet()+ this.playerArray[1].getBet());
                    this.gameMaster.insertData(1, calculatedHandString, this.playerArray[0].getBet() + this.playerArray[1].getBet());
                    
                    this.newTurn();
                    return;
                }
            case 2:
                if(this.playerArray[0].getCredit()<=0){
                    this.playerArray[1].incrementCredit(this.playerArray[0].getBet()+ this.playerArray[1].getBet());
                    this.gameMaster.insertData(0, calculatedHandString, this.playerArray[0].getBet() + this.playerArray[1].getBet());
                    this.defeat();
                    return;
                }
                if(this.playerArray[1].getBet() < this.gameMaster.getMaxBet()){
                    this.playerArray[1].incrementCredit(this.playerArray[1].getBet()*2);
                    this.playerArray[0].incrementCredit(this.gameMaster.getMaxBet() - this.playerArray[1].getBet());
                    this.gameMaster.insertData(0, calculatedHandString, this.playerArray[1].getBet()*2);
                    
                    this.newTurn();
                    return;
                }else{
                    this.playerArray[1].incrementCredit(this.playerArray[0].getBet()+ this.playerArray[1].getBet());
                    this.gameMaster.insertData(0, calculatedHandString, this.playerArray[0].getBet() + this.playerArray[1].getBet());
                    
                    this.newTurn();
                    return;
                }
            case 3:
                    this.playerArray[0].incrementCredit(this.playerArray[0].getBet());
                    this.playerArray[1].incrementCredit(this.playerArray[1].getBet());
                    this.gameMaster.insertData(2, calculatedHandString, this.playerArray[0].getBet() + this.playerArray[1].getBet());
                    
                    this.newTurn();
        }
    }
    private void newTurn(){

        this.playerArray[0].setBet(100);
        this.playerArray[0].decrementCredit(100);
        this.playerArray[1].setBet(200);
        this.playerArray[1].decrementCredit(200);
        this.gameMaster.setMaxBet(200);
        this.gameMaster.setFlopped(1, false);
        this.gameMaster.setFlopped(2, false);
        this.gameMaster.setplayerChangedCard(false);
    
        this.dealer.deal(playerArray);
        
        this.setCardPictures();
        this.displayInterface();
        
        this.playerCard1.setDisable(false);
        this.playerCard2.setDisable(false);
        this.playerCard3.setDisable(false);
        this.playerCard4.setDisable(false);
        this.playerCard5.setDisable(false);
        
        this.textArea.appendText("\nPlease make your bet!");
    }
    
    private void defeat(){
    
        this.textArea.appendText("\nVereség!!!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/GameOver.fxml"));
            Parent root = fxmlLoader.load();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/gameover.css");
            
            Stage stage = (Stage)(this.aiBet.getScene().getWindow());
            
            stage.setTitle("Defeat!");
            stage.setScene(scene);
            fxmlLoader.<GameOverController>getController().setBackGround("Defeat!");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void victory(){
    
        this.textArea.appendText("\nGyőzelem!!!");

        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/GameOver.fxml"));
            Parent root = fxmlloader.load();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/gameover.css");
            
            Stage stage = (Stage)(this.aiBet.getScene().getWindow());
            
            stage.setTitle("Victory!");
            stage.setScene(scene);
            fxmlloader.<GameOverController>getController().setBackGround("Victory!");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setCardPictures(){
        
        try{
            searchCardPictures(this.playerArray[0].getHand(), 1);
            
            this.textArea.appendText("Your new hand:");
            this.textArea.appendText(Arrays.toString(this.playerArray[0].getHand()));
            
            searchCardPictures(this.playerArray[1].getHand(), 2);
        }catch(MalformedURLException ex){
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, "Setting images has failed!", ex);
        }
    }
    private void searchCardPictures(String[] playerHand, int player)throws MalformedURLException{
    
        String choosable;

        for(int i = 0; i < 5; ++i){
            
            choosable = playerHand[i];
            
            if(choosable.startsWith("c")){
            switch (choosable){
            
                
                case "clubs2" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/clubs2.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "clubs3" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/clubs3.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "clubs4" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/clubs4.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "clubs5" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/clubs5.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "clubs6" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/clubs6.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "clubs7" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/clubs7.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "clubs8" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/clubs8.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "clubs9" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/clubs9.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "clubs10": cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/clubs10.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "clubsJ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/clubsJ.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "clubsQ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/clubsQ.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "clubsK" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/clubsK.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "clubsA" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/clubsA.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
             
                default: LOGGER.log(Level.SEVERE, "Huston! We've got a problem here!");
            }
                continue;
            }
            
            if(choosable.startsWith("d")){
            switch (choosable){
                case "diamonds2" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/diamonds2.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "diamonds3" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/diamonds3.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "diamonds4" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/diamonds4.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "diamonds5" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/diamonds5.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "diamonds6" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/diamonds6.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "diamonds7" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/diamonds7.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "diamonds8" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/diamonds8.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "diamonds9" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/diamonds9.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "diamonds10": cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/diamonds10.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "diamondsJ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/diamondsJ.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "diamondsQ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/diamondsQ.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "diamondsK" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/diamondsK.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "diamondsA" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/diamondsA.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
            
                default: LOGGER.log(Level.SEVERE, "Huston! We've got a problem here!");
            }
                continue;
            }
            
            if(choosable.startsWith("h")){
            switch (choosable){
                case "hearts2" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/hearts2.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "hearts3" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/hearts3.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "hearts4" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/hearts4.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "hearts5" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/hearts5.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "hearts6" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/hearts6.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "hearts7" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/hearts7.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "hearts8" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/hearts8.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "hearts9" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/hearts9.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "hearts10": cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/hearts10.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "heartsJ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/heartsJ.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "heartsQ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/heartsQ.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "heartsK" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/heartsK.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "heartsA" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/heartsA.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                
                default: LOGGER.log(Level.SEVERE, "Huston! We've got a problem here!");
            }
                continue;
            }
            if(choosable.startsWith("s")){
            switch (choosable){
                case "spades2" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/spades2.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "spades3" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/spades3.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "spades4" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/spades4.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "spades5" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/spades5.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "spades6" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/spades6.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "spades7" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/spades7.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "spades8" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/spades8.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "spades9" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/spades9.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "spades10": cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/spades10.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "spadesJ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/spadesJ.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "spadesQ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/spadesQ.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "spadesK" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/spadesK.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                case "spadesA" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/spadesA.png").toURI().toURL().toExternalForm()),null,null,null,null)));break;
                
                default: LOGGER.log(Level.SEVERE, "Huston! We've got a problem here!");
            }
            }
        }

    }
    private void displayInterface(){
    
        this.aiCredit.setText(Integer.toString(this.playerArray[1].getCredit()));
        this.aiBet.setText(Integer.toString(this.playerArray[1].getBet()));
        this.playerCredit.setText(Integer.toString(this.playerArray[0].getCredit()));
        this.playerBet.setText(Integer.toString(this.playerArray[0].getBet()));
        this.betTextField.setText("0");
    }
    public void afterInitialize(){
    
        Stage st = (Stage)this.aiBet.getScene().getWindow();
        st.setOnCloseRequest(e->Platform.exit());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.playerArray[0] = new Player();
        this.playerArray[1] = new Player();
        
        this.cardPicturesArray[0][0] = this.playerCard1;
        this.cardPicturesArray[0][1] = this.playerCard2;
        this.cardPicturesArray[0][2] = this.playerCard3;
        this.cardPicturesArray[0][3] = this.playerCard4;
        this.cardPicturesArray[0][4] = this.playerCard5;
        
        this.cardPicturesArray[1][0] = this.aiCard1;
        this.cardPicturesArray[1][1] = this.aiCard2;
        this.cardPicturesArray[1][2] = this.aiCard3;
        this.cardPicturesArray[1][3] = this.aiCard4;
        this.cardPicturesArray[1][4] = this.aiCard5;
        
        this.dealer.deal(playerArray);
        
        this.playerArray[0].setBet(100);
        this.playerArray[0].decrementCredit(100);
        this.playerArray[1].setBet(200);
        this.playerArray[1].decrementCredit(200);
        
        this.setCardPictures();
        
        this.displayInterface();
        
        try {
            this.anchorPane.setBackground(new Background(new BackgroundImage(new Image(new File("./src/main/resources/pictures/welcomeMenuBackGround.png").toURI().toURL().toExternalForm()),null,null,null,null)));
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.textArea.setOpacity(0.8);
        this.textArea.appendText("\nPlese make your bet!");

    }    
}
