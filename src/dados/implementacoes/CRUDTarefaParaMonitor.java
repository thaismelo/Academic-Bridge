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
import exceptions.entidades.Tarefa.ConteudoNuloException;
import exceptions.entidades.Tarefa.EstadoInvalidoException;
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Tarefa;
import negocio.modelo.TarefaParaMonitor;

/**
 *
 * @author Guilherme
 */
public class CRUDTarefaParaMonitor {
    private RepositorioGenerico repTarefaParaMonitor;

    public CRUDTarefaParaMonitor() {
        repTarefaParaMonitor = new RepositorioTarefaParaMonitor();
    }
    
    public void cadastrarTarefaParaMonitor(TarefaParaMonitor tarefaDoMonitor) throws ExceptionErroNoBanco, ConteudoNuloException, EstadoInvalidoException, DadoNuloException, DadoInexistenteException{
        if(tarefaDoMonitor.getTarefaParaMonitor()==null){
            throw new DadoNuloException();
        }
        if(tarefaDoMonitor.getData()== null){
            throw new DadoNuloException();
        }
        if(ValidacaoDosIDs.verificaTarefa(tarefaDoMonitor.getTarefaParaMonitor().getId())==false){
            throw new DadoInexistenteException();
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
    public void alterarTarefaParaMonitor(TarefaParaMonitor tarefaDoMonitor) throws ExceptionErroNoBanco, ConteudoNuloException, DadoNuloException, EstadoInvalidoException, DadoInexistenteException{
            if(tarefaDoMonitor==null || tarefaDoMonitor.getData()==null || tarefaDoMonitor.getTarefaParaMonitor()==null){
                throw new DadoNuloException();
            }
            if(ValidacaoDosIDs.verificaTarefa(tarefaDoMonitor.getTarefaParaMonitor().getId())==false){
            throw new DadoInexistenteException();
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

    public List<Tarefa> recuperarTodosPorCodMonit(int cod) throws ExceptionErroNoBanco{
        return (List<Tarefa>) new RepositorioTarefaParaMonitor().recuperarTodosPorCodMonit(cod);
    }
}
