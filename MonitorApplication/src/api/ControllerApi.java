package api;

import com.google.gson.Gson;
import database.DbConfig;
import database.local.RedeemModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;
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
    
    public boolean getVersion(){
        LOGGER.debug("getVersion");
        try {
            callGetService(config.getApiVersion());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        return true;
    }

    private String getMember() throws IOException {
        LOGGER.debug("getMember");
        return callGetService(config.getApiServiceMember());
    }

    private String getRedeem() throws IOException {
        LOGGER.debug("getRedeem");
        return callGetService(config.getApiServiceRedeem());
    }

    public MemberModel[] getMemberMapping() throws IOException {
        LOGGER.debug("getMemberMapping");
        JSONObject json = new JSONObject(getMember());
        return gson.fromJson(json.get("data").toString(), MemberModel[].class);
    }

    public RedeemModel[] getRedeemMapping() throws IOException {
        LOGGER.debug("getRedeemMapping");
        JSONObject json = new JSONObject(getRedeem());
        return gson.fromJson(json.get("data").toString(), RedeemModel[].class);
    }
}