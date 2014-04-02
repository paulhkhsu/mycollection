package com.ahm.ngt.support.enrollment.controller;
/**
 * @author Paul Hsu
 *
 */
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.ahm.ngt.support.enrollment.dto.EnrollmentDto;
import com.ahm.ngt.support.enrollment.dto.SearchByVINDto;
import com.ahm.ngt.support.enrollment.service.EnrollmentWebServiceClient;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

@Controller(value = "EnrollmentController")
@RequestMapping("VIEW")
public class EnrollmentController {

	private static Log log = LogFactoryUtil.getLog(EnrollmentController.class);

	@Autowired
	EnrollmentWebServiceClient enrollmentWebServiceClient;

	@RenderMapping
	public String handleRenderRequest(RenderRequest request,
			RenderResponse response, Model model) {
		return "vin";
	}

	@ActionMapping(params = "action=vinSearch")
	public void vinSearch(ActionRequest request, ActionResponse response,
			Model model, @RequestParam(value = "vin") String vin) {

		log.info("vin is==>" + vin);

		// call search by vin web service
		SearchByVINDto searchResult = enrollmentWebServiceClient.searchEnrollmentByVIN(
				vin, "B", "990001");

		String searchStatus = searchResult.getSearchStatus();
		log.info("search status= " + searchStatus);
		log.info(searchResult);

		// vin not found
		if (EnrollmentWebServiceClient.VINNNOTFOUND
				.equalsIgnoreCase(searchStatus)) {
			//SessionErrors.add(request, "vinNotFound");
			model.addAttribute("errmsg", "VIN not found.");
			return;
		}

		EnrollmentDto dto;
		// vin found but not enrollment info
		if (EnrollmentWebServiceClient.ENROLLMENTNOTFOUND
				.equalsIgnoreCase(searchStatus)) {
			dto = searchResult.getFirstEnrollment();
			model.addAttribute("dto", dto);
			response.setRenderParameter("action", "enrollform");
			log.info(dto);
			return;
		} else if (EnrollmentWebServiceClient.ENROLLMENTFOUND
				.equalsIgnoreCase(searchStatus)) {
			dto = searchResult
					.findEnrolmentDtoByStatus(EnrollmentWebServiceClient.ACTIVE);
			if (dto != null) {
				model.addAttribute("dto", dto);
				response.setRenderParameter("action", "changeownerform");
				log.info(dto);
				return;
			}
			dto = searchResult.getFirstEnrollment();
			if (dto != null) {
				model.addAttribute("dto", dto);
				response.setRenderParameter("action", "enrollform");
				//response.setRenderParameter("action", "changeownerform");
				log.info(dto);
				return;
			}

		}

	}

	@RenderMapping(params = "action=changeownerform")
	public String changeOwnershipForm(RenderRequest request,
			RenderResponse response, Model model) {

		setAgentInfo(request, model);
		return "changeowner";
	}

	@RenderMapping(params = "action=enrollform")
	public String enrollForm(RenderRequest request, RenderResponse response,
			Model model) {
		log.info("In enrollForm method");

		setAgentInfo(request, model);
		return "enroll";
	}

	@ActionMapping(params = "action=enroll")
	public void enroll(ActionRequest request, ActionResponse response,
			Model model, @RequestParam(value = "vin") String vin,
			@RequestParam(value = "make") String make,
			@RequestParam(value = "model") String carModel,
			@RequestParam(value = "modelSeries") String modelSeries,
			@RequestParam(value = "modelYear") String modelYear,
			@RequestParam(value = "baseColor") String baseColor,
			@RequestParam(value = "colorDesc") String colorDesc) {

		log.info("In enroll method" + vin);
		EnrollmentDto dto = new EnrollmentDto();
		dto.setVin(vin);
		dto.setMake(make);
		dto.setModel(carModel);
		dto.setModelSeries(modelSeries);
		dto.setModelYear(modelYear);
		dto.setBaseColor(baseColor);
		dto.setColorDesc(colorDesc);

		model.addAttribute("dto", dto);
		response.setRenderParameter("action", "enrollform");
	}

	@ActionMapping(params = "action=save")
	public void saveToXSM(
			ActionRequest request,
			ActionResponse response,
			Model model,
			@RequestParam(value = "vin") String vin,
			@RequestParam(value = "make") String make,
			@RequestParam(value = "model") String carModel,
			@RequestParam(value = "modelSeries") String modelSeries,
			@RequestParam(value = "modelYear") String modelYear,
			@RequestParam(value = "baseColor") String baseColor,
			@RequestParam(value = "colorDesc") String colorDesc,
			@RequestParam(value = "enrollmentStatus") String enrollmentStatus,
			@RequestParam(value = "enrollmentSalesType") String enrollmentSalesType,
			@RequestParam(value = "customerTitle") String customerTitle,
			@RequestParam(value = "customerFirstName") String customerFirstName,
			@RequestParam(value = "customerLastName") String customerLastName,
			@RequestParam(value = "homeAddress") String homeAddress,
			@RequestParam(value = "homeCity") String homeCity,
			@RequestParam(value = "homeState") String homeState,
			@RequestParam(value = "homeZipCd") String homeZipCd,
			@RequestParam(value = "workPhoneNumber") String workPhoneNumber,
			@RequestParam(value = "homePhoneNumber") String homePhoneNumber,
			@RequestParam(value = "cellPhoneNumber") String cellPhoneNumber,
			@RequestParam(value = "primaryEmail") String primaryEmail,
			@RequestParam(value = "agentFirstName") String agentFirstName,
			@RequestParam(value = "agentLastName") String agentLastName

	) {

		// call save enrollment web service

		EnrollmentDto enroll = new EnrollmentDto();
		enroll.setEnrollmentStatus(enrollmentStatus);
		enroll.setEnrollmentSalesType(enrollmentSalesType);
		enroll.setVin(vin);
		enroll.setMake(make);
		enroll.setModel(carModel);
		enroll.setModelSeries(modelSeries);
		enroll.setModelYear(modelYear);
		enroll.setBaseColor(baseColor);
		enroll.setColorDesc(colorDesc);

		enroll.setCustomerTitle(customerTitle);
		enroll.setCustomerFirstName(customerFirstName);
		enroll.setCustomerLastName(customerLastName);
		enroll.setHomeAddress(homeAddress);
		enroll.setHomeCity(homeCity);
		enroll.setHomeState(homeState);
		enroll.setHomeZipCd(homeZipCd);
		enroll.setHomeCountry("US");
		enroll.setHomePhoneNumber(homePhoneNumber);
		enroll.setCellPhoneNumber(cellPhoneNumber);
		enroll.setWorkPhoneNumber(workPhoneNumber);
		enroll.setPrimaryEmail(primaryEmail);
		enroll.setPreferredLanguage("EN");

		enroll.setSalesPersonFirstName(agentFirstName);
		enroll.setSalesPersonLastName(agentLastName);
		enroll.setSalesPersonNo(""); // need set

		enroll.setDealerDivision(EnrollmentWebServiceClient.DEVISION);
		enroll.setDealerNo(EnrollmentWebServiceClient.DEALERID);
		enroll.setDealerName("Default Dealer");
		log.info("saveToXSM" + enroll);

		EnrollmentDto dto = enrollmentWebServiceClient.saveEnrollment(enroll);
		log.info("saveToXSM return " + dto);
		log.info("will call SXM");
		model.addAttribute("dto", dto);
		response.setRenderParameter("action", "sxm");

	}

	@RenderMapping(params = "action=sxm")
	public String SXMEnroll(RenderRequest request, RenderResponse response,
			Model model) {
		log.info("In SXMEnroll method");
		return "sxm";
	}
	
	private EnrollmentDto mockDto() {
		EnrollmentDto dto = new EnrollmentDto();
		dto.setEnrollmentId("1537");
		dto.setEnrollmentStatus("PENDING");
		dto.setEnrollmentSalesType("Individual");
		dto.setVin("JH4KC1F54EC999008");
		dto.setMake("Acura");
		dto.setModel("RLX ADVANCE");
		dto.setModelSeries("RLX");
		dto.setModelYear("2014");
		dto.setBaseColor("GRAY");
		dto.setColorDesc("GRAPHITE LUSTER M.");
		dto.setHomeState("CA");
		dto.setAhmGlobalCd("A00005BJ");
		dto.setCustomerFirstName("TESTER");
		dto.setCustomerLastName("TESTLName");
		dto.setHomeAddress("1919 Torrance Blvd");
		dto.setHomeCity("Torrance");
		dto.setHomeCountry("US");
		dto.setHomeZipCd("90503-1101");
		dto.setHomePhoneNumber("3107814084");
		dto.setWorkPhoneNumber("3107814084");
		dto.setCellPhoneNumber("3107814084");
		dto.setPrimaryEmail("Testee@COMCAST.NET");
		dto.setPreferredLanguage("EN");

		return dto;
	}

	private void setAgentInfo(RenderRequest request, Model model) {
		String agentFirstName = "";
		String agentLastName = "";
		try {
			User currentUser = PortalUtil.getUser(request);
			agentFirstName = currentUser.getContact().getFirstName();
			agentLastName = currentUser.getContact().getLastName();
			model.addAttribute("agentLastName", currentUser.getContact()
					.getLastName());
		} catch (PortalException e) {
		} catch (SystemException e) {
		}
		model.addAttribute("agentFirstName", agentFirstName);
		model.addAttribute("agentLastName", agentLastName);
	}
}
