package hu.unideb.inf.poker.Modell;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Decides who is the winner, by the given players hand, and 
 * decides if both player has the same strength hand.
 * 
 */
public class WinnerCalculator {
    /**
     * Logger instance for logging.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WinnerCalculator.class.getName());
    /**
     * Calculates the strength by an ordered, String represented hand.
     * 
     * @param hand calculated, and ordered hand of a player
     * @return the strenght of a hand, represented by numeric value
     */
    public int playerCalculateHand(String[] hand){
    
        String calculatedHand = calculateStrength(hand);
        
        return  calculatedHand.contains("RoyalFlush")      ?11:
                calculatedHand.contains("Straight")  &&
                        calculatedHand.contains("Flush")   ?10:
                    calculatedHand.contains("Poker")       ?9:
                    calculatedHand.contains("FullHouse")   ?8:
                    calculatedHand.contains("Flush")       ?7:
                    calculatedHand.contains("Straight")    ?6:
                    calculatedHand.contains("Drill")       ?5:
                    calculatedHand.contains("TwoPair")     ?4:
                    calculatedHand.contains("Pair")        ?3:
                    calculatedHand.contains("High")        ?2:0;
    }
    /**
     * Calculates the winner from two player.
     * 
     * @param player1Hand the first player's hand
     * @param player2Hand the second player's hand
     * @return the winner, in case of draw, returns 3
     */
    public int winnerPlayer(String[] player1Hand, String[] player2Hand){

        LOGGER.info("winnerPlayer method has been called!");
        LOGGER.info("The player's hand is: ");
        LOGGER.info("{}", new Object[]{player1Hand.toString()});
        LOGGER.info("The AI's hand is: ");
        LOGGER.info("{}", new Object[]{player2Hand.toString()});
        String[] playersCalculatedHand = new String[]{
                             calculateStrength(player1Hand),
                             calculateStrength(player2Hand),
                             };
        LOGGER.info("The player's hand's strength as String:");
        LOGGER.info("{}", new Object[]{playersCalculatedHand[0]});
        LOGGER.info("The AI's hand's strength as String: ");
        LOGGER.info("{}", new Object[]{playersCalculatedHand[1]});
        int strength[] = new int[2];
        
        for(int i = 0; i < 2; ++i){
            strength[i] = 
                    playersCalculatedHand[i].contains("RoyalFlush")  ?11:
                    playersCalculatedHand[i].contains("Straight")  &&
                        playersCalculatedHand[i].contains("Flush")   ?10:
                    playersCalculatedHand[i].contains("Poker")       ?9:
                    playersCalculatedHand[i].contains("FullHouse")   ?8:
                    playersCalculatedHand[i].contains("Flush")       ?7:
                    playersCalculatedHand[i].contains("Straight")    ?6:
                    playersCalculatedHand[i].contains("Drill")       ?5:
                    playersCalculatedHand[i].contains("TwoPair")     ?4:
                    playersCalculatedHand[i].contains("Pair")        ?3:
                    playersCalculatedHand[i].contains("High")        ?2:0;
        }
        LOGGER.info("The player's hand's strength as integer: ");
        LOGGER.info("{0}", new Object[]{strength[0]});
        LOGGER.info("The AI's hand's strength as integer: ");
        LOGGER.info("{0}", new Object[]{strength[1]});
        if(strength[0] == strength[1]){
        
            LOGGER.info("The two player has the same hand strength: ");
            LOGGER.info("{0}", new Object[]{strength[0]});
            return this.all2Equivalent(playersCalculatedHand, strength[0]);
        }
        return strength[0] > strength[1] ? 1 : 2; 
    }
    /**
     * Tells the type of a player's hand.
     * 
     * <p>
     * NOTE: takes an unordered hand, and than calculates the type of the hand,
     * for instance: "Poker", than orders it by strength, for example:
     * "TwoPairAWith5and9".
     * This is necessary if two players have the same type of hand.
     * 
     * For instance: the first player has "TwoPairAWith5and9", the second one
     * has "TwoPair5WithAandK", than both player has TwoPair, so we have to 
     * decide witch one is stronger.
     * 
     * Since they are ordered by strength, we can make a decison by the 8th
     * character, becouse it is always higher, than 
     * the second part of the TwoPair.
     * 
     * If the high carded TwoPairs are the same as well, then we continue the 
     * algorithm, until we can decide who wins, or is it a draw.
     * 
     * This decision algorithm is implemented in winnerPlayer.
     * </p>
     * @param player is the hand of a player
     * @return a String representation of the player's strength hand 
     */
    public String calculateStrength(String[] player){
        LOGGER.info("CalculateStrength method has been called!");
        String together = Arrays.toString(player);
        String eos      = new StringBuilder()
                              .append(player[0].charAt(player[0].length()-1))
                              .append(player[1].charAt(player[1].length()-1))
                              .append(player[2].charAt(player[2].length()-1))
                              .append(player[3].charAt(player[3].length()-1))
                              .append(player[4].charAt(player[4].length()-1))
                              .toString();
        //ha a színe a kártyáknak megegyezik
    if( ( player[0].startsWith("s") && player[1].startsWith("s") && player[2].startsWith("s") && player[3].startsWith("s") && player[4].startsWith("s") ) ||
        ( player[0].startsWith("c") && player[1].startsWith("c") && player[2].startsWith("c") && player[3].startsWith("c") && player[4].startsWith("c") ) ||      
        ( player[0].startsWith("h") && player[1].startsWith("h") && player[2].startsWith("h") && player[3].startsWith("h") && player[4].startsWith("h")) ||
        ( player[0].startsWith("d") && player[1].startsWith("d") && player[2].startsWith("d") && player[3].startsWith("d") && player[4].startsWith("d"))
      ){
        LOGGER.info("The hand stands with te same colors!");
        //ha a kártyák színe azonos, és tartalmazza a '0', 'J', 'Q', 'K', 'A' kártyákat, akkor royalflush
        if( together.contains("0") && together.contains("A") && together.contains("J") && together.contains("Q") && together.contains("K") ){
           LOGGER.info("RoyalFlush has been detected!!!");
            return "RoyalFlush";
        }
        
        String straightFlush = getStraightType(eos);
        LOGGER.info("getStraigthType returned with: ");
        LOGGER.info("{0}", straightFlush);
        //ha a színük megegyezik, de nem illeszkedik egyetlen színsor mintára sem, akkor csak flush
        if(straightFlush.equals("0")){
            LOGGER.info("Since it is not a straigth and flush, it calculates the strength of flush!");
            int temp;
            int[] valueArray = new int[5];
            
            for(int c = 0; c < 5; ++c){
            
                valueArray[c] = getCardIntStrength(eos.charAt(c));
            }
            
            for(int x = 0; x < 5; ++x){
                    for(int y = 1; y < 5-x; ++y){
                        if(valueArray[y-1] <  valueArray[y]){
                            temp = valueArray[y-1];
                            valueArray[y-1] = valueArray[y];
                            valueArray[y] = temp;
                        }
                    }
                }
            LOGGER.info("After bubble sort, we got the decremented strength of the cards:");
            LOGGER.info("{0} {1} {2} {3} {4}", new Object[]{valueArray[0], valueArray[1], valueArray[2], valueArray[3], valueArray[4]});
            return new StringBuilder()
                               .append("FlushWith")
                               .append(getCardStrength(valueArray[0]))
                               .append("and")
                               .append(getCardStrength(valueArray[1]))
                               .append("and")
                               .append(getCardStrength(valueArray[2]))
                               .append("and")
                               .append(getCardStrength(valueArray[3]))
                               .append("and")
                               .append(getCardStrength(valueArray[4]))
                               .toString();
        }else{
        //ellenben a megfelelő típusú színsor
            LOGGER.info("It's a straight flush!!! ");
            return straightFlush + "Flush";
        }
   
    }
    //megnézzük, hogy sor-e
    String straight = getStraightType(eos);
    if(!straight.equals("0")){ 
        LOGGER.info("We have reached the Straigth phase, whitch means, it is not any kind of flush!");
        return straight; 
    }
    //ha a kézben nincs ismétlődés, akkor a lista mérete 5, ha van, akkor a méret csökken
    //ha pár van, akkor a méret 4.
    //ha drill, akkor a méret 3.
    //ha két pár, akkor a méret 3
    //ha full house, akkor 2
    //ha poker, akkor 2
    LOGGER.info("Now it is not a flush, neighter straight!");
    String cardStrength = "234567890JQKA";
    ArrayList<Integer> cardKey = new ArrayList<>();
    ArrayList<Integer> cardValue = new ArrayList<>();
    for(int i = 0; i < 5; ++i){
        toBreakTo:
        for(int j = 0; j < 13; ++j){
        
            if(player[i].charAt(player[i].length()-1) == cardStrength.charAt(j)){
            
                if(cardKey.isEmpty()){
                    cardKey.add(j);
                    cardValue.add(1);
                    break;
                }
                for(int k = 0; k < cardKey.size(); ++k){
                
                    if(cardKey.get(k).equals(j)){
                    
                        cardValue.set(k, cardValue.get(k)+1);
                        break toBreakTo;
                    }
                }
                cardKey.add(j);
                cardValue.add(1);
                break;
            }
        }
    }
    //mivel listába lettek rendeze, ezért jöhet a típus szerinti rendezés
    int size = cardKey.size();
    
    switch(size){
    //megnézzük, hogy póker-e, vagy fullhouse
        case 2:
            LOGGER.info("Let us see, if it is a Poker! ");
            //ha a másik értékből csak 1 van, akkor az egyértelműen póker, viszont 
            //egyezés esetén szükség van a kártya értékére
            if(cardValue.get(0) == 1 || cardValue.get(1) == 1){
                if(cardValue.get(0) > cardValue.get(1)){
                
                    return new StringBuilder()
                               .append("Poker")
                               .append(getCardStrength(cardKey.get(0))) 
                               .toString();
                }else{
                
                    return new StringBuilder()
                               .append("Poker")
                               .append(getCardStrength(cardKey.get(1))) 
                               .toString();
                }
            }
            //ha az előbbi nem teljesül, akkor full house
            LOGGER.info("Nope, it's not Poker, let's see, if it's a FullHouse!");
            if(cardValue.get(0) > cardValue.get(1)){
                
                    return new StringBuilder()
                               .append("FullHouse")
                               .append(getCardStrength(cardKey.get(0))) 
                               .toString();
                }else{
                
                    return new StringBuilder()
                               .append("FullHouse")
                               .append(getCardStrength(cardKey.get(1))) 
                               .toString();
                }
        case 3://ha van egyik kártya számúból 3, akkor drill
            LOGGER.info("Neighter Poker, nor FullHose, pray for a drill!");
            if(cardValue.get(0) == 3 || cardValue.get(1) == 3 || cardValue.get(2) == 3){
            
                int temp;
                //a tömb elejére rendezem a drill kártyát, ezeket az azonos típusok számosságából tudom megállapítani
                for(int x = 0; x < 3; ++x){
                    for(int y = 1; y < 3-x; ++y){
                        if(cardValue.get(y - 1) <  cardValue.get(y)){
                            temp = cardValue.get(y-1);
                            cardValue.set(y-1, cardValue.get(y));
                            cardValue.set(y, temp);
                            
                            temp = cardKey.get(y-1);
                            cardKey.set(y-1, cardKey.get(y));
                            cardKey.set(y, temp);
                        }
                    }
                }

                return new StringBuilder()
                               .append("Drill")
                               .append(getCardStrength(cardKey.get(0)))
                               .toString();
                
            }
            //elenkező esetben két pár
            LOGGER.info("Still not eneught, let's try with TwoPair!");
            int temp;
                //két pár esetén előre hozom a két számosságú kártyákat
                //pl A-1 5-2 K-2 ből lesz 5-2 K-2 A-1, ahol a kötőjel előtt
                //a kártya típusa áll(ász, király...), mögötte a számossága
                for(int x = 0; x < 3; ++x){
                    for(int y = 1; y < 3-x; ++y){
                        if(cardValue.get(y - 1) <  cardValue.get(y)){
                            temp = cardValue.get(y-1);
                            cardValue.set(y-1, cardValue.get(y));
                            cardValue.set(y, temp);
                            
                            temp = cardKey.get(y-1);
                            cardKey.set(y-1, cardKey.get(y));
                            cardKey.set(y, temp);
                        }
                    }
                }
                //majd ezek közül az erősebb kerül előre, előző példából:
                //K-2 5-2 A-1
                if(cardKey.get(0) < cardKey.get(1)){
                    temp = cardValue.get(0);
                    cardValue.set(0, cardValue.get(1));
                    cardValue.set(1, temp);
                    
                    temp = cardKey.get(0);
                    cardKey.set(0, cardKey.get(1));
                    cardKey.set(1, temp);
                }
                return new StringBuilder()
                               .append("TwoPair")
                               .append(getCardStrength(cardKey.get(0)))
                               .append("With")
                               .append(getCardStrength(cardKey.get(1)))
                               .append("and")
                               .append(getCardStrength(cardKey.get(2)))
                               .toString();
        case 4: //előre hozza a párt
            LOGGER.info("We can only hope in a Pair!");
                for(int x = 0; x < 4; ++x){
                    for(int y = 1; y < 4-x; ++y){
                        if(cardValue.get(y - 1) <  cardValue.get(y)){
                            temp = cardValue.get(y-1);
                            cardValue.set(y-1, cardValue.get(y));
                            cardValue.set(y, temp);
                            
                            temp = cardKey.get(y-1);
                            cardKey.set(y-1, cardKey.get(y));
                            cardKey.set(y, temp);
                        }
                    }
                }//majd a maradék három kártyát erősségi sorrendbe rendezi
                for(int x = 1; x < 4; ++x){
                    for(int y = 2; y < 4-x; ++y){
                        if(cardKey.get(y - 1) <  cardKey.get(y)){
                            temp = cardKey.get(y-1);
                            cardKey.set(y-1, cardKey.get(y));
                            cardKey.set(y, temp);
                            
                            temp = cardValue.get(y-1);
                            cardValue.set(y-1, cardValue.get(y));
                            cardValue.set(y, temp);
                        }
                    }
                }
                return new StringBuilder()
                               .append("Pair")
                               .append(getCardStrength(cardKey.get(0)))
                               .append("With")
                               .append(getCardStrength(cardKey.get(1)))
                               .append("and")
                               .append(getCardStrength(cardKey.get(2)))
                               .append("and")
                               .append(getCardStrength(cardKey.get(3)))
                               .toString();
        case 5://mivel itt nincs kártya ismétlődés, ezért nem kell foglalkozni
               //a számossággal, bőven elég erő szerint rendezni.
            LOGGER.info("Bad Luck, it's only HighCard!");
            for(int x = 0; x < 5; ++x){
                    for(int y = 1; y < 5-x; ++y){
                        if(cardKey.get(y - 1) <  cardKey.get(y)){
                            temp = cardKey.get(y-1);
                            cardKey.set(y-1, cardKey.get(y));
                            cardKey.set(y, temp);
                        }
                    }
                }
            return new StringBuilder()
                               .append("High")
                               .append(getCardStrength(cardKey.get(0)))
                               .append("With")
                               .append(getCardStrength(cardKey.get(1)))
                               .append("and")
                               .append(getCardStrength(cardKey.get(2)))
                               .append("and")
                               .append(getCardStrength(cardKey.get(3)))
                               .append("and")
                               .append(getCardStrength(cardKey.get(4)))
                               .toString();
    }
    
     return "0";   
    }
    /**
     * Assigns a character to the given numeric value.
     * 
     * <p>
     * NOTE: the cards number values are contained in an array, ordered by
     * incremental strength.
     * The integer value represents an array index.
     * </p>
     * 
     * @param card the index of the card number strength array
     * @return a character representation of the numeric strength of a card
     */
    private String getCardStrength(int card){
        
        switch(card){
        
            case 0: return "2";
            case 1: return "3";
            case 2: return "4";
            case 3: return "5";
            case 4: return "6";
            case 5: return "7";
            case 6: return "8";
            case 7: return "9";
            case 8: return "0";
            case 9: return "J";
            case 10: return "Q";
            case 11: return "K";
            case 12: return "A";
        }
        return "-1";
    }
    /**
     * Assaigns an integer value to the given character.
     * 
     * <p>
     * NOTE: this method needs a the "number" of the given card, for instance
     * "clubs7" has a color, whitch is "clubs" and a "number", whitch is "7". 
     * </p>
     * <p>
     * NOTE: the return value is an assigned nuber, whitch can be used, to
     * comprahanson. It is necessary to be an integer value, becouse in case of
     * "A", whitch represents the "Ace", it is hard to compare with numeric values.
     * </p>
     * 
     * @param card the "number" of the card
     * @return the assigned integer value
     */
    private int getCardIntStrength(char card){
    
        switch(card){
        
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case '0': return 10;
            case 'J': return 11;
            case 'Q': return 12;
            case 'K': return 13;
            case 'A': return 14;
        }
        return -1;
    }
    /**
     * Decides, if the given hand is a straigth or not.
     * 
     * @param hand an ordered String representation of the player's hand
     * @return "0" if the hand is not a straight, else the type of the straigth,
     *          for example "Straight6"
     */
    private String getStraightType(String hand){

        String[] straightCombinations = new String[]
        { "A2345", "23456", "34567", "45678", "56789", "67890", "7890J", "890JQ", "90JQK", "0JQKA" };
        
        boolean [] matches = new boolean[]{false, false, false, false, false};
        String straightFlushType;
        
        for(int i = 0; i < 10; ++i){
            
            straightFlushType = straightCombinations[i];
                    
            for(int j = 0; j < 5; ++j){
                for(int k = 0; k < 5; ++k){
                
                    if(straightFlushType.charAt(j) == hand.charAt(k)){
                    
                        matches[j] = true;
                        break;
                    }
                }//ha a keresett karakter nincs a kézben, akkor nem keresünk tövább, hanem keresünk egy másik színsort
                if(!matches[j]){
                
                    matches[0] = false; matches[1] = false; matches[2] = false; matches[3] = false; matches[4] = false;break;
                }
            }
            if(matches[0] && matches[1] && matches[2] && matches[3] && matches[4]){
            
                switch(i){
                    case 0: return "Straight5";
                    case 1: return "Straight6";
                    case 2: return "Straight7";
                    case 3: return "Straight8";
                    case 4: return "Straight9";
                    case 5: return "Straight0";
                    case 6: return "StraightJ";
                    case 7: return "StraightQ";
                    case 8: return "StraightK";
                    case 9: return "StraightA";
                }
            }
        }
        
        return "0";
    }
    /**
     * Decides who has the stronger hand, in case the two player has thy same 
     * strength type.
     * 
     * <p>
     * NOTE: if the two player has the same stregth type, for example
     * "Poker", than this method calclucates strength by
     * further parameters, for example "PokerWith5" vs. "PokerWithK"
     * </p>
     * 
     * @param calculatedHand a String array that contains the ordered strength
     *                       of the players hand
     * @param strength an integer value, whitch is given by the ordered strength 
     *                 of the ppayers hand
     * @return the stronger hand, if both are equivalent, than returns 3
     */
    private int all2Equivalent(String[] calculatedHand, int strength){
        
            int[] highCards = new int[2];
            
            switch(strength){
            
                case 11: return 3;
                case 10:
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(8));
                    }
                    
                    if( highCards[0] == highCards[1]){
                        return 3;
                    }
                    return highCards[0] > highCards[1] ? 1 : 2;
                case 9:
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(5));
                    }
                    return highCards[0] > highCards[1] ? 1 : 2;
                    
                case 8:
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(9));
                    }
                    return highCards[0] > highCards[1] ? 1 : 2;
                case 7:
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(9));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    //ha egyezik az első magas lap, akkor jöhet a második
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(13));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    //ha a második nem nyert, jön a harmadik
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(17));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    //jöhet a negyedik
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(21));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    //az ötödik
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(25));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    return 3;
                case 6:
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(8));
                    }
                    if( highCards[0] == highCards[1]){
                        return 3;
                    }
                    return highCards[0] > highCards[1] ? 1 : 2;
                case 5:
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(5));
                    }
                    return highCards[0] > highCards[1] ? 1 : 2;
                case 4:
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(7));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    //ha megegyezik a magasabbik pár, akkor nézzük az alacsonyabbikat
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(12));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    //ha még a második pár is megegyezik, akkor marad a magas lap
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(16));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    return 3;
                case 3:
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(4));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    //ha megegyezik a pár lap értéke, jöhet a magas lap
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(9));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    //ha az első magas lap sem nyert
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(13));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    //ha a második magas lap is megegyezik
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(12));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    return 3;
                case 2:
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(4));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    //ha egyezik az első magas lap, akkor jöhet a második
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(9));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    //ha a második nem nyert, jön a harmadik
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(13));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    //jöhet a negyedik
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(17));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    //az ötödik
                    for(int i = 0; i < 2; ++i){
                        highCards[i] = getCardIntStrength(calculatedHand[i].charAt(21));
                    }
                    if(highCards[0] > highCards[1]){return 1;}
                    if(highCards[0] < highCards[1]){return 2;}
                    return 3;
            }
        return -1;
    }
}
