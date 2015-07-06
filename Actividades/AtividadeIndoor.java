package Actividades;
import java.util.GregorianCalendar;
public abstract class AtividadeIndoor extends AtividadeDesportiva
{
    private double distancia;
    public AtividadeIndoor ()
    {
        super();
        this.distancia=0;
    }
    public AtividadeIndoor (int tempo, GregorianCalendar data, float distancia)
    {
        super (tempo,data);
        this.distancia = distancia;
    }
    public AtividadeIndoor (AtividadeIndoor a)
    {
        super (a.getTempo(), a.getData());
        distancia = a.getDistancia();
    }
    
    public double getDistancia () { return this.distancia;}
    public void setDistancia (float distancia) { this.distancia = distancia;}
        
    public boolean equals (Object o)
    {
        if (this==o) return true;
        if (o==null || (o.getClass()!=this.getClass())) return false;
        AtividadeIndoor a = (AtividadeIndoor) o;
        return (super.equals(a) && this.distancia == a.getDistancia());
    }
}
