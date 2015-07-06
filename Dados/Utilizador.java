package Dados;


/**
 * Write a description of class Utilizador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import Recordes.*;
import Actividades.*;
import java.util.GregorianCalendar;
import java.lang.StringBuilder;
import java.io.Serializable;


public class Utilizador implements Serializable
{
    //Variáveis de instancia
    
    private String email;
    private String password;
    private String nome;
    private String genero;
    private GregorianCalendar dataNascimento;
    private int altura;
    private float peso;
    private String desportoFavorito;
    private ListaAtividadeDesportivas atividades;
    private RegistoAtividades registo;
    private ListaAmigos amigos;
    private QuadroRecordes recordes;
    private boolean isAdmin;
    
    
    /**
     * Construtores 
     */
    public Utilizador ()
    {
        email = "";
        password = "";
        nome = "";
        genero = "";
        dataNascimento = new GregorianCalendar();
        altura = 0;
        peso = 0;
        desportoFavorito = "";
        atividades = new ListaAtividadeDesportivas();
        registo = new RegistoAtividades();
        amigos = new ListaAmigos();
        recordes = new QuadroRecordes();
        isAdmin = false;
    }
    public Utilizador (String email, String password, String nome, String genero, GregorianCalendar dataNascimento, 
    int altura, float peso, String desporto, boolean isAdmin)
    {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.genero = genero;
        this.dataNascimento = (GregorianCalendar) dataNascimento.clone();
        this.altura = altura;
        this.peso = peso;
        this.desportoFavorito = desporto;
        this.atividades = new ListaAtividadeDesportivas();
        this.registo = new RegistoAtividades();
        this.amigos = new ListaAmigos();
        this.recordes = new QuadroRecordes();
        this.isAdmin = isAdmin;
    }
    public Utilizador (Utilizador x)
    {
        this.email = x.getEmail();
        this.password = x.getPassword();
        this.nome = x.getNome();
        this.genero = x.getGenero();
        this.dataNascimento = x.getDataNascimento();
        this.altura = x.getAltura();
        this.peso = x.getPeso();
        this.desportoFavorito = x.getDesportoFavorito();
        this.atividades = new ListaAtividadeDesportivas (x.getAtividades());
        this.registo = new RegistoAtividades (x.getRegisto());
        this.amigos = new ListaAmigos (x.getListaAmigos());
        this.recordes = new QuadroRecordes (x.getRecordes());
        this.isAdmin = x.getAdminStatus();
    }
    
    /**
     * Getters
     */
    public String getEmail (){ return this.email; }
    public String getPassword (){return this.password;}
    public String getNome (){return this.nome;}
    public String getGenero (){ return this.genero;}
    public GregorianCalendar getDataNascimento (){return (GregorianCalendar) dataNascimento.clone();}
    public int getAltura () { return this.altura;}
    public float getPeso() { return this.peso;}
    public String getDesportoFavorito() { return this.desportoFavorito;}
    public ListaAtividadeDesportivas getAtividades()
    {
        ListaAtividadeDesportivas novo = new ListaAtividadeDesportivas (this.atividades);
        return novo;
    }
    public RegistoAtividades getRegisto ()
    {
        RegistoAtividades novo = new RegistoAtividades (this.registo);
        return novo;
    }
    public ListaAmigos getListaAmigos ()
    {
        ListaAmigos novo = new ListaAmigos (amigos.getAmigos(), amigos.getPedidos());
        return novo;
    }
    public QuadroRecordes getRecordes ()
    {
        QuadroRecordes novo = new QuadroRecordes (this.recordes);
        return novo;
    }
    public boolean getAdminStatus (){return this.isAdmin;}
    /**
     * Setters
     */
    
    public void setEmail (String email){this.email = email;}
    public void setNome (String nome) { this.nome = nome;}
    public void setPassword (String password){this.password = password;}
    public void setGenero (String genero){ this.genero = genero;}
    public void setDataNascimento (GregorianCalendar dataNascimento){ this.dataNascimento = (GregorianCalendar) dataNascimento.clone();}
    public void setAltura (int altura) { this.altura = altura;}
    public void setPeso (float peso) { this.peso = peso;}
    public void setDesportoFavorito (String desporto) { this.desportoFavorito = desporto;}
    public void setAdminStatus (boolean admin) { this.isAdmin = admin;}

    
    //Adiciona
    public void adicionaDesporto (AtividadeDesportiva x)
    {
        atividades.adicionaAtividadeDesportiva (x);
    }
    
    //Retorna a string com todas as informações do perfil
    public String mostraPerfil ()
    {
        StringBuilder output = new StringBuilder ();
        output.append("1 - Email: " + this.email + "\n");
        output.append("2 - Password: "+this.password +"\n");
        output.append("3 - Nome: " + this.nome + "\n");
        output.append("4 - Género: " + this.genero + "\n");
        output.append("5 - Data de Nascimento: " + 
                        this.dataNascimento.get(GregorianCalendar.DAY_OF_MONTH) + "/" +
                        (this.dataNascimento.get(GregorianCalendar.MONTH)+1) + "/" + 
                        this.dataNascimento.get(GregorianCalendar.YEAR) + "\n");
        output.append("6 - Altura: "+this.altura+"\n");
        output.append("7 - Peso:"+this.peso+"\n");
        output.append("8 - Desporto favorito:"+this.desportoFavorito+"\n");
       return output.toString();
    }
    
    /**
     *  Metodos relacionados com actividades
     */
    public String mostraAtividade(int indice)
    {
        String novo = registo.mostraAtividade (indice);
        return novo;
    }
    public void removeAtividade (int indice)
    {
        AtividadeDesportiva aux = registo.getAtividadeRegisto(indice);
        atividades.removeAtividade(aux);
    }
    public String mostraUltimasAtividades (int num)
    {
        registo.preencheRegisto (this.atividades.atividadesPorData());
        return registo.toString(num);
    }
    public String procuraAtividadePorData (GregorianCalendar data)
    {
        return registo.mostraAtividadesPorData (data);
    }
    public int getSizeAtividadesRealizadas(){
        return atividades.size();
    }
    
    /**
     *  Metodos relacionados com a lista de amigos
     */
    public boolean eAmigo (String email)
    {
        return amigos.existeAmigo(email);
    }
    public int totalAmigos ()
    {
        return amigos.totalAmigos();
    }
    public String mostraAmigos ()
    {
        String resultado = amigos.nomeAmigos();
        return resultado;
    }
    public void adicionaAmigo (String nome, String email)
    {
        amigos.adicionaAmigo (nome, email);
    }
    public void removeAmigo (String email)
    {
        amigos.removeAmigo (email);
    }
 
    /**
     *  Metodos relacionados com os pedidos de amizade
     */
    public boolean existePedido (int indice)
    {
        return (this.amigos.existePedido(indice));
    }
    public void removePedido (int indice)
    {
        if (this.amigos.existePedido(indice)) this.amigos.removePedido (indice);
    }
    public void enviaPedido (String nome)
    {
        if (!this.amigos.existePedido(nome))this.amigos.enviaPedido (nome);
    }
    public int numeroPedidos ()
    {
        return this.amigos.numeroPedidos();
    }
    public String mostraPedidos ()
    {
        return this.amigos.listaPedidos();
    }
    public String emailNumIndicePedidos (int indice)
    { 
        return this.amigos.retornaEmail (indice);
    }
    
    
    
    /**
     *  Metodos relacionados com a estatistica
     */
     public String estatisticaMensal (int mes, int ano)
    {
        int tempo = atividades.informacaoMensalTempo (mes,ano);
        double distancia = atividades.informacaoMensalDistancia (mes,ano);
        double total = atividades.numeroAtividadesPorMes(mes);
        int caloriasTotal = atividades.informacaoMensalCalorias (mes,ano);
        double tmedio;
        double distmedia;
        StringBuilder s = new StringBuilder ();
        if (total==0) 
        {
            s.append ("Este utilizador não efectou nenhuma actividade este ano\n");
            return s.toString();
        }
        else
        {
            tmedio = tempo/total;
            distmedia = Input.round (distancia/total,2);
            s.append ("Neste mês registou "+(int)total+" actividades\n");
            s.append ("\n> Tempo\n");
            if (tempo>60) s.append ("Tempo total despendido: "+math.IntToFormatedHour(tempo)+" h\n");
            else s.append ("Tempo total despendido: "+tempo+"\n");
            if (tmedio>60) s.append ("Tempo médio despendido por actividade: "+math.IntToFormatedHour((int)tmedio)+" h\n");
            else s.append ("Tempo médio despendido por actividade: "+tmedio+"\n");
            s.append ("\n>Distância\n");
            s.append ("Distância total percorrida: "+distancia+" Km\n");
            s.append ("Distância média por actividade: "+distmedia+" Km\n");
            s.append ("\n>Calorias\n");
            s.append ("Calorias gastas: "+caloriasTotal+" \n");
            s.append ("Calorias gastas em média por actividade: "+caloriasTotal/(int)total+"\n");
            return s.toString();
        }
    }
    public String estatisticaAnual (int ano)
    {
        int tempo = atividades.informacaoAnualTempo (ano);
        double distancia = atividades.informacaoAnualDistancia (ano);
        int total = atividades.numeroAtividadesPorAno(ano);
        int caloriasTotal = atividades.informacaoAnualCalorias (ano);
        StringBuilder s = new StringBuilder ();
        if (total==0) 
        {
            s.append ("Este utilizador não efectou nenhuma actividade este ano\n");
            return s.toString();
        }
        else
        {
            s.append ("Neste ano registou "+(int)total+" actividades\n");
            s.append ("\n> Tempo\n");
            if (tempo>60) s.append ("Tempo total despendido: "+math.IntToFormatedHour(tempo)+" h\n");
            else s.append ("Tempo total despendido: "+tempo+" min\n");
            if ((tempo/total)>60) s.append ("Tempo médio despendido por actividade: "+math.IntToFormatedHour (tempo/total)+" h\n");
            else s.append ("Tempo médio despendido por actividade: "+tempo/total+"\n");
            s.append ("\n>Distância\n");
            s.append ("Distância total percorrida: "+distancia+" Km\n");
            s.append ("Distância média por actividade: "+distancia/(double)total+" Km\n");
            s.append ("\n>Calorias\n");
            s.append ("Calorias gastas: "+caloriasTotal+" \n");
            s.append ("Calorias gastas em média por actividade: "+caloriasTotal/(int)total+"\n");        
            return s.toString();
        }
    }
    
    
    /**
     *  Metodos relacionados com os recordes
     */
    public void testaRecorde (String desporto, int tempo, double distancia)
    {
        if (!recordes.existeRecorde (desporto))
        {
            switch (desporto)
            {
                case "Futebol": RecordeFutebol rf = new RecordeFutebol();
                                recordes.inicializaRecorde (desporto,rf);
                                break;
                case "Ginasio": RecordeGinasio rg = new RecordeGinasio();
                                recordes.inicializaRecorde (desporto,rg);
                                break;
                case "Ciclismo":RecordeCiclismo rcicl  = new RecordeCiclismo();
                                recordes.inicializaRecorde (desporto,rcicl);
                                break;
                case "Caminhada":RecordeCaminhada rcam = new RecordeCaminhada();
                                recordes.inicializaRecorde (desporto,rcam);
                                break;
                case "Corrida": RecordeCorrida rcor = new RecordeCorrida();
                                recordes.inicializaRecorde (desporto,rcor);
                                break;
            }
        }
        recordes.testaRecorde (desporto, tempo, distancia);
    }    
    public String listaAtividadesRecordes ()
    {
        return recordes.listaRecordes();
    }
    public String recordeGinasio()
    {
        return recordes.recordeGinasio();
    }
    public String recordeCiclismo()
    {
        return recordes.recordeCiclismo();
    }
    public String recordeCaminhada()
    {
        return recordes.recordeCaminhada();
    }
    public String recordeFutebol()
    {
        return recordes.recordeFutebol();
    }
    public String recordeCorrida()
    {
        return recordes.recordeCorrida();
    }
        
    
    
    

    
    
    
    /**
     * M??todos Complementares
     */
    public Utilizador clone()
    {
        return new Utilizador(this);
    }
    
    public String toString()
    {
        StringBuilder output = new StringBuilder ();
        output.append("Email: " + this.email + "\n");
        output.append("Nome: " + this.nome + "\n");
        output.append("Género: " + this.genero + "\n");
        output.append("Data de Nascimento: " + 
                        this.dataNascimento.get(GregorianCalendar.DAY_OF_MONTH) + "/" +
                        (this.dataNascimento.get(GregorianCalendar.MONTH)+1) + "/" + 
                        this.dataNascimento.get(GregorianCalendar.YEAR) + "\n");
        output.append("Altura: "+this.altura+"\n");
        output.append("Peso:"+this.peso+"\n");
        output.append("Desporto favorito:"+this.desportoFavorito+"\n");
       return output.toString();
    }
    
    
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o==null || (this.getClass() != o.getClass())) return false;
        Utilizador x = (Utilizador) o;
        
        return (this.email == x.getEmail() &&
               this.nome == x.getNome() &&
               this.genero == x.getGenero() &&
               this.dataNascimento.get(GregorianCalendar.DAY_OF_MONTH) == 
               x.dataNascimento.get(GregorianCalendar.DAY_OF_MONTH) &&
               this.dataNascimento.get(GregorianCalendar.MONTH) == 
               x.dataNascimento.get(GregorianCalendar.MONTH) &&
               this.dataNascimento.get(GregorianCalendar.YEAR) == 
               x.dataNascimento.get(GregorianCalendar.YEAR) &&
               this.atividades.equals (x.getAtividades())
               );
    }
}