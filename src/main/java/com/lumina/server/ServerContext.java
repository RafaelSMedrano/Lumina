package com.lumina.server;

public class ServerContext {
    private static ServerState serverState;

    //A palavra-chave sychronized serve para evitar que duas ou mais threads executem ao
    // mesmo tempo o trecho de código.
    public static synchronized ServerState getServerState() {
        if(serverState == null) {
            serverState = new ServerState();
        }
        return serverState;
    }

}
