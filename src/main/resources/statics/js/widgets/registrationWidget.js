import { sendMessage, addMessageListener } from "../socket.js";

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