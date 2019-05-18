/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.ExceptionErroNoBanco;
import dados.implementacoes.CRUDLogin;
import dados.implementacoes.CRUDProfessor;
import java.util.List;
import negocio.modelo.Login;
import negocio.modelo.Professor;

/**
 *
 * @author thais
 */
public class Fachada {

    private CRUDLogin crudLogin;
    private CRUDProfessor crudProfessor;

    private static Fachada singleton = null;

    public static Fachada getSingleton() {
        if (singleton == null) {
            singleton = new Fachada();
        }
        return singleton;
    }
    
    private Fachada(){
        crudLogin = new CRUDLogin();
        crudProfessor = new CRUDProfessor();
    }
    
    public void cadastrarLogin(Login login) throws ExceptionErroNoBanco{
        this.crudLogin.cadastrarLogin(login);
    }
    
    public void removerLogin(Login login) throws ExceptionErroNoBanco{
        this.crudLogin.removerLogin(login);
    }
    
    public void alterarLogin(Login login) throws ExceptionErroNoBanco{
        this.crudLogin.alterarLogin(login);
    }
    
    public Login recuperarLogin(int codigo) throws ExceptionErroNoBanco{
        return this.crudLogin.recuperarLogin(codigo);
    }
    public List<Login> recuperarTodosLogin() throws ExceptionErroNoBanco{
        return this.crudLogin.recuperarTodos();
    }
    
    public void cadastrarProfessor(Professor professor) throws ExceptionErroNoBanco{
        this.crudProfessor.cadastrarProfessor(professor);
    }
    
    public void removerProfessor(Professor professor) throws ExceptionErroNoBanco{
        this.crudProfessor.removerProfessor(professor);
    }    
    public void alterarProfessor(Professor professor) throws ExceptionErroNoBanco{
        this.crudProfessor.alterarProfessor(professor);
    }
    
    public Professor recuperarProfessor(int codigo) throws ExceptionErroNoBanco{
        return this.crudProfessor.recuperarProfessor(codigo);
    }
    public List<Professor> recuperarTodos() throws ExceptionErroNoBanco{
        return this.crudProfessor.recuperarTodos();
    }
       
}
