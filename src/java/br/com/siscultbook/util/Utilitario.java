package br.com.siscultbook.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Carlos
 */
public class Utilitario {

    public static GregorianCalendar verificaData(String texto) {

        DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        GregorianCalendar data = new GregorianCalendar();
        formatador.setLenient(false);
        try {
            data.setTime(formatador.parse(texto));
            return data;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String DataFormatada(GregorianCalendar data) {
        try {
            SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
            return sp.format(data.getTime());
        } catch (Exception e) {
            //e.printStackTrace();
            return "";
        }
    }

    public static String DataFormatadaComleta(GregorianCalendar data) {
        try {
            SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return sp.format(data.getTime());
        } catch (Exception e) {
            //e.printStackTrace();
            return "";
        }
    }

    public static Date getCalendarioParaData(GregorianCalendar dataFormat) {

        try {
            Date data = new Date(dataFormat.getTimeInMillis());
            return data;
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return null;
    }

    public static Timestamp getCalendarioData(GregorianCalendar dataFormat) {

        try {

            Timestamp now = (Timestamp) dataFormat.getTime();
            SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            return now;

        } catch (Exception e) {
            //e.printStackTrace();
        }

        return null;
    }

    public static GregorianCalendar getDataParaCalendario(Date dataFormat) {

        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(dataFormat);            
            return cal;

        } catch (Exception e) {
            //e.printStackTrace();
        }

        return null;
    }

    public static GregorianCalendar getTimestampParaCalendario(Timestamp dataFormat) {

        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(dataFormat);
            //System.out.println("resultado obtido - " + cal);
            return cal;

        } catch (Exception e) {
            //e.printStackTrace();
        }

        return null;
    }

    public static String formatarString(String texto, String mascara) throws ParseException {
        MaskFormatter mf = new MaskFormatter(mascara);
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(texto);
    }

    public static String dataHoraHoje() {

        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        //System.out.print(formatarDate.format(data));
        //"2011-03-27 00:00:00"
        return formatarDate.format(data);
    }

    public static String dataHoraHojeCompleta() {

        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.print(formatarDate.format(data));
        //"2011-03-27 00:00:00"
        return formatarDate.format(data);
    }

    public static String dataHoje() {

        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.print(formatarDate.format(data));
        return formatarDate.format(data);
    }

    public static String formatarParaMonetario(double d) {

        DecimalFormat df = new DecimalFormat("R$ ###,###,##0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("PT", "BR")));
        String s;
        s = df.format(d);
        return s;
        
    }

    public static String formatarParaMoeda(double d) {

        DecimalFormat df = new DecimalFormat("###,###,##0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("PT", "BR")));
        String s;
        s = df.format(d);
        return s;

    }

}
