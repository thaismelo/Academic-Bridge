/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irepositorios.interfaces;

import exceptions.banco.ExceptionErroNoBanco;
import java.util.List;

/**
 *
 * @author thais
 */
public interface RepositorioGenerico<T>{
    public void inserir(T t) throws ExceptionErroNoBanco;

    public void excluir(T t) throws ExceptionErroNoBanco;

    public void alterar(T t) throws ExceptionErroNoBanco;

    public T recuperar(int codigo) throws ExceptionErroNoBanco;
    
    public int recuperaUltimoID() throws ExceptionErroNoBanco;
    
    public List<T> recuperarTodos() throws ExceptionErroNoBanco;
}
