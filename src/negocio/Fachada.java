/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import exceptions.banco.ExceptionErroNoBanco;
import dados.implementacoes.CRUDFrequencia;
import dados.implementacoes.CRUDLogin;
import dados.implementacoes.CRUDProfessor;
import dados.implementacoes.CRUDTarefa;
import dados.implementacoes.CRUDAluno;
import dados.implementacoes.CRUDMonitor;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.entidades.Disciplina.DisciplinaInexistenteException;
import exceptions.entidades.Frequencia.FrequenciaInvalidaException;
import exceptions.entidades.Login.LoginNuloOuExistenteException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import exceptions.entidades.Tarefa.ConteudoNuloException;
import exceptions.entidades.Tarefa.EstadoInvalidoException;
import exceptions.entidades.Tarefa.ListaTarefaVaziaException;
import java.util.List;
import negocio.modelo.Frequencia;
import negocio.modelo.Login;
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
    private CRUDTarefa crudTarefa;
    private CRUDAluno crudAluno;
    private CRUDFrequencia crudFrequencia;
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
        crudTarefa = new CRUDTarefa();
        crudAluno = new CRUDAluno();
        crudFrequencia = new CRUDFrequencia();
        crudMonitor = new CRUDMonitor();
    }
    
    public void cadastrarLogin(Login login) throws ExceptionErroNoBanco, LoginNuloOuExistenteException, SenhaInvalidaException, SenhaNulaException{
        this.crudLogin.cadastrarLogin(login);
    }
    
    public void removerLogin(Login login) throws ExceptionErroNoBanco, DadoInexistenteException{
        this.crudLogin.removerLogin(login);
    }
    
    public void alterarLogin(Login login) throws ExceptionErroNoBanco, DadoInexistenteException, LoginNuloOuExistenteException, SenhaNulaException{
        this.crudLogin.alterarLogin(login);
    }
    
    public Login recuperarLogin(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        return this.crudLogin.recuperarLogin(codigo);
    }
    
    public int recuperaUltimoIdLogin() throws ExceptionErroNoBanco{
        return this.crudLogin.recuperaUltimoId();
    }
    public List<Login> recuperarTodosLogin() throws ExceptionErroNoBanco{
        return this.crudLogin.recuperarTodos();
    }
    
    public void cadastrarProfessor(Professor professor) throws ExceptionErroNoBanco, EmailInvalidoException, NomeInvalidoException, DadoInexistenteException, ListaTarefaVaziaException, DisciplinaInexistenteException{
        this.crudProfessor.cadastrarProfessor(professor);
    }
    
    public void removerProfessor(Professor professor) throws ExceptionErroNoBanco, DadoInexistenteException{
        this.crudProfessor.removerProfessor(professor);
    }    
    public void alterarProfessor(Professor professor) throws ExceptionErroNoBanco, DadoInexistenteException, NomeInvalidoException, EmailInvalidoException, ListaTarefaVaziaException, DisciplinaInexistenteException{
        this.crudProfessor.alterarProfessor(professor);
    }
    
    public Professor recuperarProfessor(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        return this.crudProfessor.recuperarProfessor(codigo);
    }
    
    public int recuperarUltimoIdProfessor() throws ExceptionErroNoBanco{
        return this.crudProfessor.recuperarUltimoID();
    }
    public List<Professor> recuperarTodosProfessor() throws ExceptionErroNoBanco{
        return this.crudProfessor.recuperarTodos();
    }
    
     public void cadastrarTarefa(Tarefa tarefa) throws ExceptionErroNoBanco, ConteudoNuloException, EstadoInvalidoException{
        this.crudTarefa.cadastrarTarefa(tarefa);
    }
    
    public void removerTarefa(Tarefa tarefa) throws ExceptionErroNoBanco,DadoNuloException{
        this.crudTarefa.removerTarefa(tarefa);
    }    
    public void alterarTarefa(Tarefa tarefa) throws ExceptionErroNoBanco, ConteudoNuloException, DadoNuloException, EstadoInvalidoException{
        this.crudTarefa.alterarTarefa(tarefa);
    }
    
    public Tarefa recuperarTarefa(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        return this.crudTarefa.recuperarTarefa(codigo);
    }
    
    public int recuperarUltimoIDTarefa() throws ExceptionErroNoBanco{
        return this.crudTarefa.recuperarUltimoID();
    }
    public List<Tarefa> recuperarTodosTarefa() throws ExceptionErroNoBanco{
        return this.crudTarefa.recuperarTodos();
    }
    public void cadastrarAluno(Aluno t) throws ExceptionErroNoBanco, NomeInvalidoException, EmailInvalidoException, DadoNuloException{
        this.crudAluno.cadastrarAluno(t);
    }
    
    public void removerAluno(Aluno t) throws ExceptionErroNoBanco, DadoInexistenteException{
        this.crudAluno.removerAluno(t);
    }    
    public void alterarAluno(Aluno t) throws ExceptionErroNoBanco, DadoInexistenteException, EmailInvalidoException, NomeInvalidoException, DadoNuloException{
        this.crudAluno.alterarAluno(t);
    }
    
    public Aluno recuperarAluno(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        return this.crudAluno.recuperarAluno(codigo);
    }
    public int recuperarUltimoIdAluno() throws ExceptionErroNoBanco{
        return this.crudAluno.recuperarUltimoId();
    }
    public List<Aluno> recuperarTodosAluno() throws ExceptionErroNoBanco{
        return this.crudAluno.recuperarTodos();
    }
    
     public void cadastrarFrequencia(Frequencia t) throws ExceptionErroNoBanco, FrequenciaInvalidaException, DadoInexistenteException{
        this.crudFrequencia.cadastrarFrequencia(t);
    }
    
    public void removerFrequencia(Frequencia t) throws ExceptionErroNoBanco, DadoInexistenteException{
        this.crudFrequencia.removerFrequencia(t);
    }    
    public void alterarFrequencia(Frequencia t) throws ExceptionErroNoBanco, FrequenciaInvalidaException, DadoInexistenteException{
        this.crudFrequencia.alterarFrequencia(t);
    }
    
    public Frequencia recuperarFrequencia(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        return this.crudFrequencia.recuperarFrequencia(codigo);
    }
    public int recuperarUltimoIdFrequencia() throws ExceptionErroNoBanco{
        return this.crudFrequencia.recuperarUltimoId();
    }
    public List<Frequencia> recuperarTodosFrequencia() throws ExceptionErroNoBanco{
        return this.crudFrequencia.recuperarTodos();
    } 
     public void cadastrarMonitor(Monitor t) throws ExceptionErroNoBanco, NomeInvalidoException, EmailInvalidoException, ListaTarefaVaziaException, DadoInexistenteException{
        this.crudMonitor.cadastrarMonitor(t);
    }
    
    public void removerMonitor(Monitor t) throws ExceptionErroNoBanco, DadoInexistenteException{
        this.crudMonitor.removerMonitor(t);
    }    
    public void alterarMonitor(Monitor t) throws ExceptionErroNoBanco, DadoInexistenteException, NomeInvalidoException, EmailInvalidoException, ListaTarefaVaziaException{
        this.crudMonitor.alterarMonitor(t);
    }
    
    public Monitor recuperarMonitor(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        return this.crudMonitor.recuperarMonitor(codigo);
    }
    
    public int recuperarUltimoIdMonitor() throws ExceptionErroNoBanco{
        return this.crudMonitor.recuperaUltimoId();
    }
    
    public List<Monitor> recuperarTodosMonitor() throws ExceptionErroNoBanco{
        return this.crudMonitor.recuperarTodos();
    }
}
