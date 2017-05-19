
package com.mycompany.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The UserStatistic class represents the user's statistic database entities.
 */
@Entity
@Table
public class UserStatistic {
    
    @Id
    private int id;
    private int win;
    private String hand;
    private int winAmount; 
    private int endMatch;

    public UserStatistic(int id, int win, String hand, int winAmount, int endMatch) {
        this.id = id;
        this.win = win;
        this.hand = hand;
        this.winAmount = winAmount;
        this.endMatch = endMatch;
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

    public int getEndMatch() {
        return endMatch;
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

    public void setEndMatch(int endMatch) {
        this.endMatch = endMatch;
    }

    @Override
    public String toString() {
        return "UserStatistic{" + "id=" + id + ", win=" + win + ", hand=" + hand + ", winAmount=" + winAmount + ", endMatch=" + endMatch + '}';
    }
}
