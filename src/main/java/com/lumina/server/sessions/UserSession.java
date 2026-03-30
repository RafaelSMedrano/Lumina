package com.lumina.server.sessions;

import jakarta.websocket.Session;

public class UserSession {

    private final String username;
    private final Session websocketSession;

    private long loginTime;
    private String currentRoom;
    private boolean authenticated;

    public UserSession(String username, Session websocketSession, boolean authenticated) {
        this.username = username;
        this.websocketSession = websocketSession;
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
        return websocketSession;
    }

    public long getLoginTime() {
        return loginTime;
    }
    public boolean getAuthenticated() {
        return authenticated;
    }
}