/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import exceptions.banco.ExceptionErroNoBanco;
import dados.implementacoes.RepositorioDisciplina;
import exceptions.banco.DadoInexistenteException;
import exceptions.entidades.Login.LoginNuloOuExistenteException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import negocio.Fachada;
import negocio.modelo.Aluno;
import negocio.modelo.Frequencia;
import negocio.modelo.Login;
import negocio.modelo.Planejamento;
import negocio.modelo.Professor;
import negocio.modelo.Tarefa;

/**
 *
 * @author thais
 */
public class mainProfessor {
    public static void main(String[] args) {
       //CadastrarLogin
        try{
            Login l = new Login(13, Login.PROFESSOR, "jv", "thais123");
            Fachada.getSingleton().cadastrarLogin(l);
        }catch(ExceptionErroNoBanco | SenhaInvalidaException | SenhaNulaException | LoginNuloOuExistenteException e){
            System.out.println(e.getLocalizedMessage());
        }
    }
    
}
