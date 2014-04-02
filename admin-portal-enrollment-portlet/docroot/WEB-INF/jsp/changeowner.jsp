<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="javax.portlet.PortletPreferences"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<portlet:defineObjects />

<portlet:renderURL var="backVinPage" />

<portlet:actionURL var="enroll">
	<portlet:param name="action" value="enroll"></portlet:param>
</portlet:actionURL>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
<title>Change Ownership</title>

</head>
<body>
	<div class="div-center">
		<div class="div-title-center">Telematics Enrollment</div>

		<br>

		<div class="div-title-left">Vehicle Information</div>
		<div class="div-table-box">
			<table>
				<tbody>
					<tr>
						<td class="tdlabel">VIN</td>
						<td>${dto.vin}</td>
						<td class="tdlabel">Make</td>
						<td>${dto.make}</td>
					</tr>
					<tr>
						<td class="tdlabel">Model</td>
						<td>${dto.model}</td>
						<td class="tdlabel">Model Year</td>
						<td>${dto.modelYear}</td>
					</tr>
					<tr>
						<td class="tdlabel">Code</td>
						<td>${dto.baseColor}</td>
					</tr>
				</tbody>
			</table>
		</div>

		<br>
		<div class="div-title-left">Subscription Information</div>

		<div class="div-table-box">
			<table>
				<tbody>
					<tr>
						<td class="tdlabel">Customer ID</td>
						<td>${dto.ahmGlobalCd}</td>
						<td class="tdlabel">Status</td>
						<td>${dto.enrollmentStatus}</td>
					</tr>
					<tr>
						<td class="tdlabel">Sales Type</td>
						<td>${dto.enrollmentSalesType}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<br>
		<div class="div-title-left">
			Customer Information&nbsp; <span class="error" id="errorMsg"></span>
		</div>

		<div class="div-table-box">
			<table>
				<colgroup>
					<col span="1" style="width: 10%;">
					<col span="1" style="width: 23%;">
					<col span="1" style="width: 10%;">
					<col span="1" style="width: 23%;">
					<col span="1" style="width: 10%;">
					<col span="1" style="width: 24%;">
				</colgroup>
				<tbody>
					<tr>
						<td class="tdlabel">Name</td>
					</tr>
					<tr>
						<td class="tdlabel">Title</td>
						<td>Mr.</td>
						<td class="tdlabel">First Name</td>
						<td>${dto.customerFirstName}</td>
						<td class="tdlabel">Last Name</td>
						<td>${dto.customerLastName}</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td class="tdlabel">Address</td>
						<td>${dto.homeAddress}</td>
					</tr>
					<tr>
						<td class="tdlabel">City</td>
						<td>${dto.homeCity}</td>
						<td class="tdlabel">State</td>
						<td>${dto.homeState}</td>
						<td class="tdlabel">Zip Code</td>
						<td>${dto.homeZipCd}</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>

					<tr>
						<td class="tdlabel">Telephone</td>
					</tr>
					<tr>
						<td class="tdlabel">Work</td>
						<td>${dto.workPhoneNumber}</td>
						<td class="tdlabel">Home</td>
						<td>${dto.homePhoneNumber}</td>
						<td class="tdlabel">Cell</td>
						<td>${dto.cellPhoneNumber}</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>

					<tr>
						<td class="tdlabel">Email</td>
						<td>${dto.primaryEmail}</td>
					</tr>
					<tr>
						<td class="tdlabel">Pref Language</td>
						<td>${dto.preferredLanguage}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<br>
		<div class="div-title-left">Agent Information</div>

		<div class="div-table-box">
			<table>
				<tbody>
					<tr>
						<td class="tdlabel">First Name</td>
						<td>${agentFirstName}</td>
						<td class="tdlabel">Last Name</td>
						<td>${agentLastName}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<br>
		<div class="div-table-center">
			<form action="${enroll}" method="post">
				<input type="hidden" name="vin" value="${dto.vin}"> <input
					type="hidden" name="make" value="${dto.make}"> <input
					type="hidden" name="model" value="${dto.model}"> <input
					type="hidden" name="modelSeries" value="${dto.modelSeries}">
				<input type="hidden" name="modelYear" value="${dto.modelYear}">
				<input type="hidden" name="baseColor" value="${dto.baseColor}">
				<input type="hidden" name="colorDesc" value="${dto.colorDesc}">
				<input type="hidden" name="enrollmentSalesType"
					value="${dto.enrollmentSalesType}"> <input type="hidden"
					name="ahmGlobalCd" value="${dto.ahmGlobalCd}"> <input
					type="hidden" name="enrollmentStatus"
					value="${dto.enrollmentStatus}"> <a href="${backVinPage}"><button
						type="button">Back</button></a> &nbsp;&nbsp;&nbsp; <input
					type="submit" name="enroll" value="Change of Ownership">
			</form>
		</div>
	</div>
</body>

</html>