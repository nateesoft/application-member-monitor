package api.connect.model;

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
public class MemberModel {

    private String code;
    private String company_code;
    private String gender;
    private String status;
    private String email;
    private String birthday;
    private String expired_date;
    private Float total_purchase;
    private String mobile;
    private String point_expired_date;
    private Float total_score;
    private String active;
    private String first_name;
    private String last_name;
    private String system_created;
    private String system_updated;
    private String line_id;
    private String prefix;
    private String uuid_index;
    private String member_role;
    private String data_sync;
    private String line_user_id;
    private String saveOrUpdate;
    private String member_code_ref;
    
    // add more
    private String database;
    private String action_status;
}
