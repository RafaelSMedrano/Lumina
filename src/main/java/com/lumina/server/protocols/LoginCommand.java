package com.lumina.server.protocols;

public class LoginCommand extends Command {
    public String username;
    public String password;

    public LoginCommand(String username, String password){
        this.password = password;
        this.username = username;
        this.type = "login";
    }


}