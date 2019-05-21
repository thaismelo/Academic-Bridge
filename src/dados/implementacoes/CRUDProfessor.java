/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import exceptions.banco.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import exceptions.banco.DadoInexistenteException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import java.util.List;
import negocio.Fachada;
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
    
    public void cadastrarProfessor(Professor professor) throws ExceptionErroNoBanco, EmailInvalidoException, NomeInvalidoException{
        if(professor.getNome()==null){
            throw new NomeInvalidoException();
        }
        if(Professor.validarEmail(professor.getEmail())== false){
            throw new EmailInvalidoException();
        }
        repProfessor.inserir(professor); 
    }
    
    public void removerProfessor(Professor professor) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Professor> a = Fachada.getSingleton().recuperarTodosProfessor();
        for(int i=0; i< a.size();i++){
            if(professor.getId() == a.get(i).getId() || professor!=null){
                repProfessor.excluir(professor);
            }else{
                throw new DadoInexistenteException();
            }
        }
        
    }    
    public void alterarProfessor(Professor professor) throws ExceptionErroNoBanco{
        repProfessor.alterar(professor);
    }
    
    public Professor recuperarProfessor(int codigo) throws ExceptionErroNoBanco{
        return (Professor) repProfessor.recuperar(codigo);
    }
    public List<Professor> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Professor>) repProfessor.recuperarTodos();
    }
}
