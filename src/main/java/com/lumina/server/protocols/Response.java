package com.lumina.server.protocols;

public class Response {
    public String type;
    public String status;
    public String message;
    public Object data;

    public Response() {}

    public Response(String type, String status, String message, Object data ) {
        this.type = type;
        this.status = status;
        this.message = message;
        this.data = data;
    }
}