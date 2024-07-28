package br.com.project.javafxmvc.controller;

import br.com.project.javafxmvc.model.dao.ClienteDAO;
import br.com.project.javafxmvc.model.database.Database;
import br.com.project.javafxmvc.model.database.DatabaseFactory;
import br.com.project.javafxmvc.model.domain.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
        if (cliente != null) {
            lblGridPaneCodigo.setText(Integer.toString(cliente.getCodigo()));
            lblGridPaneNome.setText(cliente.getNome());
            lblGridPaneCPF.setText(cliente.getCpf());
            lblGridPaneTelefone.setText(cliente.getTelefone());
        }
    }

    @FXML
    public void handleButtonInserir() throws SQLException, IOException {
        Cliente cliente = new Cliente();
        boolean buttonConfirmarClicked = showCadastrosClientesDialog(cliente);
        if (buttonConfirmarClicked){
            clienteDAO.inserir(cliente);
            carregarTableViewCliente();
        }
    }

    @FXML
    public void handleButtonAlterar() throws SQLException, IOException {
       Cliente cliente = tblViewClientes.getSelectionModel().getSelectedItem();
       if(cliente != null){
           boolean buttonConfirmarClicked = showCadastrosClientesDialog(cliente);
           if (buttonConfirmarClicked){
               clienteDAO.alterar(cliente);
               carregarTableViewCliente();
           }
       }else {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setContentText("Por favor, escolha um cliente na Tabela!");
           alert.show();
       }
    }

    @FXML
    public void handleButtonExcluir() throws SQLException {
        Cliente cliente = tblViewClientes.getSelectionModel().getSelectedItem();
        if(cliente != null){
            clienteDAO.remover(cliente);
            carregarTableViewCliente();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }

    @FXML
    public boolean showCadastrosClientesDialog(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL url = new File("src/main/java/br/com/project/javafxmvc/view/CadastrosClientesDialog.fxml").toURI().toURL();
        AnchorPane page = (AnchorPane) loader.load(url.openStream());

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Clientes");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        CadastrosClientesDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente);

        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();
    }


}