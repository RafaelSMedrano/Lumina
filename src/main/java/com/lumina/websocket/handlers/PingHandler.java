package com.lumina.websocket.handlers;

import com.google.gson.Gson;
import com.lumina.websocket.dto.request.PingRequestDTO;
import com.lumina.websocket.protocol.Request;
import com.lumina.websocket.services.PingService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/*
 * Exemplo de handler WebSocket.
 * Use este fluxo ping como referencia para criar novos handlers.
 */
@Component
public class PingHandler implements MessageHandler {

    private final PingService pingService;
    private final Gson gson = new Gson();

    public PingHandler(PingService pingService) {
        this.pingService = pingService;
    }

    @Override
    public String getType() {
        return "ping";
    }

    @Override
    public Object handle(Request request, WebSocketSession session) {

        PingRequestDTO dto = gson.fromJson(
                gson.toJson(request.data),
                PingRequestDTO.class
        );

        return pingService.processPing(dto);
    }
}
