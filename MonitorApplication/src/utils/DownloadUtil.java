package utils;

import file.config.ConfigProps;
import file.config.FileConfigValue;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author nateesun
 */
public class DownloadUtil {

    private static final String folderApplications = "applications";
    private static final Logger LOGGER = Logger.getLogger(DownloadUtil.class);
    private static final SimpleDateFormat simp = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
    private static final ConfigProps config = FileConfigValue.loadConfig();

    public static void downloadAppUpdate() {
        LOGGER.debug("downloadAppUpdate");
        String dateFormat = simp.format(new Date());
        try {
            String applicationName = "update_" + dateFormat + ".zip";
            String downloadSite = config.getAppDownload() + "/" + applicationName;
            String outputFile = folderApplications + "/" + applicationName;
            new File(folderApplications).mkdir();

            URL url = new URL(downloadSite);
            try ( BufferedInputStream bis = new BufferedInputStream(url.openStream());  FileOutputStream fis = new FileOutputStream(outputFile)) {
                byte[] buffer = new byte[1024];
                int count;
                while ((count = bis.read(buffer, 0, 1024)) != -1) {
                    fis.write(buffer, 0, count);
                }
            }

            // unzip file
            UnzipUtility.unzip4(outputFile, folderApplications + "/temp");
            File[] files = new File(folderApplications + "/temp/dist").listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    UnzipUtility.copyFile(file, new File(file.getName()));
                } else if (file.isDirectory()) {
                    UnzipUtility.copyDirectory(file, new File(file.getName()));
                }
            }

            // stamp file update version
            new File("update_" + dateFormat + ".version").createNewFile();

            LOGGER.info("download application update finish, Please restart application");
            JOptionPane.showMessageDialog(null,
                    "Update application to latest version, \nPlease start application again",
                    "Update application complete",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } catch (IOException e) {
            LOGGER.warn("Not found application to update today");
        }
    }

    public static void downloadAppUpdate_NewVersion() {
        LOGGER.debug("downloadAppUpdate_NewVersion");
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
