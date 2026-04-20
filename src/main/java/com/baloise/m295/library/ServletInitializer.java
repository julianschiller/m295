package com.baloise.m295.library;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Julian Schiller
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * Configures the application for WAR deployment
	 * 
	 * @param application the Spring application builder
	 * @return the configured application builder
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LibraryApplication.class);
	}

}
