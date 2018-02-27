package gov.ga.gdc.mdc.vo;

public class ReviewDecisionVo {
    
    private String  reviewShortDesc;
    private String reviewDesc;
    private String reviewCode;
    private String isObsolete;
    private int result;

    public String getReviewShortDesc() {
        return reviewShortDesc;
    }
    public void setReviewShortDesc(String reviewShortDesc) {
        this.reviewShortDesc = reviewShortDesc;
    }
    public String getReviewDesc() {
        return reviewDesc;
    }
    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }
    public String getReviewCode() {
        return reviewCode;
    }
    public void setReviewCode(String reviewCode) {
        this.reviewCode = reviewCode;
    }
    public String getIsObsolete() {
        return isObsolete;
    }
    public void setIsObsolete(String isObsolete) {
        this.isObsolete = isObsolete;
    }
    public int getResult() {
        return result;
    }
    public void setResult(int result) {
        this.result = result;
    }

}
