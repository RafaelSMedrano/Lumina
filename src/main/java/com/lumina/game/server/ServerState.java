package com.lumina.game.server;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;

/*
 * ServerState é o estado vivo do servidor.
 *
 * Banco de dados guarda informação permanente.
 * ServerState guarda informação temporária da execução atual.
 *
 * Exemplos:
 * - quem está online agora
 * - qual socket pertence a qual usuário
 * - personagens ativos no mapa
 * - salas/instâncias ativas
 */
@Component
public class ServerState {

    /*
     * ConcurrentHashMap é usado porque WebSocket é concorrente.
     *
     * Vários usuários podem conectar, desconectar e mandar mensagens
     * ao mesmo tempo.
     *
     * HashMap normal não é seguro para esse cenário.
     */
    private final ConcurrentHashMap<String, WebSocketSession> connectedSockets =
            new ConcurrentHashMap<>();

    /*
     * Salva uma conexão ativa.
     *
     * Chave: sessionId
     * Valor: WebSocketSession
     *
     * Por enquanto usamos sessionId porque ainda não autenticamos
     * o usuário no socket.
     */
    public void addSocket(String sessionId, WebSocketSession session) {
        connectedSockets.put(sessionId, session);
    }

    /*
     * Remove uma conexão ativa.
     *
     * Chamado quando o socket fecha.
     */
    public void removeSocket(String sessionId) {
        connectedSockets.remove(sessionId);
    }

    /*
     * Busca uma conexão pelo ID.
     *
     * Pode ser útil para enviar mensagem para um socket específico.
     */
    public WebSocketSession getSocket(String sessionId) {
        return connectedSockets.get(sessionId);
    }
}