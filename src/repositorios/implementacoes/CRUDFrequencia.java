/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios.implementacoes;

import repositorios.implementacoes.banco.RepositorioFrequencia;
import exceptions.banco.ExceptionErroNoBanco;
import irepositorios.interfaces.RepositorioGenerico;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.entidades.Frequencia.FrequenciaInvalidaException;
import java.util.List;
import fachada.Fachada;
import entidades.Aluno;
import entidades.Frequencia;
import entidades.Monitor;
import entidades.ValidacaoData;
import exceptions.entidades.Frequencia.DataInvalidaException;
import java.util.ArrayList;

/**
 *
 * @author thais
 */
public class CRUDFrequencia {
    private RepositorioGenerico rep;

    public CRUDFrequencia() {
        rep = new RepositorioFrequencia();
    }
    
    public void cadastrarFrequencia(Frequencia t) throws ExceptionErroNoBanco, FrequenciaInvalidaException, DadoInexistenteException, DadoNuloException, DataInvalidaException {
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
        if(new ValidacaoData().data(t.getData()) == false){
            throw new DataInvalidaException();
        }
        rep.inserir(t);
    }

    public void removerFrequencia(Frequencia t) throws ExceptionErroNoBanco, DadoInexistenteException{
            if(t==null){
                throw new DadoInexistenteException();
            }
            rep.excluir(t);
            
    }    
    public void alterarFrequencia(Frequencia t) throws ExceptionErroNoBanco, FrequenciaInvalidaException, DadoInexistenteException, DadoNuloException, DataInvalidaException{
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
        if(new ValidacaoData().data(t.getData()) == false){
            throw new DataInvalidaException();
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
    
    public List<Frequencia> recuperarTodosPorMonit(int cod) throws ExceptionErroNoBanco{
        return (List<Frequencia>) new RepositorioFrequencia().recuperarTodosPorMonit(cod);
    }
    
    public List<String> retornarDatasUnicasFrequencia(int cod) throws ExceptionErroNoBanco{
        List<String> lista =  new ArrayList<>();
        List<Frequencia> a = recuperarTodosPorMonit(cod);
        for(int i=0; i< a.size();i++){
            if(!lista.contains(a.get(i).getData())){
                lista.add(a.get(i).getData());
            }
        }
        return lista;
    }
    
    public List<Frequencia> recuperarTodosPorData(String data) throws ExceptionErroNoBanco{
        return (List<Frequencia>) new RepositorioFrequencia().recuperarTodosPorData(data);
    }
}
