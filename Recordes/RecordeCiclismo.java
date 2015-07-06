package Recordes;

import java.io.Serializable;
/**
 *    Classe que armazena os recordes de ciclismo
 */
public class RecordeCiclismo extends RecordeAxis implements Serializable
{
    private int dezKm;
    private int vinteKm;
    private int cinquentaKm;
    private int cemKm;
    private int ironMan;
    private double umaHora;
    private double duasHoras;
    private double quatroHoras;
    
    
    /**
     *  Construtores
     */
    public RecordeCiclismo ()
    {
        super();
        dezKm = 0;
        vinteKm = 0;
        cinquentaKm = 0;
        cemKm = 0;
        ironMan = 0;
        umaHora = 0;
        duasHoras = 0;
        quatroHoras = 0;
    }
    public RecordeCiclismo (int t, int maxd, int maxv, int dez, int vinte, int cinquenta, int cem, int iron, double uma, double duas, double quatro)
    {
        super (t,maxd,maxv);
        dezKm = dez;
        vinteKm = vinte;
        cinquentaKm = cinquenta;
        cemKm = cem;
        ironMan = iron;
        umaHora = uma;
        duasHoras = duas;
        quatroHoras = quatro;
    }
    public RecordeCiclismo (RecordeCiclismo r)
    {
        super (r.getMaxTempo(), r.getMaxDist(), r.getMaxVm());
        this.dezKm = r.getDezKm();
        this.vinteKm = r.getVinteKm();
        this.cinquentaKm = r.getCinquentaKm();
        this.cemKm = r.getCemKm();
        this.ironMan = r.getIronMan();
        this.umaHora = r.getUmaHora();
        this.duasHoras = r.getDuasHoras();
        this.quatroHoras = r.getQuatroHoras();
    }
    
    
    /**
     *  Metodos get e set
     */
    public int getDezKm () { return this.dezKm;}
    public int getVinteKm () { return this.vinteKm;}
    public int getCinquentaKm () { return this.cinquentaKm;}
    public int getCemKm () { return this.cemKm;}
    public int getIronMan () { return this.ironMan;}
    public double getUmaHora () { return this.umaHora;}
    public double getDuasHoras () { return this.duasHoras;}
    public double getQuatroHoras () { return this.quatroHoras;}
        
    public void setDezKm (int r) { this.dezKm = r;}
    public void setVinteKm (int r) { this.vinteKm = r;}
    public void setCinquentaKm (int r) { this.cinquentaKm = r;}
    public void setCemKm (int r) { this.cemKm = r;}
    public void setIronMan (int r) { this.ironMan = r;}
    public void setUmaHora (double h) { this.umaHora = h;}
    public void setDuasHoras (double h) { this.duasHoras = h;}
    public void setQuatroHoras (double h) { this.quatroHoras = h;}
    
    
    //testa se algum recorde foi batido
    public void testaRecordeCiclismo (int tempo, double distancia)
    {
        int auxi;
        double auxd,vmedia;
        vmedia = math.round (distancia/((double)tempo/60), 2);
        if (tempo> this.getMaxTempo()) setMaxTempo (tempo);
        if (distancia > this.getMaxDist()) setMaxDist (distancia);
        if (vmedia > this.getMaxVm()) setMaxVm (vmedia);
   
        
        if (distancia > 7.75 && distancia < 10.25) 
        {
            auxi = math.regraTresSimples (tempo, distancia, 10);
            if (auxi>dezKm) dezKm = auxi;
        }
        if (distancia > 15 && distancia < 25)
        {
            auxi = math.regraTresSimples (tempo,distancia, 20);
            if (auxi>vinteKm) vinteKm = auxi;
        }
        if (distancia > 37.5 && distancia < 62.5)
        {
            auxi = math.regraTresSimples (tempo, distancia,50);
            if (auxi>cinquentaKm) cinquentaKm = auxi;
        }
        if (distancia >75 && distancia < 125)
        {
            auxi = math.regraTresSimples (tempo, distancia, 100);
            if (auxi>cemKm) cemKm = auxi;
        }
        if (distancia >135 && distancia > 225)
        {
            auxi = math.regraTresSimples (tempo, distancia, 180);
            if (auxi>ironMan) ironMan = auxi;
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
        if (tempo>180 && tempo<300)
        {
            auxd = math.regraTresSimplesD (tempo,distancia,240);
            if (auxd > quatroHoras) quatroHoras = math.round (auxd,2);
        }
    }
    
    
    
    
    
    
    public RecordeCiclismo clone ()
    {
        return new RecordeCiclismo (this);
    }
    public boolean equals (Object o)
    {
        if (o==this) return true;
        if (o==null || (this.getClass()!=o.getClass())) return false;
        RecordeCiclismo r = (RecordeCiclismo) o;
        return (super.equals(r) && this.dezKm==r.getDezKm() && this.vinteKm == r.getVinteKm() && this.cinquentaKm == r.getCinquentaKm() 
        && this.cemKm == r.getCemKm() && this.ironMan == r.getIronMan() && this.umaHora == r.getUmaHora() && this.duasHoras == r.getDuasHoras()
        && this.quatroHoras == r.getQuatroHoras());
    }
    public String toString ()
    {
        StringBuilder s = new StringBuilder();
        if (this.getMaxTempo()>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.getMaxTempo())+"h\n");
        else s.append ("Máximo Tempo: "+this.getMaxTempo()+" min\n");
        s.append ("Distância percorrida máxima: "+this.getMaxDist()+" Km\n");
        s.append ("Velocidade média máxima: "+this.getMaxVm()+" Km/h\n");
        if (this.dezKm>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.dezKm)+"h\n");
        else s.append ("Melhor tempo aos 10 Km: "+this.dezKm+" min\n");
        if (this.vinteKm>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.vinteKm)+"h\n");
        else s.append ("Melhor tempo aos 20 Km: "+this.vinteKm+" min\n");
        if (this.cinquentaKm>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.cinquentaKm)+"h\n");
        else s.append ("Melhor tempo aos 50 Km: "+this.cinquentaKm+" min\n");
        if (this.cemKm>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.cemKm)+"h\n");
        else s.append ("Melhor tempo aos 100 Km: "+this.cemKm+" min\n");
        if (this.ironMan>60) s.append ("Máximo tempo: "+math.IntToFormatedHour (this.ironMan)+"h\n");
        else s.append ("Melhor tempo no IronMan (parte de ciclismo - 180 Km): "+this.ironMan+" min\n");
        s.append ("Distância máxima numa hora: "+this.umaHora+" Km\n");
        s.append ("Distância máxima em duas horas: "+this.duasHoras+" Km\n");
        s.append ("Distância máxima em quatro horas: "+this.quatroHoras+" Km\n");
        return s.toString();
    }
    
    
}
