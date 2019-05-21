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
    }
    
}
