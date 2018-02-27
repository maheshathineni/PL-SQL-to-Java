package gov.ga.gdc.mdc.bo;




import gov.ga.gdc.mdc.dao.MedicalConsultsDao;
import gov.ga.gdc.mdc.dao.MedicalConsultsDaoImpl;
import gov.ga.gdc.mdc.vo.AppointmentVo;
import gov.ga.gdc.mdc.vo.ApptLocationMaintVo;
import gov.ga.gdc.mdc.vo.ApptSelctionResultVo;
import gov.ga.gdc.mdc.vo.ApptTypeMaintVo;
import gov.ga.gdc.mdc.vo.CloseConsultResultVo;
import gov.ga.gdc.mdc.vo.ConsultEntryVo;
import gov.ga.gdc.mdc.vo.ConsultantsRecMaintVo;
import gov.ga.gdc.mdc.vo.ConsultationMaintVo;
import gov.ga.gdc.mdc.vo.MedicalConsultsVo;
import gov.ga.gdc.mdc.vo.MedicalReportsVo;
import gov.ga.gdc.mdc.vo.MedicalVo;
import gov.ga.gdc.mdc.vo.ProviderListVo;
import gov.ga.gdc.mdc.vo.ReviewDecisionVo;
import gov.ga.gdc.mdc.vo.ScheduledConsultVo;
import gov.ga.gdc.mdc.vo.ScheduledDetailReportVo;
import gov.ga.gdc.mdc.vo.TotalConsultsVo;
import gov.ga.gdc.mdc.vo.TransportMaintVo;
import gov.ga.gdc.mdc.vo.UserMaintVo;

import java.util.ArrayList;
import java.util.Iterator;


public class MedicalConsultsBusinessLogicImpl implements MedicalConsultsBusinessLogic {
   
    MedicalConsultsDao medconsultsDao1 = new MedicalConsultsDaoImpl();
    MedicalConsultsDao medconsultsDao;
    
    void setMedconsultsDao(MedicalConsultsDao dao) {
        this.medconsultsDao1 = dao;
    }
    
    @Override
    public String getHostName() {
        // TODO Auto-generated method stub
        return null;
    }

    public ArrayList<MedicalReportsVo> getSundownInstitutions(){
        return  medconsultsDao1.getSundownInstitutions();
    }
    
    public ConsultEntryVo addConsultations(ConsultEntryVo dVo) {
        
        return  medconsultsDao.addConsultations(dVo );
    }
    public ConsultEntryVo  addUmDecision(ConsultEntryVo dVo){
       
        return  medconsultsDao.addUmDecision(dVo );
    }
    
    public AppointmentVo  addAppointment(AppointmentVo dVo){
        
        return  medconsultsDao.addAppointment(dVo );       
    }
    
    public ArrayList<ProviderListVo> getProviderList(){
     
        ArrayList<ProviderListVo> obj22=   medconsultsDao1.getProviderList();
      if(  obj22.size() ==0){
         
          obj22=new ArrayList<ProviderListVo>();
      }
        
        return   obj22 ;
    }
    
    public ProviderListVo addMedicalProvider(ProviderListVo dVo){
        
        return  medconsultsDao.addMedicalProvider(dVo ); 
    }
    
    public int editMedicalProvider(ProviderListVo dVo){
     
        return  medconsultsDao.editMedicalProvider(dVo ); 
    }
    
    public ArrayList<ConsultationMaintVo> getConsultationMaintList(){
        return  medconsultsDao1.getConsultationMaintList();   
    }
    public ConsultationMaintVo addConsultationMaint(ConsultationMaintVo cVo){
      
        return  medconsultsDao.addConsultationMaint(cVo ); 
    }
    public int editConsultationMaint(ConsultationMaintVo dVo){
     
        return  medconsultsDao.editConsultationMaint(dVo ); 
    }
    
    public ArrayList<UserMaintVo> getUserMaintList(){
    
        return  medconsultsDao1.getUserMaintList();
    }
    
    public int editUserMaintenance(UserMaintVo dVo){
       
        return  medconsultsDao.editUserMaintenance(dVo ); 
    }
    
    
    public ArrayList<TransportMaintVo> getTransportMaintList(){
        return  medconsultsDao1.getTransportMaintList();   
    }
    public TransportMaintVo addTransportMaint(TransportMaintVo cVo){
      
        return  medconsultsDao.addTransportMaint(cVo ); 
    }
    public int editTransportMaint(TransportMaintVo dVo){
     
        return  medconsultsDao.editTransportMaint(dVo ); 
    }
    
    
    public ArrayList<ApptLocationMaintVo> getApptLocationMaintList(){
        return  medconsultsDao1.getApptLocationMaintList();   
    }
    public ApptLocationMaintVo addApptLocationMaint(ApptLocationMaintVo cVo){
      
        return  medconsultsDao.addApptLocationMaint(cVo ); 
    }
    public int editApptLocationMaint(ApptLocationMaintVo dVo){
     
        return  medconsultsDao.editApptLocationMaint(dVo ); 
    }
    
    
    public ArrayList<ApptTypeMaintVo> getApptTypeMaintList(){
        return  medconsultsDao1.getApptTypeMaintList();   
    }
    public ApptTypeMaintVo addApptTypeMaint(ApptTypeMaintVo cVo){
      
        return  medconsultsDao.addApptTypeMaint(cVo ); 
    }
    public int editApptTypeMaint(ApptTypeMaintVo dVo){
     
        return  medconsultsDao.editApptTypeMaint(dVo ); 
    }
    
    
    public ArrayList<ConsultantsRecMaintVo> getConsultantsRecMaintList(){
        return  medconsultsDao1.getConsultantsRecMaintList();   
    }
    public ConsultantsRecMaintVo addConsultantsRecMaint(ConsultantsRecMaintVo cVo){
      
        return  medconsultsDao.addConsultantsRecMaint(cVo ); 
    }
    public int editConsultantsRecMaint(ConsultantsRecMaintVo dVo){
     
        return  medconsultsDao.editConsultantsRecMaint(dVo ); 
    }
    
    
    public ArrayList<ReviewDecisionVo> getReviewDecisionList(){
        return  medconsultsDao1.getReviewDecisionList();   
    }
    public ReviewDecisionVo addReviewDecision(ReviewDecisionVo cVo){
      
        return  medconsultsDao.addReviewDecision(cVo ); 
    }
    public int editReviewDecision(ReviewDecisionVo dVo){
     
        return  medconsultsDao.editReviewDecision(dVo ); 
    }
    
    
    public ArrayList<TotalConsultsVo> getTotalConsultsList(){
        
        String wherePart = "";
        String fromPart = "";
        String selectPart = "";
     
    
        if(1==1){
            //pending
            wherePart = "and m.appt_status_code = 104 and m.review_decision_code = 20 ";
            selectPart =null;
        }
        if(2==2){
            //unapproved
            wherePart = " and m.appt_status_code = 103  and m.review_decision_code IS NULL ";
            selectPart =null;
            
        }
        if(3==3){
           //appoinment
            
            //select 
            selectPart ="  , um.review_desc REVIEW_DESC ";
            //from
            fromPart="  med_utilization_mgmt_ref um , ";
            //where 
            wherePart = "  and m.appt_status_code = '100'  AND UM.REVIEW_CODE     = M.REVIEW_DECISION_CODE ";
        }
     
      return   medconsultsDao1.getTotalConsultsList(wherePart,fromPart,selectPart);
    }
    
    
    public ArrayList<ApptSelctionResultVo> getApptSelectionResult(){
      
        return  medconsultsDao1.getApptSelectionResult(); 
    }
    
    
    public ArrayList<CloseConsultResultVo> getCloseConsultResult(){
      
        return  medconsultsDao1.getCloseConsultResult(); 
    }
    
    public ArrayList<ScheduledConsultVo> getScheduledConsultReport(){
        
        String wherePart="";
        String requestCd ="";
        String located ="";
        String sqlQuery ="";
        
        
        if(requestCd == "2" && located == "1" )
        {
            
            sqlQuery="SELECT UM_REQ_CONSULTATION_CODE, UNO#, APPT_TIME, REVIEW_DECISION_CODE  FROM MED_CONSULTATIONS;";
            
        }else{
           
            sqlQuery="SELECT um_req_consultation_code, uno#, appt_time, review_decision_code  FROM med_consultations, jmov_beds";
        }
        
        if (requestCd != null && !requestCd.isEmpty() && !requestCd.equals("ALL")  ) 
        {                       
                System.out.println("requestCd is ALL");
                wherePart +="AND a.site_cd="+requestCd ;
                
                
        }
        
        if (requestCd != null && !requestCd.isEmpty() && requestCd.equals("ALL")  ) 
        {                       
                System.out.println("requestCd is one");
                wherePart +="AND a.site_cd="+requestCd ;
        }
        
        
        if (located != null && !located.isEmpty() && !located.equals("ALL")  ) 
        {                       
                System.out.println("located is ALL");
                wherePart +="AND a.site_cd="+located;
        }
        
        
        if (located != null && !located.isEmpty() && located.equals("ALL")  ) 
        {                       
                System.out.println("located is one");
                wherePart +="AND a.site_cd="+located;
        }
        
        return  medconsultsDao1.getScheduledConsultReport();    
    }
    
    public ArrayList<ScheduledDetailReportVo> getScheduledConsultDetailReport(){
        
     /*   
        (TRANS_DATE) < TO_DATE()
        AND (TRANS_DATE) >= TO_DATE()
        AND (APPT_TIME) < TO_DATE()
         AND (APPT_TIME) >= TO_DATE()
         AND  TRANS_TYPE_CODE = '0' 
         AND PROVIDER_CODE = '0'
         AND  UM_REQ_CONSULTATION_CODE = '0'
          AND  REQ_CONSULTATION_CODE = '0'
           AND  UM_REQ_TYPE = ''
           AND  req_type = ''
           AND JMOV_BEDS.SCRIBE_CODE = ''
            AND APPT_LOCATION_CODE = '9'
            AND  appt_status_code = '90'  ;*/
        
        return  medconsultsDao1.getScheduledConsultDetailReport();  
    }

}
