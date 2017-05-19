package com.mycompany.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This class manipulates the database Entities.
 */
public class DatabaseController {
    
    private final EntityManagerFactory emFactory;
    private final EntityManager entityManager;
    
    public DatabaseController(){
    
        this.emFactory     = Persistence.createEntityManagerFactory("com.mycompany_Poker_jar_1.0-SNAPSHOTPU");
        this.entityManager = emFactory.createEntityManager();
    }
    
    public void createUser(int id, String username, String passwd, String email){
        Users user = new Users();
        
        entityManager.getTransaction().begin();
        
        
    }
    public void readUser(){
    //TODO
    }
    public void updateUser(){
    //TODO
    }
    public void deleteUser(){
    //TODO
    }
    public void createUserStatistic(){
    //TODO
    }
    public void readUserStatistic(){
    //TODO
    }
    public void updateUserStatistic(){
    //TODO
    }
    public void deleteUserStatistic(){
    //TODO
    }
}
