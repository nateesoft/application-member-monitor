package database.local;

import java.sql.Date;

/**
 *
 * @author nateesun
 */
public class RedeemModel {

    private String uuid_index;
    private String redeem_code;
    private String product_code;
    private Integer point_to_redeem;
    private String use_in_branch;
    private String emp_code_redeem;
    private String member_code_use;
    private Integer qty_in_use;
    private Date system_create;
    private Date redeem_date;
    private Date in_time;
    private String status_use;
    private String active;
    private String redeem_name;
    private String bill_no;
    private Float discount_amt;
    private Float discount_percent;
    private String redeem_or_free;
    private String data_sync;
    private String saveOrUpdate;

    public String getUuid_index() {
        return uuid_index;
    }

    public void setUuid_index(String uuid_index) {
        this.uuid_index = uuid_index;
    }

    public String getRedeem_code() {
        return redeem_code;
    }

    public void setRedeem_code(String redeem_code) {
        this.redeem_code = redeem_code;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public Integer getPoint_to_redeem() {
        return point_to_redeem;
    }

    public void setPoint_to_redeem(Integer point_to_redeem) {
        this.point_to_redeem = point_to_redeem;
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

    public String getMember_code_use() {
        return member_code_use;
    }

    public void setMember_code_use(String member_code_use) {
        this.member_code_use = member_code_use;
    }

    public Integer getQty_in_use() {
        return qty_in_use;
    }

    public void setQty_in_use(Integer qty_in_use) {
        this.qty_in_use = qty_in_use;
    }

    public Date getSystem_create() {
        return system_create;
    }

    public void setSystem_create(Date system_create) {
        this.system_create = system_create;
    }

    public Date getRedeem_date() {
        return redeem_date;
    }

    public void setRedeem_date(Date redeem_date) {
        this.redeem_date = redeem_date;
    }

    public Date getIn_time() {
        return in_time;
    }

    public void setIn_time(Date in_time) {
        this.in_time = in_time;
    }

    public String getStatus_use() {
        return status_use;
    }

    public void setStatus_use(String status_use) {
        this.status_use = status_use;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRedeem_name() {
        return redeem_name;
    }

    public void setRedeem_name(String redeem_name) {
        this.redeem_name = redeem_name;
    }

    public String getBill_no() {
        return bill_no;
    }

    public void setBill_no(String bill_no) {
        this.bill_no = bill_no;
    }

    public Float getDiscount_amt() {
        return discount_amt;
    }

    public void setDiscount_amt(Float discount_amt) {
        this.discount_amt = discount_amt;
    }

    public Float getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(Float discount_percent) {
        this.discount_percent = discount_percent;
    }

    public String getRedeem_or_free() {
        return redeem_or_free;
    }

    public void setRedeem_or_free(String redeem_or_free) {
        this.redeem_or_free = redeem_or_free;
    }

    public String getData_sync() {
        return data_sync;
    }

    public void setData_sync(String data_sync) {
        this.data_sync = data_sync;
    }

    public String getSaveOrUpdate() {
        return saveOrUpdate;
    }

    public void setSaveOrUpdate(String saveOrUpdate) {
        this.saveOrUpdate = saveOrUpdate;
    }

    @Override
    public String toString() {
        return "RedeemModel{" + "uuid_index=" + uuid_index + ", redeem_code=" + redeem_code + ", product_code=" + product_code + ", point_to_redeem=" + point_to_redeem + ", use_in_branch=" + use_in_branch + ", emp_code_redeem=" + emp_code_redeem + ", member_code_use=" + member_code_use + ", qty_in_use=" + qty_in_use + ", system_create=" + system_create + ", redeem_date=" + redeem_date + ", in_time=" + in_time + ", status_use=" + status_use + ", active=" + active + ", redeem_name=" + redeem_name + ", bill_no=" + bill_no + ", discount_amt=" + discount_amt + ", discount_percent=" + discount_percent + ", redeem_or_free=" + redeem_or_free + ", data_sync=" + data_sync + '}';
    }

}
