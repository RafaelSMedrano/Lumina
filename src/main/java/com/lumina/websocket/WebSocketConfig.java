package com.lumina.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/*
 * Esta classe configura o WebSocket dentro do Spring.
 *
 * No Java puro/Tyrus, você usava algo como:
 *
 * @ServerEndpoint("/ws")
 *
 * No Spring, quem registra a rota "/ws" é esta classe.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    /*
     * Esse é o handler que vai cuidar das conexões WebSocket.
     *
     * O Spring injeta essa dependência automaticamente porque
     * LuminaWebSocketHandler está anotado com @Component.
     */
    private final WebSocketHandler luminaWebSocketHandler;

    /*
     * Construtor usado pelo Spring para injetar o handler.
     *
     * Isso é injeção de dependência.
     * Você não faz:
     *
     * new LuminaWebSocketHandler()
     *
     * O Spring cria e entrega para você.
     */
    public WebSocketConfig(WebSocketHandler luminaWebSocketHandler) {
        this.luminaWebSocketHandler = luminaWebSocketHandler;
    }

    /*
     * Método obrigatório da interface WebSocketConfigurer.
     *
     * É aqui que registramos quais URLs do servidor
     * aceitarão conexões WebSocket.
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        /*
         * Aqui estamos dizendo:
         *
         * "Quando alguém conectar em /ws,
         *  quem vai cuidar dessa conexão é luminaWebSocketHandler."
         *
         * Exemplo no front:
         *
         * const socket = new WebSocket("ws://localhost:8080/ws");
         */
        registry.addHandler(luminaWebSocketHandler, "/ws")

                /*
                 * Permite conexões vindas de qualquer origem.
                 *
                 * Em desenvolvimento, tudo bem.
                 *
                 * Em produção, o ideal é trocar "*" pelo domínio real,
                 * por exemplo:
                 *
                 * .setAllowedOrigins("https://lumina.com")
                 */
                .setAllowedOrigins("*");
    }
}
