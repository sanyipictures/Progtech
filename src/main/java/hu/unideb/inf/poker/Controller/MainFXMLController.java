package hu.unideb.inf.poker.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hu.unideb.inf.poker.Modell.Player;
import hu.unideb.inf.poker.Modell.GameMaster;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;

public class MainFXMLController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainFXMLController.class.getName());

    private final GameMaster gameMaster = new GameMaster();
    
    private final Button[][] cardPicturesArray = new Button[2][5];
    
    @FXML
    private AnchorPane anchorPane;
       
    @FXML
    private Label playerCredit;
    @FXML
    private Label playerCreditHintLabel;
    @FXML
    private Label aiCredit;
    @FXML
    private Label aiCreditHintLabel;
    @FXML
    private Label playerBet;
    @FXML
    private Label aiBet;
    @FXML
    private Label communicationLabel;
    
    @FXML
    private Label playerCard1Label;
    @FXML
    private Label playerCard2Label;
    @FXML
    private Label playerCard3Label;
    @FXML
    private Label playerCard4Label;
    @FXML
    private Label playerCard5Label;
    
    @FXML
    private Label aiCard1Label;
    @FXML
    private Label aiCard2Label;
    @FXML
    private Label aiCard3Label;
    @FXML
    private Label aiCard4Label;
    @FXML
    private Label aiCard5Label;
    
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
    private Button qiutButton;
    
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
    private void quitButtonAction(ActionEvent event){
        this.gameMaster.closeTransaction();
        Platform.exit();
    }
    
    @FXML
    private void cardClickedAction(ActionEvent event){
    
        if(this.gameMaster.getPlayerChangedCard()){return;}
        
        String cardId = ((Button)event.getSource()).getId();
        
        switch(cardId){
        
            case "playerCard1" : 
                        this.gameMaster.getPlayerArray()[0].setSelectedCards(1);
                        if(this.gameMaster.getPlayerArray()[0].getSelectedCard(1)){
                            this.playerCard1.setOpacity(0.5);
                        }
                        else{    
                            this.playerCard1.setOpacity(1);
                        }break;
            case "playerCard2" :
                        this.gameMaster.getPlayerArray()[0].setSelectedCards(2);
                        if(this.gameMaster.getPlayerArray()[0].getSelectedCard(2)){
                            this.playerCard2.setOpacity(0.5);
                        }
                        else{    
                            this.playerCard2.setOpacity(1);
                        }break;
            case "playerCard3" :
                        this.gameMaster.getPlayerArray()[0].setSelectedCards(3);
                        if(this.gameMaster.getPlayerArray()[0].getSelectedCard(3)){
                            this.playerCard3.setOpacity(0.5);
                        }
                        else{    
                            this.playerCard3.setOpacity(1);
                        }break;
            case "playerCard4" :
                        this.gameMaster.getPlayerArray()[0].setSelectedCards(4);
                        if(this.gameMaster.getPlayerArray()[0].getSelectedCard(4)){
                            this.playerCard4.setOpacity(0.5);
                        }
                        else{    
                            this.playerCard4.setOpacity(1);
                        }break;
            case "playerCard5" :
                        this.gameMaster.getPlayerArray()[0].setSelectedCards(5);
                        if(this.gameMaster.getPlayerArray()[0].getSelectedCard(5)){
                            this.playerCard5.setOpacity(0.5);
                        }
                        else{    
                            this.playerCard5.setOpacity(1);
                        }break;
        }
    }
    
    @FXML
    private void throwButtonAction(ActionEvent event){
    
        this.gameMaster.setFlopped(1, true);
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                            this.playerCreditHintLabel.setVisible(true);
                            this.communicationLabel.setVisible(true);
                            this.aiCreditHintLabel.setVisible(true);
                            this.communicationLabel.setText("You have flopped your cards!");
                            this.playerCreditHintLabel.setText("-" + Integer.toString(this.gameMaster.getPlayerArray()[0].getBet()));
                            this.aiCreditHintLabel.setText("+" + Integer.toString(
                                            this.gameMaster.getPlayerArray()[0].getBet() + this.gameMaster.getPlayerArray()[1].getBet())); 
                            this.searchCardPictures(this.gameMaster.getPlayerArray()[1].getHand(), 2);
                            this.aiCard1Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[0]);
                            this.aiCard2Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[1]);
                            this.aiCard3Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[2]);
                            this.aiCard4Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[3]);
                            this.aiCard5Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[4]);
                            }),
                            new KeyFrame(Duration.seconds(6.0), e -> this.communicationLabel.setText("You lost this turn!")),
                            new KeyFrame(Duration.seconds(9.0), e -> {
                            this.playerCreditHintLabel.setVisible(false);
                            this.communicationLabel.setVisible(false);
                            this.aiCreditHintLabel.setVisible(false);
                            this.endTurn();
                            }));
        timeline.play();
        
        LOGGER.info( " 204 Player has flopped cards!");
        
    }
    
    @FXML
    private void betButtonAction(ActionEvent event){
        if(this.gameMaster.getPlayerAllIn(1)){
        
            LOGGER.info(" 212 player is in all in, so returns.");
            return;
        
        }
        int betAmount;
        
        try{
            betAmount = Integer.parseInt(this.betTextField.getText());
        }catch(NumberFormatException exception){
            LOGGER.error("221 Player has entered invalid number!", exception);
            
            Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                this.communicationLabel.setVisible(true);
                                this.communicationLabel.setText("You must enter numeric value!");
                                }),
                                new KeyFrame(Duration.seconds(3.0), e -> this.communicationLabel.setVisible(false))
            );
            timeline1.play();
            LOGGER.info(" 230 after timeline1.play called");
            return;
        }
        if(betAmount > this.gameMaster.getPlayerArray()[0].getCredit()){betAmount = this.gameMaster.getPlayerArray()[0].getCredit();}
        
        if(betAmount < this.gameMaster.getMinBet()){
            if(this.gameMaster.getPlayerArray()[0].getCredit() < this.gameMaster.getMinBet()){
                LOGGER.info( "237 Player has put all in!");

                Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                        this.communicationLabel.setVisible(true);
                                        this.playerCreditHintLabel.setText("-"+Integer.toString(this.gameMaster.getPlayerArray()[0].getCredit()));
                                        this.communicationLabel.setText("You have put all in!");
                                        this.betTextField.setText("0");
                                    }),
                                        new KeyFrame(Duration.seconds(3.0), e -> {
                                            this.communicationLabel.setVisible(false);
                                            this.playerCreditHintLabel.setVisible(false);
                                            
                                            this.gameMaster.setPlayerAllIn(1, true);
                                            this.gameMaster.getPlayerArray()[0].incrementBet(this.gameMaster.getPlayerArray()[0].getCredit());
                                            this.gameMaster.getPlayerArray()[0].setCredit(0);
                                    }));                
                timeline2.play();
                timeline2.setOnFinished(e -> {
                    LOGGER.info("255 all in, and timeline2 has ended.");
                        this.displayInterface();
                        this.gameMaster.getAI().aiTurn(this.gameMaster.getPlayerArray()[1], this.gameMaster.getDealer(), gameMaster);
                        LOGGER.info("258 ai turn ended");
                });
                
                
                if(gameMaster.getFlopped(2)){
                    LOGGER.info("263 ai ha flopped!");
                    Timeline timeline3 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                            this.communicationLabel.setVisible(true);
                                            this.playerCreditHintLabel.setVisible(true);
                                            this.communicationLabel.setText("AI has flopped it's cards!");
                                            this.playerCreditHintLabel.setText("+"+Integer.toString(this.gameMaster.getPlayerArray()[0].getBet()+this.gameMaster.getPlayerArray()[1].getBet()));
                                            this.aiCreditHintLabel.setText("-"+Integer.toString(this.gameMaster.getPlayerArray()[0].getBet()+this.gameMaster.getPlayerArray()[1].getBet()));
                                            this.searchCardPictures(this.gameMaster.getPlayerArray()[1].getHand(), 2);
                                            this.aiCard1Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[0]);
                                            this.aiCard2Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[1]);
                                            this.aiCard3Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[2]);
                                            this.aiCard4Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[3]);
                                            this.aiCard5Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[4]);
                                            }),
                                            new KeyFrame(Duration.seconds(6.0), e -> this.communicationLabel.setText("You win this turn!")),
                                            new KeyFrame(Duration.seconds(9.0), e -> {
                                            this.playerCreditHintLabel.setVisible(false);
                                            this.communicationLabel.setVisible(false);
                                            this.aiCreditHintLabel.setVisible(false);
                                            
                                            })                
                    );
                    timeline3.play();
                    timeline3.setOnFinished(e -> this.endTurn());
                    LOGGER.info("287 timeline3 ended");
                    return;
                }
                Timeline timeline4 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                            this.communicationLabel.setVisible(true);
                                            this.communicationLabel.setText("AI holds your bet!");
                                            this.aiCreditHintLabel.setText("-"+Integer.toString(this.gameMaster.getMaxBet()-this.gameMaster.getPlayerArray()[1].getBet()));
                                            }),
                                            new KeyFrame(Duration.seconds(6.0), e -> this.communicationLabel.setText("Press check to end turn!")),
                                            new KeyFrame(Duration.seconds(9.0), e -> {
                                            this.communicationLabel.setVisible(false);
                                            this.aiCreditHintLabel.setVisible(false);
                                            this.displayInterface();
                                            })                
                    );
                timeline4.play();
                
                return;
            }
            LOGGER.info("306 Player tries to bet below minimum amount!");
            Timeline timeline5 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> this.communicationLabel.setVisible(true)),
                                             new KeyFrame(Duration.seconds(0.0), e -> this.communicationLabel.setText("The minimum bet is 200! Increase your bet!!!")),
                                             new KeyFrame(Duration.seconds(3.0), e -> this.communicationLabel.setVisible(false)));
            timeline5.play();
            return;
        }
        
        if( (betAmount + this.gameMaster.getPlayerArray()[0].getBet()) >= this.gameMaster.getMaxBet()){
            if( betAmount > this.gameMaster.getPlayerArray()[0].getCredit() ){
                
                LOGGER.info("317 Player has put all in!");
                Timeline timeline6 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                        this.communicationLabel.setVisible(true);
                                        this.playerCreditHintLabel.setText("-"+Integer.toString(this.gameMaster.getPlayerArray()[0].getCredit()));
                                        this.communicationLabel.setText("You have put all in!");
                                        this.betTextField.setText("0");
                                        
                                    }),
                                        new KeyFrame(Duration.seconds(3.0), e -> {
                                            this.communicationLabel.setVisible(false);
                                            this.playerCreditHintLabel.setVisible(false);
                                            
                                            this.gameMaster.setPlayerAllIn(1, true);
                                            this.gameMaster.getPlayerArray()[0].incrementBet(this.gameMaster.getPlayerArray()[0].getCredit());
                                            this.gameMaster.getPlayerArray()[0].setCredit(0);
                                            this.displayInterface();
                                            
                                    }));                
                timeline6.play();
                LOGGER.info("336 timeline6 called, interface updates with all in actions, and we are waiting timeline6 to finish");
                timeline6.setOnFinished(e -> this.gameMaster.getAI().aiTurn(this.gameMaster.getPlayerArray()[1], this.gameMaster.getDealer(), gameMaster));
                LOGGER.info("338 timeline6 called the ai turn, and now we decide, if it flopped or not");
                
                if(gameMaster.getFlopped(2)){
                    LOGGER.info("341 yes, it flopped, now we shows the ai cards, and ends the turn after that");
                    Timeline timeline7 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                            this.communicationLabel.setVisible(true);
                                            this.playerCreditHintLabel.setVisible(true);
                                            this.communicationLabel.setText("AI has flopped it's cards!");
                                            this.playerCreditHintLabel.setText("+"+Integer.toString(this.gameMaster.getPlayerArray()[0].getBet()+this.gameMaster.getPlayerArray()[1].getBet()));
                                            this.aiCreditHintLabel.setText("-"+Integer.toString(this.gameMaster.getPlayerArray()[0].getBet()+this.gameMaster.getPlayerArray()[1].getBet()));
                                            this.searchCardPictures(this.gameMaster.getPlayerArray()[1].getHand(), 2);
                                            this.aiCard1Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[0]);
                                            this.aiCard2Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[1]);
                                            this.aiCard3Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[2]);
                                            this.aiCard4Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[3]);
                                            this.aiCard5Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[4]);
                                            }),
                                            new KeyFrame(Duration.seconds(6.0), e -> this.communicationLabel.setText("You win this turn!")),
                                            new KeyFrame(Duration.seconds(9.0), e -> {
                                            this.playerCreditHintLabel.setVisible(false);
                                            this.communicationLabel.setVisible(false);
                                            this.aiCreditHintLabel.setVisible(false);
                                            
                                            })                
                    );
                    timeline7.play(); 
                    LOGGER.info("364 timeline7 starts, lets see what happens after that!");
                    timeline7.setOnFinished(e -> {
                        LOGGER.info("363 timeline7 finished, we try to call the end turn method");
                        this.endTurn();
                    });
                    
                    
                }
                LOGGER.info("372 nope, it doesn't flops, it still has the courage to fight! we shows the interface changes");
                Timeline timeline8 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                            this.communicationLabel.setVisible(true);
                                            this.communicationLabel.setText("AI holds your bet!");
                                            this.aiCreditHintLabel.setText("-"+Integer.toString(this.gameMaster.getMaxBet()-this.gameMaster.getPlayerArray()[1].getBet()));
                                            }),
                                            new KeyFrame(Duration.seconds(6.0), e -> {
                                                this.communicationLabel.setText("Press check to end turn!");
                                                this.displayInterface();
                                            }),
                                            new KeyFrame(Duration.seconds(9.0), e -> {
                                            
                                            this.communicationLabel.setVisible(false);
                                            this.aiCreditHintLabel.setVisible(false);
                                            
                                            })                
                    );
                timeline8.play();
                LOGGER.info("382 timeline8 called, and we are waiting for things to happen, especialy to return from this method!");
                
            }else{
                LOGGER.info("393 so we made our bet over the current max bet!");
                String betString = Integer.toString(betAmount);
                this.gameMaster.getPlayerArray()[0].incrementBet(betAmount);
                this.gameMaster.setMaxBet(this.gameMaster.getPlayerArray()[0].getBet());
                this.gameMaster.getPlayerArray()[0].decrementCredit(betAmount);
                Timeline timeline9 = new Timeline(
                                        new KeyFrame(Duration.seconds(0.0), e -> {
                                        this.communicationLabel.setVisible(true);
                                        this.playerCreditHintLabel.setVisible(true);
                                        this.communicationLabel.setText("You have incresed bet!");
                                        this.playerCreditHintLabel.setText("-" + betString);
                                        }),
                                        new KeyFrame(Duration.seconds(3.0), e ->{
                                        this.communicationLabel.setVisible(false);
                                        this.playerCreditHintLabel.setVisible(false);
                                        this.displayInterface();
                                        
                                        })
                );
                timeline9.play();
                LOGGER.info("413 timeline9 has started, we communicate to the player, what's the situation");
                timeline9.setOnFinished(e -> this.gameMaster.getAI().aiTurn(this.gameMaster.getPlayerArray()[1], this.gameMaster.getDealer(), gameMaster));
                LOGGER.info("415 timeline9 ended, and we hopefully called ai turn");
                
                if(gameMaster.getFlopped(2)){
                    LOGGER.info("418 yes we called, and we think it has flopped!");
                    Timeline timeline10 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                            this.communicationLabel.setVisible(true);
                                            this.playerCreditHintLabel.setVisible(true);
                                            this.communicationLabel.setText("AI has flopped it's cards!");
                                            this.playerCreditHintLabel.setText("+"+Integer.toString(this.gameMaster.getPlayerArray()[0].getBet()+this.gameMaster.getPlayerArray()[1].getBet()));
                                            this.searchCardPictures(this.gameMaster.getPlayerArray()[1].getHand(), 2);
                                            this.aiCard1Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[0]);
                                            this.aiCard2Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[1]);
                                            this.aiCard3Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[2]);
                                            this.aiCard4Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[3]);
                                            this.aiCard5Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[4]);
                                            }),
                                            new KeyFrame(Duration.seconds(6.0), e -> this.communicationLabel.setText("You win this turn!")),
                                            new KeyFrame(Duration.seconds(9.0), e -> {
                                            this.playerCreditHintLabel.setVisible(false);
                                            this.communicationLabel.setVisible(false);
                                            this.aiCreditHintLabel.setVisible(false);
                                            
                                            })                
                    );
                    timeline10.play(); 
                    LOGGER.info("440 timeline10 has called, and we shows what's going on at ai's place");
                    timeline10.setOnFinished(e -> {
                        LOGGER.info("429 we showed everything, so we ends the turn!");
                        this.endTurn();
                        
                    });
                    LOGGER.info("433 end turn called, we are after timeline10, waiting for return!");
                    return;
                }
                LOGGER.info("there is no flopping, so we just communicate the ai's fighting potential");
                Timeline timeline11 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                            this.communicationLabel.setVisible(true);
                                            this.communicationLabel.setText("AI holds your bet!");
                                            }),
                                            new KeyFrame(Duration.seconds(6.0), e -> {
                                                this.communicationLabel.setText("Press check to end turn or increase your bet!");
                                                this.displayInterface();
                                            }),
                                            new KeyFrame(Duration.seconds(9.0), e -> {
                                            this.communicationLabel.setVisible(false);
                                            
                                            })                
                    );
                timeline11.play();
                LOGGER.info("447 timeline11 play called and what's happening now? please return and don't go away!");
            }
        }
    }
    
    @FXML
    private void checkButtonAction(ActionEvent event){
        if(this.gameMaster.getPlayerArray()[0].getBet() == 0 || this.gameMaster.getPlayerArray()[0].getBet() < this.gameMaster.getMinBet()){
            Timeline timeline12 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> this.communicationLabel.setVisible(true)),
                                             new KeyFrame(Duration.seconds(0.0), e -> this.communicationLabel.setText("You can not check without any bet!")),
                                             new KeyFrame(Duration.seconds(3.0), e -> this.communicationLabel.setVisible(false)));
            timeline12.play();
            LOGGER.info("459 can not check sceduled waiting to return!");
            return;
        }
        
        if(this.gameMaster.getPlayerArray()[0].getBet() == this.gameMaster.getPlayerArray()[1].getBet()){
            LOGGER.info( "464 Player has checked!");
            Timeline timeline13 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                this.communicationLabel.setVisible(true);
                                this.communicationLabel.setText("You have checked!!");
            }),
                    new KeyFrame(Duration.seconds(3.0), e -> {
                        this.communicationLabel.setVisible(false);
                        this.endTurn();
                        
                    })
            );
            timeline13.play();
            return;
            
        } 
        if(this.gameMaster.getPlayerArray()[0].getBet() < this.gameMaster.getPlayerArray()[1].getBet()){
            if(this.gameMaster.getPlayerAllIn(1)){
                LOGGER.info("481 Player has checked, becouse we are in all in");
            Timeline timeline14 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                this.communicationLabel.setVisible(true);
                                this.communicationLabel.setText("You have checked!!");
            }),
                    new KeyFrame(Duration.seconds(3.0), e -> {
                        this.communicationLabel.setVisible(false);
                        
                    })
            );
            timeline14.play();
            LOGGER.info("492 timeline ended hopefully,so wait to end turn! ");
            timeline14.setOnFinished(e -> this.endTurn());
            return;
            }
            Timeline timeline15 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> this.communicationLabel.setVisible(true)),
                                             new KeyFrame(Duration.seconds(0.0), e -> this.communicationLabel.setText("You can not check without any bet!")),
                                             new KeyFrame(Duration.seconds(3.0), e -> this.communicationLabel.setVisible(false)));
            timeline15.play();
            LOGGER.info("500 timeline15 playes, end after this we shuold return!");
        } 
   
    }
    
    @FXML
    private void changeButtonAction(ActionEvent event){
        if(!this.gameMaster.getPlayerChangedCard()){
            boolean[] selectedCards = this.gameMaster.getPlayerArray()[0].getSelectedCards();

            if(!selectedCards[0] && !selectedCards[1] && !selectedCards[2] && !selectedCards[3] && !selectedCards[4]){
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> this.communicationLabel.setVisible(true)),
                                             new KeyFrame(Duration.seconds(0.0), e -> this.communicationLabel.setText("You have tried to changed cards, without selecting one!")),
                                             new KeyFrame(Duration.seconds(3.0), e -> this.communicationLabel.setVisible(false)));
            timeline.play();                
                this.displayInterface();
                return;
            }
            for(int i = 0; i < 5; ++i){

                if(selectedCards[i]){
                    this.gameMaster.getDealer().dealToPlayer(this.gameMaster.getPlayerArray()[0], i);
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> this.communicationLabel.setVisible(true)),
                                             new KeyFrame(Duration.seconds(0.0), e -> this.communicationLabel.setText("You have changed cards!")),
                                             new KeyFrame(Duration.seconds(3.0), e -> this.communicationLabel.setVisible(false)));
                    timeline.play(); 
                    this.displayInterface();
                }
            }
            this.playerCard1.setOpacity(1);
            this.playerCard2.setOpacity(1);
            this.playerCard3.setOpacity(1);
            this.playerCard4.setOpacity(1);
            this.playerCard5.setOpacity(1);
            
            this.setCardPictures();
            
            this.gameMaster.getPlayerArray()[0].setAllSelectedCards();
            
            this.gameMaster.setplayerChangedCard(true);
        }
    }
    @FXML
    private void increaseBetButtonAction(ActionEvent event){
        if(this.betTextField.getText().equals("")){this.betTextField.setText("0");}
        
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
    /**
     * Makes the nececcary steps after both player checks.
     */
    private void endTurn(){
        LOGGER.info("565 end turn called!");
        String calculatedHandString = this.gameMaster.getCalculatedStrengthAsString(this.gameMaster.getPlayerArray()[0].getHand());
        
        if(this.gameMaster.getFlopped(1) == true){
            if(this.gameMaster.getPlayerAllIn(1)){
                this.gameMaster.setFlopped(1, false);
                this.defeat();
                return;
            }else{
                this.gameMaster.getPlayerArray()[1].incrementCredit(this.gameMaster.getPlayerArray()[0].getBet() + this.gameMaster.getPlayerArray()[1].getBet());
                this.gameMaster.insertData(0, calculatedHandString , this.gameMaster.getPlayerArray()[0].getBet() + this.gameMaster.getPlayerArray()[1].getBet());
                LOGGER.info("575 flopped card, and increase enemy credit, and call new turn.");
                this.newTurn();
                LOGGER.info("578 new turn ended, we are before return statement");
                return;
            }
        }
        if(this.gameMaster.getFlopped(2) == true){
            if(this.gameMaster.getPlayerAllIn(2)){
                this.gameMaster.setFlopped(2, false);
                this.victory();
                return;
            }else{

                this.gameMaster.getPlayerArray()[0].incrementCredit(this.gameMaster.getPlayerArray()[0].getBet() + this.gameMaster.getPlayerArray()[1].getBet());
                this.gameMaster.insertData(1, calculatedHandString, this.gameMaster.getPlayerArray()[0].getBet() + this.gameMaster.getPlayerArray()[1].getBet());
                LOGGER.info("591 ai flopped card, and increase player's credit, and call new turn.");
                this.newTurn();
                LOGGER.info("593 new turn ended, we are before return statement");
                return;
            }
        }
        
        int winner = this.gameMaster.getWinner(this.gameMaster.getPlayerArray()[0].getHand(), this.gameMaster.getPlayerArray()[1].getHand());
        
        switch(winner){
        
            case 1:
                if(this.gameMaster.getPlayerArray()[1].getCredit()<=0){
                    this.gameMaster.getPlayerArray()[0].incrementCredit(this.gameMaster.getPlayerArray()[0].getBet()+ this.gameMaster.getPlayerArray()[1].getBet());
                    this.gameMaster.insertData(1, calculatedHandString, this.gameMaster.getPlayerArray()[0].getBet() + this.gameMaster.getPlayerArray()[1].getBet());
                    
                    this.victory();
                    return;
                }
                if(this.gameMaster.getPlayerArray()[0].getBet() < this.gameMaster.getMaxBet()){
                    Timeline timeline16 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                                                                this.communicationLabel.setVisible(true);
                                                                                this.playerCreditHintLabel.setVisible(true);
                                                                                this.communicationLabel.setText("You won this turn!");
                                                                                this.playerCreditHintLabel.setText("+"+ Integer.toString(this.gameMaster.getPlayerArray()[0].getBet()*2));
                
                                                                                this.searchCardPictures(this.gameMaster.getPlayerArray()[1].getHand(), 2);
                                                                                this.aiCard1Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[0]);
                                                                                this.aiCard2Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[1]);
                                                                                this.aiCard3Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[2]);
                                                                                this.aiCard4Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[3]);
                                                                                this.aiCard5Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[4]);
                                                                         }),
                                                            new KeyFrame(Duration.seconds(6.0), e-> {
                                                                                this.communicationLabel.setVisible(false);
                                                                                this.playerCreditHintLabel.setVisible(false);
                                                                                
                                                                                
                                                                                
                                                                         })
                            ); 
                    timeline16.play();
                    LOGGER.info("654 timeline16 called, so we are in allin, and we won!");
                    timeline16.setOnFinished(e -> {
                        this.gameMaster.getPlayerArray()[0].incrementCredit(this.gameMaster.getPlayerArray()[0].getBet()*2);
                        this.gameMaster.getPlayerArray()[1].incrementCredit(this.gameMaster.getMaxBet() - this.gameMaster.getPlayerArray()[0].getBet());
                        this.gameMaster.insertData(1, calculatedHandString, this.gameMaster.getPlayerArray()[0].getBet()*2);
                        
                        this.newTurn();
                    
                    });
                    LOGGER.info("641 new turn ended in timeline, we are before return statement");
                    return;
                }else{
                Timeline timeline17 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                    this.communicationLabel.setVisible(true);
                                    this.playerCreditHintLabel.setVisible(true);
                                    this.communicationLabel.setText("You won this turn!");
                                    this.playerCreditHintLabel.setText("+"+ Integer.toString(this.gameMaster.getPlayerArray()[0].getBet()+ this.gameMaster.getPlayerArray()[1].getBet()));
                                  
                                    this.searchCardPictures(this.gameMaster.getPlayerArray()[1].getHand(), 2);
                                    this.aiCard1Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[0]);
                                    this.aiCard2Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[1]);
                                    this.aiCard3Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[2]);
                                    this.aiCard4Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[3]);
                                    this.aiCard5Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[4]);
                                                                        }),
                                    new KeyFrame(Duration.seconds(6.0), e-> {
                                    this.communicationLabel.setVisible(false);
                                    this.playerCreditHintLabel.setVisible(false); 
                                    
                                    
                                    })
                            ); 
                    timeline17.play();
                    LOGGER.info("687 we won as we should do it, and we started the timeline7");
                    timeline17.setOnFinished(e -> {
                        this.gameMaster.getPlayerArray()[0].incrementCredit(this.gameMaster.getPlayerArray()[0].getBet()+ this.gameMaster.getPlayerArray()[1].getBet());
                        this.gameMaster.insertData(1, calculatedHandString, this.gameMaster.getPlayerArray()[0].getBet() + this.gameMaster.getPlayerArray()[1].getBet());
                        this.newTurn();
                    });
                    LOGGER.info("670 new turn ended in timeline17, we are before return statement");
                    return;
                }
            case 2:
                if(this.gameMaster.getPlayerArray()[0].getCredit()<=0){
                    this.gameMaster.getPlayerArray()[1].incrementCredit(this.gameMaster.getPlayerArray()[0].getBet()+ this.gameMaster.getPlayerArray()[1].getBet());
                    this.gameMaster.insertData(0, calculatedHandString, this.gameMaster.getPlayerArray()[0].getBet() + this.gameMaster.getPlayerArray()[1].getBet());
                    this.defeat();
                    return;
                }
                if(this.gameMaster.getPlayerArray()[1].getBet() < this.gameMaster.getMaxBet()){
                    Timeline timeline18 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                                                    this.communicationLabel.setVisible(true);
                                                                    this.aiCreditHintLabel.setVisible(true);
                                                                    this.communicationLabel.setText("You have lost this turn!");
                                                                    this.aiCreditHintLabel.setText("+"+ Integer.toString(this.gameMaster.getPlayerArray()[1].getBet()*2));
                                                                    
                                                                    
                                                                    this.searchCardPictures(this.gameMaster.getPlayerArray()[1].getHand(), 2);
                                                                    this.aiCard1Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[0]);
                                                                    this.aiCard2Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[1]);
                                                                    this.aiCard3Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[2]);
                                                                    this.aiCard4Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[3]);
                                                                    this.aiCard5Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[4]);
                                                             }),
                                                new KeyFrame(Duration.seconds(6.0), e-> {
                                                                    this.communicationLabel.setVisible(false);
                                                                    this.aiCreditHintLabel.setVisible(false);
                                                                    
                                                             })
                );
                    timeline18.play();
                    LOGGER.info("725 timeline18 called, so we lost the turn and ai was in all in");
                    timeline18.setOnFinished(e -> {
                        this.gameMaster.getPlayerArray()[1].incrementCredit(this.gameMaster.getPlayerArray()[1].getBet()*2);
                        this.gameMaster.getPlayerArray()[0].incrementCredit(this.gameMaster.getMaxBet() - this.gameMaster.getPlayerArray()[1].getBet());
                        this.gameMaster.insertData(0, calculatedHandString, this.gameMaster.getPlayerArray()[1].getBet()*2);
                        this.newTurn();
                    });
                    LOGGER.info("708 new turn ended in timeline18, we are before return statement");
                    return;
                }else{
                    Timeline timeline19 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                                                    this.communicationLabel.setVisible(true);
                                                                    this.aiCreditHintLabel.setVisible(true);
                                                                    this.communicationLabel.setText("You have lost this turn!");
                                                                    this.aiCreditHintLabel.setText("+"+ Integer.toString(this.gameMaster.getPlayerArray()[0].getBet() + this.gameMaster.getPlayerArray()[1].getBet()));
                                                                    
                                                                    
                                                                    this.searchCardPictures(this.gameMaster.getPlayerArray()[1].getHand(), 2);
                                                                    this.aiCard1Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[0]);
                                                                    this.aiCard2Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[1]);
                                                                    this.aiCard3Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[2]);
                                                                    this.aiCard4Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[3]);
                                                                    this.aiCard5Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[4]);
                                                             }),
                                                new KeyFrame(Duration.seconds(6.0), e-> {
                                                                    this.communicationLabel.setVisible(false);
                                                                    this.aiCreditHintLabel.setVisible(false);
                                                                    
                                                             })
                );
                    timeline19.play();
                    LOGGER.info("756 timeline19 called, so we lost, and ai is not in all in");
                    timeline19.setOnFinished(e -> {
                        this.gameMaster.getPlayerArray()[1].incrementCredit(this.gameMaster.getPlayerArray()[0].getBet()+ this.gameMaster.getPlayerArray()[1].getBet());
                        this.gameMaster.insertData(0, calculatedHandString, this.gameMaster.getPlayerArray()[0].getBet() + this.gameMaster.getPlayerArray()[1].getBet());
                        this.newTurn();
                    });
                    LOGGER.info("737 new turn ended in timeline19, we are before return statement");
                    return;
                }
            case 3:
                Timeline timeline20 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                    this.communicationLabel.setVisible(true);
                                    this.aiCreditHintLabel.setVisible(true);
                                    this.playerCreditHintLabel.setVisible(true);
                                    this.communicationLabel.setText("It's a draw!");
                                    this.playerCreditHintLabel.setText("+"+ Integer.toString(this.gameMaster.getPlayerArray()[0].getBet()));
                                    this.aiCreditHintLabel.setText("+"+ Integer.toString(this.gameMaster.getPlayerArray()[1].getBet()));                                   
                                                                    
                                    this.searchCardPictures(this.gameMaster.getPlayerArray()[1].getHand(), 2);
                                    this.aiCard1Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[0]);
                                    this.aiCard2Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[1]);
                                    this.aiCard3Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[2]);
                                    this.aiCard4Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[3]);
                                    this.aiCard5Label.setText(this.gameMaster.getPlayerArray()[1].getHand()[4]);
                                                }),
                                    new KeyFrame(Duration.seconds(6.0), e-> {
                                    this.communicationLabel.setVisible(false);
                                    this.aiCreditHintLabel.setVisible(false);
                                    this.playerCreditHintLabel.setVisible(true);
                                    
                                                })
                );
                timeline20.play();
                LOGGER.info("789 sometimes its hard to decide who is better, so it's a win-win situation");
                timeline20.setOnFinished(e ->{
                    this.gameMaster.getPlayerArray()[0].incrementCredit(this.gameMaster.getPlayerArray()[0].getBet());
                    this.gameMaster.getPlayerArray()[1].incrementCredit(this.gameMaster.getPlayerArray()[1].getBet());
                    this.gameMaster.insertData(2, calculatedHandString, this.gameMaster.getPlayerArray()[0].getBet());
                    this.newTurn();
                });
                LOGGER.info("796 timeline20 ended, and hopefully new turn called, and we return!");
        }
    }
    /**
     * Sets the new table after a turn ends.
     */
    private void newTurn(){

        LOGGER.info("804 new turn has called!");
        if(this.gameMaster.getPlayerArray()[0].getCredit() < 100){
            this.defeat();
            return;
        }
        if(this.gameMaster.getPlayerArray()[1].getCredit() < 200){
            this.victory();
            return;
        }
        if(this.gameMaster.getPlayerArray()[0].getCredit() == 100){
            
            this.gameMaster.getPlayerArray()[0].setBet(100);
            this.gameMaster.getPlayerArray()[0].decrementCredit(100);
            this.gameMaster.setPlayerAllIn(1, true);
        }else{
            
            this.gameMaster.getPlayerArray()[0].setBet(100);
            this.gameMaster.getPlayerArray()[0].decrementCredit(100);
            this.gameMaster.setPlayerAllIn(1, false);
        }
        if(this.gameMaster.getPlayerArray()[1].getCredit() == 200){
            
            this.gameMaster.getPlayerArray()[1].setBet(200);
            this.gameMaster.getPlayerArray()[1].decrementCredit(200);
            this.gameMaster.setPlayerAllIn(2, true);
        }else{
            this.gameMaster.getPlayerArray()[1].setBet(200);
            this.gameMaster.getPlayerArray()[1].decrementCredit(200);
            this.gameMaster.setPlayerAllIn(2, false);
        }
        
        this.gameMaster.setMaxBet(200);
        this.gameMaster.setFlopped(1, false);
        this.gameMaster.setFlopped(2, false);
        this.gameMaster.setplayerChangedCard(false);
    
        this.gameMaster.getDealer().deal(gameMaster.getPlayerArray());
        
        this.setCardPictures();
        this.displayInterface();
        
        this.playerCard1.setDisable(false);
        this.playerCard2.setDisable(false);
        this.playerCard3.setDisable(false);
        this.playerCard4.setDisable(false);
        this.playerCard5.setDisable(false);
        LOGGER.info("823 everithing has set!");
        Timeline timeline21 = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> {
                                            this.communicationLabel.setVisible(true);
                                            this.communicationLabel.setText("Please make your bet!");
                                                    }),
                                        new KeyFrame(Duration.seconds(3.0), e -> this.communicationLabel.setVisible(false))
        );
        timeline21.play();
        LOGGER.info("857 timeline21 has been called, and this is the end of new turn.");
    }
    /**
     * In case of defeat, shows the victory pop up window.
     */
    private void defeat(){

        LOGGER.info("Player has been defeated!");
        this.gameMaster.closeTransaction();
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
            LOGGER.error(ex.getLocalizedMessage());
        }
        
    }
    /**
     * In case of victory, shows the victory pop up window.
     */
    private void victory(){

        LOGGER.info("Player is victorious!");
        this.gameMaster.closeTransaction();
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
            LOGGER.info(ex.getLocalizedMessage());
        }
    }
    /**
     * Sets the players' cards images.
     */
    private void setCardPictures(){
        
        searchCardPictures(this.gameMaster.getPlayerArray()[0].getHand(), 1);
        for(int i = 0; i < 5; ++i){
            
            this.cardPicturesArray[1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/backGround.png")),null,null,null,null)));
                    }
        this.aiCard1Label.setText("");
        this.aiCard2Label.setText("");
        this.aiCard3Label.setText("");
        this.aiCard4Label.setText("");
        this.aiCard5Label.setText("");
    }
    /**
     * Sets the player's cards images.
     * 
     * @param playerHand the hand of the player
     * @param player the player, whoes cards' images will be set
     * @throws MalformedURLException if the images can not be set
     */
    private void searchCardPictures(String[] playerHand, int player){
    
        String choosable;

        for(int i = 0; i < 5; ++i){
            
            choosable = playerHand[i];
            
            if(choosable.startsWith("c")){
                switch (choosable){
                    
                    
                    case "clubs2" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/clubs2.png")),null,null,null,null)));break;
                    case "clubs3" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/clubs3.png")),null,null,null,null)));break;
                    case "clubs4" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/clubs4.png")),null,null,null,null)));break;
                    case "clubs5" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/clubs5.png")),null,null,null,null)));break;
                    case "clubs6" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/clubs6.png")),null,null,null,null)));break;
                    case "clubs7" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/clubs7.png")),null,null,null,null)));break;
                    case "clubs8" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/clubs8.png")),null,null,null,null)));break;
                    case "clubs9" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/clubs9.png")),null,null,null,null)));break;
                    case "clubs10": cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/clubs10.png")),null,null,null,null)));break;
                    case "clubsJ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/clubsJ.png")),null,null,null,null)));break;
                    case "clubsQ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/clubsQ.png")),null,null,null,null)));break;
                    case "clubsK" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/clubsK.png")),null,null,null,null)));break;
                    case "clubsA" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/clubsA.png")),null,null,null,null)));break;
                    
                    default: LOGGER.error("Huston! We've got a problem here!");
                }
                continue;
            }
            
            if(choosable.startsWith("d")){
                switch (choosable){
                    case "diamonds2" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/diamonds2.png")),null,null,null,null)));break;
                    case "diamonds3" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/diamonds3.png")),null,null,null,null)));break;
                    case "diamonds4" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/diamonds4.png")),null,null,null,null)));break;
                    case "diamonds5" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/diamonds5.png")),null,null,null,null)));break;
                    case "diamonds6" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/diamonds6.png")),null,null,null,null)));break;
                    case "diamonds7" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/diamonds7.png")),null,null,null,null)));break;
                    case "diamonds8" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/diamonds8.png")),null,null,null,null)));break;
                    case "diamonds9" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/diamonds9.png")),null,null,null,null)));break;
                    case "diamonds10": cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/diamonds10.png")),null,null,null,null)));break;
                    case "diamondsJ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/diamondsJ.png")),null,null,null,null)));break;
                    case "diamondsQ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/diamondsQ.png")),null,null,null,null)));break;
                    case "diamondsK" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/diamondsK.png")),null,null,null,null)));break;
                    case "diamondsA" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/diamondsA.png")),null,null,null,null)));break;
                    
                    default: LOGGER.error("Huston! We've got a problem here!");
                }
                continue;
            }
            
            if(choosable.startsWith("h")){
                switch (choosable){
                    case "hearts2" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/hearts2.png")),null,null,null,null)));break;
                    case "hearts3" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/hearts3.png")),null,null,null,null)));break;
                    case "hearts4" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/hearts4.png")),null,null,null,null)));break;
                    case "hearts5" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/hearts5.png")),null,null,null,null)));break;
                    case "hearts6" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/hearts6.png")),null,null,null,null)));break;
                    case "hearts7" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/hearts7.png")),null,null,null,null)));break;
                    case "hearts8" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/hearts8.png")),null,null,null,null)));break;
                    case "hearts9" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/hearts9.png")),null,null,null,null)));break;
                    case "hearts10": cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/hearts10.png")),null,null,null,null)));break;
                    case "heartsJ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/heartsJ.png")),null,null,null,null)));break;
                    case "heartsQ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/heartsQ.png")),null,null,null,null)));break;
                    case "heartsK" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/heartsK.png")),null,null,null,null)));break;
                    case "heartsA" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/heartsA.png")),null,null,null,null)));break;
                    
                    default: LOGGER.error("Huston! We've got a problem here!");
                }
                continue;
            }
            if(choosable.startsWith("s")){
                switch (choosable){
                    case "spades2" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/spades2.png")),null,null,null,null)));break;
                    case "spades3" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/spades3.png")),null,null,null,null)));break;
                    case "spades4" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/spades4.png")),null,null,null,null)));break;
                    case "spades5" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/spades5.png")),null,null,null,null)));break;
                    case "spades6" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/spades6.png")),null,null,null,null)));break;
                    case "spades7" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/spades7.png")),null,null,null,null)));break;
                    case "spades8" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/spades8.png")),null,null,null,null)));break;
                    case "spades9" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/spades9.png")),null,null,null,null)));break;
                    case "spades10": cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/spades10.png")),null,null,null,null)));break;
                    case "spadesJ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/spadesJ.png")),null,null,null,null)));break;
                    case "spadesQ" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/spadesQ.png")),null,null,null,null)));break;
                    case "spadesK" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/spadesK.png")),null,null,null,null)));break;
                    case "spadesA" : cardPicturesArray[player-1][i].setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/spadesA.png")),null,null,null,null)));break;
                    
                    default: LOGGER.error("Huston! We've got a problem here!");
                }
            }
        }

    }
    /**
     * Refreshes the interface, whitch the player can interact with.
     */
    private void displayInterface(){
    
        this.aiCredit.setText(Integer.toString(this.gameMaster.getPlayerArray()[1].getCredit()));
        this.aiBet.setText(Integer.toString(this.gameMaster.getPlayerArray()[1].getBet()));
        this.playerCredit.setText(Integer.toString(this.gameMaster.getPlayerArray()[0].getCredit()));
        this.playerBet.setText(Integer.toString(this.gameMaster.getPlayerArray()[0].getBet()));
        this.betTextField.setText("0");
        
        this.playerCreditHintLabel.setText("");
        this.aiCreditHintLabel.setText("");
        this.playerCard1Label.setText(this.gameMaster.getPlayerArray()[0].getHand()[0]);
        this.playerCard2Label.setText(this.gameMaster.getPlayerArray()[0].getHand()[1]);
        this.playerCard3Label.setText(this.gameMaster.getPlayerArray()[0].getHand()[2]);
        this.playerCard4Label.setText(this.gameMaster.getPlayerArray()[0].getHand()[3]);
        this.playerCard5Label.setText(this.gameMaster.getPlayerArray()[0].getHand()[4]);
        
    }
    /**
     * Gives the close event, and manualy closes the database connectoin and the program. 
     */
    public void afterInitialize(){  
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0), e -> this.communicationLabel.setText("Welcome")),
                                         new KeyFrame(Duration.seconds(3.0), e -> this.communicationLabel.setText("Please make your bet!")),
                                         new KeyFrame(Duration.seconds(6.0), e -> this.communicationLabel.setVisible(false)));
        timeline.play();
        
        this.aiCard1Label.setText("");
        this.aiCard2Label.setText("");
        this.aiCard3Label.setText("");
        this.aiCard4Label.setText("");
        this.aiCard5Label.setText("");
        
        Stage st = (Stage)this.aiBet.getScene().getWindow();
        st.resizableProperty().setValue(Boolean.FALSE);
        st.setOnCloseRequest(e->{
            this.gameMaster.closeTransaction();
            Platform.exit();
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gameMaster.getPlayerArray()[0] = new Player();
        this.gameMaster.getPlayerArray()[1] = new Player();
        
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
        
        this.gameMaster.getDealer().deal(gameMaster.getPlayerArray());
        
        this.gameMaster.getPlayerArray()[0].setBet(100);
        this.gameMaster.getPlayerArray()[0].decrementCredit(100);
        this.gameMaster.getPlayerArray()[1].setBet(200);
        this.gameMaster.getPlayerArray()[1].decrementCredit(200);
        
        this.setCardPictures();
        
        this.displayInterface();
        
        this.anchorPane.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/pictures/welcomeMenuBackGround.png")),null,null,null,null)));
        
    }    
}
