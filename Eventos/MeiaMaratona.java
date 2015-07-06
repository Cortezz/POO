package Eventos;


/**
 * Write a description of class MeiaMeiaMaratona here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.GregorianCalendar;
import Dados.*;

public class MeiaMaratona extends Evento
{
    private String nome;
    private static final String atividade = "Meia Maratona";
    private static final double requesito = 21.097 ;
    
    
    /**
     * Construtores
     */
    public MeiaMaratona(){
        super();
        this.nome = new String();
    }
    
    public MeiaMaratona(String nome, GregorianCalendar data,BaseDeDadosUtilizadores inscritos,
                  int numeroMaximoInscricoes, GregorianCalendar dataLimiteInscricao, String descricao){
        super(data, inscritos, numeroMaximoInscricoes, dataLimiteInscricao, descricao);
        this.nome = nome;
    }
    
    public MeiaMaratona(MeiaMaratona x){
        super(x.getData(), x.getInscritos(), x.getNumeroMaximoInscricoes(), x.getDataLimiteInscricao(),
        x.getDescricao());
        this.nome = x.getNome();
        
    }
    
    /**
     * Getters
     */
    public String getNome(){
        return this.nome;
    }
    
    /**
     * Métodos Complementares
     */
    
    public String toString(){
        StringBuilder output = new StringBuilder("Nome: " + this.getNome() + "\n");
        output.append("Atividade: "+ atividade + "\n");
        output.append("Requisito mínimo: já ter percorrido "+ requesito + "kms de corrida.\n");
        output.append(super.toString());
        
        return output.toString();
    }
    
    public MeiaMaratona clone(){
        return new MeiaMaratona (this);
    }
    
    public boolean equals(Object x)
    {
        
        if (this == x) return true;
        if (x==null || (this.getClass() != x.getClass())) return false;
        MeiaMaratona meiaMaratona = (MeiaMaratona) x;
        
        System.out.println(super.equals(x) &&
               this.getNome().equals(meiaMaratona.getNome()));
               
        return (super.equals(x) &&
               this.getNome().equals(meiaMaratona.getNome())
                );
    }
}