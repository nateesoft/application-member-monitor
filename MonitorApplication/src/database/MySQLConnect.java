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
                    LOGGER.debug(urlConnect);
                    urlConnect = "jdbc:mysql://" + config.getHostPos() + ":" + config.getPortPos() + "/" + config.getDbNamePos() + "?charset=tis-620";
                    connection = DriverManager.getConnection(urlConnect, config.getUserPos(), config.getPasswordPos());
                    break;
                case "member":
                    LOGGER.debug(urlConnect);
                    urlConnect = "jdbc:mysql://" + config.getHostMember() + ":" + config.getPortMember() + "/" + config.getDbNameMember() + "?charset=tis-620";
                    connection = DriverManager.getConnection(urlConnect, config.getUserMember(), config.getPasswordMember());
                    break;
            }

        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error(e.getMessage());
            this.connection = null;
        }
        return this.connection;
    }
}
