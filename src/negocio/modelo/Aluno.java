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
    private int idMonitor;

    public Aluno() {
    }

    public Aluno(int id, int idMonitor, String nome, String email) {
        super(nome, email);
        this.id = id;
        this.idMonitor = idMonitor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.idMonitor;
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
        if (this.idMonitor != other.idMonitor) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Turma{" + "id=" + this.id + ", nome=" + this.getNome() + ", email=" + this.getEmail() + ", idMonitor=" + this.idMonitor + '}';
    }
    
    
}
