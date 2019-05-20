/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import dados.ExceptionErroNoBanco;
import dados.implementacoes.RepositorioDisciplina;
import negocio.Fachada;
import negocio.modelo.Frequencia;
import negocio.modelo.Login;
import negocio.modelo.Planejamento;
import negocio.modelo.Prioridades;
import negocio.modelo.Professor;
import negocio.modelo.Tarefa;
import negocio.modelo.Turma;

/**
 *
 * @author thais
 */
public class main {
    public static void main(String[] args) throws ExceptionErroNoBanco {
        
       Tarefa t = new Tarefa(1, "tuts tutst quero ver", 0);
       Fachada.getSingleton().cadastrarTarefa(t);
    }
    
}
