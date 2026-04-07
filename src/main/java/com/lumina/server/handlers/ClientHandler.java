//package com.lumina.server.handlers;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonSyntaxException;
//import com.lumina.server.ServerContext;
//import com.lumina.server.ServerState;
//import com.lumina.server.exceptions.DataBaseException;
//import com.lumina.server.protocols.Command;
//import com.lumina.server.protocols.LoginCommand;
//import com.lumina.server.protocols.RegisterCommand;
//import com.lumina.server.protocols.Response;
//import java.io.IOException;
//import jakarta.websocket.Session;
//import javax.naming.AuthenticationException;
//
//
//public class ClientHandler{
//    private final ServerContext context;
//    private final Gson gson = new Gson();
//    private final Session session;
//    private final ServerState state;
//
//    public ClientHandler(Session session, ServerContext context) {
//        this.session = session;
//        this.context = context;
//        this.state = context.getServerState();
//    }
//
//    public void handleMessage(String message) throws IOException {
//
//        Command baseCmd;
//
//        if(message == null){
//            System.out.println("No messagem from client.");
//        }
//        else{
//            try {
//                baseCmd = gson.fromJson(message, Command.class);
//                System.out.println("JSOM FORMOU");
//                switch (baseCmd.type) {
//
//                    case "register" -> {
//                        RegistrationHandler registration = new RegistrationHandler(state);
//                        RegisterCommand regCmd = gson.fromJson(message, RegisterCommand.class);
//                        Response resp = registration.handleRegistration(regCmd);
//                        session.getBasicRemote().sendText(gson.toJson(resp));
//
//                    }
//
//                    case "login" -> {
//
//                        try {
//                            System.out.println("roteou para login no clientHandler");
//                            LoginCommand loginCmd = gson.fromJson(message, LoginCommand.class);
//                            System.out.println("Formou JSON de login.");
//                            LoginHandler login = new LoginHandler(context, session);
//                            System.out.println("criou handler de login");
//                            Response resp = login.handle(loginCmd);
//                            session.getBasicRemote().sendText(gson.toJson(resp));
//                            System.out.println("logou");
//                        } catch (DataBaseException e) {
//                            Response resp = new Response();
//                            resp.message = e.getMessage();
//                            session.getBasicRemote().sendText(gson.toJson(resp));
//                        } catch (AuthenticationException e){
//                            Response resp = new Response();
//                            resp.message = e.getMessage();
//                            session.getBasicRemote().sendText(gson.toJson(resp));
//                        }
//
//                    }
//
//                    default -> sendError("Comando desconhecido: " + baseCmd.type);
//
//                }
//
//            }
//            catch (JsonSyntaxException | IOException ex) {
//                sendError("JSON mal-formado.");
//            }
//
//        }
//
//
//
//    }
//    private void sendError(String message) throws IOException {
//        session.getBasicRemote().sendText(message);
//    }
//}
