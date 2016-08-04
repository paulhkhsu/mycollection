package com.myrest.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myrest.exception.RecordNotFoundException;
import com.myrest.pojo.Employee;
import com.myrest.service.EmployeeService;


/**
 * @author Paul Hsu
 */
@Controller
public class EmployeeController {

	static private final Logger log = LogManager.getLogger(EmployeeController.class
			.getName());
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/rest/employee", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee> getAll() {
		log.debug("getAll");
		List<Employee> l = employeeService.findAll();
		log.debug(l.get(0).getName());
		return l;
	}
	
	@RequestMapping(value="/rest/employee/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Employee get(@PathVariable Integer id) {
		Employee emp = employeeService.findById(id);
		log.debug(emp.getName());
		return emp;
	}

	@RequestMapping(value = "/rest/employee", method = RequestMethod.POST)
	@ResponseBody
	public Employee add(@Valid @RequestBody Employee emp) throws RecordNotFoundException {
		log.debug("Received employee: {}", emp);
		employeeService.create(emp);
		if(emp.getName().equalsIgnoreCase("badname"))
			throw new RecordNotFoundException("Employee not found");
		return emp;
	}
}
