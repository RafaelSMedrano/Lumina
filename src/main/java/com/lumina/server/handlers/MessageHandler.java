package com.lumina.server.handlers;

import com.lumina.server.protocols.Response;
import com.lumina.server.protocols.Request;

import jakarta.websocket.Session;

public interface MessageHandler {
    Response handle(Request request);
    void setSession(Session session);
}
