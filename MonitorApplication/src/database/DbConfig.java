package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author nateesun
 */
public class DbConfig {

    private static final Logger LOGGER = Logger.getLogger(DbConfig.class);
    private static final File FILE_CONFIG = new File("local.txt");
    private static final File FILE_IMAGE_PNG = new File("icon-sync.png");
    private static DbConfig config = null;

    private String hostPos;
    private String userPos;
    private String passwordPos;
    private String portPos;
    private String dbNamePos;

    private String hostMember;
    private String userMember;
    private String passwordMember;
    private String portMember;
    private String dbNameMember;

    private String apiServiceMember;
    private String apiServiceRedeem;
    private String apiServiceDB;
    private String apiServiceAuth;
    private String apiVersion;

    private int timeSync;
    private String pathDownload;

    private static void writeDefaultConfigFile() {
        LOGGER.debug("writeDefaultConfigFile");
        try {
            try (FileWriter myWriter = new FileWriter(FILE_CONFIG)) {
                myWriter.write("### LOCAL DB ###\n"
                        + "pos.host=localhost\n"
                        + "pos.user=root\n"
                        + "pos.password=\n"
                        + "pos.port=3306\n"
                        + "pos.dbName=myrestaurant\n"
                        + "\n"
                        + "### SERVER DB ###\n"
                        + "member.host=localhost\n"
                        + "member.user=root\n"
                        + "member.password=\n"
                        + "member.port=3306\n"
                        + "member.dbName=mycrmbranch\n"
                        + "\n"
                        + "### API ENDPOINT ###\n"
                        + "api.serviceVersion=http://softcrmpkh.dyndns.org:5000/api/version\n"
                        + "api.serviceMember=http://softcrmpkh.dyndns.org:5000/api/member/client\n"
                        + "api.serviceRedeem=http://softcrmpkh.dyndns.org:5000/api/redeem/client\n"
                        + "api.serviceDB=d2ViZGFpbHlfMDAx\n"
                        + "api.serviceAuth=YWRtaW46c29mdHBvczIwMTM=\n"
                        + "\n"
                        + "### TIME SYNC ###\n"
                        + "time.sync=10000\n"
                        + "app.download=http://softcrmpkh.dyndns.org:5000/images/applications");
            }
            LOGGER.info("Successfully wrote to the file.");
        } catch (IOException e) {
            LOGGER.error("An error occurred.");
        }
    }

    public static DbConfig loadConfig() {
        LOGGER.debug("loadConfig");
        if (!FILE_CONFIG.exists()) {
            writeDefaultConfigFile();
        }
        if (config != null) {
            return config;
        }
        try (InputStream input = new FileInputStream("local.txt")) {
            Properties prop = new Properties();
            prop.load(input);

            config = new DbConfig();
            config.setHostPos(prop.getProperty("pos.host"));
            config.setUserPos(prop.getProperty("pos.user"));
            config.setPasswordPos(prop.getProperty("pos.password"));
            config.setPortPos(prop.getProperty("pos.port"));
            config.setDbNamePos(prop.getProperty("pos.dbName"));

            config.setHostMember(prop.getProperty("member.host"));
            config.setUserMember(prop.getProperty("member.user"));
            config.setPasswordMember(prop.getProperty("member.password"));
            config.setPortMember(prop.getProperty("member.port"));
            config.setDbNameMember(prop.getProperty("member.dbName"));

            config.setApiServiceMember(prop.getProperty("api.serviceMember"));
            config.setApiServiceRedeem(prop.getProperty("api.serviceRedeem"));
            config.setApiServiceDB(prop.getProperty("api.serviceDB"));
            config.setApiServiceAuth(prop.getProperty("api.serviceAuth"));
            config.setApiVersion(prop.getProperty("api.serviceVersion"));

            config.setTimeSync(Integer.parseInt(prop.getProperty("time.sync")));
            config.setPathDownload(prop.getProperty("app.download"));
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }

        return config;
    }

    public String getHostPos() {
        return hostPos;
    }

    public void setHostPos(String hostPos) {
        this.hostPos = hostPos;
    }

    public String getUserPos() {
        return userPos;
    }

    public void setUserPos(String userPos) {
        this.userPos = userPos;
    }

    public String getPasswordPos() {
        return passwordPos;
    }

    public void setPasswordPos(String passwordPos) {
        this.passwordPos = passwordPos;
    }

    public String getPortPos() {
        return portPos;
    }

    public void setPortPos(String portPos) {
        this.portPos = portPos;
    }

    public String getDbNamePos() {
        return dbNamePos;
    }

    public void setDbNamePos(String dbNamePos) {
        this.dbNamePos = dbNamePos;
    }

    public String getHostMember() {
        return hostMember;
    }

    public void setHostMember(String hostMember) {
        this.hostMember = hostMember;
    }

    public String getUserMember() {
        return userMember;
    }

    public void setUserMember(String userMember) {
        this.userMember = userMember;
    }

    public String getPasswordMember() {
        return passwordMember;
    }

    public void setPasswordMember(String passwordMember) {
        this.passwordMember = passwordMember;
    }

    public String getPortMember() {
        return portMember;
    }

    public void setPortMember(String portMember) {
        this.portMember = portMember;
    }

    public String getDbNameMember() {
        return dbNameMember;
    }

    public void setDbNameMember(String dbNameMember) {
        this.dbNameMember = dbNameMember;
    }

    public String getApiServiceMember() {
        return apiServiceMember;
    }

    public void setApiServiceMember(String apiServiceMember) {
        this.apiServiceMember = apiServiceMember;
    }

    public String getApiServiceRedeem() {
        return apiServiceRedeem;
    }

    public void setApiServiceRedeem(String apiServiceRedeem) {
        this.apiServiceRedeem = apiServiceRedeem;
    }

    public String getApiServiceDB() {
        return apiServiceDB;
    }

    public void setApiServiceDB(String apiServiceDB) {
        this.apiServiceDB = apiServiceDB;
    }

    public String getApiServiceAuth() {
        return apiServiceAuth;
    }

    public void setApiServiceAuth(String apiServiceAuth) {
        this.apiServiceAuth = apiServiceAuth;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public int getTimeSync() {
        return timeSync;
    }

    public void setTimeSync(int timeSync) {
        this.timeSync = timeSync;
    }

    public String getPathDownload() {
        return pathDownload;
    }

    public void setPathDownload(String pathDownload) {
        this.pathDownload = pathDownload;
    }

}
