/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author thais
 */
public class TelaInicialProfessorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private MenuItem cadastroMonitor;

    @FXML
    private MenuItem cadastroDisciplina;

    @FXML
    private MenuItem alterarLogin;

    @FXML
    private MenuItem sairDaConta;
     
     @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
     
    @FXML
    public void chamarTelaCadastroLogin(ActionEvent event) throws IOException {
    }

    @FXML
    public void chamarCadastroDisciplina(ActionEvent event) throws IOException {
    }

    @FXML
    public void chamarCadastroMonitor() throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader();
       fxmlLoader.setLocation(TelaInicialProfessorController.class.getResource("view/CadastrarMonitor.fxml"));
       borderPane= (BorderPane) fxmlLoader.load();

       

    }
    
}
