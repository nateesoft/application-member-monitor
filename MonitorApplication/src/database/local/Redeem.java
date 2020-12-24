package database.local;

import database.MySQLMemberConnect;
import database.MySQLPOSConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import utils.ThaiUtil;

/**
 *
 * @author nateesun
 */
interface RedeemInterface {

    public RedeemModel findById(String id);
    public List<RedeemModel> findAll();
}

public class Redeem implements RedeemInterface {

    private static final Logger LOGGER = Logger.getLogger(Redeem.class);

    public RedeemModel mapping(ResultSet rs, RedeemModel model) {
        LOGGER.debug("mapping");
        try {
            model.setUuid_index(rs.getString("uuid_index"));
            model.setRedeem_code(rs.getString("redeem_code"));
            model.setProduct_code(rs.getString("product_code"));
            model.setPoint_to_redeem(rs.getInt("point_to_redeem"));
            model.setUse_in_branch(rs.getString("use_in_branch"));
            model.setEmp_code_redeem(rs.getString("emp_code_redeem"));
            model.setMember_code_use(rs.getString("member_code_use"));
            model.setQty_in_use(rs.getInt("qty_in_use"));
            model.setSystem_create(rs.getDate("system_create"));
            model.setRedeem_date(rs.getDate("redeem_date"));
            model.setIn_time(rs.getDate("in_time"));
            model.setStatus_use(rs.getString("status_use"));
            model.setActive(rs.getString("active"));
            model.setRedeem_name(rs.getString("redeem_name"));
            model.setBill_no(rs.getString("bill_no"));
            model.setDiscount_amt(rs.getFloat("discount_amt"));
            model.setDiscount_percent(rs.getFloat("discount_percent"));
            model.setRedeem_or_free(rs.getString("redeem_or_free"));
            model.setData_sync(rs.getString("data_sync"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return model;
    }

    @Override
    public RedeemModel findById(String id) {
        LOGGER.debug("findById");
        try {
            String sql = "select * from redeem where uuid_index='" + id + "'";
            MySQLPOSConnect mysql = new MySQLPOSConnect();
            try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    if (rs.next()) {
                        return mapping(rs, new RedeemModel());
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<RedeemModel> findAll() {
        LOGGER.debug("findAll");
        List<RedeemModel> listRedeems = new ArrayList<>();
        try {
            String sql = "select * from redeem order by redeem_code";
            MySQLPOSConnect mysql = new MySQLPOSConnect();
            try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        listRedeems.add(mapping(rs, new RedeemModel()));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return listRedeems;
    }

    public void update(RedeemModel[] listRedeem) {
        LOGGER.debug("update");
        if(listRedeem.length==0){
            return;
        }
        try {
            MySQLMemberConnect mysql = new MySQLMemberConnect();
            try (Connection conn = mysql.openConnection()) {
                conn.setAutoCommit(false);
                String sql = "update redeem set bill_no=?, status_use=?, active=?, use_in_branch=? where uuid_index=?";
                try (PreparedStatement prepStmt = conn.prepareStatement(sql)) {
                    for (RedeemModel model : listRedeem) {
                        if (model.getSaveOrUpdate().equals("update")) {
                            prepStmt.setString(1, model.getBill_no());
                            prepStmt.setString(2, model.getStatus_use());
                            prepStmt.setString(3, model.getActive());
                            prepStmt.setString(4, model.getUse_in_branch());
                            prepStmt.setString(5, model.getUuid_index());

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

    public void save(RedeemModel[] listRedeem) {
        LOGGER.debug("save");
        if(listRedeem.length==0){
            LOGGER.debug("not found redeem to update local db");
            return;
        }
        try {
            MySQLMemberConnect mysql = new MySQLMemberConnect();
            try (Connection conn = mysql.openConnection()) {
                conn.setAutoCommit(false);
                String sql = "insert into redeem"
                        + "(uuid_index,redeem_code,product_code,point_to_redeem,use_in_branch,"
                        + "emp_code_redeem,member_code_use,qty_in_use,system_create,redeem_date,"
                        + "in_time,status_use,active,redeem_name,bill_no,"
                        + "discount_amt,discount_percent,redeem_or_free,data_sync) "
                        + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                try (PreparedStatement prepStmt = conn.prepareStatement(sql)) {
                    for (RedeemModel model : listRedeem) {
                        if (model.getSaveOrUpdate().equals("save")) {
                            prepStmt.setString(1, model.getUuid_index());
                            prepStmt.setString(2, model.getRedeem_code());
                            prepStmt.setString(3, model.getProduct_code());
                            prepStmt.setFloat(4, model.getPoint_to_redeem());
                            prepStmt.setString(5, model.getUse_in_branch());
                            prepStmt.setString(6, model.getEmp_code_redeem());
                            prepStmt.setString(7, model.getMember_code_use());
                            prepStmt.setInt(8, model.getQty_in_use());
                            prepStmt.setDate(9, model.getSystem_create());
                            prepStmt.setDate(10, model.getRedeem_date());
                            prepStmt.setDate(11, model.getIn_time());
                            prepStmt.setString(12, model.getStatus_use());
                            prepStmt.setString(13, model.getActive());
                            prepStmt.setString(14, ThaiUtil.Unicode2ASCII(model.getRedeem_name()));
                            prepStmt.setString(15, model.getBill_no());
                            prepStmt.setFloat(16, model.getDiscount_amt());
                            prepStmt.setFloat(17, model.getDiscount_percent());
                            prepStmt.setString(18, model.getRedeem_or_free());
                            prepStmt.setString(19, model.getData_sync());

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
