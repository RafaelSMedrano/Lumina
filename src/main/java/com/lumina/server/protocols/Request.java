package com.lumina.server.protocols;

public class Request {

    public String type;
    public Object data;

    public Request() {}

    public Request(String type, Object data) {
        this.type = type;
        this.data = data;
    }

}
