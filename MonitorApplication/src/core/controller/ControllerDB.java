package core.controller;

import api.connect.model.MemberModel;
import api.connect.model.ReqMemberBody;
import api.connect.model.ReqRedeemBody;
import core.memmaster.Memmaster;
import core.redeem.Redeem;
import core.redeem.model.RedeemModel;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author nateesun
 */
public class ControllerDB {

    private static final Logger LOGGER = Logger.getLogger(ControllerDB.class);
    private final Memmaster memmaster = new Memmaster();
    private final Redeem redeem = new Redeem();

    public MemberModel[] getMemberFromBillno() {
        LOGGER.debug("ControllerDB:getMemberFromBillno");
        List<MemberModel> listMemberModel = memmaster.findMemberFromBillno();
        
        return listMemberModel.toArray(new MemberModel[0]);
    }
    
    public MemberModel[] getMember() {
        LOGGER.debug("ControllerDB:getMember");
        List<MemberModel> listMemberModel = memmaster.findMemberAll();
        
        return listMemberModel.toArray(new MemberModel[0]);
    }

    public ReqMemberBody[] getMemberReqBody(MemberModel[] insertMember) {
        LOGGER.debug("ControllerDB:getMemberReqBody");
        List<ReqMemberBody> listReq = new ArrayList<>();
        for (MemberModel model : insertMember) {
            listReq.add(new ReqMemberBody(model.getTotal_purchase(), model.getTotal_score(), model.getCode()));
        }
        
        return listReq.toArray(new ReqMemberBody[0]);
    }

    public RedeemModel[] getRedeem() {
        LOGGER.debug("ControllerDB:getRedeem");
        List<RedeemModel> listRedeemModel = redeem.findAll();
        
        return listRedeemModel.toArray(new RedeemModel[0]);
    }

    public ReqRedeemBody[] getRedeemReqBody(RedeemModel[] insertRedeem) {
        LOGGER.debug("ControllerDB:getRedeemReqBody");
        List<ReqRedeemBody> listReq = new ArrayList<>();
        for (RedeemModel model : insertRedeem) {
            listReq.add(new ReqRedeemBody(
                model.getProduct_code(),
                model.getBill_no(),
                model.getUse_in_branch(),
                model.getEmp_code_redeem(),
                model.getActive(),
                model.getRedeem_code()
            ));
        }
        
        return listReq.toArray(new ReqRedeemBody[0]);
    }

    public void saveMemberList(MemberModel[] listMember) {
        LOGGER.debug("ControllerDB:saveMemberList");
        memmaster.saveOrUpdateList(listMember);
    }

    public void saveRedeemList(RedeemModel[] listRedeem) {
        LOGGER.debug("ControllerDB:saveRedeemList");
        redeem.saveOrUpdateList(listRedeem);
    }
}
