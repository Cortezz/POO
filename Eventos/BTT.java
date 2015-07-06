package Eventos;


/**
 * Write a description of class MeiaBTT here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.GregorianCalendar;
import Dados.*;

public class BTT extends Evento
{
    private String nome;
    private static final String atividade = "BTT";
    private double requesito;
    
    
    /**
     * Construtores
     */
    public BTT(){
        super();
        this.nome = "";
        this.requesito = 0;
    }
    
    public BTT(String nome, GregorianCalendar data,BaseDeDadosUtilizadores inscritos,
                  int numeroMaximoInscricoes, GregorianCalendar dataLimiteInscricao, String descricao,
                  double requesito){
        super(data, inscritos, numeroMaximoInscricoes, dataLimiteInscricao, descricao);
        this.nome = (nome);
        this.requesito = requesito;
    }
    
    public BTT(BTT x){
        super(x.getData(), x.getInscritos(), x.getNumeroMaximoInscricoes(), x.getDataLimiteInscricao(),
        x.getDescricao());
        this.nome = (x.getNome());
        this.requesito = x.getRequesito();
        
    }
    
    /**
     * Getters
     */
    public String getNome(){
        return this.nome;
    }
    
    public double getRequesito(){
        return this.requesito;
    }
    /**
     * Métodos Complementares
     */
    
    public String toString(){
        StringBuilder output = new StringBuilder("Nome: " + this.getNome() + "\n");
        output.append("Atividade: "+ atividade + "\n");
        output.append("Requisito mínimo: já ter percorrido "+ requesito + "kms.\n");
        output.append(super.toString());
        
        return output.toString();
    }
    
    public BTT clone(){
        return new BTT (this);
    }

    public boolean equals(Object x)
    {
        
        if (this == x) return true;
        if (x==null || (this.getClass() != x.getClass())) return false;
        BTT btt = (BTT) x;
               
        return (super.equals(x) &&
                this.getNome().equals(btt.getNome()) &&
                this.getRequesito() == btt.getRequesito()
               );
    }

}
