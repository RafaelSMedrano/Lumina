package com.lumina.server;

import com.lumina.server.db.UserDAO;

public class ServerContext {
    private static ServerState serverState;
    private UserDAO userDAO;

    public ServerContext(){
        this.userDAO = new UserDAO();
    }

    //A palavra-chave sychronized serve para evitar que duas ou mais threads executem ao
    // mesmo tempo o trecho de código.
    public static synchronized ServerState getServerState() {
        if(serverState == null) {
            serverState = new ServerState();
        }
        return serverState;
    }


}
