package gov.ga.gdc.mdc.vo;

public class ConsultationMaintVo {
    
    
    
    private String consultationCode;
    private String consultationDesc;
    private String  isObsolete;
    private int  result ;
    
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
    public String getConsultationCode() {
        return consultationCode;
    }
    public void setConsultationCode(String consultationCode) {
        this.consultationCode = consultationCode;
    }
    public String getConsultationDesc() {
        return consultationDesc;
    }
    public void setConsultationDesc(String consultationDesc) {
        this.consultationDesc = consultationDesc;
    }

    
    
    

}
