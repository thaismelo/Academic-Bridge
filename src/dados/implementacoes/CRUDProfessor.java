/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import exceptions.banco.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import exceptions.banco.DadoInexistenteException;
import exceptions.entidades.Disciplina.DisciplinaInexistenteException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import exceptions.entidades.Tarefa.ListaTarefaVaziaException;
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Disciplina;
import negocio.modelo.Pessoa;
import negocio.modelo.Professor;
/**
 *
 * @author Guilherme
 */
public class CRUDProfessor {
    private RepositorioGenerico repProfessor;

    public CRUDProfessor() {
        repProfessor = new RepositorioProfessor();
    }
    
    public boolean verificaDisciplina(int idDisc) throws ExceptionErroNoBanco{
        RepositorioDisciplina disc = new RepositorioDisciplina();
        List<Disciplina> ldisc = disc.recuperarTodos();
        for(int i = 0; i< ldisc.size();i++){
            if(idDisc == ldisc.get(i).getId()){
                return true;
            }
        }
        return false;
    }
    
    public void cadastrarProfessor(Professor professor) throws ExceptionErroNoBanco, EmailInvalidoException, NomeInvalidoException, DadoInexistenteException, ListaTarefaVaziaException, DisciplinaInexistenteException{
        if(professor.getNome()==null){
            throw new NomeInvalidoException();
        }
        if(Professor.validarEmail(professor.getEmail())== false){
            throw new EmailInvalidoException();
        }
        if(professor.getLogin()==null){
            throw new DadoInexistenteException();
        }
        if(professor.getTarefas()==null || professor.getTarefas().isEmpty()){
            throw new ListaTarefaVaziaException();
        }
        
        if(verificaDisciplina(professor.getIdDisc())==false){
            throw new DisciplinaInexistenteException();
        }

        repProfessor.inserir(professor); 
    }
    
    public void removerProfessor(Professor professor) throws ExceptionErroNoBanco, DadoInexistenteException{
            if(professor!=null){
                repProfessor.excluir(professor);
            }else{
                throw new DadoInexistenteException();
            }
        
    }    
    public void alterarProfessor(Professor professor) throws ExceptionErroNoBanco, DadoInexistenteException, NomeInvalidoException, EmailInvalidoException, ListaTarefaVaziaException, DisciplinaInexistenteException{
        if(professor==null){
            throw new DadoInexistenteException();
        }
         if(professor.getNome()==null){
            throw new NomeInvalidoException();
        }
        if(Professor.validarEmail(professor.getEmail())== false){
            throw new EmailInvalidoException();
        }
        if(professor.getLogin()==null){
            throw new DadoInexistenteException();
        }
        if(professor.getTarefas()==null || professor.getTarefas().isEmpty()){
            throw new ListaTarefaVaziaException();
        }
        
        if(verificaDisciplina(professor.getIdDisc())==false){
            throw new DisciplinaInexistenteException();
        }

        repProfessor.alterar(professor);
    }
    
    public Professor recuperarProfessor(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Professor> a = Fachada.getSingleton().recuperarTodosProfessor();
        for(int i=0; i< a.size();i++){
            if(codigo == a.get(i).getId()){
                return (Professor) repProfessor.recuperar(codigo);
            }else{
                throw new DadoInexistenteException();
            }
        }
        return null;     
    }
    public List<Professor> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Professor>) repProfessor.recuperarTodos();
    }
    
    public int recuperarUltimoID() throws ExceptionErroNoBanco{
        return repProfessor.recuperaUltimoID();
    }
}
