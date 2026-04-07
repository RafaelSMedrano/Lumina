package com.lumina.server.sessions;

import jakarta.websocket.Session;

public class UserSession {

    private final String username;
    private final Session session;

    private long loginTime;
    private String currentRoom;
    private boolean authenticated;

    public UserSession(String username, Session session, boolean authenticated) {
        this.username = username;
        this.session = session;
        this.loginTime = System.currentTimeMillis();
        this.authenticated = authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getUsername() {
        return username;
    }

    public Session getWebsocketSession() {
        return session;
    }

    public long getLoginTime() {
        return loginTime;
    }
    public boolean getAuthenticated() {
        return authenticated;
    }
}