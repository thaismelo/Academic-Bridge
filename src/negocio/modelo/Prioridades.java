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
public class Prioridades {
    private int id;
    private int idProf;
    private String prioridade;

    public Prioridades() {
    }

    public Prioridades(int id, int idProf,String prioridade) {
        this.id = id;
        this.idProf = idProf;
        this.prioridade = prioridade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }
    

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + this.idProf;
        hash = 97 * hash + Objects.hashCode(this.prioridade);
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
        final Prioridades other = (Prioridades) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idProf != other.idProf) {
            return false;
        }
        if (!Objects.equals(this.prioridade, other.prioridade)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prioridades{" + "id=" + this.id + ", idProf=" + this.getIdProf() + ", prioridade=" + this.prioridade + '}';
    }

   
    
    
    
}
