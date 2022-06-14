package file.config;

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
public class ConfigProps {
    
    private String posHost;
    private String posUser;
    private String posPassword;
    private String posPort;
    private String posDb;
    private String posMemberDb;

    private String apiServiceHost;
    private String apiServiceMember;
    private String apiServiceRedeem;
    private String apiServiceDB;
    private String apiServiceVersion;
    private String apiServiceAuth;

    private int timeSync;
    private String appDownload;
    private String thaiUtf = "Y";
    
}
