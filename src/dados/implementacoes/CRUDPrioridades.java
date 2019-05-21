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
import negocio.modelo.Prioridades;

/**
 *
 * @author thais
 */
public class CRUDPrioridades {
    
    private RepositorioGenerico rep;

    public CRUDPrioridades() {
        rep = new RepositorioPrioridades();
    }
    
    public void cadastrarPrioridade(Prioridades p ) throws ExceptionErroNoBanco{
        rep.inserir(p);
    }
    
    public void removerPrioridades(Prioridades p) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Prioridades> a = Fachada.getSingleton().recuperarTodosPrioridades();
        for(int i=0; i< a.size();i++){
            if(p.getId() == a.get(i).getId()){
                rep.excluir(p);
            }else{
                throw new DadoInexistenteException();
            }
        }    
    }    
    public void alterarPrioridades(Prioridades p) throws ExceptionErroNoBanco{
        rep.alterar(p);
    }
    
    public Prioridades recuperarPrioridades(int codigo) throws ExceptionErroNoBanco{
        return (Prioridades) rep.recuperar(codigo);
    }
    public List<Prioridades> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Prioridades>) rep.recuperarTodos();
    }
}
