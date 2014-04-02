package com.ahm.ngt.springwebclient;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ahm.ngt.support.enrollment.dto.EnrollmentDto;
import com.ahm.ngt.support.enrollment.dto.SearchByVINDto;
import com.ahm.ngt.support.enrollment.service.EnrollmentWebServiceClient;

public class Testconfig {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("app.xml");
		String[] beans = ac.getBeanDefinitionNames();
		for (String s : beans)
			System.out.println(s);

		EnrollmentWebServiceClient se = (EnrollmentWebServiceClient) ac
				.getBean("enrollmentWebServiceClientImpl");
		SearchByVINDto dto = se.searchEnrollmentByVIN("JH4KC1F54EC999008", "B", "990001");
		System.out.println(dto);

		String searchStatus = dto.getSearchStatus();
		
		if (searchStatus.equalsIgnoreCase(EnrollmentWebServiceClient.VINNNOTFOUND)) {
			return ;
		}
		if (searchStatus.equalsIgnoreCase(EnrollmentWebServiceClient.ENROLLMENTNOTFOUND)) {
			return;
		}



	Iterator<EnrollmentDto> iter = dto.getEnrollmentDtos().iterator();

		EnrollmentDto first = (EnrollmentDto) iter.next();

		EnrollmentDto dto1 = se.saveEnrollment(first);
		System.out.println(dto1);
		
		se.deleteEnrollmentByVIN("JH4KC1F54EC999008", "B", "990001");
	}
}
