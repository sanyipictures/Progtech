/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Dealer class represents the Dealer's rules in a poker game.
 * 
 * <p>
 * It riffles cards, deal them to players, and changes them
 * </p>
 * 
 */
public class Dealer {
    private static final Logger LOGGER = Logger.getLogger(Dealer.class.getName());
    private static List<String> cardDeck     = new ArrayList<String>(52);
    private List<String> clubsPack           = new ArrayList<String>(13);
    private List<String> diamondsPack        = new ArrayList<String>(13);
    private List<String> heartsPack          = new ArrayList<String>(13);
    private List<String> spadesPack          = new ArrayList<String>(13);
    
    private final Random randomNumber = new Random();
    
    /**
     * Deals a single card to a given player.
     * 
     * <p>
     * NOTE: the parameter 'positon' must between 1 and 5, becouse the
     * method transfer it to the correct array index
     * </p>
     * 
     * @param player the player to deal to
     * @param position the position of the card in the player's hand 
     */
    public void dealToPlayer(Player player, int position){
    
        player.setCard(Dealer.cardDeck.remove(Dealer.cardDeck.size()-1), position);
    }
    /**
     * Deals five cards to the given players.
     * 
     * <p>
     * Since it removes five cards from the top of the pack, 
     * this ensures that there can not be two player with same cards.
     * </p>
     * 
     * @param playerArray an array, that contains all players to deal to
     */
    public void deal(Player [] playerArray){
        
        String[] hand = new String[5];
        
        this.reRiffle();
        
        for( Player t : playerArray){
        
            hand[0] = Dealer.cardDeck.remove(Dealer.cardDeck.size()-1);
            hand[1] = Dealer.cardDeck.remove(Dealer.cardDeck.size()-1);
            hand[2] = Dealer.cardDeck.remove(Dealer.cardDeck.size()-1);
            hand[3] = Dealer.cardDeck.remove(Dealer.cardDeck.size()-1);
            hand[4] = Dealer.cardDeck.remove(Dealer.cardDeck.size()-1);
            
            t.setHand(hand);
        }
        LOGGER.log(Level.INFO, "Cards are dealt to players!");
        LOGGER.log(Level.INFO, "Player's cards are: !");
        LOGGER.log(Level.INFO, "{0} {1} {2} {3} {4}", new Object[]{playerArray[0].getHand()[0], playerArray[0].getHand()[1], playerArray[0].getHand()[2], playerArray[0].getHand()[3], playerArray[0].getHand()[4]});
        LOGGER.log(Level.FINE, "{0} {1} {2} {3} {4}", new Object[]{playerArray[1].getHand()[0], playerArray[1].getHand()[1], playerArray[1].getHand()[2], playerArray[1].getHand()[3], playerArray[1].getHand()[4]});
    }
    
    /**
     * Executes the four pack's filling up, then riffles them together.
     */
    public void initializer(){
    
        initializeClubsPack();
        initializeDiamondsPack();
        initializeHeartsPack();
        initializeSpadesPack();
        
        riffleCardDeck();
    }
    /**
     * Removes all card from the main pack, than riffle a new one.
     */
    public void reRiffle(){
    
        this.cardDeck.removeAll(cardDeck);
        this.initializer();
    }
    /**
    * Sets the "clubs" card array with their String represented name. 
    */
    private void initializeClubsPack(){

        this.clubsPack.add("clubs2");
        this.clubsPack.add("clubs3");
        this.clubsPack.add("clubs4");
        this.clubsPack.add("clubs5");
        this.clubsPack.add("clubs6");
        this.clubsPack.add("clubs7");
        this.clubsPack.add("clubs8");
        this.clubsPack.add("clubs9");
        this.clubsPack.add("clubs10");
        this.clubsPack.add("clubsJ");
        this.clubsPack.add("clubsQ");
        this.clubsPack.add("clubsK");
        this.clubsPack.add("clubsA");
    }
    /**
    * Sets the "diamonds" card array with their String represented name. 
    */
    private void initializeDiamondsPack(){
    
        this.diamondsPack.add("diamonds2");
        this.diamondsPack.add("diamonds3");
        this.diamondsPack.add("diamonds4");
        this.diamondsPack.add("diamonds5");
        this.diamondsPack.add("diamonds6");
        this.diamondsPack.add("diamonds7");
        this.diamondsPack.add("diamonds8");
        this.diamondsPack.add("diamonds9");
        this.diamondsPack.add("diamonds10");
        this.diamondsPack.add("diamondsJ");
        this.diamondsPack.add("diamondsQ");
        this.diamondsPack.add("diamondsK");
        this.diamondsPack.add("diamondsA");
    }
    /**
    * Sets the "hearts" card array with their String represented name. 
    */
    private void initializeHeartsPack(){
    
        this.heartsPack.add("hearts2");
        this.heartsPack.add("hearts3");
        this.heartsPack.add("hearts4");
        this.heartsPack.add("hearts5");
        this.heartsPack.add("hearts6");
        this.heartsPack.add("hearts7");
        this.heartsPack.add("hearts8");
        this.heartsPack.add("hearts9");
        this.heartsPack.add("hearts10");
        this.heartsPack.add("heartsJ");
        this.heartsPack.add("heartsQ");
        this.heartsPack.add("heartsK");
        this.heartsPack.add("heartsA");  
    }
    /**
    * Sets the "spades" card array with their String represented name. 
    */
    private void initializeSpadesPack(){
    
        this.spadesPack.add("spades2");
        this.spadesPack.add("spades3");
        this.spadesPack.add("spades4");
        this.spadesPack.add("spades5");
        this.spadesPack.add("spades6");
        this.spadesPack.add("spades7");
        this.spadesPack.add("spades8");
        this.spadesPack.add("spades9");
        this.spadesPack.add("spades10");
        this.spadesPack.add("spadesJ");
        this.spadesPack.add("spadesQ");
        this.spadesPack.add("spadesK");
        this.spadesPack.add("spadesA");
    }
    /**
     * Riffles the "spades", "hearts", "diamonds" and "clubs" packs into a
     * single pack.
     */
    private void riffleCardDeck(){
    
        int counter1 = 13;
        int counter2 = 13;
        int counter3 = 13;
        int counter4 = 13;
        
        int[] chooseAble          = new int[] {1,1,1,1};
        
        int packChooser           = this.randomNumber.nextInt(4);
        
        int clubsCardChooser      = this.randomNumber.nextInt(13);
        int diamondsCardChooser   = this.randomNumber.nextInt(13);
        int heartsCardChooser     = this.randomNumber.nextInt(13);
        int spadesCardChooser     = this.randomNumber.nextInt(13);
        
        while(Dealer.cardDeck.size() != 52){
        
            while(chooseAble[packChooser] == 0 ){
                
                packChooser = ++packChooser%4;
            }
            
            if( packChooser == 0 && !this.clubsPack.isEmpty() ){
                    
                Dealer.cardDeck.add(this.clubsPack.remove(clubsCardChooser));
                        
                packChooser = this.randomNumber.nextInt(4);
                    
                if(this.clubsPack.isEmpty()){
                    chooseAble[0] = 0;
                    continue;
                }
                    
                clubsCardChooser = this.randomNumber.nextInt( --counter1 );
                continue;
            }
            if( packChooser == 1 && !this.diamondsPack.isEmpty() ){
            
                Dealer.cardDeck.add(this.diamondsPack.remove(diamondsCardChooser));
                
                packChooser = this.randomNumber.nextInt(4);
                
                if(this.diamondsPack.isEmpty()){
                    chooseAble[1] = 0;
                    continue;
                }
                
                diamondsCardChooser = this.randomNumber.nextInt( --counter2 );
                continue;
            }
            if( packChooser == 2 && !this.heartsPack.isEmpty() ){
            
                Dealer.cardDeck.add(this.heartsPack.remove(heartsCardChooser));
                
                packChooser = this.randomNumber.nextInt(4);
                
                if(this.heartsPack.isEmpty()){
                    chooseAble[2] = 0;
                    continue;
                }
                
                heartsCardChooser = this.randomNumber.nextInt( --counter3 );
                continue;
            }
            if( packChooser == 3 && !this.spadesPack.isEmpty() ){
            
                Dealer.cardDeck.add(this.spadesPack.remove(spadesCardChooser));
                
                packChooser = this.randomNumber.nextInt(4);
                
                if(this.spadesPack.isEmpty()){
                    chooseAble[3] = 0;
                    continue;
                }
                
                spadesCardChooser = this.randomNumber.nextInt( --counter4 );
            }
        }
    }
}
