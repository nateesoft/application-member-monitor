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
import org.apache.log4j.Logger;

/**
 *
 * @author nateesun
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

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

        try {
            systemTray.add(trayIcon);
        } catch (AWTException awtException) {
            LOGGER.error(awtException.getMessage());
        }

        // start application monitory running
        LOGGER.info("start application monitory");
        TaskController.run();
    }
}
