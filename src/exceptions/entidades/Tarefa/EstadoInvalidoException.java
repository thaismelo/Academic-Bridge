/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.entidades.Tarefa;

/**
 *
 * @author thais
 */
public class EstadoInvalidoException  extends Exception{

    public EstadoInvalidoException() {
        super("ERRO! Não corresponde à um estado.");
    }
    
    
    
}
