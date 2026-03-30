package com.lumina.server;

import com.lumina.server.sessions.UserSession;
import jakarta.websocket.Session;

import javax.naming.AuthenticationException;
import java.util.concurrent.ConcurrentHashMap;

public class ServerState {

    private final ConcurrentHashMap<String, UserSession> loggedUsers = new ConcurrentHashMap<>();

    public void addUser(String username, UserSession userSession) {

        loggedUsers.put(username, userSession);
        System.out.println("adicionou ao state");
    }

    public void removeUser(String username) {
        loggedUsers.remove(username);
    }

    public boolean isLogged(String username) {
        return loggedUsers.containsKey(username);
    }
}