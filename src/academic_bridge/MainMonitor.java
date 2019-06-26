/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Login.LoginNuloOuExistenteException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import negocio.Fachada;
import negocio.modelo.Login;
import negocio.modelo.Monitor;

/**
 *
 * @author thais
 */
public class MainMonitor {
    public static void main(String[] args) throws SenhaInvalidaException, SenhaNulaException  {
        Login l = new Login(12, Login.MONITOR, "caca", "111");
        Monitor m = new Monitor(122, 7, 2, "nana", "nana@gmail.com");
        try{
            Fachada.getSingleton().cadastrarLogin(l);
            Fachada.getSingleton().cadastrarMonitor(m);
        }catch(ExceptionErroNoBanco e){
            System.out.println(e.getMessage());
        }catch(NomeInvalidoException | EmailInvalidoException e){
            System.out.println(e.getLocalizedMessage());
        }catch(LoginNuloOuExistenteException e){
            System.out.println(e.getLocalizedMessage());
        }
    }
    
}
