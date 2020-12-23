package api;

/**
 *
 * @author nateesun
 */
public class ReqMemberBody {

    private Float Member_TotalPurchase;
    private Float Member_TotalScore;
    private String Member_Code;

    public ReqMemberBody(Float Member_TotalPurchase, Float Member_TotalScore, String Member_Code) {
        this.Member_TotalPurchase = Member_TotalPurchase;
        this.Member_TotalScore = Member_TotalScore;
        this.Member_Code = Member_Code;
    }

    public Float getMember_TotalPurchase() {
        return Member_TotalPurchase;
    }

    public void setMember_TotalPurchase(Float Member_TotalPurchase) {
        this.Member_TotalPurchase = Member_TotalPurchase;
    }

    public Float getMember_TotalScore() {
        return Member_TotalScore;
    }

    public void setMember_TotalScore(Float Member_TotalScore) {
        this.Member_TotalScore = Member_TotalScore;
    }

    public String getMember_Code() {
        return Member_Code;
    }

    public void setMember_Code(String Member_Code) {
        this.Member_Code = Member_Code;
    }

}
