package Actividades;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Calendar;
public class ComparatorAtividade implements Comparator<AtividadeDesportiva>
{
    public int compare (AtividadeDesportiva a1, AtividadeDesportiva a2)
    {
        GregorianCalendar c1 = a1.getData();
        GregorianCalendar c2 = a2.getData();
        if (c2.equals(c1)) return 1;
        return c2.compareTo(c1);
    }
}
