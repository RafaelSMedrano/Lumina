import { sendMessage, addMessageListener } from "../socket.js";
import { renderHomeView } from "../views/homeView.js";

export function renderLoginWidget(containerId) {
    const container = document.getElementById(containerId);

    container.innerHTML = `
        <div class="login-widget">
            <input type="text" id="lw-username" placeholder="Username">
            <input type="password" id="lw-password" placeholder="Password">
            <button id="lw-login-btn">Login</button>
            <div id="lw-response" class="response"></div>
        </div>
    `;


    addMessageListener((msg) => {

        if(msg.type === "login"){

            const mainContainer = document.getElementById("main-container");
            const responseDiv = document.getElementById("lw-response");

            if(msg.status === "success"){

                // 🔥 troca de tela
                mainContainer.innerHTML = "";
                const username = msg.data.username;
                console.log(username);
                renderHomeView("main-container", username);

            } else {
                document.getElementById("lw-response").innerText = msg.message;
            }

        }

    });

    document.getElementById("lw-login-btn").onclick = () => {
            const username = document.getElementById("lw-username").value;
            const password = document.getElementById("lw-password").value;

            if (!username || !password) {
                document.getElementById("lw-response").innerText = "Preencha todos os campos.";
                return;
            }

            const payload = {
                type: "login",
                data: {
                    username,
                    password
                }
            };
            sendMessage(payload);

        }


}