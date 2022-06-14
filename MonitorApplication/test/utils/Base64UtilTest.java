package utils;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import static utils.Base64Util.decode;
import static utils.Base64Util.encode;

/**
 *
 * @author nateesun
 */
public class Base64UtilTest {
    private final String textEncode = "d2ViZGFpbHlfMDAx";
    private final String textResult = "webdaily_001";
    
    @Test
    @DisplayName("testDecode")
    public void testDecode() {
        assertTrue(decode(textEncode).equals(textResult));
        assertTrue(decode(null).equals(""));
    }

    @Test
    @DisplayName("testEncode")
    public void testEncode() {
        assertTrue(encode(textResult).equals(textEncode));
        assertTrue(encode(null).equals(""));
    }
}
