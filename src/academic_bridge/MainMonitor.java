/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import exceptions.banco.DadoInexistenteException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Disciplina.DisciplinaInexistenteException;
import exceptions.entidades.Login.LoginNuloOuExistenteException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import exceptions.entidades.Tarefa.ConteudoNuloException;
import exceptions.entidades.Tarefa.EstadoInvalidoException;
import exceptions.entidades.Tarefa.ListaTarefaVaziaException;
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
        Professor prof = new Professor(14, p, 5,"profff","dgsds@dgsdg.com");
        List<Tarefa> tarefas = new ArrayList<>();
        Tarefa t1 = new Tarefa(12,1,1,"calcular", 1);
        Tarefa t2 = new Tarefa(10,1,2,"calcular p1", 1);
        Tarefa t3 = new Tarefa(11,1,2,"calcular pp2", 1);
        
        
        Monitor monitor = new Monitor(12, m, prof, tarefas, "jojo", "jojo@gmail.com");
        try{
     //       Fachada.getSingleton().cadastrarLogin(m);
            Monitor monitor2 = Fachada.getSingleton().recuperarMonitor(1);
            monitor.setTarefas(Fachada.getSingleton().recuperarTodosPorCriador(1, 2));
            System.out.println(monitor.toString());
     /*    //  Fachada.getSingleton().cadastrarLogin(p);
        //    Fachada.getSingleton().cadastrarProfessor(prof);
            Fachada.getSingleton().cadastrarMonitor(monitor);
            Fachada.getSingleton().cadastrarTarefa(t1);
            Fachada.getSingleton().cadastrarTarefa(t2);
            Fachada.getSingleton().cadastrarTarefa(t3);
        */}catch(ExceptionErroNoBanco e){
            System.out.println(e.getMessage());
       // }catch(NomeInvalidoException | EmailInvalidoException | LoginNuloOuExistenteException e){
       //     System.out.println(e.getLocalizedMessage());
       // } catch (DadoInexistenteException | ListaTarefaVaziaException | ConteudoNuloException | EstadoInvalidoException ex) {
        //    Logger.getLogger(MainMonitor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DadoInexistenteException ex) {
            Logger.getLogger(MainMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
