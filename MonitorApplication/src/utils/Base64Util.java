package utils;

import java.util.Base64;

/**
 *
 * @author nateesun
 */
public class Base64Util {

    public static String decode(String textEncode) {
        if (textEncode == null) {
            return "";
        }
        return new String(Base64.getDecoder().decode(textEncode));
    }

    public static String encode(String text) {
        if (text == null) {
            return "";
        }
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
}
