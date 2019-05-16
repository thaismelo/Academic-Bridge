/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.ExceptionErroNoBanco;
import dados.implementacoes.CRUDLogin;
import negocio.modelo.Login;

/**
 *
 * @author thais
 */
public class Fachada {

    private CRUDLogin cadastrarLogin;

    private static Fachada singleton = null;

    public static Fachada getSingleton() {
        if (singleton == null) {
            singleton = new Fachada();
        }
        return singleton;
    }
    
    private Fachada(){
        cadastrarLogin = new CRUDLogin();
    }
    
    public void cadastrarLogin(Login login) throws ExceptionErroNoBanco{
        this.cadastrarLogin.cadastrarLogin(login);
    }
    
    public void alterarLogin(Login login) throws ExceptionErroNoBanco{
        this.cadastrarLogin.alterarLogin(login);
    }
    
    public Login recuperarLogin(int codigo) throws ExceptionErroNoBanco{
        return this.cadastrarLogin.recuperarLogin(codigo);
    }

}