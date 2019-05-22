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
    private int idLogin;
    private int idProf;
    private List<Tarefa> listaTarefas;
    
    
    public Monitor() {
    }

    public Monitor(int id, int idLogin, int idProf, String nome, String email) {
        super(nome, email);
        this.id = id;
        this.idLogin = idLogin;
        this.idProf = idProf;
    }

    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + this.idLogin;
        hash = 89 * hash + this.idProf;
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
        final Monitor other = (Monitor) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idLogin != other.idLogin) {
            return false;
        }
        if (this.idProf != other.idProf) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Monitor{" + "id=" + this.id + ",idProf="+ this.idProf+",nome= " + this.getNome() + ",email="+this.getEmail()+ ", idLogin=" + this.idLogin + '}';
    }

    
    
   
   
    
    
}
