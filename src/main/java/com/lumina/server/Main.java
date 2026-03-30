package com.lumina.server;

import com.lumina.server.websocket.WebSocketServer;
import com.lumina.server.websocket.WebSocketServerEndpoint;

//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

public class Main {
    public static void main(String[] args) {

        ServerState state = new ServerState();
        ServerContext context = new ServerContext(state);
        WebSocketServerEndpoint.setContext(context);
        WebSocketServer.startServer();
    }
}
