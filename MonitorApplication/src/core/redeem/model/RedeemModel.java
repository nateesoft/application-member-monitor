package core.redeem.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author nateesun
 */

@Getter
@Setter
@ToString
public class RedeemModel {

    private String uuid_index;
    private String redeem_code;
    private String product_code;
    private Integer point_to_redeem;
    private String use_in_branch;
    private String emp_code_redeem;
    private String member_code_use;
    private Integer qty_in_use;
    private String system_create;
    private String redeem_date;
    private String in_time;
    private String status_use;
    private String active;
    private String redeem_name;
    private String bill_no;
    private Float discount_amt;
    private Float discount_percent;
    private String redeem_or_free;
    private String data_sync;
    private String saveOrUpdate;

    // add more
    private String database;
    private String action_status;
}
