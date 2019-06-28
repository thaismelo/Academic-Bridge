/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import static view.LoginController.login;
import entidades.Monitor;
import entidades.Professor;
import entidades.Tarefa;
import entidades.TarefaParaMonitor;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Tarefa.ConteudoNuloException;
import exceptions.entidades.Tarefa.EstadoInvalidoException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import static view.LoginController.chamarNovaTela;

/**
 * FXML Controller class
 *
 * @author Killua
 */
public class CadastroPlanejamentoController implements Initializable {

    @FXML
    private ComboBox<Monitor> cbMonitor;
    
    @FXML
    private TextField txtTarefa;

    @FXML
    private TextField txtData;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.carregarMonitores();
        } catch (ExceptionErroNoBanco | DadoInexistenteException ex) {
            Logger.getLogger(CadastroPlanejamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void cadastrarPlanejamento(ActionEvent event){
        Professor pf = (Professor)login;
        try {
            Tarefa tarefa = new Tarefa(0,pf.getId(),1,txtTarefa.getText(),0);
            fachada.Fachada.getSingleton().cadastrarTarefa(tarefa);
            Monitor m = cbMonitor.getSelectionModel().getSelectedItem();
            TarefaParaMonitor tarefaM = new TarefaParaMonitor(0, pf.getId(),m.getId(), tarefa, txtData.getText());
            fachada.Fachada.getSingleton().cadastrarTarefaParaMonitor(tarefaM);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Tarefa cadastrada");
            alert.showAndWait();
        } catch (ExceptionErroNoBanco | ConteudoNuloException | EstadoInvalidoException  | DadoNuloException | DadoInexistenteException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText("Dados Invalidos");
            alert.showAndWait();
        }   
    }
    
    public void carregarMonitores() throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Monitor> monitores = fachada.Fachada.getSingleton().recuperarTodosMonitor();
        ObservableList<Monitor> obsMonitores = FXCollections.observableArrayList(monitores); 
        cbMonitor.setItems(obsMonitores);
    }
    
    @FXML
    public void encaminharParaInicio(ActionEvent event) throws IOException{
        chamarNovaTela(event, "TelaInicialProfessor.fxml", "Inicio Professor");
    }
    

    
    
}
