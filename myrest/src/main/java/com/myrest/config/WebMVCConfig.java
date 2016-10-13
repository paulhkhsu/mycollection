package com.myrest.config;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.fasterxml.jackson.databind.SerializationFeature;
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

	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE);
		builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
		//converters.add(new MappingJackson2XmlHttpMessageConverter(builder.createXmlMapper(true).build()));
	}
	@Bean
	public UrlBasedViewResolver urlBasedViewResolver() {
		log.debug("setupViewResolver");
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

}
