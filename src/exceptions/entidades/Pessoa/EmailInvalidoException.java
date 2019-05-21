/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.entidades.Pessoa;

/**
 *
 * @author thais
 */
public class EmailInvalidoException extends Exception{

    public EmailInvalidoException() {
        super("ERRO! email nulo ou inválido");
    }
    
}
