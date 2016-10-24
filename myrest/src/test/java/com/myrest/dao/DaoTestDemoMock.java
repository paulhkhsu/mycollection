package com.myrest.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.myrest.config.AppInitConfig;
import com.myrest.config.AppMainConfig;
import com.myrest.pojo.Employee;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppInitConfig.class, AppMainConfig.class }, loader = AnnotationConfigWebContextLoader.class)
@ActiveProfiles("dev")
public class DaoTestDemoMock {

	@Mock
	private EmployeeDao employeeDao;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testOrderService() {
		Employee e = new Employee();
		e.setEmplNumber(1);
		e.setId(2);
		e.setName("fff");
		when(employeeDao.save(any(Employee.class))).thenReturn(e);
		Employee e1 = new Employee();
        Employee ss = employeeDao.save(e1);
		System.out.println(ss.getName());
	}
}
