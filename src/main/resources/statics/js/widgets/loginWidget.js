import { sendMessage, addMessageListener } from "../socket.js";

export function renderLoginWidget(containerId) {
    const container = document.getElementById(containerId);

    container.innerHTML = `
        <div class="login-widget">
            <input type="text" id="lw-username" placeholder="Username">
            <input type="password" id="lw-password" placeholder="Password">
            <button id="lw-login-btn">Login</button>
            <button id="lw-registration-btn">Register</button>
            <div id="lw-response" class="response"></div>
        </div>
    `;

    document.getElementById("lw-login-btn").onclick = () => {
            const username = document.getElementById("lw-username").value;
            const password = document.getElementById("lw-password").value;

            if (!username || !password) {
                document.getElementById("lw-response").innerText = "Preencha todos os campos.";
                return;
            }

            const loginPayload = {
                type: "login",
                username,
                password
            };
            sendMessage(payload);

        }


}