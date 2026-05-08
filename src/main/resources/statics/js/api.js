// api.js
// Este arquivo centraliza as chamadas HTTP do front para o backend Spring Boot.
// Assim, os widgets/telas não precisam saber os detalhes do fetch, URLs, headers etc.

export async function login(username, password) {
    // fetch faz uma requisição HTTP para o backend.
    // Como isso demora, usamos await para esperar a resposta chegar.
    const response = await fetch("http://localhost:8080/auth/login", {
        // POST é usado porque estamos enviando dados para o servidor.
        method: "POST",

        // Avisamos ao Spring que o corpo da requisição está em JSON.
        headers: {
            "Content-Type": "application/json"
        },

        // Converte o objeto JavaScript para JSON antes de enviar.
        body: JSON.stringify({
            username: username,
            password: password
        })
    });

    // response.json() também é assíncrono.
    // Ele lê o corpo da resposta e transforma o JSON em objeto JavaScript.
    console.log("HTTP status login:", response.status);

    const text = await response.text();
    console.log("Resposta bruta login:", text);

    return JSON.parse(text);
}

export async function registration(username, password, email) {
    // Chama o endpoint de cadastro do Spring Boot.
    const response = await fetch("http://localhost:8080/auth/registration", {
        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({
            username: username,
            password: password,
            email: email
        })
    });

    // Retorna a resposta do servidor já convertida para objeto JS.
    console.log("HTTP status registration:", response.status);

    const text = await response.text();
    console.log("Resposta bruta registration:", text);

    return JSON.parse(text);

}