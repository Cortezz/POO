package Recordes;
/**
 *  Recordes que involvam movimento
 */ 
public abstract class RecordeAxis extends RecordeGeral
{
    private double maxDist;
    private double maxVm;
    
    public RecordeAxis ()
    {
        super();
        maxDist = 0;
        maxVm = 0;
    }
    public RecordeAxis (int tempo, double dist, double v)
    {
        super (tempo);
        maxDist = dist;
        maxVm = v;
    }
    public RecordeAxis (RecordeAxis r)
    {
        super (r.getMaxTempo());
        this.maxDist = r.getMaxDist();
        this.maxVm = r.getMaxVm();
    }
    
    public double getMaxDist() { return this.maxDist;}
    public double getMaxVm () { return this.maxVm;}
    
    public void setMaxDist (double d) { this.maxDist = d;}
    public void setMaxVm (double v) { this.maxVm = v;}
    
    public boolean equals (Object o)
    {
        if (this==o) return true;
        if (o==null || (this.getClass()!=o.getClass())) return false;
        RecordeAxis r = (RecordeAxis) o;
        return (this.maxDist==r.getMaxDist() && this.maxVm==r.getMaxVm() && super.equals(r));
    }
    
}
