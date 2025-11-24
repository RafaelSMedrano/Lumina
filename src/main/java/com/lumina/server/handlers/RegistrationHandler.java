package com.lumina.server.handlers;

import com.lumina.server.protocols.RegisterCommand;
import com.lumina.server.protocols.Response;
import com.lumina.server.ServerState;

public class RegistrationHandler {
    private ServerState state;

    public RegistrationHandler(ServerState state) {
        this.state = state;
    }

    public Response handleRegistration(RegisterCommand cmd) {
        Response res = new Response();
        boolean success = state.registerUser(cmd.username, cmd.password);
        res.status = success ? true : false;
        res.message = success ? "Conta criada com sucesso." : "Usuário já existe.";
        return res;
    }
}