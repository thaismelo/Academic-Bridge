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
    private int idMonitor;
    private String nomeMonitor;
    private String prioridade;

    public Prioridades() {
    }

    public Prioridades(int id, int idProf,int idMonitor,String nomeMonitor,String prioridade) {
        this.id = id;
        this.idProf = idProf;
        this.idMonitor = idMonitor;
        this.nomeMonitor = nomeMonitor;
        this.prioridade = prioridade;
    }

    public int getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(int idMonitor) {
        this.idMonitor = idMonitor;
    }

    public String getNomeMonitor() {
        return nomeMonitor;
    }

    public void setNomeMonitor(String nomeMonitor) {
        this.nomeMonitor = nomeMonitor;
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
        hash = 73 * hash + this.id;
        hash = 73 * hash + this.idProf;
        hash = 73 * hash + this.idMonitor;
        hash = 73 * hash + Objects.hashCode(this.nomeMonitor);
        hash = 73 * hash + Objects.hashCode(this.prioridade);
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
        if (this.idMonitor != other.idMonitor) {
            return false;
        }
        if (!Objects.equals(this.nomeMonitor, other.nomeMonitor)) {
            return false;
        }
        if (!Objects.equals(this.prioridade, other.prioridade)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prioridades{" + "id=" + this.id + ", idProf=" + this.idProf + ", idMonitor=" + this.idMonitor + ", nomeMonitor=" + this.nomeMonitor + ", prioridade=" + this.prioridade + '}';
    }
    
    
    
}


   
    
    
    

