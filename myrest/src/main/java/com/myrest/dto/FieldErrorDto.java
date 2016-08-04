package com.myrest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Paul Hsu
 */
@XmlRootElement(name = "fields")
@XmlAccessorType(XmlAccessType.FIELD)
public class FieldErrorDto {

	private String field;

	private String message;

	public FieldErrorDto() {

	}

	public FieldErrorDto(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
}
