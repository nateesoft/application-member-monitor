package utils;

import database.DbConfig;
import org.apache.log4j.Logger;

public class ThaiUtil {

    private static final DbConfig config;
    private static final Logger LOGGER = Logger.getLogger(ThaiUtil.class);

    static {
        config = DbConfig.loadConfig();
    }

    public static void main(String[] args) {
        String text = "ทดสอบ ภาษาไทย";
        String result = ThaiUtil.Unicode2ASCII(text);
        System.out.println(result);
        System.out.println(ThaiUtil.ASCII2Unicode(result));
    }

    public static String Unicode2ASCII(String str) {
        LOGGER.debug("Unicode2ASCII <= " + str);
        if (str == null) {
            return "";
        }
        if (!config.isThaiUtf()) {
            return str;
        }
        
        StringBuilder convert = new StringBuilder(str);
        int code;
        for (int i = 0; i < str.length(); i++) {
            code = (int) str.charAt(i);
            if ((0xE01 <= code) && (code <= 0xE5B)) {
                convert.setCharAt(i, (char) (code - 0xD60));
            } else {
                convert.setCharAt(i, (char) code);
            }
        }
        LOGGER.debug("Unicode2ASCII => " + convert.toString());
        return convert.toString();
    }

    public static String ASCII2Unicode(String ascii) {
        LOGGER.debug("ASCII2Unicode <= " + ascii);
        if (ascii == null) {
            return "";
        }
        if (!config.isThaiUtf()) {
            return ascii;
        }
        StringBuilder convert = new StringBuilder(ascii);
        int code;
        for (int i = 0; i < ascii.length(); i++) {
            code = (int) ascii.charAt(i);
            if ((0xA1 <= code) && (code <= 0xFB)) {
                convert.setCharAt(i, (char) (code + 0xD60));
            } else {
                convert.setCharAt(i, (char) code);
            }
        }
        LOGGER.debug("ASCII2Unicode => " + convert.toString());
        return convert.toString();
    }
}
