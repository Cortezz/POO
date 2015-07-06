package Recordes;

import java.io.Serializable;
/**
 *  Class que armazena os recordes de corrida
 */
public class RecordeCorrida extends RecordeAxis implements Serializable
{
    private int cincoKm;
    private int dezKm;
    private int meiaMaratona;
    private int maratona;
    private double cooper;
    private double umaHora;
    private double duasHoras;
    
    
    
    /**
     *  Construtuores
     */
    public RecordeCorrida ()
    {
        super();
        cincoKm = 0;
        dezKm = 0;
        meiaMaratona = 0;
        maratona = 0;
        cooper = 0;
        umaHora = 0;
        duasHoras = 0;
    }
    public RecordeCorrida (int t, int maxd, int maxv, int cinco, int dez, int mm, int m, double cooper, double uma, double duas)
    {
        super (t,maxd,maxv);
        cincoKm = cinco;
        dezKm = dez;
        meiaMaratona = mm;
        maratona = m;
        this.cooper = cooper;
        umaHora = uma;
        duasHoras = duas;
    }
    public RecordeCorrida (RecordeCorrida r)
    {
        super (r.getMaxTempo(), r.getMaxDist(), r.getMaxVm());
        this.cincoKm = r.getCincoKm();
        this.dezKm = r.getDezKm();
        this.meiaMaratona = r.getMeiaMaratona();
        this.maratona = r.getMaratona();
        this.cooper = r.getCooper();
        this.umaHora = r.getUmaHora();
        this.duasHoras = r.getDuasHoras();
    }
    
    
    /**
     *  Metodos get e set
     */
    public int getCincoKm () { return this.cincoKm;}
    public int getDezKm () { return this.dezKm;}
    public int getMeiaMaratona () { return this.meiaMaratona;}
    public int getMaratona () { return this.maratona;}
    public double getCooper () { return this.cooper;}
    public double getUmaHora () { return this.umaHora;}
    public double getDuasHoras () { return this.duasHoras;}
        
    public void setCincoKm (int r) { this.cincoKm = r;}
    public void setDezKm (int r) { this.dezKm = r;}
    public void setMeiaMaratona (int r) { this.meiaMaratona = r;}
    public void setMaratona (int r) { this.maratona = r;}
    public void setCooper (double h) { this.cooper = h;}
    public void setUmaHora (double h) { this.umaHora = h;}
    public void setDuasHoras (double h) { this.duasHoras = h;}
    
    //Verifica se algum recorde foi batido
     public void testaRecordeCorrida (int tempo, double distancia)
    {
        int auxi;
        double auxd,vmedia;
        vmedia = math.round (distancia/((double)tempo/60), 2);
        if (tempo> this.getMaxTempo()) setMaxTempo (tempo);
        if (distancia > this.getMaxDist()) setMaxDist (distancia);
        if (vmedia > this.getMaxVm()) setMaxVm (vmedia);
   
        
        if (distancia > 3.75 && distancia < 6.25) 
        {
            auxi = math.regraTresSimples (tempo, distancia, 5);
            if (auxi>cincoKm) cincoKm = auxi;
        }
        if (distancia > 7.5 && distancia < 12.5)
        {
            auxi = math.regraTresSimples (tempo,distancia, 10);
            if (auxi>dezKm) dezKm = auxi;
        }
        if (distancia >16.5 && distancia < 27.5)
        {
            auxi = math.regraTresSimples (tempo, distancia,22);
            if (auxi>meiaMaratona) meiaMaratona = auxi;
        }
        if (distancia >33 && distancia < 55)
        {
            auxi = math.regraTresSimples (tempo, distancia, 44);
            if (auxi>maratona) maratona = auxi;
        }
        
        if (tempo>9 && tempo< 15)
        {
            auxd = math.regraTresSimplesD (tempo,distancia,12);
            if (auxd > cooper ) cooper = math.round (auxd,2);
        }
        if (tempo>45 && tempo <75)
        {
            auxd = math.regraTresSimplesD (tempo,distancia,60);
            if (auxd > umaHora) umaHora = math.round (auxd,2);
        }
        if (tempo>90 && tempo<150)
        {
            auxd = math.regraTresSimplesD (tempo,distancia,120);
            if (auxd > duasHoras) duasHoras = math.round (auxd,2);
        }
    }
    
    
    public RecordeCorrida clone ()
    {
        return new RecordeCorrida (this);
    }
    public boolean equals (Object o)
    {
        if (o==this) return true;
        if (o==null || (this.getClass()!=o.getClass())) return false;
        RecordeCorrida r = (RecordeCorrida) o;
        return (super.equals(r) && this.cincoKm==r.getCincoKm() && this.dezKm == r.getDezKm() && this.meiaMaratona == r.getMeiaMaratona() 
        && this.maratona == r.getMaratona() && this.cooper == r.getCooper() && this.umaHora == r.getUmaHora() && this.duasHoras == r.getDuasHoras());
    }
    public String toString ()
    {
        StringBuilder s = new StringBuilder();
        if (this.getMaxTempo()>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.getMaxTempo())+"h\n");
        else s.append ("Máximo Tempo: "+this.getMaxTempo()+" min\n");
        s.append ("Distância percorrida máxima: "+this.getMaxDist()+" Km\n");
        s.append ("Velocidade média máxima: "+this.getMaxVm()+" Km/h\n");
        if (this.cincoKm>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.cincoKm)+"h\n");
        else s.append ("Melhor tempo aos 5 Km: "+this.cincoKm+" min\n");
        if (this.dezKm>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.dezKm)+"h\n");
        else s.append ("Melhor tempo aos 10 Km: "+this.dezKm+" min\n");
        if (this.meiaMaratona>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.meiaMaratona)+"h\n");
        else s.append ("Melhor tempo na Meia Maratona (22km): "+this.meiaMaratona+" min\n");
        if (this.maratona>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.maratona)+"h\n");
        else s.append ("Melhor tempo na Maratona: "+this.maratona+" min\n");
        s.append ("Distância máxima no teste de Cooper: "+this.cooper+" Km\n");
        s.append ("Distância máxima numa hora: "+this.umaHora+" Km\n");
        s.append ("Distância máxima em duas horas: "+this.duasHoras+" Km\n");
        return s.toString();
    }
    
    
}