package utils;

/**
 *
 * @author nateesun
 */
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogUtil {
    public static Logger write = Logger.getLogger(LogUtil.class);

    static {
        PropertyConfigurator.configure("log4j.properties");
    }
}