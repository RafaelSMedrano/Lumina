package com.lumina.websocket.handlers;
import com.lumina.websocket.protocol.Request;
import org.springframework.web.socket.WebSocketSession;

public interface MessageHandler {
    public String getType();
    Object handle(Request request, WebSocketSession session);

}
