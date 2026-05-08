package com.lumina.rest.controllers;

import com.lumina.rest.dto.LoginRequestDTO;
import com.lumina.rest.dto.RegistrationRequestDTO;
import com.lumina.rest.dto.ResponseDTO;
import com.lumina.user.services.AuthService;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registration")
    public ResponseDTO registration(@RequestBody RegistrationRequestDTO request) {
        return authService.registration(request);
    }

    @PostMapping("/login")
    public ResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }

}