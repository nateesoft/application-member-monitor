package file.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import utils.Base64Util;

/**
 *
 * @author nateesun
 */
public class FileConfigValue {

    private static final Logger LOGGER = Logger.getLogger(FileConfigValue.class);

    public static final File FILE_CONFIG = new File("local.ini");
    public static final File FILE_IMAGE_PNG = new File("icon-sync.png");
    public static final File FILE_IMAGE_DISCONNECT_PNG = new File("icon-disconnected.png");
    public static final ConfigProps config = new ConfigProps();

    static {
        if (!FILE_CONFIG.exists()) {
            writeDefaultConfigFile();
        }

        // init load config data
        try ( InputStream input = new FileInputStream(FILE_CONFIG)) {
            Properties prop = new Properties();
            prop.load(input);

            config.setPosHost(prop.getProperty("pos.host"));
            config.setPosUser(prop.getProperty("pos.user"));
            config.setPosPassword(Base64Util.decode(prop.getProperty("pos.password")));
            config.setPosPort(prop.getProperty("pos.port"));
            config.setPosDb(prop.getProperty("pos.db"));
            config.setPosMemberDb(prop.getProperty("pos.memberDb"));

            config.setApiServiceHost(prop.getProperty("api.serviceHost"));
            config.setApiServiceMember(prop.getProperty("api.serviceHost") + "/api/member/client");
            config.setApiServiceRedeem(prop.getProperty("api.serviceHost") + "/api/redeem/client");
            config.setApiServiceDB(prop.getProperty("api.serviceDB"));
            config.setApiServiceAuth("YWRtaW46c29mdHBvczIwMTM="); // fix auth to secure
            config.setApiServiceVersion(prop.getProperty("api.serviceHost") + "/version");

            config.setAppDownload(prop.getProperty("api.serviceHost")+"/images/applications");
            config.setThaiUtf(prop.getProperty("thaiUtf"));
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private static void writeDefaultConfigFile() {
        LOGGER.debug("writeDefaultConfigFile");
        try {
            try ( FileWriter myWriter = new FileWriter(FILE_CONFIG)) {
                myWriter.write("### LOCAL DB ###\n"
                        + "pos.host=localhost\n"
                        + "pos.user=root\n"
                        + "pos.password=\n"
                        + "pos.port=3306\n"
                        + "pos.db=myrestaurant\n"
                        + "pos.memberDb=mycrmbranch\n"
                        + "\n"
                        + "### API ENDPOINT ###\n"
                        + "api.serviceHost=http://softcrmpkh.dyndns.org:5000\n"
                        + "api.serviceDB=d2ViZGFpbHlfMDAx\n"
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

    public static ConfigProps loadConfig() {
        return config;
    }

    public static void saveConfigFileData(ConfigProps props) {
        try {
            try ( FileWriter myWriter = new FileWriter(FileConfigValue.FILE_CONFIG)) {
                myWriter.write("### LOCAL DB ###\n"
                        + "pos.host=" + props.getPosHost() + "\n"
                        + "pos.user=" + props.getPosUser() + "\n"
                        + "pos.password=" + Base64Util.encode(props.getPosPassword()) + "\n"
                        + "pos.port=" + props.getPosPort() + "\n"
                        + "pos.db=" + props.getPosDb() + "\n"
                        + "pos.memberDb=" + props.getPosMemberDb() + "\n"
                        + "\n"
                        + "### API ENDPOINT ###\n"
                        + "api.serviceHost=" + props.getApiServiceHost() + "\n"
                        + "api.serviceDB=" + props.getApiServiceDB() + "\n"
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
