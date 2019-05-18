/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import dados.DAO_SQLite;
import dados.ExceptionErroNoBanco;
import dados.implementacoes.RepositorioDisciplina;
import java.util.ArrayList;
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Disciplina;
import negocio.modelo.Login;
import negocio.modelo.Professor;

/**
 *
 * @author thais
 */
public class main {
    public static void main(String[] args) throws ExceptionErroNoBanco {
        RepositorioDisciplina rep = new RepositorioDisciplina();
        Login login = new Login(34, Login.PROFESSOR, "totoi", "123");
        Professor prof = new Professor(login.getId(), 2, 14, "felipe", "dylon");        
        
        try{
            
        }catch (ExceptionErroNoBanco e){
            System.out.println("Ocorreu um erro no acesso ao banco");
            System.out.println(e.getMessage());
        }
        
    }
}
