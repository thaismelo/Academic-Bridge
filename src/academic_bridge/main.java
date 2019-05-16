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
        //Login jo = new Login(4, "gui", "fon");
        
        try{
           // Fachada.getSingleton().alterarLogin(jo);
            Login jo = Fachada.getSingleton().recuperarLogin(3);
            System.out.println(jo.getLogin());
            System.out.println(jo.getSenha());
        }catch (ExceptionErroNoBanco e){
            System.out.println("Ocorreu um erro no acesso ao banco");
            System.out.println(e.getMessage());
        }
        
    }
}
