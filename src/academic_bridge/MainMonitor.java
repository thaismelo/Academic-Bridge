/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import exceptions.banco.DadoInexistenteException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Login.LoginNuloOuExistenteException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Fachada;
import negocio.modelo.Aluno;
import negocio.modelo.Login;
import negocio.modelo.Monitor;
import negocio.modelo.Professor;
import negocio.modelo.Tarefa;

/**
 *
 * @author thais
 */
public class MainMonitor {
    public static void main(String[] args) throws SenhaInvalidaException, SenhaNulaException  {
        Login l = new Login(1, Login.MONITOR, "caca", "1ffsfafasf11");
        Login l2 = new Login(2, Login.PROFESSOR, "fffff", "22fasfasfafaf22");
        Professor p = new Professor(1, 2, 1, "fon", "fff@fff.com");
        Monitor m = new Monitor(1, l, p, "nana", "nana@gmail.com");
        try{
            Fachada.getSingleton().cadastrarLogin(l);
            Fachada.getSingleton().cadastrarLogin(l2);
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
