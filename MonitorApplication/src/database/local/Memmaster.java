package database.local;

import database.MySQLMemberConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author nateesun
 */
interface MemmasterInterface {

    public MemmasterModel findByMemberCode(String memberCode);
    public ArrayList<MemmasterModel> findAll();
//    public boolean syncData();
//    public void bulkInsert();
//    public void bulkInsertTemp();
//    public void getQuery();
//    public void create();
//    public void createTemp();
//    public void update();
//    public void updateMemberPoint();
//    public void deleteTemp();
}

public class Memmaster implements MemmasterInterface {

    public MemmasterModel mapping(ResultSet rs, MemmasterModel model) {
        try {
            model.setMember_Code(rs.getString("Member_Code"));
            model.setMember_TypeCode(rs.getString("Member_TypeCode"));
            model.setMember_BranchCode(rs.getString("Member_BranchCode"));
            model.setMember_NameThai(rs.getString("Member_NameThai"));
            model.setMember_NameEng(rs.getString("Member_NameEng"));
            model.setMember_Gender(rs.getString("Member_Gender"));
            model.setMember_Status(rs.getString("Member_Status"));
            model.setMember_NationCode(rs.getString("Member_NationCode"));
            model.setMember_OccupationCode(rs.getString("Member_OccupationCode"));
            model.setMember_IncomeCode(rs.getString("Member_IncomeCode"));
            model.setMember_EducationCode(rs.getString("Member_EducationCode"));
            model.setMember_Company(rs.getString("Member_Company"));
            model.setMember_AddressNo(rs.getString("Member_AddressNo"));
            model.setMember_Building(rs.getString("Member_Building"));
            model.setMember_AddressSoi(rs.getString("Member_AddressSoi"));
            model.setMember_AddressStreet(rs.getString("Member_AddressStreet"));
            model.setMember_AddressSubDistrict(rs.getString("Member_AddressSubDistrict"));
            model.setMember_AddressDistrict(rs.getString("Member_AddressDistrict"));
            model.setMember_Province(rs.getString("Member_Province"));
            model.setMember_PostalCode(rs.getString("Member_PostalCode"));
            model.setMember_HomeTel(rs.getString("Member_HomeTel"));
            model.setMember_Fax(rs.getString("Member_Fax"));
            model.setMember_Email(rs.getString("Member_Email"));
            model.setMember_Brithday(rs.getDate("Member_Brithday"));
            model.setMember_AppliedDate(rs.getDate("Member_AppliedDate"));
            model.setMember_ExpiredDate(rs.getDate("Member_ExpiredDate"));
            model.setMember_DiscountRate(rs.getString("Member_DiscountRate"));
            model.setMember_SpouseName(rs.getString("Member_SpouseName"));
            model.setMember_Food(rs.getString("Member_Food"));
            model.setMember_TotalPurchase(rs.getFloat("Member_TotalPurchase"));
            model.setMember_Remark1(rs.getString("Member_Remark1"));
            model.setMember_Remark2(rs.getString("Member_Remark2"));
            model.setMember_Mobile(rs.getString("Member_Mobile"));
            model.setMember_ReceiveInformation(rs.getString("Member_ReceiveInformation"));
            model.setMember_HobbySetCode(rs.getString("Member_HobbySetCode"));
            model.setMember_LastDateService(rs.getDate("Member_LastDateService"));
            model.setMember_ServiceCount(rs.getFloat("Member_ServiceCount"));
            model.setMember_PointExpiredDate(rs.getDate("Member_PointExpiredDate"));
            model.setMember_TotalScore(rs.getFloat("Member_TotalScore"));
            model.setMember_TitleNameThai(rs.getString("Member_TitleNameThai"));
            model.setMember_SurnameThai(rs.getString("Member_SurnameThai"));
            model.setMember_CompanyAddressNo(rs.getString("Member_CompanyAddressNo"));
            model.setMember_CompanyBuilding(rs.getString("Member_CompanyBuilding"));
            model.setMember_CompanySoi(rs.getString("Member_CompanySoi"));
            model.setMember_CompanyStreet(rs.getString("Member_CompanyStreet"));
            model.setMember_CompanySubDistrict(rs.getString("Member_CompanySubDistrict"));
            model.setMember_CompanyDistrict(rs.getString("Member_CompanyDistrict"));
            model.setMember_CompanyProvince(rs.getString("Member_CompanyProvince"));
            model.setMember_CompanyPostalCode(rs.getString("Member_CompanyPostalCode"));
            model.setMember_CompanyTel(rs.getString("Member_CompanyTel"));
            model.setMember_CompanyFax(rs.getString("Member_CompanyFax"));
            model.setMember_Active(rs.getString("Member_Active"));
            model.setMember_UsedTitle(rs.getString("Member_UsedTitle"));
            model.setMember_MailTo(rs.getString("Member_MailTo"));
            model.setMember_PrintLabel(rs.getString("Member_PrintLabel"));
            model.setEmployee_CodeCreate(rs.getString("Employee_CodeCreate"));
            model.setEmployee_CreateDate(rs.getDate("Employee_CreateDate"));
            model.setEmployee_CreateTime(rs.getString("Employee_CreateTime"));
            model.setEmployee_CodeModify(rs.getString("Employee_CodeModify"));
            model.setEmployee_ModifyDate(rs.getDate("Employee_ModifyDate"));
            model.setEmployee_ModifyTime(rs.getString("Employee_ModifyTime"));
            model.setMember_TranferFlag(rs.getString("Member_TranferFlag"));
            model.setMember_UnknowBirth(rs.getString("Member_UnknowBirth"));
            model.setMember_PriceNO(rs.getString("Member_PriceNO"));
            model.setSystem_Created(rs.getDate("System_Created"));
            model.setSystem_Updated(rs.getDate("System_Updated"));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return model;
    }
    
    public MemmasterModel mapping(JSONObject rs, MemmasterModel model) {
        try {
            model.setMember_Code(rs.getString("Member_Code"));
            model.setMember_TypeCode(rs.getString("Member_TypeCode"));
            model.setMember_BranchCode(rs.getString("Member_BranchCode"));
            model.setMember_NameThai(rs.getString("Member_NameThai"));
            model.setMember_NameEng(rs.getString("Member_NameEng"));
            model.setMember_Gender(rs.getString("Member_Gender"));
            model.setMember_Status(rs.getString("Member_Status"));
            model.setMember_NationCode(rs.getString("Member_NationCode"));
            model.setMember_OccupationCode(rs.getString("Member_OccupationCode"));
            model.setMember_IncomeCode(rs.getString("Member_IncomeCode"));
            model.setMember_EducationCode(rs.getString("Member_EducationCode"));
            model.setMember_Company(rs.getString("Member_Company"));
            model.setMember_AddressNo(rs.getString("Member_AddressNo"));
            model.setMember_Building(rs.getString("Member_Building"));
            model.setMember_AddressSoi(rs.getString("Member_AddressSoi"));
            model.setMember_AddressStreet(rs.getString("Member_AddressStreet"));
            model.setMember_AddressSubDistrict(rs.getString("Member_AddressSubDistrict"));
            model.setMember_AddressDistrict(rs.getString("Member_AddressDistrict"));
            model.setMember_Province(rs.getString("Member_Province"));
            model.setMember_PostalCode(rs.getString("Member_PostalCode"));
            model.setMember_HomeTel(rs.getString("Member_HomeTel"));
            model.setMember_Fax(rs.getString("Member_Fax"));
            model.setMember_Email(rs.getString("Member_Email"));
//            model.setMember_Brithday(rs.getDate("Member_Brithday"));
//            model.setMember_AppliedDate(rs.getDate("Member_AppliedDate"));
//            model.setMember_ExpiredDate(rs.getDate("Member_ExpiredDate"));
            model.setMember_DiscountRate(rs.getString("Member_DiscountRate"));
            model.setMember_SpouseName(rs.getString("Member_SpouseName"));
            model.setMember_Food(rs.getString("Member_Food"));
            model.setMember_TotalPurchase(rs.getFloat("Member_TotalPurchase"));
            model.setMember_Remark1(rs.getString("Member_Remark1"));
            model.setMember_Remark2(rs.getString("Member_Remark2"));
            model.setMember_Mobile(rs.getString("Member_Mobile"));
            model.setMember_ReceiveInformation(rs.getString("Member_ReceiveInformation"));
            model.setMember_HobbySetCode(rs.getString("Member_HobbySetCode"));
//            model.setMember_LastDateService(rs.getDate("Member_LastDateService"));
            model.setMember_ServiceCount(rs.getFloat("Member_ServiceCount"));
//            model.setMember_PointExpiredDate(rs.getDate("Member_PointExpiredDate"));
            model.setMember_TotalScore(rs.getFloat("Member_TotalScore"));
            model.setMember_TitleNameThai(rs.getString("Member_TitleNameThai"));
            model.setMember_SurnameThai(rs.getString("Member_SurnameThai"));
            model.setMember_CompanyAddressNo(rs.getString("Member_CompanyAddressNo"));
            model.setMember_CompanyBuilding(rs.getString("Member_CompanyBuilding"));
            model.setMember_CompanySoi(rs.getString("Member_CompanySoi"));
            model.setMember_CompanyStreet(rs.getString("Member_CompanyStreet"));
            model.setMember_CompanySubDistrict(rs.getString("Member_CompanySubDistrict"));
            model.setMember_CompanyDistrict(rs.getString("Member_CompanyDistrict"));
            model.setMember_CompanyProvince(rs.getString("Member_CompanyProvince"));
            model.setMember_CompanyPostalCode(rs.getString("Member_CompanyPostalCode"));
            model.setMember_CompanyTel(rs.getString("Member_CompanyTel"));
            model.setMember_CompanyFax(rs.getString("Member_CompanyFax"));
            model.setMember_Active(rs.getString("Member_Active"));
            model.setMember_UsedTitle(rs.getString("Member_UsedTitle"));
            model.setMember_MailTo(rs.getString("Member_MailTo"));
            model.setMember_PrintLabel(rs.getString("Member_PrintLabel"));
            model.setEmployee_CodeCreate(rs.getString("Employee_CodeCreate"));
//            model.setEmployee_CreateDate(rs.getDate("Employee_CreateDate"));
            model.setEmployee_CreateTime(rs.getString("Employee_CreateTime"));
            model.setEmployee_CodeModify(rs.getString("Employee_CodeModify"));
//            model.setEmployee_ModifyDate(rs.getDate("Employee_ModifyDate"));
            model.setEmployee_ModifyTime(rs.getString("Employee_ModifyTime"));
            model.setMember_TranferFlag(rs.getString("Member_TranferFlag"));
            model.setMember_UnknowBirth(rs.getString("Member_UnknowBirth"));
            model.setMember_PriceNO(rs.getString("Member_PriceNO"));
//            model.setSystem_Created(rs.getDate("System_Created"));
//            model.setSystem_Updated(rs.getDate("System_Updated"));
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
        return model;
    }

    @Override
    public MemmasterModel findByMemberCode(String memberCode) {
        try {
            String sql = "select * from memmaster where Member_Code='" + memberCode + "'";
            MySQLMemberConnect mysql = new MySQLMemberConnect();
            try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    if (rs.next()) {
                        return mapping(rs, new MemmasterModel());
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<MemmasterModel> findAll() {
        ArrayList<MemmasterModel> listMembers = new ArrayList<>();
        try {
            String sql = "select * from memmaster order by Member_Code";
            MySQLMemberConnect mysql = new MySQLMemberConnect();
            try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    if (rs.next()) {
                        MemmasterModel model = mapping(rs, new MemmasterModel());
                        listMembers.add(model);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return listMembers;
    }
}
