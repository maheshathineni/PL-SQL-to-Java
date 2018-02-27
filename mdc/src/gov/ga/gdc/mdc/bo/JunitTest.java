package gov.ga.gdc.mdc.bo;

import static org.junit.Assert.*;
import gov.ga.gdc.mdc.dao.MedicalConsultsDaoImpl;
import gov.ga.gdc.mdc.vo.ProviderListVo;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

public class JunitTest {
 
 
    MedicalConsultsBusinessLogicImpl mdcTest = new  MedicalConsultsBusinessLogicImpl();
    
    MedicalConsultsDaoImpl testImpl =Mockito.mock(MedicalConsultsDaoImpl.class);
    
    
    
    @Test
    public void testGetDBHostName() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetSundownInstitutions() {
       
    }

    @Test
    public void testGetProviderList() {
        mdcTest.setMedconsultsDao(testImpl);
        ArrayList<ProviderListVo>  obj1 =new  ArrayList<ProviderListVo>();
        obj1.add(new ProviderListVo());
        Mockito.when(testImpl.getProviderList()).thenReturn(obj1);
        
        ArrayList<ProviderListVo>  output=   mdcTest.getProviderList();
        // assertEquals(obj1, output);
         assertNotNull(output);
        
       
    }

    @Test
    public void testAddMedicalProvider() {
        fail("Not yet implemented");
    }

    @Test
    public void testEditMedicalProvider() {
        
        fail("Not yet implemented");
    }

    @Test
    public void testAddConsultations() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddUmDecision() {
        fail("Not yet implemented");
    }

}
