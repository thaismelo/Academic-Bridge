/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import dados.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import java.util.List;
import negocio.modelo.Turma;

/**
 *
 * @author thais
 */
public class CRUDTurma {
    private RepositorioGenerico rep;

    public CRUDTurma() {
        rep = new RepositorioTurma();
    }
    
    public void cadastrarTurma(Turma t) throws ExceptionErroNoBanco{
        rep.inserir(t);
    }
    
    public void removerTurma(Turma t) throws ExceptionErroNoBanco{
        rep.excluir(t);
    }    
    public void alterarTurma(Turma t) throws ExceptionErroNoBanco{
        rep.alterar(t);
    }
    
    public Turma recuperarTurma(int codigo) throws ExceptionErroNoBanco{
        return (Turma) rep.recuperar(codigo);
    }
    public List<Turma> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Turma>) rep.recuperarTodos();
    }
    
}
