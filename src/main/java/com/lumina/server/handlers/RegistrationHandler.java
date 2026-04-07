package com.lumina.server.handlers;
import com.lumina.server.ServerContext;
import com.lumina.server.exceptions.UserRegistrationException;
import com.lumina.server.protocols.RegisterCommand;
import com.lumina.server.protocols.Request;
import com.lumina.server.protocols.Response;
import com.lumina.server.ServerState;
import com.lumina.server.services.UserService;
import com.google.gson.Gson;
import jakarta.websocket.Session;

public class RegistrationHandler implements MessageHandler {
    private final ServerContext context;
    private final Gson gson;
    private Session session;

    public RegistrationHandler(ServerContext context) {
        this.context = context;
        gson = new Gson();
        this.session = null;
    }

    public RegistrationHandler(ServerContext context, Session session) {
        this.context = context;
        gson = new Gson();
        this.session = session;
    }

    public void setSession(Session session){
        this.session = session;
    }


    public Response handle(Request request) {

        RegisterCommand cmd = gson.fromJson(
                gson.toJson(request.data),
                RegisterCommand.class
        );

        Response res = new Response();
        UserService userService = new UserService();
        res.type = request.type;
        try {
            userService.registerUser(cmd);
            res.status = "success";
            res.message = "Conta criada com sucesso.";
            System.out.println("Usuário registrado.");

            } catch (UserRegistrationException e) {
                System.out.println("erro no registro.");
                res.status = "error";
                res.message = e.getMessage();      }
        System.out.println("retornando protocolo res");
        return res;

    }

}