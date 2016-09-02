package com.myrest.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myrest.dto.EmployeeList;
import com.myrest.pojo.Employee;
import com.myrest.service.EmployeeService;
/**
 * @author Paul Hsu
 */
@RestController
public class VersionController {

	static private final Logger log = LogManager
			.getLogger(VersionController.class.getName());
	@Autowired
	private EmployeeService employeeService;

	// the way to control version
	// @RequestMapping(value = "/version/employee", method = RequestMethod.GET,
	// produces = {
	// "application/vnd.company.app-1.0+json",
	// "application/vnd.company.app-1.0+xml" })
	@RequestMapping(value = "/version/employee", method = RequestMethod.GET, headers = "App-Version=v1")
	@ResponseBody
	public EmployeeList getAllV1() {
		log.debug("this is version 1");
		List<Employee> l = employeeService.findAll();
		EmployeeList ret = new EmployeeList();
		ret.setList(l);
		return ret;
	}

	// @RequestMapping(value = "/version/employee", method = RequestMethod.GET,
	// produces = {
	// "application/vnd.company.app-2.0+json",
	// "application/vnd.company.app-2.0+xml" })
	@RequestMapping(value = "/version/employee", method = RequestMethod.GET, headers = "App-Version=v2")
	@ResponseBody
	public EmployeeList getAllV2() {
		log.debug("this is version 2");
		List<Employee> l = employeeService.findAll();
		EmployeeList ret = new EmployeeList();
		ret.setList(l);
		return ret;
	}

}
