package database.local;

import api.MemberModel;
import api.ReqMemberBody;
import api.ReqRedeemBody;
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

    public MemberModel[] getMember() {
        LOGGER.debug("getMember");
        List<MemberModel> listMemberModel = memmaster.findMemberAll();
        MemberModel[] listMembers = listMemberModel.toArray(new MemberModel[listMemberModel.size()]);
        return listMembers;
    }

    public ReqMemberBody[] getMemberReqBody() {
        LOGGER.debug("getMember");
        List<MemberModel> listMemberModel = memmaster.findMemberAll();
        List<ReqMemberBody> listReq = new ArrayList<>();
        for (MemberModel model : listMemberModel) {
            listReq.add(new ReqMemberBody(model.getTotal_purchase(), model.getTotal_score(), model.getCode()));
        }
        ReqMemberBody[] listMembers = listReq.toArray(new ReqMemberBody[listReq.size()]);
        return listMembers;
    }

    public RedeemModel[] getRedeem() {
        LOGGER.debug("getRedeem");
        List<RedeemModel> listRedeemModel = redeem.findAll();
        RedeemModel[] listRedeems = listRedeemModel.toArray(new RedeemModel[listRedeemModel.size()]);
        return listRedeems;
    }

    public ReqRedeemBody[] getRedeemReqBody() {
        LOGGER.debug("getRedeem");
        List<RedeemModel> listRedeemModel = redeem.findAll();
        List<ReqRedeemBody> listReq = new ArrayList<>();
        for (RedeemModel model : listRedeemModel) {
            listReq.add(new ReqRedeemBody(
                    model.getProduct_code(),
                    model.getBill_no(),
                    model.getUse_in_branch(),
                    model.getEmp_code_redeem(),
                    model.getActive(),
                    model.getRedeem_code()
            ));
        }
        ReqRedeemBody[] listRedeems = listReq.toArray(new ReqRedeemBody[listReq.size()]);
        return listRedeems;
    }

    public void saveUpdateMember(MemberModel[] listMember) {
        LOGGER.debug("saveUpdateMember");
        memmaster.save(listMember);
//        memmaster.update(listMember);
    }

    public void saveUpdateRedeem(RedeemModel[] listRedeem) {
        LOGGER.debug("saveUpdateRedeem");
        redeem.save(listRedeem);
//        redeem.update(listRedeem);
    }
}
