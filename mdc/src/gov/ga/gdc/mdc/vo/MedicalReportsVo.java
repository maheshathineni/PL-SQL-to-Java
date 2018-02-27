package gov.ga.gdc.mdc.vo;

public class MedicalReportsVo {
    
    
    private String   requestInst =null ;
    private String   scribeCode =null;
    private String   locatedAt =null ;
    private String   daterRange =null ;
    
    
    public String getScribeCode() {
        return scribeCode;
    }
    public void setScribeCode(String scribeCode) {
        this.scribeCode = scribeCode;
    }
    public String getRequestInst() {
        return requestInst;
    }
    public void setRequestInst(String requestInst) {
        this.requestInst = requestInst;
    }
    public String getLocatedAt() {
        return locatedAt;
    }
    public void setLocatedAt(String locatedAt) {
        this.locatedAt = locatedAt;
    }
    public String getDaterRange() {
        return daterRange;
    }
    public void setDaterRange(String daterRange) {
        this.daterRange = daterRange;
    }




}
