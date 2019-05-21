/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import dados.ExceptionErroNoBanco;
import dados.implementacoes.RepositorioDisciplina;
import negocio.Fachada;
import negocio.modelo.Aluno;
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
public class main {
    public static void main(String[] args) throws ExceptionErroNoBanco {
        
       Login l = new Login(202, Login.PROFESSOR, "pipoca", "fome");
       Fachada.getSingleton().cadastrarLogin(l);
       Professor p = new Professor(1, 1, 6, "pp", "pp@p");
       Fachada.getSingleton().cadastrarProfessor(p);
       Aluno alu = new Aluno(1, 1, "joao", "tuts@s");
       Fachada.getSingleton().cadastrarAluno(alu);
    }
    
}
