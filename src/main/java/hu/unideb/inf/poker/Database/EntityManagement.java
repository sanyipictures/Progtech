package hu.unideb.inf.poker.Database;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *EntityManager represents the database manipulation.
 */
public class EntityManagement {
    private final EntityManagerFactory emfactory;
    private final EntityManager entitymanager;

    public EntityManagement() {
        this.emfactory = Persistence.createEntityManagerFactory("com.mycompany_Poker_jar_1.0-SNAPSHOTPU");
        this.entitymanager = emfactory.createEntityManager();
    }
    
    public void newEntity(int win, String hand, int prize){
        
        UserStatistic entityInstance = new UserStatistic(win, hand, prize);
        this.entitymanager.getTransaction().begin();
        
        entitymanager.persist( entityInstance );
        entitymanager.getTransaction( ).commit( );
    }
    
    public String getWinRate(){
    
        Query queryNumerator   = entitymanager.createQuery("Select count(e.win) from UserStatistic e where e.win = 1");
        Query queryDenominator = entitymanager.createQuery("Select count(e.win) from UserStatistic e");
        return Float.toString( Float.parseFloat(Long.toString((Long) queryNumerator.getSingleResult())) / Float.parseFloat(Long.toString((Long) queryDenominator.getSingleResult())));
    }
    
    public String getPrizeWinAvarage(){
    
        Query queryNumerator   = entitymanager.createQuery("Select sum(e.winAmount) from UserStatistic e where e.win = 1");
        Query queryDenominator = entitymanager.createQuery("Select count(e.win) from UserStatistic e");
        
        return Float.toString( Float.parseFloat(Long.toString((Long) queryNumerator.getSingleResult())) / Float.parseFloat(Long.toString((Long) queryDenominator.getSingleResult())));
    }
    public String getPrizeLostAvarage(){
    
        Query queryNumerator   = entitymanager.createQuery("Select sum(e.winAmount) from UserStatistic e where e.win = 0");
        Query queryDenominator = entitymanager.createQuery("Select count(e.win) from UserStatistic e");
        
        return Float.toString( Float.parseFloat(Long.toString((Long) queryNumerator.getSingleResult())) / Float.parseFloat(Long.toString((Long) queryDenominator.getSingleResult())));
    }
    public ArrayList<Long> getCardsPresentRate(){
        ArrayList<Long> avarages = new ArrayList<>();
        
        Query query = entitymanager.createQuery("Select count(e.hand) from UserStatistic e where e.hand LIKE 'High%' and e.win = 1");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("Select count(e.hand) from UserStatistic e where e.hand LIKE 'High%' and e.win = 0");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("Select count(e.hand) from UserStatistic e where e.hand LIKE 'High%' and e.win = 2");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand LIKE 'Pair%' and e.win = 1");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand LIKE 'Pair%' and e.win = 0");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand LIKE 'Pair%' and e.win = 2");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand LIKE 'TwoPair%' and e.win = 1");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand LIKE 'TwoPair%' and e.win = 0");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand LIKE 'TwoPair%' and e.win = 2");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand LIKE 'Drill%' and e.win = 1");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand LIKE 'Drill%' and e.win = 0");
        avarages.add((Long)query.getSingleResult());
        
        avarages.add(new Long(0));
        
        query = entitymanager.createQuery("SELECT count(e.win) FROM UserStatistic e WHERE e.hand = (SELECT e.hand FROM UserStatistic e WHERE e.hand not LIKE '%Flush' AND e.hand LIKE 'Straight%') and e.win = 1");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("SELECT count(e.win) FROM UserStatistic e WHERE e.hand = (SELECT e.hand FROM UserStatistic e WHERE e.hand not LIKE '%Flush' AND e.hand LIKE 'Straight%') and e.win = 0");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("SELECT count(e.win) FROM UserStatistic e WHERE e.hand = (SELECT e.hand FROM UserStatistic e WHERE e.hand not LIKE '%Flush' AND e.hand LIKE 'Straight%') and e.win = 2");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand LIKE 'Flush%' and e.win = 1");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand LIKE 'Flush%' and e.win = 0");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand LIKE 'Flush%' and e.win = 2");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("SELECT count(e.hand) from UserStatistic e WHERE e.hand LIKE 'FullHouse%' and e.win = 1");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("SELECT count(e.hand) from UserStatistic e WHERE e.hand LIKE 'FullHouse%' and e.win = 0");
        avarages.add((Long)query.getSingleResult());
        
        avarages.add(new Long(0));
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand LIKE 'Poker%' and e.win = 1");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand LIKE 'Poker%' and e.win = 0");
        avarages.add((Long)query.getSingleResult());
        
        avarages.add(new Long(0));
        
        query = entitymanager.createQuery("SELECT count(e.win) FROM UserStatistic e WHERE e.hand = (SELECT e.hand FROM UserStatistic e WHERE e.hand LIKE 'Straight%Flush') and e.win = 1");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("SELECT count(e.win) FROM UserStatistic e WHERE e.hand = (SELECT e.hand FROM UserStatistic e WHERE e.hand LIKE 'Straight%Flush') and e.win = 0");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("SELECT count(e.win) FROM UserStatistic e WHERE e.hand = (SELECT e.hand FROM UserStatistic e WHERE e.hand LIKE 'Straight%Flush') and e.win = 2");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand like 'RoyalFlush%' and e.win = 1");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand like 'RoyalFlush%' and e.win = 0");
        avarages.add((Long)query.getSingleResult());
        
        query = entitymanager.createQuery("select count(e.hand) from UserStatistic e where e.hand like 'RoyalFlush%' and e.win = 2");
        avarages.add((Long)query.getSingleResult());
        
        return avarages;
    }
    /**
     * Closes the database connection.
     */
    public void closeTransaction(){
    
        this.entitymanager.close();
        this.emfactory.close();
    }
}
