/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entidades.Monitor;
import entidades.Tarefa;
import entidades.TarefaParaMonitor;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.ExceptionErroNoBanco;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import static view.LoginController.pessoa;

/**
 * FXML Controller class
 *
 * @author thais
 */
public class QuadroDeTarefasController implements Initializable {
    
    @FXML
    private TableView<TarefaParaMonitor> tblTarefaMonitor;

    @FXML
    private TableColumn<TarefaParaMonitor, String> clnTarefa;

    @FXML
    private TableColumn<TarefaParaMonitor, String> clnDataLimite;

    @FXML
    private TableColumn<TarefaParaMonitor, String> clnStatus;
    
    @FXML
    private TableColumn<TarefaParaMonitor, String> clnMonitor;
    
    @FXML
    private TextField txtStatus;
    
    @FXML
    private Button btAlterar;

    
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
    
    public void configuraColunas(){
        clnDataLimite.setCellValueFactory(new PropertyValueFactory<>("data"));
        //
        clnTarefa.setCellValueFactory(new PropertyValueFactory<>("conteudo"));
        clnTarefa.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TarefaParaMonitor, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<TarefaParaMonitor, String> param) {
            try {
                return new SimpleStringProperty(
                        fachada.Fachada.getSingleton().recuperarMonitor(param.getValue().getCodMonit()).getNome());
            } catch (ExceptionErroNoBanco | DadoInexistenteException ex) {
                Logger.getLogger(CadastroPlanejamentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        });
        clnStatus.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }
   
    private void atualizarDadosTabela() throws ExceptionErroNoBanco {
        Monitor m = (Monitor) pessoa;
        List<TarefaParaMonitor> t = fachada.Fachada.getSingleton().recuperarTodosPorCodProfTarefaParaMonitor(m.getId());
	tblTarefaMonitor.getItems().setAll(t);
	//reiniciarCampos();
    }
}
