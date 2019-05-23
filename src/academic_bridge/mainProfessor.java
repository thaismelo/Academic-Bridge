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
import negocio.modelo.Professor;
import negocio.modelo.Tarefa;

/**
 *
 * @author thais
 */
public class mainProfessor {
    public static void main(String[] args) throws LoginNuloOuExistenteException, SenhaNulaException {
       //CadastrarLogin
        int id = 0;
        /*try{
            Login l = new Login(1, Login.PROFESSOR, "jv", "thais123");
            Fachada.getSingleton().cadastrarLogin(l);
            Professor p = new Professor(12, l, 3, "jose", "js@gmail.com");
            id = Fachada.getSingleton().recuperaUltimoIdLogin();
            p.getLogin().setId(id);
            Fachada.getSingleton().cadastrarProfessor(p);
            System.out.println(p.toString());
        }catch(ExceptionErroNoBanco | SenhaInvalidaException | EmailInvalidoException | NomeInvalidoException e){
            System.out.println(e.getLocalizedMessage());
        }*/
        
        
        //Recuperar
        
        try{
            Professor pop = Fachada.getSingleton().recuperarProfessor(1);
            Login log = Fachada.getSingleton().recuperarLogin(1);
            System.out.println(pop);
            System.out.println(log);
        }catch(ExceptionErroNoBanco e){
            System.out.println(e.getLocalizedMessage());
        }
        
        
        
        
       
    }
    
}
