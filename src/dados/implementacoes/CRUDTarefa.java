/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import exceptions.banco.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import exceptions.banco.DadoInexistenteException;
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Tarefa;

/**
 *
 * @author Guilherme
 */
public class CRUDTarefa {
    private RepositorioGenerico repTarefa;

    public CRUDTarefa() {
        repTarefa = new RepositorioTarefa();
    }
    
    public void cadastrarTarefa(Tarefa tarefa) throws ExceptionErroNoBanco{
        repTarefa.inserir(tarefa);
    }
    
    public void removerTarefa(Tarefa tarefa) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Tarefa> a = Fachada.getSingleton().recuperarTodosTarefa();
        for(int i=0; i< a.size();i++){
            if(tarefa.getId() == a.get(i).getId()){
                repTarefa.excluir(tarefa);
            }else{
                throw new DadoInexistenteException();
            }
        }
    }    
    public void alterarTarefa(Tarefa tarefa) throws ExceptionErroNoBanco{
        repTarefa.alterar(tarefa);
    }
    
    public Tarefa recuperarTarefa(int codigo) throws ExceptionErroNoBanco{
        return (Tarefa) repTarefa.recuperar(codigo);
    }
    public List<Tarefa> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Tarefa>) repTarefa.recuperarTodos();
    }
}
