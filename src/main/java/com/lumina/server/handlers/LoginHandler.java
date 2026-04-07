package com.lumina.server.handlers;

import com.lumina.server.DTO.LoginResponseDTO;
import com.lumina.server.ServerContext;
import com.lumina.server.db.UserDAO;
import com.lumina.server.exceptions.DataBaseException;
import com.lumina.server.models.User;
import com.lumina.server.protocols.LoginCommand;
import com.lumina.server.protocols.Request;
import com.lumina.server.protocols.Response;
import com.lumina.server.ServerState;
import com.lumina.server.sessions.UserSession;
import jakarta.websocket.Session;
import javax.naming.AuthenticationException;
import com.google.gson.Gson;

import java.sql.SQLException;
import com.lumina.server.handlers.MessageHandler;

public class LoginHandler implements MessageHandler {

    private final ServerContext context;
    private Session session;
    private final Gson gson;

    public LoginHandler(ServerContext context) {
        this.context = context;
        this.session = null;
        gson = new Gson();
    }

    public LoginHandler(ServerContext context, Session session) {
        this.context = context;
        this.session = session;
        gson = new Gson();
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Response handle(Request request) {

        try {
            System.out.println("Entrou no LoginHandler");

            // 🔥 converter data → LoginCommand

            LoginCommand cmd = gson.fromJson(
                    gson.toJson(request.data),
                    LoginCommand.class
            );

            System.out.println("criou o login cmd");

            String username = cmd.username;
            String password = cmd.password;

            context.getAuthService().auth(username, password);
            System.out.println("Autenticou");

            UserSession userSession = new UserSession(username, session, true);
            context.getServerState().addUser(username, userSession);
            System.out.println("adicionou ao serverState");

            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(username);

            Response res = new Response();
            res.type = request.type; // 🔥 melhor prática
            res.status = "success";
            res.message = "Login bem-sucedido, você está dentro do Lumina!";
            res.data = loginResponseDTO;

            return res;
        } catch (Exception e) {
            Response res = new Response();
            res.type = request.type;
            res.status = "error";
            res.message = e.getMessage();
            res.data = null;
            return res;
        }

    }
}
//    public Response handle(LoginCommand cmd) throws AuthenticationException {
//
//        try {
//            System.out.println("Entrou no LoginHandler");
//            String username = cmd.username;
//            String password = cmd.password;
//            context.getAuthService().login(username, password);
//            System.out.println("Autenticou");
//            UserSession userSession = new UserSession(username, session, true);
//            context.getServerState().addUser(username, userSession);
//            System.out.println("adicionou ao serverState");
//            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(username);
//            Response res = new Response();
//            res.type = "login-response";
//            res.status = "success";
//            res.message = "Login bem-sucedido, você está dentro do Lumina!";
//            res.data = loginResponseDTO;
//            return res;
//
//        } catch (AuthenticationException e) {
//            throw e;
//        }
//    }
//}