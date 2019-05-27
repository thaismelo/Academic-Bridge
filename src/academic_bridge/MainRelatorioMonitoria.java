/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

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
import java.util.List;
import negocio.Fachada;
import negocio.modelo.Login;
import negocio.modelo.Monitor;
import negocio.modelo.Professor;
import negocio.modelo.RelatorioMonitoria;
import negocio.modelo.Tarefa;

/**
 *
 * @author thais
 */
public class MainRelatorioMonitoria {
    public static void main(String[] args) {
        int id = 0;
        
        //Cadastrando monitor
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
            id=Fachada.getSingleton().recuperarUltimoIdProfessor();
            Fachada.getSingleton().cadastrarMonitor(monitor);
            
            //Cadastrando Tarefas do monitor
             List<Tarefa> lista = null;
             
             Tarefa tarefa1 = new Tarefa(12, m.getId(), Tarefa.MONITOR, "introduzir", Tarefa.FAZER);
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
             Tarefa tarefa2 = new Tarefa(13, m.getId(), Tarefa.MONITOR, "produzir ativ", Tarefa.FAZER);
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
        }  
       
    }
}
