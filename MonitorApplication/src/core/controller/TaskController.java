package core.controller;

import api.connect.ControllerApi;
import api.connect.model.MemberModel;
import core.redeem.model.RedeemModel;
import java.io.IOException;
import org.apache.log4j.Logger;
import utils.ArrayDiffMember;
import utils.ArrayDiffRedeem;

/**
 *
 * @author nateesun
 */
public class TaskController {

    private static final Logger LOGGER = Logger.getLogger(TaskController.class);

    private static final ControllerApi callAPI = new ControllerApi();
    private static final ControllerDB callLocalDb = new ControllerDB();

    private static MemberModel[] memberServerList = null;
    private static RedeemModel[] redeemServerList = null;
    
    private static final ArrayDiffMember arrayDiffMember = new ArrayDiffMember();
    private static final ArrayDiffRedeem arrayDiffRedeem = new ArrayDiffRedeem();

    public static void run(int timeSync) {
        LOGGER.info("Task running");
        if (timeSync < 1) {
            timeSync = 1;
        }
        
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            count++;
            try {
                pushUp();
                Thread.sleep(timeSync * 1000);
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
            memberServerList = callAPI.getMemberMapping();
            callLocalDb.saveMemberList(memberServerList);
            
            // redeem
            redeemServerList = callAPI.getRedeemMapping();
            callLocalDb.saveRedeemList(redeemServerList);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
    
    // refresh list from server onlys
    public static void refreshMemberListFromServer() {
        LOGGER.debug("refreshMemberListFromServer");
        try {
            memberServerList = callAPI.getMemberMapping();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
    
    // refresh list from server onlys
    public static void refreshRedeemListFromServer() {
        LOGGER.debug("refreshRedeemListFromServer");
        try {
            redeemServerList = callAPI.getRedeemMapping();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    // send to update only
    public static void pushUp() {
        LOGGER.debug("pushUp");
        try {
            // member
            MemberModel[] memberLocalList = callLocalDb.getMemberFromBillno();
            if (memberLocalList.length > 0) {
                MemberModel[] updateMember = arrayDiffMember.getLocalDiff(memberServerList, memberLocalList);
                if (updateMember.length > 0) {
                    callAPI.pushMemberService(updateMember);
                }
            }

            // redeem
            RedeemModel[] redeemLocalList = callLocalDb.getRedeem();
            if (redeemLocalList.length > 0) {
                RedeemModel[] updateRedeem = arrayDiffRedeem.getLocalDiff(redeemServerList, redeemLocalList);
                if (updateRedeem.length > 0) {
                    callAPI.pushRedeemService(updateRedeem);
                }
            }

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
