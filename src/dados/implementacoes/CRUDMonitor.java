/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import exceptions.banco.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import exceptions.entidades.Tarefa.ListaTarefaVaziaException;
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Monitor;

/**
 *
 * @author thais
 */
public class CRUDMonitor {
    private RepositorioGenerico rep;

    public CRUDMonitor() {
        rep = new RepositorioMonitor();
    }
    
    public void cadastrarMonitor(Monitor t) throws ExceptionErroNoBanco, NomeInvalidoException, EmailInvalidoException, DadoInexistenteException, DadoNuloException{
        if(t.getNome()==null){
            throw new NomeInvalidoException();
        }
        if(Monitor.validarEmail(t.getEmail())== false){
            throw new EmailInvalidoException();
        }
        if(t.getProf()==null){
            throw new DadoNuloException();
        }
        if(t.getLogin()==null){
            throw new DadoNuloException();
        }
        if(ValidacaoDosIDs.verificaLogin(t.getLogin().getId())==false){
            throw new DadoInexistenteException();
        }
        if(ValidacaoDosIDs.verificaProfessor(t.getProf().getId())==false){
            throw new DadoInexistenteException();
        }
        rep.inserir(t);
    }
    
    public void removerMonitor(Monitor t) throws ExceptionErroNoBanco, DadoInexistenteException{
            if(t!=null){
                rep.excluir(t);
            }else{
                throw new DadoInexistenteException();
            }   
    }    
    public void alterarMonitor(Monitor t) throws ExceptionErroNoBanco, DadoInexistenteException, NomeInvalidoException, EmailInvalidoException,DadoNuloException{
        if(t==null){
            throw new DadoNuloException();
        }
        if(t.getNome()==null){
            throw new NomeInvalidoException();
        }
        if(Monitor.validarEmail(t.getEmail())== false){
            throw new EmailInvalidoException();
        }
       
        if(t.getProf()==null){
            throw new DadoNuloException();
        }
        if(t.getLogin()==null){
            throw new DadoNuloException();
        }
        if(ValidacaoDosIDs.verificaLogin(t.getLogin().getId())==false){
            throw new DadoInexistenteException();
        }
        if(ValidacaoDosIDs.verificaProfessor(t.getProf().getId())==false){
            throw new DadoInexistenteException();
        }
        rep.alterar(t);
    }
    
    public Monitor recuperarMonitor(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Monitor> a = Fachada.getSingleton().recuperarTodosMonitor();
        for(int i=0; i< a.size();i++){
            if(codigo == a.get(i).getId()){
                return (Monitor) rep.recuperar(codigo);
            }
        }
        throw new DadoInexistenteException();     
    }
    public List<Monitor> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Monitor>) rep.recuperarTodos();
    }
    
    public int recuperaUltimoId() throws ExceptionErroNoBanco{
        return rep.recuperaUltimoID();
    }
    
}
