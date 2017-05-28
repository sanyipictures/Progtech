package hu.unideb.inf.poker.Modell;

import hu.unideb.inf.poker.Database.EntityManagement;

/**
 * The GameMaster class represent a higher entity in the poker game,
 * hoes duty is to watch over the rules, and decides who wins.
 * 
 * <p>
 * NOTE: in real life, all player knows the rules, and at the end of each turn, 
 * they decide who wins, and who lose.
 * They also calculate the amount of prize to be spread, and sometimes they
 * redefine the rules as well.
 * 
 * In a virtual game, there must be a GameMaster, who watch over these processes
 * </p>
 * 
 */
public class GameMaster {

    /**
     * Entitimanager instance.
     */
    private final EntityManagement entityManagement;
    /**
     * Variable to decide if cards were changed.
     */
    private boolean changedCards;
    /**
     * Variable to contain the maximum bet.
     */
    private int maxBet;
    /**
     * Variable to contain the minimum bet.
     */
    private int minBet;
    /**
     * Array to deceide whitch player has flopped his/her card.
     */
    private final boolean[] flopped;
    /**
     * Array to decidde whitch player is in all in.
     */
    private final boolean[] allIn;
    /**
     * WinnerCalculator instane.
     */
    private final WinnerCalculator winnerCalc;
    /**
     * Dealer instance.
     */
    private final Dealer dealer;
    /**
     * Array to contain the players.
     */
    private final Player[] playerArray;
    /**
     * AI instance.
     */
    private final AI ai;
    /**
     * Basic constructor.
     */
    public GameMaster(){
    
        this.changedCards      = false;
        this.minBet            = 200;
        this.maxBet            = 0;
        this.flopped           = new boolean[]{false,false};
        this.allIn             = new boolean[]{false,false};
        this.winnerCalc        = new WinnerCalculator();
        this.entityManagement  = new EntityManagement();
        this.dealer            = new Dealer();
        this.playerArray       = new Player[2];
        this.ai                = new AI();
    }
    
    /**
     * Inserts a row into the UserStatistic table.
     * 
     * @param win 0 if the player lost the turn, 1 if wins, 2 if both win
     * @param hand the calculated hand of the player
     * @param prize the amount of cash he/she earned
     */
    public void insertData(int win, String hand, int prize){this.entityManagement.newEntity(win, hand, prize);}
    
    /**
     * Closes the database connection.
     */
    public void closeTransaction(){this.entityManagement.closeTransaction();}
    /**
     * Decides the winner from two player.
     * 
     * @param player1Hand the first player's hand, represented in a String array
     * @param player2Hand the second player's hand, represented in a String array
     * @return the winner player, in case of a draw, returns 3
     */
    public int getWinner(String[] player1Hand, String[] player2Hand){ return this.winnerCalc.winnerPlayer(player1Hand, player2Hand);}
    
    /**
     * Sets the player flopped status.
     * 
     * @param player the player
     * @param t true if player flopped, false otherwise
     */
    public void setFlopped(int player, boolean t){this.flopped[player-1] = t;}
    
    /**
     * Tells if a player thrown his/her cards.
     * 
     * @param player the player
     * @return true, if player flopped, false otherwise
     */
    public boolean getFlopped(int player){return this.flopped[player-1];}
    
    /**
     * Getter for the maxBet attribute.
     * 
     * @return maxBet
     */
    public int getMaxBet(){return this.maxBet;}
    
    /**
     * Setter for the maxBet attribute.
     * 
     * @param amount the amount to be set
     */
    public void setMaxBet(int amount){this.maxBet = amount;}
    
    /**
     * Getter for the minBet attribute.
     * @return the minimum bet
     */
    public int getMinBet(){return this.minBet;}
    
    /**
     * Setter for the minBet attribute.
     * @param amount the amount to be set for minBet attribute
     */
    public void setMinBet(int amount){this.minBet = amount;}
    
    /**
     * Getter for changedCards attribute.
     * 
     * @return true, if the player has changed cards, false otherwise
     */
    public boolean getPlayerChangedCard(){return this.changedCards;}
    
    /**
     * Setter for changedCards attribute.
     * 
     * @param yesorno true, if changed, false otherwise
     */
    public void setplayerChangedCard(boolean yesorno){this.changedCards = yesorno;}
    
    /**
     * Tells if a player is in allIn.
     * 
     * @param player the player who we are interested about
     * @return true if the player is in allIn, false otherwise
     */
    public boolean getPlayerAllIn(int player){ return this.allIn[player-1]; }
    
    /**
     * Sets the given player allIn status.
     * 
     * @param player the player, whoes status will be changed
     * @param yesorno true if player gains in allIn status, false otherwise
     */
    public void setPlayerAllIn(int player, boolean yesorno){this.allIn[player-1] = yesorno;}
    
    /**
     * Getter for the playerArray attribute.
     * 
     * @return the playerArray attribute's value;
     */
    public Player[] getPlayerArray(){return this.playerArray;}
    
    /**
     * Getter for the ai variable.
     * 
     * @return the ai attribute's value
     */
    public AI getAI(){return this.ai;}
    
    /**
     * Getter for the dealer attribute.
     * 
     * @return the dealer attribute's value
     */
    public Dealer getDealer(){return this.dealer;}
    
    /**
     * Tells the given player's hand as String.
     * 
     * @param player the player's hand, before calculated
     * @return the calculated hand
     */
    public String getCalculatedStrengthAsString(String[] player){
        return this.winnerCalc.calculateStrength(player);
    }
    
}
