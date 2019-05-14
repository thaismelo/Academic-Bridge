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
public class Monitor extends Pessoa{
    private String codigo;
    private String senha;

    public Monitor() {
    }

    public Monitor(String codigo, String senha, String nome, String email) {
        super(nome, email);
        this.codigo = codigo;
        this.senha = senha;
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
    public String toString() {
        return "Monitor" + this.getNome()+"{" + "codigo=" + this.getCodigo() + ", senha=" + this.getSenha() + '}';
    }
    
    
    
}
