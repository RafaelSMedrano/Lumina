

// Exemplo de view usada por um handler do front-end.
// Ela renderiza o data recebido na resposta ping.
export function renderPingView(containerId, responseData){

    const container = document.getElementById(containerId);
    const msg = responseData.serverMessage;

    container.innerHTML = `
        ${msg}
    `;

}
