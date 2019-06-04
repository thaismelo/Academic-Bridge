/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios.implementacoes;

import repositorios.implementacoes.banco.RepositorioProfessor;
import exceptions.banco.ExceptionErroNoBanco;
import irepositorios.interfaces.RepositorioGenerico;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.entidades.Disciplina.DisciplinaInexistenteException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import exceptions.entidades.Tarefa.ListaTarefaVaziaException;
import java.util.List;
import fachada.Fachada;
import entidades.Disciplina;
import entidades.Pessoa;
import entidades.Professor;
import exceptions.entidades.Login.SenhaInvalidaException;
/**
 *
 * @author Guilherme
 */
public class CRUDProfessor {
    private RepositorioGenerico repProfessor;

    public CRUDProfessor() {
        repProfessor = new RepositorioProfessor();
    }
    
    public void cadastrarProfessor(Professor professor) throws ExceptionErroNoBanco, EmailInvalidoException, NomeInvalidoException, DadoInexistenteException,DisciplinaInexistenteException, DadoNuloException, SenhaInvalidaException{
        if(professor.getNome()==null){
            throw new NomeInvalidoException();
        }
        if(Professor.validarEmail(professor.getEmail())== false){
            throw new EmailInvalidoException();
        }
        if(professor.getLogin()==null){
            throw new DadoNuloException();
        }
        if(professor.getLogin().getSenha().length()<8){
            throw new SenhaInvalidaException();
        }
        if(ValidacaoDosIDs.verificaLogin(professor.getLogin().getId())==false){
            throw new DadoInexistenteException();
        }

        repProfessor.inserir(professor); 
    }
    
    public void removerProfessor(Professor professor) throws ExceptionErroNoBanco, DadoNuloException{
            if(professor!=null){
                repProfessor.excluir(professor);
            }else{
                throw new DadoNuloException();
            }
        
    }    
    public void alterarProfessor(Professor professor) throws ExceptionErroNoBanco, DadoInexistenteException, NomeInvalidoException, EmailInvalidoException,DisciplinaInexistenteException, DadoNuloException, SenhaInvalidaException{
        if(professor==null){
            throw new DadoNuloException();
        }
         if(professor.getNome()==null){
            throw new NomeInvalidoException();
        }
        if(Professor.validarEmail(professor.getEmail())== false){
            throw new EmailInvalidoException();
        }
        if(professor.getLogin()==null){
            throw new DadoNuloException();
        }
        if(ValidacaoDosIDs.verificaLogin(professor.getLogin().getId())==false){
            throw new DadoInexistenteException();
        }
        if(professor.getLogin().getSenha().length()<8){
            throw new SenhaInvalidaException();
        }
        repProfessor.alterar(professor);
    }
    
    public Professor recuperarProfessor(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Professor> a = Fachada.getSingleton().recuperarTodosProfessor();
        for(int i=0; i< a.size();i++){
            if(codigo == a.get(i).getId()){
                return (Professor) repProfessor.recuperar(codigo);
            }
        }
        throw new DadoInexistenteException();     
    }
    public List<Professor> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Professor>) repProfessor.recuperarTodos();
    }
    
    public int recuperarUltimoID() throws ExceptionErroNoBanco{
        return repProfessor.recuperaUltimoID();
    }
}
