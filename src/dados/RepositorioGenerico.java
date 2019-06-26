/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import exceptions.banco.DadoInexistenteException;
import exceptions.banco.ExceptionErroNoBanco;
import java.util.List;

/**
 *
 * @author thais
 */
public interface RepositorioGenerico<T>{
    public void inserir(T t) throws ExceptionErroNoBanco, DadoInexistenteException;

    public void excluir(T t) throws ExceptionErroNoBanco, DadoInexistenteException;

    public void alterar(T t) throws ExceptionErroNoBanco, DadoInexistenteException;

    public T recuperar(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException;

    public List<T> recuperarTodos() throws ExceptionErroNoBanco;
}
