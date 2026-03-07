package com.lumina.server.handlers;
import com.lumina.server.exceptions.UserRegistrationException;
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
        res.type = "registration-response";
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