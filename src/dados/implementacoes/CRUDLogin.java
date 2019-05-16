/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.implementacoes;

import dados.ExceptionErroNoBanco;
import dados.RepositorioGenerico;
import negocio.modelo.Login;

/**
 *
 * @author thais
 */
public class CRUDLogin {
    private RepositorioGenerico repLogin;

    public CRUDLogin() {
        repLogin = new RepositorioLogin();
    }
    
    public void cadastrarLogin(Login login) throws ExceptionErroNoBanco{
        repLogin.inserir(login);
    }
    
    public void removerLogin(Login login) throws ExceptionErroNoBanco{
        repLogin.excluir(login);
    }
    
    
}
