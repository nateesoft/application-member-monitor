package utils;

import api.MemberModel;
import database.local.RedeemModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nateesun
 */
public class ArrayDiff {

    public static MemberModel[] diffInsertUpdate(MemberModel[] arrA, MemberModel[] arrB) {
        List<MemberModel> listMemberDiff = new ArrayList<>();
        for (MemberModel m1 : arrA) {
            String status = foundStatus(m1, arrB);
            switch (status) {
                case "save":
                    m1.setSaveOrUpdate("save");
                    listMemberDiff.add(m1);
                    break;
                case "update":
                    m1.setSaveOrUpdate("update");
                    listMemberDiff.add(m1);
                    break;
            }
        }
        return listMemberDiff.toArray(new MemberModel[listMemberDiff.size()]);
    }

    public static RedeemModel[] diffInsertUpdate(RedeemModel[] arrA, RedeemModel[] arrB) {
        List<RedeemModel> listMemberDiff = new ArrayList<>();
        for (RedeemModel m1 : arrA) {
            String status = foundStatus(m1, arrB);
            switch (status) {
                case "save":
                    m1.setSaveOrUpdate("save");
                    listMemberDiff.add(m1);
                    break;
                case "update":
                    m1.setSaveOrUpdate("update");
                    listMemberDiff.add(m1);
                    break;
            }
        }
        return listMemberDiff.toArray(new RedeemModel[listMemberDiff.size()]);
    }

    private static String foundStatus(MemberModel m1, MemberModel[] arrB) {
        if (arrB.length == 0) {
            return "save";
        }
        boolean found = false;
        for (MemberModel model : arrB) {
            if (model.getCode().equals(m1.getCode())) {
                found = true;
                if (!model.getTotal_score().equals(m1.getTotal_score()) || !model.getTotal_purchase().equals(m1.getTotal_purchase())) {
                    return "update";
                }
            }
        }
        if (!found) {
            return "save";
        }
        return "";
    }

    private static String foundStatus(RedeemModel m1, RedeemModel[] arrB) {
        if (arrB.length == 0) {
            return "save";
        }
        boolean found = false;
        for (RedeemModel model : arrB) {
            if (model.getRedeem_code().equals(m1.getRedeem_code())) {
                found = true;
                if (!model.getBill_no().equals(m1.getBill_no())) {
                    return "update";
                }
            }
        }
        if (!found) {
            return "save";
        }
        return "";
    }

    public static MemberModel[] getLocalDiff(MemberModel[] diffMember, MemberModel[] memberLocalList) {
        List<MemberModel> listMemberDiff = new ArrayList<>();
        for (MemberModel diff : diffMember) {
            for (MemberModel local : memberLocalList) {
                if (diff.getCode().equals(local.getCode())) {
                    listMemberDiff.add(local);
                    break;
                }
            }
        }
        return listMemberDiff.toArray(new MemberModel[listMemberDiff.size()]);
    }

    public static RedeemModel[] getLocalDiff(RedeemModel[] diffRedeem, RedeemModel[] redeemLocalList) {
        List<RedeemModel> listRedeemDiff = new ArrayList<>();
        for (RedeemModel diff : diffRedeem) {
            for (RedeemModel local : redeemLocalList) {
                if (diff.getRedeem_code().equals(local.getRedeem_code())) {
                    listRedeemDiff.add(local);
                    break;
                }
            }
        }
        return listRedeemDiff.toArray(new RedeemModel[listRedeemDiff.size()]);
    }
}
