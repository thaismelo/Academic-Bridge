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
    private int idLogin;
    private int idDisc;
    
    public Professor() {
    }

    public Professor(int IdLogin) {
        this.idLogin = idLogin;
    }

    public Professor(int id,int idLogin, int idDisc, String nome, String email) {
        super(nome, email);
        this.idLogin = idLogin;
        this.idDisc = idDisc;
        this.id = id;
    }
    
    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
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
        hash = 67 * hash + this.id;
        hash = 67 * hash + this.idLogin;
        hash = 67 * hash + this.idDisc;
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
        if (this.idLogin != other.idLogin) {
            return false;
        }
        if (this.idDisc != other.idDisc) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Professor{" + "id="+ this.getId()+"idLogin=" + this.getIdLogin() + "idDisc=" + this.getIdDisc()+ "nome=" + this.getNome() + "email=" + this.getEmail()+ "idDisc=" + this.getIdDisc() + '}';
    }
  
}
