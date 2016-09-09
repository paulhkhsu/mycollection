package com.myrest.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author Paul Hsu
 */
public class AppInitConfig implements WebApplicationInitializer {
	static private final Logger log = LogManager.getLogger(AppInitConfig.class
			.getName());
	private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
	private static final String DISPATCHER_SERVLET_MAPPING = "/";

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		log.debug("onStartup");
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		//Enable a "live" profile
		rootContext.getEnvironment().setActiveProfiles("dev");
		rootContext.register(AppMainConfig.class);

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				DISPATCHER_SERVLET_NAME, new DispatcherServlet(rootContext));
		// set open view filter for JPA
		OpenEntityManagerInViewFilter oemivf = new OpenEntityManagerInViewFilter();
		oemivf.setServletContext(servletContext);
		servletContext.addFilter("openEntityManagerInViewFilter", oemivf)
				.addMappingForUrlPatterns(null, true, "/*");

		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(DISPATCHER_SERVLET_MAPPING);

		servletContext.addListener(new ContextLoaderListener(rootContext));
	}
}
