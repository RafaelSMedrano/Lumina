// ========================================
// messageRouter.js
// ========================================

// Map que associa:
// tipo da mensagem -> função handler
//
// Exemplo:
// "ping" -> pingHandler
// "chat" -> chatHandler
const routes = new Map();


// ========================================
// REGISTRAR UMA ROTA
// ========================================
export function registerRoute(type, handler) {

    // Associa o tipo da mensagem ao handler
    routes.set(type, handler);

    console.log(`Handler registrado para: ${type}`);
}


// ========================================
// REMOVER UMA ROTA
// ========================================
export function unregisterRoute(type) {

    routes.delete(type);

    console.log(`Handler removido para: ${type}`);
}


// ========================================
// ROTEAR MENSAGEM RECEBIDA
// ========================================
export function routeMessage(msg) {

    console.log("Mensagem recebida no router:", msg);

    // Procura o handler correspondente ao tipo
    const handler = routes.get(msg.type);

    // Se não existir handler
    if (!handler) {

        console.warn(`Nenhum handler registrado para: ${msg.type}`);
        return;

    }

    // Executa o handler encontrado
    handler(msg);
}