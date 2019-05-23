/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import exceptions.banco.DadoInexistenteException;
import exceptions.banco.ExceptionErroNoBanco;
import exceptions.entidades.Frequencia.FrequenciaInvalidaException;
import negocio.Fachada;
import negocio.modelo.Frequencia;

/**
 *
 * @author thais
 */
public class MainFrequencia {
    public static void main(String[] args){
        Frequencia f = new Frequencia(12, 1, 2, 1);
        
        //Cadastrar
       /* try{
            Fachada.getSingleton().cadastrarFrequencia(f);
        }catch(ExceptionErroNoBanco | FrequenciaInvalidaException e){
            System.out.println(e.getLocalizedMessage());
        }*/
        
        //Recuperar
        /*try{
            Frequencia frequencia = Fachada.getSingleton().recuperarFrequencia(1);
            System.out.println(frequencia.toString());
        }catch(ExceptionErroNoBanco | DadoInexistenteException e){
            System.out.println(e.getLocalizedMessage());
        }*/
        
        //Alterar
    }
}
