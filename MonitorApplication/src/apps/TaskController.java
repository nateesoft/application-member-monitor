package apps;

import api.ControllerApi;
import api.MemberModel;
import database.local.ControllerDB;
import database.local.MemmasterModel;
import database.local.RedeemModel;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nateesun
 */
public class TaskController {

    private static final ControllerApi api = new ControllerApi();
    private static final ControllerDB local = new ControllerDB();

    public static void run() {

    }

    public static void syncDown() {
        try {
            MemberModel memberServerList[] = api.getMemberMapping();// get new member
            RedeemModel redeemServerList[] = api.getRedeemMapping();// get new redeem

            MemmasterModel memmasterList[] = local.getMemmaster();// get local memmaster
            RedeemModel redeemLocalList[] = local.getRedeem();// get local memmaster

            if (Arrays.equals(memberServerList, memmasterList)) {
                System.out.println("Member same");
            } else {
                System.out.println("Member not same");
            }
            if (Arrays.equals(redeemServerList, redeemLocalList)) {
                System.out.println("Redeem same");
            } else {
                System.out.println("Redeem not same");
            }
        } catch (IOException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void syncUp() {
        try {
            // get local member
            MemberModel memberList[] = api.getMemberMapping();
            for (MemberModel member : memberList) {
                System.out.println(member.getCode());
            }

            // get local redeem
            RedeemModel redeemList[] = api.getRedeemMapping();
            for (RedeemModel member : redeemList) {
                System.out.println(member.getRedeem_code());
            }
        } catch (IOException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
