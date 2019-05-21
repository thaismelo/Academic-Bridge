/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import dados.ExceptionErroNoBanco;
import dados.implementacoes.RepositorioDisciplina;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import negocio.Fachada;
import negocio.modelo.Aluno;
import negocio.modelo.BacklogMonitor;
import negocio.modelo.Frequencia;
import negocio.modelo.Login;
import negocio.modelo.Planejamento;
import negocio.modelo.Prioridades;
import negocio.modelo.Professor;
import negocio.modelo.Tarefa;

/**
 *
 * @author thais
 */
public class mainProfessor {
    public static void main(String[] args)  {
        
<<<<<<< HEAD:src/academic_bridge/mainProfessor.java
       Login l = new Login(20, Login.PROFESSOR, "pipa", "fome");
       //Fachada.getSingleton().cadastrarLogin(l);
       Professor p = new Professor(12, 2, 15, "thais", "mariamail.com");
       try{
           Fachada.getSingleton().cadastrarProfessor(p);
       }catch(ExceptionErroNoBanco e){
           System.out.println(e.getMessage());
       }catch(NomeInvalidoException | EmailInvalidoException e){
           System.out.println(e.getLocalizedMessage());
       }

=======
       Login l = new Login(202, Login.PROFESSOR, "pipoca", "fome");
       Fachada.getSingleton().cadastrarLogin(l);
       Professor p = new Professor(1, 1, 6, "pp", "pp@p");
       Fachada.getSingleton().cadastrarProfessor(p);
       Aluno alu = new Aluno(1, 1, "joao", "tuts@s");
       Fachada.getSingleton().cadastrarAluno(alu);
       BacklogMonitor bk = new BacklogMonitor(1, 1, 1);
       Fachada.getSingleton().cadastrarBacklogMonitor(bk);
       
>>>>>>> 80377c564a7e15bafec2e7412368d0d2eacf8701:src/academic_bridge/main.java
    }
    
}
