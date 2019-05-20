/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import dados.ExceptionErroNoBanco;
import dados.implementacoes.RepositorioDisciplina;
import negocio.Fachada;
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
        
        RepositorioDisciplina rep = new RepositorioDisciplina();
        Login prof = new Login(07, Login.PROFESSOR,"juvents", "juju");
        
        Professor juvents = new Professor(1, 10, 14, "juvents de costa", "juju@melo");
        
        Tarefa ta = new Tarefa(12, "fazer exercicios", Tarefa.EM_PROGRESSO);
        
        Fachada.getSingleton().cadastrarTarefa(ta);
        
        
        
    }
    
}
