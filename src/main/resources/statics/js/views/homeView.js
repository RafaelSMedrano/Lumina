export function renderHomeView(containerId, username){

    const container = document.getElementById(containerId);

    container.innerHTML = `
        <div class="home-view">
           <h2>Bem-vindo, ${username}!</h2>
            <p>Você está logado 🎉</p>

            <button id="logout-btn">Logout</button>
        </div>
    `;

    document.getElementById("logout-btn").onclick = () => {
        location.reload(); // simples por enquanto
    };
}