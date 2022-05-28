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

    public static final File FILE_CONFIG = new File("local.txt");
    public static final File FILE_IMAGE_PNG = new File("icon-sync.png");
    public static final File FILE_IMAGE_DISCONNECT_PNG = new File("icon-disconnected.png");
    private static DbConfigProps config = null;

    private static void writeDefaultConfigFile() {
        LOGGER.debug("writeDefaultConfigFile");
        try {
            try ( FileWriter myWriter = new FileWriter(FILE_CONFIG)) {
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
                        + "api.serviceHost=http://softcrmpkh.dyndns.org:5000\n"
                        + "api.serviceVersion=http://softcrmpkh.dyndns.org:5000/api/version\n"
                        + "api.serviceMember=http://softcrmpkh.dyndns.org:5000/api/member/client\n"
                        + "api.serviceRedeem=http://softcrmpkh.dyndns.org:5000/api/redeem/client\n"
                        + "api.serviceDB=d2ViZGFpbHlfMDAx\n"
                        + "api.serviceAuth=YWRtaW46c29mdHBvczIwMTM=\n"
                        + "\n"
                        + "### TIME SYNC ###\n"
                        + "time.sync=10\n"
                        + "app.download=http://softcrmpkh.dyndns.org:5000/images/applications");
            }
            LOGGER.info("Successfully wrote to the file.");
        } catch (IOException e) {
            LOGGER.error("An error occurred.");
        }
    }

    public static DbConfigProps loadConfig() {
        LOGGER.debug("loadConfig");
        if (!FILE_CONFIG.exists()) {
            writeDefaultConfigFile();
        }
        if (config != null) {
            return config;
        }
        try ( InputStream input = new FileInputStream("local.txt")) {
            Properties prop = new Properties();
            prop.load(input);

            config = new DbConfigProps();
            config.setPosHost(prop.getProperty("pos.host"));
            config.setPosUser(prop.getProperty("pos.user"));
            config.setPosPassword(prop.getProperty("pos.password"));
            config.setPosPort(prop.getProperty("pos.port"));
            config.setPosDbName(prop.getProperty("pos.dbName"));

            config.setMemberHost(prop.getProperty("member.host"));
            config.setMemberUser(prop.getProperty("member.user"));
            config.setMemberPassword(prop.getProperty("member.password"));
            config.setMemberPort(prop.getProperty("member.port"));
            config.setMemberDbName(prop.getProperty("member.dbName"));

            config.setApiServiceHost(prop.getProperty("api.serviceHost"));
            config.setApiServiceMember(prop.getProperty("api.serviceMember"));
            config.setApiServiceRedeem(prop.getProperty("api.serviceRedeem"));
            config.setApiServiceDB(prop.getProperty("api.serviceDB"));
            config.setApiServiceAuth(prop.getProperty("api.serviceAuth"));
            config.setApiServiceVersion(prop.getProperty("api.serviceVersion"));

            config.setTimeSync(Integer.parseInt(prop.getProperty("time.sync")));
            config.setAppDownload(prop.getProperty("app.download"));
            config.setThaiUtf(prop.getProperty("thaiUtf"));
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }

        return config;
    }

    public static void saveConfigFileData(DbConfigProps props) {
        try {
            try ( FileWriter myWriter = new FileWriter(DbConfig.FILE_CONFIG)) {
                myWriter.write("### LOCAL DB ###\n"
                        + "pos.host=" + props.getPosHost() + "\n"
                        + "pos.user=" + props.getPosUser() + "\n"
                        + "pos.password=" + props.getPosPassword() + "\n"
                        + "pos.port=" + props.getPosPort() + "\n"
                        + "pos.dbName=" + props.getPosDbName() + "\n"
                        + "\n"
                        + "### SERVER DB ###\n"
                        + "member.host=" + props.getMemberHost() + "\n"
                        + "member.user=" + props.getMemberUser() + "\n"
                        + "member.password=" + props.getMemberPassword() + "\n"
                        + "member.port=" + props.getMemberPort() + "\n"
                        + "member.dbName=" + props.getMemberDbName() + "\n"
                        + "\n"
                        + "### API ENDPOINT ###\n"
                        + "api.serviceHost=" + props.getApiServiceHost() + "\n"
                        + "api.serviceVersion=" + props.getApiServiceVersion() + "\n"
                        + "api.serviceMember=" + props.getApiServiceMember() + "\n"
                        + "api.serviceRedeem=" + props.getApiServiceRedeem() + "\n"
                        + "api.serviceDB=" + props.getApiServiceDB() + "\n"
                        + "api.serviceAuth=" + props.getApiServiceAuth() + "\n"
                        + "\n"
                        + "### TIME SYNC ###\n"
                        + "time.sync=" + props.getTimeSync() + "\n"
                        + "app.download=" + props.getAppDownload() + "");
            }
            LOGGER.info("Successfully wrote to the file.");
        } catch (IOException e) {
            LOGGER.error("An error occurred.");
        }
    }

}
