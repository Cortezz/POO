package Eventos;


/**
 * Write a description of class MeiaProvaCiclismo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.GregorianCalendar;
import Dados.*;

public class ProvaCiclismo extends Evento
{
    private String nome;
    private static final String atividade = "ProvaCiclismo";
    private double requesito;
    
    
    /**
     * Construtores
     */
    public ProvaCiclismo(){
        super();
        this.nome = new String();
        this.requesito = 0;
    }
    
    public ProvaCiclismo(String nome, GregorianCalendar data,BaseDeDadosUtilizadores inscritos,
                  int numeroMaximoInscricoes, GregorianCalendar dataLimiteInscricao, String descricao,
                  double requesito){
        super(data, inscritos, numeroMaximoInscricoes, dataLimiteInscricao, descricao);
        this.nome =  nome;
        this.requesito = requesito;
    }
    
    public ProvaCiclismo(ProvaCiclismo x){
        super(x.getData(), x.getInscritos(), x.getNumeroMaximoInscricoes(), x.getDataLimiteInscricao(),
        x.getDescricao());
        this.nome = x.getNome();
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
        output.append("Requisito mínimo: já ter percorrido "+ requesito + "kms de bicileta.\n");
        output.append(super.toString());
        
        return output.toString();
    }
    
    public ProvaCiclismo clone(){
        return new ProvaCiclismo (this);
    }
    
    public boolean equals(Object x)
    {
        
        if (this == x) return true;
        if (x==null || (this.getClass() != x.getClass())) return false;
        ProvaCiclismo ProvaCiclismo = (ProvaCiclismo) x;
               
        return (super.equals(x) &&
                this.getNome().equals(ProvaCiclismo.getNome()) &&
                this.getRequesito() == ProvaCiclismo.getRequesito()
               );
    }
}