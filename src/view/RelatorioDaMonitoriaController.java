/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Monitor;
import entidades.RelatorioMonitoria;
import entidades.Tarefa;
import entidades.TarefaParaMonitor;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.ExceptionErroNoBanco;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import static view.LoginController.pessoa;

/**
 * FXML Controller class
 *
 * @author thais
 */
public class RelatorioDaMonitoriaController implements Initializable {

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
    
    @FXML
    private ToggleGroup grupo;
    @FXML
    private ComboBox<Integer> nivelDificuldade;

    @FXML
    private ComboBox<Integer> participatividade;
 
    @FXML
    private Button btSalvar;

    @FXML
    private Button btAlterar;

    @FXML
    private TextField txtTarefa;
   
    @FXML
    private TextField data;

    @FXML
    private Button btApagar;
    
    private ObservableList<Integer> listPorcentagens = FXCollections.observableArrayList(10,20,30,40,50,60,70,80,90,100);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nivelDificuldade.setItems(listPorcentagens);
        participatividade.setItems(listPorcentagens);
        try {
            configuraColunas();
            configurarBindings();
            participatividade.getSelectionModel().selectFirst();
            nivelDificuldade.getSelectionModel().selectFirst();
            atualizarDadosTabela();
        } catch (ExceptionErroNoBanco ex) {
            Logger.getLogger(RelatorioDaMonitoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
   public void salvaDados(ActionEvent event) {
        Monitor m = (Monitor) pessoa;
        RadioButton radio = (RadioButton) grupo.getSelectedToggle();
        if (nivelDificuldade.getSelectionModel().getSelectedItem() != null && participatividade.getSelectionModel().getSelectedItem() != null) {
            try {
                Tarefa tarefa = new Tarefa(13, m.getId(), 2, txtTarefa.getText(), 0);
                fachada.Fachada.getSingleton().cadastrarTarefa(tarefa);
                int nDificuldade = nivelDificuldade.getSelectionModel().getSelectedItem();
                int partic = participatividade.getSelectionModel().getSelectedItem();
                if (radio.getText().equals("SIM")) {
                    RelatorioMonitoria relatorio = new RelatorioMonitoria(1, m, tarefa, nDificuldade, 1, partic, data.getText());
                    fachada.Fachada.getSingleton().cadastrarRelatorioMonitoria(relatorio);
                    reiniciarCampos();
                    atualizarDadosTabela();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SUCESSO");
                    alert.setContentText("Relatório cadastrado");
                    alert.showAndWait();
                } else if (radio.getText().equals("NÃO")) {
                    RelatorioMonitoria relatorio = new RelatorioMonitoria(1, m, tarefa, nDificuldade, 2, partic, data.getText());
                    fachada.Fachada.getSingleton().cadastrarRelatorioMonitoria(relatorio);
                    reiniciarCampos();
                    atualizarDadosTabela();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("SUCESSO");
                    alert.setContentText("Relatório cadastrado");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText("Dados Invalidos");
            alert.showAndWait();
        }
    }
   
   public void reiniciarCampos() {
        tabelaRelatorio.getSelectionModel().select(null);
	participatividade.getSelectionModel().selectFirst();
        nivelDificuldade.getSelectionModel().selectFirst();
	txtTarefa.setText("");
        data.setText("**/**/****");
    }
   
    private void atualizarDadosTabela() throws ExceptionErroNoBanco {
        Monitor m = (Monitor) pessoa;
        List<RelatorioMonitoria> t = fachada.Fachada.getSingleton().recuperarTodosRelatoriosPorCodMonitor(m.getId());
	tabelaRelatorio.getItems().setAll(t);
	reiniciarCampos();
    }
    
     @FXML
    void deletarDado(ActionEvent event) {
        try {
            int id = tabelaRelatorio.getSelectionModel().selectedItemProperty().get().getId();
            RelatorioMonitoria relatorio = fachada.Fachada.getSingleton().recuperarRelatorioMonitoria(id);
            fachada.Fachada.getSingleton().removerRelatorioMonitoria(relatorio);
            reiniciarCampos();
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Relatório Removido");
            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }    
    }
    
     @FXML
    void alterarTabela(ActionEvent event) {
         RadioButton radio = (RadioButton)grupo.getSelectedToggle();
        try{
            int id = tabelaRelatorio.getSelectionModel().selectedItemProperty().get().getId();
            int nDificuldade = nivelDificuldade.getSelectionModel().getSelectedItem();
            int partic = participatividade.getSelectionModel().getSelectedItem();
            RelatorioMonitoria relatorio = fachada.Fachada.getSingleton().recuperarRelatorioMonitoria(id);
            if(radio.getText().equals("SIM")){           
                relatorio.setReforcarAssunto(1);
            }else if(radio.getText().equals("NÃO")){
                relatorio.setReforcarAssunto(2);
            }
            relatorio.setData(data.getText());
            relatorio.setNivelDificuldade(nDificuldade);
            relatorio.setParticipatividade(partic);
            relatorio.getTarefa().setConteudo(txtTarefa.getText());
            fachada.Fachada.getSingleton().alterarTarefa(relatorio.getTarefa());
            fachada.Fachada.getSingleton().alterarRelatorioMonitoria(relatorio);
            reiniciarCampos();
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Relatório Alterado");
            alert.showAndWait();
       }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
       }
    }

    @FXML
    void chamarTelaInicial(ActionEvent event) throws IOException {
        LoginController.chamarNovaTela(event, "TelaInicialMonitor.fxml", "Tela Inicial");
    }
    private void configuraColunas() {
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
    
     private void configurarBindings(){
        BooleanBinding camposPreenchidos = txtTarefa.textProperty().isEmpty().or(data.textProperty().isEmpty());
        BooleanBinding selecaoAtiva = tabelaRelatorio.getSelectionModel().selectedItemProperty().isNull();
        btApagar.disableProperty().bind(selecaoAtiva);
        btAlterar.disableProperty().bind(selecaoAtiva.or(camposPreenchidos));
        btSalvar.disableProperty().bind(camposPreenchidos);
        tabelaRelatorio.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
            if (n != null) {
		data.setText(n.getData());
		txtTarefa.setText(n.getTarefa().getConteudo());
                participatividade.getSelectionModel().select(n.getParticipatividade());
                nivelDificuldade.getSelectionModel().select(n.getNivelDificuldade());
            }});        
    }
    
}
