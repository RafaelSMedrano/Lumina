package com.lumina.server.services;

import com.lumina.server.db.UserDAO;
import com.lumina.server.exceptions.DataBaseException;
import com.lumina.server.models.User;
import com.lumina.server.protocols.LoginCommand;
import javax.naming.AuthenticationException;
import java.sql.SQLException;

public class AuthService {

    private final UserDAO userDAO;

    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void auth(String username, String password) throws AuthenticationException {
        try {
            System.out.println("Entrou na authenticacao.");
            User user = userDAO.getByUsername(username)
                    .orElseThrow(() -> new AuthenticationException("User not found"));

            if (user.checkPassword(password)) {
                System.out.println("invalid password");
                throw new AuthenticationException("Invalid password");
            }
        } catch (SQLException e) {
            System.out.println("erro ao acessar banco");
                throw new DataBaseException("Erro ao acessar banco", e);
            }
        System.out.println("Autenticou no authService.");
    }
}
