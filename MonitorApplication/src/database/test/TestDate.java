package database.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author nateesun
 */
public class TestDate {
    public static String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final SimpleDateFormat isoFormatter = new SimpleDateFormat(ISO_FORMAT);
    private static final TimeZone utc = TimeZone.getTimeZone("UTC");
    
    static {
        isoFormatter.setTimeZone(utc);
    }
    
    public static void main(String[] args) {
        String time = "1986-09-17T17:00:00.000Z";
        try {
            Date date = isoFormatter.parse(time);
            System.out.println(date);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
    }
}
