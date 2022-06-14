package apps.main.ui;

import api.connect.ControllerApi;
import api.connect.model.MemberMapping;
import api.connect.model.RedeemMapping;
import core.controller.TaskController;
import core.memmaster.Memmaster;
import core.redeem.Redeem;
import file.config.ConfigProps;
import file.config.FileConfigValue;
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
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.apache.log4j.Logger;
import utils.DownloadUtil;

/**
 *
 * @author nateesun
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static final SimpleDateFormat simp = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
    private static final ConfigProps config = FileConfigValue.loadConfig();
    private static final ControllerApi callApi = new ControllerApi();

    private static final Memmaster memmaster = new Memmaster();
    private static final Redeem redeem = new Redeem();

    public static void main(String[] args) {
        if (!SystemTray.isSupported()) {
            LOGGER.error("System tray is not supported !!! ");
            return;
        }

        SystemTray systemTray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage(FileConfigValue.FILE_IMAGE_PNG.getAbsolutePath());
        Image imageDisconnect = Toolkit.getDefaultToolkit().getImage(FileConfigValue.FILE_IMAGE_DISCONNECT_PNG.getAbsolutePath());

        PopupMenu trayPopupMenu = new PopupMenu();
        MenuItem action = new MenuItem("Log Apps");
        action.addActionListener((ActionEvent e) -> {
            LogAppDialog dialog = new LogAppDialog(null, true);
            dialog.setVisible(true);
        });

        trayPopupMenu.add(action);
        trayPopupMenu.addSeparator();

        MenuItem downloadUpdate = new MenuItem("Download last version");
        downloadUpdate.addActionListener((ActionEvent e) -> {
            LOGGER.info("Download and update");
            DownloadUtil.downloadAppUpdate();
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
                trayIcon.setToolTip("Web daily sync (Connected)");
                trayIcon.setImage(image);
                trayIcon.displayMessage("Server Status", "Connected", TrayIcon.MessageType.INFO);
            });

            socketSync.on(Socket.EVENT_DISCONNECT, (Object... os) -> {
                trayIcon.setToolTip("Web daily sync (Disconnected)");
                trayIcon.setImage(imageDisconnect);
                trayIcon.displayMessage("Server Status", "Disconnected", TrayIcon.MessageType.ERROR);
            });

            socketSync.on("sync_member", (Object... response) -> {
                TaskController.refreshMemberListFromServer();
                MemberMapping data = callApi.getMemberOneMapping(response[0].toString());
                boolean validSave = false;
                if (data.getAction_status().equals("create")) {
                    validSave = memmaster.saveOrUpdateList(data.getData());
                } else if (data.getAction_status().equals("update")) {
                    validSave = memmaster.updatePoint(data.getData()[0]);
                }
                if (validSave) {
                    trayIcon.displayMessage("Sync Data", "Found sync", TrayIcon.MessageType.INFO);
                }
            });

            socketSync.on("sync_redeem", (Object... response) -> {
                TaskController.refreshRedeemListFromServer();
                RedeemMapping data = callApi.getRedeemMapping(response[0].toString());
                boolean validSave = false;
                if (data.getAction_status().equals("create")) {
                    validSave = redeem.saveOrUpdateList(data.getData());
                } else if (data.getAction_status().equals("update")) {
                    validSave = redeem.update(data.getData()[0]);
                }
                if (validSave) {
                    trayIcon.displayMessage("Sync Data", "Found sync", TrayIcon.MessageType.INFO);
                }
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

        // start application monitory running
        LOGGER.info("start application monitory");

        // initial sync first time open application
        TaskController.syncDown();

        // scheduler time sync up
        TaskController.run(10);
    }
}
