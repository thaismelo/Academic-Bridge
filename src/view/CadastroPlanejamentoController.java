/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import entidades.Monitor;
import entidades.Professor;
import entidades.Tarefa;
import entidades.TarefaParaMonitor;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Frequencia.DataInvalidaException;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import static view.LoginController.chamarNovaTela;
import static view.LoginController.pessoa;

/**
 * FXML Controller class
 *
 * @author Guilherme
 */
public class CadastroPlanejamentoController implements Initializable {

    @FXML
    private ComboBox<Monitor> cbMonitor;
    
    @FXML
    private TextField txtTarefa;

    @FXML
    private TextField txtData;
    
    @FXML
    private TableView<TarefaParaMonitor> tblPlanejamento;
    @FXML
    private TableColumn<TarefaParaMonitor, Tarefa> clTarefa;
    @FXML
    private TableColumn<TarefaParaMonitor, String> clMonitor;
    @FXML
    private TableColumn<TarefaParaMonitor, String> clData;
    @FXML
    private TableColumn<TarefaParaMonitor, String> clEstado;
    
    @FXML
    private Button btCadastrar;
    @FXML
    private Button btAlterar;
    @FXML
    private Button btRemover;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarMonitores();
            configuraColunas();
            configurarBindings();
            cbMonitor.getSelectionModel().selectFirst();
            atualizarDadosTabela();
        } catch (ExceptionErroNoBanco | DadoInexistenteException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
    @FXML
    public void cadastrarPlanejamento(ActionEvent event){
        Professor pf = (Professor)pessoa;
        if(cbMonitor.getSelectionModel().getSelectedItem()!=null){
            try {
                Tarefa tarefa = new Tarefa(0,pf.getId(),1,txtTarefa.getText(),0);
                fachada.Fachada.getSingleton().cadastrarTarefa(tarefa);
                Monitor m = cbMonitor.getSelectionModel().getSelectedItem();
                TarefaParaMonitor tarefaM = new TarefaParaMonitor(0, pf.getId(),m.getId(), m.getDisciplina().getId(),tarefa, txtData.getText());
                fachada.Fachada.getSingleton().cadastrarTarefaParaMonitor(tarefaM);
                reiniciarCampos();
                atualizarDadosTabela();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCESSO");
                alert.setContentText("Atividade cadastrada");
                alert.showAndWait();
            } catch (ExceptionErroNoBanco | ConteudoNuloException | EstadoInvalidoException  | DadoNuloException | DadoInexistenteException | NullPointerException | DataInvalidaException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText("Dados Invalidos");
            alert.showAndWait();
        }
    }

    public void alterarPlanejamento(ActionEvent event){
        try {
            int id = tblPlanejamento.getSelectionModel().selectedItemProperty().get().getId();
            Monitor m = cbMonitor.getSelectionModel().getSelectedItem();
            TarefaParaMonitor tarefaM = fachada.Fachada.getSingleton().recuperarTarefaParaMonitor(id);
            tarefaM.getTarefaParaMonitor().setConteudo(txtTarefa.getText());
            tarefaM.setData(txtData.getText());
            tarefaM.setCodMonit(m.getId());
            tarefaM.setCodDisciplina(m.getDisciplina().getId());
            fachada.Fachada.getSingleton().alterarTarefa(tarefaM.getTarefaParaMonitor());
            fachada.Fachada.getSingleton().alterarTarefaParaMonitor(tarefaM);
            reiniciarCampos();
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Atividade Alterada");
            alert.showAndWait();
        } catch (ExceptionErroNoBanco | ConteudoNuloException | EstadoInvalidoException  | DadoNuloException | DadoInexistenteException | DataInvalidaException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    
    public void removerPlanejamento(ActionEvent event){  
        try {
            int id = tblPlanejamento.getSelectionModel().selectedItemProperty().get().getId();
            TarefaParaMonitor tarefaM = fachada.Fachada.getSingleton().recuperarTarefaParaMonitor(id);
            fachada.Fachada.getSingleton().removerTarefaParaMonitor(tarefaM);
            reiniciarCampos();
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Atividade Removida");
            alert.showAndWait();
        } catch (ExceptionErroNoBanco | DadoInexistenteException | DadoNuloException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }    
    }
    
    public void carregarMonitores() throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Monitor> monitores = fachada.Fachada.getSingleton().recuperarTodosMonitorPorProf((Professor) pessoa);
        ObservableList<Monitor> obsMonitores = FXCollections.observableArrayList(monitores); 
        cbMonitor.setItems(obsMonitores);
    }
    
    @FXML
    public void encaminharParaInicio(ActionEvent event) throws IOException{
        chamarNovaTela(event, "TelaInicialProfessor.fxml", "Inicio Professor");
    }
    
    private void configuraColunas() {
	clTarefa.setCellValueFactory(new PropertyValueFactory<>("tarefaParaMonitor"));
	clData.setCellValueFactory(new PropertyValueFactory<>("data"));
        clMonitor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TarefaParaMonitor, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<TarefaParaMonitor, String> param) {
            try {
                return new SimpleStringProperty(
                        fachada.Fachada.getSingleton().recuperarMonitor(param.getValue().getCodMonit()).getNome());
            } catch (ExceptionErroNoBanco | DadoInexistenteException ex) {
                System.out.println(ex.getMessage());
            }
            return null;
        }
        });
        clEstado.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TarefaParaMonitor, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<TarefaParaMonitor, String> param) {
            return new SimpleStringProperty(
                    param.getValue().getTarefaParaMonitor().mostrarEstado());
        }
        });
    }
    
    private void atualizarDadosTabela() throws ExceptionErroNoBanco {
        Professor p = (Professor) pessoa;
        List<TarefaParaMonitor> t = fachada.Fachada.getSingleton().recuperarTodosPorCodProfTarefaParaMonitor(p.getId());
	tblPlanejamento.getItems().setAll(t);
	reiniciarCampos();
    }
    
    public void reiniciarCampos() {
        tblPlanejamento.getSelectionModel().select(null);
	cbMonitor.getSelectionModel().selectFirst();
	txtTarefa.setText("");
        txtData.setText("**/**/****");
    }
    
    private void configurarBindings(){
        BooleanBinding camposPreenchidos = txtTarefa.textProperty().isEmpty().or(txtData.textProperty().isEmpty());
        BooleanBinding selecaoAtiva = tblPlanejamento.getSelectionModel().selectedItemProperty().isNull();
        btRemover.disableProperty().bind(selecaoAtiva);
        btAlterar.disableProperty().bind(selecaoAtiva.or(camposPreenchidos));
        btCadastrar.disableProperty().bind(camposPreenchidos);
        tblPlanejamento.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
            if (n != null) {
		txtData.setText(n.getData());
		txtTarefa.setText(n.getTarefaParaMonitor().getConteudo());
                try {
                    Monitor m = fachada.Fachada.getSingleton().recuperarMonitor(n.getCodMonit());
                    cbMonitor.getSelectionModel().select(m);
                } catch (ExceptionErroNoBanco | DadoInexistenteException ex) {
                    System.out.println(ex.getMessage());
                }
                
            }});        
    }

}
