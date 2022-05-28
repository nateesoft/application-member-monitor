package database;

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
public class DbConfigProps {
    
    private String posHost;
    private String posUser;
    private String posPassword;
    private String posPort;
    private String posDbName;

    private String memberHost;
    private String memberUser;
    private String memberPassword;
    private String memberPort;
    private String memberDbName;

    private String apiServiceHost;
    private String apiServiceMember;
    private String apiServiceRedeem;
    private String apiServiceDB;
    private String apiServiceAuth;
    private String apiServiceVersion;

    private int timeSync;
    private String appDownload;
    private String thaiUtf = "Y";
    
}
