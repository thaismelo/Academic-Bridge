/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import exceptions.banco.DadoInexistenteException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Fachada;
import negocio.modelo.Planejamento;
import negocio.modelo.Tarefa;

/**
 *
 * @author Guilherme
 */
public class MainPlanejamento {
    public static void main(String[] args) throws SenhaInvalidaException, SenhaNulaException  {
      //  Tarefa t = new Tarefa(1, "uttstu", 0);
        Planejamento p = new Planejamento(1, 1, 1, 1, "01/02/1999");
        try{
        //    Fachada.getSingleton().cadastrarTarefa(t);
        //    Fachada.getSingleton().cadastrarPlanejamento(p);
            Fachada.getSingleton().removerPlanejamento(p);
        }catch(ExceptionErroNoBanco e){
            System.out.println(e.getMessage());
        } catch (DadoInexistenteException ex) {
            Logger.getLogger(MainPlanejamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
