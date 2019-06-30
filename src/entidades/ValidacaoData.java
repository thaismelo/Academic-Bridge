/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Guilherme
 */
public class ValidacaoData {
    public boolean data(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(data);
            return dataMenorQueHoje(data);
        } catch (ParseException ex) {          
            return false;
        }
    }
    
    public boolean dataMenorQueHoje(String data) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataVerificada = LocalDate.parse(data, dtf);
        LocalDate hoje = LocalDate.now();
        return dataVerificada.compareTo(hoje) > 0;
    }
}
