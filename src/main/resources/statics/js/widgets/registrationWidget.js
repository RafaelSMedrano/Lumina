import { sendMessage, addMessageListener } from "../socket.js";
import { renderLoginWidget } from "./loginWidget.js";
export function renderRegistrationWidget(containerId){

    const container = document.getElementById(containerId);

    container.innerHTML = `
        <div class="login-widget">
            <input type="text" id="lw-username" placeholder="Username">
            <input type="email" id="lw-email" placeholder="email">
            <input type="password" id="lw-password" placeholder="Password">
            <button id="lw-registration-btn">Register</button>
            <div id="lw-response" class="response"></div>
        </div>
    `;

// 👇 LISTENER DO SERVIDOR
    addMessageListener((msg) => {
        console.log("recebeu msg");
        if (msg.type === "registration-response") {

            const container = document.getElementById("login-widget-container");
            const responseDiv = document.getElementById("lw-response");

            if (msg.status === "success") {
                container.innerHTML = "";
                responseDiv.innerHTML = `
                    <div style="text-align: center;">
                        Conta criada com sucesso! Faça login e entre no servidor.
                    </div>
                `;
                renderLoginWidget("login-widget-container");
            } else {
                responseDiv.innerText = msg.message;
            }
        }
    });

    document.getElementById("lw-registration-btn").onclick = () => {
                    const username = document.getElementById("lw-username").value;
                    const password = document.getElementById("lw-password").value;
                    const email = document.getElementById("lw-email").value;


                    if (!username || !password || !email) {
                        document.getElementById("lw-response").innerText = "Preencha todos os campos.";
                        return;
                    }

                    const payload = {
                        type: "register",
                        username,
                        password,
                        email,
                    };

                    sendMessage(payload);
                }
    }