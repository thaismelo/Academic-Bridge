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
import java.util.ArrayList;
import java.util.List;
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
        Login m = new Login(1, Login.MONITOR, "caca", "1ffsfafasf11");
        Login p = new Login(22, Login.PROFESSOR, "gira", "12345678");
        Professor prof = new Professor(14, p, 5, null);
        List<Tarefa> tarefas = new ArrayList<>();
        Tarefa t1 = new Tarefa(12, "calcular", 1);
        Tarefa t2 = new Tarefa(10, "calcular p1", 1);
        Tarefa t3 = new Tarefa(11, "calcular pp2", 1);
        tarefas.add(t1);
        tarefas.add(t2);
        tarefas.add(t3);
        
        Monitor monitor = new Monitor(12, m, prof, tarefas, "jojo", "jojo@gmail.com");
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
