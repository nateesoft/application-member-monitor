package database.local;

import database.MySQLPOSConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nateesun
 */
interface RedeemInterface {

    public RedeemModel findById(String id);
//    public void syncData();
//    public void findByRedeemCode();
//    public void findAll();
//    public void searchData();
//    public void bulkInsert();
//    public void bulkInsertTemp();
//    public void getQuery();
//    public void create();
//    public void createTemp();
//    public void update();
//    public void delete();
//    public void deleteTemp();
}

public class Redeem implements RedeemInterface {

    public RedeemModel mapping(ResultSet rs, RedeemModel model) {
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
            System.err.println(e.getMessage());
        }
        return model;
    }

    @Override
    public RedeemModel findById(String id) {
        try {
            String sql = "select * from redeem where uuid_index='"+id+"'";
            MySQLPOSConnect mysql = new MySQLPOSConnect();
            try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    if (rs.next()) {
                        return mapping(rs, new RedeemModel());
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
