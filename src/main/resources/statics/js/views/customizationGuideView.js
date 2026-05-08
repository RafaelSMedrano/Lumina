import { renderHomeView } from "./homeView.js";
import { sendMessage } from "../socket.js";

export function renderCustomizationGuideView(containerId, username) {

    const container = document.getElementById(containerId);

    container.innerHTML = `


            <h2>Customizando o Lumina</h2>

            <p>
                O Lumina utiliza uma arquitetura baseada em mensagens e handlers,
                tanto no front-end quanto no back-end.
            </p>

            <h3>Como o fluxo funciona</h3>

            <pre>
Frontend
↓
WebSocket
↓
Backend Router
↓
Backend Handler
↓
Resposta
↓
Frontend Router
↓
Frontend Handler
↓
Renderização da View
            </pre>

            <h3>Criando um novo recurso</h3>

            <p>
                Para adicionar recursos ou páginas que dependem do intercâmbio de dados entre font-end e back-end ao Lumina,
                normalmente você cria:
            </p>

            <ul>
                <li>Um handler no back-end</li>
                <li>Uma rota no MessageRouter do back-end</li>
                <li>Um handler no front-end</li>
                <li>Uma rota no MessageRouter do front-end</li>
                <li>Uma View para renderizar a interface</li>
            </ul>

            <h3>Exemplo de mensagem</h3>

            <pre>
{
    "type": "iinbox.open",
    "data": {}
}
            </pre>

            <h3>Backend</h3>

            <p>
                No back-end, em src/main/java/com/lumina/websocket/handlers/PingHandler.java você cria um handler Java responsável
                por tratar esse tipo de mensagem, adicionando a anotação @component do spring.
            </p>


            <p>
                Quando a mensagem chega pelo WebSocket,
                o router encontra automaticamente o handler correspondente
                e executa a lógica.
            </p>

            <h3>Frontend</h3>

            <p>
                No front-end, você também registra um handler
                para tratar a resposta enviada pelo servidor. No arquivo src/main/resources/statics/js/controllers/main.js adicione ao router:
            </p>

            <pre>
registerRoute("type", typeHandler);
            </pre>


            <p>
                O handler do front-end normalmente chama
                uma view responsável por renderizar a interface.
                Construa o handler em src/main/resources/statics/js/handlers/ e a view em src/main/resources/statics/js/views/
            </p>

            <pre>
export function typeHandler(msg) {
    renderTypeView(msg.data);
}

            <p>
                Dessa forma, o Lumina mantém uma arquitetura
                organizada, modular e escalável.
            </p>

            <h3> Exemplo </h3>

            <p>
                O botão a baixo manda uma requisição type = "ping"ao back-end, que devolve via dados a mensagem "pong".
            </p>

            <button id="ping-btn">ping</button>
            <div id="response"></div>

            <p>
                Para que esta acão ocorresse foram criados os arquivos abaixo. Use-os como exemplo!
            </p>

            <ul>
                <li>src/main/java/com/lumina/websocket/handlers/PingHandler.java</li>
                <li>src/main/java/com/lumina/websocket/services/PingService.java</li>
                <li>src/main/java/com/lumina/websocket/dto/request/PingRequestDTO.java</li>
                <li>src/main/java/com/lumina/websocket/dto/response/PingResponseDTO.java</li>
                <li>src/main/resources/statics/js/views/pingView.js</li>
                <li>src/main/resources/statics/js/handlers/pingHandler.js</li>

            </ul>

            <p>
                Além disso em src/main/resources/statics/js/controllers/main.js
                foi adicionado registerRoute("ping.response", pingHandler) ao router.
            </p>

            <p>
                Caso seja necessário adicionar regras de negócio à lógica, crie um service novo em src/main/java/com/lumina/websocket/services
                e use-o a partir do handler. Também opte por fazer a transferências de dados através de Data Transferer Object (DTO) em src/main/java/com/lumina/websocket/dto.
            </p>




            <button id="back-home-btn">Voltar</button>


    `;

    document.getElementById("ping-btn").onclick = () => {
            const request = {
                    type: "ping",
                    data: {
                        content: "ping"
                    }
                };

            sendMessage(request);
    }


    document.getElementById("back-home-btn").onclick = () => {
        renderHomeView("main-container", username);
    };
}
