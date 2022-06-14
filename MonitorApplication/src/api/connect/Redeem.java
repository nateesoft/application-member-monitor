package api.connect;

import core.redeem.model.RedeemModel;
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

    public RedeemModel mapping(ResultSet rs, RedeemModel bean) {
        LOGGER.debug("Redeem:mapping");
        try {
            bean.setUuid_index(rs.getString("uuid_index"));
            bean.setRedeem_code(rs.getString("redeem_code"));
            bean.setProduct_code(rs.getString("product_code"));
            bean.setPoint_to_redeem(rs.getInt("point_to_redeem"));
            bean.setUse_in_branch(rs.getString("use_in_branch"));
            bean.setEmp_code_redeem(rs.getString("emp_code_redeem"));
            bean.setMember_code_use(rs.getString("member_code_use"));
            bean.setQty_in_use(rs.getInt("qty_in_use"));
            bean.setSystem_create(DateUtil.getDateString(rs.getString("system_create")));
            bean.setRedeem_date(DateUtil.getDateString(rs.getString("redeem_date")));
            bean.setIn_time(DateUtil.getDateString(rs.getString("in_time")));
            bean.setStatus_use(rs.getString("status_use"));
            bean.setActive(rs.getString("active"));
            bean.setRedeem_name(rs.getString("redeem_name"));
            bean.setBill_no(rs.getString("bill_no"));
            bean.setDiscount_amt(rs.getFloat("discount_amt"));
            bean.setDiscount_percent(rs.getFloat("discount_percent"));
            bean.setRedeem_or_free(rs.getString("redeem_or_free"));
            bean.setData_sync(rs.getString("data_sync"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        
        return bean;
    }

}
