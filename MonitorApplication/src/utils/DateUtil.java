package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author nateesun
 */
public class DateUtil {

    private static final SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final SimpleDateFormat simpOut = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final TimeZone utc = TimeZone.getTimeZone("UTC");

    static {
        simp.setTimeZone(utc);
        simp.setTimeZone(utc);
    }

    public static java.sql.Date getDate(String time) {
        try {
            java.sql.Date date = new java.sql.Date(simp.parse(time).getTime());
            return date;
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public static String getDateString(String time) {
        try {
            Date date = simp.parse(time);
            return simpOut.format(date);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public static String getDateString(java.sql.Date time) {
        Date date = new Date(time.getTime());
        return simpOut.format(date);
    }
}
