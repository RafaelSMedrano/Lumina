package com.lumina.websocket.dto.response;

/*
 * Exemplo de DTO de saida retornado por um handler WebSocket.
 */
public class PingResponseDTO {

    public String originalMessage;
    public String serverMessage;
    public long timestamp;

    public PingResponseDTO() {
    }

    public PingResponseDTO(String originalMessage, String serverMessage, long timestamp) {
        this.originalMessage = originalMessage;
        this.serverMessage = serverMessage;
        this.timestamp = timestamp;
    }
}
