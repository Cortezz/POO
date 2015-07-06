package Recordes;

import java.util.concurrent.TimeUnit;
public class math
{
    //Arredonda um valor para um determinado numero de casas decimas
    public static double round(double valueToRound, int numberOfDecimalPlaces)
    {
        double multipicationFactor = Math.pow(10, numberOfDecimalPlaces);
        double interestedInZeroDPs = valueToRound * multipicationFactor;
        return Math.round(interestedInZeroDPs) / multipicationFactor;
    }
    //Aplica a regra de tres simples 
    public static int regraTresSimples (int tempo, double distancia, double valor)
    {
        int resultado;
        resultado = (int)(((double)tempo*valor)/(distancia));
        return resultado;
    }
    //Aplica a regra de tres simples mas devolve um double
    public static double regraTresSimplesD (int tempo, double distancia, int valor)
    {
        double resultado;
        resultado = ((double)valor*distancia/(double) tempo);
        return resultado;
    }
    //Transforma um inteiro (minutos) para uma string com o formato de horas (hh:mm)
    public static String IntToFormatedHour (int tempo)
    {
        long hours = TimeUnit.MINUTES.toHours(tempo);
        long remainMinute = tempo - TimeUnit.HOURS.toMinutes(hours);
        String result = String.format("%02d", hours) + ":" + String.format("%02d", remainMinute);
        return result;
    }
}
