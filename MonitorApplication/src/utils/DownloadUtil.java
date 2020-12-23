package utils;

import database.DbConfig;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
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

    private static final Logger LOGGER = Logger.getLogger(DownloadUtil.class);
    private static final SimpleDateFormat simp = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);

    public static void main(String[] args) {
        DownloadUtil.downloadAppUpdate();
    }

    public static void downloadAppUpdate() {
        LOGGER.debug("downloadAppUpdate");
        DbConfig config = DbConfig.loadConfig();
        StringBuilder contents = new StringBuilder(4096);
        BufferedReader br = null;

        try {
            String dateFormat = simp.format(new Date());
            String applicationName = "update_" + dateFormat + ".zip";
            String downloadSite = config.getPathDownload() + "/" + applicationName;
            String outputFile = "applications/" + applicationName;
            URL url = new URL(downloadSite);
            InputStream is = url.openConnection().getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            PrintStream ps = new PrintStream(new FileOutputStream(outputFile));
            String line;
            String newline = System.getProperty("line.separator");
            while ((line = br.readLine()) != null) {
                contents.append(line).append(newline);
            }
            ps.println(contents.toString());
            LOGGER.info("download application update finish");
            JOptionPane.showMessageDialog(null,
                    "Download application file update complete",
                    "Download success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "You are already using the latest version",
                    "No newer version found.",
                    JOptionPane.INFORMATION_MESSAGE);
            LOGGER.error(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
