/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic.bridge;

import dao_conexoes.ProfessorDAO;
import entidades.Pessoa;
import entidades.Professor;

/**
 *
 * @author thais
 */
public class TesteInsere {
    public static void main(String[] args) {

          // pronto para gravar
          Professor professor = new Professor();
          professor.setNome("cacau");
          professor.setEmail("cacau@hotmail.com");
          ProfessorDAO con = new ProfessorDAO();
          con.cadastrarProfessor(professor);
          con.listarProfessores();
    }
}
