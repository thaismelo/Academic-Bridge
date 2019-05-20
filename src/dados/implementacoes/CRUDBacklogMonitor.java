/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import dados.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import java.util.List;
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
    
     public void cadastrarBacklogMonitor(BacklogMonitor t) throws ExceptionErroNoBanco{
        rep.inserir(t);
    }
    
    public void removerBacklogMonitor(BacklogMonitor t) throws ExceptionErroNoBanco{
        rep.excluir(t);
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
