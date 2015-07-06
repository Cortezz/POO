package Eventos;


/**
 * Write a description of class BaseDeDadosEventos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.TreeSet;
import java.util.Iterator;
import java.lang.StringBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;
import Dados.*;

public class BaseDeDadosEventos implements Serializable
{
    //variáveis de instância
    private TreeSet<Evento> eventos;
    //----------------------

    //Construtores
    public BaseDeDadosEventos() {
        this.eventos = new TreeSet<Evento>(new dataFinalInscricao());
    }
    
    public BaseDeDadosEventos (BaseDeDadosEventos x) {
        this.eventos = x.getEventos();
    }
    //----------------------
    
    // Getters
    public TreeSet<Evento> getEventos() {
        return this.eventos;
    }
    //----------------------
    
    // Insere um clone de uma atividade x na lista
    public void insereEvento(Evento x) {
        eventos.add(x.clone());
    }
    //----------------------
    
    public int size(){
        return this.eventos.size();
    }   
    
    public void removeEvento(Evento x){
        eventos.remove(x);
    }
    
    public Evento getEventoIndex(int i){
        Iterator<Evento> it = eventos.iterator();
        Evento evento = null;
        int j = 0;
        
        while(it.hasNext() || j <= i){
            evento = it.next();
           if(j == i){
               return evento;
            } else {
                j++;
            }
        }
        return null;
    }
    
    public String mostraEventos ()
    {
        int i=1;
        StringBuilder o = new StringBuilder();
        Iterator<Evento> it = eventos.iterator();
        while (it.hasNext())
        {
            Evento evento = it.next();
            o.append (i);
            o.append (" - ");
            o.append (evento.getNome());
            o.append (" No dia: "+evento.getData().get(Calendar.DAY_OF_MONTH) + "/" +((evento.getData().get(Calendar.MONTH))+1) + "/" + 
            evento.getData().get(Calendar.YEAR) + "\n");
            i++;
        }
        return o.toString();
    }
    public String mostraEventosInscrito (String email)
    {
        int i=1;
        StringBuilder o = new StringBuilder();

        for (Evento evento: eventos)
        {
            BaseDeDadosUtilizadores novo = evento.getInscritos();
            if (novo.existeUtilizador (email))
            {
                o.append (i);
                o.append (" - ");
                o.append (evento.getNome());
                o.append (" No dia: "+evento.getData().get(Calendar.DAY_OF_MONTH) + "/" +((evento.getData().get(Calendar.MONTH))+1) + "/" + 
                evento.getData().get(Calendar.YEAR) + "\n");
            }
            i++;
        }
        
        return o.toString();
    }
    
    public int sizeEventosInscritoUtilizador(String email){
        int i = 0;
        for (Evento evento: eventos){
            BaseDeDadosUtilizadores novo = evento.getInscritos();
            if (novo.existeUtilizador (email)){
                i++;
            }
        }
        return i;
    }
    
    public int numeroDeEventosInscrito(Utilizador x){
        int i = 0;
        for (Evento evento : eventos){
            if(evento.getInscritos().existeUtilizador(x.getEmail())){
                i++;
            }
        }
        return i;
    }
    
    /**
     * Métodos Complementares 
     */
    public BaseDeDadosEventos clone() {
        return new BaseDeDadosEventos (this);
    }
    
    public String toString()
    {
        StringBuilder output = new StringBuilder("Eventos atuais:\n");
        Iterator <Evento> it = eventos.iterator();
        
        while(it.hasNext()){
            output.append(it.next().getNome() + "\n");
        }
        return output.toString();
    }
    
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o ==null || (this.getClass() != o.getClass())) return false;
        BaseDeDadosEventos x = (BaseDeDadosEventos) o;
        
        return (this.eventos == x.getEventos());
    }
        
}

