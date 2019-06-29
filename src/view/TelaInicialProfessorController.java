/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static view.LoginController.chamarNovaTela;

/**
 * FXML Controller class
 *
 * @author thais
 */
public class TelaInicialProfessorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
     
    @FXML
    void alterarLogin(ActionEvent event) throws IOException {
         chamarNovaTela(event, "AlterarLogin.fxml", "Alterar Login");
    }

    @FXML
    void chamarCadastroDisciplina(ActionEvent event) throws IOException {
         chamarNovaTela(event, "CadastrarDisciplina.fxml", "Cadastrar Disciplina");
    }

    @FXML
    void chamarCadastroMonitor(ActionEvent event) throws IOException {
         chamarNovaTela(event, "CadastrarMonitor.fxml", "Cadastrar Monitor");
    }

    @FXML
    void chamarCadastroPlanejamento(ActionEvent event) throws IOException {
         chamarNovaTela(event, "CadastroPlanejamento.fxml", "Cadastrar Planejamento");
    }

    //FALTA FAZER TELA
    @FXML
    void mostrarFrequencia(ActionEvent event) throws IOException {
         //chamarNovaTela(event, "cadastroPlanejamento.fxml", "Cadastro Planejamento");
    }
    
    //FALTA FAZER TELA
    @FXML
    void mostrarRelatorioMonitoria(ActionEvent event) throws IOException {
        // chamarNovaTela(event, "cadastroPlanejamento.fxml", "Cadastro Planejamento");
    }

    @FXML
    void sairDaConta(ActionEvent event) throws IOException {
         chamarNovaTela(event, "CadastroLoginTela.fxml", "Cadastrar Login");

    }
    
}
