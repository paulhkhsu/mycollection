package com.myrest.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "result")
public class ResultBaseDto {
	private String code = "0";

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
