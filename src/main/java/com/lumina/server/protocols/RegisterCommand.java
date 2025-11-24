package com.lumina.server.protocols;

public class RegisterCommand extends Command {
    public String username;
    public String password;

    public RegisterCommand(String username, String password) {
        this.password = password;
        this.username = username;
        this.type = "register";
    }
}
