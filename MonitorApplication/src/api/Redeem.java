package api;

import database.local.RedeemModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import utils.DateUtil;

/**
 *
 * @author nateesun
 */

public class Redeem {
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
            model.setSystem_create(DateUtil.getDateString(rs.getString("system_create")));
            model.setRedeem_date(DateUtil.getDateString(rs.getString("redeem_date")));
            model.setIn_time(DateUtil.getDateString(rs.getString("in_time")));
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

}
