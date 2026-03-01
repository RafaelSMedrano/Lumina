package com.lumina.server.handlers;

import com.lumina.server.protocols.RegisterCommand;
import com.lumina.server.protocols.Response;
import com.lumina.server.ServerState;
import com.lumina.server.services.UserService;

public class RegistrationHandler {
    private ServerState state;

    public RegistrationHandler(ServerState state) {
        this.state = state;
    }

    public static Response handleRegistration(RegisterCommand cmd) {
        Response res = new Response();
        UserService userService = new UserService();
        boolean success = userService.registerUser(cmd);
        res.status = success ? true : false;
        res.message = success ? "Conta criada com sucesso." : "Usuário já existe.";
        return res;
    }

}