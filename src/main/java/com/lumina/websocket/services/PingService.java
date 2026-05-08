package com.lumina.websocket.services;

import com.lumina.websocket.dto.request.PingRequestDTO;
import com.lumina.websocket.dto.response.PingResponseDTO;
import org.springframework.stereotype.Service;

/*
 * Exemplo de service usado por um handler.
 * Aqui fica a regra de negocio do fluxo ping.
 */
@Service
public class PingService {

    public PingResponseDTO processPing(PingRequestDTO dto) {

        //busness rule
        if (!dto.content.equalsIgnoreCase("ping")) {
            throw new IllegalArgumentException("Mensagem inválida. Era esperado receber: ping.");
        }
        String serverMessage = "Pong! O servidor recebeu sua mensagem com sucesso.";

        return new PingResponseDTO(
                dto.content,
                serverMessage,
                System.currentTimeMillis()
        );
    }
}
