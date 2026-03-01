package com.lumina.server.services;

import com.lumina.server.db.UserDAO;
import com.lumina.server.exceptions.UserRegistrationException;
import com.lumina.server.models.User;
import com.lumina.server.protocols.RegisterCommand;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public boolean registerUser(RegisterCommand cmd) {

        String username = cmd.username;
        String email = cmd.email;
        String passwordHash = cmd.password;

        try {
            //  REGRA DE NEGÓCIO
            if (userDAO.usernameExists(username)) {
                throw new UserRegistrationException(
                        "Este username já existe, tente outro.",
                        null
                );
            }

            if (userDAO.emailExists(email)) {
                throw new UserRegistrationException(
                        "Este email já está em uso.",
                        null
                );
            }

            // CRIAÇÃO DO MODEL
            User user = new User(
                    username,
                    email,
                    passwordHash
            );

            // PERSISTÊNCIA
            userDAO.save(user);

            return true;

        } catch (SQLException e) {
            //  CONVERSÃO DE EXCEÇÃO TÉCNICA → NEGÓCIO
            throw new UserRegistrationException(
                    "Erro interno ao registrar usuário.",
                    e
            );
        }
    }
}

//package com.lumina.server.services;
//import com.lumina.server.db.UserDAO;
//import com.lumina.server.exceptions.UserRegistrationException;
//import com.lumina.server.models.User;
//import com.lumina.server.protocols.RegisterCommand;
//import com.lumina.server.protocols.Response;
//
//import java.sql.SQLException;
//
//public class UserService {
//
//    private final UserDAO userDAO;
//
//    public UserService(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    }
//
//
//
//    public static boolean registerUser(RegisterCommand cmd) {
//
//        // Regra de negócio: nome de usuário não pode existir
//        try {
//                if (userDAO.usernameExists(username)) {
//                    throw new UserRegistrationException("Este username já existe, tente outro.", null);
//                }
//                else {
//                    // Regra de negócio: criar usuário
//                    User user = new User(username, email, password);
//                    // Persistência delegada ao DAO
//                    userDAO.save(user);
//                }
//
//
//        } catch (SQLException e) {
//            throw new UserRegistrationException("Erro ao registrar usuário, tente novamente mais tarde.", e);
//        }
//
//        return true;
//    }
//}

