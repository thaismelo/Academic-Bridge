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
public class Turma {
    private int id;
    private String nomeAluno;
    private String emailAluno;
    private int idMonitor;

    public Turma() {
    }

    public Turma(int id, String nomeAluno, String emailAluno, int idMonitor) {
        this.id = id;
        this.nomeAluno = nomeAluno;
        this.emailAluno = emailAluno;
        this.idMonitor = idMonitor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public void setEmailAluno(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    public int getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(int idMonitor) {
        this.idMonitor = idMonitor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.nomeAluno);
        hash = 79 * hash + Objects.hashCode(this.emailAluno);
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
        final Turma other = (Turma) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nomeAluno, other.nomeAluno)) {
            return false;
        }
        if (!Objects.equals(this.emailAluno, other.emailAluno)) {
            return false;
        }
        if (this.idMonitor != other.idMonitor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Turma{" + "id=" + this.id + ", nomeAluno=" + this.nomeAluno + ", emailAluno=" + this.emailAluno + ", idMonitor=" + this.idMonitor + '}';
    }
    
    
}
