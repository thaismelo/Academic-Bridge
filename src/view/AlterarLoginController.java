/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Login;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static view.LoginController.tipoDePessoa;

/**
 * FXML Controller class
 *
 * @author thais
 */
public class AlterarLoginController implements Initializable {
    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtSenha;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
     @FXML
    public void alterarLogin(ActionEvent event) {
        Login l = new Login(tipoDePessoa.getId(),tipoDePessoa.getTipo(), txtLogin.getText(), txtSenha.getText());
        try{
            fachada.Fachada.getSingleton().alterarLogin(l);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Login alterado");
            alert.show();
            
        }catch(Exception exc){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText(exc.getMessage());
            alert.show();
        }
    }
    
    @FXML
    public void sairDoAlterar(ActionEvent event) {
        
    }
}
