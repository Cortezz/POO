package Actividades;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.lang.StringBuilder;

public abstract class AtividadeDesportiva implements Serializable
{
    
   private int tempo;
   private GregorianCalendar data;
    
    /**
     * Construtores
     */
    public AtividadeDesportiva()
    {   
        tempo = 0;
        data = new GregorianCalendar();
        
    }
    
    public AtividadeDesportiva(int tempo,GregorianCalendar data)
    {
        this.tempo = tempo;
        this.data = (GregorianCalendar)data.clone();
        
    }
    
    public AtividadeDesportiva(AtividadeDesportiva x)
    {
        this.tempo = x.getTempo();
        this.data = x.getData();
    }
    /**
     * Getters
     */
    
    public int getTempo(){return this.tempo;}    
    public GregorianCalendar getData () { return (GregorianCalendar)this.data.clone();}
    /**
     *
     * Setters
     */
    
    public void setTempo(int tempo) {this.tempo = tempo; }
    public void setData (GregorianCalendar data) { this.data = (GregorianCalendar)data.clone();}
    
    
    /**
     * m??todos Complementares
     */
   public boolean equals(Object o)
   {
        if(this == o) return true;
        if(o == null || (o.getClass() != this.getClass())) return false;
        
        AtividadeDesportiva x = (AtividadeDesportiva) o;
        return (this.data.equals(x.getData()) && this.tempo == x.getTempo());
   }
   
   public int calorias ()
   {
        int cal = (int)((this.getTempo()/2) * factorK());
        return cal;
   }
   
   
   public abstract AtividadeDesportiva clone ();
   public abstract String toString();
   
   public abstract double factorK ();
    
    
    
}
