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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarBindings();
        try {
            carregarDisciplinas();
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login do Monitor Gerado");
            alert.setHeaderText("Novo Monitor Cadastrado");
            alert.setContentText("Login: "+ login.getLogin() +"\n" + "Senha: "+login.getSenha());
            alert.show();
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
    
    private void configurarBindings(){
        BooleanBinding camposPreenchidos = txtNome.textProperty().isEmpty().or(txtEmail.textProperty().isEmpty());
        btCadastrar.disableProperty().bind(camposPreenchidos);     
    }
}
