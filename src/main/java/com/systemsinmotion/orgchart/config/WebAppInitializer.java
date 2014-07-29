package com.systemsinmotion.orgchart.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	private static final String URL_PATTERN = "/*";

	public static final String CONFIG_PACKAGE = "com.systemsinmotion.orgchart.config";

	private static final String SPRING_SECURITY_FILTER_CHAIN = "springSecurityFilterChain";

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.scan(CONFIG_PACKAGE);

		addHttpMethodFilter(servletContext);

		springSecurityFilterChain(servletContext);

		ServletRegistration.Dynamic servlet = servletContext.addServlet(
				"dispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/app/*");
		servlet.setLoadOnStartup(1);

		servletContext.addListener(new ContextLoaderListener(ctx));
	}

	private void addHttpMethodFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic httpMethodFilter = servletContext.addFilter(
				"HttpMethodFilter", new HiddenHttpMethodFilter());
		httpMethodFilter.addMappingForUrlPatterns(null, false, URL_PATTERN);
	}

	private void springSecurityFilterChain(ServletContext servletContext) {
		FilterRegistration.Dynamic springSecurityFilterChain = servletContext
				.addFilter(SPRING_SECURITY_FILTER_CHAIN,
						new DelegatingFilterProxy());
		springSecurityFilterChain.addMappingForUrlPatterns(null, false,
				URL_PATTERN);
	}

}
