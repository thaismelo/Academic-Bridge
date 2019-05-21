/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import dados.ExceptionErroNoBanco;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import negocio.Fachada;
import negocio.modelo.Aluno;
import negocio.modelo.BacklogMonitor;
import negocio.modelo.Login;
import negocio.modelo.Professor;

/**
 *
 * @author thais
 */
public class mainBacklogMonitor {
    
    public static void main(String[] args) throws ExceptionErroNoBanco, EmailInvalidoException, NomeInvalidoException  {
        
       Login l = new Login(202, Login.PROFESSOR, "pipoca", "fome");
       Fachada.getSingleton().cadastrarLogin(l);
       Professor p = new Professor(1, 1, 6, "pp", "pp@p");
       Fachada.getSingleton().cadastrarProfessor(p);
       Aluno alu = new Aluno(1, 1, "joao", "tuts@s");
       Fachada.getSingleton().cadastrarAluno(alu);
       BacklogMonitor bk = new BacklogMonitor(1, 1, 1);
       Fachada.getSingleton().cadastrarBacklogMonitor(bk);
    } 
}
