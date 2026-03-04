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

    const loginContainer = document.getElementById("login-widget-container");
    renderLoginWidget("login-widget-container");

    const actionContainer = document.getElementById("lw-registration-btncontainer");

    actionContainer.innerHTML = `
        <div class="login-widget">
            <button id="register-btn">Register</button>
        </div>
    `;

    document.getElementById("register-btn").onclick = () => {
        loginContainer.innerHTML = "";
        actionContainer.innerHTML = "";
        renderRegistrationWidget("login-widget-container");
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