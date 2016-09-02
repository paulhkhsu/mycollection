package com.myrest.dto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class TestCustomer {

	public static void main(String[] args) throws Exception {
		Customer customer = new Customer();
		Address address = new Address();
		address.setStreet("1 A Street");
		customer.setContactInfo(address);

		JAXBContext jc = JAXBContext.newInstance(Customer.class, Address.class,
				PhoneNumber.class);

		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(customer, System.out);

		PhoneNumber ph = new PhoneNumber();
		ph.setPhoneNo("11111");
		customer.setContactInfo(ph);

		marshaller.marshal(customer, System.out);

	}
}