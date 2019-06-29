/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Monitor;
import entidades.Tarefa;
import entidades.TarefaParaMonitor;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author thais
 */
public class QuadroDeTarefasController implements Initializable {
    @FXML
    private TableView<TarefaParaMonitor> tabelaTarefas;

    @FXML
    private TableColumn<Monitor, String> clnMonitor;

    @FXML
    private TableColumn<Tarefa, String> clnTarefa;

    @FXML
    private TableColumn<TarefaParaMonitor, String> clnDataLimite;

    @FXML
    private TableColumn<Tarefa, Integer> clnStatus;
    
    @FXML
    private ComboBox<Tarefa> cbStatus;

    
    @FXML
    void atualizarStatus(ActionEvent event) {

    }

    @FXML
    void salvarStatus(ActionEvent event) {

    }

    @FXML
    void voltarTelaInicial(ActionEvent event) {

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initTable(){
        clnMonitor.setCellValueFactory(new PropertyValueFactory<Monitor,String>("nome"));
        clnTarefa.setCellValueFactory(new PropertyValueFactory<Tarefa,String>("conteudo"));
        clnDataLimite.setCellValueFactory(new PropertyValueFactory<TarefaParaMonitor,String>("data"));
        clnStatus.setCellValueFactory(new PropertyValueFactory<Tarefa,Integer>("estado"));
    }
    
    //public ObservableList<TarefaParaMonitor> atualizaTabela(){
        
    //}
}
