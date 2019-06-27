/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import exceptions.banco.ExceptionErroNoBanco;
import repositorios.implementacoes.banco.RepositorioDisciplina;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fachada.Fachada;
import entidades.Aluno;
import entidades.Frequencia;
import entidades.Login;
import entidades.Professor;
import entidades.Tarefa;
import entidades.TarefaParaMonitor;

/**
 *
 * @author thais
 */
public class mainProfessor {
    public static void main(String[] args) throws ExceptionErroNoBanco {
       //Cadastrar Professor (inicia com lista de tarefas vazias)
        int id = 0;
      /* 
        try{
            Login l = new Login(1, Login.PROFESSOR, "jusue1", "jusuem123");
            Fachada.getSingleton().cadastrarLogin(l);
            
            Professor p = new Professor(12, l, 5, "josue", "jusue@gmail.com");
            
            //Recupera o ultimo id adicionado do login e ja setado.
            id = Fachada.getSingleton().recuperaUltimoIdLogin();

            Fachada.getSingleton().cadastrarProfessor(p);
            
            System.out.println(p.toString());
        }catch(ExceptionErroNoBanco | NomeInvalidoException | DisciplinaInexistenteException| DadoNuloException e){
            System.out.println(e.getLocalizedMessage());
        }catch(EmailInvalidoException | LoginExistenteException | SenhaNulaException | SenhaInvalidaException | DadoInexistenteException | LoginNuloException e){
            System.out.println(e.getMessage());
        }*/
        
        
         //Professor ja cadastrado, cadastrando tarefas
      /*
        try{
            Tarefa  t = new Tarefa(1,1,1,"teste", 1);
            Fachada.getSingleton().cadastrarTarefa(t);
            TarefaParaMonitor tm = new TarefaParaMonitor(1, 1, 1, t, "22/10/18");
            Fachada.getSingleton().cadastrarTarefaParaMonitor(tm);
        
            Tarefa  t2 = new Tarefa(2,1,1, "teste2", 1);
            Fachada.getSingleton().cadastrarTarefa(t2);
            TarefaParaMonitor tm2 = new TarefaParaMonitor(1, 1, 1, t2, "22/10/18");
            Fachada.getSingleton().cadastrarTarefaParaMonitor(tm2);
        
            id = Fachada.getSingleton().recuperarUltimoIdProfessor();
            Professor pop = Fachada.getSingleton().recuperarProfessor(id);
            pop.setTarefas(Fachada.getSingleton().recuperarTodosPorCodProfTarefaParaMonitor(pop.getId()));
            System.out.println(pop.toString());
           
        }catch(ExceptionErroNoBanco | ConteudoNuloException | EstadoInvalidoException  e){
            System.out.println(e.getLocalizedMessage());
        }catch (DadoNuloException | DadoInexistenteException ex){
            Logger.getLogger(mainProfessor.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
        //Recuperar
      /*
        try{
            Professor pop = Fachada.getSingleton().recuperarProfessor(1);
            System.out.println(pop);
        }catch(ExceptionErroNoBanco | DadoInexistenteException e){
            System.out.println(e.getLocalizedMessage());
        }*/
        
        //Alterar
        
        /*try{
            Login l = new Login(1, Login.PROFESSOR, "jusue1", "jusuem123");
            Professor p = new Professor(1, l, 5, "josue de almeida", "jusue@gmail.com");
            Fachada.getSingleton().alterarProfessor(p);
            
            Professor pop = Fachada.getSingleton().recuperarProfessor(1);
            System.out.println(pop.toString());
            
        }catch(ExceptionErroNoBanco | DadoInexistenteException | DadoNuloException  | EmailInvalidoException | NomeInvalidoException | DisciplinaInexistenteException e){
            System.out.println(e.getLocalizedMessage());
        }catch(SenhaInvalidaException e){
            System.out.println(e.getMessage());
        }*/
        
        
        //RecuperarTodos
        
        /*try{
            List<Professor> list = Fachada.getSingleton().recuperarTodosProfessor();
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i).toString());
            }
            }catch(ExceptionErroNoBanco e){
                System.out.println(e.getLocalizedMessage());
            }
          */
        
        //Remover
        /*try{
            id= Fachada.getSingleton().recuperarUltimoIdProfessor();
            Professor prof = Fachada.getSingleton().recuperarProfessor(id);
            Fachada.getSingleton().removerProfessor(prof);
            
            //Mostrar os que ficaram
            
            List<Professor> list = Fachada.getSingleton().recuperarTodosProfessor();
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i).toString());
            }
            }catch(ExceptionErroNoBanco | DadoInexistenteException | DadoNuloException e){
                System.out.println(e.getLocalizedMessage());
            }*/

    
    }
}
