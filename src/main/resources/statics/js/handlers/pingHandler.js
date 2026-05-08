import { renderPingView } from "../views/pingView.js"

// Exemplo de handler do front-end para uma resposta WebSocket.
// Use este arquivo como referencia para tratar novas respostas.
export function pingHandler(response) {

    console.log("PingHandler recebeu:", response);

    // Procura a div onde vamos mostrar a resposta
    const responseDiv = document.getElementById("response");

    if (!responseDiv) {
        return;
    }

    // Renderiza a resposta do servidor
     renderPingView("response", response.data);
}
