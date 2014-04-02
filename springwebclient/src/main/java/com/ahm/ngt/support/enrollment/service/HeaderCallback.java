package com.ahm.ngt.support.enrollment.service;

import java.io.IOException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.xml.transform.StringSource;

import com.ahm.ngt.support.enrollment.util.JaxbUtil;
import com.ahm.ngt.support.enrollment.xmlbo.HondaHeaderType;

public class HeaderCallback implements WebServiceMessageCallback {

	String op;

	public HeaderCallback(String operation) {
		this.op = operation;
	}

	@Override
	public void doWithMessage(WebServiceMessage message) throws IOException,
			TransformerException {

		SoapMessage soapMessage = (SoapMessage) message;
		SoapHeader soapHeader = soapMessage.getSoapHeader();
		HondaHeaderType h = new HondaHeaderType();

		String headerStr = JaxbUtil.marshallObject(HondaHeaderType.class, h);
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StringSource s = new StringSource(headerStr);
		transformer.transform(s, soapHeader.getResult());
	
		soapMessage.setSoapAction(op);

	}

}
