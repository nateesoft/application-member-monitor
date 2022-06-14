package utils;

public class ThaiUtil {

    public static void main(String[] args) {
        System.out.println(encodeThaiAscii("ทดสอบภาษาไทย"));
    }

    public static String encodeThaiAscii(String str) {
        if (str == null) {
            return "";
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
        return convert.toString();
    }

    public static String readThaiAscii(String ascii) {
        if (ascii == null) {
            return "";
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
        
        return convert.toString();
    }
}
