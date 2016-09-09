package com.myrest.profiledemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Profile("live")
public class CacheConfigLive {

	static private final Logger log = LogManager.getLogger(CacheConfigDev.class
			.getName());

	@Bean
	public CacheManager cacheManager() {
		log.debug("Cache manager is ehCacheCacheManager");
		return new EhCacheCacheManager();
	}

}