package api.connect;

import api.connect.model.MemberModel;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import utils.DateUtil;

/**
 *
 * @author nateesun
 */

public class Member {
    private static final Logger LOGGER = Logger.getLogger(Member.class);

    public MemberModel mappingJsonToModel(JSONObject rs, MemberModel bean) {
        LOGGER.debug("Member:mappingJsonToModel");
        try {
            bean.setCode(rs.getString("code"));
            bean.setCompany_code(rs.getString("company_code"));
            bean.setGender(rs.getString("gender"));
            bean.setStatus(rs.getString("status"));
            bean.setEmail(rs.getString("email"));
            bean.setBirthday(DateUtil.getDateString(rs.getString("birthday")));
            bean.setExpired_date(DateUtil.getDateString(rs.getString("expired_date")));
            bean.setTotal_purchase(rs.getFloat("total_purchase"));
            bean.setMobile(rs.getString("mobile"));
            bean.setPoint_expired_date(DateUtil.getDateString(rs.getString("point_expired_date")));
            bean.setTotal_score(rs.getFloat("total_score"));
            bean.setActive(rs.getString("active"));
            bean.setFirst_name(rs.getString("first_name"));
            bean.setLast_name(rs.getString("last_name"));
            bean.setSystem_created(DateUtil.getDateString(rs.getString("system_created")));
            bean.setSystem_updated(DateUtil.getDateString(rs.getString("system_updated")));
            bean.setLine_id(rs.getString("line_id"));
            bean.setPrefix(rs.getString("prefix"));
            bean.setUuid_index(rs.getString("uuid_index"));
            bean.setMember_role(rs.getString("member_role"));
            bean.setData_sync(rs.getString("data_sync"));
            bean.setLine_user_id(rs.getString("line_user_id"));
        } catch (JSONException e) {
            LOGGER.error(e.getMessage());
        }
        
        return bean;
    }

    public MemberModel mappingPosModelToModel(JSONObject rs, MemberModel bean) {
        LOGGER.debug("Member:mappingPosModelToModel");
        try {
            bean.setCode(rs.getString("code"));
            bean.setCompany_code(rs.getString("company_code"));
            bean.setGender(rs.getString("gender"));
            bean.setStatus(rs.getString("status"));
            bean.setEmail(rs.getString("email"));
            bean.setBirthday(DateUtil.getDateString(rs.getString("birthday")));
            bean.setExpired_date(DateUtil.getDateString(rs.getString("expired_date")));
            bean.setTotal_purchase(rs.getFloat("total_purchase"));
            bean.setMobile(rs.getString("mobile"));
            bean.setPoint_expired_date(DateUtil.getDateString(rs.getString("point_expired_date")));
            bean.setTotal_score(rs.getFloat("total_score"));
            bean.setActive(rs.getString("active"));
            bean.setFirst_name(rs.getString("first_name"));
            bean.setLast_name(rs.getString("last_name"));
            bean.setSystem_created(DateUtil.getDateString(rs.getString("system_created")));
            bean.setSystem_updated(DateUtil.getDateString(rs.getString("system_updated")));
            bean.setLine_id(rs.getString("line_id"));
            bean.setPrefix(rs.getString("prefix"));
            bean.setUuid_index(rs.getString("uuid_index"));
            bean.setMember_role(rs.getString("member_role"));
            bean.setData_sync(rs.getString("data_sync"));
            bean.setLine_user_id(rs.getString("line_user_id"));
        } catch (JSONException e) {
            LOGGER.error(e.getMessage());
        }
        
        return bean;
    }

    public JSONObject mappingModelToJSON(MemberModel model) {
        LOGGER.debug("Member:mappingModelToJSON");
        return new JSONObject(model);
    }
}
