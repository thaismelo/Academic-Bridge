/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios.implementacoes;

import repositorios.implementacoes.banco.RepositorioRelatorioMonitoria;
import irepositorios.interfaces.RepositorioGenerico;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.RelatorioMonitoria.ForaDoIntervaloException;
import exceptions.entidades.RelatorioMonitoria.ValorIncorretoException;
import java.util.List;
import fachada.Fachada;
import entidades.RelatorioMonitoria;

/**
 *
 * @author thais
 */
public class CRUDRelatorioMonitoria {
    
    private RepositorioGenerico rep;

    public CRUDRelatorioMonitoria() {
        rep = new RepositorioRelatorioMonitoria();
    }
    
    public void cadastrarRelatorioMonitoria(RelatorioMonitoria r) throws ExceptionErroNoBanco, DadoNuloException, ForaDoIntervaloException, ValorIncorretoException, DadoInexistenteException{
        if(r.getData()==null || r.getMonitor()==null || r.getTarefa()==null){
            throw new DadoNuloException();
        }
        if(r.getNivelDificuldade()>100 || r.getNivelDificuldade()<0){
            throw new ForaDoIntervaloException();
        }
        if(r.getParticipatividade()>100 || r.getParticipatividade()<0){
            throw new ForaDoIntervaloException();
        }
        
        if(r.getReforcarAssunto()!=1 && r.getReforcarAssunto()!=2){
            throw new ValorIncorretoException();
        }
        if(ValidacaoDosIDs.verificaMonitor(r.getMonitor().getId())==false){
            throw new DadoInexistenteException();
        }
        if(ValidacaoDosIDs.verificaTarefa(r.getTarefa().getId())==false){
            throw new DadoInexistenteException();
        }
        rep.inserir(r);
    }
    
     public void removerRelatorioMonitoria(RelatorioMonitoria r) throws ExceptionErroNoBanco, DadoInexistenteException{
         if(r==null){
             throw new DadoInexistenteException();
         }
         rep.excluir(r);
    }
     
     public void alterarRelatorioMonitoria(RelatorioMonitoria r) throws ExceptionErroNoBanco, DadoInexistenteException, ForaDoIntervaloException, ValorIncorretoException, DadoNuloException{
         if(r==null){
             throw new DadoNuloException();
         }
         if(r.getData()==null || r.getMonitor()==null || r.getTarefa()==null){
            throw new DadoNuloException();
        }
        if(r.getNivelDificuldade()>100 || r.getNivelDificuldade()<0){
            throw new ForaDoIntervaloException();
        }
        if(r.getParticipatividade()>100 || r.getParticipatividade()<0){
            throw new ForaDoIntervaloException();
        }
        
        if(r.getReforcarAssunto()!=1 && r.getReforcarAssunto()!=2){
            throw new ValorIncorretoException();
        }
         if(ValidacaoDosIDs.verificaMonitor(r.getMonitor().getId())==false){
            throw new DadoInexistenteException();
        }
        if(ValidacaoDosIDs.verificaTarefa(r.getTarefa().getId())==false){
            throw new DadoInexistenteException();
        }
         rep.alterar(r);
    }
     
     public RelatorioMonitoria recuperarRelatorioMonitoria(int codigo) throws ExceptionErroNoBanco, DadoInexistenteException{
        List<RelatorioMonitoria> a = Fachada.getSingleton().recuperarTodosRelatorioMonitoria();
        for(int i=0; i< a.size();i++){
            if(codigo == a.get(i).getId()){
                return (RelatorioMonitoria) rep.recuperar(codigo);
            }
        }
        throw new DadoInexistenteException();
    }
     
     public int recuperarUltimoIDRelatorioMonitoria() throws ExceptionErroNoBanco{
        return rep.recuperaUltimoID();
    }
     
    public List<RelatorioMonitoria> recuperarTodosRelatorioMonitoria() throws ExceptionErroNoBanco{
            return (List<RelatorioMonitoria>) rep.recuperarTodos();
    }
    
    public List<RelatorioMonitoria> recuperarTodosRelatoriosPorCodMonitor(int cod) throws ExceptionErroNoBanco{
        return (List<RelatorioMonitoria>) new RepositorioRelatorioMonitoria().recuperarTodosRelatoriosPorCodMonitor(cod);
    }
    
}
