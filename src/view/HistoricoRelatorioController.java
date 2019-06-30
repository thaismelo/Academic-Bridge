/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Monitor;
import entidades.RelatorioMonitoria;
import entidades.Tarefa;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.ExceptionErroNoBanco;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import static view.LoginController.pessoa;

/**
 * FXML Controller class
 *
 * @author Guilherme
 */
public class HistoricoRelatorioController implements Initializable {

    @FXML
    private TableView<RelatorioMonitoria> tabelaRelatorio;

    @FXML
    private TableColumn<RelatorioMonitoria, Tarefa> clnAtividade;
    
    @FXML
    private TableColumn<RelatorioMonitoria, Monitor> clnMonitor;

    @FXML
    private TableColumn<RelatorioMonitoria, Integer> clnNivelDificuldade;

    @FXML
    private TableColumn<RelatorioMonitoria, String> clnReforcarAssunto;

    @FXML
    private TableColumn<RelatorioMonitoria, Integer> clnParticipatividade;

    @FXML
    private TableColumn<RelatorioMonitoria, String> clnData;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            configuraColunas();
            atualizarDadosTabela();
        } catch (ExceptionErroNoBanco | DadoInexistenteException ex) {
            Logger.getLogger(HistoricoRelatorioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    void chamarTelaInicial(ActionEvent event) throws IOException {
        LoginController.chamarNovaTela(event, "TelaInicialMonitor.fxml", "Tela Inicial");
    }
    
    private void configuraColunas() {
	clnAtividade.setCellValueFactory(new PropertyValueFactory<>("tarefa"));
        clnMonitor.setCellValueFactory(new PropertyValueFactory<>("monitor"));
	clnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        clnReforcarAssunto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RelatorioMonitoria, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<RelatorioMonitoria, String> param) {
            if(param.getValue().getReforcarAssunto()==1){
                return new SimpleStringProperty(
                    "SIM");
            }
            return new SimpleStringProperty(
                    "NÃO");
        }
        });
        clnParticipatividade.setCellValueFactory(new PropertyValueFactory<>("participatividade"));
        clnNivelDificuldade.setCellValueFactory(new PropertyValueFactory<>("nivelDificuldade"));
       
    }
    
    private void atualizarDadosTabela() throws ExceptionErroNoBanco, DadoInexistenteException {
        Monitor m = (Monitor) pessoa;
        List<Integer> lista = fachada.Fachada.getSingleton().recuperarTodosCodMonitPorDisc(m.getDisciplina().getId());
        List<RelatorioMonitoria> t = fachada.Fachada.getSingleton().recuperarTodosRelatorioMonitoriaPorListaMonit(lista);
	tabelaRelatorio.getItems().setAll(t);
    }
    
}
