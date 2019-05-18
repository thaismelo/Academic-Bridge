/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelo;

import java.util.Objects;
import negocio.modelo.Pessoa;
import negocio.modelo.Login;


/**
 *
 * @author thais
 */
public class Professor extends Pessoa{
    private int id;
    private Login idLogin;
    private Disciplina idDisc;
    
    public Professor() {
    }

    public Professor(Login IdLogin) {
        this.idLogin = idLogin;
    }

    public Professor(Login idLogin, Disciplina idDisc, int id, String nome, String email) {
        super(nome, email);
        this.idLogin = idLogin;
        this.idDisc = idDisc;
        this.id = id;
    }

    public Login getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Login idLogin) {
        this.idLogin = idLogin;
    }

    public Disciplina getIdDisc() {
        return idDisc;
    }

    public void setIdDisc(Disciplina idDisc) {
        this.idDisc = idDisc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.idLogin);
        hash = 97 * hash + Objects.hashCode(this.idDisc);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Professor other = (Professor) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.idLogin, other.idLogin)) {
            return false;
        }
        if (!Objects.equals(this.idDisc, other.idDisc)) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "Professor{" + "idLogin=" + this.idLogin + "id="+ this.getId()+"nome=" + this.getNome() + "email=" + this.getEmail()+ "idDisc=" + this.idDisc + '}';
    }

   
    
}
