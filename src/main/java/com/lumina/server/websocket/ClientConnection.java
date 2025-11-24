package com.lumina.server.websocket;

import com.google.gson.Gson;
import com.lumina.server.protocols.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection {
    private final PrintWriter out;
    private final BufferedReader in;
    private static ClientConnection instance;

    // Construtor PRIVADO (evita criação externa)
    private ClientConnection(Socket socket) throws IOException {
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Conexão estabelecida com sucesso.");
    }

    // Método estático para obter/criar a instância (Singleton)
    public static void initialize(Socket socket) throws IOException {
        if (instance == null) {
            instance = new ClientConnection(socket);
        }
    }

    // Métodos estáticos para enviar/receber dados
    public static void sendCommand(Object command, Gson gson) {
        if (instance == null) {
            throw new IllegalStateException("Conexão não inicializada. Chame ClientConnection.initialize() primeiro.");
        }
        String json = gson.toJson(command);
        instance.out.println(json);
    }

    public static String getResponse() throws IOException {
        if (instance == null) {
            throw new IllegalStateException("Conexão não inicializada. Chame ClientConnection.initialize() primeiro.");
        }
        String responseJson = instance.in.readLine();
        Gson gson = new Gson();
        Response response = gson.fromJson(responseJson, Response.class);
        return response.message;
    }
}