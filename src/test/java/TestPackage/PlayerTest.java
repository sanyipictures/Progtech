package TestPackage;

import hu.unideb.inf.poker.Modell.Player;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *Test class for Player class.
 */
public class PlayerTest {
    private final Player player = new Player();
    
    @Test
    public void settersTest(){
        player.setBet(234);
        player.setCredit(56);
        player.setSelectedCards(1);
        assertEquals(234,player.getBet());
        assertEquals(56, player.getCredit());
        assertEquals(true, player.getSelectedCard(1));
        player.decrementBet(134);
        assertEquals(100, player.getBet());
        player.decrementCredit(6);
        assertEquals(50, player.getCredit());
        player.incrementBet(300);
        assertEquals(400, player.getBet());
        player.incrementCredit(250);
        assertEquals(300, player.getCredit());
        player.setAllSelectedCards();
        assertEquals(false, player.getSelectedCard(3));
        player.setHand(new String[]{"asd1", "asd2", "asd3", "asd4", "asd5"});
        Assert.assertArrayEquals(new String[]{"asd1","asd2","asd3","asd4","asd5"}, player.getHand());
        
    }
}
