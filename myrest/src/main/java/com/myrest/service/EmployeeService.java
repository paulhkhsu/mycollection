package com.myrest.service;

import java.util.List;

import com.myrest.exception.RecordNotFoundException;
import com.myrest.pojo.Employee;

public interface EmployeeService {
	
	public Employee create(Employee emp);
	public Employee delete(int id) throws RecordNotFoundException;
	public List<Employee> findAll();
	public Employee update(Employee emp) throws RecordNotFoundException;
	public Employee findById(int id);
	public Employee findByName(String name);

}
