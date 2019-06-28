/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Login;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static view.LoginController.chamarNovaTela;
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
            encaminharInicio(event);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setContentText("Login alterado com sucesso!");
            alert.show();
            
        }catch(Exception exc){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText(exc.getMessage());
            alert.show();
        }
    }
    
    
    public void encaminharInicio(ActionEvent event) throws IOException{
        if(tipoDePessoa.getTipo()==1){
            chamarNovaTela(event, "TelaInicialProfessor.fxml", "Inicio Professor");
        }else{
            // Aqui coloca a pagina do monitor, mas como n tem ainda, coloquei nessa so pra não ficar vazio
            chamarNovaTela(event, "Login.fxml", "Login");
        }
    }
}
