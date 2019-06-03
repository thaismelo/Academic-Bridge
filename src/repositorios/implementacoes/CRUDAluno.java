/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios.implementacoes;

import repositorios.implementacoes.banco.RepositorioAluno;
import exceptions.banco.ExceptionErroNoBanco;
import irepositorios.interfaces.RepositorioGenerico;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import java.util.List;
import fachada.Fachada;
import entidades.Aluno;
import entidades.Monitor;

/**
 *
 * @author thais
 */
public class CRUDAluno {

    private RepositorioGenerico rep;

    public CRUDAluno() {
        rep = new RepositorioAluno();
    }
    
    public void cadastrarAluno(Aluno t) throws ExceptionErroNoBanco, NomeInvalidoException, EmailInvalidoException, DadoNuloException, DadoInexistenteException {
        if (t.getNome() == null) {
            throw new NomeInvalidoException();
        }
        if (Aluno.validarEmail(t.getEmail()) == false) {
            throw new EmailInvalidoException();
        }
        if(t.getMonitor() == null){
            throw new DadoNuloException();
        }
        if(ValidacaoDosIDs.verificaMonitor(t.getMonitor().getId())==false){
            throw new DadoInexistenteException();
        }
        rep.inserir(t);
    }

    public void removerAluno(Aluno t) throws ExceptionErroNoBanco, DadoInexistenteException {
        if(t==null){
            throw new DadoInexistenteException();
        }
        rep.excluir(t);
        
    }

    public void alterarAluno(Aluno t) throws ExceptionErroNoBanco, DadoInexistenteException, EmailInvalidoException, NomeInvalidoException, DadoNuloException {
        if(t == null){
            throw new DadoNuloException();
        }
        if (t.getNome() == null) {
            throw new NomeInvalidoException();
        }
        if (Aluno.validarEmail(t.getEmail()) == false) {
            throw new EmailInvalidoException();
        }
        if(t.getMonitor() == null){
            throw new DadoNuloException();
        }
        if(ValidacaoDosIDs.verificaMonitor(t.getMonitor().getId())==false){
            throw new DadoInexistenteException();
        }
        rep.alterar(t);
    }

    public Aluno recuperarAluno(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException {
        List<Aluno> a = Fachada.getSingleton().recuperarTodosAluno();
        for(int i=0; i< a.size();i++){
            if(codigo == a.get(i).getId()){
                return (Aluno) rep.recuperar(codigo);
            }
        }
        throw new DadoInexistenteException();
    }
    public int recuperarUltimoId() throws ExceptionErroNoBanco{
        return rep.recuperaUltimoID();
    }
    public List<Aluno> recuperarTodos() throws ExceptionErroNoBanco {
        return (List<Aluno>) rep.recuperarTodos();
    }

}
