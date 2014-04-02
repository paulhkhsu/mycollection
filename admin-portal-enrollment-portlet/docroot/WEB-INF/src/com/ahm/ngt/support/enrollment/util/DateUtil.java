package com.ahm.ngt.support.enrollment.util;
/**
 * @author Paul Hsu
 *
 */
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtil {

	public static String getXMLDate() {
		GregorianCalendar c = new GregorianCalendar();
		XMLGregorianCalendar cd = null;
		try {
			cd = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}

		return cd.toString();
	}
}
