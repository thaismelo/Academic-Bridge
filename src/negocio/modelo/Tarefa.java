/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelo;

import java.util.Objects;

/**
 *
 * @author Guilherme
 */ 
public class Tarefa {
    private int id;
    private int idCriador;
    private int tipoCriador;
    private String conteudo;
    private int estado;
    public static final int FAZER = 0;
    public static final int EM_PROGRESSO = 1;
    public static final int COMPLETA=2;

    public Tarefa() {
    }

    public Tarefa(int id, String conteudo, int estado) {
        this.id = id;
        this.conteudo = conteudo;
        this.estado = estado;
    }

    public Tarefa(int id, int idCriador, int tipoCriador, String conteudo, int estado) {
        this.id = id;
        this.idCriador = idCriador;
        this.tipoCriador = tipoCriador;
        this.conteudo = conteudo;
        this.estado = estado;
    }
    
    public int getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(int idCriador) {
        this.idCriador = idCriador;
    }

    public int getTipoCriador() {
        return tipoCriador;
    }

    public void setTipoCriador(int tipoCriador) {
        this.tipoCriador = tipoCriador;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.conteudo);
        hash = 53 * hash + this.estado;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tarefa other = (Tarefa) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.conteudo, other.conteudo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tarefa{" + "id=" + this.id + ", conteudo=" + this.conteudo + ", estado=" + this.estado + '}';
    }
   
}