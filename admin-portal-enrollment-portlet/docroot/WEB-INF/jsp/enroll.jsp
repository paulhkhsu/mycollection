<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
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

<portlet:actionURL var="next">
	<portlet:param name="action" value="save"></portlet:param>
</portlet:actionURL>

<html>
<head>
<meta content="text/html; charset=ISO-8859-1" http-equiv="content-type">
<title>Enrollment</title>
<script type="text/javascript" src="${context}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${context}/js/enroll.js"></script>
</head>
<body>
	<div class="div-center">
		<div class="div-title-center">Telematics Enrollment</div>

		<br>
		<form id="enrollform" action="${next}" method="post">
			<input type="hidden" name="vin" value="${dto.vin}"> <input
				type="hidden" name="make" value="${dto.make}"> <input
				type="hidden" name="model" value="${dto.model}"> <input
				type="hidden" name="modelSeries" value="${dto.modelSeries}">
			<input type="hidden" name="modelYear" value="${dto.modelYear}">
			<input type="hidden" name="baseColor" value="${dto.baseColor}">
			<input type="hidden" name="colorDesc" value="${dto.colorDesc}">
			<input type="hidden" name="enrollmentSalesType" value="Individual">
			<input type="hidden" name="enrollmentStatus" value="PENDING">
			<input type="hidden" name="agentFirstName" value="${agentFirstName}">
			<input type="hidden" name="agentLastName" value="${agentLastName}">
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
							<td>PENDING</td>
						</tr>
						<tr>
							<td class="tdlabel">Sales Type</td>
							<td>Individual</td>
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
							<td><select name="customerTitle">
									<option value="Mr.">Mr.</option>
									<option value="Mrs.">Mrs.</option>
									<option value="Ms.">Ms.</option>
									<option value="Dr.">Dr.</option>
							</select></td>

							<td class="tdlabel"><label for="customerFirstName"
								class="req">First Name</label></td>
							<td><input name="customerFirstName" type="text"
								id="customerFirstName" class="capitalize" maxlength="15"></td>
							<td class="tdlabel"><label for="customerLastName"
								class="req">Last Name</label></td>
							<td><input name="customerLastName" type="text"
								id="customerLastName" class="capitalize" maxlength="30"
								size="23"></td>
						</tr>
						<tr>
							<td class="tdlabel"><label for="homeAddress" class="req">Address</label></td>
							<td colspan="2"><input name="homeAddress" type="text"
								id="homeAddress" size="40"></td>
						</tr>
						<tr>
							<td class="tdlabel"><label for="homeCity" class="req">City</label></td>
							<td><input name="homeCity" type="text" id="homeCity"
								class="capitalize"></td>
							<td class="tdlabel"><label for="homeState" class="req">State</label></td>
							<td><input name="homeState" type="text" id="homeState"
								class="uppercase" maxlength="2" size="2"></td>
							<td class="tdlabel"><label for="homeZipCd" class="req">Zip
									Code</label></td>
							<td><input name="homeZipCd" type="text" id="homeZipCd"
								maxlength="10" size="10"></td>
						</tr>
						<tr>
							<td class="tdlabel"><label for="" class="req">Telephone</label></td>
						</tr>
						<tr>
							<td class="tdlabel"><label for="workPhoneNumber"
								class="noreq">Work</label></td>
							<td><input name="workPhoneNumber" type="text"
								id="workPhoneNumber"></td>
							<td class="tdlabel"><label for="homePhoneNumber"
								class="noreq">Home</label></td>
							<td><input name="homePhoneNumber" type="text"
								id="homePhoneNumber"></td>
							<td class="tdlabel"><label for="cellPhoneNumber"
								class="noreq">Cell</label></td>
							<td><input name="cellPhoneNumber" type="text"
								id="cellPhoneNumber"></td>
						</tr>
						<tr>
							<td class="tdlabel"><label for="primaryEmail" class="req">Email</label></td>
							<td><input name="primaryEmail" type="text" id="primaryEmail"></td>
						</tr>
						<tr>
							<td class="tdlabel"><label for="" class="req">Pref
									Language</label></td>
							<td>English</td>
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
				<a href="${backVinPage}">
					<button type="button">Back</button>
				</a> &nbsp;&nbsp;&nbsp; <input type="submit" id="submit" value="Next">
			</div>

		</form>
	</div>
</body>
</html>