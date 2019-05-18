/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import dados.DAO_SQLite;
import dados.ExceptionErroNoBanco;
import java.util.ArrayList;
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Login;

/**
 *
 * @author thais
 */
public class main {
    public static void main(String[] args) {
        Login pof = new Login(22, Login.PROFESSOR, "profjo", "cid");
        Login k = new Login(11, Login.MONITOR, "cacau", "a");
        
        try{
            List<Login> listaLogin = Fachada.getSingleton().recuperarTodosLogin();
            for(int i=0;i<listaLogin.size();i++){
                System.out.println(listaLogin.get(i).getLogin());
            }
            
        }catch (ExceptionErroNoBanco e){
            System.out.println("Ocorreu um erro no acesso ao banco");
            System.out.println(e.getMessage());
        }
        
    }
}
