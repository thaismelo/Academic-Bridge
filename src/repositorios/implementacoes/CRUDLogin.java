/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios.implementacoes;

import repositorios.implementacoes.banco.RepositorioLogin;
import exceptions.banco.ExceptionErroNoBanco;
import irepositorios.interfaces.RepositorioGenerico;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.entidades.Login.LoginExistenteException;
import exceptions.entidades.Login.LoginNuloException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import java.util.List;
import fachada.Fachada;
import entidades.Login;

/**
 *
 * @author thais
 */
public class CRUDLogin {
    private RepositorioGenerico repLogin;

    public CRUDLogin() {
        repLogin = new RepositorioLogin();
    }
    
    public boolean verificarLogin(Login l) throws ExceptionErroNoBanco{
        List<Login> lista = Fachada.getSingleton().recuperarTodosLogin();
        
        for(int i=0;i<lista.size();i++){
            if(l.getLogin().equals(lista.get(i).getLogin()) && l.getSenha().equals( lista.get(i).getSenha())
                    && l.getTipo()==lista.get(i).getTipo() ){
                return true;
            }
        }
        return false;
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
    public void cadastrarLogin(Login login) throws ExceptionErroNoBanco, SenhaInvalidaException, SenhaNulaException, DadoInexistenteException, LoginExistenteException, LoginNuloException{
        if(recuperaLogin(login)==true){
            throw new LoginExistenteException();
        }
        if(login.getLogin()==null){
            throw new LoginNuloException();
        }
        if(login.getSenha()==null){
            throw new SenhaNulaException();
        }
        if(login==null){
            throw new DadoInexistenteException();
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
    public void alterarLogin(Login login) throws ExceptionErroNoBanco, DadoInexistenteException, LoginExistenteException, SenhaNulaException, LoginNuloException{
        if(login==null){
            throw new DadoInexistenteException();
        }
        if(recuperaLogin(login)==true){
            throw new LoginExistenteException();
        }
        if(login.getLogin()==null){
            throw new LoginNuloException();
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
            }
        }
        throw new DadoInexistenteException();   
    }
    public List<Login> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Login>) repLogin.recuperarTodos();
    }
    
    public int recuperaUltimoId() throws ExceptionErroNoBanco{
        return repLogin.recuperaUltimoID();
    }
}
