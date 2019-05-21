/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.ExceptionErroNoBanco;
import dados.implementacoes.CRUDBacklogMonitor;
import dados.implementacoes.CRUDFrequencia;
import dados.implementacoes.CRUDLogin;
import dados.implementacoes.CRUDPlanejamento;
import dados.implementacoes.CRUDPrioridades;
import dados.implementacoes.CRUDProfessor;
import dados.implementacoes.CRUDTarefa;
import dados.implementacoes.CRUDAluno;
import dados.implementacoes.CRUDMonitor;
import exceptions.entidades.Login.LoginNuloOuExistenteException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import java.util.List;
import negocio.modelo.BacklogMonitor;
import negocio.modelo.Frequencia;
import negocio.modelo.Login;
import negocio.modelo.Planejamento;
import negocio.modelo.Prioridades;
import negocio.modelo.Professor;
import negocio.modelo.Tarefa;
import negocio.modelo.Aluno;
import negocio.modelo.Monitor;

/**
 *
 * @author thais
 */
public class Fachada {

    private CRUDLogin crudLogin;
    private CRUDProfessor crudProfessor;
    private CRUDPrioridades crudPrioridades;
    private CRUDTarefa crudTarefa;
    private CRUDPlanejamento crudPlanejamento;
    private CRUDAluno crudAluno;
    private CRUDFrequencia crudFrequencia;
    private CRUDBacklogMonitor crudBacklog;
    private CRUDMonitor crudMonitor;
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
        crudPlanejamento = new CRUDPlanejamento();
        crudAluno = new CRUDAluno();
        crudFrequencia = new CRUDFrequencia();
        crudBacklog = new CRUDBacklogMonitor();
        crudMonitor = new CRUDMonitor();
    }
    
    public void cadastrarLogin(Login login) throws ExceptionErroNoBanco, LoginNuloOuExistenteException, SenhaInvalidaException{
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
    
    public void cadastrarProfessor(Professor professor) throws ExceptionErroNoBanco, EmailInvalidoException, NomeInvalidoException{
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
    public List<Tarefa> recuperarTodosTarefa() throws ExceptionErroNoBanco{
        return this.crudTarefa.recuperarTodos();
    }
    public void cadastrarPlanejamento(Planejamento p ) throws ExceptionErroNoBanco{
        this.crudPlanejamento.cadastrarPlanejamento(p);
    }
    
    public void removerPlanejamento(Planejamento p) throws ExceptionErroNoBanco{
        this.crudPlanejamento.removerPlanejamento(p);
    }    
    public void alterarPlanejamento(Planejamento p) throws ExceptionErroNoBanco{
        this.crudPlanejamento.alterarPlanejamento(p);
    }
    
    public Planejamento recuperarPlanejamento(int codigo) throws ExceptionErroNoBanco{
        return this.crudPlanejamento.recuperarPlanejamento(codigo);
    }
    public List<Planejamento> recuperarTodosPlanejamento() throws ExceptionErroNoBanco{
        return this.crudPlanejamento.recuperarTodos();
    }  
    
    public void cadastrarAluno(Aluno t) throws ExceptionErroNoBanco{
        this.crudAluno.cadastrarAluno(t);
    }
    
    public void removerAluno(Aluno t) throws ExceptionErroNoBanco{
        this.crudAluno.removerAluno(t);
    }    
    public void alterarAluno(Aluno t) throws ExceptionErroNoBanco{
        this.crudAluno.alterarAluno(t);
    }
    
    public Aluno recuperarAluno(int codigo) throws ExceptionErroNoBanco{
        return this.crudAluno.recuperarAluno(codigo);
    }
    public List<Aluno> recuperarTodosAluno() throws ExceptionErroNoBanco{
        return this.crudAluno.recuperarTodos();
    }
    
     public void cadastrarFrequencia(Frequencia t) throws ExceptionErroNoBanco{
        this.crudFrequencia.cadastrarFrequencia(t);
    }
    
    public void removerFrequencia(Frequencia t) throws ExceptionErroNoBanco{
        this.crudFrequencia.removerFrequencia(t);
    }    
    public void alterarFrequencia(Frequencia t) throws ExceptionErroNoBanco{
        this.crudFrequencia.alterarFrequencia(t);
    }
    
    public Frequencia recuperarFrequencia(int codigo) throws ExceptionErroNoBanco{
        return this.crudFrequencia.recuperarFrequencia(codigo);
    }
    public List<Frequencia> recuperarTodosFrequencia() throws ExceptionErroNoBanco{
        return this.crudFrequencia.recuperarTodos();
    }
    
    public void cadastrarBacklogMonitor(BacklogMonitor t) throws ExceptionErroNoBanco{
        this.crudBacklog.cadastrarBacklogMonitor(t);
    }
    
    public void removerBacklogMonitor(BacklogMonitor t) throws ExceptionErroNoBanco{
        this.crudBacklog.removerBacklogMonitor(t);
    }    
    public void alterarBacklogMonitor(BacklogMonitor t) throws ExceptionErroNoBanco{
        this.crudBacklog.alterarBacklogMonitor(t);
    }
    
    public BacklogMonitor recuperarBacklogMonitor(int codigo) throws ExceptionErroNoBanco{
        return this.crudBacklog.recuperarBacklogMonitor(codigo);
    }
    public List<BacklogMonitor> recuperarTodosBacklogMonitor() throws ExceptionErroNoBanco{
        return this.crudBacklog.recuperarTodos();
    }
    
     public void cadastrarMonitor(Monitor t) throws ExceptionErroNoBanco, NomeInvalidoException, EmailInvalidoException{
        this.crudMonitor.cadastrarMonitor(t);
    }
    
    public void removerMonitor(Monitor t) throws ExceptionErroNoBanco{
        this.crudMonitor.removerMonitor(t);
    }    
    public void alterarMonitor(Monitor t) throws ExceptionErroNoBanco{
        this.crudMonitor.alterarMonitor(t);
    }
    
    public Monitor recuperarMonitor(int codigo) throws ExceptionErroNoBanco{
        return this.crudMonitor.recuperarMonitor(codigo);
    }
    public List<Monitor> recuperarTodosMonitor() throws ExceptionErroNoBanco{
        return this.crudMonitor.recuperarTodos();
    }
}
