package gov.ga.gdc.mdc.service;

import gov.ga.gdc.mdc.bo.MedicalConsultsBusinessDelegate;



import gov.ga.gdc.mdc.vo.ApptLocationMaintVo;
import gov.ga.gdc.mdc.vo.ApptSelctionResultVo;
import gov.ga.gdc.mdc.vo.ApptTypeMaintVo;
import gov.ga.gdc.mdc.vo.CloseConsultResultVo;
import gov.ga.gdc.mdc.vo.ConsultantsRecMaintVo;
import gov.ga.gdc.mdc.vo.ConsultationMaintVo;
import gov.ga.gdc.mdc.vo.MedicalReportsVo;
import gov.ga.gdc.mdc.vo.ProviderListVo;
import gov.ga.gdc.mdc.vo.ReviewDecisionVo;
import gov.ga.gdc.mdc.vo.ScheduledConsultVo;
import gov.ga.gdc.mdc.vo.ScheduledDetailReportVo;
import gov.ga.gdc.mdc.vo.TotalConsultsVo;
import gov.ga.gdc.mdc.vo.TransportMaintVo;
import gov.ga.gdc.mdc.vo.UserMaintVo;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/mdc")
public class MedicalConsultsServices {
    
    MedicalConsultsBusinessDelegate medBd = new MedicalConsultsBusinessDelegate();
    


	
	@GET
	@Path("getHostName")	
	//@Produces(MediaType.APPLICATION_JSON)
	public String getHostName()	
	{
		return "database";//medBd.getDBHostName(); 		
	}

	


	
/*	@POST
	@Path("selectbed")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response selectBed(
			@FormParam("scribeCode") String scribeCode,
//			@FormParam("moveCategeoryCd") String moveCategeoryCd,
			@FormParam("deviceId") String deviceId,
			@FormParam("moveDate") String moveDate,
			@FormParam("moveReasonCd") String moveReasonCd,
			@FormParam("moveAuthBy") String moveAuthBy,
			@FormParam("comments") String comments
//			MovementsVo mVo
			) {
//System.out.println("mVo ----------"+ mVo);
		MovementsVo mVo = new MovementsVo();
		mVo.setMoveCategoryCd("3");
		mVo.setMoveTypeCd("1");
		mVo.setMoveStatusCd("1");
		mVo.setUno("755556");
		mVo.setMoveDate(moveDate);
		mVo.setMoveReasonCd(moveReasonCd);
		mVo.setMoveAuthorizeBy(moveAuthBy);
		
		String movSeqNo  = medBd.bedMove(mVo);
		System.out.println("BedMove: " + movSeqNo);
		mVo.setMoveSeqNo(movSeqNo);
		return Response.status(201).entity(mVo).build();

	}*/
	





	@GET
	@Path("provider")
	//@Produces(MediaType.APPLICATION_JSON)
	public String providerhandler() {
		System.out.println("MedicalconsultswebServices: provider handler");
		//medBd.getProviderList();
		
		ArrayList<ProviderListVo> gdcMovesOutProgressList = medBd.getProviderList();
                System.out.println("Medicalconsults : gdcMedicalconsultsProgress: " + gdcMovesOutProgressList.size());

               // return Response.status(201).entity(gdcMovesOutProgressList).build();
		return "success";// Response.status(201).entity(movBd.getScribeLocationDetailsByScribeCode(scribeCode)).build();
	}
	
	
	@GET
        @Path("getSundownInstitutions")
        //@Produces(MediaType.APPLICATION_JSON)
        public String getSundownInstitutionshandler() {
                System.out.println("MedicalconsultswebServices: getSundownInstitutions  handler");
                //medBd.getProviderList();
                
                ArrayList<MedicalReportsVo> getSundownList = medBd.getSundownInstitutions();
                System.out.println("Medicalconsults : getSundownInstitutions: " + getSundownList.size());

               // return Response.status(201).entity(gdcMovesOutProgressList).build();
                return "success";// Response.status(201).entity(movBd.getScribeLocationDetailsByScribeCode(scribeCode)).build();
        }

	  @GET
	        @Path("getConsultationMaintList")
	        //@Produces(MediaType.APPLICATION_JSON)
	        public String getConsultationMaintListhandler() {
	                System.out.println("MedicalconsultswebServices: getConsultationMaintList  handler");
	                //medBd.getProviderList();
	                
	                ArrayList<ConsultationMaintVo> getSundownList = medBd.getConsultationMaintList();
	                System.out.println("Medicalconsults : getConsultationMaintList: " + getSundownList.size());

	               // return Response.status(201).entity(gdcMovesOutProgressList).build();
	                return "success";// Response.status(201).entity(movBd.getScribeLocationDetailsByScribeCode(scribeCode)).build();
	        }


	        @GET
	        @Path("getUserMaintList")
	        //@Produces(MediaType.APPLICATION_JSON)
	        public String getUserMaintListhandler() {
	                System.out.println("MedicalconsultswebServices: getUserMaintList handler");
	                //medBd.getProviderList();
	                
	                ArrayList<UserMaintVo> getUserMaintListList = medBd.getUserMaintList();
	                System.out.println("Medicalconsults : getUserMaintList: " + getUserMaintListList.size());

	               // return Response.status(201).entity(gdcMovesOutProgressList).build();
	                return "success";// Response.status(201).entity(movBd.getScribeLocationDetailsByScribeCode(scribeCode)).build();
	        }
	        
	        @GET
                @Path("getTransportMaintList")
                //@Produces(MediaType.APPLICATION_JSON)
                public String getTransportMaintListhandler() {
                        System.out.println("MedicalconsultswebServices: getTransportMaintList handler");
                        //medBd.getProviderList();
                        
                        ArrayList<TransportMaintVo> getTransportMaintList = medBd.getTransportMaintList();
                        System.out.println("Medicalconsults : getTransportMaintList: " + getTransportMaintList.size());

                       // return Response.status(201).entity(gdcMovesOutProgressList).build();
                        return "success";// Response.status(201).entity(movBd.getScribeLocationDetailsByScribeCode(scribeCode)).build();
                }
	        
	        @GET
                @Path("getApptLocationMaintList")
                //@Produces(MediaType.APPLICATION_JSON)
                public String getApptLocationMaintListhandler() {
                        System.out.println("MedicalconsultswebServices: getApptLocationMaintList  handler");
                        //medBd.getProviderList();
                        
                        ArrayList<ApptLocationMaintVo> getApptLocationMaintList = medBd.getApptLocationMaintList();
                        System.out.println("Medicalconsults : getApptLocationMaintList: " + getApptLocationMaintList.size());

                       // return Response.status(201).entity(gdcMovesOutProgressList).build();
                        return "success";// Response.status(201).entity(movBd.getScribeLocationDetailsByScribeCode(scribeCode)).build();
                }
	        
	        @GET
                @Path("getApptTypeMaintList")
                //@Produces(MediaType.APPLICATION_JSON)
                public String getApptTypeMaintListhandler() {
                        System.out.println("MedicalconsultswebServices: getApptTypeMaintList  handler");
                        //medBd.getProviderList();
                        
                        ArrayList<ApptTypeMaintVo> getApptTypeMaintList = medBd.getApptTypeMaintList();
                        System.out.println("Medicalconsults : getApptTypeMaintList: " + getApptTypeMaintList.size());

                       // return Response.status(201).entity(gdcMovesOutProgressList).build();
                        return "success";// Response.status(201).entity(movBd.getScribeLocationDetailsByScribeCode(scribeCode)).build();
                }
	        
	        
	        @GET
                @Path("getConsultantsRecMaintList")
                //@Produces(MediaType.APPLICATION_JSON)
                public String getConsultantsRecMaintListhandler() {
                        System.out.println("MedicalconsultswebServices: getConsultantsRecMaintList handler");
                        //medBd.getProviderList();
                        
                        ArrayList<ConsultantsRecMaintVo> getConsultantsRecMaintList = medBd.getConsultantsRecMaintList();
                        System.out.println("Medicalconsults : getConsultantsRecMaintList: " + getConsultantsRecMaintList.size());

                       // return Response.status(201).entity(gdcMovesOutProgressList).build();
                        return "success";// Response.status(201).entity(movBd.getScribeLocationDetailsByScribeCode(scribeCode)).build();
                }
                
	        @GET
                @Path("getReviewDecisionList")
                //@Produces(MediaType.APPLICATION_JSON)
                public String getReviewDecisionListhandler() {
                        System.out.println("MedicalconsultswebServices: getReviewDecisionList handler");
                        //medBd.getProviderList();
                        
                        ArrayList<ReviewDecisionVo> getReviewDecisionList = medBd.getReviewDecisionList();
                        System.out.println("Medicalconsults :getReviewDecisionList: " + getReviewDecisionList.size());

                       // return Response.status(201).entity(gdcMovesOutProgressList).build();
                        return "success";// Response.status(201).entity(movBd.getScribeLocationDetailsByScribeCode(scribeCode)).build();
                }
                
	  
	        // do businesslogicimpl part for getTotalConsultsList
	        @GET
                @Path("getTotalConsultsList")
                //@Produces(MediaType.APPLICATION_JSON)
                public String getTotalConsultsListhandler() {
                        System.out.println("MedicalconsultswebServices: getTotalConsultsList handler");
                        //medBd.getProviderList();
                        
                        ArrayList<TotalConsultsVo> getTotalConsultsList = medBd.getTotalConsultsList();
                        System.out.println("Medicalconsults :getTotalConsultsList: " + getTotalConsultsList.size());

                       // return Response.status(201).entity(gdcMovesOutProgressList).build();
                        return "success";// Response.status(201).entity(movBd.getScribeLocationDetailsByScribeCode(scribeCode)).build();
                }
	        
	        @GET
                @Path("getApptSelectionResult")
                //@Produces(MediaType.APPLICATION_JSON)
                public String getApptSelectionResulthandler() {
                        System.out.println("MedicalconsultswebServices: getApptSelectionResult handler");
                        //medBd.getProviderList();
                        
                        ArrayList<ApptSelctionResultVo> getApptSelectionResult = medBd.getApptSelectionResult();
                        System.out.println("Medicalconsults : getApptSelectionResult: " + getApptSelectionResult.size());

                       // return Response.status(201).entity(gdcMovesOutProgressList).build();
                        return "success";// Response.status(201).entity(movBd.getScribeLocationDetailsByScribeCode(scribeCode)).build();
                }
                
	        @GET
                @Path("getCloseConsultResult")
                //@Produces(MediaType.APPLICATION_JSON)
                public String getCloseConsultResulthandler() {
                        System.out.println("MedicalconsultswebServices: getCloseConsultResult handler");
                        //medBd.getProviderList();
                        
                        ArrayList<CloseConsultResultVo>  getCloseConsultResult = medBd.getCloseConsultResult();
                        System.out.println("Medicalconsults : getCloseConsultResult: " + getCloseConsultResult.size());

                       // return Response.status(201).entity(gdcMovesOutProgressList).build();
                        return "success";// Response.status(201).entity(movBd.getScribeLocationDetailsByScribeCode(scribeCode)).build();
                }
	        
	        @GET
                @Path("getScheduledConsultReport")
                //@Produces(MediaType.APPLICATION_JSON)
                public String getScheduledConsultReporthandler() {
                        System.out.println("MedicalconsultswebServices: getScheduledConsultReport handler");
                        //medBd.getProviderList();
                        
                        ArrayList<ScheduledConsultVo>  getScheduledConsultReport = medBd.getScheduledConsultReport();
                        System.out.println("Medicalconsults : getScheduledConsultReport: " + getScheduledConsultReport.size());

                       // return Response.status(201).entity(gdcMovesOutProgressList).build();
                        return "success";// Response.status(201).entity(movBd.getScribeLocationDetailsByScribeCode(scribeCode)).build();
                }
	        
	        @GET
                @Path("getScheduledConsultDetailReport")
                //@Produces(MediaType.APPLICATION_JSON)
                public String getScheduledConsultDetailReporthandler() {
                        System.out.println("MedicalconsultswebServices: getScheduledConsultDetailReport handler");
                        //medBd.getProviderList();
                        
                        ArrayList<ScheduledDetailReportVo>  getScheduledConsultDetailReport = medBd.getScheduledConsultDetailReport();
                        System.out.println("Medicalconsults : getScheduledConsultDetailReport: " + getScheduledConsultDetailReport.size());

                       // return Response.status(201).entity(gdcMovesOutProgressList).build();
                        return "success";// Response.status(201).entity(movBd.getScribeLocationDetailsByScribeCode(scribeCode)).build();
                }
                
                
                
                




	@POST
	@Path("addproviderlist")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String addproviderlisthandler() {
	    
	    
	    ProviderListVo mVo =new ProviderListVo();
	   mVo.setAddress1("mahesh");

	    System.out.println("hello sop");
		ProviderListVo gdcMovesOutProgressList = medBd.addMedicalProvider(mVo );
		
		//System.out.println("MovementsServices : gdcMovesOutProgress: " + gdcMovesOutProgressList);

		//return Response.status(201).entity(gdcMovesOutProgressList).build();
		return "success";

	}




/*

	@POST
	@Path("bedbodybountdetailbybedtype")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response bedBodyCountDetailByBedType(@FormParam("scribeCd") String scribeCd,
												@FormParam("bedTypeCd") String bedTypeCd) {
		System.out.println("\n\nMovementsServices bedBodyCountReport ");
		ArrayList<MovementsVo> list = movBd.bedsByBedType(scribeCd, bedTypeCd);
		return Response.status(201).entity(list).build();
	}
*/

	
}
