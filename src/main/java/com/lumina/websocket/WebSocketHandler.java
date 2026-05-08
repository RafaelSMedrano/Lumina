package com.lumina.websocket;

import com.lumina.game.server.ServerState;
import com.lumina.websocket.protocol.Request;
import com.lumina.websocket.protocol.Response;
import com.lumina.websocket.router.MessageRouter;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/*
 * Esta classe é equivalente ao seu antigo endpoint WebSocket
 * no Java puro.
 *
 * Ela recebe:
 * - conexão nova
 * - mensagem de texto
 * - desconexão
 *
 * Depois, aqui você vai plugar seu MessageRouter.
 */
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    /*
     * ServerState guarda o estado atual do servidor:
     * usuários conectados, sockets ativos, personagens online etc.
     *
     * O Spring injeta o mesmo ServerState aqui.
     */
    private final ServerState serverState;
    private final MessageRouter messageRouter;
    private final Gson gson;
    /*
     * Construtor usado pelo Spring.
     *
     * Como ServerState também será um @Component,
     * o Spring cria ele e injeta aqui.
     */
    public WebSocketHandler(ServerState serverState, MessageRouter messageRouter) {
        this.serverState = serverState;
        this.messageRouter = messageRouter;
        this.gson = new Gson();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {


        logger.info("WebSocket conectado: {}", session.getId());
        //adiciona o user no serverState
        serverState.addSocket(session.getId(), session);

    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {

        String requestString= message.getPayload();
        logger.info("Mensagem recebida: {}", message.getPayload());

        Response response;
        try {
            Request request = gson.fromJson(requestString, Request.class);

            if (request == null || request.type == null) {
                response = new Response(
                        "error.response",
                        "error",
                        "Mensagem inválida. Era esperado um JSON com o campo type.",
                        null
                );
            } else {
                response = messageRouter.route(request, session);
            }
        } catch (Exception e) {
            logger.warn("Mensagem WebSocket inválida: {}", requestString, e);

            response = new Response(
                    "error.response",
                    "error",
                    "Mensagem inválida. Não foi possível converter o JSON recebido.",
                    null
            );
        }

        try {
            String jsonResponse = gson.toJson(response);
            session.sendMessage(new TextMessage(jsonResponse));
        } catch (IOException e) {
            logger.error("Erro ao enviar resposta WebSocket para sessao: {}", session.getId(), e);
            throw new RuntimeException(e);
        }


    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {

        logger.info("WebSocket desconectado: {}", session.getId());

        /*
         * Remove a conexão do ServerState.
         *
         * Isso evita manter usuário/sessão morta na memória.
         */
        serverState.removeSocket(session.getId());
    }
}
