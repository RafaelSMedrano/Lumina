package com.lumina.rest.dto;

public class LoginResponseDTO {
    private String username;

    public LoginResponseDTO() {}

    public LoginResponseDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
