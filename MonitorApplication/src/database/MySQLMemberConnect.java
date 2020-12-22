package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 *
 * @author nateesun
 */
public class MySQLMemberConnect {

    private static final Logger LOGGER = Logger.getLogger(MySQLMemberConnect.class);
    private Connection connection;

    public Connection openConnection() {
        LOGGER.debug("openConnection");
        try {
            MySQLConnect mysql = new MySQLConnect();
            this.connection = mysql.openConnection("member");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            this.connection = null;
        }
        return this.connection;
    }
}
