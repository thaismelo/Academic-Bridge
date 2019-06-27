/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import entidades.Login;
import entidades.Pessoa;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author thais
 */
public class CadastroLoginController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
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

    @FXML
    void cadastrarLogin(ActionEvent event) {
        Login l = new Login();
        Pessoa p = new Pessoa();
        //l.setLogin();
        
    }

}
