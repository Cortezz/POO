package Dados;

import java.util.TreeMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.Serializable;
/**
 *  Classe cujas instancias vao armazenar a lista de amigos e a lista de pedidos de amizade
 */
public class ListaAmigos implements Serializable
{
    private TreeMap<String,String> amigos;
    private ArrayList<String> pedidos;
    
    /**
     *  Construtores
     */
    public ListaAmigos()
    {
        amigos = new TreeMap<String,String>();
        pedidos = new ArrayList<String>();
    }
    public ListaAmigos(ListaAmigos l)
    {
        amigos = l.getAmigos();
        pedidos = l.getPedidos();
    }
    public ListaAmigos (TreeMap<String,String> amigos, ArrayList<String> pedidos)
    {
        this.amigos = amigos;
        this.pedidos = pedidos;
    }
    
    
    //Gets
    public TreeMap<String,String> getAmigos()
    {
        TreeMap<String,String> novo = new TreeMap<String,String>();
        for (Map.Entry<String,String> e : amigos.entrySet())
            novo.put (e.getKey(), e.getValue());
        return novo;
    }
    public ArrayList<String> getPedidos()
    {
        ArrayList<String> novo = new ArrayList<String>();
        for (String n: pedidos)
            novo.add (n);
        return novo;
    }
    
    /**
     *  Metodos relacionados com a lista de amigos
     */
    public void adicionaAmigo (String email, String nome)
    {
        if (!existeAmigo(email)) amigos.put(email,nome);
    }
    public boolean existeAmigo (String email)
    {
        return (amigos.containsKey(email));
    }
    public void removeAmigo (String email)
    {
        if (existeAmigo(email)) amigos.remove(email);
    }
    public int totalAmigos ()
    {
        return amigos.size();
    }
    public String nomeAmigos ()
    {   
        StringBuilder s = new StringBuilder ();
        for (Map.Entry e : amigos.entrySet())
            s.append (e.getValue()+ "("+e.getKey()+")\n");
        return s.toString();
    }

    
    
    
    /**
     *  Metodos relacionados com a lista de pedidos de amizade
     */
    public void adicionaPedido (String nome)
    {
        if (!pedidos.contains(nome)) pedidos.add (nome);
    }
    public void removePedido (String nome)
    {
        pedidos.remove(nome);
    }
    public void removePedido (int indice)   
    {
        pedidos.remove(indice);
    }
    public int numeroPedidos ()
    {
        return pedidos.size();
    }
    public String listaPedidos ()
    {
        int i;
        StringBuilder n = new StringBuilder();
        for (i=0;i<pedidos.size();i++)
        {
            n.append (i);
            n.append (" - ");
            n.append (pedidos.get(i)+"\n");
        }
        return n.toString();
    }
    public void enviaPedido (String nome)
    {
        this.pedidos.add (nome);
    }
    public boolean existePedido (String nome)
    {
        return (this.pedidos.contains(nome));
    }
    public boolean existePedido (int indice)
    {
        if (indice>this.pedidos.size()) return false;
        else return true;
    }
    public String retornaEmail (int indice)
    {
        String email = pedidos.get(indice);
        return email;
    }
            
    
    

    public ListaAmigos clone ()
    {
        return new ListaAmigos (this);
    }
    
        
}
