/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.entidades.Login;

/**
 *
 * @author thais
 */
public class SenhaInvalidaException extends Exception{

    public SenhaInvalidaException() {
        super("ERRO! senha deve conter pelo menos 8 caracteres");
    }
    
    
    
}
