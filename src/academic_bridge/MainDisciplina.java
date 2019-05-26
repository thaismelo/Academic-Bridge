/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_bridge;

import dados.implementacoes.RepositorioDisciplina;
import exceptions.banco.ExceptionErroNoBanco;
import negocio.Fachada;

/**
 *
 * @author thais
 */
public class MainDisciplina {
    public static void main(String[] args) throws ExceptionErroNoBanco  {
        RepositorioDisciplina rep = new RepositorioDisciplina();
        rep.inserirDisciplinas();
        int id;
        id = rep.recuperarUltimoID();
        System.out.println(id);
    }
}
