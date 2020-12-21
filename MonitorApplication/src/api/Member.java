package api;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author nateesun
 */

public class Member {

    public MemberModel mappingJsonToModel(JSONObject rs, MemberModel model) {
        try {
            model.setCode(rs.getString("code"));
            model.setCompany_code(rs.getString("company_code"));
            model.setGender(rs.getString("gender"));
            model.setStatus(rs.getString("status"));
            model.setEmail(rs.getString("email"));
            model.setBirthday(rs.getString("birthday"));
            model.setExpired_date(rs.getString("expired_date"));
            model.setTotal_purchase(rs.getFloat("total_purchase"));
            model.setMobile(rs.getString("mobile"));
            model.setPoint_expired_date(rs.getString("point_expired_date"));
            model.setTotal_score(rs.getFloat("total_score"));
            model.setActive(rs.getString("active"));
            model.setFirst_name(rs.getString("first_name"));
            model.setLast_name(rs.getString("last_name"));
            model.setSystem_created(rs.getString("system_created"));
            model.setSystem_updated(rs.getString("system_updated"));
            model.setLine_id(rs.getString("line_id"));
            model.setPrefix(rs.getString("prefix"));
            model.setUuid_index(rs.getString("uuid_index"));
            model.setMember_role(rs.getString("member_role"));
            model.setData_sync(rs.getString("data_sync"));
            model.setLine_user_id(rs.getString("line_user_id"));
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
        return model;
    }
    
    public MemberModel mappingPosModelToModel(JSONObject rs, MemberModel model) {
        try {
            model.setCode(rs.getString("code"));
            model.setCompany_code(rs.getString("company_code"));
            model.setGender(rs.getString("gender"));
            model.setStatus(rs.getString("status"));
            model.setEmail(rs.getString("email"));
            model.setBirthday(rs.getString("birthday"));
            model.setExpired_date(rs.getString("expired_date"));
            model.setTotal_purchase(rs.getFloat("total_purchase"));
            model.setMobile(rs.getString("mobile"));
            model.setPoint_expired_date(rs.getString("point_expired_date"));
            model.setTotal_score(rs.getFloat("total_score"));
            model.setActive(rs.getString("active"));
            model.setFirst_name(rs.getString("first_name"));
            model.setLast_name(rs.getString("last_name"));
            model.setSystem_created(rs.getString("system_created"));
            model.setSystem_updated(rs.getString("system_updated"));
            model.setLine_id(rs.getString("line_id"));
            model.setPrefix(rs.getString("prefix"));
            model.setUuid_index(rs.getString("uuid_index"));
            model.setMember_role(rs.getString("member_role"));
            model.setData_sync(rs.getString("data_sync"));
            model.setLine_user_id(rs.getString("line_user_id"));
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
        return model;
    }
    
    public JSONObject mappingModelToJSON(MemberModel model){
        return new JSONObject(model);
    }
}
