<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="javax.portlet.PortletPreferences"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="context" value="${pageContext.request.contextPath}" />

<portlet:defineObjects />

<portlet:actionURL var="vinSearch">
	<portlet:param name="action" value="vinSearch"></portlet:param>
</portlet:actionURL>


<!DOCTYPE html>
<html>
<head>
<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
<title>VIN Search</title>
<script type="text/javascript" src="${context}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${context}/js/vin.js"></script>

</head>
<body>
	<div class="div-center">
		<div class="div-title-center">Telematics Enrollment</div>
		<br>
		<div class="div-table-center">
			<form action="${vinSearch}" method="post" id="vinform">
				<label for="vin" class="req">VIN</label> <input type="text"
					name="vin" class="uppercase" id="vin">&nbsp;&nbsp; <span
					class="error" id="errorMsg">${errmsg}</span>
				<p>
					<input type="submit" value="Search" id="submit" />
			</form>
		</div>
	</div>

</body>
</html>