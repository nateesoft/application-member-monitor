package utils;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author nateesun
 */
public class ThaiUtilTest {
    
    @Test
    @DisplayName("testEncodeThaiAscii")
    public void testEncodeThaiAscii() {
        assertTrue(ThaiUtil.encodeThaiAscii("ทดสอบภาษาไทย").equals("·´ÊÍºÀÒÉÒä·Â"));
        assertTrue(ThaiUtil.encodeThaiAscii(null).equals(""));
        assertTrue(ThaiUtil.encodeThaiAscii("").equals(""));
        assertTrue(ThaiUtil.encodeThaiAscii("null").equals("null"));
        assertTrue(ThaiUtil.encodeThaiAscii(" ").equals(" "));
        assertTrue(ThaiUtil.encodeThaiAscii("English").equals("English"));
    }
    
    @Test
    @DisplayName("testReadThaiAscii")
    public void testReadThaiAscii() {
        assertTrue(ThaiUtil.readThaiAscii("·´ÊÍºÀÒÉÒä·Â").equals("ทดสอบภาษาไทย"));
        assertTrue(ThaiUtil.readThaiAscii(null).equals(""));
        assertTrue(ThaiUtil.readThaiAscii("ภาษาไทย").equals("ภาษาไทย"));
    }
}
