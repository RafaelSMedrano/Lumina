let socket = new WebSocket("ws://localhost:8080/ws");

socket.onopen = function () {
    console.log("Conectado ao WebSocket.");
};

socket.onmessage = function (event) {
    document.getElementById("resposta").innerText = "Servidor respondeu: " + event.data;
};

function enviarMensagem() {
    socket.send("ping");
}