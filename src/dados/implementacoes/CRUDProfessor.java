/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import dados.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import java.util.List;
import negocio.modelo.Professor;
/**
 *
 * @author Guilherme
 */
public class CRUDProfessor {
    private RepositorioGenerico repProfessor;

    public CRUDProfessor() {
        repProfessor = new RepositorioProfessor();
    }
    
    public void cadastrarProfessor(Professor professor) throws ExceptionErroNoBanco{
        repProfessor.inserir(professor);
    }
    
    public void removerProfessor(Professor professor) throws ExceptionErroNoBanco{
        repProfessor.excluir(professor);
    }    
    public void alterarProfessor(Professor professor) throws ExceptionErroNoBanco{
        repProfessor.alterar(professor);
    }
    
    public Professor recuperarProfessor(int codigo) throws ExceptionErroNoBanco{
        return (Professor) repProfessor.recuperar(codigo);
    }
    public List<Professor> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Professor>) repProfessor.recuperarTodos();
    }
}
