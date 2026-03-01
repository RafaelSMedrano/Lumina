package com.lumina.server.db;
import java.util.List;
import java.util.Optional;
import java.sql.*;

// Here the T is used to “Definir o tipo da entidade que esse DAO gerencia”
public interface Dao<T> {
    Optional<T> getElement(String id) throws SQLException;
    List<T> getAll() throws SQLException;
    void save(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(String id) throws SQLException;
}