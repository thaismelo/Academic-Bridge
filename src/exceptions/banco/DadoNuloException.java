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
public class DadoNuloException extends Exception{

    public DadoNuloException() {
        super("ERRO! Dado não pode ser nulo");
    }
    
}
