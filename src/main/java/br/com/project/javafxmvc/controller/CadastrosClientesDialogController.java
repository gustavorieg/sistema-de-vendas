package br.com.project.javafxmvc.controller;

import br.com.project.javafxmvc.model.domain.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
        this.txtFieldClienteNome.setText(cliente.getNome());
        this.txtFieldClienteCPF.setText(cliente.getCpf());
        this.txtFieldClienteTelefone.setText(cliente.getTelefone());
    }

    @FXML
    public void handleButtonConfirmar() {
        if(validarEntradaDeDados()){
            cliente.setNome(txtFieldClienteNome.getText());
            cliente.setCpf(txtFieldClienteCPF.getText());
            cliente.setTelefone(txtFieldClienteTelefone.getText());

            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

    @FXML
    private boolean validarEntradaDeDados(){
        String errorMessage = "";

        if (txtFieldClienteNome.getText() == null || txtFieldClienteNome.getText().isEmpty()){
            errorMessage += "Nome inválido!\n";
        }
        if (txtFieldClienteCPF.getText() == null || txtFieldClienteCPF.getText().isEmpty()){
            errorMessage += "CPF inválido!\n";
        }
        if (txtFieldClienteTelefone.getText() == null || txtFieldClienteTelefone.getText().isEmpty()){
            errorMessage += "Telefone inválido!\n";
        }

        if(errorMessage.isEmpty()){
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }

    }

}


