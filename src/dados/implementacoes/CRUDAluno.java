/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import dados.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import java.util.List;
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
    
    public void cadastrarAluno(Aluno t) throws ExceptionErroNoBanco, NomeInvalidoException, EmailInvalidoException{
        if(t.getNome()==null){
            throw new NomeInvalidoException();
        }
        if(Aluno.validarEmail(t.getEmail())== false){
            throw new EmailInvalidoException();
        }
        rep.inserir(t);
    }
    
    public void removerAluno(Aluno t) throws ExceptionErroNoBanco{
        rep.excluir(t);
    }    
    public void alterarAluno(Aluno t) throws ExceptionErroNoBanco{
        rep.alterar(t);
    }
    
    public Aluno recuperarAluno(int codigo) throws ExceptionErroNoBanco{
        return (Aluno) rep.recuperar(codigo);
    }
    public List<Aluno> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Aluno>) rep.recuperarTodos();
    }
    
}
