/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Aluno;
import entidades.Disciplina;
import entidades.Frequencia;
import entidades.Monitor;
import entidades.Professor;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import static view.LoginController.chamarNovaTela;
import static view.LoginController.pessoa;

/**
 * FXML Controller class
 *
 * @author Guilherme
 */
public class ExibirFrequenciaController implements Initializable {

    @FXML
    private TableView<Monitor> tblMonitor;
    @FXML
    private TableColumn<Monitor, String> clDisciplina;
    @FXML
    private TableColumn<Monitor, String> clMonitor;
    
    @FXML
    private TableView<String> tblDatas;
    @FXML
    private TableColumn<String, String> clData;
    
    @FXML
    private TableView<Frequencia> tblFrequencia;
    @FXML
    private TableColumn<Frequencia, Aluno> clAluno;
    @FXML
    private TableColumn<Frequencia, String> clPresenca;

    
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
            Logger.getLogger(ExibirFrequenciaController.class.getName()).log(Level.SEVERE, null, ex);
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

        clData.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> param) {
            return new SimpleStringProperty(
                    param.getValue());
        }
        });
        clAluno.setCellValueFactory(new PropertyValueFactory<>("aluno"));
        clPresenca.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Frequencia, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Frequencia, String> param) {
            return new SimpleStringProperty(
                    param.getValue().mostrarPresenca());
        }
        });
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
        tblDatas.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
            if (n != null) {
                try {
                    atualizarDadosTabela3(n);
                } catch (ExceptionErroNoBanco ex) {
                    Logger.getLogger(ExibirFrequenciaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                }            
            });
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
    
    private void atualizarDadosTabela2(int id) throws ExceptionErroNoBanco {
        List<String> t = fachada.Fachada.getSingleton().recuperarTodosFrequenciaPorDataMonit(id);
        atualizarDadosTabela3("");
	tblDatas.getItems().setAll(t);
    }
    
    private void atualizarDadosTabela3(String id) throws ExceptionErroNoBanco {
        List<Frequencia> t = fachada.Fachada.getSingleton().recuperarTodosFrequenciaPorData(id);
        System.out.println(t);
	tblFrequencia.getItems().setAll(t);
    }
    
}
