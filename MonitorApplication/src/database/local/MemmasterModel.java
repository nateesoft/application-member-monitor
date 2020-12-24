package database.local;

import java.sql.Date;

/**
 *
 * @author nateesun
 */
public class MemmasterModel {

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

    public String getMember_Code() {
        return Member_Code;
    }

    public void setMember_Code(String Member_Code) {
        this.Member_Code = Member_Code;
    }

    public String getMember_NameThai() {
        return Member_NameThai;
    }

    public void setMember_NameThai(String Member_NameThai) {
        this.Member_NameThai = Member_NameThai;
    }

    public String getMember_Email() {
        return Member_Email;
    }

    public void setMember_Email(String Member_Email) {
        this.Member_Email = Member_Email;
    }

    public Date getMember_Brithday() {
        return Member_Brithday;
    }

    public void setMember_Brithday(Date Member_Brithday) {
        this.Member_Brithday = Member_Brithday;
    }

    public Date getMember_ExpiredDate() {
        return Member_ExpiredDate;
    }

    public void setMember_ExpiredDate(Date Member_ExpiredDate) {
        this.Member_ExpiredDate = Member_ExpiredDate;
    }

    public Float getMember_TotalPurchase() {
        return Member_TotalPurchase;
    }

    public void setMember_TotalPurchase(Float Member_TotalPurchase) {
        this.Member_TotalPurchase = Member_TotalPurchase;
    }

    public String getMember_Mobile() {
        return Member_Mobile;
    }

    public void setMember_Mobile(String Member_Mobile) {
        this.Member_Mobile = Member_Mobile;
    }

    public Float getMember_TotalScore() {
        return Member_TotalScore;
    }

    public void setMember_TotalScore(Float Member_TotalScore) {
        this.Member_TotalScore = Member_TotalScore;
    }

    public String getMember_TitleNameThai() {
        return Member_TitleNameThai;
    }

    public void setMember_TitleNameThai(String Member_TitleNameThai) {
        this.Member_TitleNameThai = Member_TitleNameThai;
    }

    public String getMember_SurnameThai() {
        return Member_SurnameThai;
    }

    public void setMember_SurnameThai(String Member_SurnameThai) {
        this.Member_SurnameThai = Member_SurnameThai;
    }

    @Override
    public String toString() {
        return "MemmasterModel{" + "Member_Code=" + Member_Code + ", Member_NameThai=" + Member_NameThai + ", Member_Email=" + Member_Email + ", Member_Brithday=" + Member_Brithday + ", Member_ExpiredDate=" + Member_ExpiredDate + ", Member_TotalPurchase=" + Member_TotalPurchase + ", Member_Mobile=" + Member_Mobile + ", Member_TotalScore=" + Member_TotalScore + ", Member_TitleNameThai=" + Member_TitleNameThai + ", Member_SurnameThai=" + Member_SurnameThai + '}';
    }

}
