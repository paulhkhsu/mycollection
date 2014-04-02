package com.ahm.ngt.support.enrollment.util;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JaxbUtil {

	public static String marshallObject(Class clz, Object obj) {
		StringWriter sw = new StringWriter();
		try {
			JAXBContext ctx = JAXBContext.newInstance(clz);
			Marshaller m = ctx.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(obj, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return sw.toString();
	}
}
