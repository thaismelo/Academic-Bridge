/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Monitor;
import entidades.Tarefa;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Tarefa.ConteudoNuloException;
import exceptions.entidades.Tarefa.EstadoInvalidoException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import static view.LoginController.pessoa;
import javafx.util.Callback;
import static view.LoginController.chamarNovaTela;

/**
 * FXML Controller class
 *
 * @author thais
 */
public class BacklogMonitorController implements Initializable {

    @FXML
    private Button btAlterar;
    @FXML
    private Button btSalvar;
    @FXML
    private Button btApagar;
    @FXML
    private TextField txtConteudo;
    @FXML
    private ToggleGroup grupo;
    
    @FXML
    private TableView<Tarefa> tblTarefa;
    @FXML
    private TableColumn<Tarefa, String> clConteudo;
    @FXML
    private TableColumn<Tarefa, String> clStatus;

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
            Logger.getLogger(BacklogMonitorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
 
    private void atualizarDadosTabela() throws ExceptionErroNoBanco {
        Monitor p = (Monitor) pessoa;
        List<Tarefa> t = fachada.Fachada.getSingleton().recuperarTodosPorCriador(p.getId(), 2);
	tblTarefa.getItems().setAll(t);
	reiniciarCampos();
    }
    
    public void reiniciarCampos() {
        tblTarefa.getSelectionModel().select(null);
	txtConteudo.setText("");
    }

    private void configurarBindings(){
        BooleanBinding camposPreenchidos = txtConteudo.textProperty().isEmpty();
        BooleanBinding selecaoAtiva = tblTarefa.getSelectionModel().selectedItemProperty().isNull();
        btApagar.disableProperty().bind(selecaoAtiva);
        btAlterar.disableProperty().bind(selecaoAtiva.or(camposPreenchidos));
        btSalvar.disableProperty().bind(camposPreenchidos);
        tblTarefa.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
            if (n != null) {
		txtConteudo.setText(n.getConteudo());
            }});        
    }

    public void cadastrarTarefa(ActionEvent event){
        Monitor pf = (Monitor)pessoa;
        try {
            Tarefa tarefa = new Tarefa(0,pf.getId(),2,txtConteudo.getText(),0);
            fachada.Fachada.getSingleton().cadastrarTarefa(tarefa);
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Tarefa cadastrada");
            alert.showAndWait();
        } catch (ExceptionErroNoBanco | ConteudoNuloException | EstadoInvalidoException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText("Dados Invalidos");
            alert.showAndWait();
        }
    }
    
    public void configuraColunas(){
        clConteudo.setCellValueFactory(new PropertyValueFactory<>("conteudo"));
        clStatus.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Tarefa, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Tarefa, String> param) {
            return new SimpleStringProperty(
                    param.getValue().mostrarEstado());
        }
        });
    }
    
    @FXML
    public void encaminharParaInicio(ActionEvent event) throws IOException{
        chamarNovaTela(event, "TelaInicialMonitor.fxml", "Inicio Monitor");
    }
    
    public void alterarTarefa(ActionEvent event){
        RadioButton radio = (RadioButton)grupo.getSelectedToggle();
        try {
            int id = tblTarefa.getSelectionModel().selectedItemProperty().get().getId();
            Tarefa tarefaM = fachada.Fachada.getSingleton().recuperarTarefa(id);
            if(radio.getText().equals("EM PROGRESSO")){           
                tarefaM.setEstado(1);            
            }else if(radio.getText().equals("COMPLETA")){
                tarefaM.setEstado(2);
            }
            tarefaM.setConteudo(txtConteudo.getText());
            fachada.Fachada.getSingleton().alterarTarefa(tarefaM);
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Tarefa Alterada");
            alert.showAndWait();
            
        } catch (ExceptionErroNoBanco | DadoInexistenteException | ConteudoNuloException | DadoNuloException | EstadoInvalidoException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText("Dados invalidos");
            alert.showAndWait();
        }
    }
    
    
    public void removerTarefa(ActionEvent event){
        try {
            int id = tblTarefa.getSelectionModel().selectedItemProperty().get().getId();
            Tarefa tarefaM = fachada.Fachada.getSingleton().recuperarTarefa(id);
            fachada.Fachada.getSingleton().removerTarefa(tarefaM);
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Tarefa Removida");
            alert.showAndWait();
        } catch (ExceptionErroNoBanco | DadoInexistenteException | DadoNuloException ex) {
            Logger.getLogger(BacklogMonitorController.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }
    
}
