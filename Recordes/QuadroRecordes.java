package Recordes;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
/**
 * Class que vai guardar os recordes do utilizador.
 */
public class QuadroRecordes implements Serializable
{
    private HashMap<String,RecordeGeral> quadro;
    
    /**
     *  Construtores
     */
    public QuadroRecordes()
    {
        quadro = new HashMap<String,RecordeGeral>();
    }
    public QuadroRecordes (QuadroRecordes q)
    {
        quadro = q.getQuadro();
    }
    
    
    
    /**
     * Metodos gets.
     */
    public HashMap<String,RecordeGeral> getQuadro ()
    {
        HashMap<String,RecordeGeral> novo = new HashMap<String,RecordeGeral>();
        for (Map.Entry<String,RecordeGeral> e : quadro.entrySet())
            quadro.put (e.getKey(), e.getValue().clone());
        return novo;
    }
    public int getTamanho ()
    {
        return quadro.size();
    }
    
    /**
     *  Metodo que envia o recorde a ser testado consoante o desporto.
     */
    public void testaRecorde (String desporto, int tempo, double distancia)
    {
        switch (desporto)
        {
            case "Futebol": RecordeGeral aux = quadro.get(desporto);
                            RecordeFutebol rf = (RecordeFutebol) aux;
                            rf.testaRecordeFutebol (tempo,distancia);
                            break;
            case "Ginasio": RecordeGeral aux1 = quadro.get(desporto);
                            RecordeGinasio rg = (RecordeGinasio) aux1;
                            rg.testaRecordeGinasio (tempo);
                            break;
            case "Caminhada": RecordeGeral aux2 = quadro.get(desporto);
                              RecordeCaminhada rc = (RecordeCaminhada) aux2;
                              rc.testaRecordeCaminhada (tempo, distancia);
                              break;
            case "Corrida": RecordeGeral aux3 = quadro.get(desporto);
                            RecordeCorrida rco = (RecordeCorrida) aux3;
                            rco.testaRecordeCorrida (tempo,distancia);
                            break;
            case "Ciclismo": RecordeGeral aux4 = quadro.get(desporto);
                             RecordeCiclismo rci = (RecordeCiclismo) aux4;
                             rci.testaRecordeCiclismo (tempo,distancia);
                             break;
        }
    }
    
    //Inicializa o Recorde
    public void inicializaRecorde (String desporto, RecordeGeral r)
    {
        if (!quadro.containsKey (desporto)) quadro.put (desporto, r.clone());
    }
    //Verifica se o Recorde existe
    public boolean existeRecorde (String desporto)
    {
        return quadro.containsKey(desporto);
    }
    
    /**
     * Retorna a lista dos desportos presentes no quadro dos recordes
     */
    public String listaRecordes()
    {
        StringBuilder s = new StringBuilder ();
        for (String n: quadro.keySet())
            s.append (n+"\n");
        return s.toString();        
    }
    
    /**
     *  Todos estes metods devolvem uma string com as propriedades de cada recorde
     */
    public String recordeFutebol ()
    {
        RecordeGeral novo = quadro.get("Futebol");
        RecordeFutebol rf = (RecordeFutebol) novo;
        return rf.toString();
    }
    public String recordeGinasio ()
    {
        RecordeGeral novo = quadro.get("Ginasio");
        RecordeGinasio rg = (RecordeGinasio) novo;
        return rg.toString();
    }
    public String recordeCiclismo ()
    {
        RecordeGeral novo = quadro.get("Ciclismo");
        RecordeCiclismo rc = (RecordeCiclismo) novo;
        return rc.toString();
    }
    public String recordeCaminhada ()
    {
        RecordeGeral novo = quadro.get("Caminhada");
        RecordeCaminhada rc = (RecordeCaminhada) novo;
        return rc.toString();
    }
    public String recordeCorrida ()
    {
        RecordeGeral novo = quadro.get("Corrida");
        RecordeCorrida rf = (RecordeCorrida) novo;
        return rf.toString();
    }

    
    
    
    public QuadroRecordes clone () 
    {
        return new QuadroRecordes (this);
    }
    public boolean equals_aux (QuadroRecordes q)
    {
        return true;
    }
    public boolean equals (Object o)
    {
        if (this==o) return true;
        if (o==null || (o.getClass()!=this.getClass())) return false;
        QuadroRecordes q = (QuadroRecordes) o;
        return this.equals_aux(q);
    }
}
