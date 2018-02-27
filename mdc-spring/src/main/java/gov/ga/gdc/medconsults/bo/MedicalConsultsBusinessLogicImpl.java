package gov.ga.gdc.medconsults.bo;

import gov.ga.gdc.medconsults.dao.MedicalConsultsDaoImpl;
import gov.ga.gdc.medconsults.vo.ProviderListVo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalConsultsBusinessLogicImpl {
    
  @Autowired
  private   MedicalConsultsDaoImpl medicalConsultsDaoImpl;
    
    
    public ProviderListVo getProviderList(){
        
        System.out.println("hello businesslogic impl");
        
        return this.medicalConsultsDaoImpl.getProviderList();
    }

}
