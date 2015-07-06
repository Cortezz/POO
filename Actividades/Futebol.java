package Actividades;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;
import Recordes.math;
public class Futebol extends AtividadeOutdoor implements Serializable, Deslocavel
{
    
    public Futebol()
    {
        super();
    }
    public Futebol (int tempo, double distancia, GregorianCalendar data, String clima)
    {
        super (tempo,data, distancia, clima);
    }
    public Futebol (Futebol f)
    {
        super (f.getTempo(),f.getData(), f.getDistancia(), f.getClima());
    }
    
    public double factorK ()
    {
        return 6.1;
    }

    
    
    
    public boolean equals (Object o)
    {
        return super.equals(o);
    }
    public Futebol clone ()
    {
        return new Futebol(this);
    }
    public String toString ()
    {
        StringBuilder s = new StringBuilder ("\nFutebol\n");
        if (this.getTempo()>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.getTempo())+"h\n");
        else s.append ("Tempo : " +this.getTempo()+" min\n");
        s.append ("Distância: "+this.getDistancia()+" km\n");
        s.append ("Calorias: "+this.calorias()+"\n");
        s.append ("Velocidade média: "+this.getVelocidadeMedia()+" km/h\n");
        s.append ("No dia: "+this.getData().get(Calendar.DAY_OF_MONTH) + "/" +((this.getData().get(Calendar.MONTH))+1) + "/" + 
        this.getData().get(Calendar.YEAR) + "\n");
        s.append ("Clima: "+this.getClima()+"\n");
        return s.toString();
    }
}
