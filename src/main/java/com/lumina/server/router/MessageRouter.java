package com.lumina.server.router;

import com.lumina.server.ServerContext;
import com.lumina.server.protocols.Request;
import com.lumina.server.protocols.Response;
import com.lumina.server.handlers.MessageHandler;

import jakarta.websocket.Session;
import java.util.HashMap;
import java.util.Map;

public class MessageRouter {

    private Map<String, MessageHandler> routes = new HashMap<>();

    public void register(String type, MessageHandler handler) {
        routes.put(type, handler);
    }

    public Response route(Request request, ServerContext context, Session session) {
        MessageHandler handler = routes.get(request.type);
        handler.setSession(session);

        if (handler == null) {
            return new Response(
                    request.type,
                    "error",
                    "Unknown message type",
                    null
            );
        }

        return handler.handle(request);
    }
}
