/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import exceptions.banco.ExceptionErroNoBanco;
import repositorios.implementacoes.CRUDFrequencia;
import repositorios.implementacoes.CRUDLogin;
import repositorios.implementacoes.CRUDProfessor;
import repositorios.implementacoes.CRUDTarefa;
import repositorios.implementacoes.CRUDAluno;
import repositorios.implementacoes.CRUDMonitor;
import repositorios.implementacoes.CRUDRelatorioMonitoria;
import repositorios.implementacoes.CRUDTarefaParaMonitor;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.entidades.Disciplina.DisciplinaInexistenteException;
import exceptions.entidades.Frequencia.FrequenciaInvalidaException;
import exceptions.entidades.Login.LoginExistenteException;
import exceptions.entidades.Login.LoginNuloException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import exceptions.entidades.RelatorioMonitoria.ForaDoIntervaloException;
import exceptions.entidades.RelatorioMonitoria.ValorIncorretoException;
import exceptions.entidades.Tarefa.ConteudoNuloException;
import exceptions.entidades.Tarefa.EstadoInvalidoException;
import java.util.List;
import entidades.Frequencia;
import entidades.Login;
import entidades.Professor;
import entidades.Tarefa;
import entidades.Aluno;
import entidades.Disciplina;
import entidades.Monitor;
import entidades.RelatorioMonitoria;
import entidades.TarefaParaMonitor;
import repositorios.implementacoes.CRUDDisciplina;

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
    private CRUDTarefaParaMonitor crudTarefaParaMonitor;
    private CRUDRelatorioMonitoria crudRelatorioMonitoria;
    private CRUDDisciplina crudDisciplina;
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
        crudDisciplina = new CRUDDisciplina();
        crudMonitor = new CRUDMonitor();
        crudTarefaParaMonitor = new CRUDTarefaParaMonitor();
        crudRelatorioMonitoria = new CRUDRelatorioMonitoria();
    }
    
    public void cadastrarLogin(Login login) throws ExceptionErroNoBanco, LoginExistenteException, SenhaInvalidaException, SenhaNulaException, DadoInexistenteException, LoginNuloException{
        this.crudLogin.cadastrarLogin(login);
    }
    
    public void removerLogin(Login login) throws ExceptionErroNoBanco, DadoInexistenteException{
        this.crudLogin.removerLogin(login);
    }
    
    public void alterarLogin(Login login) throws ExceptionErroNoBanco, DadoInexistenteException, LoginExistenteException, SenhaNulaException, LoginNuloException, SenhaInvalidaException{
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
    public int verificarLogin(Login l) throws ExceptionErroNoBanco{
        return this.crudLogin.verificarLogin(l);
    }
    
    public boolean cadastrarProfessor(Professor professor) throws ExceptionErroNoBanco, EmailInvalidoException, NomeInvalidoException, DisciplinaInexistenteException, DadoNuloException, DadoInexistenteException, SenhaInvalidaException{
        return this.crudProfessor.cadastrarProfessor(professor);
    }
    
    public void removerProfessor(Professor professor) throws ExceptionErroNoBanco, DadoNuloException{
        this.crudProfessor.removerProfessor(professor);
    }    
    public void alterarProfessor(Professor professor) throws ExceptionErroNoBanco, DadoInexistenteException, NomeInvalidoException, EmailInvalidoException, DisciplinaInexistenteException, DadoNuloException, SenhaInvalidaException{
        this.crudProfessor.alterarProfessor(professor);
    }
    
    public Professor recuperarProfessor(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        return this.crudProfessor.recuperarProfessor(codigo);
    }
    
    public Professor recuperarProfessorLogin(Login l) throws ExceptionErroNoBanco, DadoInexistenteException{
        return this.crudProfessor.recuperarProfessorLogin(l);
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
    public List<Tarefa> recuperarTodosPorCriador(int codCriador,int tipCriador) throws ExceptionErroNoBanco{
        return this.crudTarefa.recuperarTodosPorCriador(codCriador,tipCriador);
    }
    public void cadastrarAluno(Aluno t) throws ExceptionErroNoBanco, NomeInvalidoException, EmailInvalidoException, DadoNuloException, DadoInexistenteException{
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
    
     public void cadastrarFrequencia(Frequencia t) throws ExceptionErroNoBanco, FrequenciaInvalidaException, DadoNuloException, DadoInexistenteException{
        this.crudFrequencia.cadastrarFrequencia(t);
    }
    
    public void removerFrequencia(Frequencia t) throws ExceptionErroNoBanco, DadoInexistenteException{
        this.crudFrequencia.removerFrequencia(t);
    }    
    public void alterarFrequencia(Frequencia t) throws ExceptionErroNoBanco, FrequenciaInvalidaException, DadoInexistenteException, DadoNuloException{
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
     public void cadastrarMonitor(Monitor t) throws ExceptionErroNoBanco, NomeInvalidoException, EmailInvalidoException,DadoInexistenteException, DadoNuloException{
        this.crudMonitor.cadastrarMonitor(t);
    }
    
    public void removerMonitor(Monitor t) throws ExceptionErroNoBanco, DadoInexistenteException{
        this.crudMonitor.removerMonitor(t);
    }    
    public void alterarMonitor(Monitor t) throws ExceptionErroNoBanco, DadoInexistenteException, NomeInvalidoException, EmailInvalidoException, DadoNuloException, SenhaInvalidaException{
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
    
    public List<Monitor> recuperarTodosMonitorPorProf(Professor prof) throws ExceptionErroNoBanco{
        return this.crudMonitor.recuperarTodosMonitorPorProf(prof);
    }
    
    public Monitor recuperarMonitorLogin(Login l) throws ExceptionErroNoBanco, DadoInexistenteException{
        return this.crudMonitor.recuperarMonitorLogin(l);
    }
    public void cadastrarTarefaParaMonitor(TarefaParaMonitor TarefaDoMonitor) throws ExceptionErroNoBanco, ConteudoNuloException, EstadoInvalidoException, DadoNuloException, DadoInexistenteException{
        crudTarefaParaMonitor.cadastrarTarefaParaMonitor(TarefaDoMonitor);
    }
    
    public void removerTarefaParaMonitor(TarefaParaMonitor TarefaDoMonitor) throws ExceptionErroNoBanco, DadoNuloException{
            crudTarefaParaMonitor.removerTarefaParaMonitor(TarefaDoMonitor);
            
    }    
    public void alterarTarefaParaMonitor(TarefaParaMonitor TarefaDoMonitor) throws ExceptionErroNoBanco, ConteudoNuloException, DadoNuloException, EstadoInvalidoException, DadoInexistenteException{
            crudTarefaParaMonitor.alterarTarefaParaMonitor(TarefaDoMonitor);
    }
    
    public TarefaParaMonitor recuperarTarefaParaMonitor(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        return crudTarefaParaMonitor.recuperarTarefaParaMonitor(codigo);
    }
    public List<TarefaParaMonitor> recuperarTodosTarefaParaMonitor() throws ExceptionErroNoBanco{
        return crudTarefaParaMonitor.recuperarTodosTarefaParaMonitor();
    }
    
    public int recuperarUltimoIDTarefaParaMonitor() throws ExceptionErroNoBanco{
        return crudTarefaParaMonitor.recuperarUltimoIDTarefaParaMonitor();
    }
   
    public List<TarefaParaMonitor> recuperarTodosPorCodProfTarefaParaMonitor(int cod) throws ExceptionErroNoBanco{
        return crudTarefaParaMonitor.recuperarTodosPorCodProf(cod);
    }
    public List<Tarefa> recuperarTodosPorCodMonitTarefaParaMonitor(int cod) throws ExceptionErroNoBanco{
        return crudTarefaParaMonitor.recuperarTodosPorCodMonit(cod);
    }
    public void cadastrarRelatorioMonitoria(RelatorioMonitoria r) throws ExceptionErroNoBanco, DadoNuloException, ForaDoIntervaloException, ValorIncorretoException, DadoInexistenteException{
        this.crudRelatorioMonitoria.cadastrarRelatorioMonitoria(r);
    }
    
     public void removerRelatorioMonitoria(RelatorioMonitoria r) throws ExceptionErroNoBanco, DadoInexistenteException{
        this.crudRelatorioMonitoria.removerRelatorioMonitoria(r);
    }
     
     public void alterarRelatorioMonitoria(RelatorioMonitoria r) throws ExceptionErroNoBanco, DadoInexistenteException, ForaDoIntervaloException, ValorIncorretoException, DadoNuloException{
        this.crudRelatorioMonitoria.alterarRelatorioMonitoria(r);
    }
     
     public RelatorioMonitoria recuperarRelatorioMonitoria(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
            return (RelatorioMonitoria) this.crudRelatorioMonitoria.recuperarRelatorioMonitoria(codigo);
    }
     
     public int recuperarUltimoIDRelatorioMonitoria() throws ExceptionErroNoBanco{
        return this.crudRelatorioMonitoria.recuperarUltimoIDRelatorioMonitoria();
    }
     
    public List<RelatorioMonitoria> recuperarTodosRelatorioMonitoria() throws ExceptionErroNoBanco{
            return (List<RelatorioMonitoria>) this.crudRelatorioMonitoria.recuperarTodosRelatorioMonitoria();
    }
    
    public void cadastrarDisciplina(Disciplina d) throws ExceptionErroNoBanco, DadoNuloException{
        this.crudDisciplina.cadastrarDisciplina(d);
    }
    
    public void removerDisciplina (Disciplina t) throws ExceptionErroNoBanco, DadoInexistenteException{
        this.crudDisciplina .removerDisciplina (t);
    }    
    public void alterarDisciplina (Disciplina  t) throws ExceptionErroNoBanco, DadoInexistenteException, NomeInvalidoException, EmailInvalidoException, DadoNuloException{
        this.crudDisciplina .alterarDisciplina (t);
    }
    
    public Disciplina  recuperarDisciplina(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        return this.crudDisciplina.recuperarDisciplina (codigo);
    }
    
    public int recuperarUltimoIdDisciplina() throws ExceptionErroNoBanco{
        return this.crudDisciplina.recuperarUltimoId();
    }
    
    public List<Disciplina> recuperarTodosDisciplina() throws ExceptionErroNoBanco{
        return this.crudDisciplina.recuperarTodos();
    }
    
    public List<Disciplina> recuperarDisciplinasPorProf(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        return this.crudDisciplina.recuperarDisciplinasPorProf(codigo);
    }
}
