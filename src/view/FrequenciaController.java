/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Aluno;
import entidades.Frequencia;
import entidades.Monitor;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Frequencia.DataInvalidaException;
import exceptions.entidades.Frequencia.FrequenciaInvalidaException;
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
import static view.LoginController.chamarNovaTela;
import static view.LoginController.pessoa;

/**
 * FXML Controller class
 *
 * @author Guilherme
 */
public class FrequenciaController implements Initializable {

    @FXML
    private ToggleGroup grupo;
    @FXML
    private TableView<Frequencia> tblFrequencia;
    @FXML
    private TableColumn<Frequencia, Aluno> clAluno;
    @FXML
    private TableColumn<Frequencia, String> clPresenca;
    @FXML
    private TableColumn<Frequencia, String> clData;
    @FXML
    private TextField txtData;
    @FXML
    private ComboBox<Aluno> cbAluno;
    @FXML
    private Button btSalvar;
    @FXML
    private Button btAlterar;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarAlunos();
            configuraColunas();
            configurarBindings();
            atualizarDadosTabela();
        } catch (ExceptionErroNoBanco | DadoInexistenteException ex) {
            System.out.println(ex.getMessage());
        }
    }    
    
    @FXML
    public void encaminharParaInicio(ActionEvent event) throws IOException{
        chamarNovaTela(event, "TelaInicialMonitor.fxml", "Inicio Monitor");
    }
    
    public void carregarAlunos() throws ExceptionErroNoBanco, DadoInexistenteException{
        Monitor m  = (Monitor) pessoa;
        List<Aluno> alunos = fachada.Fachada.getSingleton().recuperarTodosAlunosPorCodMonitor(m.getId());
        ObservableList<Aluno> obsAlunos = FXCollections.observableArrayList(alunos); 
        cbAluno.setItems(obsAlunos);
    }
    
    private void atualizarDadosTabela() throws ExceptionErroNoBanco {
        Monitor p = (Monitor) pessoa;
        List<Frequencia> t = fachada.Fachada.getSingleton().recuperarTodosFrequenciaPorMonit(p.getId());
	tblFrequencia.getItems().setAll(t);
	reiniciarCampos();
    }
    
    public void reiniciarCampos() {
        tblFrequencia.getSelectionModel().select(null);
	cbAluno.getSelectionModel().selectFirst();
        txtData.setText("**/**/****");
    }
    
    private void configurarBindings(){
        BooleanBinding camposPreenchidos = txtData.textProperty().isEmpty();
        BooleanBinding selecaoAtiva = tblFrequencia.getSelectionModel().selectedItemProperty().isNull();
        btAlterar.disableProperty().bind(selecaoAtiva.or(camposPreenchidos));
        btSalvar.disableProperty().bind(camposPreenchidos);
        tblFrequencia.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
            if (n != null) {
		txtData.setText(n.getData());
                try {
                    Aluno m = fachada.Fachada.getSingleton().recuperarAluno(n.getAluno().getId());
                    cbAluno.getSelectionModel().select(m);
                } catch (ExceptionErroNoBanco | DadoInexistenteException ex) {
                    System.out.println(ex.getMessage());
                }
                
            }});        
    }
    
    private void configuraColunas() {
	clAluno.setCellValueFactory(new PropertyValueFactory<>("aluno"));
        clData.setCellValueFactory(new PropertyValueFactory<>("data"));
        clPresenca.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Frequencia, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Frequencia, String> param) {
            return new SimpleStringProperty(
                    param.getValue().mostrarPresenca());
        }
        });
    }
    
    public void cadastrarFrequencia(ActionEvent event){
        RadioButton radio = (RadioButton)grupo.getSelectedToggle();
        Monitor m = (Monitor) pessoa;
        if(cbAluno.getSelectionModel().getSelectedItem()!=null){
            Aluno a = cbAluno.getSelectionModel().getSelectedItem();
            try{
                Frequencia f = new Frequencia(0, a, m, 0, txtData.getText());
                if(radio.getText().equals("PRESENTE")){           
                    f.setFrequencia(1);
                }else if(radio.getText().equals("FALTA")){
                    f.setFrequencia(2);
                }
                fachada.Fachada.getSingleton().cadastrarFrequencia(f);
                atualizarDadosTabela();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCESSO");
                alert.setContentText("Criada a Frequencia");
                alert.showAndWait();
            } catch (FrequenciaInvalidaException | DadoNuloException | DadoInexistenteException | ExceptionErroNoBanco | DataInvalidaException ex) {
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
    
    public void alterarFrequencia(ActionEvent event){
        RadioButton radio = (RadioButton)grupo.getSelectedToggle();
        if(cbAluno.getSelectionModel().getSelectedItem()!=null){
            Aluno a = cbAluno.getSelectionModel().getSelectedItem();
            try{
                int id = tblFrequencia.getSelectionModel().selectedItemProperty().get().getId();
                Frequencia f = fachada.Fachada.getSingleton().recuperarFrequencia(id);
                if(radio.getText().equals("PRESENTE")){           
                    f.setFrequencia(1);
                }else if(radio.getText().equals("FALTA")){
                    f.setFrequencia(2);
                }
                f.setAluno(a);
                f.setData(txtData.getText());
                fachada.Fachada.getSingleton().alterarFrequencia(f);
                atualizarDadosTabela();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCESSO");
                alert.setContentText("Alterado Status da Frequencia");
                alert.showAndWait();
            } catch (FrequenciaInvalidaException | DadoNuloException | DadoInexistenteException | ExceptionErroNoBanco | DataInvalidaException ex) {
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
    
}
