/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios.implementacoes;

import entidades.Disciplina;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import fachada.Fachada;
import irepositorios.interfaces.RepositorioGenerico;
import java.util.List;
import repositorios.implementacoes.banco.RepositorioDisciplina;

/**
 *
 * @author thais
 */
public class CRUDDisciplina {
    private RepositorioGenerico rep;

    public CRUDDisciplina() {
        rep = new RepositorioDisciplina();
    }
    
    public void cadastrarDisciplina(Disciplina d) throws ExceptionErroNoBanco, DadoNuloException{
        if(d.getNome()==null){
            throw new DadoNuloException();
        }
        if(d.getCurso()==null){
            throw new DadoNuloException();
        }
        rep.inserir(d);
    }
    public void removerDisciplina(Disciplina d) throws ExceptionErroNoBanco, DadoInexistenteException{
        if(d==null){
            throw new DadoInexistenteException();
        }
        rep.excluir(d);
    }
        
    public void alterarDisciplina(Disciplina d) throws ExceptionErroNoBanco, DadoNuloException, DadoInexistenteException{
         if(d.getNome()==null){
            throw new DadoNuloException();
        }
        if(d.getCurso()==null){
            throw new DadoNuloException();
        }
        if(d==null){
            throw new DadoInexistenteException();
        }
        rep.alterar(d);
    }
        
    public Disciplina recuperarDisciplina(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Disciplina> a = Fachada.getSingleton().recuperarTodosDisciplina();
        for(int i=0; i< a.size();i++){
            if(codigo == a.get(i).getId()){
                return (Disciplina) rep.recuperar(codigo);
            }
        }
        throw new DadoInexistenteException();
    }
    public int recuperarUltimoId() throws ExceptionErroNoBanco{
        return rep.recuperaUltimoID();
    }
    public List<Disciplina> recuperarTodos() throws ExceptionErroNoBanco {
        return (List<Disciplina>) rep.recuperarTodos();
    }
    public List<Disciplina> recuperarDisciplinasPorProf(int cod) throws ExceptionErroNoBanco, DadoInexistenteException {
        return (List<Disciplina>) new RepositorioDisciplina().recuperarTodosPorProf(cod);
    }
    
}
