/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import exceptions.banco.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import exceptions.banco.DadoInexistenteException;
import exceptions.entidades.Login.LoginNuloOuExistenteException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Login;

/**
 *
 * @author thais
 */
public class CRUDLogin {
    private RepositorioGenerico repLogin;

    public CRUDLogin() {
        repLogin = new RepositorioLogin();
    }
    
    public void cadastrarLogin(Login login) throws ExceptionErroNoBanco, LoginNuloOuExistenteException,SenhaInvalidaException, SenhaNulaException{
        if(login.recuperaLogin(login)==false){
            throw new LoginNuloOuExistenteException();
        }
        if(login.getSenha()==null){
            throw new SenhaNulaException();
        }
        repLogin.inserir(login);
    }
    
    public void removerLogin(Login login) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Login> a = Fachada.getSingleton().recuperarTodosLogin();
        for(int i=0; i< a.size();i++){
            if(login.getId() == a.get(i).getId()){
                repLogin.excluir(login);
            }else{
                throw new DadoInexistenteException();
            }
        }
    }    
    public void alterarLogin(Login login) throws ExceptionErroNoBanco{
        repLogin.alterar(login);
    }
    
    public Login recuperarLogin(int codigo) throws ExceptionErroNoBanco{
        return (Login) repLogin.recuperar(codigo);
    }
    public List<Login> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Login>) repLogin.recuperarTodos();
    }
}
