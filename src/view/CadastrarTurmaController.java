/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Aluno;
import entidades.Monitor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import static view.LoginController.chamarNovaTela;
import static view.LoginController.pessoa;

/**
 * FXML Controller class
 *
 * @author thais
 */
public class CadastrarTurmaController implements Initializable {
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void cadastrarAluno(ActionEvent event) {
        Aluno a = new Aluno(0,(Monitor) pessoa, txtNome.getText(), txtEmail.getText());
        try{
            fachada.Fachada.getSingleton().cadastrarAluno(a);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Aluno cadastrado na sua turma");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    void voltarTelaInicial(ActionEvent event) throws IOException {
        chamarNovaTela(event, "TelaInicialMonitor.fxml", "Tela Inicial");
    }
}
