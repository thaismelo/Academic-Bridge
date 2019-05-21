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
import negocio.modelo.Planejamento;

/**
 *
 * @author Guilherme
 */
public class CRUDPlanejamento {
    private RepositorioGenerico rep;

    public CRUDPlanejamento() {
        rep = new RepositorioPlanejamento();
    }
    
    public void cadastrarPlanejamento(Planejamento p ) throws ExceptionErroNoBanco{
        rep.inserir(p);
    }
    
    public void removerPlanejamento(Planejamento p) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Planejamento> a = Fachada.getSingleton().recuperarTodosPlanejamento();
        for(int i=0; i< a.size();i++){
            if(p.getId() == a.get(i).getId() || p!=null ){
                rep.excluir(p);
            }else{
                throw new DadoInexistenteException();
            }
        }
    }    
    public void alterarPlanejamento(Planejamento p) throws ExceptionErroNoBanco{
        rep.alterar(p);
    }
    
    public Planejamento recuperarPlanejamento(int codigo) throws ExceptionErroNoBanco{
        return (Planejamento) rep.recuperar(codigo);
    }
    public List<Planejamento> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Planejamento>) rep.recuperarTodos();
    }
}
