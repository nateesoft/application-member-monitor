package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 *
 * @author nateesun
 */
public class MySQLConnect {

    private static final Logger LOGGER = Logger.getLogger(MySQLConnect.class);
    private Connection connection;
    private DbConfig config;

    public Connection openConnection(String type) {
        LOGGER.debug("openConnection type=" + type);
        try {
            config = DbConfig.loadConfig();
            Class.forName("com.mysql.jdbc.Driver");
            String urlConnect = "";
            switch (type) {
                case "pos":
                    urlConnect = "jdbc:mysql://" + config.getHostPos() + ":"+config.getPortPos()+"/" + config.getDbNamePos() + "?user=" + config.getUserPos() + "&password=" + config.getPasswordPos();
                    break;
                case "member":
                    urlConnect = "jdbc:mysql://" + config.getHostMember() + ":"+config.getPortMember()+"/" + config.getDbNameMember() + "?user=" + config.getUserMember() + "&password=" + config.getPasswordMember();
                    break;
            }
            LOGGER.debug(urlConnect);
            connection = DriverManager.getConnection(urlConnect);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error(e.getMessage());
            this.connection = null;
        }
        return this.connection;
    }
}
