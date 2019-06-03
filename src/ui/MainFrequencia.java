/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Disciplina.DisciplinaInexistenteException;
import exceptions.entidades.Frequencia.FrequenciaInvalidaException;
import exceptions.entidades.Login.LoginExistenteException;
import exceptions.entidades.Login.LoginNuloException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fachada.Fachada;
import entidades.Aluno;
import entidades.Frequencia;
import entidades.Login;
import entidades.Monitor;
import entidades.Professor;

/**
 *
 * @author thais
 */
public class MainFrequencia {
    public static void main(String[] args){
        int id = 0;
        //Cadastrar
      /* 
        try{
             //Cadastrar professor
            Login p = new Login(22, Login.PROFESSOR, "carlosNaoAlberto", "12345678");
            Fachada.getSingleton().cadastrarLogin(p);
            Professor prof = new Professor(14, p, 5,"profcarlosN","dgsdss@dgsdg.com");
            id = Fachada.getSingleton().recuperaUltimoIdLogin();
            Fachada.getSingleton().cadastrarProfessor(prof);
            //Cadastrar Monitor com seu professor
            Login m = new Login(67, Login.MONITOR, "juvenfal", "juju1234");
            Fachada.getSingleton().cadastrarLogin(m);
            Monitor monitor = new Monitor(18, m, prof, "tadeuNaobarros", "tadeuN@gmail.com");
            id = Fachada.getSingleton().recuperaUltimoIdLogin();
            id=Fachada.getSingleton().recuperarUltimoIdProfessor();
            Fachada.getSingleton().cadastrarMonitor(monitor);
            
            Aluno a1 = new Aluno(11, monitor, "josse", "josse@gmail.com");
            id=Fachada.getSingleton().recuperarUltimoIdMonitor();
            Fachada.getSingleton().cadastrarAluno(a1);
            
            Frequencia f = new Frequencia(12, a1, monitor, 1);
            Fachada.getSingleton().cadastrarFrequencia(f);
        }catch(ExceptionErroNoBanco e){
            System.out.println(e.getLocalizedMessage());
        } catch (SenhaInvalidaException | LoginExistenteException | SenhaNulaException | DadoInexistenteException | LoginNuloException | NomeInvalidoException | EmailInvalidoException | DadoNuloException | DisciplinaInexistenteException | FrequenciaInvalidaException ex) {
            Logger.getLogger(MainFrequencia.class.getName()).log(Level.SEVERE, null, ex);
        }*/
      
        //Recuperar
      /*
        try{
            id = Fachada.getSingleton().recuperarUltimoIdFrequencia();
            Frequencia frequencia = Fachada.getSingleton().recuperarFrequencia(id);
            System.out.println(frequencia.toString());
        }catch(ExceptionErroNoBanco | DadoInexistenteException e){
            System.out.println(e.getLocalizedMessage());
        }*/
        
        //RecuperarTodos    
      /*
        try{
            List<Frequencia> list = Fachada.getSingleton().recuperarTodosFrequencia();
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i).toString());
            }
        }catch(ExceptionErroNoBanco e){
            System.out.println(e.getLocalizedMessage());
        }/*
        
        //Alterar
      /*  
        try{
            id = Fachada.getSingleton().recuperarUltimoIdFrequencia();
            Frequencia frequencia = Fachada.getSingleton().recuperarFrequencia(id);
            frequencia.setFrequencia(2);
            Fachada.getSingleton().alterarFrequencia(frequencia);
        }catch(ExceptionErroNoBanco | DadoInexistenteException e){
            System.out.println(e.getLocalizedMessage());
        } catch (FrequenciaInvalidaException | DadoNuloException ex) {
            Logger.getLogger(MainFrequencia.class.getName()).log(Level.SEVERE, null, ex);
        }*/
      
        //Remover
      /*  
        try{
            id = Fachada.getSingleton().recuperarUltimoIdFrequencia();
            Frequencia frequencia = Fachada.getSingleton().recuperarFrequencia(id);
            Fachada.getSingleton().removerFrequencia(frequencia);
        } catch (ExceptionErroNoBanco | DadoInexistenteException ex) {
            Logger.getLogger(MainFrequencia.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
