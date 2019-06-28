/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios.implementacoes;

import exceptions.banco.ExceptionErroNoBanco;
import java.util.List;
import fachada.Fachada;
import entidades.Aluno;
import entidades.Disciplina;
import entidades.Login;
import entidades.Monitor;
import entidades.Professor;
import entidades.Tarefa;

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
        List<Disciplina> ldisc = Fachada.getSingleton().recuperarTodosDisciplina();
        for(int i = 0; i< ldisc.size();i++){
            if(idDisc == ldisc.get(i).getId()){
                return true;
            }
        }
        return false;
    }
}
