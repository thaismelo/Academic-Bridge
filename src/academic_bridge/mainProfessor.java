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
    public static void main(String[] args) throws SenhaInvalidaException, SenhaNulaException  {
        

       Professor p = new Professor(12, 2, 15, "thais", "mariamail.com");
       try{
           Login l = new Login(2, Login.PROFESSOR, "jose", "fomefome");
           Fachada.getSingleton().removerLogin(l);
           //Fachada.getSingleton().cadastrarProfessor(p);
       }catch(ExceptionErroNoBanco e){
           System.out.println(e.getMessage());
       //}catch(NomeInvalidoException | EmailInvalidoException e){
           //System.out.println(e.getLocalizedMessage());
       }catch(SenhaInvalidaException | DadoInexistenteException e){
           System.out.println(e.getLocalizedMessage());
       }
    }
    
}
