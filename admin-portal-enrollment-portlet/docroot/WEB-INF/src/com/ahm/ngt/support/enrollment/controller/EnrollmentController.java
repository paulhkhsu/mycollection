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
import org.springframework.web.bind.annotation.ModelAttribute;
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
		SearchByVINDto searchResult = enrollmentWebServiceClient
				.searchEnrollmentByVIN(vin, "B", "990001");

		String searchStatus = searchResult.getSearchStatus();
		log.info("search status= " + searchStatus);
		log.info(searchResult);

		// vin not found
		if (EnrollmentWebServiceClient.VINNNOTFOUND
				.equalsIgnoreCase(searchStatus)) {
			// SessionErrors.add(request, "vinNotFound");
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
	public void enroll(@ModelAttribute("dto") EnrollmentDto dto,
			ActionRequest request, ActionResponse response, Model model) {

		log.info("In enroll method" + dto.getVin());

		model.addAttribute("dto", dto);
		response.setRenderParameter("action", "enrollform");
	}

	@ActionMapping(params = "action=save")
	public void saveToXSM(@ModelAttribute("dto") EnrollmentDto enroll,
			ActionRequest request, ActionResponse response, Model model) {

		// call save enrollment web service

		enroll.setHomeCountry("US");
		enroll.setPreferredLanguage("EN");
		enroll.setSalesPersonNo(""); // need set
		enroll.setDealerDivision(EnrollmentWebServiceClient.DEVISION);
		enroll.setDealerNo(EnrollmentWebServiceClient.DEALERID);
		enroll.setDealerName("Default Dealer");
		log.info("saveToXSM" + enroll);

		EnrollmentDto backdto = enrollmentWebServiceClient
				.saveEnrollment(enroll);
		log.info("saveToXSM return " + backdto);
		log.info("will call SXM");
		model.addAttribute("dto", backdto);
		response.setRenderParameter("action", "sxm");

	}

	@RenderMapping(params = "action=sxm")
	public String SXMEnroll(RenderRequest request, RenderResponse response,
			Model model) {
		log.info("In SXMEnroll method");
		return "sxm";
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
