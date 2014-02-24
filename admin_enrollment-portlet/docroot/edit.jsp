<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="javax.portlet.PortletPreferences"%>

<portlet:defineObjects />

<liferay-ui:success key="success" message="Greeting saved successfully" />
<%
	PortletPreferences prefs = renderRequest.getPreferences();
%>

<portlet:actionURL var="editGreetingURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
</portlet:actionURL>

<aui:form action="<%=editGreetingURL%>" method="post">
	<aui:input label="greeting" name="greeting" type="text"
		value="EnterNew value here" />
	<aui:button type="submit" />
</aui:form>
<portlet:renderURL var="vieGreetingURL">
<portlet:param name="mvcPath" value="/view.jsp" />
</portlet:renderURL>

<p>
<a href="<%=vieGreetingURL%>">Back</a>
</p>