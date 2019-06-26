/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelo;

/**
 *
 * @author thais
 */
public class BacklogMonitor {
    private int id;
    private int idTarefa;
    private int idMonitor;

    public BacklogMonitor() {
    }

    public BacklogMonitor(int id, int idTarefa, int idMonitor) {
        this.id = id;
        this.idTarefa = idTarefa;
        this.idMonitor = idMonitor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public int getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(int idMonitor) {
        this.idMonitor = idMonitor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + this.idTarefa;
        hash = 97 * hash + this.idMonitor;
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
        final BacklogMonitor other = (BacklogMonitor) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idTarefa != other.idTarefa) {
            return false;
        }
        if (this.idMonitor != other.idMonitor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BacklogMonitor{" + "id=" + this.id + ", idTarefa=" + this.idTarefa + ", idMonitor=" + this.idMonitor + '}';
    }
    
        
}
