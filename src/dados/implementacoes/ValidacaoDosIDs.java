/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import exceptions.banco.ExceptionErroNoBanco;
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Aluno;
import negocio.modelo.Disciplina;
import negocio.modelo.Login;
import negocio.modelo.Monitor;
import negocio.modelo.Professor;
import negocio.modelo.Tarefa;

/**
 *
 * @author thais
 */
public class ValidacaoDosIDs {

    public static boolean verificaMonitor(int codigo) throws ExceptionErroNoBanco {
        List<Monitor> lista = Fachada.getSingleton().recuperarTodosMonitor();

        for (int i = 0; i < lista.size(); i++) {
            if (codigo == lista.get(i).getId()) {
                return true;
            }
        }
        return false;
    }

     public static boolean verificaAluno(int codigo) throws ExceptionErroNoBanco{
        List<Aluno> lista = Fachada.getSingleton().recuperarTodosAluno();
        
        for(int i=0;i<lista.size();i++){
            if(codigo == lista.get(i).getId()){
                return true;
            }
        }
        return false;
    }
     
    public static boolean verificaProfessor(int codigo) throws ExceptionErroNoBanco{
        List<Professor> lista = Fachada.getSingleton().recuperarTodosProfessor();
        
        for(int i=0;i<lista.size();i++){
            if(codigo == lista.get(i).getId()){
                return true;
            }
        }
        return false;
    }
    
    public static boolean verificaTarefa(int codigo) throws ExceptionErroNoBanco{
        List<Tarefa> lista = Fachada.getSingleton().recuperarTodosTarefa();
        
        for(int i=0;i<lista.size();i++){
            if(codigo == lista.get(i).getId()){
                return true;
            }
        }
        return false;
    }
    
    public static boolean verificaLogin(int codigo) throws ExceptionErroNoBanco{
        List<Login> lista = Fachada.getSingleton().recuperarTodosLogin();
        
        for(int i=0;i<lista.size();i++){
            if(codigo == lista.get(i).getId()){
                return true;
            }
        }
        return false;
    }
    
    public static boolean verificaDisciplina(int idDisc) throws ExceptionErroNoBanco{
        RepositorioDisciplina disc = new RepositorioDisciplina();
        List<Disciplina> ldisc = disc.recuperarTodos();
        for(int i = 0; i< ldisc.size();i++){
            if(idDisc == ldisc.get(i).getId()){
                return true;
            }
        }
        return false;
    }
}
