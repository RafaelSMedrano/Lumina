package com.lumina.websocket.dto.request;

/*
 * Exemplo de DTO de entrada para uma mensagem WebSocket.
 */
public class PingRequestDTO {

    public String content;

    public PingRequestDTO() {
    }

    public PingRequestDTO(String content) {
        this.content = content;
    }
}
