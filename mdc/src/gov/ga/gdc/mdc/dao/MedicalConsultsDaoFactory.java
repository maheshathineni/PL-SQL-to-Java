package gov.ga.gdc.mdc.dao;

import java.sql.Connection;

import gov.ga.gdc.commonspring.dao.OracleDaoFactory;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

public class MedicalConsultsDaoFactory extends OracleDaoFactory {
	private DataSource dataSource; 
	
	public MedicalConsultsDaoFactory()
	{
	}
	
	public MedicalConsultsDao getMedicalConsultsDao()
	{
		return new MedicalConsultsDaoImpl();
	}
	public void releaseConnection(Connection conn)
	{
	       DataSourceUtils.releaseConnection(conn, getDataSource());
        }
	
}
