package br.com.project.javafxmvc.controller;

import br.com.project.javafxmvc.model.dao.ClienteDAO;
import br.com.project.javafxmvc.model.database.Database;
import br.com.project.javafxmvc.model.database.DatabaseFactory;
import br.com.project.javafxmvc.model.domain.Cliente;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CadastrosClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tblViewClientes;
    @FXML
    private TableColumn<Cliente, String> tblColumnNome;
    @FXML
    private TableColumn<Cliente, String> tblColumnCPF;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Label lblGridPaneCodigo;
    @FXML
    private Label lblGridPaneNome;
    @FXML
    private Label lblGridPaneCPF;
    @FXML
    private Label lblGridPaneTelefone;

    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();


    @Override
    public void initialize(URL url, ResourceBundle rb){
        clienteDAO.setConnection(connection);
        try {
            carregarTableViewCliente();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        tblViewClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewClientes(newValue)
        );
    }

    public void carregarTableViewCliente() throws SQLException {
        tblColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tblColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listClientes = clienteDAO.listar();

        observableListClientes = FXCollections.observableArrayList(listClientes);
        tblViewClientes.setItems(observableListClientes);
    }

    public void selecionarItemTableViewClientes(Cliente cliente){
        lblGridPaneCodigo.setText(Integer.toString(cliente.getCodigo()));
        lblGridPaneNome.setText(cliente.getNome());
        lblGridPaneCPF.setText(cliente.getCpf());
        lblGridPaneTelefone.setText(cliente.getTelefone());
    }
}