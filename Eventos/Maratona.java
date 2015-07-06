package Eventos;


/**
 * Write a description of class MeiaMaratona here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.GregorianCalendar;
import Dados.*;

public class Maratona extends Evento
{
    private String nome;
    private static final String atividade = "Corrida";
    private static final double requesito = 42.195 ;
    
    
    /**
     * Construtores
     */
    public Maratona(){
        super();
        this.nome = "";
    }
    
    public Maratona(String nome, GregorianCalendar data,BaseDeDadosUtilizadores inscritos,
                  int numeroMaximoInscricoes, GregorianCalendar dataLimiteInscricao, String descricao){
        super(data, inscritos, numeroMaximoInscricoes, dataLimiteInscricao, descricao);
        this.nome = (nome);
    }
    
    public Maratona(Maratona x){
        super(x.getData(), x.getInscritos(), x.getNumeroMaximoInscricoes(), x.getDataLimiteInscricao(),
        x.getDescricao());
        this.nome = (x.getNome());
        
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
    
    public Maratona clone(){
        return new Maratona (this);
    }
    
    public boolean equals(Object x)
    {
        
        if (this == x) return true;
        if (x==null || (this.getClass() != x.getClass())) return false;
        Maratona maratona = (Maratona) x;
               
        return (super.equals(x) &&
                this.getNome().equals(maratona.getNome()) 
               );
    }
}

