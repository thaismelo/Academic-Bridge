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
public class Monitor extends Pessoa{
    private int id;
    private String codigo;
    private String senha;
    private int idTarefa;

    public Monitor() {
    }

    public Monitor(int id, String codigo, String senha, int idTarefa, String nome, String email) {
        super(nome, email);
        this.id = id;
        this.codigo = codigo;
        this.senha = senha;
        this.idTarefa = idTarefa;
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.codigo);
        hash = 53 * hash + Objects.hashCode(this.senha);
        hash = 53 * hash + this.idTarefa;
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
        final Monitor other = (Monitor) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (this.idTarefa != other.idTarefa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Monitor{" + "id=" + this.id + ", codigo=" + this.codigo + ", senha=" + this.senha + ", idTarefa=" + this.idTarefa + '}';
    }

   
    
    
}
