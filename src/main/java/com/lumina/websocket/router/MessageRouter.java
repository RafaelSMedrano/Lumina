package com.lumina.websocket.router;

import com.lumina.websocket.handlers.MessageHandler;
import com.lumina.websocket.protocol.Request;
import com.lumina.websocket.protocol.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageRouter {

    private static final Logger logger = LoggerFactory.getLogger(MessageRouter.class);

    private final Map<String, MessageHandler> routes = new HashMap<>();

    public MessageRouter(List<MessageHandler> handlers) {

        for (MessageHandler handler : handlers) {

            routes.put(handler.getType(), handler);

            logger.info("Registrado handler: {}", handler.getType());
        }
    }

    public Response route(Request request, WebSocketSession session) {
        MessageHandler handler = routes.get(request.type);

        if (handler == null) {
            return new Response(
                    responseTypeFor(request.type),
                    "error",
                    "Tipo de mensagem não reconhecido: " + request.type,
                    null
            );
        }

        try {
            Object data = handler.handle(request, session);

            return new Response(
                    responseTypeFor(request.type),
                    "success",
                    "Mensagem processada com sucesso.",
                    data
            );
        } catch (Exception e) {
            logger.error("Erro ao processar mensagem do tipo: {}", request.type, e);

            return new Response(
                    responseTypeFor(request.type),
                    "error",
                    "Erro interno ao processar mensagem.",
                    null
            );
        }
    }

    private String responseTypeFor(String requestType) {
        if (requestType.endsWith(".response")) {
            return requestType;
        }

        return requestType + ".response";
    }
}
