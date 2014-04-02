package com.ahm.ngt.support.enrollment.service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import ahm_ngt_telematicsenrollment_library.telematics.DeleteEnrollmentByVIN;

import com.ahm.ngt.support.enrollment.dto.EnrollmentDto;
import com.ahm.ngt.support.enrollment.dto.SearchByVINDto;
import com.honda.ngt.VIN;
import com.honda.ngt.dealer.Dealer;
import com.honda.ngt.dealer.SalesPerson;
import com.honda.ngt.telematics.Business;
import com.honda.ngt.telematics.CustomerAccount;
import com.honda.ngt.telematics.Enrollment;
import com.honda.ngt.telematics.SearchBYVIN;
import com.honda.ngt.telematics.SearchByVINResponse;
import com.honda.ngt.vehicle.Vehicle;
import com.ngt.ahm.telematicsenrollmentservice.DeleteEnrollmentByVINResponse;
import com.ngt.ahm.telematicsenrollmentservice.SaveEnrollment;
import com.ngt.ahm.telematicsenrollmentservice.SaveEnrollmentResponse;
import com.ngt.ahm.telematicsenrollmentservice.SearchEnrollmentByVIN;
import com.ngt.ahm.telematicsenrollmentservice.SearchEnrollmentByVINResponse;

@Component("enrollmentWebServiceClientImpl")
public class EnrollmentWebServiceClientImpl implements
		EnrollmentWebServiceClient {

	private static final com.ngt.ahm.telematicsenrollmentservice.ObjectFactory SERVICE = new com.ngt.ahm.telematicsenrollmentservice.ObjectFactory();
	private static final com.honda.ngt.telematics.ObjectFactory TELEM = new com.honda.ngt.telematics.ObjectFactory();
	private static final com.honda.ngt.ObjectFactory NGT = new com.honda.ngt.ObjectFactory();
	private static final com.honda.ngt.vehicle.ObjectFactory VECHICLE = new com.honda.ngt.vehicle.ObjectFactory();
	private static final com.honda.ngt.dealer.ObjectFactory DEALER = new com.honda.ngt.dealer.ObjectFactory();
    private static final ahm_ngt_telematicsenrollment_library.telematics.ObjectFactory AHM = new ahm_ngt_telematicsenrollment_library.telematics.ObjectFactory();
	@Autowired
	private WebServiceTemplate webServiceTemplate;

	public SearchByVINDto searchEnrollmentByVIN(String vinStr, String division,
			String dealerNo) {

		SearchEnrollmentByVIN request = SERVICE.createSearchEnrollmentByVIN();

		SearchBYVIN SearchBYVIN = TELEM.createSearchBYVIN();
		VIN vin = NGT.createVIN();
		vin.setVin(vinStr);
		SearchBYVIN.setVIN(vin);

		Dealer dealer = DEALER.createDealer();
		dealer.setDealerDivision(division);
		dealer.setDealerNo(dealerNo);
		SearchBYVIN.setDealer(dealer);

		request.setSearchBYVINRequest(SearchBYVIN);
		SearchEnrollmentByVINResponse response = (SearchEnrollmentByVINResponse) webServiceTemplate
				.marshalSendAndReceive(request, new HeaderCallback(EnrollmentWebServiceClient.SEARCHBYVIN));

		SearchByVINResponse resp = response.getSearchBYVINResponse();
		String searchStatus = resp.getSearchStatus();

		SearchByVINDto searchByVINDto = new SearchByVINDto();
		searchByVINDto.setSearchStatus(searchStatus);

		// VIN NOT FOUND AND NOT ENROLLMENT AT ALL
		if (searchStatus.equalsIgnoreCase(VINNNOTFOUND)) {
			return searchByVINDto;
		}
		List<Enrollment> enrollments = resp.getEnrollments().getValue()
				.getEnrollment();

		// VIN found but not enrollment info at all
		// return one enrollment only with car information
		if (searchStatus.equalsIgnoreCase(ENROLLMENTNOTFOUND)) {
			for (Enrollment enroll : enrollments) {
				EnrollmentDto dto = setVehicleInformation(enroll);
				searchByVINDto.addEnrollment(dto);
			}
			return searchByVINDto;
		}

		for (Enrollment enroll : enrollments) {
			EnrollmentDto dto = fromEnrollmentToDto(enroll);
			searchByVINDto.addEnrollment(dto);
		}

		return searchByVINDto;
	}

	@Override
	public EnrollmentDto saveEnrollment(EnrollmentDto dto) {

		SaveEnrollment request = SERVICE.createSaveEnrollment();
		Enrollment enrollment = TELEM.createEnrollment();

		enrollment.setEnrollmentStatus(dto.getEnrollmentStatus());
		enrollment.setEnrollmentSalesType(dto.getEnrollmentSalesType());
		request.setSaveEnrollmentRequest(enrollment);

		Vehicle vehicle = VECHICLE.createVehicle();
		vehicle.setMake(dto.getMake());
		vehicle.setBaseColor(dto.getBaseColor());
		vehicle.setModel(dto.getModel());
		vehicle.setModelSeries(dto.getModelSeries());
		vehicle.setModelYear(dto.getModelYear());
		vehicle.setColorDesc(dto.getColorDesc());

		VIN vin = NGT.createVIN();
		vin.setVin(dto.getVin());
		vehicle.setVin(vin);
		enrollment.setEnrollmentVehicle(vehicle);

		CustomerAccount customer = TELEM.createCustomerAccount();
		customer.setCustomerTitle(dto.getCustomerTitle());
		customer.setHomeState(dto.getHomeState());
		customer.setCustomerFirstName(dto.getCustomerFirstName());
		customer.setCustomerLastName(dto.getCustomerLastName());
		customer.setCustomerMiddleName(dto.getCustomerMiddleName());
		customer.setHomeAddress(dto.getHomeAddress());
		customer.setHomeCity(dto.getHomeCity());
		customer.setHomeZipCd(dto.getHomeZipCd());
		customer.setHomeCountry(dto.getHomeCountry());
		customer.setHomePhoneNumber(dto.getHomePhoneNumber());
		customer.setCellPhoneNumber(dto.getCellPhoneNumber());
		customer.setWorkPhoneNumber(dto.getWorkPhoneNumber());
		customer.setPrimaryEmail(dto.getPrimaryEmail());
		customer.setPreferredLanguage(dto.getPreferredLanguage());
		enrollment.setEnrollmentCustomer(customer);

		Dealer dealer = DEALER.createDealer();
		dealer.setDealerDivision(dto.getDealerDivision());
		dealer.setDealerNo(dto.getDealerNo());
		enrollment.setEnrollmentDealer(dealer);

		// sales person
		SalesPerson salesPerson = DEALER.createSalesPerson();
		salesPerson.setSalesPersonFirstName(dto.getSalesPersonFirstName());
		salesPerson.setSalesPersonLastName(dto.getSalesPersonLastName());
		salesPerson.setSalesPersonNo(dto.getSalesPersonNo());

		enrollment.setEnrollmentSalesPerson(salesPerson);

		SaveEnrollmentResponse response = (SaveEnrollmentResponse) webServiceTemplate
				.marshalSendAndReceive(request, new HeaderCallback(EnrollmentWebServiceClient.SAVEENROLLMENT));

		Enrollment respEnroll = response.getSaveEnrollmentResponse();
		System.out.println(respEnroll.getEnrollmentId());

		dto.setEnrollmentId(respEnroll.getEnrollmentId().toString());
		dto.setEnrollmentStatus(respEnroll.getEnrollmentStatus());
		return dto;

	}

	public String deleteEnrollmentByVIN(String vinStr, String division,
			String dealerNo) {

		com.ngt.ahm.telematicsenrollmentservice.DeleteEnrollmentByVIN request = SERVICE.createDeleteEnrollmentByVIN();

		DeleteEnrollmentByVIN deleteByVin = AHM.createDeleteEnrollmentByVIN();
		VIN vin = NGT.createVIN();
		vin.setVin(vinStr);
		deleteByVin.setVIN(vin);

		Dealer dealer = DEALER.createDealer();
		dealer.setDealerDivision(division);
		dealer.setDealerNo(dealerNo);
		deleteByVin.setDealer(dealer);
		
		request.setDeleteEnrollmentByVINRequest(deleteByVin);
		DeleteEnrollmentByVINResponse response = (DeleteEnrollmentByVINResponse) webServiceTemplate
				.marshalSendAndReceive(request, new HeaderCallback(EnrollmentWebServiceClient.DELETEBYVIN));

		return response.getStatus();
	}
	
	private EnrollmentDto setVehicleInformation(Enrollment enrollment) {
		EnrollmentDto dto = new EnrollmentDto();
		// vechicle information
		Vehicle vehicle = enrollment.getEnrollmentVehicle();
		dto.setVin(vehicle.getVin().getVin());
		dto.setMake(vehicle.getMake());
		dto.setModel(vehicle.getModel());
		dto.setModelSeries(vehicle.getModelSeries());
		dto.setModelYear(vehicle.getModelYear());
		dto.setBaseColor(vehicle.getBaseColor());
		dto.setColorDesc(vehicle.getColorDesc());
		return dto;
	}

	private EnrollmentDto fromEnrollmentToDto(Enrollment enrollment) {

		EnrollmentDto dto = new EnrollmentDto();
		dto.setEnrollmentId(enrollment.getEnrollmentId().toString());
		dto.setEnrollmentStatus(enrollment.getEnrollmentStatus());
		dto.setEnrollmentSalesType(enrollment.getEnrollmentSalesType());

		// vechicle information
		Vehicle vehicle = enrollment.getEnrollmentVehicle();
		dto.setVin(vehicle.getVin().getVin());
		dto.setMake(vehicle.getMake());
		dto.setModel(vehicle.getModel());
		dto.setModelSeries(vehicle.getModelSeries());
		dto.setModelYear(vehicle.getModelYear());
		dto.setBaseColor(vehicle.getBaseColor());
		dto.setColorDesc(vehicle.getColorDesc());

		// customer account
		CustomerAccount customer = enrollment.getEnrollmentCustomer();
		dto.setHomeState(customer.getHomeState());
		dto.setAhmGlobalCd(customer.getAhmGlobalCd());
		dto.setCustomerFirstName(customer.getCustomerFirstName());
		dto.setCustomerLastName(customer.getCustomerLastName());
		dto.setCustomerMiddleName(customer.getCustomerMiddleName());
		dto.setHomeAddress(customer.getHomeAddress());
		dto.setHomeCity(customer.getHomeCity());
		dto.setHomeZipCd(customer.getHomeZipCd());
		dto.setHomeCountry(customer.getHomeCountry());
		dto.setHomePhoneNumber(customer.getHomePhoneNumber());
		dto.setCellPhoneNumber(customer.getCellPhoneNumber());
		dto.setPrimaryEmail(customer.getPrimaryEmail());
		dto.setPreferredLanguage(customer.getPreferredLanguage());

		// sales person data
		SalesPerson salesPerson = enrollment.getEnrollmentSalesPerson();
		dto.setSalesPersonNo(salesPerson.getSalesPersonNo());
		dto.setSalesPersonFirstName(salesPerson.getSalesPersonFirstName());
		dto.setSalesPersonLastName(salesPerson.getSalesPersonLastName());

		// dealer info
		Dealer dealer1 = enrollment.getEnrollmentDealer();
		dto.setDealerDivision(dealer1.getDealerDivision());
		dto.setDealerNo(dealer1.getDealerNo());

		// last updated date
		GregorianCalendar calend = enrollment.getLastUpdateTimestamp()
				.toGregorianCalendar();
		Date dt = new Date(calend.getTimeInMillis());

		dto.setLastUpdateTimestamp(dt);

		return dto;
	}

	private EnrollmentDto fromDtoToEnrollment(EnrollmentDto dto) {
		Enrollment enrollment = TELEM.createEnrollment();

		enrollment.setEnrollmentId(Long.parseLong(dto.getEnrollmentId()));
		enrollment.setEnrollmentSalesType(dto.getEnrollmentSalesType());
		enrollment.setEnrollmentSalesClass(dto.getEnrollmentSalesClass());

		// VIN parameter
		VIN vin = NGT.createVIN();
		vin.setVin(dto.getVin());

		// vehicle
		Vehicle vehicle = VECHICLE.createVehicle();
		vehicle.setVin(vin);
		vehicle.setMake(dto.getMake());
		vehicle.setModel(dto.getModel());
		vehicle.setModelSeries(dto.getModelSeries());
		vehicle.setModelYear(dto.getModelYear());
		vehicle.setBaseColor(dto.getBaseColor());
		vehicle.setColorDesc(dto.getColorDesc());

		enrollment.setEnrollmentVehicle(vehicle);

		// customer info
		CustomerAccount customer = TELEM.createCustomerAccount();
		customer.setHomeState(dto.getHomeState());
		customer.setAhmGlobalCd(dto.getAhmGlobalCd());
		customer.setCustomerFirstName(dto.getCustomerFirstName());
		customer.setCustomerLastName(dto.getCustomerLastName());
		customer.setCustomerMiddleName(dto.getCustomerMiddleName());
		customer.setHomeAddress(dto.getHomeAddress());
		customer.setHomeCity(dto.getHomeCity());
		customer.setHomeZipCd(dto.getHomeZipCd());
		customer.setHomeCountry(dto.getHomeCountry());
		customer.setHomePhoneNumber(dto.getHomePhoneNumber());
		customer.setCellPhoneNumber(dto.getCellPhoneNumber());
		customer.setWorkPhoneNumber(dto.getWorkPhoneNumber());
		customer.setWorkPhoneExt(dto.getWorkPhoneExt());
		customer.setPrimaryEmail(dto.getPrimaryEmail());
		customer.setPreferredLanguage(dto.getPreferredLanguage());
		Business business = new Business();
		business.setBusinessName("");
		customer.setBusinessInfo(business);

		enrollment.setEnrollmentCustomer(customer);

		// sales person
		SalesPerson salesPerson = DEALER.createSalesPerson();
		salesPerson.setSalesPersonFirstName(dto.getSalesPersonFirstName());
		salesPerson.setSalesPersonLastName(dto.getSalesPersonLastName());
		salesPerson.setSalesPersonNo(dto.getSalesPersonNo());

		enrollment.setEnrollmentSalesPerson(salesPerson);

		// dealer
		Dealer dealer = DEALER.createDealer();
		dealer.setDealerDivision(DEVISION);
		dealer.setDealerName("");
		dealer.setDealerNo(DEALERID);

		enrollment.setEnrollmentDealer(dealer);

		return dto;
	}

}
