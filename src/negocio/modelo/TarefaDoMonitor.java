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
public class TarefaDoMonitor {
    private int id;
    private int codProf;
    private Tarefa tarefaDoMonitor;
    private String data;

    public TarefaDoMonitor() {
    }

    public TarefaDoMonitor(int id, String data) {
        this.id = id;
        this.data = data;
    }

    public TarefaDoMonitor(Tarefa tarefaDoMonitor, String data) {
        this.tarefaDoMonitor = tarefaDoMonitor;
        this.data = data;
    }
    
    public TarefaDoMonitor(int id, Tarefa tarefaDoMonitor, String data) {
        this.id = id;
        this.tarefaDoMonitor = tarefaDoMonitor;
        this.data = data;
    }

    public TarefaDoMonitor(int id, int codProf, Tarefa tarefaDoMonitor, String data) {
        this.id = id;
        this.codProf = codProf;
        this.tarefaDoMonitor = tarefaDoMonitor;
        this.data = data;
    }

    public TarefaDoMonitor(int id, int codProf, String data) {
        this.id = id;
        this.codProf = codProf;
        this.data = data;
    }
    
    
    public int getCodProf() {
        return codProf;
    }

    public void setCodProf(int codProf) {
        this.codProf = codProf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tarefa getTarefaDoMonitor() {
        return tarefaDoMonitor;
    }

    public void setTarefaDoMonitor(Tarefa tarefaDoMonitor) {
        this.tarefaDoMonitor = tarefaDoMonitor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TarefaDoMonitor{" + "id=" + id + ", codProf=" + codProf + ", tarefaDoMonitor=" + tarefaDoMonitor + ", data=" + data + '}';
    }
    
    
}
