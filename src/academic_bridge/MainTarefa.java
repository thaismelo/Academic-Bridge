/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import dados.implementacoes.RepositorioDisciplina;
import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Disciplina.DisciplinaInexistenteException;
import exceptions.entidades.Login.LoginExistenteException;
import exceptions.entidades.Login.LoginNuloException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import exceptions.entidades.Tarefa.ConteudoNuloException;
import exceptions.entidades.Tarefa.EstadoInvalidoException;
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Login;
import negocio.modelo.Professor;
import negocio.modelo.Tarefa;

/**
 *
 * @author thais
 */
public class MainTarefa {
    public static void main(String[] args) throws ExceptionErroNoBanco{
        
        int id = 0;
        //Cadastrar
        /*try{
           Login log = new Login(92, Login.PROFESSOR, "jujusol", "jujusal11");
           Fachada.getSingleton().cadastrarLogin(log);
           Professor p = new Professor(18, log , 9, "juliana", "jujusol@gmail.com");
           id = Fachada.getSingleton().recuperaUltimoIdLogin();
           Fachada.getSingleton().cadastrarProfessor(p);
           Tarefa ta = new Tarefa(12, p.getId(), Tarefa.PROFESSOR, "resolver ativ", Tarefa.FAZER);
           id = Fachada.getSingleton().recuperarUltimoIdProfessor();
           Fachada.getSingleton().cadastrarTarefa(ta);
           
        }catch(ExceptionErroNoBanco  e){
            System.out.println(e.getLocalizedMessage());
         }catch(ConteudoNuloException | EstadoInvalidoException e){
             System.out.println(e.getLocalizedMessage());
         }catch(SenhaInvalidaException | LoginExistenteException | SenhaNulaException | LoginNuloException | DadoInexistenteException e){
             System.out.println(e.getLocalizedMessage());
         }catch(EmailInvalidoException | NomeInvalidoException | DadoNuloException| DisciplinaInexistenteException e){
             System.out.println();
         }*/
        
        //RecuperarUlitmoID
        /*
        try{
            id = Fachada.getSingleton().recuperarUltimoIDTarefa();
            System.out.println(id);
        }catch(ExceptionErroNoBanco e){
            System.out.println(e.getLocalizedMessage());
        }*/
        
        //Recuperar
        /*try{
            id = Fachada.getSingleton().recuperarUltimoIDTarefa();
            Tarefa a = Fachada.getSingleton().recuperarTarefa(id);
            System.out.println(a);
        }catch(DadoInexistenteException | ExceptionErroNoBanco e){
            System.out.println(e.getLocalizedMessage());
        }*/
        
        
        
        //Alterar 
     /*   
        try{
            Fachada.getSingleton().alterarTarefa(tarefa);
        }catch(ExceptionErroNoBanco | DadoNuloException | ConteudoNuloException | EstadoInvalidoException e){
            System.out.println(e.getLocalizedMessage());
        }
        
        //RecuperarTodos
        
        /*try{
            List<Tarefa> tarefas = Fachada.getSingleton().recuperarTodosTarefa();
            for(int i=0;i<tarefas.size();i++){
                System.out.println(tarefas.get(i).toString());
            }
            }catch(ExceptionErroNoBanco e){
                System.out.println(e.getLocalizedMessage()); 
            }*/
        
        //Remover
        
       /* try{
            Fachada.getSingleton().removerTarefa(tarefa);
        }catch(ExceptionErroNoBanco | DadoNuloException e){
            System.out.println(e.getLocalizedMessage());
        }*/
        
    }
}
