package br.com.project.javafxmvc.controller;

import br.com.project.javafxmvc.model.domain.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastrosClientesDialogController implements Initializable {

    @FXML
    private Label lblClienteNome;
    @FXML
    private Label lblClienteCPF;
    @FXML
    private Label lblClienteTelefone;
    @FXML
    private TextField txtFieldClienteNome;
    @FXML
    private TextField txtFieldClienteCPF;
    @FXML
    private TextField txtFieldClienteTelefone;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private Cliente cliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    public void handleButtonConfirmar() {
        cliente.setNome(txtFieldClienteNome.getText());
        cliente.setCpf(txtFieldClienteCPF.getText());
        cliente.setTelefone(txtFieldClienteTelefone.getText());

        buttonConfirmarClicked = true;
        dialogStage.close();
    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }
}


