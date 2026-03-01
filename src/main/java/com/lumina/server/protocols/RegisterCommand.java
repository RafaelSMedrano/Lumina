package com.lumina.server.protocols;

public class RegisterCommand extends Command {
    public String username;
    public String password;
    public String email;

    public RegisterCommand(String username, String password, String email) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.type = "register";
    }
}
