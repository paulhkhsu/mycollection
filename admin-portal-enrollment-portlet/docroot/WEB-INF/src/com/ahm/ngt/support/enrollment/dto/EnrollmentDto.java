package com.ahm.ngt.support.enrollment.dto;

/**
 * @author Paul Hsu
 *
 */
import java.io.Serializable;
import java.util.Date;

public class EnrollmentDto extends DtoBase implements Serializable {
	private static final long serialVersionUID = 3330236345388080808L;
	private String enrollmentId = "";
	private String enrollmentStatus = "";
	private String enrollmentSalesType = "";
	private String enrollmentSalesClass = "";
	private String vin = "";
	private String make = "";
	private String model = "";
	private String modelSeries = "";
	private String modelYear = "";
	private String baseColor = "";
	private String colorDesc = "";
	private String ahmGlobalCd = "";
	private String customerTitle = "";
	private String customerFirstName = "";
	private String customerLastName = "";
	private String customerMiddleName = "";
	private String homeAddress = "";
	private String homeCity = "";
	private String homeState = "";
	private String homeZipCd = "";
	private String homeCountry = "";
	private String homePhoneNumber = "";
	private String workPhoneNumber = "";
	private String workPhoneExt = "";
	private String cellPhoneNumber = "";
	private String primaryEmail = "";
	private String preferredLanguage = "";
	private String salesPersonNo = "";
	private String salesPersonFirstName = "";
	private String salesPersonLastName = "";
	private String dealerNo = "";
	private String dealerName = "";
	private String dealerDivision = "";
	private Date lastUpdateTimestamp = new Date();

	public String getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(String enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}

	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}

	public String getEnrollmentSalesType() {
		return enrollmentSalesType;
	}

	public void setEnrollmentSalesType(String enrollmentSalesType) {
		this.enrollmentSalesType = enrollmentSalesType;
	}

	public String getEnrollmentSalesClass() {
		return enrollmentSalesClass;
	}

	public void setEnrollmentSalesClass(String enrollmentSalesClass) {
		this.enrollmentSalesClass = enrollmentSalesClass;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModelSeries() {
		return modelSeries;
	}

	public void setModelSeries(String modelSeries) {
		this.modelSeries = modelSeries;
	}

	public String getModelYear() {
		return modelYear;
	}

	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}

	public String getBaseColor() {
		return baseColor;
	}

	public void setBaseColor(String baseColor) {
		this.baseColor = baseColor;
	}

	public String getColorDesc() {
		return colorDesc;
	}

	public void setColorDesc(String colorDesc) {
		this.colorDesc = colorDesc;
	}

	public String getAhmGlobalCd() {
		return ahmGlobalCd;
	}

	public void setAhmGlobalCd(String ahmGlobalCd) {
		this.ahmGlobalCd = ahmGlobalCd;
	}

	public String getCustomerTitle() {
		return customerTitle;
	}

	public void setCustomerTitle(String customerTitle) {
		this.customerTitle = customerTitle;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerMiddleName() {
		return customerMiddleName;
	}

	public void setCustomerMiddleName(String customerMiddleName) {
		this.customerMiddleName = customerMiddleName;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	public String getHomeState() {
		return homeState;
	}

	public void setHomeState(String homeState) {
		this.homeState = homeState;
	}

	public String getHomeZipCd() {
		return homeZipCd;
	}

	public void setHomeZipCd(String homeZipCd) {
		this.homeZipCd = homeZipCd;
	}

	public String getHomeCountry() {
		return homeCountry;
	}

	public void setHomeCountry(String homeCountry) {
		this.homeCountry = homeCountry;
	}

	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}

	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}

	public String getWorkPhoneNumber() {
		return workPhoneNumber;
	}

	public void setWorkPhoneNumber(String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}

	public String getWorkPhoneExt() {
		return workPhoneExt;
	}

	public void setWorkPhoneExt(String workPhoneExt) {
		this.workPhoneExt = workPhoneExt;
	}

	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}

	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public String getSalesPersonNo() {
		return salesPersonNo;
	}

	public void setSalesPersonNo(String salesPersonNo) {
		this.salesPersonNo = salesPersonNo;
	}

	public String getSalesPersonFirstName() {
		return salesPersonFirstName;
	}

	public void setSalesPersonFirstName(String salesPersonFirstName) {
		this.salesPersonFirstName = salesPersonFirstName;
	}

	public String getSalesPersonLastName() {
		return salesPersonLastName;
	}

	public void setSalesPersonLastName(String salesPersonLastName) {
		this.salesPersonLastName = salesPersonLastName;
	}

	public String getDealerNo() {
		return dealerNo;
	}

	public void setDealerNo(String dealerNo) {
		this.dealerNo = dealerNo;
	}

	public String getDealerDivision() {
		return dealerDivision;
	}

	public void setDealerDivision(String dealerDivision) {
		this.dealerDivision = dealerDivision;
	}

	public Date getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}

	public void setLastUpdateTimestamp(Date lastUpdateTimestamp) {
		this.lastUpdateTimestamp = lastUpdateTimestamp;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
