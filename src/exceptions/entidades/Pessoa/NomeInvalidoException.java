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
public class NomeInvalidoException  extends Exception{

    public NomeInvalidoException() {
        super("ERRO! Nome não pode ser nulo");
    }
    
}
