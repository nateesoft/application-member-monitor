package utils;

import file.config.ConfigProps;
import file.config.FileConfigValue;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author nateesun
 */
public class DownloadUtil {

    private static final String folderApplications = "applications";
    private static final String folderTempApplications = "applications/temp";
    private static final Logger LOGGER = Logger.getLogger(DownloadUtil.class);
    private static final SimpleDateFormat simp = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
    private static final ConfigProps config = FileConfigValue.loadConfig();

    public static void downloadAppUpdate() {
        LOGGER.debug("downloadAppUpdate");
        try {
            String applicationName = "MonitorApplication.zip";
            String downloadSite = config.getAppDownload() + "/" + applicationName;
            String outputFile = folderApplications + "/" + applicationName;
            if (!new File(folderApplications).exists()) {
                new File(folderApplications).mkdir();
            }

            URL url = new URL(downloadSite);
            try ( BufferedInputStream bis = new BufferedInputStream(url.openStream());  FileOutputStream fis = new FileOutputStream(outputFile)) {
                byte[] buffer = new byte[1024];
                int count;
                while ((count = bis.read(buffer, 0, 1024)) != -1) {
                    fis.write(buffer, 0, count);
                }
            }
            JOptionPane.showMessageDialog(null, "Download application update finish, \nPlease update application");
            LOGGER.info("download application update finish, Please update application");
            System.exit(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Not found application to update today");
            LOGGER.warn("Not found application to update today");
        }
    }
}
