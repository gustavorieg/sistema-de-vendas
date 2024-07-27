package br.com.project.javafxmvc.model.dao;

import br.com.project.javafxmvc.model.domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {
    private Connection conn;

    public Connection getConnection() {
        return conn;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO clientes(nome, cpf, telefone) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Cliente> listar() throws SQLException {
        String sql = "SELECT * FROM clientes";
        List<Cliente> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setCpf(resultado.getString("telefone"));
                retorno.add(cliente);
            }
    } catch (SQLException ex) {
        Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return retorno;
    }
}
