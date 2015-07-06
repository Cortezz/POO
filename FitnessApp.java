import java.io.*;
import Actividades.*;
import Recordes.*;
import Dados.*;
import Eventos.*;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import Misc.*;

public class FitnessApp implements Serializable
{
    public static Menu menuEntrada, menuUtilizador, menuPerfil, menuAtividades, menuGestaoAtividades, 
    menuGestaoAmigos, menuAmigo, menuAdmin, menuEventos, menuGestorEventos, menuUtilizadorEventos;
    public static BaseDeDados baseDeDados;
    public static Utilizador user;
    public static BaseDeDadosUtilizadores utilizadores;
    public static BaseDeDadosEventos eventos;

    public static void main (String[] args) throws UtilizadorJaExiste
    {
        CarregarDados();
        CarregarMenusEntrada();
        utilizadores = baseDeDados.getUtilizadores();
        eventos = baseDeDados.getEventos();
        boasVindas();
        removeEventosDesatualizados();
        do{
            System.out.println ("");
            Separador.Barras();
            System.out.println ("                     Início de Sessão\n");
            menuEntrada.executa();
            switch (menuEntrada.getOpcao())
                {
                    case 1: registo();
                            break;
                    case 2: login();
                            break;
                    case 0: System.out.println ("Volte em breve!\n");
                            break;
                 }
        } while (menuEntrada.getOpcao()!=0);
         try 
         {
             baseDeDados.gravaEstado("users.db");
         }
        catch (IOException e) {System.out.println("A operação de gravação falhou");}
    }
    public static void mainApp () throws UtilizadorJaExiste
    {
        if (!user.getAdminStatus())
        {
            do
            {
                Separador.Barras();
                System.out.println ("                     Menu Principal\n");
                if (user.numeroPedidos()>0) System.out.println ("\n        ATENÇÃO: Tem pedidos de amizade pendentes.\n");
                menuUtilizador.executa();
                switch (menuUtilizador.getOpcao())
                    {
                        case 1: gestaoAtividades();
                                    break;
                        case 2: gestaoAmigos();
                                break;
                        case 3: alteraPerfil();
                                break;
                        case 0: break;
                        default: System.out.println ("Erro!");
                                 break;
                    }
            } while (menuUtilizador.getOpcao()!=0);
        }
        else 
        {
            do
            {
                Separador.Barras();
                System.out.println ("                     Menu Principal\n");
                if (user.numeroPedidos()>0) System.out.println ("\n        ATENÇÃO: Tem pedidos de amizade pendentes.\n");
                menuAdmin.executa();
                switch (menuAdmin.getOpcao())
                    {
                        case 1: gestaoAtividades();
                                    break;
                        case 2: gestaoAmigos();
                                break;
                        case 3: alteraPerfil();
                                break;
                        case 4: admin();
                                break;
                        case 0: break;
                        default: System.out.println ("Erro!");
                                 break;
                    }
            } while (menuAdmin.getOpcao()!=0);
        }
        
            
    }
    
    
    
    
    public static void registo() throws UtilizadorJaExiste
    {
        Utilizador d ;
        String nome,email,pwd,genero,desporto,DataString, pwdAdmin;
        boolean isAdmin = false;
        float peso;
        int altura,ano,mes,dia;
        GregorianCalendar data;
        int opc,flag;
        
        Separador.Barras();
        System.out.println ("                          Registo\n");
        System.out.print ("Nome: ");
        nome = Input.lerString();
        email = ControloDeErros.Input.getEmailValido("E-Mail: ");
        pwd = ControloDeErros.Input.getPasswordsValidas();
        System.out.print ("Género (Masculino ou Feminino): ");
        genero = ControloDeErros.Input.getEscolhaBinaria("Masculino", "Feminino");
        altura = ControloDeErros.Input.getOpcaoValida("Qual a sua altura: ", 50, 300);
        peso = ControloDeErros.Input.getOpcaoValida("Qual o seu peso: ", 20, 700);
        data = ControloDeErros.Input.getDataValida("Insira a sua data de Nascimento (DD MM AAAA): ");
        System.out.print ("Desporto Favorito: ");
        desporto = Input.lerString();
        do
        {
            flag = 0;
            Separador.Barra();
            System.out.println ("                          Tipo de conta\n");
            System.out.println ("1 - Conta normal");
            System.out.println ("2 - Conta administrador");
            opc = ControloDeErros.Input.getOpcaoValida("Opção: ", 1, 2);
            switch (opc)
            {
                case 1: isAdmin = false; 
                        flag = 1;
                        break;
                case 2: System.out.println ("Digite a password de administrador:");
                        if ((pwdAdmin = Input.lerString()).equals("123"))
                        {
                            isAdmin = true;
                            flag = 1;
                        }
                        else System.out.println ("Password de administrador errada.");
                        break;
                default: System.out.println ("Erro!");
            }
        }while (flag!=1);
        
        d = new Utilizador (email,pwd,nome,genero,data,altura,peso,desporto,isAdmin);
        utilizadores.insereUtilizador (d);
        System.out.println ("\n                   Registado com sucesso!");
    }
    public static void login () throws UtilizadorJaExiste
    {
        String email,pwd;
        
        Separador.Barras();
        System.out.println ("                          Login\n");
        email = ControloDeErros.Input.getEmailValido("E-mail: ");
        System.out.print ("Password:");
        pwd = Input.lerString();
        if (utilizadores.passwordCorrespondeEmail(email,pwd))
        {
            System.out.println ("\n                     Login efectuado!");
            user = utilizadores.getUtilizador (email);
            mainApp();
        }
        else 
        {   
            System.out.println ("Password e/ou E-mail errados.");
        }
    }
    
    
    
    public static void alteraPerfil   ()
    {
        String s;
        Separador.Barra();
        System.out.println ("                          Perfil Actual\n");
        System.out.println (user.mostraPerfil());
        do 
        {
            Separador.Barras();
            System.out.println ("                     Alteração do Perfil\n");                       
            menuPerfil.executa();
            switch (menuPerfil.getOpcao())
            {
                case 1: s = ControloDeErros.Input.getEmailValido("Digite o novo e-mail: ");
                        user.setEmail (s);
                        break;
                case 2: s = ControloDeErros.Input.getPasswordsValidas();
                        user.setPassword (s);
                        break;
                case 3: System.out.print ("Digite o novo nome: ");
                        s = Input.lerString();
                        user.setNome(s);
                        break;
                case 4: System.out.print ("Insira o genero: ");
                        s = ControloDeErros.Input.getEscolhaBinaria("Masculino", "Feminino");
                        user.setGenero(s);
                        break;
                case 5: GregorianCalendar data = ControloDeErros.Input.getDataValida("Insira a sua data de nascimento (DD MM AAAA): ");
                        user.setDataNascimento (data);
                        break;
                case 6: int altura = ControloDeErros.Input.getOpcaoValida("Digite a nova altura: ", 50, 700);
                        user.setAltura (altura);
                        break;
                case 7: float peso = (float)ControloDeErros.Input.getPesoValido();
                        user.setPeso (peso);
                        break;
                case 8: System.out.print ("Digite o novo desporto favorito:");
                        s = Input.lerString();
                        user.setDesportoFavorito(s);
                        break;
                case 0: break;
                default: System.out.print ("Erro!");
            }
        } while (menuPerfil.getOpcao()!=0);                
    }   
    
    
    
    
    
    
    
    public static void gestaoAtividades () throws UtilizadorJaExiste
    {
        do 
        {
            Separador.Barras();
            System.out.println ("                     Gestão de Actividades\n");
            menuGestaoAtividades.executa();
            switch (menuGestaoAtividades.getOpcao())
            {
                case 1: inserirDesporto();
                        break;
                case 2: registoAtividades();
                        break;
                case 3: consultaAtividade();
                        break;
                case 4: estatistica();
                        break;
                case 5: recordes();
                        break;
                case 6: eventos();
                        break;
                case 0: break;
                default: System.out.println ("Erro!");
                         break;
            }
        } while (menuGestaoAtividades.getOpcao()!=0);
    }
        
    public static void inserirDesporto ()
    {
        int tempo,desnivel;
        GregorianCalendar data;
        String dataString,clima;
        double distancia;
        String perguntaData = "Quando foi realizada a atividade (DD MM AAAA)? ";
        String perguntaDistancia = "Distância percorrida(em km): ";
        String perguntaClima = "Clima (Nevar, Chuva, Nublado, Sol): ";
        String perguntaDesnivel = "Desnível (em metros): ";
        int desnivelMaximo = 9000;
        
        Separador.Barras();
        System.out.println ("                      Inserção de Actividades\n");
        menuAtividades.executa();
        if (menuAtividades.getOpcao()!=0)
        {
            switch (menuAtividades.getOpcao())
            {
                case 1: Separador.Barra();
                        System.out.println ("                     Futebol");
                        tempo = ControloDeErros.Input.getTempoValido();
                        data = ControloDeErros.Input.getDataValida(perguntaData);
                        distancia = ControloDeErros.Input.getDistanciaValida(perguntaDistancia);
                        System.out.print (perguntaClima);
                        clima = ControloDeErros.Input.getClimaValido();
                        Futebol f = new Futebol (tempo,distancia,data,clima);
                        user.adicionaDesporto (f);
                        user.testaRecorde ("Futebol", tempo, distancia);
                        break;
                case 2: Separador.Barra();
                        System.out.println ("                     Ginásio");
                        tempo = ControloDeErros.Input.getTempoValido();
                        data = ControloDeErros.Input.getDataValida(perguntaData);
                        distancia = ControloDeErros.Input.getDistanciaValida(perguntaDistancia);
                        System.out.print (perguntaClima);
                        clima = ControloDeErros.Input.getClimaValido();
                        Ginasio g = new Ginasio (tempo,data);
                        user.adicionaDesporto (g);
                        RecordeGinasio rg = new RecordeGinasio ();
                        user.testaRecorde ("Ginasio",tempo,distancia);
                        break;
                case 3: Separador.Barra();
                        System.out.println ("                     Corrida");
                        tempo = ControloDeErros.Input.getTempoValido();
                        data = ControloDeErros.Input.getDataValida(perguntaData);
                        distancia = ControloDeErros.Input.getDistanciaValida(perguntaDistancia);
                        System.out.print (perguntaClima);
                        clima = ControloDeErros.Input.getClimaValido();
                        desnivel = ControloDeErros.Input.getOpcaoValida(perguntaDesnivel, 0, desnivelMaximo);
                        Corrida c = new Corrida (tempo,distancia,data,clima,desnivel);
                        user.adicionaDesporto (c);
                        user.testaRecorde ("Corrida", tempo, distancia);
                        break;
                case 4: Separador.Barra();
                        System.out.println ("                     Caminhada");
                        tempo = ControloDeErros.Input.getTempoValido();
                        data = ControloDeErros.Input.getDataValida(perguntaData);
                        distancia = ControloDeErros.Input.getDistanciaValida(perguntaDistancia);
                        System.out.print (perguntaClima);
                        clima = ControloDeErros.Input.getClimaValido();
                        desnivel = ControloDeErros.Input.getOpcaoValida(perguntaDesnivel, 0, desnivelMaximo);
                        Caminhada ca = new Caminhada(tempo,distancia,data,clima,desnivel);
                        user.adicionaDesporto (ca);
                        user.testaRecorde ("Caminhada", tempo, distancia);
                        break;
                case 5: Separador.Barra();
                        System.out.println ("                     Ciclismo");
                        tempo = ControloDeErros.Input.getTempoValido();
                        data = ControloDeErros.Input.getDataValida(perguntaData);
                        distancia = ControloDeErros.Input.getDistanciaValida(perguntaDistancia);
                        System.out.print (perguntaClima);
                        clima = ControloDeErros.Input.getClimaValido();
                        desnivel = ControloDeErros.Input.getOpcaoValida(perguntaDesnivel, 0, desnivelMaximo);
                        Ciclismo ci = new Ciclismo (tempo,distancia,data,clima,desnivel);
                        user.adicionaDesporto (ci);
                        user.testaRecorde ("Ciclismo", tempo, distancia);
                        break;
            }
        }
        else System.out.println ("Inserção cancelada.");
    }
          
    public static void registoAtividades ()
    {
        int opc, actividade;
        opc = 1;
        do{
            Separador.Barra();
            System.out.println ("                       Últimas dez actividades\n");
            System.out.println (user.mostraUltimasAtividades(10));
            Separador.Barra();
            System.out.println ("1 - Ver informação sobre uma dada actividade");
            System.out.println ("0 - Sair");
            opc = ControloDeErros.Input.getOpcaoValida("Opção: ", 0, 1);
            Separador.Barra();
            switch (opc)
            {
                case 1: actividade = ControloDeErros.Input.getOpcaoValida("Quer ver informação sobre que actividade? ", 0 , user.getSizeAtividadesRealizadas());
                        Separador.Barra();
                        System.out.println (user.mostraAtividade (actividade));
                        Separador.Barra();
                case 0: break;
                default : System.out.println ("Erro!");
            } 
        } while (opc!=0); 
    }
    
    public static void consultaAtividade()
    {
        GregorianCalendar data;
        int opc,opc1,indice;
        String dataString;
        opc = 1;
        do
        {
            Separador.Barras();
            System.out.println ("                          Consulta e remoção de Actividades\n");
            System.out.println (user.mostraUltimasAtividades(10));
            Separador.Barra();
            System.out.println ("1 - Procurar uma actividade (por data)");
            System.out.println ("0 - Sair");
            opc = ControloDeErros.Input.getOpcaoValida("Opção: ", 0 , 1);
            Separador.Barra();
            switch (opc)
            {
                case 1: data = ControloDeErros.Input.getDataValida("Data (DD MM AAAA): ");
                        Separador.Barra();
                        System.out.println (user.procuraAtividadePorData (data));
                        Separador.Barra();
                        System.out.println ("1 - Remover actividade");
                        System.out.println ("0 - Sair");
                        System.out.print ("Opção:");
                        opc1 = Input.lerInt();
                        Separador.Barra();
                        switch (opc1)
                        {
                            case 1: System.out.println ("Qual o índice da actividade que quer remover?");
                                    indice = Input.lerInt();
                                    user.removeAtividade (indice);
                                    System.out.println ("              Actividade removido com sucesso!");
                                    break;
                            case 0: break;
                            default: System.out.println ("Erro!");
                        }
                case 0: break;
                default: System.out.println ("Erro!");
            }
        } while (opc!=0);
                
    }
    public static void estatistica()
    {
        GregorianCalendar hoje = new GregorianCalendar();
        int opc,mes,ano;
        do
        {
            Separador.Barras();
            System.out.println ("                          Estatística\n");
            System.out.println ("1 - Estatística Mensal");
            System.out.println ("2 - Estatística Anual");
            System.out.println ("0 - Sair");
            System.out.print ("Opção:");
            opc = Input.lerInt();
            Separador.Barra();
            switch (opc)
            {
                case 1: System.out.println ("Qual o mês?");
                        mes = ControloDeErros.Input.getOpcaoValida("Qual o mês? ", 1 , 12) - 1 ;
                        ano = ControloDeErros.Input.getOpcaoValida("Qual o ano?", 1900, hoje.get(GregorianCalendar.YEAR)) ;
                        Separador.Barra();
                        System.out.println ("                   Estatística Mensal ("+(mes+1)+"/"+ano+")\n");
                        System.out.println (user.estatisticaMensal (mes,ano));
                        break;
                case 2: ano = ControloDeErros.Input.getOpcaoValida("Qual o ano?", 1900, hoje.get(GregorianCalendar.YEAR)) ;
                        Separador.Barra();
                        System.out.println ("                   Estatística Anual ("+ano+")\n");
                        System.out.println (user.estatisticaAnual (ano));
                        break;
                case 0: break;
                default: System.out.println ("Erro!");
            }
        } while (opc!=0);
    }
    public static void recordes()
    {
        String opc;
        do
        {
            Separador.Barras();
            System.out.println ("                          Recordes\n");
            System.out.println (user.listaAtividadesRecordes());
            System.out.println ("\n Digite \"sair\" para sair");
            Separador.Barra();
            System.out.print ("Quer ver os recordes de que actividade? ");
            opc = Input.lerString();
            Separador.Barra();
            switch (opc)
            {
                case "Futebol":   Separador.Barra();
                                  System.out.println ("                          Recordes de Futebol\n");
                                  System.out.println (user.recordeFutebol());
                                  break;
                case "Caminhada": Separador.Barra();
                                  System.out.println ("                          Recordes de Caminhada\n");
                                  System.out.println (user.recordeCaminhada());
                                  break;
                case "Ginasio":   Separador.Barra();
                                  System.out.println ("                          Recordes de Ginásio\n");
                                  System.out.println (user.recordeGinasio());
                                  break;
                case "Corrida":   Separador.Barra();
                                  System.out.println ("                          Recordes de Corrida\n");
                                  System.out.println (user.recordeCorrida());
                                  break;
                case "Ciclismo":  Separador.Barra();
                                  System.out.println ("                          Recordes de Ciclismo\n");
                                  System.out.println (user.recordeCiclismo());
                                  break;
                case "sair": break;
                default: System.out.println ("Erro!");
            }
        } while (!opc.equals("sair"));
    }
    public static void eventos () throws UtilizadorJaExiste
    {
        do 
        {
            Separador.Barras();
            System.out.println ("                     Eventos\n");
            menuUtilizadorEventos.executa();
            switch (menuUtilizadorEventos.getOpcao())
            {
                case 1: mostrarEventos();
                        break;
                case 2: removeInscricao();
                        break;
                case 3: verEventosInscrito();
                        break;
                case 0: break;
                default: System.out.println ("Erro!");
                         break;
            }
        } while (menuUtilizadorEventos.getOpcao()!=0);
    }
    public static void mostrarEventos () throws UtilizadorJaExiste
    {
        GregorianCalendar hoje = new GregorianCalendar();
        int opc,indice;
        String opc1;
        Evento novo;
        opc = 1;
        do{
            Separador.Barra();
            System.out.println ("                       Eventos disponíveis\n");
            System.out.println (eventos.mostraEventos());
            Separador.Barra();
            System.out.println ("1 - Ver informação dum evento");
            System.out.println ("0 - Sair");
            opc = ControloDeErros.Input.getOpcaoValida("Opção: ", 0, 1);
            Separador.Barra();
            switch (opc)
            {
                case 1: indice = ControloDeErros.Input.getOpcaoValida("Quer ver informação sobre que evento,", 1, eventos.size());
                        Separador.Barra();
                        System.out.println (eventos.mostraEventos ());
                        novo = eventos.getEventoIndex(indice-1);
                        System.out.println (novo.toString());
                        Separador.Barra();
                        System.out.print ("Quer inscrever-se neste evento (Sim/Não)? ");
                        if (hoje.compareTo(novo.getDataLimiteInscricao()) < 0){
                            System.out.println("Já ultrapassou a data limite de inscrição");
                            break;
                        }
                        opc1 = ControloDeErros.Input.getEscolhaBinaria("Sim", "Não");
                        switch (opc1)
                        {
                            case "Sim": novo.inscreveUtilizador (user);
                                        System.out.println ("              Inscreveu-se com sucesso!");                                        
                                        break;
                            case "Não": break;
                            default : System.out.println ("Erro!");
                        }
                case 0: break;
                default : System.out.println ("Erro!");
            } 
        } while (opc!=0);   
    }
    public static void removeInscricao ()
    {
        int opc,indice;
        String opc1;
        Evento novo;
        opc = 1;
        do{
            Separador.Barra();
            System.out.println ("           Eventos nos quais o utilizador está inscrito\n");
            System.out.println (eventos.mostraEventosInscrito(user.getEmail()));
            Separador.Barra();
            System.out.println ("1 - Ver informação dum evento");
            System.out.println ("0 - Sair");
            opc = ControloDeErros.Input.getOpcaoValida("Opção: ", 0, 1);
            Separador.Barra();
            switch (opc)
            {
                case 1: indice = ControloDeErros.Input.getOpcaoValida("Quer ver informação sobre que evento,", 1, eventos.sizeEventosInscritoUtilizador(user.getEmail()));
                        Separador.Barra();
                        novo = eventos.getEventoIndex(indice-1);
                        System.out.println (novo.toString());
                        Separador.Barra();
                        System.out.print ("Quer remover a sua inscrição neste evento (Sim/Não)? ");
                        opc1 = ControloDeErros.Input.getEscolhaBinaria("Sim", "Não");
                        switch (opc1)
                        {
                            case "Sim": novo.desinscreveUtilizador (user);
                                        System.out.println ("              Inscrição removida com sucesso!");
                                        break;
                            case "Não": break;
                            default : System.out.println ("Erro!");
                        }
                case 0: break;
                default : System.out.println ("Erro!");
            } 
        } while (opc!=0);  
        
    }
    public static void verEventosInscrito ()
    {
            int opc, indice;
            Separador.Barra();
            System.out.println ("                   Eventos nos quais o utilizador está inscrito\n");
            System.out.println (eventos.mostraEventosInscrito(user.getEmail()));
            Separador.Barra();
            System.out.println ("1 - Ver informação dum evento");
            System.out.println ("0 - Sair");
            opc = ControloDeErros.Input.getOpcaoValida("Opção", 0, 1 );
            switch (opc)
            {
                case 1: System.out.println ("Quer ver informação sobre que evento?");
                        indice = ControloDeErros.Input.getOpcaoValida("Quer ver informação sobre que evento,", 1, eventos.sizeEventosInscritoUtilizador(user.getEmail()));
                        Separador.Barra();
                        Evento novo = eventos.getEventoIndex(indice-1);
                        System.out.println (novo.toString());
                        Separador.Barra();
                        break;
                case 0: break;
                default: System.out.println ("Erro!");
            }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public static void gestaoAmigos()
    {
        do 
        {
            Separador.Barras();
            System.out.println ("                     Gestão de Amigos\n");
            menuGestaoAmigos.executa();
            switch (menuGestaoAmigos.getOpcao())
            {
                case 1: verListaAmigos();
                        break;
                case 2: adicionaAmigo();
                        break;
                case 3: pedidosAmizade();
                        break;
                case 4: removerAmigos(); 
                        break;
                case 5: infoAmigos();
                        break;
                case 0: break;
                default: System.out.println ("Erro!");
                         break;
            }
        } while (menuGestaoAmigos.getOpcao()!=0);
    }
    
    public static void verListaAmigos()
    {
        Separador.Barra();
        System.out.println ("                     Nº de amigos:"+user.totalAmigos()+"");
        System.out.println ("                     Lista de Amigos\n");
        System.out.println (user.mostraAmigos());
    }
    public static void adicionaAmigo ()
    {
        String s;
        s = ControloDeErros.Input.getEmailValido("Digite o e-mail da pessoa que quer adicionar: ");
        if (utilizadores.existeUtilizador (s))
        {
            if (!user.eAmigo(s))
            {
            utilizadores.adicionaPedido (user.getEmail(),s);
            System.out.println ("\n                     O seu pedido foi enviado!");
            }
            else System.out.println ("\n                Esse utilizador já é seu amigo!");
        }
        else System.out.println ("\n                     Esse utilizador não existe!");
    }
    public static void pedidosAmizade()
    {
        int opc, indice;
        do
        {
            Separador.Barra();
            System.out.println ("                     Lista de Pedidos\n");
            System.out.println (user.mostraPedidos());
            if ((user.numeroPedidos())==0) System.out.println ("Não tem nenhum pedido de amizade :(");
            Separador.Barra();
            System.out.println ("1 - Aceitar pedidos de amizade");
            System.out.println ("0 - Sair");
            opc = ControloDeErros.Input.getOpcaoValida("Opção: ", 0, 1);
            switch (opc)
            {
                case 1: System.out.println ("Quer aceitar que pedido?");
                        indice = ControloDeErros.Input.getOpcaoValida("Quer aceitar que pedido? ", 1, user.numeroPedidos() );
                        String email = user.emailNumIndicePedidos(indice);
                        Utilizador novo = utilizadores.getUtilizador(email);
                        user.adicionaAmigo (novo.getEmail(), novo.getNome());
                        novo.adicionaAmigo (user.getEmail(), user.getNome());
                        user.removePedido (indice);
                        System.out.println ("                     Pedido aceite!");
                case 0: break;
                default: System.out.println ("Erro");
            }
        } while (opc!=0);
    }
    public static void removerAmigos ()
    {
        int opc=1;
        String email;
        do
        {
            Separador.Barras();
            System.out.println ("                          Remoção de Amigos\n");
            System.out.println (user.mostraAmigos());
            Separador.Barra();
            System.out.println ("1 - Remover Amigos");
            System.out.println ("0 - Sair");
            opc = ControloDeErros.Input.getOpcaoValida("Opção: ", 0, 1 );
            Separador.Barra();
            switch (opc)
            {
                case 1: email = ControloDeErros.Input.getEmailValido("Qual o e-mail do amigo que pretende remover?");
                        if (user.eAmigo(email))
                        {
                            Utilizador novo = utilizadores.getUtilizador (email);
                            novo.removeAmigo (user.getEmail());
                            user.removeAmigo (email);
                            System.out.println ("              Amigo removido com sucesso!");
                        }
                        else System.out.println ("Remoção falhada.");
                        break;
                case 0: break;
                default: System.out.println ("Erro!");
            }
        } while (opc!=0);
        
    }
    
    public static void infoAmigos ()
    {
        int opc;
        String email;
        do
        {
            Separador.Barras();
            System.out.println ("                     Lista de Amigos\n");
            System.out.println (user.mostraAmigos());
            Separador.Barras();
            System.out.println ("1- Ver informação de um amigo");
            System.out.println ("0 - Sair");
            opc = ControloDeErros.Input.getOpcaoValida("Opção", 0, 1);
            switch (opc)
            {
                case 1: email = ControloDeErros.Input.getEmailValido("Qual o e-mail do amigo que pretende ver informação?");
                        if (user.eAmigo(email)) cuscarAmigo(email);
                        else System.out.println ("Esse utilizador não consta na sua lista de amigos");
                        break;
                case 0: break;
                default: System.out.println ("Erro");
            }
        }while (opc!=0);
    }
    public static void cuscarAmigo (String email)
    {
        Utilizador amigo = utilizadores.getUtilizador (email);
        do
        {
            Separador.Barras();
            System.out.println ("                     Perfil do "+amigo.getNome()+"\n");
            menuUtilizador.executa();
            switch (menuUtilizador.getOpcao())
                {
                    case 1: GestaoAtividadesAmigo(email);
                            break;
                    case 2: Separador.Barra();
                            System.out.println ("                     Lista de Amigos\n");
                            System.out.println (amigo.mostraAmigos());
                            break;
                    case 3: Separador.Barra();
                            System.out.println ("                     Perfil\n");
                            System.out.println (amigo.toString());
                            break;
                    case 0: break;
                    default: System.out.println ("Erro!");
                             break;
                }
            } while (menuUtilizador.getOpcao()!=0);
    }
    
    
    public static void GestaoAtividadesAmigo (String email)
    {
        int opc, indice;
        do
        {
            Separador.Barra();
            System.out.println ("                     Gestão de Actividades\n");

            System.out.println ("1 - Ver as últimas dez actividades");
            System.out.println ("2 - Ver a estatística");
            System.out.println ("3 - Recordes das actividades");
            System.out.println ("0 - Sair");
            opc = ControloDeErros.Input.getOpcaoValida("Opção: ", 0, 3);
            switch (opc)
            {
                case 1: RegistoAtividadesAmigo (email);
                        break;
                case 2: estatisticaAmigo (email);
                        break;
                case 3: recordesAmigo (email);
                        break;
                case 0: break;
                default: System.out.println ("Erro");
            }
        } while (opc!=0);
    }

    public static void RegistoAtividadesAmigo (String email)
    {
        int opc, actividade;
        Utilizador amigo = utilizadores.getUtilizador(email);
        do{
            Separador.Barra();
            System.out.println ("                       Últimas dez actividades\n");
            System.out.println (amigo.mostraUltimasAtividades(10));
            Separador.Barra();
            System.out.println ("1 - Ver informação sobre uma dada actividade");
            System.out.println ("0 - Sair");
            opc = ControloDeErros.Input.getOpcaoValida("Opção: ", 0, 1);
            Separador.Barra();
            switch (opc)
            {
                case 1: actividade = ControloDeErros.Input.getOpcaoValida("Quer ver informação sobre que actividade?",1, amigo.getSizeAtividadesRealizadas());
                        Separador.Barra();
                        System.out.println (amigo.mostraAtividade (actividade));
                        Separador.Barra();
                case 0: break;
                default : System.out.println ("Erro!");
            }
        } while (opc!=0);
    }
    public static void estatisticaAmigo (String email)
    {
        int opc,mes,ano;
        Utilizador amigo = utilizadores.getUtilizador(email);
        GregorianCalendar hoje = new GregorianCalendar();
        do
        {
            Separador.Barras();
            System.out.println ("                          Estatística\n");
            System.out.println ("1 - Estatística Mensal");
            System.out.println ("2 - Estatística Anual");
            System.out.println ("0 - Sair");
            System.out.print ("Opção:");
            opc = ControloDeErros.Input.getOpcaoValida("Opção: ", 0, 2);
            Separador.Barra();
            switch (opc)
            {
                case 1: mes = ControloDeErros.Input.getOpcaoValida("Qual o mês? ", 1 , 12) - 1 ;
                        ano = ControloDeErros.Input.getOpcaoValida("Qual o ano?", 1900, hoje.get(GregorianCalendar.YEAR)) ;
                        Separador.Barra();
                        System.out.println ("                   Estatística Mensal ("+(mes+1)+"/"+ano+")\n");
                        System.out.println (amigo.estatisticaMensal (mes,ano));
                        break;
                case 2: ano = ControloDeErros.Input.getOpcaoValida("Qual o ano?", 1900, hoje.get(GregorianCalendar.YEAR)) ;
                        Separador.Barra();
                        System.out.println ("                   Estatística Anual ("+ano+")\n");
                        System.out.println (amigo.estatisticaAnual (ano));
                        break;
                case 0: break;
                default: System.out.println ("Erro!");
            }
        } while (opc!=0);
    }
    public static void recordesAmigo (String email)
    {
        String opc;
        Utilizador amigo = utilizadores.getUtilizador(email);
        do
        {
            Separador.Barras();
            System.out.println ("                          Recordes\n");
            System.out.println (amigo.listaAtividadesRecordes());
            System.out.println ("\n Digite \"sair\" para sair");
            Separador.Barra();
            System.out.print ("Quer ver os recordes de que actividade?:");
            opc = Input.lerString();
            Separador.Barra();
            switch (opc)
            {
                case "Futebol":   Separador.Barra();
                                  System.out.println ("                          Recordes de Futebol\n");
                                  System.out.println (amigo.recordeFutebol());
                                  break;
                case "Caminhada": Separador.Barra();
                                  System.out.println ("                          Recordes de Caminhada\n");
                                  System.out.println (amigo.recordeCaminhada());
                                  break;
                case "Ginasio":   Separador.Barra();
                                  System.out.println ("                          Recordes de Ginásio\n");
                                  System.out.println (amigo.recordeGinasio());
                                  break;
                case "Corrida":   Separador.Barra();
                                  System.out.println ("                          Recordes de Corrida\n");
                                  System.out.println (amigo.recordeCorrida());
                                  break;
                case "Ciclismo":  Separador.Barra();
                                  System.out.println ("                          Recordes de Ciclismo\n");
                                  System.out.println (amigo.recordeCiclismo());
                                  break;
                case "sair": break;
                default: System.out.println ("Erro!");
            }
        } while (!opc.equals("sair"));
    }
    public static void admin () throws UtilizadorJaExiste
    {
        do 
        {
            Separador.Barras();
            System.out.println ("                     Painel de administrador\n");
            menuGestorEventos.executa();
            switch (menuGestorEventos.getOpcao())
            {
                case 1: inserirEvento();
                        break;
                case 2: mostrarEventos();
                        break;
                case 3: removerEventos();
                        break;
                case 0: break;
                default: System.out.println ("Erro!");
                         break;
            }
        } while (menuGestorEventos.getOpcao()!=0);
    }
    public static void inserirEvento ()
    {
        String nome, dataString, datalimiteString, descricao;
        double requisito;
        GregorianCalendar data, datalimite;
        int max;
        
        Separador.Barras();
        System.out.println ("                     Inserir eventos\n");
        menuEventos.executa();
        System.out.print ("Nome: ");
        nome = Input.lerString();

        data = ControloDeErros.Input.getDataValidaFutura("Quando será realizado o evento (DD MM AAAA)? ");
        datalimite = ControloDeErros.Input.getDataValidaFutura("Até quando é possível realizar inscrição (DD MM AAAA)?");
        while ((datalimite.compareTo(data))>0)
        {
            System.out.println ("A data limite de inscrição deve ser mais recente que a realização do evento!");
            datalimite = ControloDeErros.Input.getDataValidaFutura("Até quando é possível realizar inscrição (DD MM AAAA)?");
        }
        BaseDeDadosUtilizadores inscritos = new BaseDeDadosUtilizadores();
        System.out.print ("");
        max = ControloDeErros.Input.getOpcaoValida("Número máximo de inscrições (mínimo 10): ", 10, 999999999);
        System.out.print ("Descrição: ");
        descricao = Input.lerString();
        switch (menuEventos.getOpcao())
        {
              case 1: System.out.print ("Requisito (número mínimo de Km já realizados): ");
                        requisito = Input.lerDouble();
                        baseDeDados.insereEvento (new ProvaCiclismo (nome,data,inscritos,max,datalimite,descricao,requisito)); 
                        System.out.println ("              Actividade inserida com sucesso!");
                        break;
                case 2: baseDeDados.insereEvento (new MeiaMaratona (nome,data,inscritos,max,datalimite,descricao));
                        System.out.println ("              Actividade inserida com sucesso!");                
                        break;
                case 3: System.out.print ("Requisito (número mínimo de Km já realizados): ");
                        requisito = Input.lerDouble();
                        baseDeDados.insereEvento (new CaminhadaConjunta (nome,data,inscritos,max,datalimite,descricao,requisito));
                        System.out.println ("              Actividade inserida com sucesso!");                        
                        break;
                case 4: baseDeDados.insereEvento (new Maratona (nome,data,inscritos,max,datalimite,descricao));
                        System.out.println ("              Actividade inserida com sucesso!");                
                        break;
                case 5: System.out.print ("Requisito (número mínimo de Km já realizados): ");
                        requisito = Input.lerDouble();
                        baseDeDados.insereEvento (new BTT(nome,data,inscritos,max,datalimite,descricao,requisito));
                        System.out.println ("              Actividade inserida com sucesso!");                        
                case 0: break;
                default: System.out.println ("Erro!");
                        break;
        }
    }
    public static void removerEventos ()
    {
        int opc, indice;
        Separador.Barra();
        System.out.println ("                       Eventos disponíveis\n");
        System.out.println (eventos.mostraEventos());
        Separador.Barra();
        System.out.println ("1 - Remover eventos");
        System.out.println ("0 - Sair");
        opc = ControloDeErros.Input.getOpcaoValida("Opção: ", 0 ,1);
        switch (opc)
        {
            case 1: System.out.println ("Qual o evento que quer remover?");
                    indice = Input.lerInt();
                    Evento evento = eventos.getEventoIndex (indice-1);
                    eventos.removeEvento (evento);
                    System.out.println ("            Actividade removida com sucesso!");
                    break;
            case 0: break;
            default: System.out.println ("Erro!");
        }
        
    }

   
    
    
        
        
    public static void CarregarMenusEntrada()
    {
        String[] Entrada = 
        {
            "1 - Registar",
            "2 - Efectuar Login"
        };
       
        String[] perfil=
        {
            "1 - Alterar e-mail",
            "2 - Alterar password",
            "3 - Alterar nome",
            "4 - Alterar género",
            "5 - Alterar data de nascimento",
            "6 - Alterar altura",
            "7 - Alterar peso",
            "8 - Alterar desporto favorito"
        };
        
        String[] util = 
        {
            "1 - Gestão de Actividades",
            "2 - Gestão de Amigos",
            "3 - Perfil do Utilizador"
        };
        String [] admin = 
        {
                    
            "1 - Gestão de Actividades",
            "2 - Gestão de Amigos",
            "3 - Perfil do Utilizador",
            "4 - Painel de Administrador"
        };
        
        
        String[] gestAt =
        {
            "1 - Inserir uma actividade",
            "2 - Consultar as dez últimas actividades",
            "3 - Consultar e remover uma actividade",
            "4 - Consultar a estatística das actividades",
            "5 - Recordes",
            "6 - Eventos"
        };
        
        String [] actividades = 
        {
            "1 - Futebol",
            "2 - Ginasio",
            "3 - Corrida",
            "4 - Caminhada",
            "5 - Ciclismo"
        };
        
        String [] amigos =
        {
            "1 - Ver lista de amigos",
            "2 - Adicionar amigo",
            "3 - Ver os pedidos de amizade",
            "4 - Remover amigos",
            "5 - Ver o perfil de amigos"
        };
        
        String [] perfAmigos =
        {
            "1 - Gestão de actividades",
            "2 - Ver lista de amigos",
            "3 - Ver o perfil"
        };
        
        String [] eventos = 
        {
            "1 - Ciclismo",
            "2 - Meia-maratona",
            "3 - Caminhada conjunta",
            "4 - BTT"
        };
        
        String [] gestEventos =
        {
            "1 - Inserir evento",
            "2 - Mostrar eventos disponíveis",
            "3 - Remover evento"
        };
        
        String [] UtilizadorEventos = 
        {
            "1 - Consultar e inscrever em eventos",
            "2 - Remover inscrição",
            "3 - Ver eventos nos quais está inscrito"   
        };
        
        menuUtilizadorEventos = new Menu (UtilizadorEventos);
        menuGestorEventos = new Menu (gestEventos);
        menuEventos = new Menu (eventos);
        menuAdmin = new Menu (admin);
        menuAmigo = new Menu (perfAmigos);
        menuGestaoAmigos = new Menu (amigos);
        menuAtividades = new Menu (actividades);  
        menuPerfil = new Menu (perfil);
        menuUtilizador = new Menu (util);
        menuEntrada = new Menu (Entrada);
        menuGestaoAtividades = new Menu (gestAt);
        
    }
    public static void CarregarDados()
    {
        try 
        {  
            FileInputStream tis = new FileInputStream ("users.db");  
            ObjectInputStream ois = new ObjectInputStream (tis);
            baseDeDados = (BaseDeDados) ois.readObject();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            baseDeDados = new BaseDeDados();
        } 
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            baseDeDados = new BaseDeDados();
        }
        catch (ClassCastException e) {
            System.out.println(e.getMessage());
            baseDeDados = new BaseDeDados();
        }
    }
    
    public static GregorianCalendar StringToData (String s)
    {
        int ano,dia,mes;
        GregorianCalendar data;
        String[] split = s.split ("/");
        dia = Integer.parseInt(split[0]);
        mes = Integer.parseInt(split[1]);
        mes--;
        ano = Integer.parseInt(split[2]);
        data = new GregorianCalendar (ano,mes,dia);
        return data;
    }
    public static void boasVindas()
    {
        Separador.Barra();
        System.out.println ("---------------------Fitness UM-------------------------");
        System.out.println ("                      Bem-vindo!                  ");
        System.out.println ("\n");
        Separador.Barras();
    }
    
        
        
    public static void removeEventosDesatualizados(){
        GregorianCalendar hoje = new GregorianCalendar();
        Iterator<Evento> it = eventos.getEventos().iterator();
        while(it.hasNext()){
            Evento temp = it.next();
            if (temp.getData().compareTo(hoje) < 0) {
                eventos.removeEvento(temp);
            }
        }
    }        
        
}
        
        

                              
                            
  
                        
