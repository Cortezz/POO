package Dados;

import java.io.IOException;
import java.io.*;
import java.util.Map;
import java.util.HashMap;
/**
 *  Classe cujas instancias vao armazenar uma base de dados de utilizadores
 */
public class BaseDeDadosUtilizadores implements Serializable
{
    
    private HashMap<String, Utilizador> utilizadores;
    
    /**
     * Construtores
     */
    public BaseDeDadosUtilizadores()
    {
        utilizadores = new HashMap<String, Utilizador>();
    }
    public BaseDeDadosUtilizadores(BaseDeDadosUtilizadores x)
    {
        this.utilizadores = x.getUtilizadores();
    }
    
    /**
     *  get
     */
    public HashMap<String, Utilizador> getUtilizadores()
    {
        HashMap<String, Utilizador> copia = new HashMap<String, Utilizador>();
        
        for(Map.Entry<String, Utilizador> x : utilizadores.entrySet())
        {
            Utilizador u = x.getValue();
            copia.put(x.getKey(), u.clone());
        }
        return copia;
    }
    
    /**
     *  Metodos Principais
     */
    public void insereUtilizador (Utilizador x) throws UtilizadorJaExiste 
    {
        try
        {
            if (utilizadores.containsKey (x.getEmail())) throw new UtilizadorJaExiste ("Esse utilizador já existe.");
            utilizadores.put(x.getEmail(), x.clone());
        }
        catch (UtilizadorJaExiste e) { System.out.println (e);}
    }
    public void removeUtilizador (Utilizador x)
    {
        utilizadores.remove (x.getEmail());
        
    }
    
    
    
    
    public boolean passwordCorrespondeEmail(String email, String password)
    {   
        if(utilizadores.containsKey(email))
        {
            Utilizador utilizador = utilizadores.get(email);
            
            if (utilizador.getPassword().equals(password)) 
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        
        return false;
    }
    
    public Utilizador getUtilizador(String email)
    {
        Utilizador utilizador = utilizadores.get(email);
        
        return utilizador;
    }
    public boolean existeUtilizador (String email)
    {
        return (utilizadores.containsKey (email));
    }
    public void adicionaPedido (String env, String rec)
    {
        Utilizador novo = utilizadores.get(rec);
        novo.enviaPedido (env);
    }
    public int getSize()
    {
        return utilizadores.size();
    }
    
    

    
    
    /**
     * Métodos Complementares
     */
    public BaseDeDadosUtilizadores clone()
    {
        return new BaseDeDadosUtilizadores(this);
    }
    
    
    public String toString()
    {
        StringBuilder output = new StringBuilder("*** Utilizador ***\n");
        
        for (Map.Entry<String, Utilizador> x : utilizadores.entrySet())
        {
            String u = x.getKey();
            output.append(u);
            output.append("\n");
        }
        return output.toString();
    }
    
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o==null || (this.getClass() != o.getClass())) return false;
        BaseDeDadosUtilizadores x = (BaseDeDadosUtilizadores) o;
        
        return (
               this.utilizadores == x.getUtilizadores() 
               );
    }
    
    
}
