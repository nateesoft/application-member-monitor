package database;

import java.io.FileInputStream;
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

    public static DbConfig loadConfig() {
        LOGGER.debug("loadConfig");
        DbConfig config = new DbConfig();
        try (InputStream input = new FileInputStream("local.txt")) {
            Properties prop = new Properties();
            prop.load(input);

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

}
