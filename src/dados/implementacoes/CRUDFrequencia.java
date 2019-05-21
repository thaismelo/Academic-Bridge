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
    
     public void cadastrarFrequencia(Frequencia t) throws ExceptionErroNoBanco{
        rep.inserir(t);
    }
    
    public void removerFrequencia(Frequencia t) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Frequencia> a = Fachada.getSingleton().recuperarTodosFrequencia();
        for(int i=0; i< a.size();i++){
            if(t.getId() == a.get(i).getId() || t!=null){
                rep.excluir(t);
            }else{
                throw new DadoInexistenteException();
            }
        }
    }    
    public void alterarFrequencia(Frequencia t) throws ExceptionErroNoBanco{
        rep.alterar(t);
    }
    
    public Frequencia recuperarFrequencia(int codigo) throws ExceptionErroNoBanco{
        return (Frequencia) rep.recuperar(codigo);
    }
    public List<Frequencia> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Frequencia>) rep.recuperarTodos();
    }
    
    
}
