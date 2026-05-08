import { routeMessage } from "./messageRouter.js";
// ===============================
// VARIÁVEIS INTERNAS DO MÓDULO
// ===============================

const DEFAULT_SOCKET_URL = "ws://localhost:8080/ws";

// Guarda a única instância do WebSocket da aplicação.
// Começa como null porque ainda não existe conexão.
let socket = null;
let socketUrl = DEFAULT_SOCKET_URL;
let pendingMessages = [];

// ========================================
// FUNÇÃO PRINCIPAL — OBTER O SOCKET GLOBAL
// ========================================
export function initSocket(url = DEFAULT_SOCKET_URL){
    console.log("entrou do getsocket");
    socketUrl = url;

    // Se não existe socket OU ele foi fechado,
    // criamos uma nova conexão.
    if (!socket || socket.readyState === WebSocket.CLOSED) {

        console.log("Criando nova conexão WebSocket...");

        // Cria a conexão com o servidor
        socket = new WebSocket(url);

        // Quando a conexão abrir com sucesso
        socket.onopen = () => {
            console.log("WebSocket global conectado.");
            flushPendingMessages();
        };

        // Quando chega mensagem do servidor
        socket.onmessage = (event) => {

            console.log("Mensagem bruta recebida:", event.data);

                        try {

                            // Converte JSON texto -> objeto JS
                            const msg = JSON.parse(event.data);

                            // Envia para o router
                            routeMessage(msg);

                        } catch (err) {

                            console.error("Erro ao converter mensagem:", err);
                        }
        };

        // Se ocorrer erro na conexão
        socket.onerror = (err) => {
            console.error("Erro no WebSocket:", err);
        };

        // Quando a conexão fecha
        socket.onclose = () => {
            console.log("WebSocket fechado.");

            // Limpamos a referência para permitir reconexão futura
            socket = null;

            if (pendingMessages.length > 0) {
                console.log("WebSocket não conectado. Tentando conectar...");
                initSocket(socketUrl);
            }
        };
    }
    else {
        console.log("Já existe uma conexão websocket iniciada.")
    }

    return socket
}


export function getSocket(url = DEFAULT_SOCKET_URL) {
    console.log("entrou do getsocket");
    // Se não existe socket OU ele foi fechado,
    // criamos uma nova conexão.
    if (!socket || socket.readyState === WebSocket.CLOSED) {
        initSocket(url);
    }

    // Retorna sempre o mesmo socket (Singleton)
    return socket;
}

export function sendMessage(payload, url = DEFAULT_SOCKET_URL) {
    const message = typeof payload === "string" ? payload : JSON.stringify(payload);

    if (socket && socket.readyState === WebSocket.OPEN) {
        socket.send(message);
        return true;
    }

    console.log("WebSocket não conectado. Tentando conectar...");
    pendingMessages.push(message);

    if (!socket || socket.readyState === WebSocket.CLOSED) {
        initSocket(url);
    }

    return false;
}

function flushPendingMessages() {
    while (pendingMessages.length > 0 && socket && socket.readyState === WebSocket.OPEN) {
        socket.send(pendingMessages.shift());
    }
}

// ========================================
// FECHAR O SOCKET MANUALMENTE
// ========================================
export function closeSocket() {
    if (socket) {
        console.log("Fechando WebSocket manualmente...");
        socket.close();
        socket = null;
    }
}
