/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.modelo;

import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Login.SenhaInvalidaException;
import java.util.List;
import negocio.Fachada;

/**
 *
 * @author thais
 */
public class Login {
    public static final int PROFESSOR = 1;
    public static final int MONITOR = 2;
    private int id;
    private int tipo;
    private String login;
    private String senha;

    public Login() {
    }
    
    public Login(int id, int tipo,String login, String senha) throws SenhaInvalidaException{
        this.id = id;
        this.tipo = tipo;
        if(tipo == 2){
            this.login = GeradorDeSenha.gerarSenha();
            this.senha = "123";
        }else{
            this.login = login;
            if(senha.length()>=8){
                this.senha = senha;
            }else{
                throw new SenhaInvalidaException("ERRO! senha deve conter pelo menos 8 caracteres");
            }
        }
    }

    public boolean recuperaLogin(Login l) throws ExceptionErroNoBanco{
        List<Login> lista = Fachada.getSingleton().recuperarTodosLogin();
        
        for(int i=0;i<lista.size();i++){
            if(l.getLogin() == null || l.getLogin().equals(lista.get(i).getLogin())){
                return false;
            }
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    

    @Override
    public String toString() {
        return "Login{"+"id=" + this.getId() + "tipo="+ this.getTipo() + "login=" + this.getLogin() + ", senha=" + this.getSenha() + '}';
    }
    
    
}
