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
public class ConteudoNuloException extends Exception{

    public ConteudoNuloException() {
        super("ERRO! O conteudo não pode ser vazio ou nulo");
    }
    
}
