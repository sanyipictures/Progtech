
package hu.unideb.inf.poker.Modell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;
/**
 * The AI class represents the opponent of the player.
 */
public class AI {
    
    /**
     * Logger instance, for logging.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AI.class.getName());
    /**
     * Contains the card strength.
     */
    private int strength;
    /**
     * An instance of WinnerCalculator class.
     */
    private final WinnerCalculator winnerCalc;
    /**
     * Random instance, for random number generation.
     */
    private final Random random;
    
    /**
     * Basic constructor.
     */
    public AI(){
    
        this.strength = -1;
        this.winnerCalc = new WinnerCalculator();
        this.random = new Random();
    }
    /**
     * Decides the necessary steps in the AI's turn.
     * 
     * @param player the AI represented player
     * @param dealer a dealer instance needes to deal cards to the AI player
     * @param gameMaster a gameMaster instance, to check the game status
     * @return true if not flopped, false otherwise
     */
    public boolean aiTurn(Player player, Dealer dealer, GameMaster gameMaster){
        
        this.strength = winnerCalc.playerCalculateHand(player.getHand());
        
        switch(strength){
        
            case 3: 
                if(player.getBet() < gameMaster.getMaxBet()){

                     if(random.nextInt(100) < 30){
                         gameMaster.setFlopped(2, true);
                         LOGGER.info("AI has flopped cards");
                         return false;
                     }
                }
            case 4:
                if(player.getBet() < gameMaster.getMaxBet()){

                     if(random.nextInt(100) < 20){
                         gameMaster.setFlopped(2, true);
                         LOGGER.info( "AI has flopped cards");
                         return false;
                     }
                }
            case 5:
                if(player.getBet() < gameMaster.getMaxBet()){

                     if(random.nextInt(100) < 15){
                         gameMaster.setFlopped(2, true);
                         LOGGER.info( "AI has flopped cards");
                         return false;
                     }
                }
            case 6:
                if(player.getBet() < gameMaster.getMaxBet()){

                     if(random.nextInt(100) < 10){
                         gameMaster.setFlopped(2, true);
                         LOGGER.info( "AI has flopped cards");
                         return false;
                     }
                }
            case 7:
                if(player.getBet() < gameMaster.getMaxBet()){

                     if(random.nextInt(100) < 5){
                         gameMaster.setFlopped(2, true);
                         LOGGER.info( "AI has flopped cards");
                         return false;
                     }
                }
            case 8:
                if(player.getBet() < gameMaster.getMaxBet()){

                     if(random.nextInt(100) < 2){
                         gameMaster.setFlopped(2, true);
                         LOGGER.info( "AI has flopped cards");
                         return false;
                     }
                }
        
        }
        if(gameMaster.getPlayerAllIn(2)){ return true; }
        
        if(player.getCredit() < (gameMaster.getMaxBet() - player.getBet()) ){
                         
            player.incrementBet(player.getCredit());
            player.setCredit(0);
            gameMaster.setPlayerAllIn(2, true);
            LOGGER.info( "AI takes ALL-IN!");
                             
        }else{            
            player.decrementCredit(gameMaster.getMaxBet() - player.getBet() );
            player.incrementBet(gameMaster.getMaxBet() - player.getBet() );
            LOGGER.info( "AI holds the bet!");
                             
            }
                     return true;
    }
}
