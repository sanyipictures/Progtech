package TestPackage;

import hu.unideb.inf.poker.Modell.WinnerCalculator;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *Test class for WinnerCalculator.
 */
public class WinnerCalculatorTest {
    
    private final WinnerCalculator wc = new WinnerCalculator();
    
    @Test
    public void playerCalculateHandTest(){
        assertEquals(3, this.wc.playerCalculateHand(new String[]{"clubs2", "clubsA", "heartsQ", "clubsQ", "diamonds9"}));
    }
    @Test
    public void winnerPlayerTest(){
        assertEquals(1, this.wc.winnerPlayer(new String[]{"clubs2", "clubsA", "heartsQ", "clubsQ", "diamonds9"}, new String[]{"clubs2", "clubsA", "heartsQ", "clubs3", "diamonds9"}));
    }
    @Test
    public void calculateStrengthTest(){
        assertEquals("Poker2", this.wc.calculateStrength(new String[]{"clubs2", "clubsA", "hearts2", "spades2", "diamonds2"}));
    }
}
