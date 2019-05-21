/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import dados.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import exceptions.entidades.Login.LoginNuloOuExistenteException;
import exceptions.entidades.Login.SenhaInvalidaException;
import java.util.List;
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
    
    public void cadastrarLogin(Login login) throws ExceptionErroNoBanco, LoginNuloOuExistenteException,SenhaInvalidaException{
        if(login.recuperaLogin(login)==false){
            throw new LoginNuloOuExistenteException();
        }
        repLogin.inserir(login);
    }
    
    public void removerLogin(Login login) throws ExceptionErroNoBanco{
        repLogin.excluir(login);
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
