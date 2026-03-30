import { renderLoginWidget } from "../widgets/loginWidget.js";
import { renderRegistrationWidget } from "../widgets/registrationWidget.js";
import { initSocket } from "../socket.js"

//O que é addEventListener?
//É um metodo usado para registrar um "ouvinte" (listener) que será executado quando um evento acontecer.
//Sintaxe básica:initSocket();
//element.addEventListener("tipoDoEvento", função);


document.addEventListener("DOMContentLoaded", () => {
    console.log("entrou no eventlistener");
    initSocket(); // inicia a conexão

    const mainContainer = document.getElementById("main-container");

    mainContainer.innerHTML = `
        <div id="login-widget"></div>
        <div class="login-widget" id="action-area"></div>
    `;

    renderLoginWidget("login-widget");
    const actionArea = document.getElementById("action-area");

    actionArea.innerHTML = `
    <button id="register-btn">Register</button>
    `;

    document.getElementById("register-btn").onclick = () => {
        mainContainer.innerHTML = "";
        renderRegistrationWidget("main-container");
    };

});


// Eventos dos links
//document.addEventListener("click", (e) => {
//
//    if (e.target.id === "open-register") {
//        renderRegisterWidget(containerId);
//    }
//
//    if (e.target.id === "open-login") {
//        renderLoginWidget(containerId);
//    }
//
//});