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
import javafx.beans.binding.BooleanBinding;
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
    @FXML
    private Button btLogar;
    
    public static Pessoa pessoa;
    public static Login tipoDePessoa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarBindings();
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
    private void realizarLogin(ActionEvent event){
        Login l = new Login();
        l.setLogin(txtLogin.getText());
        l.setSenha(txtSenha.getText());
        try {
            l.setId(fachada.Fachada.getSingleton().verificarLogin(l));
            if(l.getId()!= -1){
                l = fachada.Fachada.getSingleton().recuperarLogin(l.getId());
                tipoDePessoa = l;
                if(l.getTipo()==1){
                    pessoa = fachada.Fachada.getSingleton().recuperarProfessorLogin(l);
                    chamarNovaTela(event, "TelaInicialProfessor.fxml", "Inicio Professor");
                }else{
                    pessoa = fachada.Fachada.getSingleton().recuperarMonitorLogin(l);
                    chamarNovaTela(event, "TelaInicialMonitor.fxml", "Inicio Monitor");
                }
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
        }catch (IOException | ExceptionErroNoBanco | DadoInexistenteException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void configurarBindings(){
        BooleanBinding camposPreenchidos = txtLogin.textProperty().isEmpty().or(txtSenha.textProperty().isEmpty());
        btLogar.disableProperty().bind(camposPreenchidos);     
    }
    
}
