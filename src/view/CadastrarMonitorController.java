/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Disciplina;
import entidades.Login;
import entidades.Monitor;
import entidades.Professor;
import entidades.TarefaParaMonitor;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Login.LoginExistenteException;
import exceptions.entidades.Login.LoginNuloException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import static view.LoginController.chamarNovaTela;
import static view.LoginController.pessoa;

/**
 * FXML Controller class
 *
 * @author thais
 */
public class CadastrarMonitorController implements Initializable {

    @FXML
    private ComboBox<Disciplina> cbDisciplinas;
     @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;
    
    @FXML
    private Button btCadastrar; 
      @FXML
    private Button btAlterar;

    @FXML
    private Button btRemover;

    @FXML
    private TableView<Monitor> tblMonitor;

    @FXML
    private TableColumn<Monitor, String> clnNome;

    @FXML
    private TableColumn<Monitor, String> clnEmail;

    @FXML
    private TableColumn<Monitor, Disciplina> clnDisc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarDisciplinas();
            configurarBindings();
            atualizarDadosTabela();
            configuraColunas();
        } catch (ExceptionErroNoBanco | DadoInexistenteException ex) {
            Logger.getLogger(CadastrarMonitorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void carregarDisciplinas() throws ExceptionErroNoBanco, DadoInexistenteException{
        Professor p = (Professor) pessoa;
        List<Disciplina> disciplinas = fachada.Fachada.getSingleton().recuperarDisciplinasPorProf(p.getId());
        ObservableList<Disciplina> obsDisciplinas = FXCollections.observableArrayList(disciplinas);
        cbDisciplinas.setItems(obsDisciplinas);
    }
    
    @FXML
    private void cadastrarLogin(ActionEvent event) throws ExceptionErroNoBanco, LoginExistenteException, SenhaInvalidaException, SenhaNulaException, DadoInexistenteException, LoginNuloException{
        if(cbDisciplinas.getSelectionModel().getSelectedItem()!=null && Monitor.validarEmail(txtEmail.getText())== true){    
            try{
            Login login  = new Login(1, Login.MONITOR, "blba", "123");
            Disciplina d = cbDisciplinas.getSelectionModel().getSelectedItem();
            fachada.Fachada.getSingleton().cadastrarLogin(login);
            Monitor monitor = new Monitor(12, login,(Professor) pessoa, txtNome.getText(), txtEmail.getText(),d);
            fachada.Fachada.getSingleton().cadastrarMonitor(monitor);
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login do Monitor Gerado");
            alert.setHeaderText("Novo Monitor Cadastrado");
            alert.setContentText("Login: "+ login.getLogin() +"\n" + "Senha: "+login.getSenha());
            alert.show();
            reiniciarCampos();
           }catch(NomeInvalidoException | EmailInvalidoException | DadoNuloException | NullPointerException ex){
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("ERRO");
               alert.setContentText("Dados Invalidos");
               alert.showAndWait();
           }
       }else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("ERRO");
           alert.setContentText("Dados Invalidos");
           alert.showAndWait();
        }
    }
    
    @FXML
    void voltarTelaInicial(ActionEvent event) throws IOException {
         chamarNovaTela(event,  "TelaInicialProfessor.fxml", "Tela Inicial");
    }
    
    public void reiniciarCampos() {
        tblMonitor.getSelectionModel().select(null);
	txtEmail.setText("");
        txtNome.setText("");
        cbDisciplinas.getSelectionModel().selectFirst();
    }
    
     private void atualizarDadosTabela() throws ExceptionErroNoBanco {
        Professor p = (Professor) pessoa;
        List<Monitor> t = fachada.Fachada.getSingleton().recuperarTodosMonitorPorProf(p);
	tblMonitor.getItems().setAll(t);
	reiniciarCampos();
    }
     
    private void configurarBindings(){
        BooleanBinding camposPreenchidos = txtNome.textProperty().isEmpty().or(txtEmail.textProperty().isEmpty());
        BooleanBinding selecaoAtiva = tblMonitor.getSelectionModel().selectedItemProperty().isNull();
        btRemover.disableProperty().bind(selecaoAtiva);
        btAlterar.disableProperty().bind(selecaoAtiva.or(camposPreenchidos));
        btCadastrar.disableProperty().bind(camposPreenchidos);
        tblMonitor.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
            if (n != null) {
		txtNome.setText(n.getNome());
		txtEmail.setText(n.getEmail());
                try {
                    Disciplina d = fachada.Fachada.getSingleton().recuperarDisciplina(n.getDisciplina().getId());
                    cbDisciplinas.getSelectionModel().select(d);
                } catch (ExceptionErroNoBanco | DadoInexistenteException ex) {
                    System.out.println(ex.getMessage());
                }
                
            }});        
    }
    
    private void configuraColunas() {
	clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
	clnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clnDisc.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
    }
      
     @FXML
    void alterarMonitor(ActionEvent event) {
         if(cbDisciplinas.getSelectionModel().getSelectedItem()!=null){
            Disciplina a = cbDisciplinas.getSelectionModel().getSelectedItem();
            try{
                int id = tblMonitor.getSelectionModel().selectedItemProperty().get().getId();
                Monitor f = fachada.Fachada.getSingleton().recuperarMonitor(id);
                f.setDisciplina(a);
                f.setNome(txtNome.getText());
                f.setEmail(txtEmail.getText());
                fachada.Fachada.getSingleton().alterarMonitor(f);
                atualizarDadosTabela();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCESSO");
                alert.setContentText("Monitor alterado");
                alert.showAndWait();
            } catch (Exception ex) {
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
    
    @FXML
    void removerDado(ActionEvent event) {
        try {
            int id = tblMonitor.getSelectionModel().selectedItemProperty().get().getId();
            Monitor m = fachada.Fachada.getSingleton().recuperarMonitor(id);
            fachada.Fachada.getSingleton().removerMonitor(m);
            reiniciarCampos();
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Monitor removido");
            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }    
    }

}
