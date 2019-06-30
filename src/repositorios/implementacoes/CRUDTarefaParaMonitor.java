/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios.implementacoes;

import repositorios.implementacoes.banco.RepositorioTarefaParaMonitor;
import exceptions.banco.ExceptionErroNoBanco;
import irepositorios.interfaces.RepositorioGenerico;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.entidades.Tarefa.ConteudoNuloException;
import exceptions.entidades.Tarefa.EstadoInvalidoException;
import java.util.List;
import fachada.Fachada;
import entidades.Tarefa;
import entidades.TarefaParaMonitor;
import entidades.ValidacaoData;
import exceptions.entidades.Frequencia.DataInvalidaException;

/**
 *
 * @author Guilherme
 */
public class CRUDTarefaParaMonitor {
    private RepositorioGenerico repTarefaParaMonitor;

    public CRUDTarefaParaMonitor() {
        repTarefaParaMonitor = new RepositorioTarefaParaMonitor();
    }
    
    public void cadastrarTarefaParaMonitor(TarefaParaMonitor tarefaDoMonitor) throws ExceptionErroNoBanco, ConteudoNuloException, EstadoInvalidoException, DadoNuloException, DadoInexistenteException, DataInvalidaException{
        if(tarefaDoMonitor.getTarefaParaMonitor()==null){
            throw new DadoNuloException();
        }
        if(tarefaDoMonitor.getData()== null){
            throw new DadoNuloException();
        }
        if(ValidacaoDosIDs.verificaTarefa(tarefaDoMonitor.getTarefaParaMonitor().getId())==false){
            throw new DadoInexistenteException();
        }
        if(new ValidacaoData().data(tarefaDoMonitor.getData()) == false){
            throw new DataInvalidaException();
        }
        repTarefaParaMonitor.inserir(tarefaDoMonitor);
    }
    
    public void removerTarefaParaMonitor(TarefaParaMonitor TarefaDoMonitor) throws ExceptionErroNoBanco, DadoNuloException{
            if(TarefaDoMonitor!= null){
                repTarefaParaMonitor.excluir(TarefaDoMonitor);
            }else{
                throw new DadoNuloException();
            }
    }    
    public void alterarTarefaParaMonitor(TarefaParaMonitor tarefaDoMonitor) throws ExceptionErroNoBanco, ConteudoNuloException, DadoNuloException, EstadoInvalidoException, DadoInexistenteException, DataInvalidaException{
            if(tarefaDoMonitor==null || tarefaDoMonitor.getData()==null || tarefaDoMonitor.getTarefaParaMonitor()==null){
                throw new DadoNuloException();
            }
            if(ValidacaoDosIDs.verificaTarefa(tarefaDoMonitor.getTarefaParaMonitor().getId())==false){
                throw new DadoInexistenteException();
            }
            if(new ValidacaoData().data(tarefaDoMonitor.getData()) == false){
                throw new DataInvalidaException();
            }
            repTarefaParaMonitor.alterar(tarefaDoMonitor);
    }
    
    public TarefaParaMonitor recuperarTarefaParaMonitor(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<TarefaParaMonitor> a = Fachada.getSingleton().recuperarTodosTarefaParaMonitor();
        for(int i=0; i< a.size();i++){
            if(codigo == a.get(i).getId()){
                return (TarefaParaMonitor) repTarefaParaMonitor.recuperar(codigo);
            }
        }
        throw new DadoInexistenteException();
    }
    public List<TarefaParaMonitor> recuperarTodosTarefaParaMonitor() throws ExceptionErroNoBanco{
        return (List<TarefaParaMonitor>) repTarefaParaMonitor.recuperarTodos();
    }
    
    public int recuperarUltimoIDTarefaParaMonitor() throws ExceptionErroNoBanco{
        return repTarefaParaMonitor.recuperaUltimoID();
    }
    
    public List<TarefaParaMonitor> recuperarTodosPorCodProf(int cod) throws ExceptionErroNoBanco{
        return (List<TarefaParaMonitor>) new RepositorioTarefaParaMonitor().recuperarTodosPorCodProf(cod);
    }

    public List<TarefaParaMonitor> recuperarTodosPorCodMonit(int cod) throws ExceptionErroNoBanco{
        return (List<TarefaParaMonitor>) new RepositorioTarefaParaMonitor().recuperarTodosPorCodMonit(cod);
    }
    
    public List<TarefaParaMonitor> recuperarTodosPorCodDisc(int cod) throws ExceptionErroNoBanco{
        return (List<TarefaParaMonitor>) new RepositorioTarefaParaMonitor().recuperarTodosPorCodDisc(cod);
    }
}
