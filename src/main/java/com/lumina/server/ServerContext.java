package com.lumina.server;

import com.lumina.server.db.UserDAO;
import com.lumina.server.services.AuthService;

public class ServerContext {
    private static ServerState state;
    private static AuthService authService;

    public ServerContext(ServerState state){
        this.state = state;
    }

    //A palavra-chave sychronized serve para evitar que duas ou mais threads executem ao
    // mesmo tempo o trecho de código.
    public static synchronized ServerState getServerState() {
        if(state == null) {
            state = new ServerState();
        }
        return state;
    }
    public static synchronized AuthService getAuthService() {
        System.out.println("passou o authService pelo context");
        if(authService == null) {
            UserDAO userDAO = new UserDAO();
            authService = new AuthService(userDAO);
        }
        return authService;
    }
}
