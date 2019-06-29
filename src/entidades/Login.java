/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Login.SenhaInvalidaException;
import java.util.List;
import fachada.Fachada;

/**
 *
 * @author thais
 */
public class Login {
    public static final int PROFESSOR = 1;
    public static final int MONITOR = 2;
    private int id;
    private int tipo;
    private String login;
    private String senha;

    public Login() {
    }
    
    public Login(int id, int tipo,String login, String senha){
        this.id = id;
        this.tipo = tipo;
        if(tipo == 2){
            this.login = GeradorDeSenha.gerarSenha();
            this.senha = "12345678";
        }else{
            this.login = login;
            this.senha = senha;
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    

    @Override
    public String toString() {
        return "Login{"+"id=" + this.getId() + ", tipo="+ this.getTipo() + ", login=" + this.getLogin() + ", senha=" + this.getSenha() + '}';
    }
    
    
}
