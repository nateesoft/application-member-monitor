package database.local;

import api.MemberModel;
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

    public RedeemModel[] getRedeem() {
        LOGGER.debug("getRedeem");
        List<RedeemModel> listRedeemModel = redeem.findAll();
        RedeemModel[] listRedeems = listRedeemModel.toArray(new RedeemModel[listRedeemModel.size()]);
        return listRedeems;
    }

    public void saveUpdateMember(MemberModel[] listMember) {
        LOGGER.debug("saveUpdateMember");
        memmaster.save(listMember);
        memmaster.update(listMember);
    }

    public void saveUpdateRedeem(RedeemModel[] listRedeem) {
        LOGGER.debug("saveUpdateRedeem");
        redeem.save(listRedeem);
        redeem.update(listRedeem);
    }
}
