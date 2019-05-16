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
        Login jo = new Login(222, "Iasmym", "fufu");
        Login ka = new Login(33, "kaka", "kkkk");
        try{
            Fachada.getSingleton().cadastrarLogin(ka);
            Fachada.getSingleton().removerLogin(jo);
        }catch (ExceptionErroNoBanco e){
            System.out.println("Ocorreu um erro no acesso ao banco");
            System.out.println(e.getMessage());
        }
        
    }
}
