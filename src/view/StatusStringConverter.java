/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;

/**
 *
 * @author thais
 */
public class StatusStringConverter extends StringConverter<Integer>{

     final public static ObservableList<Integer> OPTIONS = FXCollections.observableArrayList(1, 2);
    @Override
    public String toString(Integer object) {
         if(object==1){
             return "SIM";
         }
         return "NÃO";
    }

    @Override
    public Integer fromString(String string) {
        return null;
    }
    
}
