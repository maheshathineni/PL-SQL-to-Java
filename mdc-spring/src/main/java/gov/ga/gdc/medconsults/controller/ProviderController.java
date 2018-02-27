package gov.ga.gdc.medconsults.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import gov.ga.gdc.medconsults.bo.MedicalConsultsBusinessLogicImpl;
import gov.ga.gdc.medconsults.vo.ProviderListVo;


@RestController
@RequestMapping("/provider")
public class ProviderController {
   
  
  // private  MedicalConsultsBusinessDelegate medBusdel ;
    @Autowired
    private  MedicalConsultsBusinessLogicImpl  medicalConsultsBusinessLogicImpl;
  
    @RequestMapping(method=RequestMethod.GET)
  public ProviderListVo getProviderList(){
        
        System.out.println(" 1 gdc 1");
        
        return this.medicalConsultsBusinessLogicImpl.getProviderList();
    }

}
