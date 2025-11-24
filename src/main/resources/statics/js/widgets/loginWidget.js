let loginSocket;

function renderLoginWidget(containerId, socketUrl = "ws://localhost:8080/ws") {
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

    loginSocket = new WebSocket(socketUrl);

    loginSocket.onopen = () => {
        console.log("WebSocket (login) conectado.")
    }

    loginSocket.onmessage = (event) => {
        const response = JSON.parse(event.data);
        document.getElementById("lw-response").innerText = response.message || "Response received";
    }

    loginSocket.onerror = () => {
            document.getElementById("lw-response").innerText = "Erro na conexão WebSocket.";
        };

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

            if (loginSocket.readyState === WebSocket.OPEN) {
                loginSocket.send(JSON.stringify(loginPayload));
            } else {
                document.getElementById("lw-response").innerText = "Conexão WebSocket não está aberta.";
            }
        }
     document.getElementById("lw-registration-btn").onclick = () => {
                const username = document.getElementById("lw-username").value;
                const password = document.getElementById("lw-password").value;

                if (!username || !password) {
                    document.getElementById("lw-response").innerText = "Preencha todos os campos.";
                    return;
                }

                const loginPayload = {
                    type: "register",
                    username,
                    password
                };

                if (loginSocket.readyState === WebSocket.OPEN) {
                    loginSocket.send(JSON.stringify(loginPayload));
                } else {
                    document.getElementById("lw-response").innerText = "Conexão WebSocket não está aberta.";
                }
            }



}