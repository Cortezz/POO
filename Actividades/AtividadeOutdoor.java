package Actividades;

import java.util.GregorianCalendar;
public abstract class AtividadeOutdoor extends AtividadeDesportiva
{
    private double distancia;
    private String clima;
 
    public AtividadeOutdoor()
    {
        super();
        distancia = 0;
        clima = "";
    }
    public AtividadeOutdoor (int tempo, GregorianCalendar data, double distancia, String clima)
    {
        super (tempo,data);
        this.distancia = distancia;
        this.clima = clima;
    }
    public AtividadeOutdoor (AtividadeOutdoor a)
    {
        super (a.getTempo(),a.getData());
        this.distancia = a.getDistancia();
        this.clima = a.getClima();
    }
    
    public int calorias ()
    {
        int cal = (int)(((this.getTempo()*this.getDistancia())/9) * factorK());
        return cal;
    
    }
    
    public double getDistancia () {return this.distancia;}
    public String getClima () {return this.clima;}
    public double getTempoHoras () { return (double)getTempo()/60;}
    public double getVelocidadeMedia () 
    { 
        double aux,result;
        aux= distancia/getTempoHoras();
        result = roundToDecimals (aux,2);
        return result;
    }
    
    public  double roundToDecimals(double d, int c)  
    {   
        int temp = (int)(d * Math.pow(10 , c));  
        return ((double)temp)/Math.pow(10 , c);  
    }
    
    
    public void setDistancia (double distancia) { this.distancia = distancia;}
    public void setClima (String clima) {this.clima = clima;}

    
    public boolean equals (Object o)
    {
        if (this==o) return true;
        if (o==null || (o.getClass()!=this.getClass())) return false;
        AtividadeIndoor a = (AtividadeIndoor) o;
        return (super.equals(a) && this.distancia == a.getDistancia());
    }
}
