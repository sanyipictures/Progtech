package TestPackage;

import hu.unideb.inf.poker.Modell.Dealer;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *Test for Dealer class.
 */
public class DealerTest {
    private final Dealer dealer = new Dealer();
    
    @Test
    public void RiffleingTest(){
        assertEquals(1, dealer.initializer());
    }
}
