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

/**
 *
 * @author Guilherme
 */
public class CRUDTarefa {
    private RepositorioGenerico repTarefa;

    public CRUDTarefa() {
        repTarefa = new RepositorioTarefa();
    }
    
    public void cadastrarTarefa(Tarefa tarefa) throws ExceptionErroNoBanco, ConteudoNuloException{
        if(tarefa.getConteudo()==null || tarefa.getConteudo().isEmpty()){
            throw new ConteudoNuloException();
        }
        repTarefa.inserir(tarefa);
    }
    
    public void removerTarefa(Tarefa tarefa) throws ExceptionErroNoBanco, DadoNuloException{
            if(tarefa!= null){
                repTarefa.excluir(tarefa);
            }else{
                throw new DadoNuloException();
            }
    }    
    public void alterarTarefa(Tarefa tarefa) throws ExceptionErroNoBanco, ConteudoNuloException, DadoNuloException{
            if(tarefa==null){
                throw new DadoNuloException();
            }
            if(tarefa.getConteudo()==null){
                throw new ConteudoNuloException();
            }
            repTarefa.alterar(tarefa);
    }
    
    public Tarefa recuperarTarefa(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Tarefa> a = Fachada.getSingleton().recuperarTodosTarefa();
        for(int i=0; i< a.size();i++){
            if(codigo == a.get(i).getId()){
                return (Tarefa) repTarefa.recuperar(codigo);
            }else{
                throw new DadoInexistenteException();
            }
        }
        return null;
    }
    public List<Tarefa> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Tarefa>) repTarefa.recuperarTodos();
    }
}
