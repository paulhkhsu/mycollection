package com.myrest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Paul Hsu
 */
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class RestArgumentNotValidDto {

	private String errorCode;
	private String text;
	private List<FieldErrorDto> fields = new ArrayList<FieldErrorDto>();

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<FieldErrorDto> getFields() {
		return fields;
	}

	public void setFields(List<FieldErrorDto> fields) {
		this.fields = fields;
	}

	public void addFieldError(FieldErrorDto field) {
		
		this.fields.add(field);
	}
}
