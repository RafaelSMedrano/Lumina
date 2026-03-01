package com.lumina.server.models;

import java.sql.Timestamp;
import java.time.Instant;

public class User {
    private int  id;
    private String userName;
    private String passwordHash;
    private String email;
    private Timestamp createdAt;
    

    public User(String userName, String passwordHash, String email){
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.email = email;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }


    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
