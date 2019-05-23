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
    public boolean recuperaLogin(Login l) throws ExceptionErroNoBanco{
        List<Login> lista = Fachada.getSingleton().recuperarTodosLogin();
        
        for(int i=0;i<lista.size();i++){
            if(l.getLogin().equals(lista.get(i).getLogin())){
                return true;
            }
        }
        return false;
    }
    public void cadastrarLogin(Login login) throws ExceptionErroNoBanco, LoginNuloOuExistenteException,SenhaInvalidaException, SenhaNulaException{
        if(recuperaLogin(login)==false){
            throw new LoginNuloOuExistenteException();
        }
        if(login.getSenha()==null){
            throw new SenhaNulaException();
        }
        repLogin.inserir(login);
    }
    
    public void removerLogin(Login login) throws ExceptionErroNoBanco, DadoInexistenteException{
            if(login!=null){
                repLogin.excluir(login);
            }else{
                throw new DadoInexistenteException();
            }
        
    }    
    public void alterarLogin(Login login) throws ExceptionErroNoBanco, DadoInexistenteException, LoginNuloOuExistenteException, SenhaNulaException{
        if(login==null){
            throw new DadoInexistenteException();
        }
         if(recuperaLogin(login)==false){
            throw new LoginNuloOuExistenteException();
        }
        if(login.getSenha()==null){
            throw new SenhaNulaException();
        }
        
        repLogin.alterar(login);
    }
    
    public Login recuperarLogin(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Login> a = Fachada.getSingleton().recuperarTodosLogin();
        for(int i=0; i< a.size();i++){
            if(codigo == a.get(i).getId()){
                return (Login) repLogin.recuperar(codigo);
            }else{
                throw new DadoInexistenteException();
            }
        }
        return null;    
    }
    public List<Login> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Login>) repLogin.recuperarTodos();
    }
    
    public int recuperaUltimoId() throws ExceptionErroNoBanco{
        return repLogin.recuperaUltimoID();
    }
}
