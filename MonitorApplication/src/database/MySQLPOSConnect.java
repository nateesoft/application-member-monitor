package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nateesun
 */
public class MySQLPOSConnect {
    
    private Connection connection;

    public Connection openConnection() {
        try {
            MySQLConnect mysql = new MySQLConnect();
            this.connection = mysql.openConnection("pos");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            this.connection = null;
        }
        return this.connection;
    }

    public static void main(String[] args) {
        MySQLPOSConnect mysql = new MySQLPOSConnect();
        try {
            try (Connection conn = mysql.openConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("select * from redeem")) {
                while (rs.next()) {
                    System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
