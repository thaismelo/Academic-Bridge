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
public class Aluno extends Pessoa{
    private int id;
    private Monitor monitor;

    public Aluno(int id, Monitor monitor) {
        this.id = id;
        this.monitor = monitor;
    }

    public Aluno(int id, Monitor monitor, String nome, String email) {
        super(nome, email);
        this.id = id;
        this.monitor = monitor;
    }
    
    
    public Aluno() {
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        final Aluno other = (Aluno) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.monitor, other.monitor)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Turma{" + "id=" + this.id + ", nome=" + this.getNome() + ", email=" + this.getEmail() + ", Monitor=" + this.monitor + '}';
    }
    
    
}
