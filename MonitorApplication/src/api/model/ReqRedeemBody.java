package api;

/**
 *
 * @author nateesun
 */
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

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getBill_no() {
        return bill_no;
    }

    public void setBill_no(String bill_no) {
        this.bill_no = bill_no;
    }

    public String getUse_in_branch() {
        return use_in_branch;
    }

    public void setUse_in_branch(String use_in_branch) {
        this.use_in_branch = use_in_branch;
    }

    public String getEmp_code_redeem() {
        return emp_code_redeem;
    }

    public void setEmp_code_redeem(String emp_code_redeem) {
        this.emp_code_redeem = emp_code_redeem;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRedeem_code() {
        return redeem_code;
    }

    public void setRedeem_code(String redeem_code) {
        this.redeem_code = redeem_code;
    }

}
