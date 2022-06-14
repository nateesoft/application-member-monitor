package api.model;

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
public class ReqRedeemBody {

    private String product_code;
    private String bill_no;
    private String use_in_branch;
    private String emp_code_redeem;
    private String active;
    private String redeem_code;

    public ReqRedeemBody(String product_code, String bill_no, String use_in_branch, String emp_code_redeem, String active, String redeem_code) {
        this.product_code = product_code;
        this.bill_no = bill_no;
        this.use_in_branch = use_in_branch;
        this.emp_code_redeem = emp_code_redeem;
        this.active = active;
        this.redeem_code = redeem_code;
    }
}
