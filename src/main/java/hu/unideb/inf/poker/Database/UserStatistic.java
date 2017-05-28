/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.poker.Database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserStatistic represents an Entity for JPA.
 */
@Entity
@Table
public class UserStatistic{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO) 
   private int id;
   private int win;
   private String hand;
   private int winAmount;

    public UserStatistic() {
    }

    public UserStatistic(int win, String hand, int winAmount) {
        this.win = win;
        this.hand = hand;
        this.winAmount = winAmount;
    }

    public int getId() {
        return id;
    }

    public int getWin() {
        return win;
    }

    public String getHand() {
        return hand;
    }

    public int getWinAmount() {
        return winAmount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public void setWinAmount(int winAmount) {
        this.winAmount = winAmount;
    }

    @Override
    public String toString() {
        return "UserStatistic{" + "id=" + id + ", win=" + win + ", hand=" + hand + ", winAmount=" + winAmount + '}';
    }
   
   
}
