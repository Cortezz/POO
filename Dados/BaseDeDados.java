package Dados;

import java.io.*;
import Eventos.*;
import Actividades.*;

public class BaseDeDados implements Serializable{    private BaseDeDadosEventos eventos;
    private BaseDeDadosUtilizadores utilizadores;
    

    /**
     * Construtores
     */
    public BaseDeDados() {
        this.utilizadores = new BaseDeDadosUtilizadores();
        this.eventos = new BaseDeDadosEventos();
    }
    
    public BaseDeDados(BaseDeDadosUtilizadores utilizadores, BaseDeDadosEventos eventos) {
        this.utilizadores = utilizadores;
        this.eventos = eventos;
    }
    
    public BaseDeDados(BaseDeDados x){
        this.utilizadores = x.getUtilizadores();
        this.eventos = x.getEventos();
    }

    /*
     * Getters 
     */
    public BaseDeDadosUtilizadores getUtilizadores() {
        return utilizadores;
    }

    public BaseDeDadosEventos getEventos() {
        return eventos;
    }

    /*
     * Setters 
     */
    public void setUtilizadores(BaseDeDadosUtilizadores utilizadores) {
        this.utilizadores = utilizadores;
    }

    public void setEventos(BaseDeDadosEventos eventos) {
        this.eventos = eventos.clone();
    }

    /*
    m??todos principais
     */
    public void insereUtilizador (Utilizador x) throws UtilizadorJaExiste
    {
            utilizadores.insereUtilizador(x);
    }
    
    public void insereEvento ( Evento x){
        eventos.insereEvento(x);
    }
    
    public void gravaEstado(String fich) throws IOException 
    {
        ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream(fich));
        oos.writeObject(this);
        oos.flush(); oos.close();
    }


   
    public BaseDeDados clone() {
        return new BaseDeDados(this); 
    } 
}