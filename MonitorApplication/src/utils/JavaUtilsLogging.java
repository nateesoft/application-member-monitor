package utils;

/**
 *
 * @author nathee
 */
import java.util.logging.Logger;

public class JavaUtilsLogging {

    private static final Logger LOGGER = Logger.getLogger(JavaUtilsLogging.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Test Info message");
        LOGGER.warning("Test warning message");
    }
}
