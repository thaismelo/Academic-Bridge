/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import exceptions.banco.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import exceptions.banco.DadoInexistenteException;
import exceptions.entidades.Frequencia.FrequenciaInvalidaException;
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Frequencia;

/**
 *
 * @author thais
 */
public class CRUDFrequencia {
    private RepositorioGenerico rep;

    public CRUDFrequencia() {
        rep = new RepositorioFrequencia();
    }
    
    public void cadastrarFrequencia(Frequencia t) throws ExceptionErroNoBanco, FrequenciaInvalidaException {
        if (t.getFrequencia() != 1 && t.getFrequencia() != 2) {
            throw new FrequenciaInvalidaException();
        }
        rep.inserir(t);
    }

    public void removerFrequencia(Frequencia t) throws ExceptionErroNoBanco, DadoInexistenteException{
            if(t==null){
                throw new DadoInexistenteException();
            }
            rep.excluir(t);
            
    }    
    public void alterarFrequencia(Frequencia t) throws ExceptionErroNoBanco, FrequenciaInvalidaException, DadoInexistenteException{
        if(t==null){
            throw new DadoInexistenteException();
        }
        if (t.getFrequencia() != 1 && t.getFrequencia() != 2) {
            throw new FrequenciaInvalidaException();
        }
        rep.alterar(t);
    }
    
    public Frequencia recuperarFrequencia(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Frequencia> a = Fachada.getSingleton().recuperarTodosFrequencia();
        for(int i=0; i< a.size();i++){
            if(codigo == a.get(i).getId()){
                return (Frequencia) rep.recuperar(codigo);
            }else{
                throw new DadoInexistenteException();
            }
        }
        return null;
    }
    public List<Frequencia> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Frequencia>) rep.recuperarTodos();
    }
    
    
}
