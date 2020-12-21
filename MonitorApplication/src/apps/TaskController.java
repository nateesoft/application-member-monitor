package apps;

import api.ControllerApi;
import api.MemberModel;
import database.local.ControllerDB;
import database.local.RedeemModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ArrayDiff;

/**
 *
 * @author nateesun
 */
public class TaskController {

    private static final ControllerApi api = new ControllerApi();
    private static final ControllerDB local = new ControllerDB();

    public static void run() {
        syncDown();
    }

    public static void syncDown() {
        try {
            MemberModel memberServerList[] = api.getMemberMapping();
            RedeemModel redeemServerList[] = api.getRedeemMapping();

            MemberModel memberLocalList[] = local.getMember();
            RedeemModel redeemLocalList[] = local.getRedeem();

            MemberModel []insertMember = ArrayDiff.diffInsertUpdate(memberServerList, memberLocalList);
            RedeemModel []insertRedeem = ArrayDiff.diffInsertUpdate(redeemServerList, redeemLocalList);
            
           local.saveUpdateMember(insertMember);
           local.saveUpdateRedeem(insertRedeem);
        } catch (IOException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void pushUp() {
        
    }
}
