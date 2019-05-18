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
        Login login = new Login(222, Login.PROFESSOR, "PitPat", "555");
        
        try{
            Fachada.getSingleton().cadastrarLogin(login);
            Professor prof = new Professor(login.getId(), 5, 65, "Vader", "fake@Gmail");
            Fachada.getSingleton().cadastrarProfessor(prof);
            
        }catch (ExceptionErroNoBanco e){
            System.out.println("Ocorreu um erro no acesso ao banco");
            System.out.println(e.getMessage());
        }
        
    }
}
