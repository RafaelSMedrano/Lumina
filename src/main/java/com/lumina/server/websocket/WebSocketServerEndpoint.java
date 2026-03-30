package com.lumina.server.websocket;
import com.lumina.server.handlers.ClientHandler;
import com.lumina.server.ServerContext;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint("/ws")
public class WebSocketServerEndpoint {

    private static ServerContext context;

    public static void setContext(ServerContext ctx) {
        context = ctx;
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Conexão aberta: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Mensagem recebida: " + message);
        ClientHandler handler = new ClientHandler(session, context);

        System.out.println("criou handler");
        handler.handleMessage(message);

    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Conexão fechada: " + session.getId());
    }
}