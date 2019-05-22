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
import negocio.modelo.Tarefa;

/**
 *
 * @author thais
 */
public class MainMonitor {
    public static void main(String[] args) throws SenhaInvalidaException, SenhaNulaException  {
        Login l = new Login(12, Login.MONITOR, "caca", "111");
        Monitor m = new Monitor(122, 1, 1, "nana", "nana@gmail.com");
        Aluno a = new Aluno(1, 1, "fonn", "fff@fff.com");
        Aluno b = new Aluno(2, 1, "fafan", "dd@ddf.com");
        Tarefa t = new Tarefa(1, "ganhou", 0);
        Tarefa t2 = new Tarefa(2, "perdeu", 0);
        try{
            Fachada.getSingleton().cadastrarLogin(l);
            Fachada.getSingleton().cadastrarMonitor(m);
            Fachada.getSingleton().cadastrarAluno(a);
            Fachada.getSingleton().cadastrarAluno(b);
            Fachada.getSingleton().cadastrarTarefa(t);
            Fachada.getSingleton().cadastrarTarefa(t2);
        }catch(ExceptionErroNoBanco e){
            System.out.println(e.getMessage());
        }catch(NomeInvalidoException | EmailInvalidoException e){
            System.out.println(e.getLocalizedMessage());
        }catch(LoginNuloOuExistenteException e){
            System.out.println(e.getLocalizedMessage());
        } 
    }
    
}
