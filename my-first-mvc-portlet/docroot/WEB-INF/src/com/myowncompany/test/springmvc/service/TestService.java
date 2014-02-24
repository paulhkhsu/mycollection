package com.myowncompany.test.springmvc.service;

import org.springframework.stereotype.Service;

import com.myowncompany.test.springmvc.dto.EnrollmentDto;

@Service
public class TestService {


	public EnrollmentDto getDto(){
		
		return new EnrollmentDto();
	}
}
