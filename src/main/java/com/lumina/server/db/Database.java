package com.lumina.server.db;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    private static final HikariDataSource dataSource;
    static {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/lumina_db");
        config.setUsername("root");
        config.setPassword("sua_senha");

        config.setMaximumPoolSize(10);    // Máximo de conexões simultâneas
        config.setMinimumIdle(2);         // Conexões mínimas ociosas
        config.setIdleTimeout(60000);     // 60s para fechar conexões ociosas
        config.setConnectionTimeout(30000); // 30s para esperar conexão
        config.setLeakDetectionThreshold(2000); // Detecta conexões vazando

        dataSource = new HikariDataSource(config);
    }

    /**
     * Retorna uma conexão do pool.
     * Lembre-se de SEMPRE fechar a conexão depois de usar (con.close()).
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
