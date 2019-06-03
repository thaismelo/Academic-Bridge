/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import repositorios.implementacoes.banco.RepositorioDisciplina;
import exceptions.banco.ExceptionErroNoBanco;
import java.util.List;
import fachada.Fachada;
import entidades.Disciplina;

/**
 *
 * @author thais
 */
public class MainDisciplina {
    public static void main(String[] args) throws ExceptionErroNoBanco  {
        RepositorioDisciplina rep = new RepositorioDisciplina();
        rep.inserirDisciplinas();
        
        //Recupera o ultimo id inserido
        /*int id;
        id = rep.recuperarUltimoID();
        System.out.println(id);*/
        
        
        
        //Mostrar todas as disciplinas
        /*List<Disciplina> lista = rep.recuperarTodos();
        
        for(int i = 0; i<lista.size();i++){
            System.out.println(lista.get(i).toString());
        }*/
        
        
        //Recuperar uma disciplina
        
        /*Disciplina d = rep.recuperar(5);
        System.out.println(d.toString());
        */
    }
}
