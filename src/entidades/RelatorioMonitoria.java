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
public class RelatorioMonitoria {
    private int id;
    private Monitor monitor;
    private Tarefa tarefa;
    private int nivelDificuldade;
    private int reforcarAssunto;
    public static final int SIM=1;
    public static final int NÃO=2;
    private int participatividade;
    private String data;

    public RelatorioMonitoria() {
    }

    public RelatorioMonitoria(int id, Monitor monitor, Tarefa tarefa, int nivelDificuldade, int reforcarAssunto, int participatividade, String data) {
        this.id = id;
        this.monitor = monitor;
        this.tarefa = tarefa;
        this.nivelDificuldade = nivelDificuldade;
        this.reforcarAssunto = reforcarAssunto;
        this.participatividade = participatividade;
        this.data = data;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
    public int getNivelDificuldade() {
        return nivelDificuldade;
    }

    public void setNivelDificuldade(int nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }

    public int getReforcarAssunto() {
        return reforcarAssunto;
    }

    public void setReforcarAssunto(int reforçarAssunto) {
        this.reforcarAssunto = reforçarAssunto;
    }

    public int getParticipatividade() {
        return participatividade;
    }

    public void setParticipatividade(int participatividade) {
        this.participatividade = participatividade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
        final RelatorioMonitoria other = (RelatorioMonitoria) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.monitor, other.monitor)) {
            return false;
        }
        if (!Objects.equals(this.tarefa, other.tarefa)) {
            return false;
        }
        if (this.nivelDificuldade != other.nivelDificuldade) {
            return false;
        }
        if (this.reforcarAssunto != other.reforcarAssunto) {
            return false;
        }
        if (this.participatividade != other.participatividade) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "RelatorioMonitoria{" + "id=" + this.id + ", idMonitor=" + this.monitor + ", idTarefa=" + this.tarefa + ", nivelDificuldade=" + this.nivelDificuldade + ", reforcarAssunto=" + this.reforcarAssunto + ", participatividade=" + this.participatividade + ", data=" + this.data + '}';
    }
    
    
    
}
