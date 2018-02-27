package gov.ga.gdc.medconsults.dao;

import gov.ga.gdc.commonspring.dao.OracleDaoFactory;
import gov.ga.gdc.commonspring.exception.DataAccessException;
import gov.ga.gdc.medconsults.vo.ProviderListVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class MedicalConsultsDaoImpl extends OracleDaoFactory {
    

    
 // @Autowired   
 // private  MedicalConsultsDaoImpl medDaoImpl ;
    
    
    public ProviderListVo getProviderList()
    {
        
                                   // ArrayList<ProviderListVo>   providerList = new ArrayList<ProviderListVo>();
                                
        System.out.println("hello dao impll");
                                    ProviderListVo mVo=null; 
                                    Connection orclConn = null;
                                    PreparedStatement stmt = null;
                                    ResultSet rs = null;
                                    
                                    try {
                                          
                                        
                                        javax.sql.DataSource ds = null;

                                        Context ic = new InitialContext();
                                
                                        ds = (DataSource)ic.lookup("java:comp/env/jdbc/DEVCoreDS");
                                    
                                     
                                         orclConn = ds.getConnection();
                                    }
                                    catch(Exception e)
                                   {  
                               /*        if(orclConn != null)
                                       orclConn.close();
                                       */
                                       System.out.println("Error Creating Read Only Connection" + e.toString());
                                       throw new DataAccessException("Problem creating Read Only connection",e);
                                 //    throw e;
                                   }
                                    
                                    
                                    try {
                                  //  MedicalConsultsDaoFactory orclFactory= new MedicalConsultsDaoFactory();
                                    
                                      //  OracleDaoFactory  orclFactory = new OracleDaoFactory();
                                        
                                    //  orclConn = orclFactory.createReadOnlyConnection();
                                      
                                      
                                      System.out.println("orclConn122 :"+orclConn);
                                    
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
                                     
                                      //  providerList.add(mVo);
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
                                   
                                    return mVo;
        
    }
    
    
}
