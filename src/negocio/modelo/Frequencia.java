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
public class Frequencia {
    private int id;
    private int idTurma;
    private int idMonitor;
    private int frequencia;
    public static final int FALTA = 1;

    public Frequencia() {
    }

    public Frequencia(int id, int idTurma, int idMonitor, int frequencia) {
        this.id = id;
        this.idTurma = idTurma;
        this.idMonitor = idMonitor;
        this.frequencia = frequencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public int getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(int idMonitor) {
        this.idMonitor = idMonitor;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + this.idTurma;
        hash = 67 * hash + this.idMonitor;
        hash = 67 * hash + this.frequencia;
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
        final Frequencia other = (Frequencia) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idTurma != other.idTurma) {
            return false;
        }
        if (this.idMonitor != other.idMonitor) {
            return false;
        }
        if (this.frequencia != other.frequencia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Frequencia{" + "id=" + this.id + ", idTurma=" + this.idTurma + ", idMonitor=" + this.idMonitor + ", frequencia=" + this.frequencia + '}';
    }
    
    
}
