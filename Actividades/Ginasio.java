package Actividades;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.io.Serializable;
import Recordes.math;
public class Ginasio extends AtividadeDesportiva implements Serializable
{
    public Ginasio()
    {
        super ();
    }
    public Ginasio(int tempo, GregorianCalendar data)
    {
        super (tempo, data);
    }
    public Ginasio (Ginasio w)
    {
        super (w.getTempo(), w.getData());
    }
    
    public double factorK ()
    {
        return 3.4;
    }
    public boolean equals (Object o)
    {
        return super.equals(o);
    }
    public String toString ()
    {
        StringBuilder s = new StringBuilder ("\nWeight Training\n");
        if (this.getTempo()>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.getTempo())+"h\n");
        else s.append ("Tempo: "+this.getTempo()+" min\n");
        s.append ("Calorias: "+this.calorias()+"\n");
        s.append ("No dia: "+this.getData().get(Calendar.DAY_OF_MONTH) + "/" +((this.getData().get(Calendar.MONTH))+1) + "/" + 
        this.getData().get(Calendar.YEAR) + "\n");
        return s.toString();
    }
    public Ginasio clone ()
    {
        return new Ginasio(this);
    }
}
