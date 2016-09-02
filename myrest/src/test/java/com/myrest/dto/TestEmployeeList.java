package com.myrest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.myrest.pojo.Employee;

public class TestEmployeeList {

	public static void main(String[] args) throws Exception {
		EmployeeList empl = new EmployeeList();
		Employee e1 = new Employee();
		e1.setEmplNumber(1);
		e1.setId(1);
		e1.setName("1");

		Employee e2 = new Employee();
		e2.setEmplNumber(2);
		e2.setId(2);
		e2.setName("2");

		List<Employee> l = new ArrayList<Employee>();
		
		l.add(e1); l.add(e2);
		empl.setList(l);
		
		
		
		JAXBContext jc = JAXBContext.newInstance(EmployeeList.class, Employee.class);

		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(empl, System.out);

	}
}