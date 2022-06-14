package api.connect;

import api.connect.model.MemberMapping;
import api.connect.model.MemberModel;
import api.connect.model.RedeemMapping;
import api.connect.model.ReqMemberBody;
import api.connect.model.ReqRedeemBody;
import com.google.gson.Gson;
import core.controller.ControllerDB;
import core.redeem.model.RedeemModel;
import file.config.ConfigProps;
import file.config.FileConfigValue;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author nateesun
 */
public class ControllerApi {

    private static final Logger LOGGER = Logger.getLogger(ControllerApi.class);

    private static final String USER_AGENT = "Mozilla/5.0";
    private final ConfigProps config = FileConfigValue.loadConfig();
    private final Gson gson = new Gson();
    private final ControllerDB controllerDB = new ControllerDB();

    public String callGetService(String url) throws IOException {
        LOGGER.debug("ControllerApi:callGetService :" + url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("database", config.getApiServiceDB());
        con.setRequestProperty("Authorization", "Basic " + config.getApiServiceAuth());
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            LOGGER.debug(response.toString());
            return response.toString();
        }

        return null;
    }

    public String sendPutService(String url, String reqBody) throws IOException {
        LOGGER.debug("ControllerApi:sendPutService :" + url + "," + reqBody);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("database", config.getApiServiceDB());
        con.setRequestProperty("Authorization", "Basic YWRtaW46c29mdHBvczIwMTM=");

        if (reqBody != null) {
            con.setDoInput(true);
            con.setDoOutput(true);
            try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
                out.writeBytes(reqBody);
                out.flush();
            }
        }

        int responseCode = con.getResponseCode();
        LOGGER.info("ControllerApi:PUT Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            LOGGER.debug(response.toString());
            return response.toString();
        } else {
            LOGGER.error("ControllerApi:PUT request not worked");
        }

        return null;
    }

    public boolean getVersion() {
        LOGGER.debug("ControllerApi:getVersion");
        try {
            callGetService(config.getApiServiceVersion());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        return true;
    }
    
    public MemberModel getMemberMapping(String getMember) {
        LOGGER.debug("ControllerApi:getMemberMapping");
        JSONObject json = new JSONObject(getMember);
        return gson.fromJson(json.toString(), MemberModel.class);
    }
    
    public MemberMapping getMemberOneMapping(String getMember) {
        LOGGER.debug("ControllerApi:getMemberOneMapping");
        JSONObject json = new JSONObject(getMember);
        return gson.fromJson(json.toString(), MemberMapping.class);
    }

    public MemberModel[] getMemberMapping() throws IOException {
        LOGGER.debug("ControllerApi:getMemberMapping");
        String getMember = callGetService(config.getApiServiceMember());
        LOGGER.debug(getMember);
        JSONObject json = new JSONObject(getMember);
        return gson.fromJson(json.get("data").toString(), MemberModel[].class);
    }
    
    public RedeemMapping getRedeemMapping(String getRedeem) {
        LOGGER.debug("ControllerApi:getRedeemMapping");
        JSONObject json = new JSONObject(getRedeem);
        return gson.fromJson(json.toString(), RedeemMapping.class);
    }

    public RedeemModel[] getRedeemMapping() throws IOException {
        LOGGER.debug("ControllerApi:getRedeemMapping");
        String getRedeem = callGetService(config.getApiServiceRedeem());
        LOGGER.debug(getRedeem);
        JSONObject json = new JSONObject(getRedeem);
        return gson.fromJson(json.get("data").toString(), RedeemModel[].class);
    }

    public String pushMemberService(MemberModel[] insertMember) throws IOException {
        LOGGER.debug("ControllerApi:pushMemberService");
        ReqMemberBody[] model = controllerDB.getMemberReqBody(insertMember);
        if (model.length == 0) {
            LOGGER.debug("not found member to push update");
            return "";
        }
        JSONArray jsonArr = new JSONArray();
        for (ReqMemberBody m : model) {
            jsonArr.put(gson.toJson(m));
        }
        String jsonData = jsonArr.toString();
        LOGGER.debug(jsonData);
        return sendPutService(config.getApiServiceMember(), jsonData);
    }

    public String pushRedeemService(RedeemModel[] insertRedeem) throws IOException {
        LOGGER.debug("ControllerApi:pushRedeemService");
        ReqRedeemBody[] model = controllerDB.getRedeemReqBody(insertRedeem);
        if (model.length == 0) {
            LOGGER.debug("not found redeem to push update");
            return "";
        }
        JSONArray jsonArr = new JSONArray();
        for (ReqRedeemBody r : model) {
            jsonArr.put(gson.toJson(r));
        }
        String jsonData = jsonArr.toString();
        LOGGER.debug(jsonData);
        return sendPutService(config.getApiServiceRedeem(), jsonData);
    }
}
