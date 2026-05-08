import { renderCustomizationGuideView } from "./customizationGuideView.js"

export function renderHomeView(containerId, username){

    const container = document.getElementById(containerId);

    container.innerHTML = `
        <div class="home-view">
           <h2>Bem-vindo, ${username}!</h2>
            <p>Você está logado 🎉</p>

            <button id="logout-btn">Logout</button>
            <button id="customize-btn">Como customizar sua aplicação</button>
        </div>
    `;

    document.getElementById("customize-btn").onclick = () => {

        renderCustomizationGuideView(containerId, username);

    };

//    document.getElementById("ping-btn").onclick = () => {
//
//        const payload = {
//                        type: "ping",
//                        data: ""
//                    };
//                    sendMessage(payload);
//
//    };

}
