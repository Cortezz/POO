package Actividades;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;
import java.io.Serializable;
import java.util.GregorianCalendar;
/**
 *  Classe cujas instancias armazenam todas as actividades do utilizador
 */
public class ListaAtividadeDesportivas implements Serializable
{
    /**
     * variaveis de Instancia
     */
    private ArrayList<AtividadeDesportiva> lista;
    
    
    /**
     * Construtores
     */
    public ListaAtividadeDesportivas()
    {
        lista = new ArrayList<AtividadeDesportiva>();
    }
    public ListaAtividadeDesportivas(ListaAtividadeDesportivas lista)
    {
        this.lista = lista.getLista();
    }
    
    /**
     * getters
     */
    public ArrayList<AtividadeDesportiva> getLista()
    {
        ArrayList<AtividadeDesportiva> copia = new ArrayList<AtividadeDesportiva>();
        
        for (AtividadeDesportiva x : lista)
        {
            copia.add(x.clone());
        }
        return copia;
    }
    /**
     * Setters
     */
    public void setLista(ArrayList<AtividadeDesportiva> lista)
    {
        this.lista.clear();
        for(AtividadeDesportiva x : lista)
        {
            lista.add(x.clone());
        }
    }
    
    /**
     * metodos principais
     */
    public void adicionaAtividadeDesportiva(AtividadeDesportiva x)
    {
        lista.add (x.clone());
    }
    //Ordena as datas num TreeSet
    public TreeSet<AtividadeDesportiva> atividadesPorData ()
    {
        TreeSet<AtividadeDesportiva> t = new TreeSet<AtividadeDesportiva>(new ComparatorAtividade());
        for (AtividadeDesportiva a: lista)
            t.add (a);
        return t;
    }
    public void removeAtividade (AtividadeDesportiva a)
    {
        lista.remove (a);
    }
    
    public int size(){
        return lista.size();
    }
    
    /**
     *  Metodos relacionados com a estatistica dos tres parametros: calorias, tempo e distancia
     */
    public int informacaoMensalCalorias (int mes, int ano)
    {
        int total = 0;
        AtividadeOutdoor novo;
        for (AtividadeDesportiva a : lista)
        {
            int mesO = (a.getData().get(GregorianCalendar.MONTH));
            int anoO = a.getData().get(GregorianCalendar.YEAR);
            if (a instanceof Deslocavel)
            {
                novo = (AtividadeOutdoor) a;
                if (mesO==mes && anoO==ano) total = total+ novo.calorias();
            }
            else if (mesO==mes && anoO==ano) total = total + a.calorias();
        }
        return total;
    }
    public int informacaoAnualCalorias (int date)
    {
        int total = 0;
        AtividadeOutdoor novo;
        for (AtividadeDesportiva a : lista)
        {
            int anoO = a.getData().get(GregorianCalendar.YEAR);
            if (a instanceof Deslocavel)
            {
                novo = (AtividadeOutdoor) a;
                if (anoO == date) total = total+ novo.calorias();
            }
            else if (anoO == date) total = total + a.calorias();
        }
        return total;
    }
    public int informacaoMensalTempo (int mes, int ano)
    {
        int total = 0;
        for (AtividadeDesportiva novo : lista)
        {
            int mesO = (novo.getData().get(GregorianCalendar.MONTH));
            int anoO = novo.getData().get(GregorianCalendar.YEAR);
            if (mesO==mes && anoO==ano) total = total+novo.getTempo();
        }
        return total;
    }
    public int informacaoAnualTempo (int date)
    {
        int total = 0;
        for (AtividadeDesportiva novo : lista)
        {
            int anoO = novo.getData().get(GregorianCalendar.YEAR);
            if (anoO == date) total = total+novo.getTempo();
        }
        return total;
    }
        public double informacaoMensalDistancia (int mes, int ano)
    {
        double total = 0;
        AtividadeOutdoor novo;
        for (AtividadeDesportiva a : lista)
        {
            if (a instanceof Deslocavel)
            {
                novo = (AtividadeOutdoor) a;
                int mesO = (novo.getData().get(GregorianCalendar.MONTH));
                int anoO = novo.getData().get(GregorianCalendar.YEAR);
                if (mesO==mes && anoO==ano) total = total+(double)novo.getDistancia();
            }
        }
        return total;
    }
    public double informacaoAnualDistancia (int date)
    {
        double total = 0;
        AtividadeOutdoor novo;
        for (AtividadeDesportiva a : lista)
        {
            if (a instanceof Deslocavel)
            {
                novo = (AtividadeOutdoor) a;
                int anoO = novo.getData().get(GregorianCalendar.YEAR);
                if (anoO == date) total = total+(double)novo.getDistancia();
            }
        }
        return total;
    }
    public int numeroAtividadesPorMes (int mes)
    {
        int total = 0;
        for (AtividadeDesportiva a: lista)
            if ((a.getData().get(GregorianCalendar.MONTH))==mes) total++;
        return total;
    }
    public int numeroAtividadesPorAno (int ano)
    {
        int total = 0;
        for (AtividadeDesportiva a: lista)
            if ((a.getData().get(GregorianCalendar.YEAR))==ano) total++;
        return total;
    }
            
        
    
    
    
    
    
    
    
    /**
     * metodos Complementares
     */
    public ListaAtividadeDesportivas clone()
    {
        return new ListaAtividadeDesportivas (this);
    }
    
     public String toString()
    {
        StringBuilder output = new StringBuilder("--- AtividadeDesportivas Realizadas ---\n");
        
        for (AtividadeDesportiva x : this.lista)
        {
            output.append(x.toString());
        }
       
       return output.toString();
    }
    
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o ==null || (this.getClass() != o.getClass())) return false;
        ListaAtividadeDesportivas x = (ListaAtividadeDesportivas) o;
        
        return (this.lista == x.getLista());
    }
    
   
        
}
