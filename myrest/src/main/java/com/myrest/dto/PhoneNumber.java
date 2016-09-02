package com.myrest.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PhoneNumber extends ContactInfo {
 
	
	private String phoneNo;

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
}