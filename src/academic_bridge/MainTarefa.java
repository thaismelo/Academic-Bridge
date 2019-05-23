/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import exceptions.banco.DadoInexistenteException;
import exceptions.banco.DadoNuloException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Tarefa.ConteudoNuloException;
import exceptions.entidades.Tarefa.EstadoInvalidoException;
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Tarefa;

/**
 *
 * @author thais
 */
public class MainTarefa {
    public static void main(String[] args){
        
        Tarefa tarefa = new Tarefa(21, "blabla", 1);

        //Cadastrar
        /*try{
           Fachada.getSingleton().cadastrarTarefa(tarefa);
        }catch(ExceptionErroNoBanco  e){
            System.out.println(e.getLocalizedMessage());
         }catch(ConteudoNuloException | EstadoInvalidoException e){
             System.out.println(e.getLocalizedMessage());
         }*/
        
        //RecuperarUlitmoID
        int id = 0;
        try{
            id = Fachada.getSingleton().recuperarUltimoIDTarefa();
            System.out.println(id);
        }catch(ExceptionErroNoBanco e){
            System.out.println(e.getLocalizedMessage());
        }
        
        
        
        //Recuperar 
        /*try{
            Tarefa ta = Fachada.getSingleton().recuperarTarefa(1);
            System.out.println(ta.toString());
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
