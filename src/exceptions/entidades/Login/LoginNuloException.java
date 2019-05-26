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
public class LoginNuloException extends Exception{

    public LoginNuloException() {
        super("ERRO! Login nulo não pode ser cadastrado.");
    }
    
    
    
}
