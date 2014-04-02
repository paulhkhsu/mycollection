<SCRIPT type="text/javascript">
function fnSubmit() {
  document.sxmpage.submit();
  return;
}
</SCRIPT>

<body onload="fnSubmit()">
<form action="http://www.yahoo.com" name="sxmpage" method="post" >
			<input type="hidden" name="ahmGlobalCd" value="${dto.ahmGlobalCd}">
			<input type="hidden" name="dealerNo" value="${dto.dealerNo}">
			<input type="hidden" name="salesPersonNo" value="${dto.salesPersonNo}">
			<input type="hidden" name="salesPersonFirstName" value="${dto.salesPersonFirstName}">
			<input type="hidden" name="dealerName" value="${dto.dealerName}">
			<input type="hidden" name="vin" value="${dto.vin}">
			<input type="hidden" name="make" value="${dto.make}">
			<input type="hidden" name="model" value="${dto.model}">
			<input type="hidden" name="modelYear" value="${dto.modelYear}">
			<input type="hidden" name="baseColor" value="${dto.baseColor}">

			<input type="hidden" name="customerTitle" value="${dto.customerTitle}">
			<input type="hidden" name="customerFirstName" value="${dto.customerFirstName}">
			<input type="hidden" name="customerLastName" value="${dto.customerLastName}">
			<input type="hidden" name="primaryEmail" value="${dto.primaryEmail}">
			<input type="hidden" name="preferredLanguage" value="${dto.preferredLanguage}">
			<input type="hidden" name="homeAddress" value="${dto.homeAddress}">
			<input type="hidden" name="homeCity" value="${dto.homeCity}">

			<input type="hidden" name="homeState" value="${dto.homeState}">
			<input type="hidden" name="homeZipCd" value="${dto.homeZipCd}">
			<input type="hidden" name="homeCountry" value="${dto.homeCountry}">
			<input type="hidden" name="homePhoneNumber" value="${dto.homePhoneNumber}">
			<input type="hidden" name="workPhoneNumber" value="${dto.workPhoneNumber}">
			<input type="hidden" name="cellPhoneNumber" value="${dto.cellPhoneNumber}">
			<input type="hidden" name="cellPhoneNumber" value="${agentLastName}">
			<input type="hidden" name="dealerDivision" value="${dto.dealerDivision}">

</form>