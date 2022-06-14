package api.model;

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
public class ReqMemberBody {

    private Float Member_TotalPurchase;
    private Float Member_TotalScore;
    private String Member_Code;

    public ReqMemberBody(Float Member_TotalPurchase, Float Member_TotalScore, String Member_Code) {
        this.Member_TotalPurchase = Member_TotalPurchase;
        this.Member_TotalScore = Member_TotalScore;
        this.Member_Code = Member_Code;
    }
}
