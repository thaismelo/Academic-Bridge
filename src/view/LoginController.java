/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Login;
import entidades.Pessoa;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.ExceptionErroNoBanco;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author thais
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
   
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtSenha;
    public static Pessoa login;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public static void chamarNovaTela(ActionEvent e, String novaTela, String titulo) throws IOException {
        Parent novo = FXMLLoader.load((LoginController.class.getResource(novaTela)));
        Scene novaCena = new Scene(novo);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(novaCena);
        window.setTitle(titulo);
        window.show();
    }

    @FXML
    private void realizarLogin(ActionEvent event) throws ExceptionErroNoBanco, DadoInexistenteException{
        Login l = new Login();
        l.setLogin(txtLogin.getText());
        l.setSenha(txtSenha.getText());
        l.setTipo(1);
        try {
            l.setId(fachada.Fachada.getSingleton().verificarLogin(l));
            if(l.getId()!= -1){
                l = fachada.Fachada.getSingleton().recuperarLogin(l.getId());
                login = fachada.Fachada.getSingleton().recuperarProfessorLogin(l);
                chamarNovaTela(event, "CadastroPlanejamento.fxml", "Inicio Professor");
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setContentText("Login realizado");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setContentText("Login e/ou Senha incorreto(s)");
                alert.showAndWait();
            }
        }catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
