/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import dados.RepositorioGenerico;
import exceptions.banco.ExceptionErroNoBanco;
import java.util.List;
import negocio.modelo.RelatorioMonitoria;

/**
 *
 * @author thais
 */
public class CRUDRelatorioMonitoria {
    
    private RepositorioGenerico rep;

    public CRUDRelatorioMonitoria() {
        rep = new RepositorioRelatorioMonitoria();
    }
    
    public void cadastrarRelatorioMonitoria(RelatorioMonitoria r) throws ExceptionErroNoBanco{
        rep.inserir(r);
    }
    
     public void removerRelatorioMonitoria(RelatorioMonitoria r) throws ExceptionErroNoBanco{
        rep.excluir(r);
    }
     
     public void alterarRelatorioMonitoria(RelatorioMonitoria r) throws ExceptionErroNoBanco{
        rep.alterar(r);
    }
     
     public RelatorioMonitoria recuperarRelatorioMonitoria(int codigo) throws ExceptionErroNoBanco{
            return (RelatorioMonitoria) rep.recuperar(codigo);
    }
     
     public int recuperarUltimoIDRelatorioMonitoria() throws ExceptionErroNoBanco{
        return rep.recuperaUltimoID();
    }
     
    public List<RelatorioMonitoria> recuperarTodosRelatorioMonitoria() throws ExceptionErroNoBanco{
            return (List<RelatorioMonitoria>) rep.recuperarTodos();
    }
    
    
    
}
