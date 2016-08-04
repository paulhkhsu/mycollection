package com.myrest.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Paul Hsu
 */
@Configuration
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

}
