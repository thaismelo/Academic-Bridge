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
    private Login idLogin;
    
    public Professor() {
    }

    public Professor(Login IdLogin) {
        this.idLogin = idLogin;
    }

    public Professor( int id,Login idLogin, String nome, String email) {
        super(id, nome, email);
        this.idLogin = idLogin;
    }
    
    public Login getidLogin() {
        return idLogin;
    }

    public void setidLogin(Login idLogin) {
        this.idLogin = idLogin;
    }

    @Override
    public String toString() {
        return "Professor "+ this.getNome()+"{" + "login=" + this.idLogin.getLogin() + "senha="+this.idLogin.getSenha()+ '}';
    }
    
}
