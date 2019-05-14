/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelo;

import negocio.modelo.Pessoa;
import negocio.modelo.Login;


/**
 *
 * @author thais
 */
public class Professor extends Pessoa{
    private Login login;
    private int id;
    public Professor() {
    }

    public Professor(Login login, String nome, String email) {
        super(nome, email);
        this.login = login;
    }

    public Professor(int id, String nome, String email) {
        super(nome, email);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Professor "+ this.getNome()+"{" + "login=" + this.login.getLogin() + "senha="+this.login.getSenha()+ '}';
    }
    
}
