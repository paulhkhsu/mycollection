package com.myrest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myrest.pojo.Employee;

@XmlRootElement(name = "employees")
public class EmployeeList {

	private List<Employee> list;


    @JsonProperty("employees")
	@XmlElement(name = "employee")
	public List<Employee> getList() {
		return list;
	}

	public void setList(List<Employee> list) {
		this.list = list;
	}
}
