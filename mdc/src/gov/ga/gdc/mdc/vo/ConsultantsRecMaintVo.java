package gov.ga.gdc.mdc.vo;

public class ConsultantsRecMaintVo {
    
    private String  recommendShortDesc;
    private String recommendDesc;
    private String recommendCode;
    private String isObsolete;
    private int result;
    
    
    
    public String getIsObsolete() {
        return isObsolete;
    }
    public void setIsObsolete(String isObsolete) {
        this.isObsolete = isObsolete;
    }   
    public String getRecommendShortDesc() {
        return recommendShortDesc;
    }
    public void setRecommendShortDesc(String recommendShortDesc) {
        this.recommendShortDesc = recommendShortDesc;
    }
    public String getRecommendDesc() {
        return recommendDesc;
    }
    public void setRecommendDesc(String recommendDesc) {
        this.recommendDesc = recommendDesc;
    }
    public String getRecommendCode() {
        return recommendCode;
    }
    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode;
    }
    public int getResult() {
        return result;
    }
    public void setResult(int result) {
        this.result = result;
    }


}
