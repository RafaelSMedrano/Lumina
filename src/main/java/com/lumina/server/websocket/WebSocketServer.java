package com.lumina.server.websocket;

import org.glassfish.tyrus.server.Server;

public class WebSocketServer {

    public static void startServer() {
        Server server = new Server("localhost", 8080, "/", null, WebSocketServerEndpoint.class);
        try {
            server.start();
            System.out.println("Servidor WebSocket iniciado na porta 8080.");
            System.in.read(); // Aguarda enter para encerrar
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
