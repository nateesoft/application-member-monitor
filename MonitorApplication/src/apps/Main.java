package apps;

import database.DbConfig;
import database.DbConfigProps;
import io.socket.client.IO;
import io.socket.client.Socket;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import utils.DownloadUtil;

/**
 *
 * @author nateesun
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static final SimpleDateFormat simp = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
    private static DbConfigProps config = null;

    public static void main(String[] args) {
        if (!SystemTray.isSupported()) {
            LOGGER.error("System tray is not supported !!! ");
            return;
        }

        if (config == null) {
            config = DbConfig.loadConfig();
        }

        SystemTray systemTray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage(DbConfig.FILE_IMAGE_PNG.getAbsolutePath());
        Image imageDisconnect = Toolkit.getDefaultToolkit().getImage(DbConfig.FILE_IMAGE_DISCONNECT_PNG.getAbsolutePath());

        PopupMenu trayPopupMenu = new PopupMenu();
        MenuItem action = new MenuItem("Log Apps");
        action.addActionListener((ActionEvent e) -> {
            LogAppDialog dialog = new LogAppDialog(null, true);
            dialog.setVisible(true);
        });

        trayPopupMenu.add(action);
        trayPopupMenu.addSeparator();

        MenuItem downloadUpdate = new MenuItem("Check Update");
        downloadUpdate.addActionListener((ActionEvent e) -> {
            LOGGER.info("Download and update");
            if (checkVersion()) {
                DownloadUtil.downloadAppUpdate();
            } else {
                JOptionPane.showMessageDialog(null,
                        "You are already using the latest version",
                        "No newer version found.",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        trayPopupMenu.add(downloadUpdate);
        trayPopupMenu.addSeparator();

        MenuItem close = new MenuItem("Exit");
        close.addActionListener((ActionEvent e) -> {
            LOGGER.info("Close application monitor");
            System.exit(0);
        });
        trayPopupMenu.add(close);

        TrayIcon trayIcon = new TrayIcon(imageDisconnect, "Web daily sync (Disconnected)", trayPopupMenu);
        try {
            Socket socketSync = IO.socket(config.getApiServiceHost());
            socketSync.on(Socket.EVENT_CONNECT, (Object... os) -> {
                // check if connected
                trayIcon.setToolTip("Web daily sync (Connected)");
                trayIcon.setImage(image);
            });

            socketSync.on(Socket.EVENT_DISCONNECT, (Object... os) -> {
                // check if disconnected
                trayIcon.setToolTip("Web daily sync (Disconnected)");
                trayIcon.setImage(imageDisconnect);
            });

            socketSync.open();
        } catch (URISyntaxException e) {
            LOGGER.error(e.getMessage());
        }

        trayIcon.setImageAutoSize(true);
        trayIcon.addActionListener((ActionEvent e) -> {
            System.out.println("Click Alert");
        });

        try {
            systemTray.add(trayIcon);
        } catch (AWTException awtException) {
            LOGGER.error(awtException.getMessage());
        }

        // first time download
        if (checkVersion()) {
            DownloadUtil.downloadAppUpdate();
        }

        // start application monitory running
        LOGGER.info("start application monitory");
        TaskController.run();
    }

    private static boolean checkVersion() {
        return !new File("update_" + simp.format(new Date()) + ".version").exists();
    }
}
