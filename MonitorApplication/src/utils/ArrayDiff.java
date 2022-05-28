package utils;

import api.model.MemberModel;
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
                boolean isScore = model.getTotal_score().equals(m1.getTotal_score());
                boolean isPurchase = model.getTotal_purchase().equals(m1.getTotal_purchase());
                if (!isScore || !isPurchase) {
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
                if (model.getBill_no() != null && !model.getBill_no().equals(m1.getBill_no())) {
                    return "update";
                }
            }
        }
        if (!found) {
            return "save";
        }
        return "";
    }

    public static MemberModel[] getLocalDiff(MemberModel[] apiMember, MemberModel[] memberLocalList) {
        List<MemberModel> listMemberDiff = new ArrayList<>();
        for (MemberModel api : apiMember) {
            for (MemberModel local : memberLocalList) {
                if (api.getCode().equals(local.getCode())) {
                    boolean isScore = api.getTotal_score().equals(local.getTotal_score());
                    boolean isPurchase = api.getTotal_purchase().equals(local.getTotal_purchase());
                    if (!isScore || !isPurchase) {
                        listMemberDiff.add(local);
                    }
                    break;
                }
            }
        }
        return listMemberDiff.toArray(new MemberModel[listMemberDiff.size()]);
    }

    public static RedeemModel[] getLocalDiff(RedeemModel[] apiRedeem, RedeemModel[] redeemLocalList) {
        List<RedeemModel> listRedeemDiff = new ArrayList<>();
        for (RedeemModel api : apiRedeem) {
            for (RedeemModel local : redeemLocalList) {
                if (api.getRedeem_code().equals(local.getRedeem_code())) {
                    boolean isBillNo = false;
                    if (api.getBill_no() != null && local.getBill_no() != null) {
                        if (api.getBill_no().equals(local.getBill_no())) {
                            isBillNo = true;
                        }
                    }
                    if (api.getBill_no() == null && local.getBill_no() == null) {
                        isBillNo = true;
                    }
                    if (!isBillNo) {
                        listRedeemDiff.add(local);
                    }
                    break;
                }
            }
        }
        return listRedeemDiff.toArray(new RedeemModel[listRedeemDiff.size()]);
    }
}
