package com.ahm.ngt.support.enrollment.xmlbo;
/**
 * @author Paul Hsu
 *
 */
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ahm.ngt.support.enrollment.util.DateUtil;

@XmlRootElement(name = "hondaHeaderType")
public class HondaHeaderType {

	@XmlElement
	String messageId = UUID.randomUUID().toString();
	@XmlElement
	String siteId = "iN";
	@XmlElement
	String businessId = "AHM";
	@XmlElement
	String collectedTimestamp;

	public HondaHeaderType() {
		collectedTimestamp = DateUtil.getXMLDate();
	}



}
