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
import exceptions.entidades.Login.LoginExistenteException;
import exceptions.entidades.Login.LoginNuloException;
import exceptions.entidades.Login.SenhaInvalidaException;
import exceptions.entidades.Login.SenhaNulaException;
import exceptions.entidades.Pessoa.EmailInvalidoException;
import exceptions.entidades.Pessoa.NomeInvalidoException;
import exceptions.entidades.RelatorioMonitoria.ForaDoIntervaloException;
import exceptions.entidades.RelatorioMonitoria.ValorIncorretoException;
import exceptions.entidades.Tarefa.ConteudoNuloException;
import exceptions.entidades.Tarefa.EstadoInvalidoException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fachada.Fachada;
import entidades.Login;
import entidades.Monitor;
import entidades.Professor;
import entidades.RelatorioMonitoria;
import entidades.Tarefa;

/**
 *
 * @author thais
 */
public class MainRelatorioMonitoria {
    public static void main(String[] args) {
        int id = 0;
        
        //Cadastro
      /* 
        try{
            //Cadastrar professor
            Login p = new Login(22, Login.PROFESSOR, "carlosAlberto", "12345678");
            Fachada.getSingleton().cadastrarLogin(p);
            Professor prof = new Professor(14, p, 5,"profcarlos","dgsds@dgsdg.com");
            id = Fachada.getSingleton().recuperaUltimoIdLogin();
            Fachada.getSingleton().cadastrarProfessor(prof);
            //Cadastrar Monitor com seu professor
            Login m = new Login(67, Login.MONITOR, "juvenal", "juju1234");
            Fachada.getSingleton().cadastrarLogin(m);
            Monitor monitor = new Monitor(18, m, prof, "tadeubarros", "tadeu@gmail.com");
            id = Fachada.getSingleton().recuperaUltimoIdLogin();
            id = Fachada.getSingleton().recuperarUltimoIdProfessor();
            Fachada.getSingleton().cadastrarMonitor(monitor);
            
            //Cadastrando Tarefas do monitor
             List<Tarefa> lista = new ArrayList<>();
             
             Tarefa tarefa1 = new Tarefa(12, monitor.getId(), Tarefa.MONITOR, "introduzir", Tarefa.FAZER);
             Fachada.getSingleton().cadastrarTarefa(tarefa1);
             id= Fachada.getSingleton().recuperarUltimoIDTarefa();
             Tarefa t1 = Fachada.getSingleton().recuperarTarefa(id);
             lista.add(t1);
             
             //Monitor fazendo o relatorio da tarefa
             RelatorioMonitoria relatorio1 = new RelatorioMonitoria(15, monitor, tarefa1, 10, RelatorioMonitoria.NÃO, 100,"25/05/2019" );
             id = Fachada.getSingleton().recuperarUltimoIDTarefa();
             id= Fachada.getSingleton().recuperarUltimoIdMonitor();
             Fachada.getSingleton().cadastrarRelatorioMonitoria(relatorio1);
             
             //Nova tarefa
             Tarefa tarefa2 = new Tarefa(13, monitor.getId(), Tarefa.MONITOR, "produzir ativ", Tarefa.FAZER);
             Fachada.getSingleton().cadastrarTarefa(tarefa2);
             id= Fachada.getSingleton().recuperarUltimoIDTarefa();
             Tarefa t2 = Fachada.getSingleton().recuperarTarefa(id);
             lista.add(t2);
             
             //Relatorio da nova monitoria
             
             RelatorioMonitoria relatorio2 = new RelatorioMonitoria(18, monitor, tarefa2, 0, RelatorioMonitoria.NÃO, 100,"25/05/2019" );
             id = Fachada.getSingleton().recuperarUltimoIDTarefa();
             id= Fachada.getSingleton().recuperarUltimoIdMonitor();
             Fachada.getSingleton().cadastrarRelatorioMonitoria(relatorio2);
             
             id= Fachada.getSingleton().recuperarUltimoIdMonitor();
             Monitor monitorCadastrado = Fachada.getSingleton().recuperarMonitor(id);
             
             monitorCadastrado.setTarefas(lista);
             
        }catch(ExceptionErroNoBanco | DadoInexistenteException | DadoNuloException e){
            System.out.println(e.getLocalizedMessage());
        }catch(SenhaInvalidaException | LoginExistenteException | LoginNuloException | SenhaNulaException e){
            System.out.println(e.getLocalizedMessage());
        }catch(NomeInvalidoException | EmailInvalidoException | DisciplinaInexistenteException e){
             System.out.println(e.getLocalizedMessage());
        }catch(ConteudoNuloException | EstadoInvalidoException e){
            System.out.println(e.getLocalizedMessage());
        }catch(ForaDoIntervaloException | ValorIncorretoException e){
            System.out.println(e.getLocalizedMessage());
        }*/  
       
        //Recuperar
      /* 
        try{
            id = Fachada.getSingleton().recuperarUltimoIDRelatorioMonitoria();
            RelatorioMonitoria relatorio = Fachada.getSingleton().recuperarRelatorioMonitoria(id);
            System.out.println(relatorio.toString());
        } catch (ExceptionErroNoBanco ex) {
            Logger.getLogger(MainRelatorioMonitoria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DadoInexistenteException ex) {
            Logger.getLogger(MainRelatorioMonitoria.class.getName()).log(Level.SEVERE, null, ex);
        }*/
      
        //RecuperarTodos    
      /*
        try{
            List<RelatorioMonitoria> list = Fachada.getSingleton().recuperarTodosRelatorioMonitoria();
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i).toString());
            }
        }catch(ExceptionErroNoBanco e){
            System.out.println(e.getLocalizedMessage());
        }*/
      
      
        //Alterar
      /* 
        try{
            id = Fachada.getSingleton().recuperarUltimoIDRelatorioMonitoria();
            RelatorioMonitoria relatorio = Fachada.getSingleton().recuperarRelatorioMonitoria(id);
            System.out.println(relatorio.toString());
            relatorio.setNivelDificuldade(2);
            relatorio.setParticipatividade(71);
            relatorio.setReforcarAssunto(RelatorioMonitoria.SIM);
            Fachada.getSingleton().alterarRelatorioMonitoria(relatorio);
        } catch (ExceptionErroNoBanco | DadoInexistenteException | ForaDoIntervaloException | ValorIncorretoException | DadoNuloException ex) {
            Logger.getLogger(MainRelatorioMonitoria.class.getName()).log(Level.SEVERE, null, ex);
        }*/
      
        //Remover
      /*  
        try{
            id = Fachada.getSingleton().recuperarUltimoIDRelatorioMonitoria();
            RelatorioMonitoria relatorio = Fachada.getSingleton().recuperarRelatorioMonitoria(id);
            Fachada.getSingleton().removerRelatorioMonitoria(relatorio);
            
            //Mostrar os que ficaram
            
            List<RelatorioMonitoria> list = Fachada.getSingleton().recuperarTodosRelatorioMonitoria();
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i).toString());
            }
        } catch (ExceptionErroNoBanco ex) {
            Logger.getLogger(MainRelatorioMonitoria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DadoInexistenteException ex) {
            Logger.getLogger(MainRelatorioMonitoria.class.getName()).log(Level.SEVERE, null, ex);
        }*/
                
    }
}
