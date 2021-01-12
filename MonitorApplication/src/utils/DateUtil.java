package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author nateesun
 */
public class DateUtil {

    private static final SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
    private static final SimpleDateFormat simpOut = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    private static final TimeZone utc = TimeZone.getTimeZone("UTC");

    static {
        simp.setTimeZone(utc);
        simp.setTimeZone(utc);
    }

    public static java.sql.Date getDate(String time) {
        try {
            Date tDate = simp.parse(time);
            Date ouDate = simpOut.parse(simpOut.format(tDate));
            java.sql.Date date = new java.sql.Date(ouDate.getTime());
            return date;
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public static java.sql.Date getDateAdd(java.sql.Date plusTime) {
        Calendar c = Calendar.getInstance();
        c.setTime(plusTime);
        c.add(Calendar.MINUTE, 30);
        java.sql.Date date = new java.sql.Date(c.getTimeInMillis());
        return date;
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
        if(time==null){
            return null;
        }
        Date date = new Date(time.getTime());
        return simpOut.format(date);
    }
}
