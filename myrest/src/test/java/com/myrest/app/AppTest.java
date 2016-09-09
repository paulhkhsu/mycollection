package com.myrest.app;


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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.myrest.config.AppInitConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppInitConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@ActiveProfiles("dev")
public class AppTest {
	private MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void simple() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/"))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.view().name("index"));
	}
}