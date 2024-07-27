package br.com.project.javafxmvc.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

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
}
