/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static view.LoginController.chamarNovaTela;

/**
 * FXML Controller class
 *
 * @author thais
 */
public class TelaInicialProfessorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    public void encaminharCadastroPlanejamento(ActionEvent event) throws IOException{
        chamarNovaTela(event, "cadastroPlanejamento.fxml", "Cadastro Planejamento");
    }
    
}
