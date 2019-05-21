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
public class SenhaNulaException extends Exception{

    public SenhaNulaException() {
        super("ERRO! campo senha não pode estar vazio");
    }
    
}
