/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.banco;

/**
 *
 * @author thais
 */
public class DadoInexistenteException extends Exception{

    public DadoInexistenteException() {
        super("ERRO! Dado não existe no banco.");
    }
    
    
    
}
