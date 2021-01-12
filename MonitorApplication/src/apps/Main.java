package apps;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

    public static void main(String[] args) {
        if (!SystemTray.isSupported()) {
            LOGGER.error("System tray is not supported !!! ");
            return;
        }

        SystemTray systemTray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage(new File("icon-sync.png").getAbsolutePath());

        PopupMenu trayPopupMenu = new PopupMenu();
        MenuItem action = new MenuItem("Log Apps");
        action.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogAppDialog dialog = new LogAppDialog(null, true);
                dialog.setVisible(true);
            }
        });
        trayPopupMenu.add(action);
        trayPopupMenu.addSeparator();
        MenuItem downloadUpdate = new MenuItem("Check Update");
        downloadUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("Download and update");
                if (!new File("update_" + simp.format(new Date()) + ".version").exists()) {
                    DownloadUtil.downloadAppUpdate();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "You are already using the latest version",
                            "No newer version found.",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        trayPopupMenu.add(downloadUpdate);
        trayPopupMenu.addSeparator();
        MenuItem close = new MenuItem("Exit");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("Close application monitor");
                System.exit(0);
            }
        });
        trayPopupMenu.add(close);

        TrayIcon trayIcon = new TrayIcon(image, "Web daily sync", trayPopupMenu);
        trayIcon.setImageAutoSize(true);
        trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click Alert");
            }
        });

        try {
            systemTray.add(trayIcon);
        } catch (AWTException awtException) {
            LOGGER.error(awtException.getMessage());
        }

        // first time download
        if (!new File("update_" + simp.format(new Date()) + ".version").exists()) {
            DownloadUtil.downloadAppUpdate();
        }

//        while (!Thread.currentThread().isInterrupted()) {
//            try {
//                // show notification
//                trayIcon.displayMessage("Attention!", "From Server", TrayIcon.MessageType.INFO);
//                Thread.sleep(10000);
//            } catch (InterruptedException ex) {
//                LOGGER.error(ex.getMessage());
//            }
//        }
        // start application monitory running
        LOGGER.info("start application monitory");
        TaskController.run();
    }
}
