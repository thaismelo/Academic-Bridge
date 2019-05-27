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
public class CRUDTarefaDoMonitor {
    private RepositorioGenerico repTarefaParaMonitor;

    public CRUDTarefaDoMonitor() {
        repTarefaParaMonitor = new RepositorioTarefaParaMonitor();
    }
    
    public void cadastrarTarefaDoMonitor(TarefaParaMonitor tarefaParaMonitor) throws ExceptionErroNoBanco, ConteudoNuloException, EstadoInvalidoException, DadoNuloException, DadoInexistenteException{
        if(tarefaParaMonitor.getTarefaParaMonitor()==null){
            throw new DadoNuloException();
        }
        if(tarefaParaMonitor.getData()== null){
            throw new DadoNuloException();
        }
        if(ValidacaoDosIDs.verificaTarefa(tarefaParaMonitor.getTarefaParaMonitor().getId())==false){
            throw new DadoInexistenteException();
        }
        repTarefaParaMonitor.inserir(tarefaParaMonitor);
    }
    
    public void removerTarefaParaMonitor(TarefaParaMonitor TarefaParaMonitor) throws ExceptionErroNoBanco, DadoNuloException{
            if(TarefaParaMonitor!= null){
                repTarefaParaMonitor.excluir(TarefaParaMonitor);
            }else{
                throw new DadoNuloException();
            }
    }    
    public void alterarTarefaParaMonitor(TarefaParaMonitor tarefaParaMonitor) throws ExceptionErroNoBanco, ConteudoNuloException, DadoNuloException, EstadoInvalidoException, DadoInexistenteException{
            if(tarefaParaMonitor==null || tarefaParaMonitor.getData()==null || tarefaParaMonitor.getTarefaParaMonitor()==null){
                throw new DadoNuloException();
            }
            if(ValidacaoDosIDs.verificaTarefa(tarefaParaMonitor.getTarefaParaMonitor().getId())==false){
            throw new DadoInexistenteException();
        }
            repTarefaParaMonitor.alterar(tarefaParaMonitor);
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
}
