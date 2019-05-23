/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelo;

import java.util.Objects;

/**
 *
 * @author thais
 */
public class Professor extends Pessoa{
    private int id;
    private Login login;
    private int idDisc;
    
    public Professor() {
    }

    public Professor(int id, Login login, int idDisc, String nome, String email) {
        super(nome, email);
        this.id = id;
        this.login = login;
        this.idDisc = idDisc;
    }
    public Professor(Login login) {
        this.login = login;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public int getIdDisc() {
        return idDisc;
    }

    public void setIdDisc(int idDisc) {
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
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (this.idDisc != other.idDisc) {
            return false;
        }
        return true;
    }
    
  

    @Override
    public String toString() {
        return "Professor{" + "id="+ this.getId()+"idLogin=" + this.getLogin() + "idDisc=" + this.getIdDisc()+ "nome=" + this.getNome() + "email=" + this.getEmail()+ "idDisc=" + this.getIdDisc() + '}';
    }
  
}
