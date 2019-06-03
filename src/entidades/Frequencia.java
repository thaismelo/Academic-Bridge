/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Objects;

/**
 *
 * @author thais
 */
public class Frequencia {
    private int id;
    private Aluno aluno;
    private Monitor monitor;
    private int frequencia;
    public static final int PRESENÇA = 1;
    public static final int FALTA =2;

    public Frequencia() {
    }

    public Frequencia(int id, Aluno aluno, Monitor monitor, int frequencia) {
        this.id = id;
        this.aluno = aluno;
        this.monitor = monitor;
        this.frequencia = frequencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    
    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        if (!Objects.equals(this.aluno, other.aluno)) {
            return false;
        }
        if (!Objects.equals(this.monitor, other.monitor)) {
            return false;
        }
        if (this.frequencia != other.frequencia) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Frequencia{" + "id=" + this.id + ", Aluno=" + this.aluno + ", Monitor=" + this.monitor + ", frequencia=" + this.frequencia + '}';
    }
    
    
}
