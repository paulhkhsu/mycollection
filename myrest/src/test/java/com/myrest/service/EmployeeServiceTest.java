package com.myrest.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.myrest.config.AppInitConfig;
import com.myrest.config.AppMainConfig;
import com.myrest.pojo.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppInitConfig.class, AppMainConfig.class }, loader = AnnotationConfigWebContextLoader.class)
@ActiveProfiles("dev")
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@Before
	public void setup() {
	}

	@Test
	public void testfindById() {
		Employee e = employeeService.findById(16);
		System.out.println(e.getName());
	}
}
