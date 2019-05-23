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
import negocio.modelo.TarefaDoMonitor;

/**
 *
 * @author Guilherme
 */
public class CRUDTarefaDoMonitor {
    private RepositorioGenerico repTarefaDoMonitor;

    public CRUDTarefaDoMonitor() {
        repTarefaDoMonitor = new RepositorioTarefaDoMonitor();
    }
    
    public void cadastrarTarefaDoMonitor(TarefaDoMonitor TarefaDoMonitor) throws ExceptionErroNoBanco, ConteudoNuloException, EstadoInvalidoException{
        repTarefaDoMonitor.inserir(TarefaDoMonitor);
    }
    
    public void removerTarefaDoMonitor(TarefaDoMonitor TarefaDoMonitor) throws ExceptionErroNoBanco, DadoNuloException{
            if(TarefaDoMonitor!= null){
                repTarefaDoMonitor.excluir(TarefaDoMonitor);
            }else{
                throw new DadoNuloException();
            }
    }    
    public void alterarTarefaDoMonitor(TarefaDoMonitor TarefaDoMonitor) throws ExceptionErroNoBanco, ConteudoNuloException, DadoNuloException, EstadoInvalidoException{
            if(TarefaDoMonitor==null){
                throw new DadoNuloException();
            }
            repTarefaDoMonitor.alterar(TarefaDoMonitor);
    }
    
    public TarefaDoMonitor recuperarTarefaDoMonitor(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<TarefaDoMonitor> a = Fachada.getSingleton().recuperarTodosTarefaDoMonitor();
        for(int i=0; i< a.size();i++){
            if(codigo == a.get(i).getId()){
                return (TarefaDoMonitor) repTarefaDoMonitor.recuperar(codigo);
            }else{
                throw new DadoInexistenteException();
            }
        }
        return null;
    }
    public List<TarefaDoMonitor> recuperarTodosTarefaDoMonitor() throws ExceptionErroNoBanco{
        return (List<TarefaDoMonitor>) repTarefaDoMonitor.recuperarTodos();
    }
    
    public int recuperarUltimoIDTarefaDoMonitor() throws ExceptionErroNoBanco{
        return repTarefaDoMonitor.recuperaUltimoID();
    }
    
    public List<TarefaDoMonitor> recuperarTodosPorProfTarefaDoMonitor(int cod) throws ExceptionErroNoBanco{
        return (List<TarefaDoMonitor>) new RepositorioTarefaDoMonitor().recuperarTodosPorCodProf(cod);
    }
}
