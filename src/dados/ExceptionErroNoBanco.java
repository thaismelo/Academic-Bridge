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
public class ExceptionErroNoBanco extends Exception {

    private String message;

    public ExceptionErroNoBanco(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
