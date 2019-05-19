/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import dados.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import java.util.List;
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
    
    public void removerPlanejamento(Planejamento p) throws ExceptionErroNoBanco{
        rep.excluir(p);
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
