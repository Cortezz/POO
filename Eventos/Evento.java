package Eventos;


/**
 * Write a description of class Evento here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import Actividades.*;
import Dados.*;
import java.util.GregorianCalendar;
import java.lang.StringBuilder;
import java.io.Serializable;

public abstract class Evento implements Serializable
{
    // variáveis de instância
    private GregorianCalendar data;
    private BaseDeDadosUtilizadores inscritos;
    private int numeroMaximoInscricoes;
    private GregorianCalendar dataLimiteInscricao;
    private String descricao;
    //-----------------------
    
    /**
     * Construtores
     */
    public Evento() {
        this.data = new GregorianCalendar();
        this.inscritos = new BaseDeDadosUtilizadores();
        this.numeroMaximoInscricoes = 0;
        this.dataLimiteInscricao = new GregorianCalendar();
        this.descricao = new String();
    }
    
    public Evento(GregorianCalendar data,BaseDeDadosUtilizadores inscritos,
                  int numeroMaximoInscricoes, GregorianCalendar dataLimiteInscricao, String descricao)
                  {
        this.data = (GregorianCalendar) data.clone();
        this.inscritos = inscritos.clone();
        this.numeroMaximoInscricoes = numeroMaximoInscricoes;
        this.dataLimiteInscricao = (GregorianCalendar) dataLimiteInscricao.clone();
        this.descricao = descricao;
                      
    }
    
    public Evento (Evento x){
        this.data = x.getData();
        this.inscritos = x.getInscritos();
        this.numeroMaximoInscricoes = x.getNumeroMaximoInscricoes();
        this.dataLimiteInscricao = x.getDataLimiteInscricao();
        this.descricao = x.getDescricao();
    }
    
    /**
     * Getters
     */
    /*public String getNome(){
        return this.nome;
    }
    
    public Atividade getAtividade(){
        return this.atividade.clone();
    }*/
    
    public GregorianCalendar getData(){
        return (GregorianCalendar) this.data.clone();
    }
    
    public BaseDeDadosUtilizadores getInscritos(){
        return this.inscritos;
    }
    
    public int getNumeroMaximoInscricoes(){
        return this.numeroMaximoInscricoes;
    }
    
    public GregorianCalendar getDataLimiteInscricao(){
        return (GregorianCalendar)this.dataLimiteInscricao.clone();
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    /**
     * métodos principais
     */
    public int getSizeInscritos(){
        return inscritos.getSize();
    }
    
    public void inscreveUtilizador(Utilizador x) throws UtilizadorJaExiste{
        inscritos.insereUtilizador(x);
    }
    
    public void desinscreveUtilizador(Utilizador x){
        inscritos.removeUtilizador(x);
    }
    
    public abstract String getNome();
    /**
     * Métodos Complementares
     */
    public abstract Evento clone();
    
    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append(inscritos.toString());
        output.append("Data de realização: " + this.data.get(GregorianCalendar.DAY_OF_MONTH) + "/" +
                                               ((this.data.get(GregorianCalendar.MONTH))+1) + "/" +
                                               this.data.get(GregorianCalendar.YEAR) + "\n");
        output.append("Número de inscritos: " + this.getSizeInscritos() + " de " + this.numeroMaximoInscricoes + "\n"); 
        output.append("Data limite de inscrição: " + this.dataLimiteInscricao.get(GregorianCalendar.DAY_OF_MONTH) + "/" +
                                                ((this.dataLimiteInscricao.get(GregorianCalendar.MONTH))+1) + "/" +
                                                this.dataLimiteInscricao.get(GregorianCalendar.YEAR) + "\n");
        output.append("Descrição: " + this.getDescricao() + "\n");
        
        return output.toString();
    }
    
    public boolean equals(Object x){
        if (this == x) return true;
        if (x==null || (this.getClass() != x.getClass())) return false;
        Evento evento = (Evento) x;
        
        return (this.getData() == evento.getData() &&
        this.getInscritos() == evento.getInscritos() &&
        this.getNumeroMaximoInscricoes() == evento.getNumeroMaximoInscricoes() &&
        this.getDataLimiteInscricao() == evento.getDataLimiteInscricao() &&
        this.getDescricao().equals(evento.getDescricao())
        );
    
    }
}
