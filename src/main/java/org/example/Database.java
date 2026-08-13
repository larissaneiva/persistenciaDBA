package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    //Diz ao jdbc para usar o Sqlite e qual arquivo
    private static final String URL = "jdbc:sqlite:agenda.db";
    //Retorna um objeto de conexão com o banco
    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL);
        } catch(SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null; //se a conexão falhar
        }
    }
    public static void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS contatos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "telefone TEXT" +
                ");";
        //O objeto statement garante que comandos sql sejam enviados ao banco
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }
}
