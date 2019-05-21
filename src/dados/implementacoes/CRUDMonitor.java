/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import exceptions.banco.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import exceptions.banco.DadoInexistenteException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Monitor;

/**
 *
 * @author thais
 */
public class CRUDMonitor {
    private RepositorioGenerico rep;

    public CRUDMonitor() {
        rep = new RepositorioMonitor();
    }
    
    public void cadastrarMonitor(Monitor t) throws ExceptionErroNoBanco, NomeInvalidoException, EmailInvalidoException{
        if(t.getNome()==null){
            throw new NomeInvalidoException();
        }
        if(Monitor.validarEmail(t.getEmail())== false){
            throw new EmailInvalidoException();
        }
        rep.inserir(t);
    }
    
    public void removerMonitor(Monitor t) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<Monitor> a = Fachada.getSingleton().recuperarTodosMonitor();
        for(int i=0; i< a.size();i++){
            if(t.getId() == a.get(i).getId()){
                rep.excluir(t);
            }else{
                throw new DadoInexistenteException();
            }
        }    
    }    
    public void alterarMonitor(Monitor t) throws ExceptionErroNoBanco{
        rep.alterar(t);
    }
    
    public Monitor recuperarMonitor(int codigo) throws ExceptionErroNoBanco{
        return (Monitor) rep.recuperar(codigo);
    }
    public List<Monitor> recuperarTodos() throws ExceptionErroNoBanco{
        return (List<Monitor>) rep.recuperarTodos();
    }
    
    
}
