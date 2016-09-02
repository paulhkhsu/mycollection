package com.myrest.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.myrest.exception.RecordNotFoundException;
import com.myrest.pojo.Employee;
import com.myrest.service.EmployeeService;


/**
 * @author Paul Hsu
 */
@RestController
public class EmployeeController {

	static private final Logger log = LogManager.getLogger(EmployeeController.class
			.getName());
	@Autowired
	private EmployeeService employeeService;

	// the way to control version
	//@RequestMapping(value = "/rest/employee", method = RequestMethod.GET, produces={"application/vnd.company.app-1.0+json","application/vnd.company.app-1.0+xml"})

	// if we return List the application/xml won't work, need anohter java to return list see VersionContoller.java
	@RequestMapping(value = "/rest/employee", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee> getAll() {
		List<Employee> l = employeeService.findAll();
		return l;
	}
	
	@RequestMapping(value="/rest/employee/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Employee get(@PathVariable Integer id) {
		Employee emp = employeeService.findById(id);
		log.debug(emp.getName());
		return emp;
	}
 
	//headers="Accept=application/xml, application/json"
	//@RequestHeader(value="Accept",defaultValue="test", required=false) String accept
	@RequestMapping(value = "/rest/employee", method = RequestMethod.POST)
	@ResponseBody
	public Employee add(@Valid @RequestBody Employee emp) throws RecordNotFoundException {
		log.debug("Received employee: {}", emp);
		employeeService.create(emp);
		if(emp.getName().equalsIgnoreCase("badname"))
			throw new RecordNotFoundException("Employee not found");
		return emp;
	}
	
	@RequestMapping(value = "/rest/employee", method = RequestMethod.PUT)
	@ResponseBody
	public Employee update(@Valid @RequestBody Employee emp) throws RecordNotFoundException {
		log.debug("Received employee: {}", emp);
		Employee emp1 = employeeService.findById(emp.getId());
		if(null == emp1)
			throw new RecordNotFoundException("Employee not found");
		employeeService.update(emp);
		return emp;
	}

	
	@RequestMapping(value = "/rest/employee/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Employee delete(@PathVariable("id") int id) throws RecordNotFoundException {
		Employee emp = employeeService.findById(id);
		if(null == emp)
			throw new RecordNotFoundException("Employee not found");
		employeeService.delete(id);
		return emp;
	}

}
