/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.entidades.RelatorioMonitoria;

/**
 *
 * @author thais
 */
public class ForaDoIntervaloException extends Exception{

    public ForaDoIntervaloException() {
        super("ERRO! o dado tem que estar entre 0 e 100.");
    }
    
    
    
}
