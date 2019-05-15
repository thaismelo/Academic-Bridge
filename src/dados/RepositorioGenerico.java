/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;

/**
 *
 * @author thais
 */
public interface RepositorioGenerico<T>{
    public void inserir(T t) throws ExceptionErroNoBanco;

    public void excluir(T t);

    public void alterar(T t);

    public T recuperar(int codigo);

    public List<T> recuperarTodos();
}
