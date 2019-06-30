/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;



import entidades.Disciplina;
import entidades.Professor;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static view.LoginController.chamarNovaTela;
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
    
    @FXML
    private Button btCadastrar;

    @FXML
    private Button btAlterar;

    @FXML
    private Button btRemover;

    @FXML
    private TableView<Disciplina> tblDisciplina;

    @FXML
    private TableColumn<Disciplina, String> clnNome;

    @FXML
    private TableColumn<Disciplina, String> clnCurso;
    
   /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarDisciplinas();
            configurarBindings();
            carregarDisciplinas();
            atualizarDadosTabela();
            configuraColunas();
        } catch (ExceptionErroNoBanco | DadoInexistenteException ex) {
            Logger.getLogger(CadastrarDisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    public void cadastrarDisciplina(ActionEvent event) throws DadoInexistenteException{
        Professor p = (Professor) pessoa;
        Disciplina d = new Disciplina(0, txtNome.getText(), txtCurso.getText(),p);
        try {
            fachada.Fachada.getSingleton().cadastrarDisciplina(d);
            reiniciarCampos();
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Disciplina cadastrada");
            alert.showAndWait();
        } catch (ExceptionErroNoBanco | DadoNuloException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        
    }
    public void reiniciarCampos() {
        tblDisciplina.getSelectionModel().select(null);
	txtNome.setText("");
        txtCurso.setText("");
    }
    
    private void atualizarDadosTabela() throws ExceptionErroNoBanco, DadoInexistenteException {
        Professor p = (Professor) pessoa;
        List<Disciplina> t = fachada.Fachada.getSingleton().recuperarDisciplinasPorProf(p.getId());
	tblDisciplina.getItems().setAll(t);
	reiniciarCampos();
    }
     public void carregarDisciplinas() throws ExceptionErroNoBanco{
        List<Disciplina> d = fachada.Fachada.getSingleton().recuperarTodosDisciplina();
        ObservableList<Disciplina> obsDisc = FXCollections.observableArrayList(d); 
        tblDisciplina.setItems(obsDisc);
    }
     
     public void configuraColunas(){
        clnNome.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
       clnCurso.setCellValueFactory(
                new PropertyValueFactory<>("curso"));
       
    }
     @FXML
    void voltarTelaInicial(ActionEvent event) throws IOException {
         chamarNovaTela(event, "TelaInicialProfessor.fxml", "Tela Inicial");
    }
    
    private void configurarBindings(){
        BooleanBinding camposPreenchidos = txtCurso.textProperty().isEmpty().or(txtNome.textProperty().isEmpty());
        btCadastrar.disableProperty().bind(camposPreenchidos);     
        BooleanBinding selecaoAtiva = tblDisciplina.getSelectionModel().selectedItemProperty().isNull();
        btRemover.disableProperty().bind(selecaoAtiva);
        btAlterar.disableProperty().bind(selecaoAtiva.or(camposPreenchidos));
        tblDisciplina.getSelectionModel().selectedItemProperty().addListener((b, o, n) -> {
            if (n != null) {
                txtNome.setText(n.getNome());
		txtCurso.setText(n.getCurso());
            }
        });
    }
    
     @FXML
    void alterarDisciplina(ActionEvent event) {
        try{
            int id = tblDisciplina.getSelectionModel().selectedItemProperty().get().getId();
            Disciplina a = fachada.Fachada.getSingleton().recuperarDisciplina(id);
            a.setNome(txtNome.getText());
            a.setCurso(txtCurso.getText());
            fachada.Fachada.getSingleton().alterarDisciplina(a);
            reiniciarCampos();
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Disciplina alterada");
            alert.showAndWait();
        }catch(Exception e){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("ERRO");
           alert.setContentText(e.getMessage());
           alert.showAndWait(); 
        
       }
    }
    
     @FXML
    void removerDisciplina(ActionEvent event) {
        try{
            int id = tblDisciplina.getSelectionModel().selectedItemProperty().get().getId();
            Disciplina a = fachada.Fachada.getSingleton().recuperarDisciplina(id);
            fachada.Fachada.getSingleton().removerDisciplina(a);
            reiniciarCampos();
            atualizarDadosTabela();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO");
            alert.setContentText("Disciplina deletada");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setContentText(e.getMessage());
            alert.showAndWait(); 
        }
    }
    
}
