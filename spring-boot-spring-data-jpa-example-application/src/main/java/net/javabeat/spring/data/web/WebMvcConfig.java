package net.javabeat.spring.data.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
//@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	// default
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		System.out.println("here2");
		configurer.defaultContentType(MediaType.APPLICATION_XML);
	}
}
