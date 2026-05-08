import { renderLoginWidget } from "../widgets/loginWidget.js";
import { renderRegistrationWidget } from "../widgets/registrationWidget.js";
import { registerRoute } from "../messageRouter.js";
import { pingHandler } from "../handlers/pingHandler.js";



// ========================================
// EVENTO DISPARADO QUANDO O HTML TERMINA
// DE CARREGAR
// ========================================
document.addEventListener("DOMContentLoaded", () => {

    console.log("Lumina iniciado.");

    registerRoute("ping.response", pingHandler);


    // ========================================
    // CONTAINER PRINCIPAL
    // ========================================
    const mainContainer = document.getElementById("main-container");

    mainContainer.innerHTML = `
        <div id="login-widget"></div>
        <div class="auth" id="action-area">
            <button id="register-btn">Register</button>
        </div>
        <div id="lw-response" class="auth"></div>
    `;

    renderLoginWidget("login-widget");

    document.getElementById("register-btn").onclick = () => {

        mainContainer.innerHTML = "";

        renderRegistrationWidget("main-container");
    };

});

