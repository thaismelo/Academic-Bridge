/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author thais
 */
public class Pessoa {
    private String nome;
    private String email;

    public Pessoa() {
    }

    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    
    public static boolean validarEmail(String email) {
        boolean emailValidando = false; 
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"; 
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE); 
            Matcher matcher = pattern.matcher(email); 
            if (matcher.matches()) { 
                emailValidando = true; 
            } 
        } 
        return emailValidando; 
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pessoa{"+"nome=" + this.getNome() + ", email=" + this.getEmail() + '}';
    }
    
    
}
