package Misc;

import Dados.*;
import java.util.List;
import java.util.ArrayList;

public class Menu
{
    private List<String> opcoes;
    private int opc;

    public void Menu ()
    {
        opcoes = new ArrayList<String>();
        opc = 0;
    }
    public Menu (String[] op)
    {
        this.opcoes = new ArrayList<String>();
        for (String linha:  op)
            this.opcoes.add (linha);
        this.opc = 0;   
    }
    public int getOpcao () { return this.opc;}
    
    public void mostraMenu ()
    {
        int i;
        for (i=0;i<opcoes.size();i++)
            System.out.println (opcoes.get(i));
        System.out.println ("0 - Sair\n");
        Separador.Barra();
        System.out.print ("Op????o:");
    }
    public void executa ()
    {
        do 
        {   
            mostraMenu();
            this.opc = ControloDeErros.Input.getOpcaoValida("", 0, opcoes.size());
            System.out.println ("\f\n");
        } while (this.opc ==-1);
    }
    public int lerOpcao () 
    {
       int opcao = Input.lerInt(); 
       if (opcao<0 || opcao>this.opcoes.size())
            return -1;
       return opcao;
    }
    
    
}
