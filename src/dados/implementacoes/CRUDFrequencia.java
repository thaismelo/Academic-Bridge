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
import exceptions.entidades.Frequencia.FrequenciaInvalidaException;
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Aluno;
import negocio.modelo.Frequencia;
import negocio.modelo.Monitor;

/**
 *
 * @author thais
 */
public class CRUDFrequencia {
    private RepositorioGenerico rep;

    public CRUDFrequencia() {
        rep = new RepositorioFrequencia();
    }
    
    public void cadastrarFrequencia(Frequencia t) throws ExceptionErroNoBanco, FrequenciaInvalidaException, DadoInexistenteException, DadoNuloException {
        if (t.getFrequencia() != 1 && t.getFrequencia() != 2) {
            throw new FrequenciaInvalidaException();
        }
        if(t.getAluno()==null){
            throw new DadoNuloException();
        }
        if(t.getMonitor()==null){
            throw new DadoNuloException();
        }
        if(ValidacaoDosIDs.verificaMonitor(t.getMonitor().getId())==false){
            throw new DadoInexistenteException();
        }
        if(ValidacaoDosIDs.verificaAluno(t.getAluno().getId())==false){
            throw new DadoInexistenteException();
        }
        rep.inserir(t);
    }

    public void removerFrequencia(Frequencia t) throws ExceptionErroNoBanco, DadoInexistenteException{
            if(t==null){
                throw new DadoInexistenteException();
            }
            rep.excluir(t);
            
    }    
    public void alterarFrequencia(Frequencia t) throws ExceptionErroNoBanco, FrequenciaInvalidaException, DadoInexistenteException, DadoNuloException{
        if(t==null){
            throw new DadoInexistenteException();
        }
        if (t.getFrequencia() != 1 && t.getFrequencia() != 2) {
            throw new FrequenciaInvalidaException();
        }
        if(t.getAluno()==null){
            throw new DadoNuloException();
        }
        if(t.getMonitor()==null){
            throw new DadoNuloException();
        }
        if(ValidacaoDosIDs.verificaMonitor(t.getMonitor().getId())==false){
            throw new DadoInexistenteException();
        }
        if(ValidacaoDosIDs.verificaAluno(t.getAluno().getId())==false){
            throw new DadoInexistenteException();
        }
        rep.alterar(t);
    }
    
    public Frequencia recuperarFrequencia(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Frequencia> a = Fachada.getSingleton().recuperarTodosFrequencia();
        for(int i=0; i< a.size();i++){
            if(codigo == a.get(i).getId()){
                return (Frequencia) rep.recuperar(codigo);
            }
        }
        throw new DadoInexistenteException();
    }
    public List<Frequencia> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Frequencia>) rep.recuperarTodos();
    }
    
    public int recuperarUltimoId() throws ExceptionErroNoBanco{
        return rep.recuperaUltimoID();
    }
    
}
