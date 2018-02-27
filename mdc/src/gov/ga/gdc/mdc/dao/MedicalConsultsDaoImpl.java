package gov.ga.gdc.mdc.dao;

import gov.ga.gdc.commonspring.dao.ReferenceDao;
import gov.ga.gdc.commonspring.exception.DataAccessException;
import gov.ga.gdc.commonspring.exception.RecordVersionException;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MedicalConsultsDaoImpl implements MedicalConsultsDao {
    

    MedicalConsultsDaoFactory medicalConsultsDaoFactory;
        ReferenceDao referenceDao;

    public ReferenceDao getReferenceDao() {
        return referenceDao;
    }

    public void setReferenceDao(ReferenceDao referenceDao) {
        this.referenceDao = referenceDao;
    }

    public void setMedicalConsultsDaoFactory(MedicalConsultsDaoFactory medicalConsultsDaoFactory) {
        this.medicalConsultsDaoFactory = medicalConsultsDaoFactory;
    }


	
	
	Log log = LogFactory.getLog("gov.ga.gdc.otf.dao.MovementsDaoImpl.java");

	public MedicalConsultsDaoFactory getMedicalConsultsDaoFactory() {
		return medicalConsultsDaoFactory;
	}

    @Override
    public String getHostName() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ArrayList<MedicalConsultsVo> getMedicalConsultations()
    {
        
                                    ArrayList<MedicalConsultsVo>   medicalConsultsList = new ArrayList<MedicalConsultsVo>();
                                    
                                    MedicalConsultsVo mVo=null; 
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    ResultSet rs = null;
                                    
                                    try {
                                                  
                                    MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                    
                                        orclConn = orclFactory.createReadOnlyConnection();
                                 
                           /*             List<OrderHeaderEntity> orderResponseList = namedParameterjdbcTemplate.query(
                                                OrderServiceQueries.QUERY_FETCH_TOP_ORDERS,
                                                new BeanPropertyRowMapper<OrderHeaderEntity>(OrderHeaderEntity.class));
                                        LOG.info("TOp Order query"+OrderServiceQueries.QUERY_FETCH_TOP_ORDERS);
                                        List<Order> orderVOList = new ArrayList<Order>();*/
                                    String sqlQuery ="SELECT MC.REQ_DATE ,                                       "+
                                                     "    MC.ENTRY_DATE  ,                                       "+
                                            // add name for user id 
                                                     "    MC.USER_ID  ,                                          "+
                                                     "    MC.REQ_CONSULTATION_CODE ,                             "+
                                                     "    MCR.CONSULTATION_DESC ,                                "+
                                                     "    MC.REQ_INST_CODE ,                                     "+
                                                     "    SI.SCRIBE_CODE,                                        "+
                                                     "    S.SCRIBE_DESC,                                         "+
                                                     "    MC.REQ_BY,                                             "+
                                                     "    MC.REQ_TYPE,                                           "+
                                                     "    MC.DIAGNOSIS,                                          "+
                                                     "    MC.MEDICAL_HISTORY,                                    "+
                                                     "    MC.PHYSICAL_HISTORY,                                   "+
                                                     "    MC.LABWORK,                                            "+
                                                     "    MC.MEDICATIONS ,                                       "+
                                                     "    MC.REQ_COMMENTS,                                       "+
                                                     "    MC.REVIEW_AUTHORITY,                                   "+
                                                     "    UR.EMPLOYEE_ID,                                        "+
                                                     "    (E.LAST_NAME   || E.MIDDLE_NAME  || E.FIRST_NAME) NAME,"+
                                                     "    MC.UM_REQ_TYPE,                                        "+
                                                     "    MC.REVIEW_DECISION_CODE,                               "+
                                                     "    UM.REVIEW_DESC,                                        "+
                                                     "    MC.UM_ENTERED_DT    ,                                  "+
                                                     //add desc for um desc 
                                                     "    MC.UM_REQ_CONSULTATION_CODE ,                          "+
                                                     "    MC.APPROVAL_NUMBER ,                                   "+
                                                     "    MC.REVIEW_COMMENTS,                                    "+
                                                     "    MC.APPT_LOCATION_CODE ,                                "+
                                                     "    AL.LOCATION_DESC ,                                     "+
                                                     "    MC.APPT_TIME,                                          "+
                                                     "    MC.APPT_COMMENTS,                                      "+
                                                     "    MC.TRANS_DATE,                                         "+
                                                     "    MC.TRANS_COMMENTS,                                     "+
                                                     "    MC.TRANS_TYPE_CODE ,                                   "+
                                                     "    MT.TRANS_DESC,                                         "+
                                                     "    MC.PROVIDER_CODE,                                      "+
                                                     "    MP.NAME,                                               "+
                                                     "    MC.MED_CONSULTANT_NAME,                                "+
                                                     "    MC.SERVICE_DELIVERED_DT,                               "+
                                                     "    MC.CONSULTANTS_REPORT ,                                "+
                                                     "    MC.CONSULTANTS_REC_CODE ,                              "+
                                                     "    MCR.RECOMMEND_DESC   ,                                   "+
                                                     "    MC.CONSULTATION_DATE                                   "+
                                                     "  FROM MED_CONSULTATIONS MC ,                              "+
                                                     "       MED_CONSULTATIONS_REF MCR ,                         "+
                                                     "       SUNDOWN_INSTITUTION_REF SI,                         "+
                                                     "       SCRIBE_REF S ,                                      "+
                                                     "       MED_USER_REF UR ,                                   "+
                                                     "       EMPLOYEES E,                                        "+
                                                     "       MED_UTILIZATION_MGMT_REF um ,                       "+
                                                     "       MED_APPT_LOCATION_REF al,                           "+
                                                     "       MED_TRANS_TYPE_REF mt,                              "+
                                                     "       MED_PROVIDERS_REF mp,                               "+
                                                     "       MED_CONSULTANTS_REC_REF mcr                         "+
                                                     "  WHERE MC.UNO#                ='340340'                   "+
                                                     "      AND MC.REQ_CONSULTATION_CODE =MCR.CONSULTATION_CODE  "+
                                                     "      AND MC.REQ_INST_CODE         = SI.SCRIBE_CODE        "+
                                                     "      AND SI.SCRIBE_CODE           =S.SCRIBE_CODE          "+
                                                     "      AND MC.REVIEW_AUTHORITY      = UR.EMPLOYEE_ID        "+
                                                     "      AND UR.EMPLOYEE_ID           = E.EMPLOYEE_ID         "+
                                                     "      AND MC.REVIEW_DECISION_CODE  = UM.REVIEW_CODE        "+
                                                     "      AND MC.APPT_LOCATION_CODE    =AL.LOCATION_CODE       "+
                                                     "      AND MC.TRANS_TYPE_CODE       =MT.TRANS_CODE          "+
                                                     "      AND MC.PROVIDER_CODE         = MP.PROVIDER_CODE      "+
                                                     "      AND MC.CONSULTANTS_REC_CODE  =MCR.RECOMMEND_CODE     "+
                                                     "      AND MC.REQ_CONSULTATION_CODE ='163'          ";
                                    
                                    System.out.println("getMedicalConsultations query :  "+sqlQuery);
                                    
                                            stmt = orclConn.prepareStatement(sqlQuery);
                                            stmt.clearParameters();  
                                            rs = stmt.executeQuery();
                                            
                                            while (rs.next())
                                            {
                                                mVo = new MedicalConsultsVo();
                                                mVo.setRequestDate(rs.getString(3));
                                                mVo.setEnteredDate(rs.getString(3));
                                                mVo.setEnteredBY(rs.getString(3));
                                               // mVo.setUserName(rs.getString(3));
                                                mVo.setReqConsultationCode(rs.getString(1));
                                                mVo.setReqConsultationDesc(rs.getString(2));
                                                mVo.setReqInstCode(rs.getString(3));
                                                mVo.setScribeCode(rs.getString(3));
                                                mVo.setScribeDesc(rs.getString(3));
                                                mVo.setRequestedBy(rs.getString(3));
                                                mVo.setRequestType(rs.getString(3));
                                                mVo.setDiagnosis(rs.getString(3));
                                                mVo.setMedicalHistory(rs.getString(3));
                                                mVo.setPhysicalHistory(rs.getString(3));
                                                mVo.setLabWork(rs.getString(3));
                                                mVo.setMedications(rs.getString(3));
                                                mVo.setRequestComments(rs.getString(3));
                                                mVo.setReviewAuthority(rs.getString(3));
                                                mVo.setEmployeeId(rs.getString(3));
                                                mVo.setEmployeeName(rs.getString(3));
                                                mVo.setReqType(rs.getString(3));
                                                mVo.setReviewDecisionCode(rs.getString(3));
                                                mVo.setReviewDecisionDesc(rs.getString(3));
                                                mVo.setUmEnteredDate(rs.getString(3));
                                                mVo.setUmReqConsultationCode(rs.getString(3));
                                           //     mVo.setUmReqConsultationDesc(rs.getString(3));
                                                mVo.setApprovalNumber(rs.getString(3));
                                                mVo.setReviewComments(rs.getString(3));
                                                mVo.setApptLocationCode(rs.getString(3));
                                                mVo.setApptLocationDesc(rs.getString(3));
                                                mVo.setApptTime(rs.getString(3));
                                                mVo.setApptComments(rs.getString(3));
                                                mVo.setTransportDate(rs.getString(3));
                                                mVo.setTransportComments(rs.getString(3));
                                                mVo.setTransportCode(rs.getString(3));
                                                mVo.setTransportDesc(rs.getString(3));
                                                mVo.setProviderCode(rs.getString(3));
                                                mVo.setProviderName(rs.getString(3));
                                                mVo.setConsultantsName(rs.getString(3));
                                                mVo.setServiceDeliveredDate(rs.getString(3));
                                                mVo.setConsultantsReport(rs.getString(3));
                                                mVo.setConsultantsRecCode(rs.getString(3));
                                                mVo.setConsultantsRecDesc(rs.getString(3));
                                                mVo.setConsultantsDate(rs.getString(3));
                                          
                                                         
                                                            medicalConsultsList.add(mVo);
                                    }
                                    rs.close();
                                    stmt.close();
                                    orclConn.close();
                                    }
                                    catch(SQLException se) {
                                        System.out.println("MedicalConsultsImpl.getMedicalConsultations: " + se.toString());
                                        throw new DataAccessException("getProviderList", se);
                                        
                                    }finally{
                                        try {
                                            if (rs != null)
                                                    rs.close();
                                            if (stmt != null)
                                                    stmt.close();
                                            if (orclConn != null)
                                                    orclConn.close();
                                    } catch (SQLException e) {
                                            System.out.println("MedicalConsultsImpl.getMedicalConsultations: "
                                                            + e.toString());
                                            throw new DataAccessException("getMedicalConsultations", e);
                                    }
                                        
                                    }
                                   
                                    return medicalConsultsList;
        
    }
    
    public ArrayList<TotalConsultsVo> getTotalConsultsList(String wherePart,String fromPart, String selectPart)
    {
        
                                    ArrayList<TotalConsultsVo>   totalConsultsList = new ArrayList<TotalConsultsVo>();
                                    
                                    TotalConsultsVo mVo=null; 
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    ResultSet rs = null;
                                    
                                    try {
                                                  
                                    MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                    
                                        orclConn = orclFactory.createReadOnlyConnection();
                                    
                                    String sqlQuery = " SELECT   M.UNO# ,   "+
                                                 "           M.REQ_DATE ,     "+                                                 
                                                 "           M.UM_REQ_TYPE ,  "+
                                                 "           M.UM_REQ_CONSULTATION_CODE ,  "+
                                                 "           M.REQ_INST_CODE ,  "+
                                                 "           M.REQ_CONSULTATION_CODE , "+
                                                 "           M.ENTRY_DATE ,     "+                                                
                                                 "           M.RECORD_VERSION ,   "+                                                  
                                                 "           (D.LAST_NAME || D.FIRST_NAME || D.MIDDLE_NAME ) NAME ,  "+
                                                 "           D.CURR_SUPERVISION_LEVEL_CD  "+
                                                            selectPart+                                             
                                                 "        FROM  "+
                                                 "           MED_CONSULTATIONS M, "+
                                                             fromPart+
                                                 "           OFFENDER_DEMOGRAPHICS D  , "+
                                                 "           MED_CONSULTATIONS_REF R , "+
                                                 "           scribe_ref sc  "+
                                                 "        WHERE  "+
                                                 "             R.CONSULTATION_CODE = M.REQ_CONSULTATION_CODE "+
                                                 "             AND  sc.scribe_code = m.req_inst_code "
                                                                + wherePart +"" +
                                                 "              AND D.UNO(+)           = M.UNO#  ";
                                        
                                    
                                    System.out.println("getTotalConsultsList query :  "+sqlQuery);
                                    
                                    stmt = orclConn.prepareStatement(sqlQuery);
                                    stmt.clearParameters();  
                                    rs = stmt.executeQuery();
                                    
                                    while (rs.next())
                                    {
                                        mVo = new TotalConsultsVo();
                                        mVo.setUno(rs.getString(1));
                                        mVo.setRequestDate(rs.getString(2));
                                        mVo.setRequestType(rs.getString(3));
                                        mVo.setUmRequestConsultationCode(rs.getString(1));
                                        mVo.setRequestInstCode(rs.getString(2));
                                        mVo.setRequestConsultationCode(rs.getString(3));
                                        mVo.setEntryDate(rs.getString(1));
                                        mVo.setRecordVersion(rs.getString(2));
                                        mVo.setOffenderName(rs.getString(3));
                                        mVo.setLevelCd(rs.getString(3));
                                        mVo.setReviewDesc(rs.getString(3));
                                     
                                        totalConsultsList.add(mVo);
                                    }
                                    rs.close();
                                    stmt.close();
                                    orclConn.close();
                                    }
                                    catch(SQLException se) {
                                        System.out.println("MedicalConsultsImpl.getTotalConsultsList: " + se.toString());
                                        throw new DataAccessException("getTotalConsultsList", se);
                                        
                                    }finally{
                                        try {
                                            if (rs != null)
                                                    rs.close();
                                            if (stmt != null)
                                                    stmt.close();
                                            if (orclConn != null)
                                                    orclConn.close();
                                    } catch (SQLException e) {
                                            System.out.println("MedicalConsultsImpl.getTotalConsultsList: "
                                                            + e.toString());
                                            throw new DataAccessException("getTotalConsultsList", e);
                                    }
                                        
                                    }
                                   
                                    return  totalConsultsList;
        
    }
    public ArrayList<ProviderListVo> getProviderList()
    {
        
                                    ArrayList<ProviderListVo>   providerList = new ArrayList<ProviderListVo>();
                                    
                                    ProviderListVo mVo=null; 
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    ResultSet rs = null;
                                    
                                    try {
                                                  
                                    MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                    
                                        orclConn = orclFactory.createReadOnlyConnection();
                                    
                                    String sqlQuery = " SELECT                  "+ 
                                                      "  PROVIDER_CODE,         "+
                                                      "  NAME,                  "+
                                                      "  SPECIALITY,            "+
                                                      "  IS_OBSOLETE            "+
                                                      " FROM MED_PROVIDERS_REF  "+
                                                      "  ORDER BY NAME  ";
                                    
                                    System.out.println("getProviderList query :  "+sqlQuery);
                                    
                                    stmt = orclConn.prepareStatement(sqlQuery);
                                    stmt.clearParameters();  
                                    rs = stmt.executeQuery();
                                    
                                    while (rs.next())
                                    {
                                        mVo = new ProviderListVo();
                                        mVo.setName(rs.getString(1));
                                        mVo.setSpecialty(rs.getString(2));
                                        mVo.setStatus(rs.getString(3));
                                     
                                        providerList.add(mVo);
                                    }
                                    rs.close();
                                    stmt.close();
                                    orclConn.close();
                                    }
                                    catch(SQLException se) {
                                        System.out.println("MedicalConsultsImpl.getProviderList: " + se.toString());
                                        throw new DataAccessException("getProviderList", se);
                                        
                                    }finally{
                                        try {
                                            if (rs != null)
                                                    rs.close();
                                            if (stmt != null)
                                                    stmt.close();
                                            if (orclConn != null)
                                                    orclConn.close();
                                    } catch (SQLException e) {
                                            System.out.println("MedicalConsultsImpl.getProviderList: "
                                                            + e.toString());
                                            throw new DataAccessException("getProviderList", e);
                                    }
                                        
                                    }
                                   
                                    return providerList;
        
    }
    
    // add medical provider
    
    public ProviderListVo addMedicalProvider(ProviderListVo cVo)
    {

                                    int result = 0;
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory();  
                                    
                                    try {
                                       
                                            orclConn = orclFactory.createConnection();
                                          

                                            //String seqNo = referenceDao.getNextSeqNo("");
                     
                                            String sqlQuery =" INSERT INTO MED_PROVIDERS_REF                              "+ 
                                                             " (                                                          "+                                                   
                                                             "    PROVIDER_CODE,                                          "+
                                                             "    ADDRESS_1,                                              "+
                                                             "    ADDRESS_2,                                              "+
                                                             "    CITY ,                                                  "+
                                                             "    STATE_CODE ,                                            "+
                                                             "    IS_OBSOLETE ,                                           "+
                                                             "    PHONE,                                                  "+
                                                             "    FAX ,                                                   "+
                                                             "    PAGER ,                                                 "+
                                                             "    EMAIL,                                                  "+
                                                             "    SPECIALITY ,                                            "+
                                                             "    COMMENTS ,                                              "+
                                                             "    NAME ,                                                  "+
                                                             "    ZIP ,                                                   "+                                  
                                                             " )                                                          "+ 
                                                             " VALUES " + "(?, ?,?, ?,?,?,?,?,?,?,?,?,?,?)";

                                            System.out.println("addMedicalProvider sqlQuery : " + sqlQuery);

                                            stmt = orclConn.prepareStatement(sqlQuery);
                                            stmt.clearParameters();
                                            
                                           // stmt.setString(1, seqNo);
                                            stmt.setString(1, cVo.getProviderCode());                                                      
                                            stmt.setString(2, cVo.getAddress1());
                                            stmt.setString(3, cVo.getAddress2());                                                  
                                            stmt.setString(4, cVo.getCity());
                                            stmt.setString(5, cVo.getStateCode());
                                            stmt.setString(6, cVo.getIsObsolete());
                                            stmt.setString(7, cVo.getPhone());
                                            stmt.setString(8, cVo.getFax());
                                            stmt.setString(9, cVo.getPager());
                                            stmt.setString(10, cVo.getEmail());
                                            stmt.setString(11, cVo.getSpecialty());
                                            stmt.setString(12, cVo.getComments());
                                            stmt.setString(13, cVo.getName());
                                            stmt.setString(14, cVo.getZip());
                                        

                                            result = stmt.executeUpdate();
                                            
                                            cVo.setResult(result);

                                    } catch (SQLException se) {
                                            System.out.println("MedicalConsultsImpl.addMedicalProvider: " + se.toString());
                                            throw new DataAccessException("addMedicalProvider", se);
                                    } finally {
                                            try {
                                                    if (stmt != null)
                                                            stmt.close();
                                                    if(orclFactory != null)
                                                        orclFactory.releaseConnection(orclConn);
                                                   
                                            } catch (SQLException e) {
                                                    System.out.println("MedicalConsultsImpl.addMedicalProvider: " + e.toString());
                                                    throw new DataAccessException("addMedicalProvider", e);
                                            }
                                    }
                                    return cVo;

                            }
    
    // edit medical provider
    
    public int editMedicalProvider(ProviderListVo dVo){
        
                             
                                int result = 0;
                                Connection orclConn = null;
                                PreparedStatement stmt = null;
                                try {
                                    MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory(); 
                                    
                                        orclConn = orclFactory.createConnection();
                            
                                        
                       
                                       
                                       String sqlQuery = "UPDATE MED_PROVIDERS_REF    "+
                                                         "    SET NAME    = UPPER(?),      "+
                                                         "    ADDRESS_1   = UPPER(?), "+
                                                         "    ADDRESS_2   = UPPER(?),  "+
                                                         "    CITY        = UPPER(?),  "+
                                                         "    STATE_CODE  = ? , "+
                                                         "    ZIP         = ? ,   "+
                                                         "    PHONE       = ? ,  "+
                                                         "    FAX         = ? ,  "+
                                                         "    PAGER       = ? ,  "+
                                                         "    EMAIL       = ? ,  "+
                                                         "    SPECIALITY  = ? ,  "+
                                                         "    IS_OBSOLETE = ? ,   "+
                                                         "    COMMENTS    = UPPER(?)  "+
                                                         "  WHERE PROVIDER_CODE= ? ";
                            
                                        System.out.println("sqlQuery editMedicalProvider : " + sqlQuery);
                            
                                        stmt = orclConn.prepareStatement(sqlQuery);
                                        stmt.clearParameters();
                                        
                                        stmt.setString(1, dVo.getName());
                                        stmt.setString(2, dVo.getAddress1());                
                                        stmt.setString(3, dVo.getAddress2());           
                                        stmt.setString(4, dVo.getCity());
                                        stmt.setString(5, dVo.getStateCode());
                                        stmt.setString(6, dVo.getZip());
                                        stmt.setString(7, dVo.getPhone());
                                        stmt.setString(8, dVo.getFax());
                                        stmt.setString(9, dVo.getPager());
                                        stmt.setString(10,dVo.getEmail());
                                        stmt.setString(11,dVo.getSpecialty());
                                        stmt.setString(12,dVo.getIsObsolete());
                                        stmt.setString(13,dVo.getComments());
                            
                                        result = stmt.executeUpdate();
                                        
                                        //System.out.println("result:"+result);
                            
                                } catch (SQLException se) {
                                        if (se.getErrorCode() == 20000) {
                                                throw new RecordVersionException();
                                        }
                                        System.out.println("MedicalConsultsImpl.editMedicalProvider: " + se.toString());
                                        throw new DataAccessException("editMedicalProvider", se);
                                }  finally {
                                        try {
                                                if (stmt != null)
                                                        stmt.close();
                                        } catch (SQLException e) {
                                                System.out.println("MedicalConsultsImpl.editMedicalProvider: " + e.toString());
                                                
                                                throw new DataAccessException("editMedicalProvider", e);
                                        }
                                }
                                return result;
        
    }
   
    
    //get consultation maintenance
    
    public ArrayList<ConsultationMaintVo> getConsultationMaintList()
    {
        
                                    ArrayList<ConsultationMaintVo>   consultationMaintList = new ArrayList<ConsultationMaintVo>();
                                    
                                    ConsultationMaintVo mVo=null; 
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    ResultSet rs = null;
                                    
                                    try {
                                                  
                                    MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                    
                                        orclConn = orclFactory.createReadOnlyConnection();
                                    
                                
                                    
                                    String sqlQuery = "SELECT CONSULTATION_CODE,        "+
                                                      "  CONSULTATION_DESC              "+
                                                      "FROM MED_CONSULTATIONS_REF       "+
                                                      " WHERE IS_OBSOLETE IS NULL       "+
                                                      " ORDER BY CONSULTATION_DESC  ";
                                    
                                    System.out.println("getConsultationMaintList query :  "+sqlQuery);
                                    
                                    stmt = orclConn.prepareStatement(sqlQuery);
                                    stmt.clearParameters();  
                                    rs = stmt.executeQuery();
                                    
                                    while (rs.next())
                                    {
                                        mVo = new ConsultationMaintVo();
                                        mVo.setConsultationCode(rs.getString(1));
                                        mVo.setConsultationDesc(rs.getString(2));
                                     
                                     
                                        consultationMaintList.add(mVo);
                                    }
                                    rs.close();
                                    stmt.close();
                                    orclConn.close();
                                    }
                                    catch(SQLException se) {
                                        System.out.println("MedicalConsultsImpl.getConsultationMaintList: " + se.toString());
                                        throw new DataAccessException("getConsultationMaintList", se);
                                        
                                    }finally{
                                        try {
                                            if (rs != null)
                                                    rs.close();
                                            if (stmt != null)
                                                    stmt.close();
                                            if (orclConn != null)
                                                    orclConn.close();
                                    } catch (SQLException e) {
                                            System.out.println("MedicalConsultsImpl.getConsultationMaintList: "
                                                            + e.toString());
                                            throw new DataAccessException("getConsultationMaintList", e);
                                    }
                                        
                                    }
                                   
                                    return consultationMaintList;
        
    }
   
    // add consultation maintenance 
    
    public ConsultationMaintVo addConsultationMaint(ConsultationMaintVo cVo)
    {

                                    int result = 0;
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory();  
                                    
                                    try {
                                       
                                            orclConn = orclFactory.createConnection();
                                          

                                            //String seqNo = referenceDao.getNextSeqNo("");
                     
                                            String sqlQuery =" INSERT INTO MED_CONSULTATIONS_REF                        "+ 
                                                             " (                                                        "+                                                   
                                                             "    CONSULTATION_CODE,                                    "+                                                
                                                             "    CONSULTATION_DESC                                     "+                                                                                   
                                                             " )                                                        "+ 
                                                             " VALUES " + "(?, ?)";

                                            System.out.println("addConsultationMaint sqlQuery : " + sqlQuery);

                                            stmt = orclConn.prepareStatement(sqlQuery);
                                            stmt.clearParameters();
                                            
                                           // stmt.setString(1, seqNo);
                                            stmt.setString(1, cVo.getConsultationCode());                                                      
                                            stmt.setString(2, cVo.getConsultationDesc());
                                            result = stmt.executeUpdate();
                                            
                                            cVo.setResult(result);

                                    } catch (SQLException se) {
                                            System.out.println("MedicalConsultsImpl.addConsultationMaint: " + se.toString());
                                            throw new DataAccessException("addConsultationMaint", se);
                                    } finally {
                                            try {
                                                    if (stmt != null)
                                                            stmt.close();
                                                    if(orclFactory != null)
                                                        orclFactory.releaseConnection(orclConn);
                                                   
                                            } catch (SQLException e) {
                                                    System.out.println("MedicalConsultsImpl.addConsultationMaint: " + e.toString());
                                                    throw new DataAccessException("addConsultationMaint", e);
                                            }
                                    }
                                    return cVo;

                            }
    
    // edit consultation maintenance 
    
    public int editConsultationMaint(ConsultationMaintVo dVo){
        
        
                                    int result = 0;
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    try {
                                        MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory(); 
                                        
                                            orclConn = orclFactory.createConnection();
                                                            
                                           
                                           String sqlQuery = "UPDATE MED_PROVIDERS_REF    "+
                                                             "    SET  "+
                                               
                                                             "    IS_OBSOLETE = ?    "+
                                                        
                                                             "  WHERE CONSULTATION_CODE= ? ";
                                
                                            System.out.println("sqlQuery editConsultationMaint : " + sqlQuery);
                                
                                            stmt = orclConn.prepareStatement(sqlQuery);
                                            stmt.clearParameters();
                                            
                                            stmt.setString(1, dVo.getIsObsolete());  
                                            stmt.setString(2, dVo.getConsultationCode());
                               
                                            result = stmt.executeUpdate();
                                            
                                            //System.out.println("result:"+result);
                                
                                    } catch (SQLException se) {
                                            if (se.getErrorCode() == 20000) {
                                                    throw new RecordVersionException();
                                            }
                                            System.out.println("MedicalConsultsImpl.editConsultationMaint: " + se.toString());
                                            throw new DataAccessException("editMedicalProvider", se);
                                    }  finally {
                                            try {
                                                    if (stmt != null)
                                                            stmt.close();
                                            } catch (SQLException e) {
                                                    System.out.println("MedicalConsultsImpl.editConsultationMaint: " + e.toString());
                                                    
                                                    throw new DataAccessException("editConsultationMaint", e);
                                            }
                                    }
                                    return result;

}

    
    //
    
    //get medical user  maintenance
    
    public ArrayList<UserMaintVo> getUserMaintList()
    {
        
                                    ArrayList<UserMaintVo>   userMaintList = new ArrayList<UserMaintVo>();
                                    
                                    UserMaintVo mVo=null; 
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    ResultSet rs = null;
                                    
                                    try {
                                                  
                                    MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                    
                                        orclConn = orclFactory.createReadOnlyConnection();
                                    
                              
                                    String sqlQuery ="SELECT M.EMPLOYEE_ID,LAST_NAME || ', '||FIRST_NAME||' '||MIDDLE_NAME  AS NAME  "+
                                                     "  FROM MED_USER_REF M, EMPLOYEES_VW E   "+
                                                     " WHERE IS_OBSOLETE IS NULL AND M.EMPLOYEE_ID=E.EMPLOYEE_ID  "+
                                                     " ORDER BY NAME ";
                                    
                                    System.out.println("getUserMaintList query :  "+sqlQuery);
                                    
                                    stmt = orclConn.prepareStatement(sqlQuery);
                                    stmt.clearParameters();  
                                    rs = stmt.executeQuery();
                                    
                                    while (rs.next())
                                    {
                                        mVo = new UserMaintVo();
                                        mVo.setEmployeeId(rs.getString(1));
                                        mVo.setEmployeeName(rs.getString(2));
                                     
                                     
                                        userMaintList.add(mVo);
                                    }
                                    rs.close();
                                    stmt.close();
                                    orclConn.close();
                                    }
                                    catch(SQLException se) {
                                        System.out.println("MedicalConsultsImpl.getUserMaintList: " + se.toString());
                                        throw new DataAccessException("getUserMaintList", se);
                                        
                                    }finally{
                                        try {
                                            if (rs != null)
                                                    rs.close();
                                            if (stmt != null)
                                                    stmt.close();
                                            if (orclConn != null)
                                                    orclConn.close();
                                    } catch (SQLException e) {
                                            System.out.println("MedicalConsultsImpl.getUserMaintList: "
                                                            + e.toString());
                                            throw new DataAccessException("getUserMaintList", e);
                                    }
                                        
                                    }
                                   
                                    return userMaintList;
        
    }
   
    
    //edit  medical user  maintenance
    
    public int editUserMaintenance(UserMaintVo dVo){
        
                                
                                int result = 0;
                                Connection orclConn = null;
                                PreparedStatement stmt = null;
                                try {
                                    MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory(); 
                                    
                                        orclConn = orclFactory.createConnection();
                                                        
                                       
                                       String sqlQuery = "UPDATE MED_USER_REF    "+
                                                         "    SET  "+                                           
                                                         "    IS_OBSOLETE = ?    "+                                                    
                                                         "  WHERE  employee_id = ? ";
                            
                                        System.out.println("sqlQuery editUserMaintenance : " + sqlQuery);
                                
                                            stmt = orclConn.prepareStatement(sqlQuery);
                                            stmt.clearParameters();
                                            
                                            stmt.setString(1, "Y");  
                                            stmt.setString(2, dVo.getEmployeeId());
                               
                                            result = stmt.executeUpdate();
                                            
                                            //System.out.println("result:"+result);
                            
                                } catch (SQLException se) {
                                        if (se.getErrorCode() == 20000) {
                                                throw new RecordVersionException();
                                        }
                                        System.out.println("MedicalConsultsImpl.editUserMaintenance: " + se.toString());
                                        throw new DataAccessException("editMedicalProvider", se);
                                }  finally {
                                        try {
                                                if (stmt != null)
                                                        stmt.close();
                                        } catch (SQLException e) {
                                                System.out.println("MedicalConsultsImpl.editUserMaintenance: " + e.toString());
                                                
                                                throw new DataAccessException("editUserMaintenance", e);
                                        }
                                }
                                return result;

}

    
    
  //get Transport type maintenance
    
    public ArrayList<TransportMaintVo> getTransportMaintList()
    {
        
                                    ArrayList<TransportMaintVo>   transportMaintList = new ArrayList<TransportMaintVo>();
                                    
                                    TransportMaintVo mVo=null; 
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    ResultSet rs = null;
                                    
                                    try {
                                                  
                                    MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                    
                                        orclConn = orclFactory.createReadOnlyConnection();
                                 
                                    
                                    String sqlQuery ="SELECT TRANS_CODE,TRANS_DESC  "+
                                                     " FROM MED_TRANS_TYPE_REF      "+
                                                     " WHERE IS_OBSOLETE IS NULL    "+ 
                                                     " ORDER BY TRANS_DESC  " ;
                                    
                                    System.out.println("getTransportMaintList query :  "+sqlQuery);
                                    
                                    stmt = orclConn.prepareStatement(sqlQuery);
                                    stmt.clearParameters();  
                                    rs = stmt.executeQuery();
                                    
                                    while (rs.next())
                                    {
                                        mVo = new TransportMaintVo();
                                        mVo.setTransCode(rs.getString(1));
                                        mVo.setTransDesc(rs.getString(2));
                                                                         
                                        transportMaintList.add(mVo);
                                    }
                                    rs.close();
                                    stmt.close();
                                    orclConn.close();
                                    }
                                    catch(SQLException se) {
                                        System.out.println("MedicalConsultsImpl.getTransportMaintList: " + se.toString());
                                        throw new DataAccessException("getTransportMaintList", se);
                                        
                                    }finally{
                                        try {
                                            if (rs != null)
                                                    rs.close();
                                            if (stmt != null)
                                                    stmt.close();
                                            if (orclConn != null)
                                                    orclConn.close();
                                    } catch (SQLException e) {
                                            System.out.println("MedicalConsultsImpl.getTransportMaintList: "
                                                            + e.toString());
                                            throw new DataAccessException("getTransportMaintList", e);
                                    }
                                        
                                    }
                                   
                                    return transportMaintList;
        
    }
   
    // add Transport type maintenance 
    
    public TransportMaintVo addTransportMaint(TransportMaintVo cVo)
    {

                                    int result = 0;
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory();  
                                    
                                    try {
                                       
                                            orclConn = orclFactory.createConnection();
                                          

                                            //String seqNo = referenceDao.getNextSeqNo("");
                     
                                            String sqlQuery =" INSERT INTO MED_TRANS_TYPE_REF                    "+ 
                                                             " (                                                 "+                                                   
                                                             "    TRANS_CODE,                                    "+                                                
                                                             "    TRANS_DESC                                     "+                                                                                   
                                                             " )                                                 "+ 
                                                             " VALUES " + "(?, ?)";

                                            System.out.println("addTransportMaint sqlQuery : " + sqlQuery);

                                            stmt = orclConn.prepareStatement(sqlQuery);
                                            stmt.clearParameters();
                                            
                                           // stmt.setString(1, seqNo);
                                            stmt.setString(1, cVo.getTransCode());                                                      
                                            stmt.setString(2, cVo.getTransDesc());
                                            result = stmt.executeUpdate();
                                            
                                            cVo.setResult(result);

                                    } catch (SQLException se) {
                                            System.out.println("MedicalConsultsImpl.addTransportMaint: " + se.toString());
                                            throw new DataAccessException("addTransportMaint", se);
                                    } finally {
                                            try {
                                                    if (stmt != null)
                                                            stmt.close();
                                                    if(orclFactory != null)
                                                        orclFactory.releaseConnection(orclConn);
                                                   
                                            } catch (SQLException e) {
                                                    System.out.println("MedicalConsultsImpl.addTransportMaint: " + e.toString());
                                                    throw new DataAccessException("addTransportMaint", e);
                                            }
                                    }
                                    return cVo;

                            }
    
    // edit Transport type maintenance 
    
    public int editTransportMaint(TransportMaintVo dVo){
        
        
                                    int result = 0;
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    try {
                                        MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory(); 
                                        
                                            orclConn = orclFactory.createConnection();
                                                            
                                           
                                           String sqlQuery = "UPDATE MED_TRANS_TYPE_REF    "+
                                                             "    SET  "+
                                               
                                                             "    IS_OBSOLETE = ?    "+
                                                        
                                                             "  WHERE TRANS_CODE = ? ";
                                
                                            System.out.println("sqlQuery editTransportMaint : " + sqlQuery);
                                
                                            stmt = orclConn.prepareStatement(sqlQuery);
                                            stmt.clearParameters();
                                            
                                            stmt.setString(1, dVo.getIsObsolete());  
                                            stmt.setString(2, dVo.getTransCode());
                               
                                            result = stmt.executeUpdate();
                                            
                                            //System.out.println("result:"+result);
                                
                                    } catch (SQLException se) {
                                            if (se.getErrorCode() == 20000) {
                                                    throw new RecordVersionException();
                                            }
                                            System.out.println("MedicalConsultsImpl.editTransportMaint: " + se.toString());
                                            throw new DataAccessException("editTransportMaint", se);
                                    }  finally {
                                            try {
                                                    if (stmt != null)
                                                            stmt.close();
                                            } catch (SQLException e) {
                                                    System.out.println("MedicalConsultsImpl.editTransportMaint: " + e.toString());
                                                    
                                                    throw new DataAccessException("editTransportMaint", e);
                                            }
                                    }
                                    return result;

}

    
  //get appointment location maintenance
    
    public ArrayList<ApptLocationMaintVo> getApptLocationMaintList()
    {
        
                                    ArrayList<ApptLocationMaintVo>   apptLocationMaintList = new ArrayList<ApptLocationMaintVo>();
                                    
                                    ApptLocationMaintVo mVo=null; 
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    ResultSet rs = null;
                                    
                                    try {
                                                  
                                    MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                    
                                        orclConn = orclFactory.createReadOnlyConnection();
                               
                                    
                                    String sqlQuery ="SELECT LOCATION_DESC,LOCATION_CODE "+
                                                     "  FROM MED_APPT_LOCATION_REF      "+
                                                     "  WHERE IS_OBSOLETE IS NULL       "+
                                                     "  ORDER BY LOCATION_DESC " ;
                                    
                                    
                                    System.out.println("getApptLocationMaintList query :  "+sqlQuery);
                                    
                                    stmt = orclConn.prepareStatement(sqlQuery);
                                    stmt.clearParameters();  
                                    rs = stmt.executeQuery();
                                    
                                    while (rs.next())
                                    {
                                        mVo = new ApptLocationMaintVo();
                                        mVo.setApptLocationDesc(rs.getString(1));
                                        mVo.setApptLocationCode(rs.getString(2));
                                                                         
                                        apptLocationMaintList.add(mVo);
                                    }
                                    rs.close();
                                    stmt.close();
                                    orclConn.close();
                                    }
                                    catch(SQLException se) {
                                        System.out.println("MedicalConsultsImpl.getApptLocationMaintList: " + se.toString());
                                        throw new DataAccessException("getApptLocationMaintList", se);
                                        
                                    }finally{
                                        try {
                                            if (rs != null)
                                                    rs.close();
                                            if (stmt != null)
                                                    stmt.close();
                                            if (orclConn != null)
                                                    orclConn.close();
                                    } catch (SQLException e) {
                                            System.out.println("MedicalConsultsImpl.getApptLocationMaintList: "
                                                            + e.toString());
                                            throw new DataAccessException("getApptLocationMaintList", e);
                                    }
                                        
                                    }
                                   
                                    return apptLocationMaintList;
        
    }
   
    // add appointment location maintenance 
    
    public ApptLocationMaintVo addApptLocationMaint(ApptLocationMaintVo cVo)
    {

                                    int result = 0;
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory();  
                                    
                                    try {
                                       
                                            orclConn = orclFactory.createConnection();
                                          

                                            //String seqNo = referenceDao.getNextSeqNo("");
                     
                                            String sqlQuery =" INSERT INTO MED_APPT_LOCATION_REF                    "+ 
                                                             " (                                                 "+                                                   
                                                             "    LOCATION_CODE,                                    "+                                                
                                                             "    LOCATION_DESC                                     "+                                                                                   
                                                             " )                                                 "+ 
                                                             " VALUES " + "(?, ?)";

                                            System.out.println("addApptLocationMaint sqlQuery : " + sqlQuery);

                                            stmt = orclConn.prepareStatement(sqlQuery);
                                            stmt.clearParameters();
                                            
                                           // stmt.setString(1, seqNo);
                                            stmt.setString(1, cVo.getApptLocationCode());                                                      
                                            stmt.setString(2, cVo.getApptLocationDesc());
                                            result = stmt.executeUpdate();
                                            
                                            cVo.setResult(result);

                                    } catch (SQLException se) {
                                            System.out.println("MedicalConsultsImpl.addApptLocationMaint: " + se.toString());
                                            throw new DataAccessException("addApptLocationMaint", se);
                                    } finally {
                                            try {
                                                    if (stmt != null)
                                                            stmt.close();
                                                    if(orclFactory != null)
                                                        orclFactory.releaseConnection(orclConn);
                                                   
                                            } catch (SQLException e) {
                                                    System.out.println("MedicalConsultsImpl.addApptLocationMaint: " + e.toString());
                                                    throw new DataAccessException("addApptLocationMaint", e);
                                            }
                                    }
                                    return cVo;

                            }
    
    // edit  appointment location maintenance 
    
    public int editApptLocationMaint(ApptLocationMaintVo dVo){
        
        
                                    int result = 0;
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    try {
                                        MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory(); 
                                        
                                            orclConn = orclFactory.createConnection();
                                                            
                                           
                                           String sqlQuery = "UPDATE MED_APPT_LOCATION_REF    "+
                                                             "    SET  "+
                                               
                                                             "    IS_OBSOLETE = ?    "+
                                                        
                                                             "  WHERE LOCATION_CODE = ? ";
                                
                                            System.out.println("sqlQuery editApptLocationMaint : " + sqlQuery);
                                
                                            stmt = orclConn.prepareStatement(sqlQuery);
                                            stmt.clearParameters();
                                            
                                            stmt.setString(1, dVo.getIsObsolete());  
                                            stmt.setString(2, dVo.getApptLocationCode());
                               
                                            result = stmt.executeUpdate();
                                            
                                            //System.out.println("result:"+result);
                                
                                    } catch (SQLException se) {
                                            if (se.getErrorCode() == 20000) {
                                                    throw new RecordVersionException();
                                            }
                                            System.out.println("MedicalConsultsImpl.editApptLocationMaint: " + se.toString());
                                            throw new DataAccessException("editApptLocationMaint", se);
                                    }  finally {
                                            try {
                                                    if (stmt != null)
                                                            stmt.close();
                                            } catch (SQLException e) {
                                                    System.out.println("MedicalConsultsImpl.editApptLocationMaint: " + e.toString());
                                                    
                                                    throw new DataAccessException("editApptLocationMaint", e);
                                            }
                                    }
                                    return result;

}

    
 //get appointment Type maintenance
    
    public ArrayList<ApptTypeMaintVo> getApptTypeMaintList()
    {
        
                                    ArrayList<ApptTypeMaintVo>   apptTypeMaintList = new ArrayList<ApptTypeMaintVo>();
                                    
                                    ApptTypeMaintVo mVo=null; 
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    ResultSet rs = null;
                                    
                                    try {
                                                  
                                    MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                    
                                        orclConn = orclFactory.createReadOnlyConnection();
                                    
                                    String sqlQuery ="SELECT STATUS_CODE,STATUS_DESC "+
                                                     " FROM MED_APPT_STATUS_REF  "+
                                                     " WHERE IS_OBSOLETE IS NULL  "+
                                                     " ORDER BY STATUS_DESC";
                                    
                                    
                                    System.out.println("getApptTypeMaintList query :  "+sqlQuery);
                                    
                                    stmt = orclConn.prepareStatement(sqlQuery);
                                    stmt.clearParameters();  
                                    rs = stmt.executeQuery();
                                    
                                    while (rs.next())
                                    {
                                        mVo = new ApptTypeMaintVo();
                                        mVo.setStatusCode(rs.getString(1));
                                        mVo.setStatusDesc(rs.getString(2));
                                                                         
                                        apptTypeMaintList.add(mVo);
                                    }
                                    rs.close();
                                    stmt.close();
                                    orclConn.close();
                                    }
                                    catch(SQLException se) {
                                        System.out.println("MedicalConsultsImpl.getApptTypeMaintList: " + se.toString());
                                        throw new DataAccessException("getApptTypeMaintList", se);
                                        
                                    }finally{
                                        try {
                                            if (rs != null)
                                                    rs.close();
                                            if (stmt != null)
                                                    stmt.close();
                                            if (orclConn != null)
                                                    orclConn.close();
                                    } catch (SQLException e) {
                                            System.out.println("MedicalConsultsImpl.getApptTypeMaintList: "
                                                            + e.toString());
                                            throw new DataAccessException("getApptTypeMaintList", e);
                                    }
                                        
                                    }
                                   
                                    return apptTypeMaintList;
        
    }
   
    // add appointment Type maintenance 
    
    public ApptTypeMaintVo addApptTypeMaint(ApptTypeMaintVo cVo)
    {

                                    int result = 0;
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory();  
                                    
                                    try {
                                       
                                            orclConn = orclFactory.createConnection();
                                          

                                            //String seqNo = referenceDao.getNextSeqNo("");
                     
                                            String sqlQuery =" INSERT INTO MED_APPT_STATUS_REF                   "+ 
                                                             " (                                                 "+                                                   
                                                             "    STATUS_CODE,                                    "+                                                
                                                             "    STATUS_DESC                                     "+                                                                                   
                                                             " )                                                 "+ 
                                                             " VALUES " + "(?, ?)";

                                            System.out.println("addApptTypeMaint sqlQuery : " + sqlQuery);

                                            stmt = orclConn.prepareStatement(sqlQuery);
                                            stmt.clearParameters();
                                            
                                           // stmt.setString(1, seqNo);
                                            stmt.setString(1, cVo.getStatusCode());                                                      
                                            stmt.setString(2, cVo.getStatusDesc());
                                            result = stmt.executeUpdate();
                                            
                                            cVo.setResult(result);

                                    } catch (SQLException se) {
                                            System.out.println("MedicalConsultsImpl.addApptTypeMaint: " + se.toString());
                                            throw new DataAccessException("addApptTypeMaint", se);
                                    } finally {
                                            try {
                                                    if (stmt != null)
                                                            stmt.close();
                                                    if(orclFactory != null)
                                                        orclFactory.releaseConnection(orclConn);
                                                   
                                            } catch (SQLException e) {
                                                    System.out.println("MedicalConsultsImpl.addApptTypeMaint: " + e.toString());
                                                    throw new DataAccessException("addApptTypeMaint", e);
                                            }
                                    }
                                    return cVo;

                            }
    
    // edit  appointment Type maintenance 
    
    public int editApptTypeMaint(ApptTypeMaintVo dVo){
        
        
                                    int result = 0;
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    try {
                                        MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory(); 
                                        
                                            orclConn = orclFactory.createConnection();
                                                            
                                           
                                           String sqlQuery = "UPDATE MED_APPT_STATUS_REF    "+
                                                             "    SET                       "+                                              
                                                             "    IS_OBSOLETE = ?           "+                                                        
                                                             "  WHERE STATUS_CODE = ? ";
                                
                                            System.out.println("sqlQuery editApptTypeMaint : " + sqlQuery);
                                
                                            stmt = orclConn.prepareStatement(sqlQuery);
                                            stmt.clearParameters();
                                            
                                            stmt.setString(1, dVo.getIsObsolete());  
                                            stmt.setString(2, dVo.getStatusCode());
                               
                                            result = stmt.executeUpdate();
                                            
                                            //System.out.println("result:"+result);
                                
                                    } catch (SQLException se) {
                                            if (se.getErrorCode() == 20000) {
                                                    throw new RecordVersionException();
                                            }
                                            System.out.println("MedicalConsultsImpl.editApptTypeMaint: " + se.toString());
                                            throw new DataAccessException("editApptTypeMaint", se);
                                    }  finally {
                                            try {
                                                    if (stmt != null)
                                                            stmt.close();
                                            } catch (SQLException e) {
                                                    System.out.println("MedicalConsultsImpl.editApptTypeMaint: " + e.toString());
                                                    
                                                    throw new DataAccessException("editApptTypeMaint", e);
                                            }
                                    }
                                    return result;

}
    
    
    //get consultant recommendation maintenance
       
       public ArrayList<ConsultantsRecMaintVo> getConsultantsRecMaintList()
       {
           
                                       ArrayList<ConsultantsRecMaintVo>   consultantsRecMaintList = new ArrayList<ConsultantsRecMaintVo>();
                                       
                                       ConsultantsRecMaintVo mVo=null; 
                                       Connection orclConn = null;
                                       PreparedStatement stmt = null;
                                       ResultSet rs = null;
                                       
                                       try {
                                                     
                                       MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                       
                                           orclConn = orclFactory.createReadOnlyConnection();
                                    
                                       String sqlQuery ="SELECT IS_OBSOLETE,            "+
                                                        "  RECOMMEND_CODE,              "+
                                                        "  RECOMMEND_DESC,              "+
                                                        "  RECOMMEND_SHORT_DESC         "+
                                                        " FROM MED_CONSULTANTS_REC_REF  "+
                                                        " ORDER BY RECOMMEND_SHORT_DESC ";
                                       
                                       
                                       System.out.println("getConsultantsRecMaintList query :  "+sqlQuery);
                                       
                                       stmt = orclConn.prepareStatement(sqlQuery);
                                       stmt.clearParameters();  
                                       rs = stmt.executeQuery();
                                       
                                       while (rs.next())
                                       {
                                           mVo = new ConsultantsRecMaintVo();
                                           mVo.setIsObsolete(rs.getString(1));
                                           mVo.setRecommendCode(rs.getString(2));
                                           mVo.setRecommendDesc(rs.getString(3));
                                           mVo.setRecommendShortDesc(rs.getString(4));
                                                                            
                                           consultantsRecMaintList.add(mVo);
                                       }
                                       rs.close();
                                       stmt.close();
                                       orclConn.close();
                                       }
                                       catch(SQLException se) {
                                           System.out.println("MedicalConsultsImpl.getConsultantsRecMaintList: " + se.toString());
                                           throw new DataAccessException("getConsultantsRecMaintList", se);
                                           
                                       }finally{
                                           try {
                                               if (rs != null)
                                                       rs.close();
                                               if (stmt != null)
                                                       stmt.close();
                                               if (orclConn != null)
                                                       orclConn.close();
                                       } catch (SQLException e) {
                                               System.out.println("MedicalConsultsImpl.getConsultantsRecMaintList: "
                                                               + e.toString());
                                               throw new DataAccessException("getConsultantsRecMaintList", e);
                                       }
                                           
                                       }
                                      
                                       return consultantsRecMaintList;
           
       }
      
       // add consultant recommendation maintenance
       
       public ConsultantsRecMaintVo addConsultantsRecMaint(ConsultantsRecMaintVo cVo)
       {

                                       int result = 0;
                                       Connection orclConn = null;
                                       PreparedStatement stmt = null;
                                       MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory();  
                                       
                                       try {
                                          
                                               orclConn = orclFactory.createConnection();
                                             

                                               //String seqNo = referenceDao.getNextSeqNo("");
                        
                                               String sqlQuery =" INSERT INTO MED_CONSULTANTS_REC_REF                   "+ 
                                                                " (                                                 "+                                                   
                                                                "   RECOMMEND_CODE,                                 "+                                                
                                                                "   RECOMMEND_SHORT_DESC                            "+  
                                                                "   RECOMMEND_DESC,                                 "+                                                
                                                                "   IS_OBSOLETE                                     "+                                                                                                                                                                  
                                                                " )                                                 "+ 
                                                                " VALUES " + "(?,?,?,?)";

                                               System.out.println("addConsultantsRecMaint sqlQuery : " + sqlQuery);

                                               stmt = orclConn.prepareStatement(sqlQuery);
                                               stmt.clearParameters();
                                               
                                              // stmt.setString(1, seqNo);
                                               stmt.setString(1, cVo.getRecommendCode());                                                      
                                               stmt.setString(2, cVo.getRecommendShortDesc());
                                               stmt.setString(3, cVo.getRecommendDesc());                                                      
                                               stmt.setString(4, cVo.getIsObsolete());
                                               result = stmt.executeUpdate();
                                               
                                               cVo.setResult(result);

                                       } catch (SQLException se) {
                                               System.out.println("MedicalConsultsImpl.addConsultantsRecMaint: " + se.toString());
                                               throw new DataAccessException("addConsultantsRecMaint", se);
                                       } finally {
                                               try {
                                                       if (stmt != null)
                                                               stmt.close();
                                                       if(orclFactory != null)
                                                           orclFactory.releaseConnection(orclConn);
                                                      
                                               } catch (SQLException e) {
                                                       System.out.println("MedicalConsultsImpl.addConsultantsRecMaint: " + e.toString());
                                                       throw new DataAccessException("addConsultantsRecMaint", e);
                                               }
                                       }
                                       return cVo;

                               }
       
       // edit  consultant recommendation maintenance
       
       public int editConsultantsRecMaint(ConsultantsRecMaintVo dVo){
           
           
                                       int result = 0;
                                       Connection orclConn = null;
                                       PreparedStatement stmt = null;
                                       try {
                                           MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory(); 
                                           
                                               orclConn = orclFactory.createConnection();
                                                               
                                              
                                              String sqlQuery = "UPDATE MED_CONSULTANTS_REC_REF    "+
                                                                "    SET                       "+                                              
                                                                "    IS_OBSOLETE = ?           "+                                                        
                                                                "  WHERE RECOMMEND_CODE = ? ";
                                   
                                               System.out.println("sqlQuery editConsultantsRecMaint : " + sqlQuery);
                                   
                                               stmt = orclConn.prepareStatement(sqlQuery);
                                               stmt.clearParameters();
                                               
                                               stmt.setString(1, dVo.getIsObsolete());  
                                               stmt.setString(2, dVo.getRecommendCode());
                                  
                                               result = stmt.executeUpdate();
                                               
                                               //System.out.println("result:"+result);
                                   
                                       } catch (SQLException se) {
                                               if (se.getErrorCode() == 20000) {
                                                       throw new RecordVersionException();
                                               }
                                               System.out.println("MedicalConsultsImpl.editConsultantsRecMaint: " + se.toString());
                                               throw new DataAccessException("editConsultantsRecMaint", se);
                                       }  finally {
                                               try {
                                                       if (stmt != null)
                                                               stmt.close();
                                               } catch (SQLException e) {
                                                       System.out.println("MedicalConsultsImpl.editConsultantsRecMaint: " + e.toString());
                                                       
                                                       throw new DataAccessException("editConsultantsRecMaint", e);
                                               }
                                       }
                                       return result;

   }
  
       
       
       //get review decision maintenance
          
          public ArrayList<ReviewDecisionVo> getReviewDecisionList()
          {
              
                                          ArrayList<ReviewDecisionVo>   reviewDecisionList = new ArrayList<ReviewDecisionVo>();
                                          
                                          ReviewDecisionVo mVo=null; 
                                          Connection orclConn = null;
                                          PreparedStatement stmt = null;
                                          ResultSet rs = null;
                                          
                                          try {
                                                        
                                          MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                          
                                              orclConn = orclFactory.createReadOnlyConnection();
                                          
                                          String sqlQuery ="SELECT IS_OBSOLETE,         "+
                                                           "  REVIEW_CODE,              "+
                                                           "  REVIEW_DESC,              "+
                                                           "  REVIEW_SHORT_DESC         "+
                                                           " FROM MED_UTILIZATION_MGMT_REF "+
                                                           " ORDER BY REVIEW_SHORT_DESC ";
                                          
                                          
                                          System.out.println("getReviewDecision query :  "+sqlQuery);
                                          
                                          stmt = orclConn.prepareStatement(sqlQuery);
                                          stmt.clearParameters();  
                                          rs = stmt.executeQuery();
                                          
                                          while (rs.next())
                                          {
                                              mVo = new ReviewDecisionVo();
                                              mVo.setIsObsolete(rs.getString(1));
                                              mVo.setReviewCode(rs.getString(2));
                                              mVo.setReviewDesc(rs.getString(3));
                                              mVo.setReviewShortDesc(rs.getString(4));
                                                                               
                                              reviewDecisionList.add(mVo);
                                          }
                                          rs.close();
                                          stmt.close();
                                          orclConn.close();
                                          }
                                          catch(SQLException se) {
                                              System.out.println("MedicalConsultsImpl.getReviewDecisionList: " + se.toString());
                                              throw new DataAccessException("getReviewDecisionList", se);
                                              
                                          }finally{
                                              try {
                                                  if (rs != null)
                                                          rs.close();
                                                  if (stmt != null)
                                                          stmt.close();
                                                  if (orclConn != null)
                                                          orclConn.close();
                                          } catch (SQLException e) {
                                                  System.out.println("MedicalConsultsImpl.getReviewDecisionList: "
                                                                  + e.toString());
                                                  throw new DataAccessException("getReviewDecisionList", e);
                                          }
                                              
                                          }
                                         
                                          return reviewDecisionList;
              
          }
         
          // add review decision maintenance
          
          public ReviewDecisionVo addReviewDecision(ReviewDecisionVo cVo)
          {
                                          int result = 0;
                                          Connection orclConn = null;
                                          PreparedStatement stmt = null;
                                          MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory();  
                                          
                                          try {
                                             
                                                  orclConn = orclFactory.createConnection();
                                                

                                                  //String seqNo = referenceDao.getNextSeqNo("");
                           
                                                  String sqlQuery =" INSERT INTO MED_UTILIZATION_MGMT_REF            "+ 
                                                                   " (                                              "+                                                   
                                                                   "   REVIEW_CODE,                                 "+                                                
                                                                   "   REVIEW_SHORT_DESC                            "+  
                                                                   "   REVIEW_DESC,                                 "+                                                
                                                                   "   IS_OBSOLETE                                  "+                                                                                                                                                                  
                                                                   " )                                              "+ 
                                                                   " VALUES " + "(?,?,?,?)";

                                                  System.out.println("addReviewDecision sqlQuery : " + sqlQuery);

                                                  stmt = orclConn.prepareStatement(sqlQuery);
                                                  stmt.clearParameters();
                                                  
                                                 // stmt.setString(1, seqNo);
                                                  stmt.setString(1, cVo.getReviewCode());                                                      
                                                  stmt.setString(2, cVo.getReviewShortDesc());
                                                  stmt.setString(3, cVo.getReviewDesc());                                                      
                                                  stmt.setString(4, cVo.getIsObsolete());
                                                  result = stmt.executeUpdate();
                                                  
                                                  cVo.setResult(result);

                                          } catch (SQLException se) {
                                                  System.out.println("MedicalConsultsImpl.addReviewDecision: " + se.toString());
                                                  throw new DataAccessException("addReviewDecision", se);
                                          } finally {
                                                  try {
                                                          if (stmt != null)
                                                                  stmt.close();
                                                          if(orclFactory != null)
                                                              orclFactory.releaseConnection(orclConn);
                                                         
                                                  } catch (SQLException e) {
                                                          System.out.println("MedicalConsultsImpl.addReviewDecision: " + e.toString());
                                                          throw new DataAccessException("addReviewDecision", e);
                                                  }
                                          }
                                          return cVo;

                                  }
          
          // edit review decision maintenance
          
          public int editReviewDecision(ReviewDecisionVo dVo){
              
              
                                          int result = 0;
                                          Connection orclConn = null;
                                          PreparedStatement stmt = null;
                                          try {
                                              MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory(); 
                                              
                                                  orclConn = orclFactory.createConnection();
                                                                  
                                                 
                                                 String sqlQuery = "UPDATE MED_UTILIZATION_MGMT_REF     "+
                                                                   "    SET                       "+                                              
                                                                   "    IS_OBSOLETE = ?           "+                                                        
                                                                   "  WHERE REVIEW_CODE = ? ";
                                      
                                                  System.out.println("sqlQuery editReviewDecision : " + sqlQuery);
                                      
                                                  stmt = orclConn.prepareStatement(sqlQuery);
                                                  stmt.clearParameters();
                                                  
                                                  stmt.setString(1, dVo.getIsObsolete());  
                                                  stmt.setString(2, dVo.getReviewCode());
                                     
                                                  result = stmt.executeUpdate();
                                                  
                                                  //System.out.println("result:"+result);
                                      
                                          } catch (SQLException se) {
                                                  if (se.getErrorCode() == 20000) {
                                                          throw new RecordVersionException();
                                                  }
                                                  System.out.println("MedicalConsultsImpl.editReviewDecision: " + se.toString());
                                                  throw new DataAccessException("editReviewDecision", se);
                                          }  finally {
                                                  try {
                                                          if (stmt != null)
                                                                  stmt.close();
                                                  } catch (SQLException e) {
                                                          System.out.println("MedicalConsultsImpl.editReviewDecision: " + e.toString());
                                                          
                                                          throw new DataAccessException("editReviewDecision", e);
                                                  }
                                          }
                                          return result;

      }
   
 
          
    public ArrayList<MedicalConsultsVo> getConsultationsHistory()
    {
        
                                    ArrayList<MedicalConsultsVo>   reportList = new ArrayList<MedicalConsultsVo>();
                                    
                                    MedicalConsultsVo mVo=null; 
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    ResultSet rs = null;
                                    
                                    try {
                                                  
                                    MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                    
                                        orclConn = orclFactory.createReadOnlyConnection();
                                        
                                        String sqlQuery = "     SELECT UNO#,                    "+
                                                            "   REQ_DATE,                       "+
                                                            "   REQ_TYPE,                       "+
                                                            "   REQ_INST_CODE,                  "+
                                                            "   REQ_CONSULTATION_CODE,          "+
                                                            "   REVIEW_DECISION_CODE,           "+
                                                            "   APPT_STATUS_CODE,               "+
                                                            "   CONSULTANTS_REC_CODE,           "+
                                                            "   UM_REQ_TYPE,                    "+
                                                            "   UM_REQ_CONSULTATION_CODE,       "+
                                                            "   RECORD_VERSION                  "+
                                                            " FROM                              "+
                                                            "   MED_CONSULTATIONS               "+
                                                            " WHERE                             "+
                                                            "   UNO# = '340340'                 "+
                                                            " ORDER BY REQ_DATE DESC ";
                                    
                                   
                                    
                                    System.out.println("getConsultationsHistory query :  "+sqlQuery);
                                    
                                    stmt = orclConn.prepareStatement(sqlQuery);
                                    stmt.clearParameters();  
                                    rs = stmt.executeQuery();
                                    
                                    while (rs.next())
                                    {
                                        mVo = new MedicalConsultsVo();           
                                        mVo.setUno(rs.getString(1));
                                        mVo.setReqDate(rs.getString(2));
                                        mVo.setReqType(rs.getString(3));
                                        mVo.setReqInstCode(rs.getString(4));
                                        mVo.setReqConsultationCode(rs.getString(5));
                                        mVo.setReviewDecisionCode(rs.getString(6));
                                        mVo.setApptStatusCode(rs.getString(7));
                                        mVo.setConsultantsRecCode(rs.getString(8));
                                        mVo.setUmReqType(rs.getString(9));
                                        mVo.setUmReqConsultationCode(rs.getString(10));
                                        mVo.setRecordVersion(rs.getString(11));
                                      
                                     
                                        reportList.add(mVo);
                                    }
                                    rs.close();
                                    stmt.close();
                                    orclConn.close();
                                    }
                                    catch(SQLException se) {
                                        System.out.println("MedicalConsultsImpl.getConsultationsHistory: " + se.toString());
                                        throw new DataAccessException("getConsultationsHistory", se);
                                        
                                    }finally{
                                        try {
                                            if (rs != null)
                                                    rs.close();
                                            if (stmt != null)
                                                    stmt.close();
                                            if (orclConn != null)
                                                    orclConn.close();
                                    } catch (SQLException e) {
                                            System.out.println("MedicalConsultsImpl.getConsultationsHistory: "
                                                            + e.toString());
                                            throw new DataAccessException("getConsultationsHistory", e);
                                    }
                                        
                                    }
                                   
                                    return reportList;
        
    }
    
    
 
    
    public ConsultEntryVo addConsultations(ConsultEntryVo cVo)
    {

                                    int result = 0;
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory();  
                                    
                                    try {
                                       
                                            orclConn = orclFactory.createConnection();
                                          

                                            //String seqNo = referenceDao.getNextSeqNo("");
                     
                                            String sqlQuery =" INSERT INTO MED_CONSULTATIONS                      "+ 
                                                             " ( "+                                                   
                                                             "    UNO#,                                           "+
                                                             "    REQ_DATE,                                       "+
                                                             "    REQ_INST_CODE,                                  "+
                                                             "    REQ_BY,                                         "+
                                                             "    MEDICAL_DIRECTOR,                               "+
                                                             "    REQ_CONSULTATION_CODE,                          "+
                                                             "    REQ_TYPE,                                       "+
                                                             "    DIAGNOSIS,                                      "+
                                                             "    MEDICAL_HISTORY,                                "+
                                                             "    PHYSICAL_HISTORY,                               "+
                                                             "    LABWORK,                                        "+
                                                             "    MEDICATIONS,                                    "+
                                                             "    REQ_COMMENTS,                                   "+
                                                             "    APPT_STATUS_CODE,                               "+
                                                             "    ENTRY_DATE,                                     "+
                                                             "    USER_ID,                                        "+
                                                             "     RECORD_VERSION                                 "+                                                     
                                                             " )                                                  "+ 
                                                             " VALUES " + "(?, ?,?, ?,?,sysdate,?,sysdate,?,1)";

                                            System.out.println("addConsultations sqlQuery : " + sqlQuery);

                                            stmt = orclConn.prepareStatement(sqlQuery);
                                            stmt.clearParameters();
                                            
                                           // stmt.setString(1, seqNo);
                                            stmt.setString(1, cVo.getUno());                                                      
                                            stmt.setString(2, cVo.getRequestDate());
                                            stmt.setString(3, cVo.getRequestInstCode());                                                  
                                            stmt.setString(4, cVo.getRequestBy());
                                            stmt.setString(5, cVo.getMedicalDirector());
                                            stmt.setString(6, cVo.getRequestConsultationCode());
                                            stmt.setString(7, cVo.getRequestType());
                                            stmt.setString(8, cVo.getDiagnosis());
                                            stmt.setString(9, cVo.getMedicalHistory());
                                            stmt.setString(10, cVo.getPhysicalHistory());
                                            stmt.setString(11, cVo.getLabWork());
                                            stmt.setString(12, cVo.getMedications());
                                            stmt.setString(13, cVo.getRequestComments());
                                            stmt.setString(14, cVo.getApptStatusCode());
                                            stmt.setString(15, cVo.getEntryDate());
                                            stmt.setString(16, cVo.getUserId());
                                            stmt.setString(17, cVo.getRecordVersion());

                                            result = stmt.executeUpdate();
                                            
                                            cVo.setResult(result);

                                    } catch (SQLException se) {
                                            System.out.println("MedicalConsultsImpl.addConsultations: " + se.toString());
                                            throw new DataAccessException("addConsultations", se);
                                    } finally {
                                            try {
                                                    if (stmt != null)
                                                            stmt.close();
                                                    if(orclFactory != null)
                                                        orclFactory.releaseConnection(orclConn);
                                                   
                                            } catch (SQLException e) {
                                                    System.out.println("MedicalConsultsImpl.addConsultations: " + e.toString());
                                                    throw new DataAccessException("addConsultations", e);
                                            }
                                    }
                                    return cVo;

                            }
                            
    
    public ArrayList<MedicalReportsVo> getSundownInstitutions()
    {
        
                                    ArrayList<MedicalReportsVo>   reportList = new ArrayList<MedicalReportsVo>();
                                    
                                    MedicalReportsVo mVo=null; 
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    ResultSet rs = null;
                                    
                                    try {
                                                  
                                    MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                    
                                        orclConn = orclFactory.createReadOnlyConnection();
                                    
                                    String sqlQuery = " SELECT  "                                          +
                                                 "   M.SCRIBE_CODE AS SCRIBE_CODE,"                   +
                                                 "   S.SCRIBE_DESC AS SCRIBE_DESC,"                   +
                                                 "   M.IS_OBSOLETE "                                  +
                                                 " FROM  "                                            +
                                                 "  SUNDOWN_INSTITUTION_REF M, "                      +
                                                 "  SCRIBE_REF S "                                    +
                                                 " WHERE "                                            +
                                                 "  (M.SCRIBE_CODE= S.SCRIBE_CODE) AND "              +
                                                 "  (M.IS_OBSOLETE != 'Y' OR M.IS_OBSOLETE IS NULL) " +
                                                 "  ORDER BY S.SCRIBE_DESC";
                                    
                                    System.out.println("getSundownInstitutions query :  "+sqlQuery);
                                    
                                    stmt = orclConn.prepareStatement(sqlQuery);
                                    stmt.clearParameters();  
                                    rs = stmt.executeQuery();
                                    
                                    while (rs.next())
                                    {
                                        mVo = new MedicalReportsVo();           
                                        mVo.setScribeCode(rs.getString(1));
                                        mVo.setRequestInst(rs.getString(2));
                                     
                                        reportList.add(mVo);
                                    }
                                    rs.close();
                                    stmt.close();
                                    orclConn.close();
                                    }
                                    catch(SQLException se) {
                                        System.out.println("MedicalConsultsImpl.getSundownInstitutions: " + se.toString());
                                        throw new DataAccessException("getSundownInstitutions", se);
                                        
                                    }finally{
                                        try {
                                            if (rs != null)
                                                    rs.close();
                                            if (stmt != null)
                                                    stmt.close();
                                            if (orclConn != null)
                                                    orclConn.close();
                                    } catch (SQLException e) {
                                            System.out.println("MedicalConsultsImpl.getSundownInstitutions: "
                                                            + e.toString());
                                            throw new DataAccessException("getSundownInstitutions", e);
                                    }
                                        
                                    }
                                   
                                    return reportList;
        
    }
    public ConsultEntryVo addUmDecision(ConsultEntryVo cVo)
    {

                                    int result = 0;
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory();  
                                    
                                    try {
                                       
                                            orclConn = orclFactory.createConnection();
                                          
                                     
                                    
                                           
                                            //String seqNo = referenceDao.getNextSeqNo("");
                     
                                            String sqlQuery =" UPDATE  MED_CONSULTATIONS         "+
                                                             "  SET  (                           "+                                                
                                                             "  REVIEW_DECISION_CODE = ?,        "+ 
                                                             "  REVIEW_AUTHORITY     = ? ,       "+ 
                                                             "  APPROVAL_NUMBER      = ? ,       "+ 
                                                             "  REVIEW_COMMENTS      = ? ,       "+ 
                                                             "  APPT_STATUS_CODE     = ? ,       "+ 
                                                             "  UM_ENTERED_BY        = ? ,       "+ 
                                                             "  UM_ENTERED_DT        = ? ,       "+ 
                                                             "  UM_REQ_TYPE          = ? ,       "+                                              
                                                             "  RECORD_VERSION       = ? ,       "+                                            
                                                             "  UM_REQ_CONSULTATION_CODE = ?     "+ 
                                                             " WHERE UNO#   = ?                  "+ 
                                                             "  AND TO_CHAR(REQ_DATE,'mm/dd/yyyy')  = ?   "+ 
                                                             "  AND REQ_CONSULTATION_CODE    = ?  ";                                               
                                                           

                                            System.out.println("addUmDecision sqlQuery : " + sqlQuery);

                                            stmt = orclConn.prepareStatement(sqlQuery);
                                            stmt.clearParameters();
                                            
                                           // stmt.setString(1, seqNo);
                                            stmt.setString(1, cVo.getUno());                                                      
                                            stmt.setString(2, cVo.getRequestDate());
                                            stmt.setString(3, cVo.getRequestInstCode());                                                  
                                            stmt.setString(4, cVo.getRequestBy());
                                            stmt.setString(5, cVo.getMedicalDirector());
                                            stmt.setString(6, cVo.getRequestConsultationCode());
                                            stmt.setString(7, cVo.getRequestType());
                                            stmt.setString(8, cVo.getDiagnosis());
                                            stmt.setString(9, cVo.getMedicalHistory());
                                            stmt.setString(10, cVo.getPhysicalHistory());
                                            stmt.setString(11, cVo.getLabWork());
                                            stmt.setString(12, cVo.getMedications());
                                            stmt.setString(13, cVo.getRequestComments());
                                            stmt.setString(14, cVo.getApptStatusCode());
                                            stmt.setString(15, cVo.getEntryDate());
                                            stmt.setString(16, cVo.getUserId());
                                            stmt.setString(17, cVo.getRecordVersion());

                                            result = stmt.executeUpdate();
                                            
                                            cVo.setResult(result);

                                    } catch (SQLException se) {
                                            System.out.println("MedicalConsultsImpl.addUmDecision: " + se.toString());
                                            throw new DataAccessException("addUmDecision", se);
                                    } finally {
                                            try {
                                                    if (stmt != null)
                                                            stmt.close();
                                                    if(orclFactory != null)
                                                        orclFactory.releaseConnection(orclConn);
                                                   
                                            } catch (SQLException e) {
                                                    System.out.println("MedicalConsultsImpl.addUmDecision: " + e.toString());
                                                    throw new DataAccessException("addUmDecision", e);
                                            }
                                    }
                                    return cVo;

                            }

    public AppointmentVo addAppointment(AppointmentVo cVo)
    {

                                    int result = 0;
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    MedicalConsultsDaoFactory orclFactory = this.getMedicalConsultsDaoFactory();  
                                    
                                    try {
                                       
                                            orclConn = orclFactory.createConnection();
                                      //  //String seqNo = referenceDao.getNextSeqNo("");
                     
                                            String sqlQuery =" UPDATE  MED_CONSULTATIONS        "+
                                                             "  SET  (                          "+                                                
                                                             "  APPT_STATUS_CODE    = ?,        "+ 
                                                             "  APPT_TIME           = ? ,       "+ 
                                                             "  APPT_LOCATION_CODE  = ? ,       "+ 
                                                             "  APPT_COMMENTS       = ? ,       "+ 
                                                             "  TRANS_DATE          = ? ,       "+ 
                                                             "  TRANS_TYPE_CODE     = ? ,       "+ 
                                                             "  TRANS_COMMENTS      = ? ,       "+ 
                                                             "  PROVIDER_CODE       = ? ,       "+                                              
                                                             "  APPT_ENTERED_BY     = ? ,       "+                                            
                                                             "  RECORD_VERSION      = ?   ,     "+ 
                                                             "  APPT_ENTERED_DT     = ?         "+
                                                             " WHERE UNO#           = ?         "+ 
                                                             "  AND TO_CHAR(REQ_DATE,'mm/dd/yyyy')  = ?   "+ 
                                                             "  AND REQ_CONSULTATION_CODE    = ?  ";                                               
                                                           

                                            System.out.println("addAppointment sqlQuery : " + sqlQuery);

                                            stmt = orclConn.prepareStatement(sqlQuery);
                                            stmt.clearParameters();
                                            
                                           // stmt.setString(1, seqNo);
                                            stmt.setString(1, cVo.getApptStatusCode());                                                      
                                            stmt.setString(2, cVo.getApptTime());
                                            stmt.setString(3, cVo.getApptLocationCode());                                                  
                                            stmt.setString(4, cVo.getApptComments());
                                            stmt.setString(5, cVo.getTransportDate());
                                            stmt.setString(6, cVo.getTransportCode());
                                            stmt.setString(7, cVo.getTransportComments());
                                            stmt.setString(8, cVo.getProviderCode());
                                            stmt.setString(9, cVo.getEnteredBy());
                                            stmt.setString(10, cVo.getRecordVersion());
                                            stmt.setString(11, cVo.getEnteredDate());
                                            
                                            stmt.setString(12, cVo.getUno());
                                            stmt.setString(13, cVo.getRequestDate());
                                            stmt.setString(14, cVo.getRequestConsultationCode());
                                          

                                            result = stmt.executeUpdate();
                                            
                                            cVo.setResult(result);

                                    } catch (SQLException se) {
                                            System.out.println("MedicalConsultsImpl.addAppointment: " + se.toString());
                                            throw new DataAccessException("addAppointment", se);
                                    } finally {
                                            try {
                                                    if (stmt != null)
                                                            stmt.close();
                                                    if(orclFactory != null)
                                                        orclFactory.releaseConnection(orclConn);
                                                   
                                            } catch (SQLException e) {
                                                    System.out.println("MedicalConsultsImpl.addAppointment: " + e.toString());
                                                    throw new DataAccessException("addAppointment", e);
                                            }
                                    }
                                    return cVo;

                            }

    
    public ArrayList<ApptSelctionResultVo> getApptSelectionResult(){
        
    
                                            ArrayList<ApptSelctionResultVo>   apptResultList = new ArrayList<ApptSelctionResultVo>();
                                            
                                            ApptSelctionResultVo mVo=null; 
                                            Connection orclConn = null;
                                            PreparedStatement stmt = null;
                                            ResultSet rs = null ;
                                            
                                            try {
                                                          
                                            MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                            
                                                orclConn = orclFactory.createReadOnlyConnection();
        
                                                String sqlQuery = "SELECT MC.APPT_TIME,                                         "+
                                                              "        MC.PROVIDER_CODE,                                        "+
                                                              "        MP.NAME ,                                                "+
                                                              "        MC.APPT_LOCATION_CODE,                                   "+
                                                              "        AL.LOCATION_DESC ,                                       "+
                                                              "        MC.REQ_CONSULTATION_CODE,                                "+
                                                              "        MCR.CONSULTATION_DESC ,                                  "+
                                                              "        MC.TRANS_TYPE_CODE,                                      "+
                                                              "        MTR.TRANS_DESC ,                                         "+
                                                              "        MC.REQ_INST_CODE,                                        "+
                                                              "        SR.SCRIBE_DESC ,                                        "+
                                                              "        MC.UNO#,                                                 "+
                                                              "        MC.REQ_DATE ,                                               "+
                                                              "        JB.SCRIBE_CODE,                                          "+
                                                              "        MC.UM_REQ_CONSULTATION_CODE,                             "+
                                                              "        MC.RECORD_VERSION ,                                      "+
                                                              "        OD.CURR_SUPERVISION_LEVEL_CD ,                           "+
                                                              "        SS.SECURITY_DESC                                         "+
                                                              "      FROM MED_CONSULTATIONS  MC,                                "+
                                                              "        MED_PROVIDERS_REF MP ,                                   "+
                                                              "        MED_APPT_LOCATION_REF AL,                                "+
                                                              "        MED_CONSULTATIONS_REF MCR ,                              "+
                                                              "        MED_TRANS_TYPE_REF MTR,                                  "+
                                                              "        SCRIBE_REF SR ,                                          "+
                                                              "        OFFENDER_DEMOGRAPHICS OD,                                "+
                                                              "        SECURITY_REF  SS ,                                       "+
                                                              "        JMOV_BEDS JB                                             "+
                                                              "      WHERE                                                      "+
                                                              "        MC.PROVIDER_CODE=MP.PROVIDER_CODE                        "+
                                                              "        AND MC.UNO#= OD.UNO                                      "+
                                                              "        AND MC.APPT_LOCATION_CODE =AL.LOCATION_CODE              "+
                                                              "        AND MC.REQ_CONSULTATION_CODE = MCR.CONSULTATION_CODE     "+
                                                              "        AND MC.TRANS_TYPE_CODE = MTR.TRANS_CODE                  "+
                                                              "        AND MC.REQ_INST_CODE = SR.SCRIBE_CODE                    "+
                                                              "        AND OD.CURR_SUPERVISION_LEVEL_CD  =SS.SECURITY_CODE   ";
        
                                                System.out.println("getApptSelectionResult sqlQuery : " + sqlQuery);
                                                
                                                stmt = orclConn.prepareStatement(sqlQuery);
                                                stmt.clearParameters();  
                                                rs = stmt.executeQuery();
                                                System.out.println("hello:"+rs );
                                                while (rs.next())
                                                {
                                                    
                                                    System.out.println("hello");
                                                    mVo = new ApptSelctionResultVo(); 
                                                    mVo.setApptTime(rs.getString(1));
                                                    mVo.setProviderCode(rs.getString(2));
                                                    mVo.setProviderName(rs.getString(3));
                                                    mVo.setApptLocationCode(rs.getString(4));
                                                    mVo.setApptLocationDesc(rs.getString(5));
                                                    mVo.setReqConsultationCode(rs.getString(6));
                                                    mVo.setReqConsultationDesc(rs.getString(7));
                                                    mVo.setTransportCode(rs.getString(8));
                                                    mVo.setTransportDesc(rs.getString(9));
                                                    mVo.setScribeCode(rs.getString(10));
                                                    mVo.setScribeDesc(rs.getString(11));
                                                    mVo.setUno(rs.getString(12));
                                                    mVo.setRequestDate(rs.getString(13));
                                                    mVo.setScribeCode(rs.getString(14));
                                                    mVo.setUmReqConsultationCode(rs.getString(15));
                                                    mVo.setRecordVersion(rs.getString(16));
                                                    mVo.setSecurityCd(rs.getString(17));
                                                    mVo.setSecurityDesc(rs.getString(18));
                                                 
                                                    apptResultList.add(mVo);
                                                }
                                                rs.close();
                                                stmt.close();
                                                orclConn.close();
                                                }
                                                catch(SQLException se) {
                                                    System.out.println("MedicalConsultsImpl.getApptSelectionResult: " + se.toString());
                                                    throw new DataAccessException("getApptSelectionResult", se);
                                                    
                                                }finally{
                                                    try {
                                                        if (rs != null)
                                                                rs.close();
                                                        if (stmt != null)
                                                                stmt.close();
                                                        if (orclConn != null)
                                                                orclConn.close();
                                                } catch (SQLException e) {
                                                        System.out.println("MedicalConsultsImpl.getApptSelectionResult: "
                                                                        + e.toString());
                                                        throw new DataAccessException("getApptSelectionResult", e);
                                                }
                                                    
                                                }
                                       System.out.println("apptResultList:"+apptResultList);        
                                                return apptResultList;
                                                
    }
    
    
    
    
    public ArrayList<CloseConsultResultVo> getCloseConsultResult(){
        
    
                                            ArrayList<CloseConsultResultVo>   closeConsultList = new ArrayList<CloseConsultResultVo>();
                                            
                                            CloseConsultResultVo mVo=null; 
                                            Connection orclConn = null;
                                            PreparedStatement stmt = null;
                                            ResultSet rs = null ;
                                            
                                            try {
                                                          
                                            MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                            
                                                orclConn = orclFactory.createReadOnlyConnection();
                                                
                                                
                                                String sqlQuery ="SELECT MC.APPT_TIME,                                                                   "+
                                                                 "       MC.PROVIDER_CODE,                                                               "+
                                                                 "       MP.NAME ,                                                                       "+
                                                                 "       MC.APPT_LOCATION_CODE,                                                          "+
                                                                 "       AL.LOCATION_DESC ,                                                              "+
                                                                 "       MC.REQ_CONSULTATION_CODE,                                                       "+
                                                                 "       MCR.CONSULTATION_DESC ,                                                         "+
                                                                 "       MC.RECORD_VERSION,                                                              "+
                                                                 "       MC.UNO#,                                                                        "+
                                                                 "       (OD.LAST_NAME ||','||OD.FIRST_NAME  ||''|| OD.MIDDLE_NAME) OFFENDERNAME ,       "+
                                                                 "       MC.REQ_DATE                                                                     "+
                                                                 "     FROM MED_PROVIDERS_REF MP ,                                                       "+
                                                                 "       MED_APPT_LOCATION_REF AL,                                                       "+
                                                                 "       MED_CONSULTATIONS_REF MCR ,                                                     "+
                                                                 "       OFFENDER_DEMOGRAPHICS OD ,                                                      "+
                                                                 "       MED_CONSULTATIONS MC                                                            "+
                                                                 "     WHERE MC.PROVIDER_CODE       =MP.PROVIDER_CODE                                    "+
                                                                 "       AND MC.APPT_LOCATION_CODE    =AL.LOCATION_CODE                                  "+
                                                                 "       AND MC.REQ_CONSULTATION_CODE = MCR.CONSULTATION_CODE ";
                                      
        
                                                System.out.println("getCloseConsultResult sqlQuery : " + sqlQuery);
                                                
                                                stmt = orclConn.prepareStatement(sqlQuery);
                                                stmt.clearParameters();  
                                                rs = stmt.executeQuery();
                                                
                                                while (rs.next())
                                                {
                                                    mVo = new CloseConsultResultVo(); 
                                                    mVo.setApptTime(rs.getString(1));
                                                    mVo.setProviderCode(rs.getString(2));
                                                    mVo.setProviderName(rs.getString(3));
                                                    mVo.setApptLocationCode(rs.getString(4));
                                                    mVo.setApptLocationDesc(rs.getString(5));
                                                    mVo.setReqConsultationCode(rs.getString(6));
                                                    mVo.setReqConsultationDesc(rs.getString(7));
                                                    mVo.setRecordVersion(rs.getString(8));
                                                    mVo.setUno(rs.getString(9));
                                                    mVo.setOffenderName(rs.getString(10));
                                                    mVo.setRequestDate(rs.getString(11));
                                                
                                                    closeConsultList.add(mVo);
                                                }
                                                rs.close();
                                                stmt.close();
                                                orclConn.close();
                                                }
                                                catch(SQLException se) {
                                                    System.out.println("MedicalConsultsImpl.getCloseConsultResult: " + se.toString());
                                                    throw new DataAccessException("getCloseConsultResult", se);
                                                    
                                                }finally{
                                                    try {
                                                        if (rs != null)
                                                                rs.close();
                                                        if (stmt != null)
                                                                stmt.close();
                                                        if (orclConn != null)
                                                                orclConn.close();
                                                } catch (SQLException e) {
                                                        System.out.println("MedicalConsultsImpl.getCloseConsultResult: "
                                                                        + e.toString());
                                                        throw new DataAccessException("getCloseConsultResult", e);
                                                }
                                                    
                                                }
                                               
                                                return closeConsultList;
                                                
    }
    
    
    public ArrayList<ScheduledConsultVo> getScheduledConsultReport(){
        
                                            ArrayList<ScheduledConsultVo>   scheduledConsultList = new ArrayList<ScheduledConsultVo>();
                                            
                                            ScheduledConsultVo mVo=null; 
                                            Connection orclConn = null;
                                            PreparedStatement stmt = null;
                                            ResultSet rs = null ;
                                            
                                            try {
                                                          
                                            MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                            
                                                orclConn = orclFactory.createReadOnlyConnection();
                                                    
                                              String sqlQuery=" SELECT                                                                        "+
                                                               "   MC.UM_REQ_CONSULTATION_CODE,                                               "+
                                                               "   MCR.CONSULTATION_DESC ,                                                    "+
                                                               "   MC.UNO#,                                                                   "+
                                                               "  (OD.LAST_NAME ||','||OD.FIRST_NAME  ||''|| OD.MIDDLE_NAME) OFFENDERNAME ,   "+
                                                               "   MC.APPT_TIME,                                                              "+
                                                               "   MC.REVIEW_DECISION_CODE                                                    "+
                                                               "   UM.REVIEW_DESC                                                             "+
                                                               " FROM                                                                         "+
                                                               "   MED_CONSULTATIONS  ,                                                       "+
                                                               "    OFFENDER_DEMOGRAPHICS OD ,                                                "+
                                                               "    MED_CONSULTATIONS_REF MCR ,                                               "+
                                                               "    MED_UTILIZATION_MGMT_REF UM                                               "+
                                                               " WHERE                                                                        "+
                                                               "   MC.UNO#= OD.UNO                                                            "+
                                                               "   AND MC.REVIEW_DECISION_CODE  = UM.REVIEW_CODE                              "+
                                                               "   AND MC.REQ_CONSULTATION_CODE = MCR.CONSULTATION_CODE                       "+
                                                               "   AND APPT_STATUS_CODE = '101'                                               "+
                                                               "   AND (APPT_TIME)      < TO_DATE                                             "+
                                                               "   AND ((APPT_TIME)    >= TO_DATE                                             "+
                                                               "   AND req_inst_code    = ''                                                  "+
                                                               "   ORDER BY APPT_TIME ";
                                               
                                               
       
                                               String sqlQuery1="SELECT                                                                       "+
                                                                "  MC.UM_REQ_CONSULTATION_CODE,                                               "+
                                                                "  MCR.CONSULTATION_DESC ,                                                    "+
                                                                "  MC.UNO#,                                                                   "+
                                                               " (OD.LAST_NAME ||','||OD.FIRST_NAME  ||''|| OD.MIDDLE_NAME) OFFENDERNAME ,    "+
                                                                "  MC.APPT_TIME,                                                              "+
                                                                "  MC.REVIEW_DECISION_CODE                                                    "+
                                                                "  UM.REVIEW_DESC                                                             "+
                                                                " FROM                                                                        "+
                                                                "   MED_CONSULTATIONS,                                                        "+
                                                                "   JMOV_BEDS  ,                                                              "+
                                                                "   OFFENDER_DEMOGRAPHICS OD ,                                                "+
                                                                "   MED_CONSULTATIONS_REF MCR ,                                               "+
                                                                "   MED_UTILIZATION_MGMT_REF UM                                               "+
                                                                " WHERE                                                                       "+
                                                               "    MC.UNO#= OD.UNO                                                           "+
                                                               "    AND MC.REVIEW_DECISION_CODE  = UM.REVIEW_CODE                             "+
                                                               "    AND MC.REQ_CONSULTATION_CODE = MCR.CONSULTATION_CODE                      "+
                                                                "   AND  MED_CONSULTATIONS.UNO# = JMOV_BEDS.OCCUPIED_BY_UNO                   "+
                                                                "   AND JMOV_BEDS.SCRIBE_CODE = 'S_50000250'                                  "+
                                                                "   AND APPT_STATUS_CODE =  '101'                                             "+
                                                                "   AND (APPT_TIME) < TO_DATE                                                 "+ 
                                                                "   AND ((APPT_TIME) >= TO_DATE                                               "+
                                                                "   AND REQ_INST_CODE = ''                                                    "+
                                                                "   ORDER BY APPT_TIME ";


                                               System.out.println("getScheduledConsultReport sqlQuery : " + sqlQuery);
                                               
                                               stmt = orclConn.prepareStatement(sqlQuery);
                                               stmt.clearParameters();  
                                               rs = stmt.executeQuery();
                                               
                                               while (rs.next())
                                               {
                                                   mVo = new ScheduledConsultVo(); 
                                                   mVo.setUmReqConsultationCode(rs.getString(1));
                                                   mVo.setUmReqConsultationDesc(rs.getString(2));
                                                   mVo.setUno(rs.getString(1));
                                                   mVo.setOffenderName(rs.getString(1));
                                                   mVo.setApptTime(rs.getString(1));                                              
                                                   mVo.setReviewDecisionCode(rs.getString(1));
                                                   mVo.setReviewDecisionDesc(rs.getString(2));
                                                  
                                               
                                               
                                                   scheduledConsultList.add(mVo);
                                               }
                                               rs.close();
                                               stmt.close();
                                               orclConn.close();
                                               }
                                               catch(SQLException se) {
                                                   System.out.println("MedicalConsultsImpl.getScheduledConsultReport: " + se.toString());
                                                   throw new DataAccessException("getScheduledConsultReport", se);
                                                   
                                               }finally{
                                                   try {
                                                       if (rs != null)
                                                               rs.close();
                                                       if (stmt != null)
                                                               stmt.close();
                                                       if (orclConn != null)
                                                               orclConn.close();
                                               } catch (SQLException e) {
                                                       System.out.println("MedicalConsultsImpl.getScheduledConsultReport: "
                                                                       + e.toString());
                                                       throw new DataAccessException("getScheduledConsultReport", e);
                                               }
                                                   
                                               }
                                              
                                               return scheduledConsultList;
        
        
    }
    
    
    public ArrayList<ScheduledDetailReportVo> getScheduledConsultDetailReport(){
        

                                                    ArrayList<ScheduledDetailReportVo>   scheduledDetailList = new ArrayList<ScheduledDetailReportVo>();
                                                    
                                                    ScheduledDetailReportVo mVo=null; 
                                                    Connection orclConn = null;
                                                    PreparedStatement stmt = null;
                                                    ResultSet rs = null ;
                                                    
                                                    try {
                                                                  
                                                    MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                                    
                                                        orclConn = orclFactory.createReadOnlyConnection();
        
                                                  
                                                 String sqlQuery="  SELECT MC.APPT_TIME,                                                         "+
                                                                 "     MC.APPT_LOCATION_CODE,                                                    "+
                                                                 "     AL.LOCATION_DESC ,                                                        "+
                                                                 "     MC.REQ_CONSULTATION_CODE,                                                 "+
                                                                 "     MCR.CONSULTATION_DESC ,                                                   "+
                                                                 "     MC.UM_REQ_CONSULTATION_CODE,                                              "+
                                                                 "     MC.PROVIDER_CODE,                                                         "+
                                                                 "     MP.NAME ,                                                                 "+
                                                                 "     MC.TRANS_TYPE_CODE,                                                       "+
                                                                 "     MTR.TRANS_DESC ,                                                          "+
                                                                 "     MC.REQ_INST_CODE ,                                                        "+
                                                                 "     SR.SCRIBE_DESC ,                                                          "+
                                                                 "     MC.UNO#,                                                                  "+
                                                                 "    (OD.LAST_NAME ||','||OD.FIRST_NAME  ||''|| OD.MIDDLE_NAME) OFFENDERNAME ,  "+
                                                                 "     MC.REQ_DATE,                                                              "+
                                                                 "     MC.REQ_TYPE,                                                              "+
                                                                 "     MC.UM_REQ_TYPE,                                                           "+
                                                              // "    -- jb.SCRIBE_CODE,
                                                                 "     MC.RECORD_VERSION                                                         "+
                                                                 "  FROM MED_CONSULTATIONS MC,                                                   "+
                                                              // "   --JMOV_BEDS JB ,
                                                                 "     MED_APPT_LOCATION_REF AL ,                                                "+
                                                                 "     MED_CONSULTATIONS_REF MCR  ,                                              "+
                                                                 "     MED_PROVIDERS_REF MP ,                                                    "+
                                                                 "     MED_TRANS_TYPE_REF MTR ,                                                  "+
                                                                 "     OFFENDER_DEMOGRAPHICS OD  ,                                               "+
                                                                 "     SCRIBE_REF SR                                                             "+                                                                     
                                                                 "   WHERE                                                                       "+
                                                                 "     MC.UNO#= OD.UNO                                                           "+
                                                          //     "    --and  MC.REQ_INST_CODE =JB.SCRIBE_CODE 
                                                                 "     AND MC.REQ_INST_CODE = SR.SCRIBE_CODE                                     "+
                                                                 "     AND MC.PROVIDER_CODE       =MP.PROVIDER_CODE                              "+
                                                                 "     AND MC.APPT_LOCATION_CODE    =AL.LOCATION_CODE                            "+
                                                                 "     AND MC.REQ_CONSULTATION_CODE = MCR.CONSULTATION_CODE                      "+
                                                                 "     AND MC.TRANS_TYPE_CODE = MTR.TRANS_CODE                                   "+
                                                                 "     AND MC.UNO#='340340' ";
                                                 
                                                 System.out.println("getScheduledConsultDetailReport sqlQuery : " + sqlQuery);
                                                 
                                                 stmt = orclConn.prepareStatement(sqlQuery);
                                                 stmt.clearParameters();  
                                                 rs = stmt.executeQuery();
                                                 
                                                 while (rs.next())
                                                 {
                                                     mVo = new ScheduledDetailReportVo(); 
                                                     mVo.setApptTime(rs.getString(1));
                                                     mVo.setApptLocationCode(rs.getString(2));
                                                     mVo.setApptLocationDesc(rs.getString(1));
                                                     mVo.setReqConsultationCode(rs.getString(1)); 
                                                     mVo.setReqConsultationDesc(rs.getString(1)); 
                                                     mVo.setUmReqConsultationCode(rs.getString(1)); 
                                                     mVo.setProviderCode(rs.getString(1)); 
                                                     mVo.setProviderName(rs.getString(1));
                                                     mVo.setTransportCode(rs.getString(1));
                                                     mVo.setTransportDesc(rs.getString(2));
                                                     mVo.setScribeCode(rs.getString(1));
                                                     mVo.setScribeDesc(rs.getString(2));
                                                     mVo.setUno(rs.getString(2));
                                                     mVo.setOffenderName(rs.getString(1));
                                                     mVo.setRequestDate(rs.getString(1)); 
                                                     mVo.setRequestType(rs.getString(1));  
                                                     mVo.setUmRequestType(rs.getString(1)); 
                                                     mVo.setRecordVersion(rs.getString(1));
                                             
                                                     scheduledDetailList.add(mVo);
                                                 }
                                                 rs.close();
                                                 stmt.close();
                                                 orclConn.close();
                                                 }
                                                 catch(SQLException se) {
                                                     System.out.println("MedicalConsultsImpl.getScheduledConsultDetailReport: " + se.toString());
                                                     throw new DataAccessException("getScheduledConsultDetailReport", se);
                                                     
                                                 }finally{
                                                     try {
                                                         if (rs != null)
                                                                 rs.close();
                                                         if (stmt != null)
                                                                 stmt.close();
                                                         if (orclConn != null)
                                                                 orclConn.close();
                                                 } catch (SQLException e) {
                                                         System.out.println("MedicalConsultsImpl.getScheduledConsultDetailReport: "
                                                                         + e.toString());
                                                         throw new DataAccessException("getScheduledConsultDetailReport", e);
                                                 }
                                                     
                                                 }
                                                
                                                 return scheduledDetailList;
        
        
    }
	
	
}



	
	

	
	
	


	
	
	
	
	

	
	
	
	
	

	
	
	
	
	
	
	
	