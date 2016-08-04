package com.myrest.config;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.myrest.interceptor.LoggingInterceptor;

/**
 * @author Paul Hsu
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableWebMvc
@ComponentScan(basePackages = { "com.myrest.service, com.myrest.controller, com.myrest.webcontroller, com.myrest.handler" })
public class WebMVCConfig extends WebMvcConfigurerAdapter {
	static private final Logger log = LogManager.getLogger(WebMVCConfig.class
			.getName());
	@Resource
	private Environment env;

	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		log.debug("configureDefaultServletHandling");
		configurer.enable();
	}

	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		log.debug("ContentNegotiationConfigurer");
		configurer.defaultContentType(MediaType.APPLICATION_XML);
	}

	public void addInterceptors(InterceptorRegistry registry) {
		log.debug("addInterceptors");
		registry.addInterceptor(new LoggingInterceptor());
	}

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		log.debug("setupViewResolver");
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

}
