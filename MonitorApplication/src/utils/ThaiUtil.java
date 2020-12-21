package utils;

public class ThaiUtil {

    public static void main(String[] args) {
        String text = "ทดสอบ ภาษาไทย";
        String result = ThaiUtil.Unicode2ASCII(text);
        System.out.println(result);
        System.out.println(ThaiUtil.ASCII2Unicode(result));
    }

    public static String Unicode2ASCII(String unicode) {
        if (unicode == null) {
            return "";
        }
        StringBuilder ascii = new StringBuilder(unicode);
        String saveAscii = "";
        int code;
        for (int i = 0; i < unicode.length(); i++) {
            code = (int) unicode.charAt(i);
            if ((0xE01 <= code) && (code <= 0xE5B)) {
                ascii.setCharAt(i, (char) (code - 0xD60));
                saveAscii += (char) (code - 0xD60);
            } else {
                saveAscii += (char) code;
            }
        }
        return saveAscii;
    }

    public static String ASCII2Unicode(String ascii) {
        if (ascii == null) {
            return "";
        }
        StringBuilder unicode = new StringBuilder(ascii);
        int code;
        for (int i = 0; i < ascii.length(); i++) {
            code = (int) ascii.charAt(i);
            if ((0xA1 <= code) && (code <= 0xFB)) {
                unicode.setCharAt(i, (char) (code + 0xD60));
            } else {
                unicode.setCharAt(i, (char) code);
            }
        }
        return unicode.toString();
    }

    public static String Unicode2ASCII_js(String text) {
        String ascii = "";
        int code;
        for (int i = 0; i < text.length(); i++) {
            code = (int) text.charAt(i);
            if ((0xE01 <= code) && (code <= 0xE5B)) {
                ascii += (char) (code - 0xD60);
            }
        }
        return ascii;
    }
}
