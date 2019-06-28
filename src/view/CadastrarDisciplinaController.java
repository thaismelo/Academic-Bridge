/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;



import entidades.Disciplina;
import entidades.Professor;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import static view.LoginController.pessoa;

/**
 * FXML Controller class
 *
 * @author Guilherme
 */
public class CadastrarDisciplinaController implements Initializable {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCurso;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void cadastrarDisciplina(ActionEvent event){
        Disciplina d = new Disciplina(0, txtNome.getText(), txtCurso.getText(), (Professor) pessoa);
        try {
            fachada.Fachada.getSingleton().cadastrarDisciplina(d);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Disciplina cadastrada");
            alert.showAndWait();
        } catch (ExceptionErroNoBanco | DadoNuloException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText("Dados Invalidos");
            alert.showAndWait();
        }
        
    }
    
}
