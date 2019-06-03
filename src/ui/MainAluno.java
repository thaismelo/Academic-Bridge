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
import java.util.List;
import fachada.Fachada;
import entidades.Aluno;
import entidades.Login;
import entidades.Monitor;
import entidades.Professor;

/**
 *
 * @author thais
 */
public class MainAluno {
     public static void main(String[] args) {
          int id = 0;
         //Cadastrar Monitor para ele poder cadastrar seus alunos
        /*
         try{
             //Cadastrar professor
            Login p = new Login(22, Login.PROFESSOR, "carloslberto", "12345678");
            Fachada.getSingleton().cadastrarLogin(p);
            Professor prof = new Professor(14, p, 5,"profcarlos","gsds@dgsdg.com");
            id = Fachada.getSingleton().recuperaUltimoIdLogin();
            Fachada.getSingleton().cadastrarProfessor(prof);
          
            //Cadastrar Monitor com seu professor
            Login m = new Login(67, Login.MONITOR, "juvenal", "juju1234");
            Fachada.getSingleton().cadastrarLogin(m);
            Monitor monitor = new Monitor(18, m, prof, "tadbarros", "tadu@gmail.com");
            id = Fachada.getSingleton().recuperaUltimoIdLogin();
            id=Fachada.getSingleton().recuperarUltimoIdProfessor();
            Fachada.getSingleton().cadastrarMonitor(monitor);
            
            Aluno a1 = new Aluno(11, monitor, "josse", "josse@gmail.com");
            id=Fachada.getSingleton().recuperarUltimoIdMonitor();
            Fachada.getSingleton().cadastrarAluno(a1);
            
            
            Aluno a2 = new Aluno(11, monitor, "mariajuse", "mama@gmail.com");
            id=Fachada.getSingleton().recuperarUltimoIdMonitor();
            Fachada.getSingleton().cadastrarAluno(a2);
            
         }catch(ExceptionErroNoBanco | DadoInexistenteException | DadoNuloException e){
            System.out.println(e.getLocalizedMessage());
        }catch(SenhaInvalidaException | LoginExistenteException | LoginNuloException | SenhaNulaException e){
            System.out.println(e.getLocalizedMessage());
        }catch(NomeInvalidoException | EmailInvalidoException | DisciplinaInexistenteException e){
             System.out.println(e.getLocalizedMessage());
        }*/
          
        
       //Recuperar ultimo Aluno cadastrado
          
        /* try{
            id= Fachada.getSingleton().recuperarUltimoIdAluno();
            Aluno a = Fachada.getSingleton().recuperarAluno(id);
            System.out.println(a);
        }catch(ExceptionErroNoBanco | DadoInexistenteException e){
            System.out.println(e.getLocalizedMessage());
        }*/
          
       //Alterar aluno
          
        /*try{
            Login p = new Login(3, Login.PROFESSOR, "carlosAlberto", "12345678");
            Professor prof = new Professor(14, p, 5,"profcarlos","dgsds@dgsdg.com");
            Login m = new Login(4, Login.MONITOR, "juvenal", "juju1234");
            Monitor monitor = new Monitor(2, m, prof, "tadeubarros", "tadeu@gmail.com");
            Aluno a2 =  new Aluno(2, monitor, "mariajuse da silva", "mama@gmail.com");
            Fachada.getSingleton().alterarAluno(a2);
            
            //Mostrar
            //executa o Recuperar aluno
        }catch(ExceptionErroNoBanco | DadoInexistenteException | DadoNuloException e){
              System.out.println(e.getLocalizedMessage());
        }catch(SenhaInvalidaException e){
            System.out.println(e.getLocalizedMessage());
        }catch(NomeInvalidoException | EmailInvalidoException e){
             System.out.println(e.getLocalizedMessage());
        }*/
          
          
        //RecuperarTodos
          
        /*try{
            List<Aluno> list = Fachada.getSingleton().recuperarTodosAluno();
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i).toString());
            }
            }catch(ExceptionErroNoBanco e){
                System.out.println(e.getLocalizedMessage());
            }*/
          
        //Remover Aluno
          /*try{
            id= Fachada.getSingleton().recuperarUltimoIdAluno();
            Aluno a = Fachada.getSingleton().recuperarAluno(id);
            Fachada.getSingleton().removerAluno(a);
            
            //Mostrar os que ficaram
            
            List<Aluno> list = Fachada.getSingleton().recuperarTodosAluno();
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i).toString());
            }
            }catch(ExceptionErroNoBanco | DadoInexistenteException e){
                System.out.println(e.getLocalizedMessage());
            }*/
          
     }
}
