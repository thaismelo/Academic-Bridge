/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import dados.DAO_SQLite;
import dados.ExceptionErroNoBanco;
import negocio.Fachada;
import negocio.modelo.Login;

/**
 *
 * @author thais
 */
public class main {
    public static void main(String[] args) {
        Login ka = new Login(33, "kaka", "kkkk");
        Login jo = new Login(6, "iss", "fon");
        
        try{
            Fachada.getSingleton().removerLogin(ka);
            jo = Fachada.getSingleton().recuperarLogin(6);
            System.out.println(jo.getLogin());
            System.out.println(jo.getSenha());
        }catch (ExceptionErroNoBanco e){
            System.out.println("Ocorreu um erro no acesso ao banco");
            System.out.println(e.getMessage());
        }
        
    }
}
