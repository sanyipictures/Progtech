package com.mycompany.poker;
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
    private boolean changedCards;
    private int maxBet;
    private int minBet;
    private final boolean[] flopped;
    private final WinnerCalculator winnerCalc;
    
    public GameMaster(){
    
        this.changedCards = false;
        this.minBet       = 200;
        this.maxBet       = 0;
        this.flopped      = new boolean[]{false,false};
        this.winnerCalc   = new WinnerCalculator();
    }
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
     * @return 
     */
    public int getMinBet(){return this.minBet;}
    /**
     * Setter for the minBet attribute.
     * @param amount 
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
    
}
