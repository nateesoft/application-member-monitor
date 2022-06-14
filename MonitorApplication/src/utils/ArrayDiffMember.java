/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import api.connect.model.MemberModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nateesun
 */
public class ArrayDiffMember implements ArrayDiff<MemberModel> {

    public boolean isNullOrEmpty(MemberModel[] listMember) {
        int count = 0;
        for (MemberModel m : listMember) {
            if (m.getCode() != null) {
                count++;
            }
        }
        return listMember == null || listMember.length == 0 || count == 0;
    }

    public boolean isNotNull(MemberModel[] listMember) {
        return !isNullOrEmpty(listMember);
    }

    public boolean isMemberNotNull(MemberModel member) {
        return member != null && member.getCode() != null;
    }

    public boolean isNullOrEmpty(MemberModel member) {
        return member == null || member.getCode() == null;
    }

    @Override
    public MemberModel[] diffInsertUpdate(MemberModel[] memberServerList, MemberModel[] memberLocalList) {
        List<MemberModel> listMemberDiff = new ArrayList<>();
        for (MemberModel memberServer : memberServerList) {
            String status = foundStatus(memberServer, memberLocalList);
            switch (status) {
                case "save":
                    memberServer.setSaveOrUpdate("save");
                    listMemberDiff.add(memberServer);
                    break;
                case "update":
                    memberServer.setSaveOrUpdate("update");
                    listMemberDiff.add(memberServer);
                    break;
            }
        }
        return listMemberDiff.toArray(new MemberModel[0]);
    }

    @Override
    public String foundStatus(MemberModel memberServer, MemberModel[] memberLocalList) {
        if (memberServer == null) {
            memberServer = new MemberModel();
            memberServer.setCode("");
            memberServer.setTotal_purchase(0f);
            memberServer.setTotal_score(0f);
        }
        for (MemberModel model : memberLocalList) {
            if (model == null) {
                model = new MemberModel();
                model.setCode("");
                model.setTotal_purchase(0f);
                model.setTotal_score(0f);
            }
            if (memberServer.getCode().equals(model.getCode())) {
                if (Float.compare(memberServer.getTotal_purchase(), model.getTotal_purchase()) != 0) {
                    return "update";
                }
                if (Float.compare(memberServer.getTotal_score(), model.getTotal_score()) != 0) {
                    return "update";
                }
            }
        }
        return "";
    }

    @Override
    public MemberModel[] getLocalDiff(MemberModel[] apiMember, MemberModel[] memberLocalList) {
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
        return listMemberDiff.toArray(new MemberModel[0]);
    }

    private boolean compareTrue(MemberModel memberServer, MemberModel model) {
        if (memberServer != null && model != null) {
            if (memberServer.getCode() != null && model.getCode() != null) {
                return memberServer.getCode().equals(model.getCode());
            }
        }

        return false;
    }

}
