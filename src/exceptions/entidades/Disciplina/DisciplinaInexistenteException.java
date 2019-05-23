/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.entidades.Disciplina;

/**
 *
 * @author thais
 */
public class DisciplinaInexistenteException extends Exception{

    public DisciplinaInexistenteException() {
        super("ERRO! Disciplina não existe no banco.");
    }
    
    
}
