/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author thais
 */
public class Monitor extends Pessoa{
    private int id;
    private Login login;
    private Professor prof;
    private Disciplina disciplina;
    private List<Tarefa> tarefas;
    
    
    public Monitor() {
    }

    public Monitor(int id, Login login, Professor prof, List<Tarefa> tarefas, String nome, String email) {
        super(nome, email);
        this.id = id;
        this.login = login;
        this.prof = prof;
        this.tarefas = tarefas;
    }

    public Monitor(int id, Login login, Professor prof, String nome, String email, Disciplina disciplina) {
        super(nome, email);
        this.id = id;
        this.login = login;
        this.prof = prof;
        this.disciplina = disciplina;
    }
    
    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Professor getProf() {
        return prof;
    }

    public void setProf(Professor prof) {
        this.prof = prof;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Monitor other = (Monitor) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.prof, other.prof)) {
            return false;
        }
        if (!Objects.equals(this.disciplina, other.disciplina)) {
            return false;
        }
        if (!Objects.equals(this.tarefas, other.tarefas)) {
            return false;
        }
        return true;
    }

   
   

    @Override
    public String toString() {
        return "Monitor{" + "id=" + this.id + ", login=" + this.login + ", prof=" + this.prof + ", tarefas=" + this.tarefas + ",disciplina="+this.disciplina+'}';
    }
    
    
    
}
  