package br.com.project.javafxmvc.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FXMLVBoxMainController implements Initializable {

    @FXML
    private MenuItem menuItemCadastrosClientes;
    @FXML
    private MenuItem menuItemCadastrosProdutos;
    @FXML
    private MenuItem menuItemProcessosVendas;
    @FXML
    private MenuItem menuItemGraficosVendasPorMes;
    @FXML
    private MenuItem menuItemVendasPorMesGraficos;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void handlemenuItemCadastrosClientes() throws IOException {
        URL url = new File("src/main/java/br/com/project/javafxmvc/view/CadastrosClientes.fxml").toURI().toURL();
        AnchorPane a = (AnchorPane) FXMLLoader.load(url);
        anchorPane.getChildren().setAll(a);
    }
}
