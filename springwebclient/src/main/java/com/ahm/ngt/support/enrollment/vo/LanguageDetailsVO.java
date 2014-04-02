package com.ahm.ngt.support.enrollment.vo;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class LanguageDetailsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String languageCode;
	private String languageName;
	private String isDefault;

	

	/**
	 * Gets the value of the languageCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLanguageCode() {
		return languageCode;
	}
		
	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * Sets the value of the languageCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLanguageCode(String value) {
		this.languageCode = value;
	}

	/**
	 * Gets the value of the languageName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLanguageName() {
		return languageName;
	}

	/**
	 * Sets the value of the languageName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLanguageName(String value) {
		this.languageName = value;
	}

}
