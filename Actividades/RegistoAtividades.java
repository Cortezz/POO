package Actividades;
import java.util.TreeMap;
import java.util.Map;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;
/**
 *  Classes cujas instancias vao armazenar as dez ultimas actividades
 */
public class RegistoAtividades implements Serializable
{
    private TreeMap<Integer,AtividadeDesportiva> registo;
    
    /**
     *  Construtores
     */
    public RegistoAtividades()
    {
        registo = new TreeMap<Integer,AtividadeDesportiva>();
    }
    public RegistoAtividades (RegistoAtividades r)
    {
        registo = r.getRegisto();
    }
    
    /**
     * Gets
     */
    public TreeMap<Integer,AtividadeDesportiva> getRegisto ()
    {
        TreeMap<Integer,AtividadeDesportiva> resultado = new TreeMap <Integer,AtividadeDesportiva>();
        for (Map.Entry<Integer,AtividadeDesportiva> e : registo.entrySet())
        {
            int n = e.getKey();
            AtividadeDesportiva a = e.getValue();
            resultado.put (n,a.clone());
        }
        return resultado;
    }
    
    //Preenche a estrutura de dados
    public void preencheRegisto (TreeSet<AtividadeDesportiva> t)
    {
        registo = new TreeMap<Integer,AtividadeDesportiva>();
        int i=0;
        Iterator<AtividadeDesportiva> it = t.iterator();
        while (it.hasNext())
        {
            registo.put (i+1,it.next());
            i++;
        }
    }

    
    //Retorna a actividade cuja chave e o indice
    public AtividadeDesportiva getAtividadeRegisto (int indice)
    {
        AtividadeDesportiva a = registo.get(indice);
        return a;
    }
    
    
    public String mostraAtividade(int indice)
    {
        AtividadeDesportiva a = registo.get(indice);
        String resultado = a.toString();
        return resultado;
    }
    public String mostraAtividadesPorData (GregorianCalendar data)
    {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<Integer,AtividadeDesportiva> e: registo.entrySet())
            if (e.getValue().getData().equals (data)) 
            {
                s.append ("\n");
                s.append ("-"+e.getKey()+"-\n");
                s.append (e.getValue().toString());
                s.append ("\n");
            }
        return s.toString();
    }
            
    public RegistoAtividades clone ()
    {
        return new RegistoAtividades(this);
    }
    public String toString (int a)
    {
        StringBuilder s = new StringBuilder ();
        int j = 1;
        for (Map.Entry<Integer,AtividadeDesportiva> e : registo.entrySet())
        {
            if (j<=a)
            {
                s.append (""+e.getKey()+"- ");
                s.append (""+e.getValue().getData().get(Calendar.DAY_OF_MONTH) + "/" +(e.getValue().getData().get(Calendar.MONTH)+1) + "/" + 
                e.getValue().getData().get(Calendar.YEAR) + "\n");
                s.append (" "+e.getValue().getClass().getSimpleName()+"\n\n");
            }
            j++;
        }
        return s.toString();
    }
    
}
