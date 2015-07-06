package Recordes;

import java.io.Serializable;
/**
 *  Classe que vai armazenar os recordes da actividade Ginasio
 */
public class RecordeGinasio extends RecordeGeral implements Serializable
{
    public RecordeGinasio ()
    {
        super();
    }
    public RecordeGinasio (int tempo)
    {
        super (tempo);
    }
    public RecordeGinasio (RecordeGinasio r)
    {
        super (r.getMaxTempo());
    }
    
    public void testaRecordeGinasio (int tempo)
    {
        if (tempo>this.getMaxTempo()) setMaxTempo (tempo);
    }
    
    
    public RecordeGinasio clone ()
    {
        return new RecordeGinasio (this);
    }
    public String toString ()
    {
        StringBuilder s = new StringBuilder ();
        if (this.getMaxTempo()>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.getMaxTempo())+"h\n");
        else s.append ("Máximo Tempo: "+this.getMaxTempo()+" min\n");
        return s.toString();
    }
}
