package gov.ga.gdc.mdc.vo;

public class AppointmentVo {
    
    private String  providerCode;
    private String  apptLocationCode; 
    private String  apptTime;
    private String   apptComments;
    private String  transportDate;
    private String  transportCode;
    private String  transportComments;
    private String  apptStatusCode;
    
    private String  enteredBy;
    private String  enteredDate;
    private String  recordVersion;
    private int result;
    private String    uno ;
    private String    requestDate;
    private String    requestConsultationCode;
    
    
    
    public String getUno() {
        return uno;
    }
    public void setUno(String uno) {
        this.uno = uno;
    }
    public String getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
    public String getRequestConsultationCode() {
        return requestConsultationCode;
    }
    public void setRequestConsultationCode(String requestConsultationCode) {
        this.requestConsultationCode = requestConsultationCode;
    }
 
    
    
    
    public String getEnteredBy() {
        return enteredBy;
    }
    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }
    public String getEnteredDate() {
        return enteredDate;
    }
    public void setEnteredDate(String enteredDate) {
        this.enteredDate = enteredDate;
    }
    public String getRecordVersion() {
        return recordVersion;
    }
    public void setRecordVersion(String recordVersion) {
        this.recordVersion = recordVersion;
    }

    
    public String getProviderCode() {
        return providerCode;
    }
    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }
    public String getApptLocationCode() {
        return apptLocationCode;
    }
    public void setApptLocationCode(String apptLocationCode) {
        this.apptLocationCode = apptLocationCode;
    }
    public String getApptTime() {
        return apptTime;
    }
    public void setApptTime(String apptTime) {
        this.apptTime = apptTime;
    }
    public String getApptComments() {
        return apptComments;
    }
    public void setApptComments(String apptComments) {
        this.apptComments = apptComments;
    }
    public String getTransportDate() {
        return transportDate;
    }
    public void setTransportDate(String transportDate) {
        this.transportDate = transportDate;
    }
    public String getTransportCode() {
        return transportCode;
    }
    public void setTransportCode(String transportCode) {
        this.transportCode = transportCode;
    }
    public String getTransportComments() {
        return transportComments;
    }
    public void setTransportComments(String transportComments) {
        this.transportComments = transportComments;
    }
    public String getApptStatusCode() {
        return apptStatusCode;
    }
    public void setApptStatusCode(String apptStatusCode) {
        this.apptStatusCode = apptStatusCode;
    }
    public int getResult() {
        return result;
    }
    public void setResult(int result) {
        this.result = result;
    }

    

}
