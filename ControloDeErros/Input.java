package ControloDeErros;


/**
 * Write a description of class ControloDeErrosUtilizador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Input
{
    /**
     * Verifica se uma String ?? composta apenas por Ints.
     */
    public static boolean stringIsNum(String x)
    {
        if (x.matches("[0-9]+"))
        {
            return true;
        }
        return false;
    }
    
    public static boolean stringIsDouble(String x)
    {
        if (x.matches("[0-9]+.[0-9]+") || x.matches("[0-9]+"))
        {
            return true;
        }
        return false;
    }
    
    /**
     * Verifica se um Email tem o formato correto
     */
    public static boolean opcaoIsEmail(String email)
    {
  
        if(email.contains("@") && email.contains(".") &&
           email.lastIndexOf(".") > email.indexOf("@") &&
           email.lastIndexOf("@") == email.indexOf("@") &&
           email.lastIndexOf(".")+1 < email.length() )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Verifica se um n??mero x est?? no intervalo [limiteInferior, limiteSuperior]
     */
    public static boolean estaNoIntervalo(double limiteInferior, double limiteSuperior, double x)
    {
        if (x >= limiteInferior && x <= limiteSuperior)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Verifica se duas strings s??o iguais
     */
    public static boolean ambasPasswordsIguais(String passUm, String passDois)
    {
        if(passUm.equals(passDois))
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    /**
     * Pede ao utilizador para introduzir uma opcao e verifica se est?? no intervalo [limiteInferiorm limiteSuperior]
     */
    public static int getOpcaoValida(String pergunta, int limiteInferior, int limiteSuperior)
    {
        Scanner input = new Scanner(System.in);
        String buff = new String();
        boolean opcaoInvalida = true;
        int opcao = 0;
        
        while(opcaoInvalida)
        {
               System.out.printf(pergunta);
               buff = input.nextLine();
               
               if(!stringIsNum(buff))
               {
                   System.out.println(mensagemDeErro(1));
               }
               else
               {
                   opcao = Integer.parseInt(buff);
                   
                   if(estaNoIntervalo(limiteInferior, limiteSuperior, opcao))
                   {
                       opcaoInvalida = false;
                   }
                   else
                   {
                    System.out.println(mensagemDeErro(2));
                   }      
               }
         }
         return opcao;
    }
    
    /**
     * Pede ao utilizador para inserir um email
     * Verifica se tem o formato correto
     * 
     */
    public static String getEmailValido(String pergunta)
    {
        Scanner input = new Scanner (System.in);
        boolean opcaoInvalida = true;
        String email = new String();
        
        while(opcaoInvalida)
        {
            System.out.printf(pergunta);
            email = input.next();
            input.nextLine();
            
            if(!opcaoIsEmail(email))
            {
                System.out.println(mensagemDeErro(3));
            }
            else
            {
                opcaoInvalida = false;

            }
        }
        return email;
    }
    
    /**
     * Pede ao utilizador para inserir a password desejada duas vezes
     * Se forem iguais retorna a password
     * Se forem diferentes torna a perguntar por passwords
     */
    public static String getPasswordsValidas()
    {
        Scanner input = new Scanner (System.in);
        boolean opcaoInvalida = true;
        String password = new String();
        
        while(opcaoInvalida)
        {
            System.out.printf("Insira a sua password: ");
            password = input.nextLine();
            
            System.out.printf("Insira de novo a password: ");
            String passwordDois = input.nextLine();
            
            if(!ambasPasswordsIguais(password, passwordDois))
            {
                System.out.println(mensagemDeErro(5));
            }
            else
            {
                opcaoInvalida = false;
            }
        }
        
        return password;
    }
    
    /**
     * Pede ao utilizador o seu nome
     * Devolve o nome
     */
    public static String getNomeValido()
    {
        Scanner input = new Scanner (System.in);
        boolean opcaoInvalida = true;
        String nome = new String();
        
        while (opcaoInvalida)
        {
            System.out.printf("Insira o seu nome: ");
            nome = input.nextLine();
            opcaoInvalida = false;
            /*if()
            {
            
            }
            else
            {
            
            }*/
        }
        return nome;
    }
    
    public static String getEscolhaBinaria(String escolhaUm, String escolhaDois)
    {
        Scanner input = new Scanner (System.in);
        boolean opcaoInvalida = true;
        String escolha = new String();
        
        while (opcaoInvalida)
        {
            escolha = input.next();
            input.nextLine();
            if(escolha.equals(escolhaUm) || escolha.equals(escolhaDois) 
            )
            {
                opcaoInvalida = false;
            }
            else
            {
                System.out.println(mensagemDeErro(1));
            }
        }
        return escolha;
    }
    
    public static String getClimaValido(){
        Scanner input = new Scanner (System.in);
        boolean opcaoInvalida = true;
        String escolha = new String();
        
        while (opcaoInvalida)
        {
            escolha = input.next();
            input.nextLine();
            if(!escolha.equals("Nevar") || !escolha.equals("Chuva") || !escolha.equals("Nublado") || !escolha.equals("Sol")){
                opcaoInvalida = false;
            }
            else
            {
                System.out.println(mensagemDeErro(12));
            }
        }
        return escolha;
    }
    
    /**
     * Pergunta ao utilizador por um dia mes e ano e verifica se ?? uma data poss??vel
     * Caso for, retorna essa data em tipo GregorianCalendar
     * Caso n??o for, torna a perguntar por uma data
     */
    public static GregorianCalendar getDataValida(String pergunta)
    {
        String stringAno = new String();
        int ano = 0;
        String stringMes = new String();
        int mes = 0;
        String stringDia = new String();
        int dia = 0;
        GregorianCalendar hoje = new GregorianCalendar();
        
        Scanner input = new Scanner (System.in);
        boolean opcaoInvalida = true;
        GregorianCalendar data;
        
        while(opcaoInvalida)
        {
            System.out.printf(pergunta);
            stringDia = input.next();
            stringMes = input.next();
            stringAno = input.next();
            input.nextLine();
            
            if(!stringIsNum(stringDia+stringMes+stringAno))
            {
                System.out.println(mensagemDeErro(6));
            }
            else
            {
                    dia = Integer.parseInt(stringDia);
                    mes = Integer.parseInt(stringMes); 
                    ano = Integer.parseInt(stringAno);
                    
                    if(!estaNoIntervalo(1900, hoje.get(GregorianCalendar.YEAR), ano))
                    {
                        System.out.println(mensagemDeErro(7));
                    } else
                    {
                        if( ano == hoje.get(GregorianCalendar.YEAR) &&
                            !estaNoIntervalo(1, hoje.get(GregorianCalendar.MONTH) + 1, mes))
                            {
                                System.out.println(mensagemDeErro(7));
                            } else
                            {
                                if( ano == hoje.get(GregorianCalendar.YEAR) &&
                                    mes == hoje.get(GregorianCalendar.MONTH) + 1 &&
                                    !estaNoIntervalo(1, hoje.get(GregorianCalendar.DAY_OF_MONTH), dia))
                                {
                                    System.out.println(mensagemDeErro(7));
                                }
                                else 
                                {
                                    GregorianCalendar testeBissexto = new GregorianCalendar(ano, 1, 1);
                                    switch(mes)
                                    {
                                    case 1 : 
                                    case 3 : 
                                    case 5 : 
                                    case 7 :
                                    case 8 :
                                    case 10:
                                    case 12: 
                                        if (!estaNoIntervalo(1, 31, dia))
                                        {
                                            System.out.println(mensagemDeErro(7));
                                        } 
                                        opcaoInvalida = false;
                                        break;
                                    case 2: 
                                        if(testeBissexto.isLeapYear(ano) && !estaNoIntervalo(1, 29, dia))
                                        {
                                            System.out.println(mensagemDeErro(7));
                                        } 
                                        else
                                        {
                                            if(!testeBissexto.isLeapYear(ano) && !estaNoIntervalo(1, 28, dia))
                                            {
                                                System.out.println(mensagemDeErro(7));
                                            }
                                            else
                                            {
                                                opcaoInvalida = false;
                                            }
                                        }
                                        break;
                                    case 4: 
                                    case 6:
                                    case 9:
                                    case 11:
                                        if(!estaNoIntervalo(1,30, dia))
                                        {
                                            System.out.println(mensagemDeErro(7));
                                        }
                                        else
                                        {
                                            opcaoInvalida = false;
                                        }
                                        break;
                                    }
                    }}}
            }
        
        }
        data = new GregorianCalendar(ano, (mes-1), dia);
        return data;
    }

   
   public static GregorianCalendar getDataValidaFutura(String pergunta)
    {
        String stringAno = new String();
        int ano = 0;
        String stringMes = new String();
        int mes = 0;
        String stringDia = new String();
        int dia = 0;
        GregorianCalendar hoje = new GregorianCalendar();
        
        Scanner input = new Scanner (System.in);
        boolean opcaoInvalida = true;
        GregorianCalendar data;
        
        while(opcaoInvalida)
        {
            System.out.printf(pergunta);
            stringDia = input.next();
            stringMes = input.next();
            stringAno = input.next();
            input.nextLine();
            
            if(!stringIsNum(stringDia+stringMes+stringAno))
            {
                System.out.println(mensagemDeErro(6));
            }
            else
            {
                    dia = Integer.parseInt(stringDia);
                    mes = Integer.parseInt(stringMes); 
                    ano = Integer.parseInt(stringAno);
                    
                    if(!estaNoIntervalo(hoje.get(GregorianCalendar.YEAR), 3000, ano))
                    {
                        System.out.println(mensagemDeErro(7));
                    } else
                    {
                        if( ano == hoje.get(GregorianCalendar.YEAR) &&
                            !estaNoIntervalo(hoje.get(GregorianCalendar.MONTH) + 1, 12,  mes))
                            {
                                System.out.println(mensagemDeErro(7));
                            } else
                            {
                                if( ano == hoje.get(GregorianCalendar.YEAR) &&
                                    mes == hoje.get(GregorianCalendar.MONTH) + 1 &&
                                    !estaNoIntervalo(hoje.get(GregorianCalendar.DAY_OF_MONTH), 31, dia))
                                {
                                    System.out.println(mensagemDeErro(7));
                                }
                                else 
                                {
                                    GregorianCalendar testeBissexto = new GregorianCalendar(ano, 1, 1);
                                    switch(mes)
                                    {
                                    case 1 : 
                                    case 3 : 
                                    case 5 : 
                                    case 7 :
                                    case 8 :
                                    case 10:
                                    case 12: 
                                        if (!estaNoIntervalo(1, 31, dia))
                                        {
                                            System.out.println(mensagemDeErro(7));
                                        } 
                                        opcaoInvalida = false;
                                        break;
                                    case 2: 
                                        if(testeBissexto.isLeapYear(ano) && !estaNoIntervalo(1, 29, dia))
                                        {
                                            System.out.println(mensagemDeErro(7));
                                        } 
                                        else
                                        {
                                            if(!testeBissexto.isLeapYear(ano) && !estaNoIntervalo(1, 28, dia))
                                            {
                                                System.out.println(mensagemDeErro(7));
                                            }
                                            else
                                            {
                                                opcaoInvalida = false;
                                            }
                                        }
                                        break;
                                    case 4: 
                                    case 6:
                                    case 9:
                                    case 11:
                                        if(!estaNoIntervalo(1,30, dia))
                                        {
                                            System.out.println(mensagemDeErro(7));
                                        }
                                        else
                                        {
                                            opcaoInvalida = false;
                                        }
                                        break;
                                    }
                    }}}
            }
        
        }
        data = new GregorianCalendar(ano, (mes-1), dia);
        return data;
    }
    
    /**
     * Pede ao utilizador uma altura
     * Devolve altura se for v??lida
     */
    public static int getAlturaValida()
    {
        Scanner input = new Scanner(System.in);
        boolean opcaoInvalida = true;
        String buff = new String();
        int altura = 0;
        
        while(opcaoInvalida)
        {
            System.out.printf("Insira a sua altura em centímetros (Ex.185): ");
            buff = input.nextLine();
            
            if(!stringIsNum(buff))
               {
                   System.out.println(mensagemDeErro(1));
               }
               else
               {
                   altura = Integer.parseInt(buff);
                   
                   if(estaNoIntervalo(60, 275, altura))
                   {
                       opcaoInvalida = false;
                   }
                   else
                   {
                    System.out.println(mensagemDeErro(9));
                   }      
               }   
        }
        return altura;
    }
    
    /**
     * Pede ao utilizador um peso
     * Devolve peso se for v??lido
     */
    public static double getPesoValido()
    {
        Scanner input = new Scanner(System.in);
        boolean opcaoInvalida = true;
        String buff = new String();
        double peso = 0;
        
        while(opcaoInvalida)
        {
            System.out.printf("Insira o seu peso em kilogramas (Ex 67.5): ");
            buff = input.nextLine();
            
            if(!stringIsNum(buff))
               {
                   System.out.println(mensagemDeErro(1));
               }
               else
               {
                   peso = Integer.parseInt(buff);
                   
                   if(estaNoIntervalo(30, 650, peso))
                   {
                       opcaoInvalida = false;
                   }
                   else
                   {
                    System.out.println(mensagemDeErro(8));
                   }      
               }   
        }
        return peso;
    }
    
    /**
     * Pede ao utilizador o seu desporto favorito
     * Retorna o desporto favorito se for v??lido
     */
    public static String getDesportoFavoritoValido()
    {
        Scanner input = new Scanner (System.in);
        boolean opcaoInvalida = true;
        String desportoFavorito = new String();
        
        while (opcaoInvalida)
        {
            System.out.printf("Insira o seu desporto favorito: ");
            desportoFavorito = input.nextLine();
            opcaoInvalida = false;
            /*if()
            {
            
            }
            else
            {
            
            }*/
        }
        return desportoFavorito;
    }
    
    public static double getTipoDeContaValido(double limiteInferior, double limiteSuperior)
    {
        Scanner input = new Scanner (System.in);
        boolean opcaoInvalida = true;
        String buff = new String();
        int tipo = 0;
        
        while(opcaoInvalida)
        {
            System.out.printf("Qual o tipo de conta que pretende criar? ");
               buff = input.nextLine();
               
               if(!stringIsNum(buff))
               {
                   System.out.println(mensagemDeErro(1));
               }
               else
               {
                   tipo = Integer.parseInt(buff);
                   
                   if(estaNoIntervalo(limiteInferior, limiteSuperior, tipo))
                   {
                       opcaoInvalida = false;
                   }
                   else
                   {
                    System.out.println(mensagemDeErro(2));
                   }      
               }
        }
        return tipo;
    }
    
    public static int getTempoValido()
    {
        Scanner input = new Scanner (System.in);
        boolean opcaoInvalida = true;
        String buff = new String();
        int tempo = 0;
        
        while(opcaoInvalida)
        {
            System.out.printf("Quanto tempo demorou a atividade (em minutos)? ");
               buff = input.nextLine();
               
               if(!stringIsNum(buff) || (tempo = Integer.parseInt(buff)) < 0)
               {
                   System.out.println(mensagemDeErro(10));
               }
               else
               {
                       opcaoInvalida = false;     
               }
        }
        return tempo;
    }
    
    /**
     * Verifica se ?? uma distancia ?? v??lida
     * Se sim devolve a distancia
     */
    public static double getDistanciaValida(String pergunta)
    {
        Scanner input = new Scanner(System.in);
        String buff = new String();
        boolean opcaoInvalida = true;
        double distancia = 0;
        
        while(opcaoInvalida)
        {
               System.out.printf(pergunta);
               buff = input.nextLine();
               
               if(!stringIsDouble(buff))
               {
                   System.out.println(mensagemDeErro(9));
               }
               else
               {
                   distancia = Double.parseDouble(buff);
                   
                   if(distancia > 0 )
                   {
                       opcaoInvalida = false;
                   }
                   else
                   {
                    System.out.println(mensagemDeErro(9));
                   }      
               }
         }
         return distancia;
        }
    
    public static int getClimaValido(int limiteInferior, int limiteSuperior)
    {
        Scanner input = new Scanner(System.in);
        String buff = new String();
        boolean opcaoInvalida = true;
        int clima = 0;
        
        while(opcaoInvalida)
        {
               System.out.printf("Como descreve o clima de 1 a 10? (1 - Péssimo, 10 - Excelente): ");
               buff = input.nextLine();
               
               if(!stringIsNum(buff))
               {
                   System.out.println(mensagemDeErro(12));
               }
               else
               {
                   clima = Integer.parseInt(buff);
                   
                   if(estaNoIntervalo(limiteInferior, limiteSuperior, clima))
                   {
                       opcaoInvalida = false;
                   }
                   else
                   {
                    System.out.println(mensagemDeErro(2));
                   }      
               }
         }
         return clima;
    }
    
    public static String mensagemDeErro(int x)
    {
        switch(x)
        {
            case 1: return "Opção não é válida. Tente de novo.";
            case 2: return "Opção não está no intervalo correto.";
            case 3: return "Email inserido inválido. Deverá estar no formato xxxxx@xxxxx.x"; 
            case 4: return "Email já existe na base de dados!"; 
            case 5: return "Passwords não coincidem!"; 
            case 6: return "A data deve ser composta por números!"; 
            case 7: return "A data não está num intervalo possível"; 
            case 8: return "Este peso não é válido."; 
            case 9: return "Esta medida não é válida."; 
            case 10: return ""; 
            case 11: return "Tempo não válido"; 
            case 12: return "Clima não válido"; 
            case 13: return "Email não válido (Repetido/Existente/O próprio)"; 
        }
        
        return new String("FALTA MENSAGEM DE ERRO");
    }
}
