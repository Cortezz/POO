package Actividades;
import Recordes.math;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;
public class Ciclismo extends AtividadeOutdoor3D implements Serializable, Deslocavel,Ascendivel
{
    
    public Ciclismo()
    {
        super();
    }
    public Ciclismo (int tempo, double distancia, GregorianCalendar data, String clima, int altura)
    {
        super (tempo,data,distancia, clima, altura);
    }
    public Ciclismo (Ciclismo f)
    {
        super (f.getTempo(),f.getData(), f.getDistancia(), f.getClima(), f.getAltura());
    }

    public double factorK ()
    {
        return 4.9;
    }
    
    
    
    public boolean equals (Object o)
    {
        return super.equals(o);
    }
    public Ciclismo clone ()
    {
        return new Ciclismo(this);
    }
    public String toString ()
    {
        StringBuilder s = new StringBuilder ("\nCiclismo\n");
        if (this.getTempo()>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.getTempo())+"h\n");
        else s.append ("Tempo: " +this.getTempo()+" min\n");
        s.append ("Distância: "+this.getDistancia()+" km\n");
        s.append ("Calorias: "+this.calorias()+"\n");
        s.append ("Velocidade média: "+this.getVelocidadeMedia()+" km/h\n");
        s.append ("No dia: "+this.getData().get(Calendar.DAY_OF_MONTH) + "/" +((this.getData().get(Calendar.MONTH))+1) + "/" + 
        this.getData().get(Calendar.YEAR) + "\n");
        s.append ("Clima: "+this.getClima()+"\n");
        s.append ("Desnivel: "+this.getAltura()+" m\n");
        return s.toString();
    }
}
