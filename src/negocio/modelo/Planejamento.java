/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelo;

import java.util.List;
import java.util.Objects;

/**
 * @author Guilherme
 */
public class Planejamento {
    private int id;
    private int idProf;
    private int idMonitor;
    private List<Tarefa> listaTarefas;
    private String data;

    public Planejamento() {
    }

    public Planejamento(int id, int idProf, int idMonitor, List<Tarefa> listaTarefas, String data) {
        this.id = id;
        this.idProf = idProf;
        this.idMonitor = idMonitor;
        this.listaTarefas = listaTarefas;
        this.data = data;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Tarefa> getListaTarefas() {
        return listaTarefas;
    }

    public void setListaTarefas(List<Tarefa> listaTarefas) {
        this.listaTarefas = listaTarefas;
    }
    
    public int getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(int idMonitor) {
        this.idMonitor = idMonitor;
    }
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Planejamento other = (Planejamento) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idProf != other.idProf) {
            return false;
        }
        if (this.idMonitor != other.idMonitor) {
            return false;
        }
        if (!Objects.equals(this.listaTarefas, other.listaTarefas)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Planejamento{" + "id=" + id + ", idProf=" + idProf + ", idMonitor=" + idMonitor + ", listaTarefas=" + listaTarefas + ", data=" + data + '}';
    }


    
}
