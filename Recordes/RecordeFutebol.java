package Recordes;
import java.io.Serializable;
/**
 *  Classe que armazena recordes de futebol
 */
public class RecordeFutebol extends RecordeAxis implements Serializable
{
    
    /**
     *  Construtores
     */
    public RecordeFutebol()
    {
        super();
    }
    public RecordeFutebol (int tempo, double dist, double v)
    {
        super (tempo, dist, v);
    }
    public RecordeFutebol (RecordeFutebol r)
    {
        super (r.getMaxTempo(), r.getMaxDist(), r.getMaxVm());
    }
    
    //testa se algum recorde foi batido
    public void testaRecordeFutebol (int tempo, double distancia)
    {
        double vmedia = math.round (distancia/((double)tempo/60), 2);
        if (tempo> this.getMaxTempo()) setMaxTempo (tempo);
        if (distancia > this.getMaxDist()) setMaxDist (distancia);
        if (vmedia > this.getMaxVm()) setMaxVm (vmedia);
    }
   
    public RecordeFutebol clone ()
    {
        return new RecordeFutebol (this);
    }
    public String toString ()
    {
        StringBuilder s = new StringBuilder ();
        if (this.getMaxTempo()>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.getMaxTempo())+"h\n");
        else s.append ("Máximo Tempo: "+this.getMaxTempo()+" min\n");
        s.append ("Distância percorrida máxima: "+this.getMaxDist()+" Km\n");
        s.append ("Velocidade média máxima: "+this.getMaxVm()+" Km/h\n");
        return s.toString();
    }
}
