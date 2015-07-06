package Actividades;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;
import Recordes.math;
public class Caminhada extends AtividadeOutdoor3D implements Serializable,Deslocavel,Ascendivel
{
    
    public Caminhada()
    {
        super();
    }
    public Caminhada (int tempo, double distancia, GregorianCalendar data, String clima, int altura)
    {
        super (tempo,data, distancia, clima, altura);
    }
    public Caminhada (Caminhada f)
    {
        super (f.getTempo(),f.getData(), f.getDistancia(), f.getClima(), f.getAltura());
    }

    
    public double factorK ()
    {
        return 1.4;
    }
    
    
    
    
    public boolean equals (Object o)
    {
        return super.equals(o);
    }
    public Caminhada clone ()
    {
        return new Caminhada(this);
    }
    public String toString ()
    {
        StringBuilder s = new StringBuilder ("\nCaminhada\n");
        if (this.getTempo()>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.getTempo())+"h\n");
        else s.append ("Tempo: " +this.getTempo()+" min\n");
        s.append ("Distância : "+this.getDistancia()+" km\n");
        s.append ("Calorias: "+this.calorias()+"\n");
        s.append ("Velocidade média: "+this.getVelocidadeMedia()+" km/h\n");
        s.append ("No dia: "+this.getData().get(Calendar.DAY_OF_MONTH) + "/" +((this.getData().get(Calendar.MONTH))+1) + "/" + 
        this.getData().get(Calendar.YEAR) + "\n");
        s.append ("Clima: "+this.getClima()+"\n");
        s.append ("Desnivel: "+this.getAltura()+" m\n");
        return s.toString();
    }
}