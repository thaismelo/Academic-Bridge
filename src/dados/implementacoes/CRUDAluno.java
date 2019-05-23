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
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Aluno;

/**
 *
 * @author thais
 */
public class CRUDAluno {

    private RepositorioGenerico rep;

    public CRUDAluno() {
        rep = new RepositorioAluno();
    }

    public void cadastrarAluno(Aluno t) throws ExceptionErroNoBanco, NomeInvalidoException, EmailInvalidoException, DadoNuloException {
        if (t.getNome() == null) {
            throw new NomeInvalidoException();
        }
        if (Aluno.validarEmail(t.getEmail()) == false) {
            throw new EmailInvalidoException();
        }
        if(t.getMonitor() == null){
            throw new DadoNuloException();
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
            throw new DadoInexistenteException();
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
        
        rep.alterar(t);
    }

    public Aluno recuperarAluno(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException {
        List<Aluno> a = Fachada.getSingleton().recuperarTodosAluno();
        for(int i=0; i< a.size();i++){
            if(codigo == a.get(i).getId()){
                return (Aluno) rep.recuperar(codigo);
            }else{
                throw new DadoInexistenteException();
            }
        }
        return null;
    }
    public int recuperarUltimoId() throws ExceptionErroNoBanco{
        return rep.recuperaUltimoID();
    }
    public List<Aluno> recuperarTodos() throws ExceptionErroNoBanco {
        return (List<Aluno>) rep.recuperarTodos();
    }

}
