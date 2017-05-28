package hu.unideb.inf.poker.Modell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Player class represents the players in the game.
 */
public class Player {
    /**
     * Logger instance for logging.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Player.class.getName());
    /**
     * Variable to contain the player's credit.
     */
    private int credit;
    /**
     * Array to contain the player's hand.
     */
    private String[] hand;
    /**
     * Variable to contain the player's bet amount.
     */
    private int bet;
    /**
     * Array to contain the selected cards.
     */
    private final boolean[] selectedCards;
    /**
     * Basic constructor.
     */
    public Player(){
    
        this.credit        = 10000;
        this.hand          = new String[5];
        this.selectedCards = new boolean[]{false,false,false,false,false};
        this.bet           = 0;
        
        LOGGER.info("40 A player Instance has been created!");
    }
    /**
     * Setter for the credit property.
     * 
     * @param amount the amount of credit to be set 
     */
    public void setCredit(int amount){ 
        this.credit = amount; 
        LOGGER.info( "49 The player's credit has been changed to: {} ",this.getCredit());
    }
    /**
     * Getter for the credit property.
     * 
     * @return the amount of the credit, the player owns
     */
    public int getCredit(){return this.credit;}
    /**
     * Setter for the bet property.
     * 
     * @param amount the amount of bet to be set
     */
    public void setBet(int amount){
        this.bet = amount;
        LOGGER.info( "64 The player's bet has been changed to: {} ", this.getBet());
    }
    /**
     * Getter for the bet property.
     * 
     * @return the amount of bet, the player has
     */
    public int getBet(){return this.bet;}
    /**
     * Increments the player's actual bet.
     * 
     * @param amount the amount of credit to increment with
     */
    public void incrementBet(int amount){
        this.bet += amount;
        LOGGER.info( "79 The player's bet has been incremented with:{} ",amount );
        LOGGER.info( "80 The player's bet has been changed to:{} ",this.getBet());
    }
    /**
     * Decrements the player's actual bet.
     * 
     * @param amount the amount of credit to decrement with
     */
    public void decrementBet(int amount){
        this.bet -= amount;
        LOGGER.info( "89 The player's bet has been decremented with:{} ", amount);
        LOGGER.info( "90 The player's credit has been changed to:{} ", this.getBet());
    }
    public String[] getHand(){ return this.hand; }
    /**
     * Setter for the hand property.
     * 
     * @param hand a string array, representing a five card hand
     */
    public void setHand(String[] hand){ 
        this.hand = hand.clone(); 
        LOGGER.info( "100 The player's hand has been changed to: {} {} {} {} {}", this.hand[0], this.hand[1], this.hand[2], this.hand[3], this.hand[4]);
    }
    /**
     * Sets a single card in the player's hand, at the given position.
     * 
     * <p>
     * NOTE: this method is created to make the player able to change a single
     * card in his/her hand, since the game rules allows it once in evry turn.
     * </p>
     * 
     * @param card the name of the card to replace with
     * @param position the position of the card, whitch is to be replaced
     */
    public void setCard(String card, int position){
        this.hand[position] = card;
        LOGGER.info( "115 The player's card at {} position has been changed to {}", position+1, card);
    }
    /**
     * Getter for the selectedCards property.
     * 
     * @return an array, whitch contains the selected and non 
     *         selected cards as well
     */
    public boolean[] getSelectedCards(){return this.selectedCards;}
    /**
     * Getter for a single card in the selectedCards attribute.
     * 
     * @param card the interrested card's position
     * @return a boolean value, true, if the selected card has already selected,
     *         false otherwise
     */
    public boolean getSelectedCard(int card){return this.selectedCards[card-1];}
    /**
     * Sets a specified card position selected, or unselect it.
     * 
     * @param position the position of the interrested card in the player's hand
     */
    public void setSelectedCards(int position){
    
        if(this.selectedCards[position-1]){
        
            this.selectedCards[position-1] = false;
            LOGGER.info( "142 The player's cardselection has been changed to false ");
            return;
        }
        this.selectedCards[position-1] = true;
        LOGGER.info( "146 The player's cardselection has been changed to true ");
    }
    /**
     * Sets all cards' selected status to false.
     */
    public void setAllSelectedCards(){
        this.selectedCards[0] = false;
        this.selectedCards[1] = false;
        this.selectedCards[2] = false;
        this.selectedCards[3] = false;
        this.selectedCards[4] = false;
    }
    /**
     * Increments the player's credit with a specified amount of credit.
     * 
     * @param amount the amount of credit to increment with
     */
    public void incrementCredit(int amount){ 
        this.credit += amount; 
        LOGGER.info( "165 The player's credit has been incrmented with " + amount +" amount, new value:{} ", this.getCredit());
    }
    /**
     * Decrements the player's credit with a specified amount of credit.
     * 
     * @param amount the amount of credit to incement with
     */
    public void decrementCredit(int amount){
        this.credit -= amount; 
        LOGGER.info( "174 The player's credit has been decremented with {} ", amount);
        LOGGER.info( "175 The player's new credit amount is {}",this.getCredit());
    }
}
