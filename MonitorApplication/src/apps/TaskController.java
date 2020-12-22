package apps;

import api.ControllerApi;
import api.MemberModel;
import database.local.ControllerDB;
import database.local.RedeemModel;
import java.io.IOException;
import org.apache.log4j.Logger;
import utils.ArrayDiff;

/**
 *
 * @author nateesun
 */
public class TaskController {
    private static final Logger LOGGER = Logger.getLogger(TaskController.class);

    private static final ControllerApi API = new ControllerApi();
    private static final ControllerDB DB_LOCAL = new ControllerDB();
    private static int count = 0;

    public static void run() {
        LOGGER.info("Task running");
        while (!Thread.currentThread().isInterrupted()) {
            syncDown();
            pushUp();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
               LOGGER.error(ex.getMessage());
            }
        }
    }

    public static void syncDown() {
        LOGGER.debug("syncDown");
        try {
            MemberModel memberServerList[] = API.getMemberMapping();
            RedeemModel redeemServerList[] = API.getRedeemMapping();

            MemberModel memberLocalList[] = DB_LOCAL.getMember();
            RedeemModel redeemLocalList[] = DB_LOCAL.getRedeem();

            MemberModel[] insertMember = ArrayDiff.diffInsertUpdate(memberServerList, memberLocalList);
            RedeemModel[] insertRedeem = ArrayDiff.diffInsertUpdate(redeemServerList, redeemLocalList);

            DB_LOCAL.saveUpdateMember(insertMember);
            DB_LOCAL.saveUpdateRedeem(insertRedeem);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public static void pushUp() {
        LOGGER.debug("pushUp");
        // sync from file member local

        // sync from file redeem local
    }
}
