package com.myrest.dto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		String jsonInString = mapper.writeValueAsString(customer);
		System.out.println(jsonInString);

	}
}