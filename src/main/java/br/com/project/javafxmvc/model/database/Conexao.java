package br.com.project.javafxmvc.model.database;

import java.sql.*;

public class Conexao {

    public Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sistema_vendas", "postgres", "gustavorieg");
            return conn;
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco: " + e.getMessage());
            return null;
        }
    }
}
