package com.mvc.portlets;

import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;

public class MyPortlet extends MVCPortlet {

	public void processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {

		System.out.println("portlet controller excuting");
		PortletPreferences prefs = actionRequest.getPreferences();
		String greeting = actionRequest.getParameter("greeting");
		if (greeting != null) {
			prefs.setValue("greeting", greeting);
			prefs.store();
		}
		SessionMessages.add(actionRequest, "success");
		super.processAction(actionRequest, actionResponse);
	}

}
