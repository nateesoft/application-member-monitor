package api;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nateesun
 */
public class RestApiConnectTest {
    public static void main(String[] args) {
        try {
            RestApiConnect.sendGET();
        } catch (IOException ex) {
            Logger.getLogger(RestApiConnectTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
