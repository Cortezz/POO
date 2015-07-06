package Eventos;


/**
 * Write a description of class ComparaData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Comparator;
import java.io.Serializable;
import Eventos.*;

public class dataFinalInscricao implements Comparator<Evento>, Serializable {
    public int compare(Evento x, Evento y){
        if (x.equals(y)){
            return 0;
        }else if (x.getDataLimiteInscricao().compareTo(y.getDataLimiteInscricao()) > 0) {
                return 1;
              }else return -1;
    }
}
