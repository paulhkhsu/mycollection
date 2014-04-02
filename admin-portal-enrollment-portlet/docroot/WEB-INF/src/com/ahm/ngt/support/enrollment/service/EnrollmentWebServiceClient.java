package com.ahm.ngt.support.enrollment.service;
/**
 * @author Paul Hsu
 *
 */
import com.ahm.ngt.support.enrollment.dto.EnrollmentDto;
import com.ahm.ngt.support.enrollment.dto.SearchByVINDto;

public interface EnrollmentWebServiceClient {
	static public String VINNNOTFOUND = "VINNotFound";
	static public String ENROLLMENTFOUND = "EnrollmentFound";
	static public String ENROLLMENTNOTFOUND = "EnrollmentNotFound";
	static public String NOTSUBMITTED = "NOT SUBMITTED";
	static public String PENDING = "PENDING";
	static public String ACTIVE = "ACTIVE";
	static public String DEALERID = "990001";
	static public String DEVISION = "B";
	
	static public String SEARCHBYVIN="http://ahm.ngt.com/TelematicsEnrollmentService/TelematicsEnrollmentService/searchEnrollmentByVIN";
	static public String SAVEENROLLMENT="http://ahm.ngt.com/TelematicsEnrollmentService/TelematicsEnrollmentService/saveEnrollment";
	static public String DELETEBYVIN="http://ahm.ngt.com/TelematicsEnrollmentService/TelematicsEnrollmentService/deleteEnrollmentByVIN";
	
	public SearchByVINDto searchEnrollmentByVIN(String vinStr, String division, String dealerNo) ;
	public EnrollmentDto saveEnrollment(EnrollmentDto dto) ;
	public String deleteEnrollmentByVIN(String vinStr, String division, String dealerNo) ;
}
