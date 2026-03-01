
// ===============================
// VARIÁVEIS INTERNAS DO MÓDULO
// ===============================

// Guarda a única instância do WebSocket da aplicação.
// Começa como null porque ainda não existe conexão.
let socket = null;

// Lista de funções (callbacks) que querem ser avisadas
// sempre que chegar uma mensagem do servidor.
// Isso implementa o padrão "Observer".
let listeners = [];


// ========================================
// FUNÇÃO PRINCIPAL — OBTER O SOCKET GLOBAL
// ========================================
export function initSocket(url = "ws://localhost:8080/ws"){
    console.log("entrou do getsocket");
    // Se não existe socket OU ele foi fechado,
    // criamos uma nova conexão.
    if (!socket || socket.readyState === WebSocket.CLOSED) {

        console.log("Criando nova conexão WebSocket...");

        // Cria a conexão com o servidor
        socket = new WebSocket(url);

        // Quando a conexão abrir com sucesso
        socket.onopen = () => {
            console.log("WebSocket global conectado.");
        };

        // Quando chega mensagem do servidor
        socket.onmessage = (event) => {

            // Converte o texto JSON recebido para objeto JS
            const data = JSON.parse(event.data);

            // Envia essa mensagem para TODOS que estão "inscritos"
            // no sistema de listeners
            listeners.forEach(callback => {
                callback(data);
            });
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
        };
    }
    else {
        console.log("Já existe uma conexão websocket iniciada.")
    }

}


export function getSocket(url = "ws://localhost:8080/ws") {
    console.log("entrou do getsocket");
    // Se não existe socket OU ele foi fechado,
    // criamos uma nova conexão.
    if (!socket || socket.readyState === WebSocket.CLOSED) {
        initSocket();
    }

    // Retorna sempre o mesmo socket (Singleton)
    return socket;
}

export function sendMessage(payload) {
    if (socket && socket.readyState === WebSocket.OPEN) {
        socket.send(JSON.stringify(payload));
    } else {
        console.error("WebSocket não está aberto.");
    }
}


// ========================================
// ADICIONAR "OUVINTE" DE MENSAGENS
// ========================================
export function addMessageListener(callback) {
    // Guarda a função que quer escutar mensagens
    listeners.push(callback);
}


// ========================================
// REMOVER "OUVINTE" DE MENSAGENS
// (importante quando um widget é fechado)
// ========================================
export function removeMessageListener(callback) {
    // Remove apenas o callback específico
    listeners = listeners.filter(cb => cb !== callback);
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