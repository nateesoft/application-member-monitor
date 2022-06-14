package database.connect;

import file.config.ConfigProps;
import file.config.FileConfigValue;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author nateesun
 */
public class MySQLConnect {

    private static final Logger LOGGER = Logger.getLogger(MySQLConnect.class);
    private Connection connection = null;
    private final ConfigProps config = FileConfigValue.loadConfig();

    public Connection open(String type) {
        if (connection != null) {
            return connection;
        }
        LOGGER.debug("MySQLConnect: open type=" + type);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String urlConnect = "";

            switch (type) {
                case "pos":
                    LOGGER.debug(urlConnect);
                    urlConnect = "jdbc:mysql://" + config.getPosHost() + ":" + config.getPosPort() + "/" + config.getPosDb() + "?charset=tis-620";
                    connection = DriverManager.getConnection(urlConnect, config.getPosUser(), config.getPosPassword());
                    break;
                case "member":
                    LOGGER.debug(urlConnect);
                    urlConnect = "jdbc:mysql://" + config.getPosHost() + ":" + config.getPosPort() + "/" + config.getPosMemberDb() + "?charset=tis-620";
                    connection = DriverManager.getConnection(urlConnect, config.getPosUser(), config.getPosPassword());
                    break;
                case "":
                    LOGGER.debug(urlConnect);
                    urlConnect = "jdbc:mysql://" + config.getPosHost() + ":" + config.getPosPort();
                    connection = DriverManager.getConnection(urlConnect, config.getPosUser(), config.getPosPassword());
                    break;
            }

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
            System.exit(0);
            connection = null;
        }

        return connection;
    }

    public void dump() {

    }

    public void restore(String database, String fileName) {
        String[] restoreCmd = new String[]{"/bin/sh", "docker exec f0fb1593900c /usr/bin/mysqldump -u root --password=mysql5password " + database + " " + fileName};
        try {
            Process runtimeProcess = Runtime.getRuntime().exec(restoreCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Done");
            } else {
                System.out.println("Failed");
            }
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void close() {
        LOGGER.debug("MySQLConnect: close");
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        MySQLConnect mysql = new MySQLConnect();
        Connection conn = mysql.open("");
        conn.createStatement().execute("DROP DATABASE temp_webdaily_001");
        conn.createStatement().execute("CREATE DATABASE temp_webdaily_001");
        mysql.restore("temp_webdaily_001", "webdaily_001.sql");
    }
}
