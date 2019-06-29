/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author thais
 */
public class TarefaParaMonitor {
    private int id;
    private int codProf;
    private int codMonit;
    private int codDisciplina;
    private Tarefa tarefaParaMonitor;
    private String data;

    public TarefaParaMonitor() {
    }

    public TarefaParaMonitor(int id, String data) {
        this.id = id;
        this.data = data;
    }
    
    public TarefaParaMonitor(int id, int codProf, int codMonit, Tarefa tarefaParaMonitor, String data) {
        this.id = id;
        this.codProf = codProf;
        this.codMonit = codMonit;
        this.tarefaParaMonitor = tarefaParaMonitor;
        this.data = data;
    }
    
    public TarefaParaMonitor(int id, Tarefa tarefaParaMonitor, String data) {
        this.id = id;
        this.tarefaParaMonitor = tarefaParaMonitor;
        this.data = data;
    }

    public TarefaParaMonitor(int id, int codProf, Tarefa tarefaParaMonitor, String data) {
        this.id = id;
        this.codProf = codProf;
        this.tarefaParaMonitor = tarefaParaMonitor;
        this.data = data;
    }

    public TarefaParaMonitor(int id, int codMonit, String data) {
        this.id = id;
        this.codMonit = codMonit;
        this.data = data;
    }

    public TarefaParaMonitor(int id, int codProf, int codMonit, int codDisciplina, Tarefa tarefaParaMonitor, String data) {
        this.id = id;
        this.codProf = codProf;
        this.codMonit = codMonit;
        this.codDisciplina = codDisciplina;
        this.tarefaParaMonitor = tarefaParaMonitor;
        this.data = data;
    }

    public int getCodDisciplina() {
        return codDisciplina;
    }

    public void setCodDisciplina(int codDisciplina) {
        this.codDisciplina = codDisciplina;
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

    public Tarefa getTarefaParaMonitor() {
        return tarefaParaMonitor;
    }

    public void setTarefaParaMonitor(Tarefa tarefaDoMonitor) {
        this.tarefaParaMonitor = tarefaDoMonitor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCodMonit() {
        return codMonit;
    }

    public void setCodMonit(int codMonit) {
        this.codMonit = codMonit;
    }

    @Override
    public String toString() {
        return "TarefaParaMonitor{" + "id=" + id + ", codProf=" + codProf + ", codMonit=" + codMonit + ", tarefaParaMonitor=" + tarefaParaMonitor + ", data=" + data + '}';
    }    
    
    
}
