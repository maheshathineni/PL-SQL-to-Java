package gov.ga.gdc.mdc.bo;

import gov.ga.gdc.commonspring.bo.CommonBusinessDelegate;
import gov.ga.gdc.commonspring.exception.DataAccessException;
import gov.ga.gdc.commonspring.vo.OffenderVo;
import gov.ga.gdc.commonspring.vo.ScribeVo;
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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MedicalConsultsBusinessDelegate {
    

    MedicalConsultsBusinessLogic  medConsultsBL = new MedicalConsultsBusinessLogicImpl();
	CommonBusinessDelegate commonBD = new CommonBusinessDelegate();

	public String getDBHostName()
	{
		return medConsultsBL.getHostName();
	}

        public ArrayList<MedicalReportsVo> getSundownInstitutions() {
            return medConsultsBL.getSundownInstitutions();
         }
        
        public ArrayList<ProviderListVo> getProviderList() {
            return medConsultsBL.getProviderList();
         }
     
        
        public ProviderListVo addMedicalProvider(ProviderListVo dVo) {
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addMedicalProvider update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.addMedicalProvider(dVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            }

        }
        

        public int editMedicalProvider(ProviderListVo dVo) {
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addMedicalProvider update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.editMedicalProvider(dVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            }

        }

        public ConsultEntryVo addConsultations(ConsultEntryVo dVo) {
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addConsultations update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.addConsultations(dVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            }

        }
        

        public ConsultEntryVo  addUmDecision(ConsultEntryVo dVo) {
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addUmDecision update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.addUmDecision(dVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            }

        }
        
        public AppointmentVo  addAppointment(AppointmentVo dVo) {
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert  addAppointment update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.addAppointment(dVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            }

        }
        
        public ArrayList<ConsultationMaintVo> getConsultationMaintList(){
            
            return medConsultsBL.getConsultationMaintList();
        }
        
        public ConsultationMaintVo addConsultationMaint(ConsultationMaintVo cVo){
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addConsultationMaint update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.addConsultationMaint(cVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            } 
            
        }
        
        public int editConsultationMaint(ConsultationMaintVo dVo){
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert editConsultationMaint update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.editConsultationMaint(dVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            }
        }
  
        public ArrayList<UserMaintVo> getUserMaintList(){
          
            return medConsultsBL.getUserMaintList();
        }
        
        public int editUserMaintenance(UserMaintVo dVo){
          
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert editUserMaintenance update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.editUserMaintenance(dVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            }
        }
        
        
        public ArrayList<TransportMaintVo> getTransportMaintList(){
            
            return medConsultsBL.getTransportMaintList();
        }
        
        public TransportMaintVo addTransportMaint(TransportMaintVo cVo){
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addTransportMaint update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.addTransportMaint(cVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            } 
            
        }
        
        public int editTransportMaint(TransportMaintVo dVo){
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addTransportMaint update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.editTransportMaint(dVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            }
        }
        
        
        public ArrayList<ApptLocationMaintVo> getApptLocationMaintList(){
            
            return medConsultsBL.getApptLocationMaintList();
        }
        
        public ApptLocationMaintVo addApptLocationMaint(ApptLocationMaintVo cVo){
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addApptLocationMaint update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.addApptLocationMaint(cVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            } 
            
        }
        
        public int editApptLocationMaint(ApptLocationMaintVo dVo){
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addApptLocationMaint update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.editApptLocationMaint(dVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            }
        }
  
        
        
        public ArrayList<ApptTypeMaintVo> getApptTypeMaintList(){
            
            return medConsultsBL.getApptTypeMaintList();
        }
        
        public ApptTypeMaintVo addApptTypeMaint(ApptTypeMaintVo cVo){
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addApptTypeMaint update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.addApptTypeMaint(cVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            } 
            
        }
        
        public int editApptTypeMaint(ApptTypeMaintVo dVo){
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addApptTypeMaint update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.editApptTypeMaint(dVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            }
        }
  
        
        
        public ArrayList<ConsultantsRecMaintVo> getConsultantsRecMaintList(){
            
            return medConsultsBL.getConsultantsRecMaintList();
        }
        
        public ConsultantsRecMaintVo addConsultantsRecMaint(ConsultantsRecMaintVo cVo){
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addConsultantsRecMaint update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.addConsultantsRecMaint(cVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            } 
            
        }
        
        public int editConsultantsRecMaint(ConsultantsRecMaintVo dVo){
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addConsultantsRecMaint update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.editConsultantsRecMaint(dVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            }
        }
  
  
        
        
        public ArrayList<ReviewDecisionVo> getReviewDecisionList(){
            
            return medConsultsBL.getReviewDecisionList();
        }
        
        public ReviewDecisionVo addReviewDecision(ReviewDecisionVo cVo){
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addReviewDecision update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.addReviewDecision(cVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            } 
            
        }
        
        public int editReviewDecision(ReviewDecisionVo dVo){
            try {
                ApplicationContext ctx = new ClassPathXmlApplicationContext("medicalconsultscontext.xml");
                MedicalConsultsBusinessLogic medConsultsBusinessLogic = (MedicalConsultsBusinessLogic) ctx.getBean("medconsults");
                System.out.println("Entered insert addReviewDecision update " + medConsultsBusinessLogic);
                return medConsultsBusinessLogic.editReviewDecision(dVo);
            } catch (DataAccessException se) {
                throw new DataAccessException("Insert Update ", se);
            }
        }
        
        public ArrayList<TotalConsultsVo> getTotalConsultsList(){
         
            return medConsultsBL.getTotalConsultsList();
        }
        
        //the resul not showing in service
        public ArrayList<ApptSelctionResultVo> getApptSelectionResult(){
         
            return medConsultsBL.getApptSelectionResult();
        }
        
        public ArrayList<CloseConsultResultVo> getCloseConsultResult(){
            
            return medConsultsBL.getCloseConsultResult();
        }
        
        public ArrayList<ScheduledConsultVo> getScheduledConsultReport(){
            
            return medConsultsBL.getScheduledConsultReport();
        }
        
       public ArrayList<ScheduledDetailReportVo> getScheduledConsultDetailReport(){
            
            return medConsultsBL.getScheduledConsultDetailReport();
        }
  
  

	
/*	
	public ArrayList<MovementsVo> bedAndPopulationReport(String scribeCd) {
		return  movementsBusinessLogic.bedAndPopulationReport(scribeCd);
	}

	public ArrayList<MovementsVo> facilityVacancyReport(String date) {
		return movementsBusinessLogic.facilityVacancyReport(date);
	}

	public ArrayList<ArrayList<MovementsVo>> medicalMhVacancyReport() {
		return movementsBusinessLogic.medicalMhVacancyReport();
	}*/

}
