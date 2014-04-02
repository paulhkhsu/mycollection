package com.ahm.ngt.support.enrollment.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonSerialize;
@XmlRootElement
@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class CountryDetailsVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String countryCode;
	private String countryName;
	private List<LanguageDetailsVO> languageDetails;

	/**
	 * Gets the value of the countryCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Sets the value of the countryCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCountryCode(String value) {
		this.countryCode = value;
	}

	/**
	 * Gets the value of the countryName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Sets the value of the countryName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCountryName(String value) {
		this.countryName = value;
	}

	/**
	 * Gets the value of the languageDetails property.
	 * 
	 * @return possible object is {@link LanguageDetailsVO }
	 * 
	 */
	public List<LanguageDetailsVO> getLanguageDetails() {
		return languageDetails;
	}

	/**
	 * Sets the value of the languageDetails property.
	 * 
	 * @param value
	 *            allowed object is {@link LanguageDetailsVO }
	 * 
	 */
	public void setLanguageDetails(List<LanguageDetailsVO> value) {
		this.languageDetails = value;
	}
}