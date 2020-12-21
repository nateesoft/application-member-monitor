package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nateesun
 */
public class MySQLConnect {

    private Connection connection;
    private DbConfig config;
    
    public Connection openConnection(String type) {
        try {
            config = DbConfig.loadConfig();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String urlConnect = "";
            switch (type) {
                case "pos":
                    urlConnect = "jdbc:mysql://"+config.getHostPos()+"/"+config.getDbNamePos()+"?"+ "user="+config.getUserPos()+"&password="+config.getPasswordPos();
                    break;
                case "member":
                    urlConnect = "jdbc:mysql://"+config.getHostMember()+"/"+config.getDbNameMember()+"?"+ "user="+config.getUserMember()+"&password="+config.getPasswordMember();
                    break;
            }
            connection = DriverManager.getConnection(urlConnect);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            this.connection = null;
        }
        return this.connection;
    }
}
