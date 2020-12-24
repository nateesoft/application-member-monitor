package apps;

import api.ControllerApi;
import api.MemberModel;
import database.DbConfig;
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
        DbConfig config = DbConfig.loadConfig();
        while (!Thread.currentThread().isInterrupted()) {
            if (count % 2 == 0) {
                syncDown();
            } else {
                pushUp();
            }
            try {
                count++;
                Thread.sleep(config.getTimeSync());
            } catch (InterruptedException ex) {
                LOGGER.error(ex.getMessage());
            }
            if (count == 100) {
                count = 0;
            }
        }
    }

    // insert onlys
    public static void syncDown() {
        LOGGER.debug("syncDown");
        try {
            // member
            MemberModel memberServerList[] = API.getMemberMapping();
            if (memberServerList.length > 0) {
                MemberModel memberLocalList[] = DB_LOCAL.getMember();
                MemberModel[] insertMember = ArrayDiff.diffInsertUpdate(memberServerList, memberLocalList);
                if (insertMember.length > 0) {
                    DB_LOCAL.saveUpdateMember(insertMember);
                }
            }

            // redeem
            RedeemModel redeemServerList[] = API.getRedeemMapping();
            if (redeemServerList.length > 0) {
                RedeemModel redeemLocalList[] = DB_LOCAL.getRedeem();
                RedeemModel[] insertRedeem = ArrayDiff.diffInsertUpdate(redeemServerList, redeemLocalList);
                if (insertRedeem.length > 0) {
                    DB_LOCAL.saveUpdateRedeem(insertRedeem);
                }
            }

        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    // send to update only
    public static void pushUp() {
        LOGGER.debug("pushUp");
        try {
            // member
            MemberModel memberServerList[] = API.getMemberMapping();
            if (memberServerList.length > 0) {
                MemberModel memberLocalList[] = DB_LOCAL.getMember();
                MemberModel[] insertMember = ArrayDiff.diffInsertUpdate(memberServerList, memberLocalList);
                if (insertMember.length > 0) {
                    API.pushMemberService();
                }
            }

            // redeem
            RedeemModel redeemServerList[] = API.getRedeemMapping();
            if (redeemServerList.length > 0) {
                RedeemModel redeemLocalList[] = DB_LOCAL.getRedeem();
                RedeemModel[] insertRedeem = ArrayDiff.diffInsertUpdate(redeemServerList, redeemLocalList);
                if (insertRedeem.length > 0) {
                    API.pushRedeemService();
                }
            }

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
