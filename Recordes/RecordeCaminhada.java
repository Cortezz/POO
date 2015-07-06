package Recordes;

import java.io.Serializable;
/**
 *  Classe que armazena os recordes de caminhada
 */
public class RecordeCaminhada extends RecordeAxis implements Serializable
{
    
    /**
     * Construtores
     */
    public RecordeCaminhada()
    {
        super();
    }
    public RecordeCaminhada (int tempo, double dist, double v)
    {
        super (tempo, dist, v);
    }
    public RecordeCaminhada (RecordeCaminhada r)
    {
        super (r.getMaxTempo(), r.getMaxDist(), r.getMaxVm());
    }
    
    /**
     *  Testa se algum recorde foi batido
     */
    public void testaRecordeCaminhada (int tempo, double distancia)
    {
        double vmedia = math.round (distancia/((double)tempo/60), 2);
        if (tempo> this.getMaxTempo()) setMaxTempo (tempo);
        if (distancia > this.getMaxDist()) setMaxDist (distancia);
        if (vmedia > this.getMaxVm()) setMaxVm (vmedia);
    }
    
    
    public RecordeCaminhada clone ()
    {
        return new RecordeCaminhada (this);
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