package com.lumina.server.websocket;
//import com.lumina.server.handlers.ClientHandler;
import com.lumina.server.ServerContext;
import com.lumina.server.handlers.LoginHandler;
import com.lumina.server.handlers.RegistrationHandler;
import com.lumina.server.protocols.Request;
import com.lumina.server.protocols.Response;
import com.lumina.server.router.MessageRouter;
import jakarta.websocket.Session;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;

import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import com.google.gson.Gson;



@ServerEndpoint("/ws")
public class WebSocketServerEndpoint {

    private static ServerContext context;
    private static MessageRouter router = new MessageRouter();
    private static Gson gson;

    static {
        router.register("registration", new RegistrationHandler(context));
        router.register("login", new LoginHandler(context));
        // registrar outros handlers aqui
    }
    public static void setContext(ServerContext ctx) {
        context = ctx;
        gson = new Gson();

    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Conexão aberta: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Mensagem recebida: " + message);

        Request request = gson.fromJson(message, Request.class);

        Response response = router.route(request, context, session);

        String jsonResponse = gson.toJson(response);

        session.getBasicRemote().sendText(jsonResponse);

//        ClientHandler handler = new ClientHandler(session, context);
//
//        System.out.println("criou handler");
//        handler.handleMessage(message);

    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Conexão fechada: " + session.getId());
    }
}