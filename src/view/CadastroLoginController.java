/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Login;
import entidades.Pessoa;
import entidades.Professor;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Disciplina.DisciplinaInexistenteException;
import exceptions.entidades.Login.LoginExistenteException;
import exceptions.entidades.Login.LoginNuloException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import fachada.Fachada;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author thais
 */
public class CadastroLoginController implements Initializable {

    @FXML
    private TextField txtLogin;

    @FXML
    private Button cadastrarLogin;

    @FXML
    private Button chamarTelaLogin;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    public void cadastrarLogin(ActionEvent event) throws ExceptionErroNoBanco, LoginExistenteException, SenhaInvalidaException, SenhaNulaException, DadoInexistenteException, LoginNuloException, NomeInvalidoException, EmailInvalidoException, DisciplinaInexistenteException, DadoNuloException {
        Login l = new Login();
        Professor prof = new Professor();
        l.setLogin(txtLogin.getText());
        l.setSenha(txtSenha.getText());
        l.setTipo(1);
        prof.setLogin(l);
        prof.setNome(txtNome.getText());
        prof.setEmail(txtEmail.getText());
        try {
            fachada.Fachada.getSingleton().cadastrarLogin(l);
            int id = Fachada.getSingleton().recuperaUltimoIdLogin();
            fachada.Fachada.getSingleton().cadastrarProfessor(prof);
            chamarNovaTela(event, "TelaInicialProfessor.fxml", "Tela Inicial");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    public static void chamarNovaTela(ActionEvent e, String novaTela, String titulo) throws IOException {
        Parent novo = FXMLLoader.load((LoginController.class.getResource(novaTela)));
        Scene novaCena = new Scene(novo);

        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(novaCena);
        window.setTitle(titulo);
        window.show();
    }

    @FXML
    public void chamarTelaLogin(ActionEvent event) throws IOException {
        chamarNovaTela(event, "LoginTela.fxml", "Login");
    }

}
