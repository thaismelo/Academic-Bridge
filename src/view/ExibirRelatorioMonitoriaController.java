/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Monitor;
import entidades.Professor;
import entidades.RelatorioMonitoria;
import entidades.Tarefa;
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
import static view.LoginController.chamarNovaTela;
import static view.LoginController.pessoa;

/**
 * FXML Controller class
 *
 * @author Killua
 */
public class ExibirRelatorioMonitoriaController implements Initializable {

    
    @FXML
    private TableView<Monitor> tblMonitor;
    @FXML
    private TableColumn<Monitor, String> clDisciplina;
    @FXML
    private TableColumn<Monitor, String> clMonitor;
    //
    @FXML
    private TableView<RelatorioMonitoria> tabelaRelatorio;

    @FXML
    private TableColumn<RelatorioMonitoria, Tarefa> clnAtividade;

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
            configurarBindings();
            atualizarDadosTabela();
        } catch (ExceptionErroNoBanco ex) {
            Logger.getLogger(ExibirRelatorioMonitoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void configuraColunas() {
        clMonitor.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clDisciplina.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Monitor, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Monitor, String> param) {
            return new SimpleStringProperty(
                    param.getValue().getDisciplina().getNome());
        }
        });
        clnAtividade.setCellValueFactory(new PropertyValueFactory<>("tarefa"));
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
    
    @FXML
    public void encaminharParaInicio(ActionEvent event) throws IOException{
        chamarNovaTela(event, "TelaInicialProfessor.fxml", "Inicio Professor");
    }
    
    private void atualizarDadosTabela() throws ExceptionErroNoBanco {
        Professor p = (Professor) pessoa;
        List<Monitor> t = fachada.Fachada.getSingleton().recuperarTodosMonitorPorProf(p);
	tblMonitor.getItems().setAll(t);
    }
    
    private void atualizarDadosTabela2(int cod) throws ExceptionErroNoBanco {
        List<RelatorioMonitoria> t = fachada.Fachada.getSingleton().recuperarTodosRelatoriosPorCodMonitor(cod);
	tabelaRelatorio.getItems().setAll(t);
    }
    
    private void configurarBindings(){
        tblMonitor.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
            if (n != null) {
                try {
                    atualizarDadosTabela2(n.getId());
                } catch (ExceptionErroNoBanco ex) {
                    Logger.getLogger(ExibirFrequenciaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            });
    } 
    
    
    
    
    
}
