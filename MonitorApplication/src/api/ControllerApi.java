package api;

import com.google.gson.Gson;
import database.DbConfig;
import database.local.ControllerDB;
import database.local.RedeemModel;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private final DbConfig config;
    private final Gson gson = new Gson();
    private final ControllerDB controllerDB = new ControllerDB();

    public ControllerApi() {
        config = DbConfig.loadConfig();
    }

    public String callGetService(String url) throws IOException {
        LOGGER.debug("callGetService :" + url);
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
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            return response.toString();
        }

        return null;
    }

    public String sendPutService(String url, String reqBody) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("database", config.getApiServiceDB());
        con.setRequestProperty("Authorization", "Basic " + config.getApiServiceAuth());

        if (reqBody != null) {
            con.setDoInput(true);
            con.setDoOutput(true);
            try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
                out.writeBytes(reqBody);
                out.flush();
            }
        }

        int responseCode = con.getResponseCode();
        LOGGER.info("PUT Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            return response.toString();
        } else {
            LOGGER.error("PUT request not worked");
        }

        return null;
    }

    public boolean getVersion() {
        LOGGER.debug("getVersion");
        try {
            callGetService(config.getApiVersion());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        return true;
    }

    public MemberModel[] getMemberMapping() throws IOException {
        LOGGER.debug("getMemberMapping");
        String getMember = callGetService(config.getApiServiceMember());
        JSONObject json = new JSONObject(getMember);
        return gson.fromJson(json.get("data").toString(), MemberModel[].class);
    }

    public RedeemModel[] getRedeemMapping() throws IOException {
        LOGGER.debug("getRedeemMapping");
        String getRedeem = callGetService(config.getApiServiceRedeem());
        JSONObject json = new JSONObject(getRedeem);
        return gson.fromJson(json.get("data").toString(), RedeemModel[].class);
    }

    public String pushMemberService() throws IOException {
        LOGGER.debug("pushMemberService");
        ReqMemberBody[] model = controllerDB.getMemberReqBody();
        if (model.length == 0) {
            LOGGER.debug("not found member to push update");
            return "";
        }
        JSONArray jsonArr = new JSONArray();
        for (ReqMemberBody m : model) {
            jsonArr.put(gson.toJson(m));
        }
        String jsonData = jsonArr.toString();
        return sendPutService(config.getApiServiceMember(), jsonData);
    }

    public String pushRedeemService() throws IOException {
        LOGGER.debug("pushRedeemService");
        ReqRedeemBody[] model = controllerDB.getRedeemReqBody();
        if (model.length == 0) {
            LOGGER.debug("not found redeem to push update");
            return "";
        }
        JSONArray jsonArr = new JSONArray();
        for (ReqRedeemBody r : model) {
            jsonArr.put(gson.toJson(r));
        }
        String jsonData = jsonArr.toString();
        return sendPutService(config.getApiServiceRedeem(), jsonData);
    }
}
