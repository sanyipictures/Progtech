
package com.mycompany.poker;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * The AI class represents the opponent of the player.
 */
public class AI {
    
    private static final Logger LOGGER = Logger.getLogger(AI.class.getName());
    private int strength;
    private final WinnerCalculator winnerCalc;
    private final Random random;
    
    public AI(){
    
        this.strength = -1;
        this.winnerCalc = new WinnerCalculator();
        this.random = new Random();
        LOGGER.setLevel(Level.FINE);
    }
    /**
     * Decides the necessary steps in the AI's turn.
     * 
     * @param player the AI represented player
     * @param dealer a dealer instance needes to deal cards to the AI player
     * @param gameMaster a gameMaster instance, to check the game status
     */
    public void aiTurn(Player player, Dealer dealer, GameMaster gameMaster){
        
        this.strength = winnerCalc.playerCalculateHand(player.getHand());
        
        switch(strength){
        
            case 3: 
                if(player.getBet() < gameMaster.getMaxBet()){

                     if(random.nextInt(100) < 30){
                         gameMaster.setFlopped(2, true);
                         LOGGER.log(Level.FINE, "AI has flopped cards");
                         return;
                     }
                }
            case 4:
                if(player.getBet() < gameMaster.getMaxBet()){

                     if(random.nextInt(100) < 20){
                         gameMaster.setFlopped(2, true);
                         LOGGER.log(Level.FINE, "AI has flopped cards");
                         return;
                     }
                }
            case 5:
                if(player.getBet() < gameMaster.getMaxBet()){

                     if(random.nextInt(100) < 15){
                         gameMaster.setFlopped(2, true);
                         LOGGER.log(Level.FINE, "AI has flopped cards");
                         return;
                     }
                }
            case 6:
                if(player.getBet() < gameMaster.getMaxBet()){

                     if(random.nextInt(100) < 10){
                         gameMaster.setFlopped(2, true);
                         LOGGER.log(Level.FINE, "AI has flopped cards");
                         return;
                     }
                }
            case 7:
                if(player.getBet() < gameMaster.getMaxBet()){

                     if(random.nextInt(100) < 5){
                         gameMaster.setFlopped(2, true);
                         LOGGER.log(Level.FINE, "AI has flopped cards");
                         return;
                     }
                }
            case 8:
                if(player.getBet() < gameMaster.getMaxBet()){

                     if(random.nextInt(100) < 2){
                         gameMaster.setFlopped(2, true);
                         LOGGER.log(Level.FINE, "AI has flopped cards");
                         return;
                     }
                }
        
        }
        if(player.getCredit() < (gameMaster.getMaxBet() - player.getBet()) ){
                         
            player.incrementBet(player.getCredit());
            player.setCredit(0);
            LOGGER.log(Level.FINE, "AI takes ALL-IN!");
                             
        }else{            
            player.decrementCredit(gameMaster.getMaxBet() - player.getBet() );
            player.incrementBet(gameMaster.getMaxBet() - player.getBet() );
            LOGGER.log(Level.FINE, "AI holds the bet!");
                             
            }
                     
    }
}
