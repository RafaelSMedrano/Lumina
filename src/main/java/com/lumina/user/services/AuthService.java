package com.lumina.user.services;

import com.lumina.rest.dto.ResponseDTO;
import com.lumina.rest.dto.LoginRequestDTO;
import com.lumina.rest.dto.LoginResponseDTO;
import com.lumina.rest.dto.RegistrationRequestDTO;
import com.lumina.rest.dto.RegistrationResponseDTO;
import com.lumina.user.models.User;
import com.lumina.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseDTO registration(RegistrationRequestDTO request) {

        // verifica se já existe usuário com esse username
        if (userRepository.findByUsername(request.username) != null) {
            throw new RuntimeException("Usuário já existe");
        }

        // cria entidade
        User user = new User(request.username, request.password, request.email);

        // salva no banco
        userRepository.save(user);

        RegistrationResponseDTO registrationData = new RegistrationResponseDTO(user.getUsername());

        return new ResponseDTO(
                "success",
                "Usuário registrado com sucesso",
                registrationData
                );
    }

    public ResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByUsername(request.username);

        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        if (!user.getPassword().equals(request.password)) {
            throw new RuntimeException("Senha inválida");
        }
        LoginResponseDTO loginData = new LoginResponseDTO(user.getUsername());

        return new ResponseDTO(
                "success",
                "Login realizado com sucesso",
                loginData
        );
    }
}


