/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import core.redeem.model.RedeemModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nateesun
 */
public class ArrayDiffRedeem implements ArrayDiff<RedeemModel> {

    @Override
    public RedeemModel[] diffInsertUpdate(RedeemModel[] arrA, RedeemModel[] arrB) {
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
        return listMemberDiff.toArray(new RedeemModel[0]);
    }

    @Override
    public String foundStatus(RedeemModel m1, RedeemModel[] arrB) {
        if (arrB.length == 0) {
            return "save";
        }
        if (m1 == null) {
            return "";
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

    @Override
    public RedeemModel[] getLocalDiff(RedeemModel[] apiRedeem, RedeemModel[] redeemLocalList) {
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

        return listRedeemDiff.toArray(new RedeemModel[0]);
    }

}
