package core.memmaster.model;

import java.sql.Date;
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
public class MemmasterBean {

    private String Member_Code;
    private String Member_NameThai;
    private String Member_Email;
    private Date Member_Brithday;
    private Date Member_ExpiredDate;
    private Float Member_TotalPurchase;
    private String Member_Mobile;
    private Float Member_TotalScore;
    private String Member_TitleNameThai;
    private String Member_SurnameThai;

}
