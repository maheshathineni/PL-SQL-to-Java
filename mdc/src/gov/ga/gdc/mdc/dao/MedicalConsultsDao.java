package gov.ga.gdc.mdc.dao;




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

public interface MedicalConsultsDao {
	
	public String getHostName();
	
	 public ArrayList<MedicalReportsVo> getSundownInstitutions();
	 
	    public ConsultEntryVo addConsultations(ConsultEntryVo dVo);
	    
	    public ConsultEntryVo  addUmDecision(ConsultEntryVo dVo);
	    
	    public AppointmentVo  addAppointment(AppointmentVo dVo);
	    
	    public ArrayList<ProviderListVo> getProviderList();
	    
	    public ProviderListVo addMedicalProvider(ProviderListVo dVo);
	    
	    public int editMedicalProvider(ProviderListVo dVo) ; 
	    
	    public ArrayList<ConsultationMaintVo> getConsultationMaintList();           
            public ConsultationMaintVo addConsultationMaint(ConsultationMaintVo cVo);
            public int editConsultationMaint(ConsultationMaintVo dVo);
            
            public ArrayList<UserMaintVo> getUserMaintList();
            public int editUserMaintenance(UserMaintVo dVo);
            
            public ArrayList<TransportMaintVo> getTransportMaintList();           
            public TransportMaintVo addTransportMaint(TransportMaintVo cVo);
            public int editTransportMaint(TransportMaintVo dVo);
            
            public ArrayList<ApptLocationMaintVo> getApptLocationMaintList();           
            public ApptLocationMaintVo addApptLocationMaint(ApptLocationMaintVo cVo);
            public int editApptLocationMaint(ApptLocationMaintVo dVo);
            
            public ArrayList<ApptTypeMaintVo> getApptTypeMaintList();           
            public ApptTypeMaintVo addApptTypeMaint(ApptTypeMaintVo cVo);
            public int editApptTypeMaint(ApptTypeMaintVo dVo);
	    
            public ArrayList<ConsultantsRecMaintVo> getConsultantsRecMaintList();           
            public ConsultantsRecMaintVo addConsultantsRecMaint(ConsultantsRecMaintVo cVo);
            public int editConsultantsRecMaint(ConsultantsRecMaintVo dVo);
           
            
            public ArrayList<ReviewDecisionVo> getReviewDecisionList();           
            public  ReviewDecisionVo addReviewDecision(ReviewDecisionVo cVo);
           
            public int editReviewDecision(ReviewDecisionVo dVo);
            
            public ArrayList<TotalConsultsVo> getTotalConsultsList(String wherePart,String fromPart,String selectPart);
            
            public ArrayList<ApptSelctionResultVo> getApptSelectionResult();
            
            public ArrayList<CloseConsultResultVo> getCloseConsultResult();
            
            public ArrayList<ScheduledConsultVo> getScheduledConsultReport();
            
            public ArrayList<ScheduledDetailReportVo> getScheduledConsultDetailReport();
            
            
            
/*	public ArrayList<MovementsVo> movementsHistory(String uno) ;
	public ArrayList<MovementsVo> arrive(String uno);

	
	public String insertMovementRecord(MovementsVo mVo) ;
	public ArrayList<MovementsVo> getMovementsDetailsByUno(String uno);
	public void updateBedOccupiedByUno(String bedSeqNo);


	public ArrayList<ArrayList<MovementsVo>> medicalMhVacancyReport() ;*/

}
