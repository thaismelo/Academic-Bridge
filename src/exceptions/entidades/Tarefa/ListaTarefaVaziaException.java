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
public class ListaTarefaVaziaException extends Exception{

    public ListaTarefaVaziaException() {
        super("ERRO! Lista não pode ser vazia ou nula.");
    }
    
    
}
