/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static view.LoginController.chamarNovaTela;

/**
 * FXML Controller class
 *
 * @author thais
 */
public class TelaInicialMonitorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      @FXML
    void chamarBacklog(ActionEvent event) throws IOException {
         chamarNovaTela(event, "BacklogMonitor.fxml", "Planejamento Pessoal");
    }

    @FXML
    void chamarCadastrarFrequencia(ActionEvent event) throws IOException {
        chamarNovaTela(event, "Frequencia.fxml", "Frequencia");
    }

    @FXML
    void chamarCadastrarRelatorio(ActionEvent event) throws IOException {
        chamarNovaTela(event, "RelatorioDaMonitoria.fxml", "Relatorio da Monitoria");
    }

    @FXML
    void chamarCadastrarTurma(ActionEvent event) throws IOException {
        chamarNovaTela(event, "CadastrarTurma.fxml", "Cadastrar Turma");
    }

    @FXML
    void chamarQuadroDeTarefas(ActionEvent event) throws IOException {
        chamarNovaTela(event, "QuadroDeTarefas.fxml", "Quadro de Tarefas");
    }

    @FXML
    void mostrarRelatorio(ActionEvent event) throws IOException {
        chamarNovaTela(event, "HistoricoRelatorio.fxml", "Histórico de Relatórios");
    }
    
    @FXML
    void chamaAlterarLogin(ActionEvent event) throws IOException {
        chamarNovaTela(event, "AlterarLogin.fxml", "Alterar Login");
    }

    @FXML
    void chamaCadastroLogin(ActionEvent event) throws IOException {
        chamarNovaTela(event, "CadastroLoginTela.fxml", "Cadastrar Login");

    }
}
