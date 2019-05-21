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
import negocio.modelo.BacklogMonitor;

/**
 *
 * @author thais
 */
public class CRUDBacklogMonitor {
    private RepositorioGenerico rep;

    public CRUDBacklogMonitor() {
        rep = new RepositorioBacklogMonitor();
    }
    
     public void cadastrarBacklogMonitor(BacklogMonitor t) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<BacklogMonitor> a = Fachada.getSingleton().recuperarTodosBacklogMonitor();
        for (int i = 0; i < a.size(); i++) {
            if(t.getIdMonitor() == a.get(i).getIdMonitor() && t.getIdTarefa() == a.get(i).getIdTarefa()){
                rep.inserir(t);
            }else{
                throw new DadoInexistenteException();
            }
        }
    }
    
    public void removerBacklogMonitor(BacklogMonitor t) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<BacklogMonitor> a = Fachada.getSingleton().recuperarTodosBacklogMonitor();
        for(int i=0; i< a.size();i++){
            if(t.getId() == a.get(i).getId() || t!=null){
                rep.excluir(t);
            }else{
                throw new DadoInexistenteException();
            }
        }
    }    
    public void alterarBacklogMonitor(BacklogMonitor t) throws ExceptionErroNoBanco{
        rep.alterar(t);
    }
    
    public BacklogMonitor recuperarBacklogMonitor(int codigo) throws ExceptionErroNoBanco{
        return (BacklogMonitor) rep.recuperar(codigo);
    }
    public List<BacklogMonitor> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<BacklogMonitor>) rep.recuperarTodos();
    }
    
    
    
}
