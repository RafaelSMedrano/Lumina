//package com.lumina.server.db;
//import com.lumina.server.models.User;
//import java.sql.*;
//import java.util.Optional;
//
//public class UserDAO {
//// DAO = Data Acces Object
//    // Verifica existência por username
//    public boolean usernameExists(String username) throws SQLException {
//        String sql = "SELECT id FROM users WHERE username = ? LIMIT 1";
//        try (Connection con = Database.getConnection();
//             PreparedStatement pst = con.prepareStatement(sql)) {
//
//            pst.setString(1, username);
//            try (ResultSet rs = pst.executeQuery()) {
//                return rs.next();
//            }
//        }
//    }
//
//    // Verifica existência por email
//    public boolean emailExists(String email) throws SQLException {
//        String sql = "SELECT id FROM users WHERE email = ? LIMIT 1";
//        try (Connection con = Database.getConnection();
//             PreparedStatement pst = con.prepareStatement(sql)) {
//
//            pst.setString(1, email);
//            try (ResultSet rs = pst.executeQuery()) {
//                return rs.next();
//            }
//        }
//    }
//
//    public void save(User user) throws SQLException {
//
//        String sql = """
//            INSERT INTO users (username, email, password_hash, created_at)
//            VALUES (?, ?, ?, ?)
//        """;
//
//        try (Connection conn = Database.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(
//                     sql,
//                     Statement.RETURN_GENERATED_KEYS
//             )) {
//
//            stmt.setString(1, user.getUserName());
//            stmt.setString(2, user.getEmail());
//            stmt.setString(3, user.getPasswordHash());
//            stmt.setTimestamp(4, user.getCreatedAt());
//
//            stmt.executeUpdate();
//
//            // 🔑 aqui o DAO recupera o ID gerado
//            try (ResultSet keys = stmt.getGeneratedKeys()) {
//                if (keys.next()) {
//                    user.setId(keys.getInt(1));
//                } else {
//                    throw new SQLException("Falha ao obter ID gerado do usuário");
//                }
//            }
//        }
//    }
//
//    // Registra e retorna o ID gerado (Optional<Long>)
//    public Optional<Long> registerUserAndGetId(String username, String passwordHash, String email) throws SQLException {
//        String sql = "INSERT INTO users (username, password_hash, email) VALUES (?, ?, ?)";
//        try (Connection con = Database.getConnection();
//             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//
//            pst.setString(1, username);
//            pst.setString(2, passwordHash);
//            pst.setString(3, email);
//
//            int affected = pst.executeUpdate();
//            if (affected == 0) return Optional.empty();
//
//            try (ResultSet keys = pst.getGeneratedKeys()) {
//                if (keys.next()) {
//                    return Optional.of(keys.getLong(1));
//                } else {
//                    return Optional.empty();
//                }
//            }
//        }
//    }
//
//    // Busca e retorna Optional<User>
//    public Optional<User> getUserByUsername(String username) throws SQLException {
//        String sql = "SELECT id, username, password_hash, email, created_at FROM users WHERE username = ?";
//        try (Connection con = Database.getConnection();
//             PreparedStatement pst = con.prepareStatement(sql)) {
//
//            pst.setString(1, username);
//            try (ResultSet rs = pst.executeQuery()) {
//                if (rs.next()) {
//                    User user = new User(
//                            rs.getInt("id"),
//                            rs.getString("username"),
//                            rs.getString("password_hash"),
//                            rs.getString("email")
//                    );
//                    return Optional.of(user);
//                } else {
//                    return Optional.empty();
//                }
//            }
//        }
//    }
//}

package com.lumina.server.db;

import com.lumina.server.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements Dao<User> {

    // =============================
    // Métodos específicos de usuário
    // =============================

    public boolean usernameExists(String username) throws SQLException {
        String sql = "SELECT id FROM users WHERE username = ? LIMIT 1";
        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT id FROM users WHERE email = ? LIMIT 1";
        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        }
    }

    public Optional<User> getByUsername(String username) throws SQLException {
        String sql = "SELECT id, username, password_hash, email, created_at FROM users WHERE username = ?";
        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(mapUser(rs));
            }
        }
    }

    // =============================
    // Implementação da interface Dao<User>
    // =============================

    @Override
    public Optional<User> getElement(String id) throws SQLException {
        String sql = "SELECT id, username, password_hash, email, created_at FROM users WHERE id = ?";
        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(mapUser(rs));
            }
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        String sql = "SELECT id, username, password_hash, email, created_at FROM users";

        List<User> users = new ArrayList<>();

        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                users.add(mapUser(rs));
            }
        }

        return users;
    }

    @Override
    public void save(User user) throws SQLException {
        String sql = """
            INSERT INTO users (username, email, password_hash, created_at)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS
             )) {

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setTimestamp(4, user.getCreatedAt());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (!keys.next()) {
                    throw new SQLException("Falha ao obter ID gerado do usuário");
                }
                user.setId(keys.getInt(1));
            }
        }
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = """
            UPDATE users
            SET username = ?, email = ?, password_hash = ?
            WHERE id = ?
        """;

        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, user.getUserName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPasswordHash());
            pst.setInt(4, user.getId());

            pst.executeUpdate();
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection con = Database.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, id);
            pst.executeUpdate();
        }
    }

    // =============================
    // Mapper centralizado
    // =============================

    private User mapUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getString("username"),
                rs.getString("password_hash"),
                rs.getString("email")
        );
    }
}


