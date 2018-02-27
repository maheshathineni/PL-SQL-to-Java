package gov.ga.gdc.medconsults.bo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = {"gov.ga.gdc.medconsults"}) 
public class Main {
    
    public static void main(String[] args){
        
        SpringApplication.run(Main.class, args);
        
        System.out.println("hello GDC");
        
    }

}
