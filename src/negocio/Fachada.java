/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.ExceptionErroNoBanco;
import dados.implementacoes.CRUDLogin;
import dados.implementacoes.CRUDPrioridades;
import dados.implementacoes.CRUDProfessor;
import dados.implementacoes.CRUDTarefa;
import java.util.List;
import negocio.modelo.Login;
import negocio.modelo.Prioridades;
import negocio.modelo.Professor;
import negocio.modelo.Tarefa;

/**
 *
 * @author thais
 */
public class Fachada {

    private CRUDLogin crudLogin;
    private CRUDProfessor crudProfessor;
    private CRUDPrioridades crudPrioridades;
    private CRUDTarefa crudTarefa;

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
        crudPrioridades = new CRUDPrioridades();
        crudTarefa = new CRUDTarefa();
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
    public List<Professor> recuperarTodosProfessor() throws ExceptionErroNoBanco{
        return this.crudProfessor.recuperarTodos();
    }
    
    public void cadastrarPrioridades(Prioridades p) throws ExceptionErroNoBanco{
        this.crudPrioridades.cadastrarPrioridade(p);
    }
    
    public void removerPrioridades(Prioridades p) throws ExceptionErroNoBanco{
        this.crudPrioridades.removerPrioridades(p);
    }    
    public void alterarPrioridades(Prioridades p) throws ExceptionErroNoBanco{
        this.crudPrioridades.alterarPrioridades(p);
    }
    
    public Prioridades recuperarPrioridades(int codigo) throws ExceptionErroNoBanco{
        return this.crudPrioridades.recuperarPrioridades(codigo);
    }
    public List<Prioridades> recuperarTodosPrioridades() throws ExceptionErroNoBanco{
        return this.crudPrioridades.recuperarTodos();
    }
     public void cadastrarTarefa(Tarefa tarefa) throws ExceptionErroNoBanco{
        this.crudTarefa.cadastrarTarefa(tarefa);
    }
    
    public void removerTarefa(Tarefa tarefa) throws ExceptionErroNoBanco{
        this.crudTarefa.removerTarefa(tarefa);
    }    
    public void alterarTarefa(Tarefa tarefa) throws ExceptionErroNoBanco{
        this.crudTarefa.alterarTarefa(tarefa);
    }
    
    public Tarefa recuperarTarefa(int codigo) throws ExceptionErroNoBanco{
        return this.crudTarefa.recuperarTarefa(codigo);
    }
    public List<Tarefa> recuperarTodos() throws ExceptionErroNoBanco{
        return this.crudTarefa.recuperarTodos();
    }
       
}
