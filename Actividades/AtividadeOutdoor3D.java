package Actividades;

import java.util.GregorianCalendar;
public abstract class AtividadeOutdoor3D extends AtividadeOutdoor
{
    private int altura;
    
    public AtividadeOutdoor3D ()
    {
        super();
        this.altura = 0;
    }
    public AtividadeOutdoor3D (int tempo, GregorianCalendar data, double distancia, String clima, int altura)
    {
        super (tempo,data,distancia,clima);
        this.altura = altura;
    }
    public AtividadeOutdoor3D (AtividadeOutdoor3D a)
    {
        super (a.getTempo(), a.getData(), a.getDistancia(), a.getClima());
        this.altura = a.getAltura();
    }
    
    public int getAltura() { return this.altura;}
    public void setAltura (int altura) {this.altura = altura;}
    
    public boolean equals (Object o)
    {
        if (o==this) return true;
        if (o==null || (o.getClass()!=this.getClass())) return false;
        AtividadeOutdoor3D a = (AtividadeOutdoor3D) o;
        return (super.equals(a) && this.altura == a.getAltura());
    }
}
