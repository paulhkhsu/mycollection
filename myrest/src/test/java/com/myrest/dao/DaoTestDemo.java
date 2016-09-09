package com.myrest.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppInitConfig.class, AppMainConfig.class }, loader = AnnotationConfigWebContextLoader.class)
@ActiveProfiles("dev")
public class DaoTestDemo {


	@Autowired
	private EmployeeDao employeeDao;

	@Before
	public void setup() {
	}
	@Test
	public void testOrderService() {
		System.out.println(employeeDao.count());
	}
}
