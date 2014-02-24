package com.myowncompany.test.springmvc.controller;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.myowncompany.test.springmvc.dto.EnrollmentDto;
import com.myowncompany.test.springmvc.service.TestService;

@Controller(value = "MyFirstSpringMVCTestController")
@RequestMapping("VIEW")
public class MyFirstSpringMVCTestController {

	private static Log log = LogFactoryUtil
			.getLog(MyFirstSpringMVCTestController.class);

	@Autowired
	TestService testService;

	/*
	 * maps the incoming portlet request to this method Since no request
	 * parameters are specified, therefore the default render method will always
	 * be this method
	 */
	@RenderMapping
	public String handleRenderRequest(RenderRequest request,
			RenderResponse response, Model model) {
		log.info("In handleRenderRequest method");
		return "vin";
	}

	@ActionMapping(params = "action=vinSearch")
	public void vinSearch(ActionRequest request, ActionResponse response,
			Model model) {
		String vin = ParamUtil.get(request, "vin", "");
		log.info("vin is==>" + vin);
		EnrollmentDto dto = testService.getDto();
		dto.setVin(vin);
		dto.setMake("Honda");
		model.addAttribute("dto", dto);
		response.setRenderParameter("action", "enrollmentform");

	}

	@RenderMapping(params = "action=enrollmentform")
	public String enrollmentForm(RenderRequest request,
			RenderResponse response, Model model) {
		log.info("In enrollmentForm method");
		// EnrollmentDto dto = (EnrollmentDto) model.asMap().get("dto");
		return "enrollment";
	}

	@ActionMapping(params = "action=enroll")
	public void enroll(ActionRequest request, ActionResponse response,
			Model model) {
		log.info("In enroll method");

	}

	@ResourceMapping(value = "myInfo")
	public void getMyInformation(ResourceRequest request,
			ResourceResponse response) throws IOException {
		JSONObject json = JSONFactoryUtil.createJSONObject();
		log.info("in myInfo   " + request.getParameter("field2"));
		try {
			response.setCharacterEncoding("UTF-8");
			json.put("firstName", "first name");
			json.put("lastName", "last name");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
		}
	}

}
