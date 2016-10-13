package com.myrest.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Paul Hsu
 */
@Configuration
@ComponentScan(basePackages = { "com.myrest.validation",
		"com.myrest.profiledemo" })
@Import({ WebMVCConfig.class, DBConfig.class })
public class AppMainConfig {
	static private final Logger log = LogManager.getLogger(AppMainConfig.class
			.getName());
	static private String RESOUCE_BASE = "i18n/messages";

	@Bean
	public ResourceBundleMessageSource messageSource() {
		log.debug("messageSource");
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename(RESOUCE_BASE);
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
	// spring boot use this bean otherwise override configureMessageConverters()
	// in WebMVCConfig.java
	// @Bean
	// public Jackson2ObjectMapperBuilder jacksonBuilder() {
	// log.debug("Jackson2ObjectMapperBuilder");
	// Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
	// builder.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE); //
	// enables wrapping for root elements
	// return builder;
	// }
}
