<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="javax.portlet.PortletPreferences"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<portlet:defineObjects />

<portlet:renderURL var="backVinPage">
</portlet:renderURL>
<portlet:actionURL var="enroll">
	<portlet:param name="action" value="enroll"></portlet:param>
</portlet:actionURL>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${context}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		alert("page load");
	});
</script>
</head>
<body>
	<form action="${enroll}" method="post">
		<table id="enrollment" border="1">
			<tr>
				<td>VIN : <input type="text" name="vin" value="${dto.vin}"
					disabled="disabled">
				</td>
			</tr>
			<tr>
				<td>Make : <input type="text" name="make" value="${dto.make}"
					disabled="disabled">
			</tr>
			<tr>
				<td><input type="submit" value="enroll" /></td>
			</tr>
		</table>
	</form>
	<table>
		<tr>
			<td><a href="${backVinPage}"><img
					src="${context}/images/righttoleft.jpg"></a></td>
		</tr>
	</table>
</body>
</html>