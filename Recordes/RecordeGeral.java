package Recordes;
import java.io.Serializable;
/**
 * Super classe abstracta dos recordes 
 */
public abstract class RecordeGeral implements Serializable
{
    private int maxTempo;

    public RecordeGeral()
    {
        maxTempo = 0;
    }
    public RecordeGeral (int tempo)
    {
        maxTempo = tempo;
    }
    public RecordeGeral (RecordeGeral r)
    {
        maxTempo = r.getMaxTempo();
    }
    
    public int getMaxTempo () { return this.maxTempo;}
    public void setMaxTempo (int t) { this.maxTempo = t;}
    
    public boolean equals (Object o)
    {
        if (this==o) return true;
        else if (o==null || this.getClass()!=o.getClass()) return false;
        RecordeGeral r = (RecordeGeral)o;
        return (this.maxTempo==r.getMaxTempo());
    }
    public abstract RecordeGeral clone ();
}
