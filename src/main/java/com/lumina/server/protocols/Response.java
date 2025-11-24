package com.lumina.server.protocols;

public class Response {
    public boolean status;
    public String message;

    public Response() {}

    public Response(boolean success, String message) {
        this.status = success;
        this.message = message;
    }
}