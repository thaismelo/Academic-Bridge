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
public class LoginExistenteException extends Exception{

    public LoginExistenteException() {
        super("ERRO! login já existente");
    }
    
    
    
}
