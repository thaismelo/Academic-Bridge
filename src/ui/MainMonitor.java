/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Disciplina.DisciplinaInexistenteException;
import exceptions.entidades.Login.LoginExistenteException;
import exceptions.entidades.Login.LoginNuloException;
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
import fachada.Fachada;
import entidades.Aluno;
import entidades.Disciplina;
import entidades.Login;
import entidades.Monitor;
import entidades.Professor;
import entidades.Tarefa;

/**
 *
 * @author thais
 */
public class MainMonitor {
    public static void main(String[] args){
        int id = 0;
        
        //Cadastrando monitor
      
        try{
            //Cadastrar professor
            Login p = new Login(1, Login.PROFESSOR, "caeerAlberto", "12345678");
          //  Fachada.getSingleton().cadastrarLogin(p);
            Professor prof = new Professor(1, p,"pcarldos","dgsdskkk@dgsdg.com");
            id = Fachada.getSingleton().recuperaUltimoIdLogin();
           /* try {
                Fachada.getSingleton().cadastrarProfessor(prof);
            } catch (DisciplinaInexistenteException ex) {
                Logger.getLogger(MainMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }*/            //Cadastrar Monitor com seu professor
            Login m = new Login(3, Login.MONITOR, "ff ", "afa");
            Fachada.getSingleton().cadastrarLogin(m);
            Disciplina d = new Disciplina(2, "fon", "tuts");
            Monitor monitor = new Monitor(18, m, prof, "Galvao", "gav@gmail.com",d);
            id = Fachada.getSingleton().recuperaUltimoIdLogin();
            id = Fachada.getSingleton().recuperarUltimoIdProfessor();
            System.out.println(id);
            Fachada.getSingleton().cadastrarMonitor(monitor);
            System.out.println(monitor.toString());
        } catch (ExceptionErroNoBanco | DadoInexistenteException | NomeInvalidoException | EmailInvalidoException | DadoNuloException ex) {
            Logger.getLogger(MainMonitor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LoginExistenteException ex) {
            Logger.getLogger(MainMonitor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SenhaInvalidaException ex) {
            Logger.getLogger(MainMonitor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SenhaNulaException ex) {
            Logger.getLogger(MainMonitor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LoginNuloException ex) {
            Logger.getLogger(MainMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Monitor ja cadastrado, recuperando suas tarefas
      /*
        try{
            Tarefa  t = new Tarefa(1,1,Tarefa.MONITOR,"M teste", 1);
            Fachada.getSingleton().cadastrarTarefa(t);
        
            Tarefa  t2 = new Tarefa(2,1,Tarefa.MONITOR,"M teste2", 1);
            Fachada.getSingleton().cadastrarTarefa(t2);
            
        
            id = Fachada.getSingleton().recuperarUltimoIdMonitor();
            Monitor monit = Fachada.getSingleton().recuperarMonitor(id);
            monit.setTarefas(Fachada.getSingleton().recuperarTodosPorCriador(monit.getId(), 2));
            System.out.println(monit.toString());
            
        }catch(ExceptionErroNoBanco | ConteudoNuloException | EstadoInvalidoException  e){
            System.out.println(e.getLocalizedMessage());
        }catch (DadoInexistenteException ex){
            Logger.getLogger(mainProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        //Monitor recuperando tarefas designadas pelo professor
      /* 
        try{
        
            id = Fachada.getSingleton().recuperarUltimoIdMonitor();
            Monitor monit = Fachada.getSingleton().recuperarMonitor(id);
            monit.setTarefas(Fachada.getSingleton().recuperarTodosPorCodMonitTarefaParaMonitor(monit.getId()));
            System.out.println(monit.toString());
            
        }catch(ExceptionErroNoBanco  e){
            System.out.println(e.getLocalizedMessage());
        }catch (DadoInexistenteException ex){
            Logger.getLogger(mainProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
        //Recuperar
      /*
        try{
            Monitor monitor = Fachada.getSingleton().recuperarMonitor(1);
            System.out.println(monitor.toString());
        }catch(ExceptionErroNoBanco | DadoInexistenteException e){
            System.out.println(e.getLocalizedMessage());
        }*/
      
        //Alterar
      /*
        try{
            Login p = new Login(22, Login.PROFESSOR, "carlosAlberto", "12345678");
            Professor prof = new Professor(1, p, 5,"profcarlos","dgsds@dgsdg.com");
            Login l = new Login(2, Login.MONITOR, "p0j42dc1", "123");
            Monitor m = new Monitor(1, l, prof, "felie", "dilon@gmail.com");
            Fachada.getSingleton().alterarMonitor(m);
            
            Monitor monit = Fachada.getSingleton().recuperarMonitor(1);
            System.out.println(monit.toString());
            
        }catch(ExceptionErroNoBanco | DadoInexistenteException | DadoNuloException  | EmailInvalidoException | NomeInvalidoException e){
            System.out.println(e.getLocalizedMessage());
        }catch(SenhaInvalidaException e){
            System.out.println(e.getMessage());
        }*/
      
        //RecuperarTodos
        
      /*
        try{
            List<Monitor> list = Fachada.getSingleton().recuperarTodosMonitor();
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i).toString());
            }
        }catch(ExceptionErroNoBanco e){
            System.out.println(e.getLocalizedMessage());
        }*/
        
        
        //Remover 
       /*
        try{
            id= Fachada.getSingleton().recuperarUltimoIdMonitor();
            Monitor m = Fachada.getSingleton().recuperarMonitor(id);
            System.out.println(m.getLogin().getId());
            Fachada.getSingleton().removerMonitor(m);
            
            //Mostrar os que ficaram
            
            List<Monitor> list = Fachada.getSingleton().recuperarTodosMonitor();
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i).toString());
            }
            }catch(ExceptionErroNoBanco | DadoInexistenteException e){
                System.out.println(e.getLocalizedMessage());
            }
            */ 
                
    }
}
