package com.lumina.server.handlers;

import com.lumina.server.protocols.LoginCommand;
import com.lumina.server.protocols.Response;
import com.lumina.server.ServerState;

public class AuthHandler {
    private ServerState state;

    public AuthHandler(ServerState state) {
        this.state = state;
    }

    public Response handleLogin(LoginCommand cmd) {
        Response res = new Response();
        boolean success = state.login(cmd.username, cmd.password);
        //res.status = success ? "ok" : "fail";
        res.message = success ? "Login bem-sucedido." : "Usuário ou senha inválidos.";
        System.out.println(res.message);
        return res;
    }
    
}