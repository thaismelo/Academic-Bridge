/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Monitor;
import entidades.Tarefa;
import entidades.TarefaParaMonitor;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Tarefa.ConteudoNuloException;
import exceptions.entidades.Tarefa.EstadoInvalidoException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import static view.LoginController.chamarNovaTela;
import static view.LoginController.pessoa;

/**
 * FXML Controller class
 *
 * @author thais
 */
public class QuadroDeTarefasController implements Initializable {
    
    @FXML
    private TableView<TarefaParaMonitor> tblTarefaMonitor;

    @FXML
    private TableColumn<TarefaParaMonitor, String> clnTarefa;

    @FXML
    private TableColumn<TarefaParaMonitor, String> clnDataLimite;

    @FXML
    private TableColumn<TarefaParaMonitor, String> clnStatus;
    
    @FXML
    private TableColumn<TarefaParaMonitor, String> clnMonitor;
    
    @FXML
    private ToggleGroup grupo;
    
    @FXML
    private Button btAlterar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            configuraColunas();
            atualizarDadosTabela();
            configurarBindings();
        } catch (ExceptionErroNoBanco ex) {
            System.out.println(ex.getMessage());
        }
    }    
    
    public void configuraColunas(){
        clnDataLimite.setCellValueFactory(new PropertyValueFactory<>("data"));
        //
        clnTarefa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TarefaParaMonitor, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<TarefaParaMonitor, String> param) {
            try {
                return new SimpleStringProperty(
                        fachada.Fachada.getSingleton().recuperarTarefa(param.getValue().getCodDisciplina()).getConteudo());
            } catch (ExceptionErroNoBanco | DadoInexistenteException ex) {
                Logger.getLogger(CadastroPlanejamentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        });
        clnStatus.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TarefaParaMonitor, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<TarefaParaMonitor, String> param) {
            return new SimpleStringProperty(
                    param.getValue().getTarefaParaMonitor().mostrarEstado());
        }
        });
        clnMonitor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TarefaParaMonitor, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<TarefaParaMonitor, String> param) {
            try {
                return new SimpleStringProperty(
                        fachada.Fachada.getSingleton().recuperarMonitor(param.getValue().getCodMonit()).getNome());
            } catch (ExceptionErroNoBanco | DadoInexistenteException ex) {
                Logger.getLogger(CadastroPlanejamentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        });
    }
   
    private void atualizarDadosTabela() throws ExceptionErroNoBanco {
        Monitor m = (Monitor) pessoa;
        List<TarefaParaMonitor> t = fachada.Fachada.getSingleton().recuperarTodosTarefaParaMonitorPorCodDisc(m.getDisciplina().getId());
	tblTarefaMonitor.getItems().setAll(t);
	reiniciarCampos();
    }
    
    public void reiniciarCampos() {
        tblTarefaMonitor.getSelectionModel().select(null);
    }

    @FXML
    public void encaminharParaInicio(ActionEvent event) throws IOException{
        chamarNovaTela(event, "TelaInicialMonitor.fxml", "Inicio Monitor");
    }

    private void configurarBindings(){
        BooleanBinding selecaoAtiva = tblTarefaMonitor.getSelectionModel().selectedItemProperty().isNull();
        btAlterar.disableProperty().bind(selecaoAtiva);                
    }
    
    @FXML
    private void alterar(ActionEvent event){
        RadioButton radio = (RadioButton)grupo.getSelectedToggle();
        Monitor m = (Monitor) pessoa;
        try {
            if(tblTarefaMonitor.getSelectionModel().selectedItemProperty().get().getCodMonit() == m.getId()){
                int id = tblTarefaMonitor.getSelectionModel().selectedItemProperty().get().getTarefaParaMonitor().getId();
                Tarefa tarefaM = fachada.Fachada.getSingleton().recuperarTarefa(id);
                if(radio.getText().equals("EM PROGRESSO")){           
                    tarefaM.setEstado(1);            
                }else if(radio.getText().equals("COMPLETA")){
                    tarefaM.setEstado(2);
                }
                fachada.Fachada.getSingleton().alterarTarefa(tarefaM);
                atualizarDadosTabela();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCESSO");
                alert.setContentText("Alterado Status da Tarefa");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setContentText("Tarefa de outro monitor");
                alert.showAndWait();
            }
        } catch (ExceptionErroNoBanco | DadoInexistenteException | ConteudoNuloException | DadoNuloException | EstadoInvalidoException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
        }
        
    }
    
    
    
}
