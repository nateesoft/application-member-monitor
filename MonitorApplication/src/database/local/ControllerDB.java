package database.local;

import api.MemberModel;
import java.util.List;

/**
 *
 * @author nateesun
 */
public class ControllerDB {

    private final Memmaster memmaster = new Memmaster();
    private final Redeem redeem = new Redeem();

    public MemberModel[] getMember() {
        List<MemberModel> listMemberModel = memmaster.findMemberAll();
        MemberModel[] listMembers = listMemberModel.toArray(new MemberModel[listMemberModel.size()]);
        return listMembers;
    }

    public RedeemModel[] getRedeem() {
        List<RedeemModel> listRedeemModel = redeem.findAll();
        RedeemModel[] listRedeems = listRedeemModel.toArray(new RedeemModel[listRedeemModel.size()]);
        return listRedeems;
    }

    public void saveUpdateMember(MemberModel[] listMember) {
        memmaster.save(listMember);
        memmaster.update(listMember);
    }

    public void saveUpdateRedeem(RedeemModel[] listRedeem) {
        redeem.save(listRedeem);
        redeem.update(listRedeem);
    }
}
