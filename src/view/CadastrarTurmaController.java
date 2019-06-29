/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Aluno;
import entidades.Monitor;
import exceptions.banco.ExceptionErroNoBanco;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import static view.LoginController.chamarNovaTela;
import static view.LoginController.pessoa;
import static view.LoginController.tipoDePessoa;

/**
 * FXML Controller class
 *
 * @author thais
 */
public class CadastrarTurmaController implements Initializable {
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TableView<Aluno> Aluno;

    @FXML
    private TableColumn<Aluno, String> clnNome;

    @FXML
    private TableColumn<Aluno, String> clnEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarAlunos();
            atualizarDadosTabela();
        } catch (ExceptionErroNoBanco ex) {
            Logger.getLogger(CadastrarTurmaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    @FXML
    void cadastrarAluno(ActionEvent event) {
        Aluno a = new Aluno(1, (Monitor) pessoa, txtNome.getText(), txtEmail.getText());
        try{
            fachada.Fachada.getSingleton().cadastrarAluno(a);
            reiniciarCampos();
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Aluno cadastrado na sua turma");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    public void reiniciarCampos() {
	txtNome.setText("");
        txtEmail.setText("");
    }
    
    private void atualizarDadosTabela() throws ExceptionErroNoBanco {
        List<Aluno> t = fachada.Fachada.getSingleton().recuperarTodosAluno();
	Aluno.getItems().setAll(t);
	reiniciarCampos();
    }
     public void carregarAlunos() throws ExceptionErroNoBanco{
        List<Aluno> t = fachada.Fachada.getSingleton().recuperarTodosAluno();
        ObservableList<Aluno> obsAlunos = FXCollections.observableArrayList(t); 
        Aluno.setItems(obsAlunos);
    }
    @FXML
    void alterarDado(ActionEvent event) {
        try{
            int id = Aluno.getSelectionModel().selectedItemProperty().get().getId();
            Aluno a = fachada.Fachada.getSingleton().recuperarAluno(id);
            fachada.Fachada.getSingleton().alterarAluno(a);
            reiniciarCampos();
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Aluno alterado");
            alert.showAndWait();
        }catch(Exception e){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("ERRO");
           alert.setContentText(e.getMessage());
           alert.showAndWait(); 
        
       }
    }
    
     @FXML
    void deletarDado(ActionEvent event) {
        try{
            int id = Aluno.getSelectionModel().selectedItemProperty().get().getId();
            Aluno a = fachada.Fachada.getSingleton().recuperarAluno(id);
            fachada.Fachada.getSingleton().removerAluno(a);
            reiniciarCampos();
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Aluno deletado");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText(e.getMessage());
            alert.showAndWait(); 
        }
    }
    @FXML
    void voltarTelaInicial(ActionEvent event) throws IOException {
        chamarNovaTela(event, "TelaInicialMonitor.fxml", "Tela Inicial");
    }
}
