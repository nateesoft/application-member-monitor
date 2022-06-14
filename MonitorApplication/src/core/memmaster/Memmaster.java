package core.memmaster;

import api.connect.model.MemberModel;
import core.memmaster.model.MemmasterBean;
import database.connect.MySQLConnect;
import file.config.ConfigProps;
import file.config.FileConfigValue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import utils.DateUtil;
import utils.ThaiUtil;

/**
 *
 * @author nateesun
 */
public class Memmaster {

    private static final Logger LOGGER = Logger.getLogger(Memmaster.class);
    private static final ConfigProps config = FileConfigValue.loadConfig();

    private final String sqlUpdateMemmaster = "update memmaster set Member_TotalPurchase=?, Member_TotalScore=? where Member_Code=?;";

    public MemmasterBean mapping(ResultSet rs, MemmasterBean model) {
        LOGGER.debug("mapping");
        try {
            model.setMember_Code(rs.getString("Member_Code"));
            model.setMember_NameThai(rs.getString("Member_NameThai"));
            model.setMember_Email(rs.getString("Member_Email"));
            model.setMember_Brithday(rs.getDate("Member_Brithday"));
            model.setMember_ExpiredDate(rs.getDate("Member_ExpiredDate"));
            model.setMember_TotalPurchase(rs.getFloat("Member_TotalPurchase"));
            model.setMember_Mobile(rs.getString("Member_Mobile"));
            model.setMember_TotalScore(rs.getFloat("Member_TotalScore"));
            model.setMember_TitleNameThai(rs.getString("Member_TitleNameThai"));
            model.setMember_SurnameThai(rs.getString("Member_SurnameThai"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
        }
        return model;
    }

    public MemberModel mapping(ResultSet rs, MemberModel model) {
        LOGGER.debug("mapping");
        try {
            model.setCode(rs.getString("Member_Code"));
            model.setFirst_name(rs.getString("Member_NameThai"));
            model.setEmail(rs.getString("Member_Email"));
            model.setBirthday(DateUtil.getDateString(rs.getDate("Member_Brithday")));
            model.setExpired_date(DateUtil.getDateString(rs.getDate("Member_ExpiredDate")));
            model.setTotal_purchase(rs.getFloat("Member_TotalPurchase"));
            model.setMobile(rs.getString("Member_Mobile"));
            model.setTotal_score(rs.getFloat("Member_TotalScore"));
            model.setPrefix(rs.getString("Member_TitleNameThai"));
            model.setLast_name(rs.getString("Member_SurnameThai"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
        }
        return model;
    }

    public MemmasterBean findByMemberCode(String memberCode) {
        LOGGER.debug("findByMemberCode");

        MySQLConnect mysql = new MySQLConnect();
        try {
            String sql = "select Member_Code,Member_NameThai,Member_HomeTel,Member_Email,Member_Brithday,Member_ExpiredDate,"
                    + "Member_TotalPurchase,Member_Mobile,Member_TotalScore,Member_TitleNameThai,Member_SurnameThai,"
                    + "Member_CompanyTel "
                    + "from memmaster where Member_Code='" + memberCode + "' limit 1";
            try ( Connection conn = mysql.open("member");  PreparedStatement stmt = conn.prepareStatement(sql)) {
                try ( ResultSet rs = stmt.executeQuery(sql)) {
                    if (rs.next()) {
                        return mapping(rs, new MemmasterBean());
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
        } finally {
            mysql.close();
        }
        return null;
    }

    public List<MemmasterBean> findAll() {
        LOGGER.debug("findAll");
        List<MemmasterBean> listMembers = new ArrayList<>();
        MySQLConnect mysql = new MySQLConnect();
        try {
            String sql = "select Member_Code,Member_NameThai,Member_HomeTel,Member_Email,Member_Brithday,Member_ExpiredDate,\n"
                    + "Member_TotalPurchase,Member_Mobile,Member_TotalScore,Member_TitleNameThai,Member_SurnameThai,\n"
                    + "Member_CompanyTel from memmaster order by Member_Code";
            try ( Connection conn = mysql.open("member");  PreparedStatement stmt = conn.prepareStatement(sql)) {
                try ( ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        listMembers.add(mapping(rs, new MemmasterBean()));
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
        } finally {
            mysql.close();
        }
        return listMembers;
    }

    public List<MemberModel> findMemberAll() {
        LOGGER.debug("findMemberAll");
        List<MemberModel> listMembers = new ArrayList<>();
        MySQLConnect mysql = new MySQLConnect();
        try {
            String sql = "select Member_Code,Member_NameThai,Member_HomeTel,Member_Email,Member_Brithday,Member_ExpiredDate,"
                    + "Member_TotalPurchase,Member_Mobile,Member_TotalScore,Member_TitleNameThai,Member_SurnameThai,"
                    + "Member_CompanyTel from memmaster order by Member_Code";
            try ( Connection conn = mysql.open("member");  PreparedStatement stmt = conn.prepareStatement(sql)) {
                try ( ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        listMembers.add(mapping(rs, new MemberModel()));
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
        } finally {
            mysql.close();
        }
        return listMembers;
    }

    public List<MemberModel> findMemberFromBillno() {
        LOGGER.debug("findMemberFromBillno");
        List<MemberModel> listMembers = new ArrayList<>();
        MySQLConnect mysql = new MySQLConnect();
        try {
            String sql = "select m.Member_Code, m.Member_NameThai, m.Member_HomeTel,"
                    + "m.Member_Email, m.Member_Brithday, m.Member_ExpiredDate,"
                    + "m.Member_TotalPurchase, m.Member_Mobile, m.Member_TotalScore,"
                    + "m.Member_TitleNameThai, m.Member_SurnameThai "
                    + "from " + config.getPosDb() + ".billno b "
                    + "left join " + config.getPosMemberDb() + ".memmaster m on "
                    + "b.B_MemCode = m.Member_Code ";
            try ( Connection conn = mysql.open("member");  PreparedStatement stmt = conn.prepareStatement(sql)) {
                try ( ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        listMembers.add(mapping(rs, new MemberModel()));
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
        } finally {
            mysql.close();
        }
        return listMembers;
    }

    public boolean updatePoint(MemberModel model) {
        LOGGER.debug("Memmaster:update");
        MySQLConnect mysql = new MySQLConnect();
        boolean isUpdateData = false;
        try {
            try ( Connection conn = mysql.open("member")) {
                conn.setAutoCommit(false);
                try ( PreparedStatement prepStmt = conn.prepareStatement(sqlUpdateMemmaster)) {
                    prepStmt.setFloat(1, model.getTotal_purchase());
                    prepStmt.setFloat(2, model.getTotal_score());
                    prepStmt.setString(3, model.getCode());

                    prepStmt.addBatch();
                    int[] numUpdates = prepStmt.executeBatch();
                    for (int i = 0; i < numUpdates.length; i++) {
                        if (numUpdates[i] == -2) {
                            LOGGER.debug("Execution " + i + ": unknown number of rows updated");
                        } else {
                            isUpdateData = true;
                            LOGGER.debug("Execution " + i + "successful: " + numUpdates[i] + " rows updated");
                        }
                    }
                }
                conn.commit();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
        } finally {
            mysql.close();
        }

        return isUpdateData;
    }

    public void update(MemberModel[] listMember) {
        LOGGER.debug("Memmaster:updateList => size(" + listMember.length + ")");
        if (listMember.length == 0) {
            LOGGER.debug("not found member to update local db");
            return;
        }
        MySQLConnect mysql = new MySQLConnect();

        try {
            try ( Connection conn = mysql.open("member")) {
                conn.setAutoCommit(false);
                try ( PreparedStatement prepStmt = conn.prepareStatement(sqlUpdateMemmaster)) {
                    for (MemberModel model : listMember) {
                        if (model.getSaveOrUpdate().equals("update")) {
                            prepStmt.setFloat(1, model.getTotal_purchase());
                            prepStmt.setFloat(2, model.getTotal_score());
                            prepStmt.setString(3, model.getCode());

                            prepStmt.addBatch();
                        }
                    }
                    int[] numUpdates = prepStmt.executeBatch();
                    for (int i = 0; i < numUpdates.length; i++) {
                        if (numUpdates[i] == -2) {
                            LOGGER.debug("Execution " + i + ": unknown number of rows updated");
                        } else {
                            LOGGER.debug("Execution " + i + "successful: " + numUpdates[i] + " rows updated");
                        }
                    }
                }
                conn.commit();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
        } finally {
            mysql.close();
        }
    }

    public boolean saveOrUpdateList(MemberModel[] listMember) {
        boolean isUpdate = false;
        LOGGER.debug("Memmaster:saveList");
        if (listMember.length == 0) {
            LOGGER.debug("not found member to update local db");
            return isUpdate;
        }
        MySQLConnect mysql = new MySQLConnect();
        try {
            try ( Connection conn = mysql.open("member")) {
                conn.setAutoCommit(false);
                String sqlInsertMemmaster = "insert into memmaster("
                        + "Member_Code, Member_NameThai, Member_HomeTel, Member_Email, Member_Brithday,"
                        + "Member_ExpiredDate, Member_TotalPurchase, Member_Mobile, Member_TotalScore, Member_TitleNameThai,"
                        + "Member_SurnameThai, Member_CompanyTel, System_Created, System_Updated, Member_AppliedDate, "
                        + "Member_LastDateService, Member_PointExpiredDate, Employee_CreateDate, Employee_ModifyDate, Member_Company, "
                        + "Member_AddressNo, Member_Building, Member_AddressSoi, Member_AddressStreet, Member_AddressSubDistrict, "
                        + "Member_AddressDistrict, Member_Province, Member_PostalCode, Member_Fax, Member_SpouseName, "
                        + "Member_Food, Member_Remark1, Member_Remark2, Member_HobbySetCode, Member_CompanyAddressNo, "
                        + "Member_CompanyBuilding, Member_CompanySoi, Member_CompanySubDistrict, Member_CompanyDistrict, Member_CompanyProvince, "
                        + "Member_CompanyPostalCode, Member_CompanyFax,Member_CompanyStreet) "
                        + "values ("
                        + "?,?,?,?,?,"
                        + "?,?,?,?,?,"
                        + "?,?,now(),now(),now(),"
                        + "now(),?,now(),now(),?,"
                        + "?,?,?,?,?,"
                        + "?,?,?,?,?,"
                        + "?,?,?,?,?,"
                        + "?,?,?,?,?,"
                        + "?,?,?) on duplicate key update Member_TotalPurchase=?, Member_TotalScore=?;";
                try ( PreparedStatement prepStmt = conn.prepareStatement(sqlInsertMemmaster)) {
                    for (MemberModel model : listMember) {
                        prepStmt.setString(1, model.getCode());//Member_Code
                        prepStmt.setString(2, ThaiUtil.encodeThaiAscii(model.getFirst_name()));//Member_NameThai
                        prepStmt.setString(3, model.getMobile());//Member_HomeTel
                        prepStmt.setString(4, model.getEmail());//Member_Email
                        prepStmt.setDate(5, DateUtil.getDate(model.getBirthday()));//Member_Brithday
                        prepStmt.setDate(6, DateUtil.getDate(model.getExpired_date()));//Member_ExpiredDate
                        prepStmt.setFloat(7, model.getTotal_purchase());//Member_TotalPurchase
                        prepStmt.setString(8, model.getMobile());//Member_Mobile
                        prepStmt.setFloat(9, model.getTotal_score());//Member_TotalScore
                        prepStmt.setString(10, ThaiUtil.encodeThaiAscii(model.getPrefix()));//Member_TitleNameThai
                        prepStmt.setString(11, ThaiUtil.encodeThaiAscii(model.getLast_name()));//Member_SurnameThai
                        prepStmt.setString(12, model.getMobile());//Member_CompanyTel
                        prepStmt.setDate(13, DateUtil.getDate(model.getPoint_expired_date()));//Member_PointExpiredDate
                        prepStmt.setString(14, model.getCompany_code());//Member_Company
                        prepStmt.setString(15, "");//Member_AddressNo
                        prepStmt.setString(16, "");//Member_Building
                        prepStmt.setString(17, "");//Member_AddressSoi
                        prepStmt.setString(18, "");//Member_AddressStreet
                        prepStmt.setString(19, "");//Member_AddressSubDistrict
                        prepStmt.setString(20, "");//Member_AddressDistrict
                        prepStmt.setString(21, "");//Member_Province
                        prepStmt.setString(22, "");//Member_PostalCode
                        prepStmt.setString(23, "");//Member_Fax
                        prepStmt.setString(24, "");//Member_SpouseName
                        prepStmt.setString(25, "");//Member_Food
                        prepStmt.setString(26, "");//Member_Remark1
                        prepStmt.setString(27, "");//Member_Remark2
                        prepStmt.setString(28, "");//Member_HobbySetCode
                        prepStmt.setString(29, "");//Member_CompanyAddressNo
                        prepStmt.setString(30, "");//Member_CompanyBuilding
                        prepStmt.setString(31, "");//Member_CompanySoi
                        prepStmt.setString(32, "");//Member_CompanySubDistrict
                        prepStmt.setString(33, "");//Member_CompanyDistrict
                        prepStmt.setString(34, "");//Member_CompanyProvince
                        prepStmt.setString(35, "");//Member_CompanyPostalCode
                        prepStmt.setString(36, "");//Member_CompanyFax
                        prepStmt.setString(37, "");//Member_CompanyStreet
                        prepStmt.setFloat(38, model.getTotal_purchase());//Member_TotalPurchase
                        prepStmt.setFloat(39, model.getTotal_score());//Member_TotalScore

                        prepStmt.addBatch();
                    }
                    int[] numUpdates = prepStmt.executeBatch();
                    for (int i = 0; i < numUpdates.length; i++) {
                        if (numUpdates[i] == -2) {
                            LOGGER.debug("Execution " + i + ": unknown number of rows updated");
                        } else {
                            isUpdate = true;
                            LOGGER.debug("Execution " + i + "successful: " + numUpdates[i] + " rows updated");
                        }
                    }
                }
                conn.commit();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            LOGGER.error(e.getMessage());
        } finally {
            mysql.close();
        }
        
        return isUpdate;
    }

}
