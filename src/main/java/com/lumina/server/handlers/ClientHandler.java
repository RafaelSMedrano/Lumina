package com.lumina.server.handlers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lumina.server.ServerState;
import com.lumina.server.protocols.Command;
import com.lumina.server.protocols.LoginCommand;
import com.lumina.server.protocols.RegisterCommand;
import com.lumina.server.protocols.Response;
import java.io.IOException;
import jakarta.websocket.Session;

public class ClientHandler{

    private final Gson gson = new Gson();
    private final AuthHandler auth;

    private final Session session;

    public ClientHandler(Session session, ServerState state) {
        this.session = session;
        this.auth          = new AuthHandler(state);

    }

    public void handleMessage(String message) throws IOException {

        Command baseCmd;

        if(message == null){
            System.out.println("No messagem from client.");
        }
        else{
            try {
                baseCmd = gson.fromJson(message, Command.class);
                System.out.println("JSOM FORMOU");
                switch (baseCmd.type) {

                    case "register" -> {

                        RegisterCommand regCmd = gson.fromJson(message, RegisterCommand.class);
                        Response resp = RegistrationHandler.handleRegistration(regCmd);
                        System.out.println("criou o protocolo de resposta");
                        System.out.flush();
                        session.getBasicRemote().sendText(gson.toJson(resp));
                        System.out.println("enviou a resposta JSON");

                    }

                    case "login" -> {

                        LoginCommand loginCmd = gson.fromJson(message, LoginCommand.class);
                        Response resp = auth.handleLogin(loginCmd);
                        session.getBasicRemote().sendText(gson.toJson(resp));
                    }

                    default -> sendError("Comando desconhecido: " + baseCmd.type);

                }

            }
            catch (JsonSyntaxException | IOException ex) {
                sendError("JSON mal-formado.");
            }

        }



    }
    private void sendError(String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }
}
