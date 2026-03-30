package com.lumina.server.handlers;

import com.lumina.server.DTO.LoginResponseDTO;
import com.lumina.server.ServerContext;
import com.lumina.server.db.UserDAO;
import com.lumina.server.exceptions.DataBaseException;
import com.lumina.server.models.User;
import com.lumina.server.protocols.LoginCommand;
import com.lumina.server.protocols.Response;
import com.lumina.server.ServerState;
import com.lumina.server.sessions.UserSession;
import jakarta.websocket.Session;
import javax.naming.AuthenticationException;
import java.sql.SQLException;

public class LoginHandler {

    private final ServerContext context;
    private final Session session;

    public LoginHandler(ServerContext context, Session session) {
        this.context = context;
        this.session = session;
    }

    public Response handleLogin(LoginCommand cmd) throws AuthenticationException {

        try {
            System.out.println("Entrou no LoginHandler");
            String username = cmd.username;
            String password = cmd.password;
            context.getAuthService().login(username, password);
            System.out.println("Autenticou");
            UserSession userSession = new UserSession(username, session, true);
            context.getServerState().addUser(username, userSession);
            System.out.println("adicionou ao serverState");
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO("username");
            Response res = new Response();
            res.type = "login-response";
            res.status = "success";
            res.message = "Login bem-sucedido, você está dentro don Lumina!";
            res.data = loginResponseDTO;
            return res;

        } catch (AuthenticationException e) {
            throw e;
        }
    }
}