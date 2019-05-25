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
public class ValorIncorretoException extends Exception{

    public ValorIncorretoException() {
        super("ERRO! o valor tem que ser 1 ou 2");
    }
    
    
}
