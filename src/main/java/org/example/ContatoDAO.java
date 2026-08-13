package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    //CREATE
    public void adicionar(Contato contato) {
        //? placeholder informar o que será adicionado e evita SQL injection
        String sql = "INSERT INTO contatos(nome, telefone) VALUES(?, ?)";
        try (Connection conn = Database.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //Define os valores para os placeholders
            pstmt.setString(1, contato.getNome());
            pstmt.setString(2, contato.getTelefone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar contato: " + e.getMessage());
        }
    }
    //READ
    public List<Contato> listar() {
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT * FROM contatos";

        try (Connection conn = Database.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            //iteração sobre o resultado da consulta
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                //cria objeto um objeto contato e add a lista
                contatos.add(new Contato(id, nome, telefone));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar contatos: " + e.getMessage());
        }
        return contatos;
    }
    //UPDATE
    public void atualizar(Contato contato) {
        String sql = "UPDATE contatos SET nome = ?, telefone = ?, WHERE id = ?";
        try (Connection conn = Database.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, contato.getNome());
            pstmt.setString(2, contato.getTelefone());
            pstmt.setInt(3, contato.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar contato: " + e.getMessage());
        }
    }
    //DELETE
    public void remover(int id) {
        String sql = "DELETE FROM contatos WHERE id = ?";
        try (Connection conn = Database.conectar();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao remover contato: " + e.getMessage());
        }
    }
}
