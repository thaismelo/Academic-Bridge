/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Disciplina;
import entidades.Login;
import entidades.Monitor;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Login.LoginExistenteException;
import exceptions.entidades.Login.LoginNuloException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import static view.LoginController.chamarNovaTela;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void carregarDisciplinas() throws ExceptionErroNoBanco, DadoInexistenteException{
        int idProf = fachada.Fachada.getSingleton().recuperarUltimoIdProfessor();
        List<Disciplina> disciplinas = fachada.Fachada.getSingleton().recuperarDisciplinasPorProf(idProf);
        ObservableList<Disciplina> obsDisciplinas = FXCollections.observableArrayList(disciplinas);
        cbDisciplinas.setItems(obsDisciplinas);
    }
    
    @FXML
    private void cadastrarLogin(ActionEvent event) throws ExceptionErroNoBanco, LoginExistenteException, SenhaInvalidaException, SenhaNulaException, DadoInexistenteException, LoginNuloException{
        Login login  = new Login(1, Login.MONITOR, "blba", "123");
        fachada.Fachada.getSingleton().cadastrarLogin(login);
        //Monitor monitor = new Monitor(12, login, txtNome.getText(), txtEmail.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login do Monitor Gerado");
        alert.setHeaderText("Novo Monitor Cadastrado");
        alert.setContentText("Login: "+ login.getLogin() +"\n" + "Senha: "+login.getSenha());
        alert.show();
        
    }
    
     @FXML
    void voltarTelaInicial(ActionEvent event) throws IOException {
         chamarNovaTela(event,  "TelaInicialProfessor.fxml", "Tela Inicial");
    }
}
