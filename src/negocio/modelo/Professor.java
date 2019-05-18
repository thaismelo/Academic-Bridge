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
    private Login idLogin;
    private Disciplina idDisc;
    
    public Professor() {
    }

    public Professor(Login IdLogin) {
        this.idLogin = idLogin;
    }

    public Professor(Login idLogin, Disciplina idDisc, int id, String nome, String email) {
        super(id, nome, email);
        this.idLogin = idLogin;
        this.idDisc = idDisc;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idLogin);
        hash = 79 * hash + Objects.hashCode(this.idDisc);
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
        return "Professor{" + "idLogin=" + this.idLogin + "nome=" + this.getNome() + "email=" + this.getEmail()+ "idDisc=" + this.idDisc + '}';
    }

   
    
}
