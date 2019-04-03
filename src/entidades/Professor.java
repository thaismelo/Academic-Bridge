/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author thais
 */
public class Professor extends Pessoa{
    private Login login;

    public Professor() {
    }

    public Professor(Login login, String nome, String email) {
        super(nome, email);
        this.login = login;
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
