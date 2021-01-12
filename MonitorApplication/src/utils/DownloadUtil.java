package utils;

import database.DbConfig;
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
    private static final String folderTempApplications = "applications/temp";
    private static final Logger LOGGER = Logger.getLogger(DownloadUtil.class);
    private static final SimpleDateFormat simp = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);

    public static void downloadAppUpdate() {
        LOGGER.debug("downloadAppUpdate");
        DbConfig config = DbConfig.loadConfig();
        try {
            String dateFormat = simp.format(new Date());
            String applicationName = "update_" + dateFormat + ".zip";
            String downloadSite = config.getPathDownload() + "/" + applicationName;
            String outputFile = folderApplications + "/" + applicationName;
            if (!new File(folderApplications).exists()) {
                new File(folderApplications).mkdir();
            }

            URL url = new URL(downloadSite);
            try (BufferedInputStream bis = new BufferedInputStream(url.openStream());
                    FileOutputStream fis = new FileOutputStream(outputFile)) {
                byte[] buffer = new byte[1024];
                int count;
                while ((count = bis.read(buffer, 0, 1024)) != -1) {
                    fis.write(buffer, 0, count);
                }
            }
            
            // unzip file
            LOGGER.info("unzip file from applicatioj download temp file");
            if (!new File(folderTempApplications).exists()) {
                new File(folderTempApplications).mkdir();
            }
            UnzipUtility.unzip(outputFile, folderTempApplications);

            // copy to update program
            File from = new File(folderTempApplications + "/dist/MonitorApplication.jar");
            File to = new File("MonitorApplication.jar");
            UnzipUtility.copyFile(from, to);

            // copy lib to
            File folderDist = new File("lib");
            LOGGER.info("Copy lib to " + folderDist.getAbsolutePath());
            UnzipUtility.copyDirectory(new File(folderTempApplications + "/dist/lib"), folderDist);

            // stamp file update version
            new File("update_" + dateFormat + ".version").createNewFile();
            
            LOGGER.info("download application update finish, Please restart application");
            JOptionPane.showMessageDialog(null,
                    "Update application to latest version, \nPlease start application again",
                    "Update application complete",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Notfound Application Update Today",
                    e.getMessage(), JOptionPane.INFORMATION_MESSAGE);
            LOGGER.error(e.getMessage());
        }

    }

}
