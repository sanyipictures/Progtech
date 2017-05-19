package com.mycompany.database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Useres class represents the player Entity in the database.
 */
@Entity
@Table
public class Users {
    
    @Id
    private int id;
    private String username;
    private String password;
    private String email;

    public Users() {
        super();
    }
    
    public Users(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString(){
        return "ID: "+id+"Username: "+username+"PassWord:"+password+"E-mail: "+email;
    }
}
