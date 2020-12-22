package database.local;

import api.MemberModel;
import database.MySQLMemberConnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import utils.DateUtil;
import utils.ThaiUtil;

/**
 *
 * @author nateesun
 */
interface MemmasterInterface {

    public MemmasterModel findByMemberCode(String memberCode);

    public List<MemmasterModel> findAll();

    public List<MemberModel> findMemberAll();
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

    private static final Logger LOGGER = Logger.getLogger(Memmaster.class);

    public MemmasterModel mapping(ResultSet rs, MemmasterModel model) {
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
            model.setMember_Active(rs.getString("Member_Active"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return model;
    }

    public MemberModel mapping(ResultSet rs, MemberModel model) {
        LOGGER.debug("mapping");
        try {
            model.setCode(rs.getString("Member_Code"));
            model.setFirst_name(ThaiUtil.ASCII2Unicode(rs.getString("Member_NameThai")));
            model.setEmail(rs.getString("Member_Email"));
            model.setBirthday(DateUtil.getDateString(rs.getDate("Member_Brithday")));
            model.setExpired_date(DateUtil.getDateString(rs.getDate("Member_ExpiredDate")));
            model.setTotal_purchase(rs.getFloat("Member_TotalPurchase"));
            model.setMobile(rs.getString("Member_Mobile"));
            model.setTotal_score(rs.getFloat("Member_TotalScore"));
            model.setPrefix(ThaiUtil.ASCII2Unicode(rs.getString("Member_TitleNameThai")));
            model.setLast_name(ThaiUtil.ASCII2Unicode(rs.getString("Member_SurnameThai")));
            model.setActive(rs.getString("Member_Active"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return model;
    }

    @Override
    public MemmasterModel findByMemberCode(String memberCode) {
        LOGGER.debug("findByMemberCode");
        try {
            String sql = "select Member_Code,Member_NameThai,Member_HomeTel,Member_Email,Member_Brithday,Member_ExpiredDate,\n"
                    + "Member_TotalPurchase,Member_Mobile,Member_TotalScore,Member_TitleNameThai,Member_SurnameThai,\n"
                    + "Member_CompanyTel,Member_Active "
                    + "from memmaster where Member_Code='" + memberCode + "'";
            MySQLMemberConnect mysql = new MySQLMemberConnect();
            try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    if (rs.next()) {
                        return mapping(rs, new MemmasterModel());
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<MemmasterModel> findAll() {
        LOGGER.debug("findAll");
        List<MemmasterModel> listMembers = new ArrayList<>();
        try {
            String sql = "select Member_Code,Member_NameThai,Member_HomeTel,Member_Email,Member_Brithday,Member_ExpiredDate,\n"
                    + "Member_TotalPurchase,Member_Mobile,Member_TotalScore,Member_TitleNameThai,Member_SurnameThai,\n"
                    + "Member_CompanyTel,Member_Active "
                    + "from memmaster order by Member_Code";
            MySQLMemberConnect mysql = new MySQLMemberConnect();
            try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        listMembers.add(mapping(rs, new MemmasterModel()));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return listMembers;
    }

    @Override
    public List<MemberModel> findMemberAll() {
        LOGGER.debug("findMemberAll");
        List<MemberModel> listMembers = new ArrayList<>();
        try {
            String sql = "select Member_Code,Member_NameThai,Member_HomeTel,Member_Email,Member_Brithday,Member_ExpiredDate,"
                    + "Member_TotalPurchase,Member_Mobile,Member_TotalScore,Member_TitleNameThai,Member_SurnameThai,"
                    + "Member_CompanyTel,Member_Active "
                    + "from memmaster order by Member_Code";
            MySQLMemberConnect mysql = new MySQLMemberConnect();
            try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        listMembers.add(mapping(rs, new MemberModel()));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return listMembers;
    }

    public void update(MemberModel[] listMember) {
        LOGGER.debug("update");
        try {
            MySQLMemberConnect mysql = new MySQLMemberConnect();
            try (Connection conn = mysql.openConnection()) {
                conn.setAutoCommit(false);
                String sql = "update memmaster set Member_TotalPurchase=?, Member_TotalScore=? where Member_Code=?;";
                try (PreparedStatement prepStmt = conn.prepareStatement(sql)) {
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
            LOGGER.error(e.getMessage());
        }
    }

    public void save(MemberModel[] listMember) {
        LOGGER.debug("save");
        try {
            MySQLMemberConnect mysql = new MySQLMemberConnect();
            try (Connection conn = mysql.openConnection()) {
                conn.setAutoCommit(false);
                String sql = "insert into memmaster"
                        + "(Member_Code,Member_NameThai,Member_HomeTel,Member_Email,Member_Brithday,"
                        + "Member_ExpiredDate,Member_TotalPurchase,Member_Mobile,Member_TotalScore,Member_TitleNameThai,"
                        + "Member_SurnameThai,Member_CompanyTel,Member_Active,System_Created,System_Updated) "
                        + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                try (PreparedStatement prepStmt = conn.prepareStatement(sql)) {
                    for (MemberModel model : listMember) {
                        if (model.getSaveOrUpdate().equals("save")) {
                            prepStmt.setString(1, model.getCode());
                            prepStmt.setString(2, ThaiUtil.Unicode2ASCII(model.getFirst_name()));
                            prepStmt.setString(3, model.getMobile());
                            prepStmt.setString(4, model.getEmail());
                            prepStmt.setDate(5, DateUtil.getDate(model.getBirthday()));
                            prepStmt.setDate(6, DateUtil.getDate(model.getExpired_date()));
                            prepStmt.setFloat(7, model.getTotal_purchase());
                            prepStmt.setString(8, model.getMobile());
                            prepStmt.setFloat(9, model.getTotal_score());
                            prepStmt.setString(10, ThaiUtil.Unicode2ASCII(model.getPrefix()));
                            prepStmt.setString(11, ThaiUtil.Unicode2ASCII(model.getLast_name()));
                            prepStmt.setString(12, model.getMobile());
                            prepStmt.setString(13, "Y");
                            prepStmt.setDate(14, new Date(new java.util.Date().getTime()));
                            prepStmt.setDate(15, new Date(new java.util.Date().getTime()));

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
            LOGGER.error(e.getMessage());
        }
    }
}
