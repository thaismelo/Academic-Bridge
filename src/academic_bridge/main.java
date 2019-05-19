/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import dados.DAO_SQLite;
import dados.ExceptionErroNoBanco;
import dados.implementacoes.RepositorioDisciplina;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import negocio.Fachada;
import negocio.modelo.Disciplina;
import negocio.modelo.GeradorDeSenha;
import negocio.modelo.Login;
import negocio.modelo.Prioridades;
import negocio.modelo.Professor;

/**
 *
 * @author thais
 */
public class main {

    public static void main(String[] args) throws ExceptionErroNoBanco {
       RepositorioDisciplina rep = new RepositorioDisciplina();
       Login login = new Login(2, Login.PROFESSOR, "Pat", "55");
        
        try {
            //Fachada.getSingleton().cadastrarLogin(login);
            //Professor p = new Professor(8, 7, 20, "coco", "kl");
            //Fachada.getSingleton().cadastrarProfessor(p);
            Prioridades p = new Prioridades(12,3, "aplicar solucao");
            Fachada.getSingleton().cadastrarPrioridades(p);
        } catch (ExceptionErroNoBanco e) {
            System.out.println("Ocorreu um erro no acesso ao banco");
            System.out.println(e.getMessage());
        }
    }
}
