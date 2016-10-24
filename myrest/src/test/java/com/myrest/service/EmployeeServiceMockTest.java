package com.myrest.service;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.myrest.config.AppInitConfig;
import com.myrest.config.AppMainConfig;
import com.myrest.dao.EmployeeDao;
import com.myrest.pojo.Employee;

@RunWith(SpringJUnit4ClassRunner.class)


@WebAppConfiguration
@ContextConfiguration(classes = { AppInitConfig.class, AppMainConfig.class }, loader = AnnotationConfigWebContextLoader.class)
@ActiveProfiles("dev")
public class EmployeeServiceMockTest {

	@Mock
	private EmployeeDao employeeDao;

	@InjectMocks
	private EmployeeService employeeService = new EmployeeServiceImpl();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testfindById() {
		Employee e = new Employee();
		e.setEmplNumber(1);
		e.setId(16);
		e.setName("fffffff");

		when(employeeDao.findOne(16)).thenReturn(e);
		//Employee ee = employeeDao.findOne(16);
		//System.out.println(ee.getId());
		Employee eps = employeeService.findById(16);
		System.out.println(eps.getName());
	}
}
