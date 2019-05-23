/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelo;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author thais
 */
public class Monitor extends Pessoa{
    private int id;
    private Login login;
    private Professor prof;
    
    
    public Monitor() {
    }

    public Monitor(int id, Login login, Professor prof, String nome, String email) {
        super(nome, email);
        this.id = id;
        this.login = login;
        this.prof = prof;
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

    public Professor getProf() {
        return prof;
    }

    public void setProf(Professor prof) {
        this.prof = prof;
    }

    
}
  