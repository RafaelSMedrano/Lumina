package com.lumina.server;

import com.lumina.server.models.LuminaChar;

import java.util.HashMap;
import java.util.Map;


public class ServerState {
        // Usuários cadastrados: username -> senha (isso deve ser substituído por algo mais seguro depois)
    private Map<String, String> registeredUsers = new HashMap<>();

    // Jogadores conectados: username -> ClientHandler
    private Map<String, ClientHandler> connectedClients = new HashMap<>();

    // Exemplo: Mapa de personagens (username -> objeto Character)
    private Map<String, LuminaChar> characters = new HashMap<>();

    // Qualquer outro estado do jogo (mapa, salas, etc)
    // private GameMap map;
    // private List<Area> areas;

    // Métodos de manipulação
    public boolean registerUser(String username, String password) {
        if (registeredUsers.containsKey(username)) return false;
        registeredUsers.put(username, password);
        System.out.println(registeredUsers);
        return true;
    }

    public boolean login(String username, String password) {
        System.out.println(registeredUsers);
        System.out.println(registeredUsers.containsKey(username) &&
                registeredUsers.get(username).equals(password));
        return registeredUsers.containsKey(username) &&
               registeredUsers.get(username).equals(password);
    }

    public void connectClient(String username, ClientHandler handler) {
        connectedClients.put(username, handler);
    }

    public void disconnectClient(String username) {
        connectedClients.remove(username);
    }

    public boolean isUserConnected(String username) {
        return connectedClients.containsKey(username);
    }

    public void addCharacter(String username, LuminaChar character) {
        characters.put(username, character);
    }

    public LuminaChar getCharacter(String username) {
        return characters.get(username);
    }

    // ... outros métodos conforme o crescimento do jogo
}
